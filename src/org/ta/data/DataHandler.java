/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ta.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author atombor
 */
public class DataHandler {

	private ArrayList<AddressRecord> dataList = null;

	protected DataHandler(final String fileName) {
		dataList = new ArrayList();
		File file = new File(fileName);

		//file.isDirectory() ?
		BufferedReader lineReader = null;

		try {

			if (file.exists()) {

				lineReader = new BufferedReader(new FileReader(file));
				String text = null;

				while ((text = lineReader.readLine()) != null) {
					AddressRecord addressRecord = parseFileLine(text);
					if (addressRecord == null) {
						System.out.println("Error parsing file line: " + text);
					} else {
						dataList.add(addressRecord);
					}
				}
			} else {
				file.createNewFile();
			}

		} /*
		 catch (FileNotFoundException e) {
		 e.printStackTrace();
		 }
		 */ catch (IOException e) {
			System.out.println("IOExceotion occured at file opening" + e.getStackTrace());
		} finally {
			try {
				if (lineReader != null) {
					lineReader.close();
				}
			} catch (IOException e) {
			}
		}

	}

	private AddressRecord parseFileLine(String text) {
		String[] line = text.split(",");
		if (line.length == 2) {
			return new AddressRecord(line[0], line[1]);
		}
		return null;
	}

}
