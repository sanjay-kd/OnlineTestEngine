package com.nsit.testengine.questions;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.nsit.testengine.questions.dto.QuestionDTO;
import com.nsit.testengine.questions.dto.ResultModel;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ResultView extends JFrame {

	private JPanel contentPane;
	private JTable table;


	/**
	 * Create the frame.
	 */
	public ResultView(int finalScore,ArrayList<QuestionDTO> questionDTOArrayList) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 604, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblResultIs = new JLabel("RESULT");
		lblResultIs.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblResultIs.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultIs.setBounds(198, 6, 120, 37);
		contentPane.add(lblResultIs);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 64, 507, 310);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new ResultModel(questionDTOArrayList));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);
		
		JLabel totalScoreLabel = new JLabel("Total Score : "+finalScore);
		totalScoreLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		totalScoreLabel.setBounds(204, 386, 185, 44);
		contentPane.add(totalScoreLabel);
	}
}
