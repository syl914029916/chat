package com.qq.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class StuModel extends AbstractTableModel{
	//rowData用来存放行数据、columnNames存放列名
	Vector rowData,columnNames;
	
	//添加学生(增、删、改)
	public boolean updStu(String sql,String []paras){
		//创建SqlHelper(如果程序并发性不考虑,可以把SqlHelper做成static)
		SqlHelper sqlHelper=new SqlHelper();
		return sqlHelper.updExecute(sql, paras);
	}
	
	//查询的本质就是用来初始化
	public void queryStu(String sql,String []paras){
		SqlHelper sqlHelper=null;
		//中间
		columnNames=new Vector<>();
		//设置列名
		columnNames.add("用户名");
		columnNames.add("密码");
		columnNames.add("序号");
//		columnNames.add("年龄");
//		columnNames.add("籍贯");
//		columnNames.add("系别");
		
		rowData=new Vector<>();
		//rowData可以存放多行
		try {
			sqlHelper=new SqlHelper();
			ResultSet rs=sqlHelper.queryExectue(sql, paras);
			
			while(rs.next()){
				Vector hang=new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
//				hang.add(rs.getInt(4));
//				hang.add(rs.getString(5));
//				hang.add(rs.getString(6));
				//加入rowData
				rowData.add(hang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sqlHelper.close();
		}
	}
	
	//得到共有多少列
	public int getColumnCount() {
		return this.columnNames.size();
	}
	
	@Override
	//得到列名
	public String getColumnName(int column) {
		return (String)this.columnNames.get(column);
	}

	//得到共有多少行
	public int getRowCount() {
		return this.rowData.size();
	}
	
	//得到某行某列的数据
	public Object getValueAt(int rowIndex, int columnIndex) {
		return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);
	}
}
