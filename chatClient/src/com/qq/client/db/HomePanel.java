package com.qq.client.db;

import java.awt.Graphics;

import java.awt.Image;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import javax.swing.JPanel;


public class HomePanel extends JPanel {
//	ImageIcon icon;
	private Image img = null;

	public HomePanel(Image img) { // /img/HomeImg.jpg �Ǵ���������ڱ�д����Ŀ��bin�ļ����µ�img�ļ����µ�һ��ͼƬ
//		icon = new ImageIcon("image/1.jpg");
//		img = icon.getImage();
		this.img=img;
		this.setOpaque(false);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// ����������Ϊ�˱���ͼƬ���Ը��洰�����е�����С�������Լ����óɹ̶���С
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
