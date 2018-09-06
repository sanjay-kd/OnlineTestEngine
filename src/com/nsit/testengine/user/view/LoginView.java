package com.nsit.testengine.user.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.nsit.testengine.user.dao.UserDAO;
import com.nsit.testengine.user.dto.UserDTO;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxtField;
	private JPasswordField passwordTxtField;

	public void loginUser(){
		String userId = userNameTxtField.getText();
		String password = new String(passwordTxtField.getPassword());
		UserDAO userDao = new UserDAO();
		try {
			UserDTO userDTO = userDao.doLogin(userId, password);
			DashBoard dashBoard = new DashBoard();
			dashBoard.setDashBooard(userDTO);
			dashBoard.setVisible(true);
			this.setVisible(false);
			this.dispose();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, "Class not found exception occured.Contact admin!!");
			e.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "SQLException occured.Contact admin asap!!");
			e.printStackTrace();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "Some serious problem occured.Contact admin!!");
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 353, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblLogin.setBounds(107, 16, 120, 37);
		contentPane.add(lblLogin);
		
		JLabel label = new JLabel("Username");
		label.setBounds(37, 97, 76, 16);
		contentPane.add(label);
		
		userNameTxtField = new JTextField();
		userNameTxtField.setColumns(10);
		userNameTxtField.setBounds(125, 88, 166, 35);
		contentPane.add(userNameTxtField);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setBounds(37, 167, 76, 16);
		contentPane.add(label_1);
		
		passwordTxtField = new JPasswordField();
		passwordTxtField.setBounds(125, 158, 166, 35);
		contentPane.add(passwordTxtField);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginUser();
			}
		});
		loginBtn.setRequestFocusEnabled(false);
		loginBtn.setOpaque(true);
		loginBtn.setBorderPainted(false);
		loginBtn.setBackground(Color.LIGHT_GRAY);
		loginBtn.setBounds(110, 244, 117, 41);
		contentPane.add(loginBtn);
	}
}
