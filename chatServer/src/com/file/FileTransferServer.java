package com.file;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.RoundingMode;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;

import javax.swing.JFileChooser;

/**
 * �ļ�����Server��<br>
 */
public class FileTransferServer extends ServerSocket {

	private static final int SERVER_PORT = 9000; // ����˶˿�

	private static DecimalFormat df = null;

	static {
		// �������ָ�ʽ������һλ��ЧС��
		df = new DecimalFormat("#0.0");
		df.setRoundingMode(RoundingMode.HALF_UP);
		df.setMinimumFractionDigits(1);
		df.setMaximumFractionDigits(1);
	}

	public FileTransferServer() throws Exception {
		super(SERVER_PORT);
	}

	/**
	 * ʹ���̴߳���ÿ���ͻ��˴�����ļ�
	 * 
	 * @throws Exception
	 */
	public void load() throws Exception {
		while (true) {
			// server���Խ�������Socket����������server��accept����������ʽ��
			Socket socket = this.accept();
			/**
			 * ���ǵķ���˴���ͻ��˵�����������ͬ�����еģ� ÿ�ν��յ����Կͻ��˵���������� ��Ҫ�ȸ���ǰ�Ŀͻ���ͨ����֮������ٴ�����һ����������
			 * ���ڲ����Ƚ϶������»�����Ӱ���������ܣ� Ϊ�ˣ����ǿ��԰�����Ϊ���������첽������ͻ���ͨ�ŵķ�ʽ
			 */
			// ÿ���յ�һ��Socket�ͽ���һ���µ��߳���������
			new Thread(new Task(socket)).start();
		}
	}

	/**
	 * ����ͻ��˴���������ļ��߳���
	 */
	class Task implements Runnable {

		private Socket socket;

		private DataInputStream dis;

		private FileOutputStream fos;

		public Task(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				dis = new DataInputStream(socket.getInputStream());

				// �ļ����ͳ���
				String fileName = dis.readUTF();
				long fileLength = dis.readLong();
				// File directory = new File("C:\\Users\\902sx\\Desktop\\syl");
//        if(!directory.exists()) { 
//          directory.mkdir(); 
//        } 
				//��������Ի���
				JFileChooser jfc=new JFileChooser();
				//��������
				jfc.setDialogTitle("���ļ����浽...");
				//ʹ��Ĭ�Ϸ�ʽ
				jfc.showSaveDialog(null);
				//��ʾ
				jfc.setVisible(true);
				
				//�õ��û�ϣ�����ļ����浽�δ����ļ�ȫ·��
				String filename=jfc.getSelectedFile().getAbsolutePath();

				//directory.getAbsolutePath() + File.separatorChar + 
				File file = new File(filename);
				fos = new FileOutputStream(file);

				// ��ʼ�����ļ�
				byte[] bytes = new byte[1024];
				int length = 0;
				while ((length = dis.read(bytes, 0, bytes.length)) != -1) {
					fos.write(bytes, 0, length);
					fos.flush();
				}
				System.out.println("======== �ļ����ճɹ� [File Name��" + fileName + "] [Size��" + getFormatFileSize(fileLength)
						+ "] ========");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (fos != null)
						fos.close();
					if (dis != null)
						dis.close();
					socket.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * ��ʽ���ļ���С
	 * 
	 * @param length
	 * @return
	 */
	private String getFormatFileSize(long length) {
		double size = ((double) length) / (1 << 30);
		if (size >= 1) {
			return df.format(size) + "GB";
		}
		size = ((double) length) / (1 << 20);
		if (size >= 1) {
			return df.format(size) + "MB";
		}
		size = ((double) length) / (1 << 10);
		if (size >= 1) {
			return df.format(size) + "KB";
		}
		return length + "B";
	}

	/**
	 * ���
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FileTransferServer server = new FileTransferServer(); // ���������
			System.out.println("������������,9000�˿ڵȴ�����");
			server.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}