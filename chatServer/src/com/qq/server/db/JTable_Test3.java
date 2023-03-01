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
	//�������
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
			// ����ǰ�����������Ϊ���ڲ���ϵͳ�����
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
	
	//���캯��
	public JTable_Test3(){
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("��ѯ");
		jb1.addActionListener(this);
		jl1=new JLabel("����������");
		
		//�Ѹ����ռ������
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jp2=new JPanel();
		jb2=new JButton("���");
		jb2.addActionListener(this);
		jb3=new JButton("�޸�");
		jb3.addActionListener(this);
		jb4=new JButton("ɾ��");
		jb4.addActionListener(this);
		//�Ѹ�����ť���뵽jp2��
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		//����һ������ģ�Ͷ���
		sm=new StuModel();
		String []paras={"1"};
		sm.queryStu("select * from chat where 1=?", paras);
		
		//��ʼ��JTable
		jt=new JTable(sm);
		jt.setOpaque(false);
		//��ʼ��jsp JScrollPane
		Image image = new ImageIcon("image/chatbg.jpg").getImage();
		jhp = new HomePanel(image);
		jhp.setLayout(new BorderLayout());
		jhp.add(jt, "Center");
		jsp = new JScrollPane(jhp);
//		jsp=new JScrollPane(jt);
		
		//��jsp���뵽jframe
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
			//��Ϊ�ѶԱ�����ݷ�װ��StuModel�У����ǾͿ��ԱȽϼ򵥵���ɲ�ѯ
			String name=this.jtf.getText();
			//дһ��SQL���
			String sql="select * from chat where userName=?";
			String []paras={name};
			//�����µ�����ģ���࣬������
			sm=new StuModel();
			sm.queryStu(sql, paras);
			//����JTable
			jt.setModel(sm);
		}
		//�û�������ʱ
		else if(e.getSource()==jb2){
			StuAddDialog sa=new StuAddDialog(this, "����û�", true);
			//�����ٻ���µ�����ģ��
			//�����µ�����ģ���࣬������
			sm=new StuModel();
			String []paras2={"1"};
			sm.queryStu("select * from chat where 1=?", paras2);
			//����JTable
			jt.setModel(sm);
		}
		//�û��޸�����
		else if(e.getSource()==jb3){
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1){
				//��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			//��ʾ�޸ĶԻ���
			new StuUpdDialog(this,"�޸��û���Ϣ",true,sm,rowNum); 
			
			//��������ģ��
			sm=new StuModel();
			String []paras2={"1"};
			sm.queryStu("select * from chat where 1=?", paras2);
			//����JTable
			jt.setModel(sm);
		}
		
		//�û����ɾ��ʱ��ɾ��һ��ѡ�е�����
		else if(e.getSource()==jb4){
			//1���õ�ѧ����ID��
			//getSelectedRow�᷵���û����е���
			//������û�һ�ж�û��ѡ�񣬾ͻ᷵��-1
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1){
				//��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			//�õ�ѧ�����
			String userId=(String)sm.getValueAt(rowNum, 2);
			//����һ��sql���
			String sql="delete from chat where userId=?";
			String []paras={userId};
			StuModel temp=new StuModel();
			if(temp.updStu(sql, paras)){
				JOptionPane.showMessageDialog(this,"ɾ�����ݳɹ�","ɾ��������ʾ",JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(this,"ɾ������ʧ��","ɾ��������ʾ",JOptionPane.ERROR_MESSAGE);
			}
			
			//��������ģ��
			sm=new StuModel();
			String []paras2={"1"};
			sm.queryStu("select * from chat where 1=?", paras2);
			//����JTable
			jt.setModel(sm);
		}
	}
}
