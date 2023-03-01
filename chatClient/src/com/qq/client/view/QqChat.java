/**
 * 这是与好友聊天的界面
 * 因为客户端，要处于读取的状态，因此我们把它做成一个线程
 */
package com.qq.client.view;

import com.qq.client.tools.*;
import com.qq.client.db.*;
import com.qq.client.model.*;
import com.qq.common.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QqChat extends JFrame implements ActionListener {
	HomePanel jhp, jp;
	JTextArea jta;
	JTextField jtf;
	JButton jb;
	// JPanel jp;
	JScrollPane jsp1;
	String ownerId;
	String friendId;
	// 创建一个容器
	Container ct;
	SqlHelper sqlh, sqlh2, sqlh3;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// QqChat qqChat=new QqChat("1");
	}

	public QqChat(String ownerId, String friend) {
		this.ownerId = ownerId;
		this.friendId = friend;
//		System.out.println("宋宋"+ownerId+friend);
		ImageIcon imageIcon;
		JLabel imageLabel;
		// 设置jta属性
		jta = new JTextArea();
		jta.setEditable(false);
		jta.setBackground(Color.WHITE);
		jta.setOpaque(false);
//		Image image = new ImageIcon("image/chatbg.jpg").getImage();
		jta.setLineWrap(true); // 激活自动换行功能
		// jta.setWrapStyleWord(true); // 激活断行不断字功能
		Font x = new Font("宋体", 0, 14);
		jta.setFont(x);
		// jsp1.setOpaque(false);
		Image image = new ImageIcon("image/11.jpg").getImage();
		jhp = new HomePanel(image);
		jhp.setLayout(new BorderLayout());
		jhp.add(jta, "Center");
		jsp1 = new JScrollPane(jhp);
//		jta.setBackground((new ImageIcon("image/chatbg")).getImage());
		jtf = new JTextField(25);
		jb = new JButton("发送");
		jb.addActionListener(this);
		Image image2 = new ImageIcon("image/chatbg2.jpg").getImage();
		jp = new HomePanel(image2);
		jp.setBorder(BorderFactory.createTitledBorder("≧﹏≦　≧﹏≦　≧﹏≦　≧﹏≦　≧﹏≦　≧﹏≦　≧﹏≦　≧﹏≦　"));
		jp.add(jtf);
		jp.add(jb);

		this.add(jsp1, "Center");
		this.add(jp, "South");
//		this.add(bgp);
		String l = ownerId;
		// 连接数据库
		sqlh = new SqlHelper();
		String[] paras = { l };
		// 提取数据
		String pw = null;
		ResultSet rs = sqlh.queryExectue("select userName from chat where userId=?", paras);
		try {
			while (rs.next()) {
				pw = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s = friend;
		// 连接数据库
		sqlh2 = new SqlHelper();
		String[] paras1 = { s };
		// 提取数据
		// System.out.println("宋宋"+paras1[0]);
		String pw1 = null;
		ResultSet rs1 = sqlh2.queryExectue("select userName from chat where userId=?", paras1);
		try {
			while (rs1.next()) {
				pw1 = rs1.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("宋宋"+pw1);
		this.setTitle(pw.trim() + " " + "正在和 " + pw1.trim() + " " + "聊天");
		this.setIconImage((new ImageIcon("image/qq.gif").getImage()));
		this.setSize(400, 300);
		this.setVisible(true);
	}

	// 写一个方法，让它显示消息
	public void showMessage(Message m) { // " 对 "+m.getGetter()+
		 String a=m.getSender().trim();
		 sqlh3 = new SqlHelper();
			String []paras2 = {a};
			//提取数据
			String pw2 = null;
			ResultSet rs2 = sqlh3.queryExectue("select userName from chat where userId=?",paras2);
			try {
				while (rs2.next()) {
					pw2 = rs2.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		String info = m.getSendTime()+"\r\n"+pw2.trim() + " 对我说:"+"\r\n" + m.getCon() + "\r\n";
		this.jta.append(info);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == jb) {
			// 如果用户点击了，发送按钮
			Message m = new Message();
			m.setMesType(MessageType.message_comm_mes);
			// m.setMesDate(new java.util.Date().toString());
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			// System.out.println("啊啊啊啊啊"+m.getSender()+m.getGetter());
			m.setCon(jtf.getText());
			Date dd = new Date();

			// 格式化

			SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String time = sim.format(dd);
			m.setSendTime(time);
			jtf.setText("");
			jta.append("\t\t\t\t" + m.getSendTime()+ "\r\n"+"\t\t\t\t\t"+"我说:" + "\r\n" + "\t\t\t\t\t    " + m.getCon() + "\r\n");
			// 发送给服务器.
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}

		}

	}

//	public void run() {
//		// TODO Auto-generated method stub
//		while(true)
//		{
//			try {
//				//读取[如果读不到就等待.]
//				ObjectInputStream ois=new ObjectInputStream(QqClientConServer.s.getInputStream());
//				
//				Message m=(Message)ois.readObject();
//				
//				//显示
//				String info=m.getSender()+" 对 "+m.getGetter()+" 说:"+m.getCon()+"\r\n";
//				this.jta.append(info);
//				
//				
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				// TODO: handle exception
//			}
//		
//			
//			
//		}
//		
//	}

}
