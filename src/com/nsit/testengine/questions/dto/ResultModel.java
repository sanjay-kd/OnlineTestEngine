package com.nsit.testengine.questions.dto;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ResultModel extends AbstractTableModel{
	
	ArrayList<QuestionDTO> questionDTOArrayList;
	String[] columnNames = {"Q No.","Question","Right Answer","Your Answer","Score"};
	
	public ResultModel(ArrayList<QuestionDTO> questionDTOArrayList){
		this.questionDTOArrayList = questionDTOArrayList;
	}
	
	

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}



	@Override
	public int getRowCount() {
		return questionDTOArrayList.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		QuestionDTO questionDTO = questionDTOArrayList.get(rowIndex);
		switch(columnIndex){
		case 0: 
			return questionDTO.getQId();
		case 1 : 
			return questionDTO.getQName();
		case 2 : 
			return questionDTO.getRAns();
		case 3 : 
			return questionDTO.getYourAns();
		case 4 : 
			return questionDTO.getScore();
		
		}
		return null;
	}

}
