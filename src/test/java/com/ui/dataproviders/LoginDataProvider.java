package com.ui.dataproviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.Testdata;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;


public class LoginDataProvider {

	@DataProvider(name = "LoginCredentailsDataProvider")
	public Iterator<Object[]> loginDataProvider() {

		File testDataJsonFile = new File(System.getProperty("user.dir") + "/Test-Data/loginData.json");
		FileReader reader = null;
		Gson gson = new Gson();
		try {
			reader = new FileReader(testDataJsonFile);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		Testdata data = gson.fromJson(reader, Testdata.class);

		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();

		for (User user : data.getData()) {
			dataToBeReturned.add(new Object[] { user });
		}

		return dataToBeReturned.iterator();

	}

	@DataProvider(name = "LoginCredentailsDataProviderCSV")
	public Iterator<User> loginCSVDataProvider() {
		return CSVReaderUtility.readCSVFile("loginData.csv");
	}
	
	@DataProvider(name = "LoginCredentailsDataProviderXLSX")
	public Iterator<User> loginXLSXDataProvider() {
		return ExcelReaderUtility.readExcelFile("loginData.xlsx");
	}

}
