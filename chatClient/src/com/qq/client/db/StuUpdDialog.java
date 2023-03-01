package com.qq.client.db;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StuUpdDialog extends JDialog implements ActionListener {
	// ��������Ҫ��swing���
	JLabel jl1, jl2, jl3;
	JButton jb1, jb2;
	JTextField jtf1, jtf2, jtf3;
	JPanel jp1, jp2, jp3;

	// owner���ĸ�����;title������;modelָ����ģ̬���ڣ����Ƿ�ģ̬
	public StuUpdDialog(Frame owner, String title, boolean modal, StuModel sm, int rowNum) {
		super(owner, title, modal);// ���ø��๹�췽�����ﵽģʽ�Ի���Ч��
		jl1 = new JLabel("�û���");
		jl2 = new JLabel("����");
		jl3 = new JLabel("���");
//		jl4=new JLabel("����");
//		jl5=new JLabel("����");
//		jl6=new JLabel("ϵ��");

		jtf1 = new JTextField();
		// ��ʼ������
		jtf1.setText((String) sm.getValueAt(rowNum, 0));

		jtf2 = new JTextField();
		jtf2.setText((String) sm.getValueAt(rowNum, 1));
		jtf3 = new JTextField();
		jtf3.setText((String) sm.getValueAt(rowNum, 2));
		//��jtf3�����޸�
		jtf3.setEditable(false);
//		jtf4=new JTextField();
//		jtf4.setText(sm.getValueAt(rowNum, 3).toString());
//		jtf5=new JTextField();
//		jtf5.setText((String)sm.getValueAt(rowNum, 4));
//		jtf6=new JTextField();
//		jtf6.setText((String)sm.getValueAt(rowNum, 5));

		jb1 = new JButton("�޸�");
		jb2 = new JButton("ȡ��");

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// ���ò���
		jp1.setLayout(new GridLayout(3, 1));
		jp2.setLayout(new GridLayout(3, 1));

		// ������
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
//		jp1.add(jl4);
//		jp1.add(jl5);
//		jp1.add(jl6);

		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
//		jp2.add(jtf4);
//		jp2.add(jtf5);
//		jp2.add(jtf6);

		jp3.add(jb1);
		jp3.add(jb2);

		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		jb1.addActionListener(this);
		jb2.addActionListener(this);

		// չ��
		this.setSize(300, 250);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// �û������Ӱ�ť�����Ӧ����
		if (e.getSource() == jb1) {
			// ��һ��sql���
			String sql = "update chat set userName=?,passWord=? where userId=?";
			String[] paras = { jtf1.getText(),jtf2.getText(), jtf3.getText()
					};
			StuModel temp = new StuModel();
			if (temp.updStu(sql, paras)) {
				JOptionPane.showMessageDialog(this, "�޸����ݳɹ�", "�޸�������ʾ", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "�޸�����ʧ��", "�޸�������ʾ", JOptionPane.ERROR_MESSAGE);
			}
			this.dispose();
		} else if (e.getSource() == jb2) {
			this.dispose();
		}
	}
}
