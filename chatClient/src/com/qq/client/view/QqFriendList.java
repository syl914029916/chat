/**
 * �ҵĺ����б�,(Ҳ����İ���ˣ�������)
 */
package com.qq.client.view;

import com.qq.client.tools.*;
import com.qq.common.Message;


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.qq.client.db.*;
public class QqFriendList extends JFrame implements ActionListener, MouseListener {

	// �����һ�ſ�Ƭ.
	HomePanel jphy2;
	JPanel jphy1, jphy3;
	JButton jphy_jb1, jphy_jb2, jphy_jb3;
	JScrollPane jsp1;
	String owner;
	// ����ڶ��ſ�Ƭ(İ����).
	HomePanel jpmsr2;
	JPanel jpmsr1, jpmsr3;
	JButton jpmsr_jb1, jpmsr_jb2, jpmsr_jb3;
	JScrollPane jsp2;
	JLabel[] jb1s;
	// ������JFrame���ó�CardLayout
	CardLayout cl;
	SqlHelper sqlh,sqlh2;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// QqFriendList qqFriendList=new QqFriendList();
	}

	// �������ߵĺ������
	public void upateFriend(Message m) {
		String onLineFriend[] = m.getCon().split(" ");

		for (int i = 0; i < onLineFriend.length; i++) {

			jb1s[Integer.parseInt(onLineFriend[i]) - 1].setEnabled(true);
		}
	}

	public QqFriendList(String ownerId) {
		this.owner = ownerId;
		// �����һ�ſ�Ƭ(��ʾ�����б�)
		jphy_jb1 = new JButton("����");
//		jphy_jb2 = new JButton("����");
//		jphy_jb2.addActionListener(this);
//		jphy_jb3 = new JButton("�ևN");

		jphy1 = new JPanel();
		jphy1.setLayout(new BorderLayout());
		// �ٶ���20������
		Image image = new ImageIcon("image/1.jpg").getImage();
		jphy2 = new HomePanel(image);

		jphy2.setLayout(new GridLayout(20, 1, 4, 10));
		// ��jphy2����ʼ��20����.
		jb1s = new JLabel[20];
		String l = ownerId;
		//�������ݿ�
		sqlh = new SqlHelper();
		String []paras = {l};
		//��ȡ����
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
		for (int i = 0; i < jb1s.length; i++) {
			if (i == 0) {
				int a=i+1;
				String s=""+a;
				String []paras1 = {s};
				System.out.println(s);
				//��ȡ����
				String pw1 = null;
				ResultSet rs1 = sqlh.queryExectue("select userName from chat where userId=?", paras1);
				try {
					while (rs1.next()) {
						pw1 = rs1.getString(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(pw1);
				jb1s[i] = new JLabel(pw1 + "", new ImageIcon("image/qq.jpg"), JLabel.LEFT);
				jb1s[i].setEnabled(false);
				if (jb1s[i].getText().equals(ownerId)) {
					jb1s[i].setEnabled(true);
				}
				jb1s[i].addMouseListener(this);
				jphy2.add(jb1s[i]);
			}
			else if (i == 1) {
				int a=i+1;
				String s=""+a;
				String []paras1 = {s};
				//��ȡ����
				String pw1 = null;
				
				ResultSet rs1 = sqlh.queryExectue("select userName from chat where userId=?", paras1);
				try {
					while (rs1.next()) {
						pw1 = rs1.getString(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jb1s[i] = new JLabel(pw1 + "", new ImageIcon("image/qq2.jpg"), JLabel.LEFT);
				jb1s[i].setEnabled(false);
				if (jb1s[i].getText().equals(ownerId)) {
					jb1s[i].setEnabled(true);
				}
				jb1s[i].addMouseListener(this);
				jphy2.add(jb1s[i]);
			}
			else if (i == 2) {
				int a=i+1;
				String s=""+a;
				String []paras1 = {s};
				//��ȡ����
				String pw1 = null;
				ResultSet rs1 = sqlh.queryExectue("select userName from chat where userId=?", paras1);
				try {
					while (rs1.next()) {
						pw1 = rs1.getString(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jb1s[i] = new JLabel(pw1 + "", new ImageIcon("image/qq3.jpg"), JLabel.LEFT);
				jb1s[i].setEnabled(false);
				if (jb1s[i].getText().equals(ownerId)) {
					jb1s[i].setEnabled(true);
				}
				jb1s[i].addMouseListener(this);
				jphy2.add(jb1s[i]);
			}
			else if (i == 3) {
				int a=i+1;
				String s=""+a;
				String []paras1 = {s};
				//��ȡ����
				String pw1 = null;
				ResultSet rs1 = sqlh.queryExectue("select userName from chat where userId=?", paras1);
				try {
					while (rs1.next()) {
						pw1 = rs1.getString(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jb1s[i] = new JLabel(pw1+ "", new ImageIcon("image/qq4.jpg"), JLabel.LEFT);
				jb1s[i].setEnabled(false);
				if (jb1s[i].getText().equals(ownerId)) {
					jb1s[i].setEnabled(true);
				}
				jb1s[i].addMouseListener(this);
				jphy2.add(jb1s[i]);
			}
			else {
				int a=i+1;
				String s=""+a;
				String []paras1 = {s};
				//��ȡ����
				String pw1 = null;
				ResultSet rs1 = sqlh.queryExectue("select userName from chat where userId=?", paras1);
				try {
					while (rs1.next()) {
						pw1 = rs1.getString(1);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jb1s[i] = new JLabel(pw1 + "", new ImageIcon("image/qq5.jpg"), JLabel.LEFT);
				jb1s[i].setEnabled(false);
				if (jb1s[i].getText().equals(ownerId)) {
					jb1s[i].setEnabled(true);
				}
				jb1s[i].addMouseListener(this);
				jphy2.add(jb1s[i]);
			}

		}

//		jphy3 = new JPanel(new GridLayout(2, 1));
//		// ��������ť���뵽jphy3
//		jphy3.add(jphy_jb2);
//		jphy3.add(jphy_jb3);

		jsp1 = new JScrollPane(jphy2);

		// ��jphy1,��ʼ��
		jphy1.add(jphy_jb1, "North");
		jphy1.add(jsp1, "Center");
		//jphy1.add(jphy3, "South");

		// ����ڶ��ſ�Ƭ
//
//		jpmsr_jb1 = new JButton("����");
//		jpmsr_jb1.addActionListener(this);
//		jpmsr_jb2 = new JButton("����");
//		jpmsr_jb3 = new JButton("�ևN");
//		jpmsr1 = new JPanel(new BorderLayout());
//		// �ٶ���20��İ����
//		jpmsr2 = new HomePanel(image);
//		jpmsr2.setLayout(new GridLayout(20, 1, 4, 4));
//		// ��jphy2����ʼ��20İ����.
//		JLabel[] jb1s2 = new JLabel[20];
//
//		for (int i = 0; i < jb1s2.length; i++) {
//			jb1s2[i] = new JLabel(i + 1 + "", new ImageIcon("image/mm.jpg"), JLabel.LEFT);
//			jpmsr2.add(jb1s2[i]);
//		}
//
//		jpmsr3 = new JPanel(new GridLayout(2, 1));
//		// ��������ť���뵽jphy3
//		jpmsr3.add(jpmsr_jb1);
//		jpmsr3.add(jpmsr_jb2);
//
//		jsp2 = new JScrollPane(jpmsr2);
//
//		// ��jphy1,��ʼ��
//		jpmsr1.add(jpmsr3, "North");
//		jpmsr1.add(jsp2, "Center");
//		jpmsr1.add(jpmsr_jb3, "South");

		cl = new CardLayout();
		this.setLayout(cl);
		this.add(jphy1, "1");
		//this.add(jpmsr1, "2");
		this.setIconImage((new ImageIcon("image/qq.gif").getImage()));
		// �ڴ�����ʾ�Լ��ı��.
		this.setTitle(pw); // ������ʾ�û���
		this.setSize(250, 500);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		// �������˱�����ť������ʾ�ڶ��ſ�Ƭ
//		if (arg0.getSource() == jphy_jb2) {
//			cl.show(this.getContentPane(), "2");
//		} else if (arg0.getSource() == jpmsr_jb1) {
//			cl.show(this.getContentPane(), "1");
//		}
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		// ��Ӧ�û�˫�����¼������õ����ѵı��.
		if (arg0.getClickCount() == 2) {
			// �õ��ú��ѵı��
			String friendName = ((JLabel) arg0.getSource()).getText().trim();
//			System.out.print("����"+friendName);
			//�������ݿ�
			sqlh2 = new SqlHelper();
			String []paras = {friendName};
			//��ȡ����
			String pw2 = null;
			ResultSet rs2 = sqlh2.queryExectue("select userId from chat where userName=?", paras);
			try {
				while (rs2.next()) {
					pw2 = rs2.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println("����"+pw2);
			String friendNo = pw2.trim();
			// System.out.println("��ϣ���� "+friendNo+" ����");
			//System.out.println("������"+owner+friendNo);
			QqChat qqChat = new QqChat(this.owner, friendNo);
			
			// �����������뵽������
			ManageQqChat.addQqChat(this.owner + " " + friendNo, qqChat);

		}
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel) arg0.getSource();
		jl.setForeground(Color.red);
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel) arg0.getSource();
		jl.setForeground(Color.black);
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
