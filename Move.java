package com.langsin.homework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Move {//在主窗口中移动hello world
	public static void main(String[] args) {
		JFrame jf=new JFrame("袁颖");
		jf.setBounds(100, 100, 400, 300);
		final JLabel j1=new JLabel("Hello World");
		j1.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent e) { 
				j1.setLocation(e.getX() , e.getY()); 
			} 
		}); 
		jf.add(j1);
		j1.setVisible(true);
		jf.setVisible(true); 
	}

}
