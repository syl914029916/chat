package com.qq.client.view;

import java.awt.Graphics;

import java.awt.Image;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import javax.swing.JPanel;


public class HomePanel extends JPanel {
//	ImageIcon icon;
	private Image img = null;

	public HomePanel(Image img) { // /img/HomeImg.jpg 是存放在你正在编写的项目的bin文件夹下的img文件夹下的一个图片
//		icon = new ImageIcon("image/1.jpg");
//		img = icon.getImage();
		this.img=img;
		this.setOpaque(false);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 下面这行是为了背景图片可以跟随窗口自行调整大小，可以自己设置成固定大小
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
