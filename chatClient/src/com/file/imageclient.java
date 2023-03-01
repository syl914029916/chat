package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;

public class imageclient {
	private static final int PORT = 9001;
	private static final String IP = "192.168.0.104";
	static File file;
	static FileInputStream fis;
	static OutputStream os;
	public static void main(String[] args) {
		try {
			Socket socket = new Socket(IP, PORT);
			// ��ȡͼ���ļ����ϴ�
			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("��ѡ��Ҫ�򿪵��ļ�...");
			// ʹ��Ĭ�Ϸ�ʽ
			jfc.showOpenDialog(null);
			// ��ʾ
			jfc.setVisible(true);

			// �õ��û�ѡ����ļ�ȫ(����)·��
			String filename = jfc.getSelectedFile().getAbsolutePath();
			file = new File(filename); 
			fis = new FileInputStream(file);
			os = socket.getOutputStream();
			byte[] buff = new byte[1024];
			int length = 0;
			long progress = 0;
			while ((length = fis.read(buff)) != -1) {
				os.write(buff, 0, length);
				os.flush();
				progress += length;
//				System.out.print("| " + (100 * progress / file.length()) + "% |");
//				if((100 * progress / file.length())%10==0) {
//					System.out.print("\r\n");
//				}
			}
//			os.flush();
			socket.shutdownOutput();
			// ��ȡ��Ӧ����
			InputStream is = socket.getInputStream();
			byte[] readBuff = new byte[1024];
			length = is.read(readBuff);
			String message = new String(readBuff, 0, length);
			System.out.println(message);
			fis.close();
			socket.close();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
