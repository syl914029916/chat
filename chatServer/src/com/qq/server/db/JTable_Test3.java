package com.qq.server.db;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;



public class JTable_Test3 extends JFrame implements ActionListener{
	//定义组件
	JPanel jp1,jp2;
	JLabel jl1;
	JButton jb1,jb2,jb3,jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	StuModel sm;
	HomePanel jhp;
	public static void main(String[] args) {
		try {
			// 将当前窗体外观设置为所在操作系统的外观
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new JTable_Test3();
	}
	
	//构造函数
	public JTable_Test3(){
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("查询");
		jb1.addActionListener(this);
		jl1=new JLabel("请输入名字");
		
		//把各个空间加入列
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jp2=new JPanel();
		jb2=new JButton("添加");
		jb2.addActionListener(this);
		jb3=new JButton("修改");
		jb3.addActionListener(this);
		jb4=new JButton("删除");
		jb4.addActionListener(this);
		//把各个按钮加入到jp2中
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		//创建一个数据模型对象
		sm=new StuModel();
		String []paras={"1"};
		sm.queryStu("select * from chat where 1=?", paras);
		
		//初始化JTable
		jt=new JTable(sm);
		jt.setOpaque(false);
		//初始化jsp JScrollPane
		Image image = new ImageIcon("image/chatbg.jpg").getImage();
		jhp = new HomePanel(image);
		jhp.setLayout(new BorderLayout());
		jhp.add(jt, "Center");
		jsp = new JScrollPane(jhp);
//		jsp=new JScrollPane(jt);
		
		//把jsp放入到jframe
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1){
			//因为把对表的数据封装到StuModel中，我们就可以比较简单的完成查询
			String name=this.jtf.getText();
			//写一个SQL语句
			String sql="select * from chat where userName=?";
			String []paras={name};
			//构建新的数据模型类，并更新
			sm=new StuModel();
			sm.queryStu(sql, paras);
			//更新JTable
			jt.setModel(sm);
		}
		//用户点击添加时
		else if(e.getSource()==jb2){
			StuAddDialog sa=new StuAddDialog(this, "添加用户", true);
			//重新再获得新的数据模型
			//构建新的数据模型类，并更新
			sm=new StuModel();
			String []paras2={"1"};
			sm.queryStu("select * from chat where 1=?", paras2);
			//更新JTable
			jt.setModel(sm);
		}
		//用户修改数据
		else if(e.getSource()==jb3){
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1){
				//提示
				JOptionPane.showMessageDialog(this, "请选择一行", "提示", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			//显示修改对话框
			new StuUpdDialog(this,"修改用户信息",true,sm,rowNum); 
			
			//更新数据模型
			sm=new StuModel();
			String []paras2={"1"};
			sm.queryStu("select * from chat where 1=?", paras2);
			//更新JTable
			jt.setModel(sm);
		}
		
		//用户点击删除时，删除一条选中的数据
		else if(e.getSource()==jb4){
			//1、得到学生的ID号
			//getSelectedRow会返回用户点中的行
			//如果该用户一行都没有选择，就会返回-1
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1){
				//提示
				JOptionPane.showMessageDialog(this, "请选择一行", "提示", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			//得到学生编号
			String userId=(String)sm.getValueAt(rowNum, 2);
			//创建一个sql语句
			String sql="delete from chat where userId=?";
			String []paras={userId};
			StuModel temp=new StuModel();
			if(temp.updStu(sql, paras)){
				JOptionPane.showMessageDialog(this,"删除数据成功","删除数据提示",JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(this,"删除数据失败","删除数据提示",JOptionPane.ERROR_MESSAGE);
			}
			
			//更新数据模型
			sm=new StuModel();
			String []paras2={"1"};
			sm.queryStu("select * from chat where 1=?", paras2);
			//更新JTable
			jt.setModel(sm);
		}
	}
}
