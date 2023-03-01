package com.qq.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SqlHelper {
	//����������ݿ���Ҫ�����
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;	
	String sqlDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url="jdbc:sqlserver://127.0.0.1:1433;databaseName=vvv;user=syl;password=269788;";
	
	public SqlHelper(){
		try {
			//1����������
			Class.forName(sqlDriver);
			//2���õ�����
			ct=DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//�ر����ݿ���Դ
	public void close(){
		try {
			if(rs!=null){
				rs.close();
			}
			if(ps!=null){
				ps.close();
			}
			if(ct!=null){
				ct.close();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	//дһ������Ҫע��ķ���(�����������٣�����д��һ�����������һ�㶼����������ע��)
	public ResultSet queryExectue(String sql){
		try {
			//3������ps
			ps=ct.prepareStatement(sql);
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر���Դ������
		}
		return rs;
	}
	
	//�����ݿ�Ĳ�ѯ����
	public ResultSet queryExectue(String sql,String []paras){
		try {
			//3������ps
			ps=ct.prepareStatement(sql);
			//��ps���ʺŸ�ֵ
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر���Դ������
		}
		return rs;
	}
	
	//�Ѷ����ݿ������ɾ���ĺ���һ��
	public boolean updExecute(String sql,String []paras){
		boolean b=true;
		try {
			//3������ps
			ps=ct.prepareStatement(sql);
			//��ps���ʺŸ�ֵ
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			
			//4��ִ�в���
			if(ps.executeUpdate()!=1){
				b=false;
			}
		} catch (Exception e) {
			b=false;
			JOptionPane.showMessageDialog(null, "����Դ��������ݿ��û������������", "���ݿ����Ӵ�����ʾ", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}finally{
			this.close();
		}
		return b;
	}
}
