package com.qq.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SqlHelper {
	//定义操作数据库需要的组件
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;	
	String sqlDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url="jdbc:sqlserver://127.0.0.1:1433;databaseName=vvv;user=syl;password=269788;";
	
	public SqlHelper(){
		try {
			//1、加载驱动
			Class.forName(sqlDriver);
			//2、得到连接
			ct=DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//关闭数据库资源
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
	
	//写一个不需要注入的方法(由于数据量少，所以写了一个这个方法。一般都带有条件的注入)
	public ResultSet queryExectue(String sql){
		try {
			//3、创建ps
			ps=ct.prepareStatement(sql);
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭资源？？？
		}
		return rs;
	}
	
	//对数据库的查询操作
	public ResultSet queryExectue(String sql,String []paras){
		try {
			//3、创建ps
			ps=ct.prepareStatement(sql);
			//给ps的问号赋值
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭资源？？？
		}
		return rs;
	}
	
	//把对数据库的增、删、改合在一起
	public boolean updExecute(String sql,String []paras){
		boolean b=true;
		try {
			//3、创建ps
			ps=ct.prepareStatement(sql);
			//给ps的问号赋值
			for(int i=0;i<paras.length;i++){
				ps.setString(i+1, paras[i]);
			}
			
			//4、执行操作
			if(ps.executeUpdate()!=1){
				b=false;
			}
		} catch (Exception e) {
			b=false;
			JOptionPane.showMessageDialog(null, "数据源错误或数据库用户名、密码错误", "数据库连接错误提示", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}finally{
			this.close();
		}
		return b;
	}
}
