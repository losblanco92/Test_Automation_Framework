package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ui.pojo.User;

public class ExcelReaderUtility {

	public static Iterator<User> readExcelFile(String fileName) {

		File xlsxFile = new File(System.getProperty("user.dir") + "//Test-Data//"+fileName);
		XSSFSheet sheet;
		XSSFWorkbook workbook = null;
		Iterator<Row> rowIterator;
		List<User> userList = null;

		User user;
		Row row;
		Cell emailAddressCell;
		Cell passwordCell;
		try {
			workbook = new XSSFWorkbook(xlsxFile);
			sheet = workbook.getSheet("LoginTest");
			if (sheet == null) {
			    throw new RuntimeException("Sheet not found! Check sheet name in Excel.");
			}

			rowIterator = sheet.iterator();
			rowIterator.next();

			userList = new ArrayList<User>();

			while (rowIterator.hasNext()) {
				row = rowIterator.next();

				emailAddressCell = row.getCell(0);
				passwordCell = row.getCell(1);
				user = new User(emailAddressCell.toString(), passwordCell.toString());
				userList.add(user);
			}

			workbook.close();
			
		} catch (InvalidFormatException | IOException e) {

			e.printStackTrace();
		}

		return userList.iterator();

	}
}
