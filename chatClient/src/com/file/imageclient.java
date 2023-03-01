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
			// 读取图像文件并上传
			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("请选择要打开的文件...");
			// 使用默认方式
			jfc.showOpenDialog(null);
			// 显示
			jfc.setVisible(true);

			// 得到用户选择的文件全(绝对)路径
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
			// 读取响应数据
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
