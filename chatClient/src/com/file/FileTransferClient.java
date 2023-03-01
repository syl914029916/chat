package com.file;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

import javax.swing.JFileChooser;

/**
 * 客户端
 */
public class FileTransferClient extends Socket {

	private static final String SERVER_IP = "192.168.0.104"; // 服务端IP
	private static final int SERVER_PORT = 9000; // 服务端端口

	private Socket client;

	private FileInputStream fis;

	private DataOutputStream dos;

	/**
	 * 构造函数<br/>
	 * 与服务器建立连接
	 * 
	 * @throws Exception
	 */
	public FileTransferClient() throws Exception {
		super(SERVER_IP, SERVER_PORT);
		this.client = this;
		System.out.println("Cliect[port:" + client.getLocalPort() + "] 成功连接服务端");
	}

	/**
	 * 向服务端传输文件
	 * 
	 * @throws Exception
	 */
	public void sendFile() throws Exception {
		try {
			// 创建一个文件选择组件
			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("请选择要打开的文件...");
			// 使用默认方式
			jfc.showOpenDialog(null);
			// 显示
			jfc.setVisible(true);

			// 得到用户选择的文件全(绝对)路径
			String filename = jfc.getSelectedFile().getAbsolutePath();

			File file = new File(filename);
			if (file.exists()) {
				fis = new FileInputStream(file);
				dos = new DataOutputStream(client.getOutputStream());

				// 文件名和长度
				dos.writeUTF(file.getName());
				dos.flush();
				dos.writeLong(file.length());
				dos.flush();

				// 开始传输文件
				System.out.println("======== 开始传输文件 ========");
				byte[] bytes = new byte[1024];
				int length = 0;
				long progress = 0;
				while ((length = fis.read(bytes, 0, bytes.length)) != -1) {
					dos.write(bytes, 0, length);
					dos.flush();
					progress += length;
					System.out.print("| " + (100 * progress / file.length()) + "% |");
				}
				System.out.println();
				System.out.println("======== 文件成功传输到服务器 ========");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				fis.close();
			if (dos != null)
				dos.close();
			client.close();
		}
	}

	/**
	 * 入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FileTransferClient client = new FileTransferClient(); // 启动客户端连接
			client.sendFile(); // 传输文件
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}