package com.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;

public class imageserver {
	private static final int PORT=9001;
	public static void main(String[] args) {
		try {
			System.out.print("���������������ȴ�����");
			ServerSocket ss=new ServerSocket(PORT);
			Socket socket=ss.accept();
			//���ϴ���ͼ�����ݲ�д���ļ�
			InputStream is=socket.getInputStream();
			JFileChooser jfc=new JFileChooser();
			//��������
			jfc.setDialogTitle("���ļ����浽...");
			//ʹ��Ĭ�Ϸ�ʽ
			jfc.showSaveDialog(null);
			//��ʾ
			jfc.setVisible(true);
			
			//�õ��û�ϣ�����ļ����浽�δ����ļ�ȫ·��
			String filename=jfc.getSelectedFile().getAbsolutePath();
			FileOutputStream fos=new FileOutputStream(filename);
			byte[] buff=new byte[1024];
			int length=0;
			while((length=is.read(buff))!=-1) {
				fos.write(buff, 0, length);
			}
			fos.flush();
			//��Ӧ
			OutputStream os=socket.getOutputStream();
			os.write("�ף�����Ҫ��ͼƬ�Ѿ��ϴ��ɹ�".getBytes());
			socket.close();
			ss.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
