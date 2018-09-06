package com.nsit.testengine.questions;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.nsit.testengine.constants.Constants;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class QuestionUploadView extends JFrame {

	private JPanel contentPane;

	public void chooseFile(){
		QuestionUploadHelper helper = new QuestionUploadHelper();
		JFileChooser fileChooser = new JFileChooser(Constants.SELECT_FILE_PATH);
		fileChooser.showOpenDialog(this);
		if(fileChooser.getSelectedFile() != null){
		String path = fileChooser.getSelectedFile().getAbsolutePath();
		System.out.println("Path is : "+path);
		try {
			String message = helper.uploadFile(path);
			JOptionPane.showMessageDialog(this, message);
		} catch (IOException e) {
			System.out.println("File could not be uplaoded!!");
			e.printStackTrace();
		}
		}
		else {
			System.out.println("Operation Cancelled by User");
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionUploadView frame = new QuestionUploadView();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QuestionUploadView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 369, 253);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUploadQuestions = new JLabel("UPLOAD QUESTIONS");
		lblUploadQuestions.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblUploadQuestions.setBounds(82, 22, 200, 40);
		contentPane.add(lblUploadQuestions);
		
		JButton uploadBtn = new JButton("Upload File");
		uploadBtn.setForeground(Color.DARK_GRAY);
		uploadBtn.setFocusPainted(false);
		uploadBtn.setOpaque(true);
		uploadBtn.setBackground(Color.ORANGE);
		uploadBtn.setBorderPainted(false);
		uploadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFile();
			}
		});
		uploadBtn.setBounds(121, 128, 127, 40);
		contentPane.add(uploadBtn);
	}
}
