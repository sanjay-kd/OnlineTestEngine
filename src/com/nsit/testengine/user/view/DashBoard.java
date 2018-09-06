package com.nsit.testengine.user.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.nsit.testengine.user.dto.RightDTO;
import com.nsit.testengine.user.dto.UserDTO;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DashBoard extends JFrame {

	private JPanel contentPane;
	JLabel welcomeLabel = new JLabel();

	public void setDashBooard(UserDTO userDTO) {
		if (userDTO != null) {
			welcomeLabel.setText("Welcome " + userDTO.getUserName());
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			JMenu menu = new JMenu("File");
			menuBar.add(menu);
			for(RightDTO rightDTO : userDTO.getRightList()){
				JMenuItem menuItem = new JMenuItem(rightDTO.getRightName());
				menu.add(menuItem);
				menu.addSeparator();
				menuItem.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						//Reflection
						try {
							int lastIndex = rightDTO.getScreenName().indexOf(".java");
							String className = rightDTO.getScreenName().substring(0, lastIndex);
							Object object = Class.forName(className).newInstance();
							Method method = object.getClass().getMethod("setVisible", boolean.class);
							method.invoke(object, true);
							
						} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e1) {
							e1.printStackTrace();
						}
						
					}
					
				});
			}
			
		}
	}

	public DashBoard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// JMenuBar menuBar = new JMenuBar();
		// JMenu menu = new JMenu("File");
		// menu.add(new JMenuItem("New"));
		// menuBar.add(menu);
		//
		// setJMenuBar(menuBar);

		welcomeLabel.setBounds(94, 64, 212, 49);
		contentPane.add(welcomeLabel);
	}
}
