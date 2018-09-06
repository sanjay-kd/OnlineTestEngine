package com.nsit.testengine.questions.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nsit.testengine.constants.Query;
import com.nsit.testengine.questions.dto.QuestionDTO;
import com.nsit.testengine.util.CommonDAO;

public class QuestionDAO {
	
	public void uploadQuestionToDB(ArrayList<QuestionDTO> arrayList) throws ClassNotFoundException, SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null ;
		try{
		connection  = CommonDAO.getConnection();
		preparedStatement = connection.prepareStatement(Query.QUESTION_UPLOAD);
		for(QuestionDTO question : arrayList){
		preparedStatement.setInt(1, question.getQId());
		preparedStatement.setString(2, question.getQName());
		preparedStatement.setString(3, question.getAns1());
		preparedStatement.setString(4, question.getAns2());
		preparedStatement.setString(5, question.getAns3());
		preparedStatement.setString(6, question.getAns4());
		preparedStatement.setString(7, question.getRAns());
		preparedStatement.setInt(8, question.getScore());
		preparedStatement.addBatch();
		}
		preparedStatement.executeBatch();
	}finally{
		if(preparedStatement!=null){
			preparedStatement.close();
		}
		if(connection!=null){
			connection.close();
		}
	}
	}
}
