package org.malai.ex.speech.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SpeechEditorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	 private JPanel container = new JPanel();
	 private JLabel label = new JLabel("Speech Recognition");
	
	public SpeechEditorFrame() {
		super();
		this.setTitle("Speech editor");
		this.setLocation(100, 100);
		this.setSize(800, 600);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    JPanel top = new JPanel();
	    top.add(label);
	    container.add(top, BorderLayout.NORTH);
	    this.setContentPane(container);
	    this.setVisible(true);              
		
		
		
	}



	



}