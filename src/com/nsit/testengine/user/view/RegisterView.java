package com.nsit.testengine.user.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import com.nsit.testengine.user.dao.UserDAO;
import com.nsit.testengine.user.dto.UserDTO;
import com.nsit.testengine.util.CommonUtils;

public class RegisterView extends JFrame {
	
	Logger logger = Logger.getLogger(RegisterView.class);
	

	private JPanel contentPane;
	private JTextField fullNameTxtField;
	private JTextField userNameTxtField;
	private JPasswordField passwordTxtField;
	private JPasswordField confirmPasswordTxtField;
	private JTextField emailIDTxtField;
	private JLabel passwordStrengthLabel;
	private JComboBox cityComboBox;
	private ButtonGroup btnGrp;

	public void registerUser(){
		logger.debug("Inside register Message!");
		
		String fullName = fullNameTxtField.getText();
		String userName = userNameTxtField.getText();
		String password = new String(passwordTxtField.getPassword());
		String emailID = emailIDTxtField.getText();
		String city = cityComboBox.getSelectedItem().toString();
		String gender = btnGrp.getSelection().getActionCommand();
		
		if(verifyPasswordMatch()){
			
			if ( passwordStrengthLabel.getText().equalsIgnoreCase("Weak") ) {
				
				JOptionPane.showMessageDialog(this, "Password strength low.Add special charatcers!");
			
			}
			
			else {
				
				UserDTO userDto = new UserDTO();
				userDto.setFullName(fullName);
				userDto.setUserName(userName);
				userDto.setPassword(password);
				userDto.setEmailID(emailID);
				userDto.setCity(city);
				userDto.setGender(gender);
				
				UserDAO userDao = new UserDAO();
				
				try {
					String message = userDao.doRegister(userDto);
					JOptionPane.showMessageDialog(this, message);
				} 
				
				catch ( ClassNotFoundException | SQLException e ) {
					
					JOptionPane.showMessageDialog(this, "Some Database problem occured.Contact admin asap!");
					logger.error("Class Not Found or SQL exception Occured");
					String message = CommonUtils.stackToString(e);
					logger.error(message);
				
				}
			}
			
		}
		else{
			JOptionPane.showMessageDialog(this, "Password Didn't Match!!");
		}
	}
	
	public boolean verifyPasswordMatch(){
		String passwordValue = new String(passwordTxtField.getPassword()).intern();
		String confirmPasswordValue = new String(confirmPasswordTxtField.getPassword()).intern();
		if(passwordValue == confirmPasswordValue){
			return true;
		}
		return false;
	}
	
	public void checkPasswordStrength(){
//		String strongPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}";
		String strongPattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}";
		String goodPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{5,}";
		String password = new String(passwordTxtField.getPassword());
		if(password.matches(strongPattern)){
			passwordStrengthLabel.setText("Strong");
			passwordStrengthLabel.setForeground(Color.GREEN);
		}
		else if(password.matches(goodPattern)){
			passwordStrengthLabel.setText("Good");
			passwordStrengthLabel.setForeground(Color.ORANGE);
		}
		else{
			passwordStrengthLabel.setText("Weak");
			passwordStrengthLabel.setForeground(Color.RED);
		}
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterView frame = new RegisterView();
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
	public RegisterView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 388, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setBounds(64, 21, 215, 35);
		contentPane.add(lblRegister);
		
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setBounds(44, 87, 76, 16);
		contentPane.add(lblFullName);
		
		fullNameTxtField = new JTextField();
		fullNameTxtField.setBounds(175, 78, 166, 35);
		contentPane.add(fullNameTxtField);
		fullNameTxtField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(44, 139, 76, 16);
		contentPane.add(lblUsername);
		
		userNameTxtField = new JTextField();
		userNameTxtField.setColumns(10);
		userNameTxtField.setBounds(175, 130, 166, 35);
		contentPane.add(userNameTxtField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(44, 196, 76, 16);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(44, 253, 123, 16);
		contentPane.add(lblConfirmPassword);
		
		passwordTxtField = new JPasswordField();
		passwordTxtField.setBounds(175, 187, 166, 35);
		contentPane.add(passwordTxtField);
		passwordTxtField.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				checkPasswordStrength();
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}
		});
		
		confirmPasswordTxtField = new JPasswordField();
		confirmPasswordTxtField.setBounds(175, 244, 166, 35);
		contentPane.add(confirmPasswordTxtField);
		
		JLabel lblEmail = new JLabel("Email ID");
		lblEmail.setBounds(44, 314, 76, 16);
		contentPane.add(lblEmail);
		
		passwordStrengthLabel = new JLabel("Strength");
		passwordStrengthLabel.setVerticalAlignment(SwingConstants.TOP);
		passwordStrengthLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		passwordStrengthLabel.setBounds(180, 221, 156, 27);
		contentPane.add(passwordStrengthLabel);
		
		emailIDTxtField = new JTextField();
		emailIDTxtField.setColumns(10);
		emailIDTxtField.setBounds(175, 305, 166, 35);
		contentPane.add(emailIDTxtField);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(44, 374, 76, 16);
		contentPane.add(lblCity);
		
		String city[] = {"Delhi","Mumbai","Haryana","Rajasthan","Tamil Nadu","Kerala","UP","J&K"};
		
		cityComboBox = new JComboBox(city);
		cityComboBox.setBounds(175, 370, 166, 27);
		contentPane.add(cityComboBox);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(44, 420, 76, 16);
		contentPane.add(lblGender);
		
		JRadioButton maleRadioBtn = new JRadioButton("Male");
		maleRadioBtn.setSelected(true);
		maleRadioBtn.setFocusPainted(false);
		maleRadioBtn.setBounds(175, 416, 61, 23);
		maleRadioBtn.setActionCommand("Male");
		contentPane.add(maleRadioBtn);
		
		JRadioButton femaleRadioBtn = new JRadioButton("Female");
		femaleRadioBtn.setFocusPainted(false);
		femaleRadioBtn.setBounds(247, 416, 83, 23);
		femaleRadioBtn.setActionCommand("Female");
		contentPane.add(femaleRadioBtn);
		
		//For selecting only one of the radio Buttons for gender we use a class ButtonGroup
		btnGrp = new ButtonGroup();
		btnGrp.add(maleRadioBtn);
		btnGrp.add(femaleRadioBtn);
		
		JButton registerBtn = new JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerUser();
			}
		});
		registerBtn.setOpaque(true);
		registerBtn.setBackground(Color.LIGHT_GRAY);
		registerBtn.setBorderPainted(false);
		registerBtn.setRequestFocusEnabled(false);
		registerBtn.setBounds(119, 472, 117, 41);
		contentPane.add(registerBtn);
	}
}
