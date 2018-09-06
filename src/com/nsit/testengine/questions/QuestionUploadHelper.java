package com.nsit.testengine.questions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nsit.testengine.constants.Constants;
import com.nsit.testengine.questions.dao.QuestionDAO;
import com.nsit.testengine.questions.dto.QuestionDTO;

public class QuestionUploadHelper {
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	private FileOutputStream fos;
	private BufferedOutputStream bos;
	private boolean uploadStatus = false;
	private boolean firstRow = true;

	public void writeToDB(String completePath) throws IOException {
		int counter;
		ArrayList<QuestionDTO> arrayList = new ArrayList<>();
		QuestionDAO questionDAO = new QuestionDAO();
		System.out.println("Path is : " + completePath);
		file = new File(completePath);
		fis = new FileInputStream(file);
		Workbook workBook = null;
		int beginIndex = completePath.lastIndexOf(".");
		if (completePath.substring(beginIndex + 1).equalsIgnoreCase("xls")) {
			workBook = new HSSFWorkbook(fis);
		}
		if (completePath.substring(beginIndex + 1).equalsIgnoreCase("xlsx")) {
			workBook = new XSSFWorkbook(fis);
		}

		Sheet sheet = workBook.getSheetAt(0);
		Iterator<Row> row = sheet.rowIterator();
		while (row.hasNext()) {
			Row currentRow = row.next();
			QuestionDTO questionDTO = new QuestionDTO();
			counter=0;
			if (firstRow) {
				firstRow = false;
				continue;
			}
			Iterator<Cell> cell = currentRow.cellIterator();
			while (cell.hasNext()) {
				Cell currentCell = cell.next();
				counter++;
				if (counter == 1) {
					questionDTO.setQId((int) currentCell.getNumericCellValue());
				}

				if (counter == 2) {
					questionDTO.setQName(currentCell.getStringCellValue());
				}

				if (counter == 3) {
					questionDTO.setAns1(currentCell.getStringCellValue());
				}
				
				if (counter == 4) {
					questionDTO.setAns2(currentCell.getStringCellValue());
				}
				
				if (counter == 5) {
					questionDTO.setAns3(currentCell.getStringCellValue());
				}
				
				if (counter == 6) {
					questionDTO.setAns4(currentCell.getStringCellValue());
				}
				
				if (counter == 7) {
					questionDTO.setRAns(currentCell.getStringCellValue());
				}
				
				if (counter == 8) {
					questionDTO.setScore((int) currentCell.getNumericCellValue());
				}

			}
			arrayList.add(questionDTO);
		}
		try {
			System.out.println("ArrayList size is : "+arrayList.size());
			questionDAO.uploadQuestionToDB(arrayList);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error occured!!");
			e.printStackTrace();
		}
		

	}

	public String uploadFile(String path) throws IOException {
		UploadFilePath uploadFilePath = new UploadFilePath();
		String fileName = uploadFilePath.getFileName(path);

		String completeFileUploadPath = Constants.UPLOAD_FILE_PATH + fileName;

		try {
			File getFile = new File(path);
			File uploadFile = new File(completeFileUploadPath);

			fos = new FileOutputStream(uploadFile);
			bos = new BufferedOutputStream(fos);

			if (getFile.exists()) {
				fis = new FileInputStream(getFile);
				bis = new BufferedInputStream(fis);
				int singleByte = bis.read();
				while (singleByte != -1) {
					bos.write(singleByte);
					singleByte = bis.read();
					uploadStatus = true;
				}
			}
		} finally {
			if (bos != null) {
				bos.close();
			}
			if (fos != null) {
				fos.close();
			}
			if (bis != null) {
				bis.close();
			}
			if (fis != null) {
				fis.close();
			}
		}

		writeToDB(completeFileUploadPath);
		return uploadStatus ? "Uploaded Succesfully" : "Upload Failure";
	}

}
