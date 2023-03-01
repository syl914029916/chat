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
	//rowData������������ݡ�columnNames�������
	Vector rowData,columnNames;
	
	//���ѧ��(����ɾ����)
	public boolean updStu(String sql,String []paras){
		//����SqlHelper(������򲢷��Բ�����,���԰�SqlHelper����static)
		SqlHelper sqlHelper=new SqlHelper();
		return sqlHelper.updExecute(sql, paras);
	}
	
	//��ѯ�ı��ʾ���������ʼ��
	public void queryStu(String sql,String []paras){
		SqlHelper sqlHelper=null;
		//�м�
		columnNames=new Vector<>();
		//��������
		columnNames.add("�û���");
		columnNames.add("����");
		columnNames.add("���");
//		columnNames.add("����");
//		columnNames.add("����");
//		columnNames.add("ϵ��");
		
		rowData=new Vector<>();
		//rowData���Դ�Ŷ���
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
				//����rowData
				rowData.add(hang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sqlHelper.close();
		}
	}
	
	//�õ����ж�����
	public int getColumnCount() {
		return this.columnNames.size();
	}
	
	@Override
	//�õ�����
	public String getColumnName(int column) {
		return (String)this.columnNames.get(column);
	}

	//�õ����ж�����
	public int getRowCount() {
		return this.rowData.size();
	}
	
	//�õ�ĳ��ĳ�е�����
	public Object getValueAt(int rowIndex, int columnIndex) {
		return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);
	}
}
