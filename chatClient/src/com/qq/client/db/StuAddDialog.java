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

public class StuAddDialog extends JDialog implements ActionListener{
	//定义我需要的swing组件
	JLabel jl1,jl2,jl3;
	JButton jb1,jb2;
	JTextField jtf1,jtf2,jtf3;
	JPanel jp1,jp2,jp3;
	
	//owner它的父窗口;title窗口名;model指定是模态窗口，还是非模态
	public StuAddDialog(Frame owner,String title,boolean modal){
		super(owner,title,modal);//调用父类构造方法，达到模式对话框效果
		jl1=new JLabel("用户名");
		jl2=new JLabel("密码");
		jl3=new JLabel("序号");
//		jl4=new JLabel("年龄");
//		jl5=new JLabel("籍贯");
//		jl6=new JLabel("系别");
//		
		jtf1=new JTextField();
		jtf2=new JTextField();
		jtf3=new JTextField();
//		jtf4=new JTextField();
//		jtf5=new JTextField();
//		jtf6=new JTextField();
		
		jb1=new JButton("添加");
		jb2=new JButton("取消");
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		//设置布局
		jp1.setLayout(new GridLayout(3,1));
		jp2.setLayout(new GridLayout(3,1));
		
		//添加组件
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
		
		this.add(jp1,BorderLayout.WEST);
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		
		//展现
		this.setSize(300, 250);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//用户点击添加按钮后的响应动作
		if(e.getSource()==jb1){
			StuModel temp=new StuModel();
			String sql="insert into chat values(?,?,?)";
			String []paras={jtf1.getText(),jtf2.getText(),jtf3.getText()};
			if(!temp.updStu(sql, paras)){
				JOptionPane.showMessageDialog(this, "添加数据失败", "添加数据提示", JOptionPane.ERROR_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(this,"添加数据成功","添加数据提示",JOptionPane.INFORMATION_MESSAGE);
			}
			//关闭对话框
			this.dispose();
		}
		else if(e.getSource()==jb2){
			this.dispose();
		}
	}
}
