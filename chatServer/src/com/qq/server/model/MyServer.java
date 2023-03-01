/**
 * 这是qq服务器，它在监听，等待某个qq客户端，来连接
 */
package com.qq.server.model;

import com.qq.server.db.*;
import com.qq.common.*;
import java.net.*;
import java.sql.*;
import java.io.*;
import java.util.*;

public class MyServer extends Thread {
	ServerSocket ss;
	StuModel sm;
	SqlHelper sqlh;

	public void run() {

		try {

			// 在9999监听
			//System.out.println("我是服务器，在监听");
			ss = new ServerSocket(9999);
			// 阻塞,等待连接
			while (true) {
				Socket s = ss.accept();
				String pw = null;
				// 接收客户端发来的信息.
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u = (User) ois.readObject();
				//System.out.println("服务器接收到用户id:" + u.getUserId() + "  密码:" + u.getPasswd());
				Message m = new Message();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				String l = u.getUserId();
				//连接数据库
				sqlh = new SqlHelper();
				String []paras = {l};
				//提取数据
				ResultSet rs = sqlh.queryExectue("select passWord from chat where userId=?", paras);
				while (rs.next()) {
					pw = rs.getString(1);
				}
//				//？？为啥不等
//				System.out.print(pw);
//				System.out.print(u.getPasswd());
//				if(u.getPasswd().equals(pw)) {
//					System.out.print("相等");
//				}else {
//					System.out.print("不相等");
//				}
				//登录验证
				if (u.getPasswd().trim().equals(pw.trim())) {
					// 返回一个成功登陆的信息报

					m.setMesType("1");
					oos.writeObject(m);

					// 这里就单开一个线程，让该线程与该客户端保持通讯.
					SerConClientThread scct = new SerConClientThread(s);
					ManageClientThread.addClientThread(u.getUserId(), scct);
					// 启动与该客户端通信的线程.
					scct.start();

					// 并通知其它在线用户.
					scct.notifyOther(u.getUserId());
				} else {
					m.setMesType("2");
					oos.writeObject(m);
					// 关闭Socket
					s.close();

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void Close() {
		try {
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
