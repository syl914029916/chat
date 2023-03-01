/**
 * ����qq�����������ڼ������ȴ�ĳ��qq�ͻ��ˣ�������
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

			// ��9999����
			//System.out.println("���Ƿ��������ڼ���");
			ss = new ServerSocket(9999);
			// ����,�ȴ�����
			while (true) {
				Socket s = ss.accept();
				String pw = null;
				// ���տͻ��˷�������Ϣ.
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u = (User) ois.readObject();
				//System.out.println("���������յ��û�id:" + u.getUserId() + "  ����:" + u.getPasswd());
				Message m = new Message();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				String l = u.getUserId();
				//�������ݿ�
				sqlh = new SqlHelper();
				String []paras = {l};
				//��ȡ����
				ResultSet rs = sqlh.queryExectue("select passWord from chat where userId=?", paras);
				while (rs.next()) {
					pw = rs.getString(1);
				}
//				//����Ϊɶ����
//				System.out.print(pw);
//				System.out.print(u.getPasswd());
//				if(u.getPasswd().equals(pw)) {
//					System.out.print("���");
//				}else {
//					System.out.print("�����");
//				}
				//��¼��֤
				if (u.getPasswd().trim().equals(pw.trim())) {
					// ����һ���ɹ���½����Ϣ��

					m.setMesType("1");
					oos.writeObject(m);

					// ����͵���һ���̣߳��ø��߳���ÿͻ��˱���ͨѶ.
					SerConClientThread scct = new SerConClientThread(s);
					ManageClientThread.addClientThread(u.getUserId(), scct);
					// ������ÿͻ���ͨ�ŵ��߳�.
					scct.start();

					// ��֪ͨ���������û�.
					scct.notifyOther(u.getUserId());
				} else {
					m.setMesType("2");
					oos.writeObject(m);
					// �ر�Socket
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
