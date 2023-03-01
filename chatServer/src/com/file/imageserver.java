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
			System.out.print("服务器已启动，等待连接");
			ServerSocket ss=new ServerSocket(PORT);
			Socket socket=ss.accept();
			//读上传的图像数据并写入文件
			InputStream is=socket.getInputStream();
			JFileChooser jfc=new JFileChooser();
			//设置名字
			jfc.setDialogTitle("将文件保存到...");
			//使用默认方式
			jfc.showSaveDialog(null);
			//显示
			jfc.setVisible(true);
			
			//得到用户希望把文件保存到何处，文件全路径
			String filename=jfc.getSelectedFile().getAbsolutePath();
			FileOutputStream fos=new FileOutputStream(filename);
			byte[] buff=new byte[1024];
			int length=0;
			while((length=is.read(buff))!=-1) {
				fos.write(buff, 0, length);
			}
			fos.flush();
			//响应
			OutputStream os=socket.getOutputStream();
			os.write("亲，你想要的图片已经上传成功".getBytes());
			socket.close();
			ss.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
