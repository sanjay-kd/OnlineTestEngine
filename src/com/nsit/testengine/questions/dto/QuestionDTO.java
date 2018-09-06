package com.nsit.testengine.questions.dto;

public class QuestionDTO {
	private int QId;
	private String QName;
	private String Ans1;
	private String Ans2;
	private String Ans3;
	private String Ans4;
	private String RAns;
	private int Score;
	private String yourAns;
	
	public String getYourAns() {
		return yourAns;
	}
	public void setYourAns(String yourAns) {
		this.yourAns = yourAns;
	}
	public int getQId() {
		return QId;
	}
	public void setQId(int qId) {
		QId = qId;
	}
	public String getQName() {
		return QName;
	}
	public void setQName(String qName) {
		QName = qName;
	}
	public String getAns1() {
		return Ans1;
	}
	public void setAns1(String ans1) {
		Ans1 = ans1;
	}
	public String getAns2() {
		return Ans2;
	}
	public void setAns2(String ans2) {
		Ans2 = ans2;
	}
	public String getAns3() {
		return Ans3;
	}
	public void setAns3(String ans3) {
		Ans3 = ans3;
	}
	public String getAns4() {
		return Ans4;
	}
	public void setAns4(String ans4) {
		Ans4 = ans4;
	}
	public String getRAns() {
		return RAns;
	}
	public void setRAns(String rAns) {
		RAns = rAns;
	}
	public int getScore() {
		return Score;
	}
	public void setScore(int score) {
		Score = score;
	}
	
}
