package com.file;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

import javax.swing.JFileChooser;

/**
 * �ͻ���
 */
public class FileTransferClient extends Socket {

	private static final String SERVER_IP = "192.168.0.104"; // �����IP
	private static final int SERVER_PORT = 9000; // ����˶˿�

	private Socket client;

	private FileInputStream fis;

	private DataOutputStream dos;

	/**
	 * ���캯��<br/>
	 * ���������������
	 * 
	 * @throws Exception
	 */
	public FileTransferClient() throws Exception {
		super(SERVER_IP, SERVER_PORT);
		this.client = this;
		System.out.println("Cliect[port:" + client.getLocalPort() + "] �ɹ����ӷ����");
	}

	/**
	 * �����˴����ļ�
	 * 
	 * @throws Exception
	 */
	public void sendFile() throws Exception {
		try {
			// ����һ���ļ�ѡ�����
			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("��ѡ��Ҫ�򿪵��ļ�...");
			// ʹ��Ĭ�Ϸ�ʽ
			jfc.showOpenDialog(null);
			// ��ʾ
			jfc.setVisible(true);

			// �õ��û�ѡ����ļ�ȫ(����)·��
			String filename = jfc.getSelectedFile().getAbsolutePath();

			File file = new File(filename);
			if (file.exists()) {
				fis = new FileInputStream(file);
				dos = new DataOutputStream(client.getOutputStream());

				// �ļ����ͳ���
				dos.writeUTF(file.getName());
				dos.flush();
				dos.writeLong(file.length());
				dos.flush();

				// ��ʼ�����ļ�
				System.out.println("======== ��ʼ�����ļ� ========");
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
				System.out.println("======== �ļ��ɹ����䵽������ ========");
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
	 * ���
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FileTransferClient client = new FileTransferClient(); // �����ͻ�������
			client.sendFile(); // �����ļ�
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}