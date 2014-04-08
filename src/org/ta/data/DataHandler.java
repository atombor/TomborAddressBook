/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ta.data;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author atombor
 */
public class DataHandler {

	private ArrayList<AddressRecord> dataList = null;

	private final static Charset ENCODING = StandardCharsets.UTF_8;
	private final static OpenOption[] WRITE_OPTIONS = {
		StandardOpenOption.WRITE
    };
	private final static FileAttribute[] FILE_ATTR = {};
	private final static LinkOption[] OPEN_OPTIONS = {};


	protected DataHandler(final String fileName) {
		this.dataList = new ArrayList();

		Path dataFilePath = Paths.get(DataHandlerFactory.fileName);
		List<String> stringDataList = null;
		try {
			if (Files.exists(dataFilePath, OPEN_OPTIONS)) {
				stringDataList = Files.readAllLines(dataFilePath, ENCODING);
			}
			else {
				Files.createFile(dataFilePath, FILE_ATTR);
			}
		} catch (IOException e) {
			System.out.println("IOException occured at file opening.");
			e.printStackTrace(System.out);
		}	

		if (stringDataList != null) {
			for(String line: stringDataList) {
				this.dataList.add( parseFileLine(line) );
			}
		}
	
	}

	private AddressRecord parseFileLine(String text) {
		String[] dataLine = text.split(",");
		if (dataLine.length == 2) {
			return new AddressRecord(dataLine[0], dataLine[1]);
		}
		return null;
	}
	
	public void saveDataList() {
		//TODO remove
			
		Path dataFilePath = Paths.get(DataHandlerFactory.fileName);

		try {
			Files.write(dataFilePath, dataList, ENCODING, WRITE_OPTIONS );	
		} catch (IOException e) {
			System.out.println("IOException occured at file save.");
			e.printStackTrace(System.out);
		}		
	}
	
	public void addAddressRecord(String name, String phoneNumber ) {
		dataList.add(new AddressRecord(name, phoneNumber));
	}
	
	public List<AddressRecord> getAllAddressRecord() {
		return this.dataList;
	}
	
}
