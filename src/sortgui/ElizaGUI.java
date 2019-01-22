package sortgui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.*;

public class ElizaGUI extends JFrame{
	
	
	public ElizaGUI(){
		
		MyJPanel jp1 = new MyJPanel();
		jp1.setBorder(BorderFactory.createEmptyBorder(30, 30,  30, 30));
		jp1.setBackground(Color.BLACK);
		setLayout(new GridLayout(1, 1));
		setSize(700,980);
		setVisible(true);
		add(jp1);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	
	

}