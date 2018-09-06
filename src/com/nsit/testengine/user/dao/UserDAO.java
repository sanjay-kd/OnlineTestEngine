package com.nsit.testengine.user.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nsit.testengine.user.dto.RightDTO;
import com.nsit.testengine.user.dto.UserDTO;
import com.nsit.testengine.util.CommonDAO;

public class UserDAO {

	public UserDTO doLogin(String userId, String password) throws SQLException, ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		UserDTO userDTO = null;
		ArrayList<RightDTO> rightList =null;
		try {
			connection = CommonDAO.getConnection();
			// String query = "SELECT * FROM USERS WHERE userid=? and password
			// =?";
			String query = "SELECT USER_MST.USERID, ROLE_MST.NAME AS ROLENAME, RIGHT_MST.NAME AS RIGHTNAME, RIGHT_MST.SCREENNAME "
					+ "FROM USER_MST, ROLE_MST, RIGHT_MST, USER_ROLE_MAPPING, ROLE_RIGHT_MAPPING "
					+ "WHERE USER_MST.UID = USER_ROLE_MAPPING.UID AND ROLE_MST.ROLEID = USER_ROLE_MAPPING.ROLEID "
					+ "AND ROLE_MST.ROLEID = ROLE_RIGHT_MAPPING.ROLEID "
					+ "AND RIGHT_MST.RIGHTID = ROLE_RIGHT_MAPPING.RIGHTID "
					+ "AND USER_MST.USERID =? AND USER_MST.PASSWORD = ?;";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			

			 while(resultSet.next()) {
				if (userDTO == null){
					userDTO = new UserDTO();
					userDTO.setUserName(resultSet.getString("userid"));
					userDTO.setRoleName(resultSet.getString("rolename"));
					rightList = new ArrayList<>();
					userDTO.setRightList(rightList);
				}
				rightList.add(new RightDTO(resultSet.getString("rightname"),resultSet.getString("screenname")));
			}
				return userDTO;
			
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

	public String doRegister(UserDTO userDto) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = CommonDAO.getConnection();
			String query = "INSERT INTO USERS(fullName,userId,password,city,emailId,gender) VALUES(?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, userDto.getFullName());
			preparedStatement.setString(2, userDto.getUserName());
			preparedStatement.setString(3, userDto.getPassword());
			preparedStatement.setString(4, userDto.getCity());
			preparedStatement.setString(5, userDto.getEmailID());
			preparedStatement.setString(6, userDto.getGender());
			preparedStatement.executeUpdate();
			return "Registered Succesfull";
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

	}

}
