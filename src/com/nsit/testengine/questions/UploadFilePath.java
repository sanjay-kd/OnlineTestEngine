package com.nsit.testengine.questions;

public class UploadFilePath {
	private String fileName;

	public String getFileName(String filePath){
		
		int beginIndex = filePath.lastIndexOf("/");
		fileName = filePath.substring(beginIndex+1);
		return fileName;
	}
	
//	public static void main( String[] args ){
//		UploadFilePath file = new UploadFilePath();
//		file.getUploadFilePath("/Users/sdfhs.pdf");
//	}
}
