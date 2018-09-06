package com.nsit.testengine.questions;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.nsit.testengine.questions.dto.QuestionDTO;
import com.nsit.testengine.util.CommonDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TakeTestView extends JFrame {

	private JPanel contentPane;
	JLabel qIdLabel = new JLabel("QId");
	JLabel qNameLabel = new JLabel("Question Name");
	JRadioButton ans1RadioBtn = new JRadioButton("Ans1");
	JRadioButton ans2RadioBtn = new JRadioButton("Ans2");
	JRadioButton ans3RadioBtn = new JRadioButton("Ans3");
	JRadioButton ans4RadioBtn = new JRadioButton("Ans4");
	JButton previousBtn = new JButton("Previous");
	JButton nextBtn = new JButton("Next");
	ButtonGroup btnGrp = new ButtonGroup();
	private final JLabel lblTimeLeft = new JLabel("Time Left");
	private final JLabel timeLeftLabel = new JLabel("0");

	private ArrayList<QuestionDTO> questionDTOArrayList = new ArrayList<>();
	QuestionDTO questionDTO;
	private int index = -1;
	private int time = 5;
	private String markedAns = null;
	private ButtonModel selectedRadioBtn;
	private boolean isFirstTime = false;
	private static int finalScore=0;
	
	public void showMeResult(){
		timer.stop();
		setStudentAnsInDTO();
		finishTest();
		JOptionPane.showMessageDialog(getParent(), "Thank You!");
		ResultView resultView = new ResultView(finalScore,questionDTOArrayList);
		resultView.setVisible(true);
		setVisible(false);
		dispose();
	}

	Timer timer = new Timer(1000, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			timeLeftLabel.setText(String.valueOf(time));
			time--;
			if (time < 0) {
				showMeResult();
			}
		}
	});
	
	public void finishTest(){
		for(QuestionDTO questionDTO : questionDTOArrayList){
			if(questionDTO.getYourAns() != null){
				if(questionDTO.getYourAns().equals(questionDTO.getRAns())){
					finalScore += questionDTO.getScore();
				}
			}
		}
	}

	public void clearSelection() {
		btnGrp.clearSelection();
	}

	public void checkBtnEnableDisable() {
		
		if(questionDTOArrayList.size() == 1){
			nextBtn.setEnabled(false);
			previousBtn.setEnabled(false);
		} 
		else if (index > 0 && index < questionDTOArrayList.size()-1) {
			nextBtn.setEnabled(true);
			previousBtn.setEnabled(true);
		} 
		else if (index < questionDTOArrayList.size()-1) {
			nextBtn.setEnabled(true);
			previousBtn.setEnabled(false);
		} 
		else if (index >= questionDTOArrayList.size()-1) {
			nextBtn.setEnabled(false);
			previousBtn.setEnabled(true);
		} 
//		else {
//			nextBtn.setEnabled(true);
//			previousBtn.setEnabled(false);
//		}
	}
	
	public void setRadioButtonSelection(){
		if(questionDTOArrayList.get(index).getYourAns() != null){
		if(questionDTOArrayList.get(index).getYourAns().equals("a")){
			ans1RadioBtn.setSelected(true);
		}
		if(questionDTOArrayList.get(index).getYourAns().equals("b")){
			ans2RadioBtn.setSelected(true);
		}
		if(questionDTOArrayList.get(index).getYourAns().equals("c")){
			ans3RadioBtn.setSelected(true);
		}
		if(questionDTOArrayList.get(index).getYourAns().equals("d")){
			ans4RadioBtn.setSelected(true);
		}
		}
		else{
			clearSelection();
		}
	}
	
	public void setStudentAnsInDTO(){
		if (ans1RadioBtn.isSelected()) {
			questionDTOArrayList.get(index).setYourAns("a");
		} 
		else if (ans2RadioBtn.isSelected()) {
			questionDTOArrayList.get(index).setYourAns("b");
		}
		else if (ans3RadioBtn.isSelected()) {
			questionDTOArrayList.get(index).setYourAns("c");
		}
		else if (ans4RadioBtn.isSelected()) {
			questionDTOArrayList.get(index).setYourAns("d");
		}
	}
	
	public void printSingleQuestion() {
		if (index < questionDTOArrayList.size()) {
			index++;
			qIdLabel.setText(String.valueOf(questionDTOArrayList.get(index).getQId()));
			qNameLabel.setText(String.valueOf(questionDTOArrayList.get(index).getQName()));
			ans1RadioBtn.setText(String.valueOf(questionDTOArrayList.get(index).getAns1()));
			ans2RadioBtn.setText(String.valueOf(questionDTOArrayList.get(index).getAns2()));
			ans3RadioBtn.setText(String.valueOf(questionDTOArrayList.get(index).getAns3()));
			ans4RadioBtn.setText(String.valueOf(questionDTOArrayList.get(index).getAns4()));
			if(isFirstTime){
			setRadioButtonSelection();
			}
		}
	}

	public void getQuestionsFromDB() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		final String GET_QUESTION_SQL = "SELECT qid,qname,ans1,ans2,ans3,ans4,rans,score FROM QUESTION";

		try {

			connection = CommonDAO.getConnection();
			preparedStatement = connection.prepareStatement(GET_QUESTION_SQL);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				questionDTO = new QuestionDTO();
				questionDTO.setQId(resultSet.getInt("qid"));
				questionDTO.setQName(resultSet.getString("qname"));
				questionDTO.setAns1(resultSet.getString("ans1"));
				questionDTO.setAns2(resultSet.getString("ans2"));
				questionDTO.setAns3(resultSet.getString("ans3"));
				questionDTO.setAns4(resultSet.getString("ans4"));
				questionDTO.setRAns(resultSet.getString("rans"));
				questionDTO.setScore(resultSet.getInt("score"));
				questionDTOArrayList.add(questionDTO);
			}

		} catch (SQLException | ClassNotFoundException e) {
			
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TakeTestView frame = new TakeTestView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public TakeTestView() throws SQLException {

		getQuestionsFromDB();
		printSingleQuestion();
		isFirstTime = true;
		checkBtnEnableDisable();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTakeTest = new JLabel("GK Test");
		lblTakeTest.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblTakeTest.setBounds(170, 6, 159, 37);
		contentPane.add(lblTakeTest);

		qIdLabel.setBounds(27, 61, 34, 46);
		contentPane.add(qIdLabel);
		qNameLabel.setBounds(74, 61, 429, 46);
		contentPane.add(qNameLabel);

		ans1RadioBtn.setBounds(47, 142, 364, 23);
		contentPane.add(ans1RadioBtn);

		ans2RadioBtn.setBounds(47, 186, 364, 23);
		contentPane.add(ans2RadioBtn);

		ans3RadioBtn.setBounds(47, 231, 364, 23);
		contentPane.add(ans3RadioBtn);

		ans4RadioBtn.setBounds(47, 276, 364, 23);
		contentPane.add(ans4RadioBtn);

		btnGrp.add(ans1RadioBtn);
		btnGrp.add(ans2RadioBtn);
		btnGrp.add(ans3RadioBtn);
		btnGrp.add(ans4RadioBtn);
		previousBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStudentAnsInDTO();
				index = index - 2;
				printSingleQuestion();
				checkBtnEnableDisable();
			}
		});

		previousBtn.setBounds(74, 336, 117, 29);
		contentPane.add(previousBtn);
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStudentAnsInDTO();
				printSingleQuestion();
				checkBtnEnableDisable();
			}
		});

		nextBtn.setBounds(248, 336, 117, 29);
		contentPane.add(nextBtn);
		lblTimeLeft.setBounds(460, 6, 61, 16);

		contentPane.add(lblTimeLeft);
		timeLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLeftLabel.setBounds(460, 34, 61, 16);

		contentPane.add(timeLeftLabel);
		
		JButton btnFinishTest = new JButton("Finish Test");
		btnFinishTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showMeResult();
			}
		});
		btnFinishTest.setBounds(404, 336, 117, 29);
		contentPane.add(btnFinishTest);
		timer.start();
	}
}
