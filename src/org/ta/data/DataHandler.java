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
import java.util.Collections;
import java.util.List;

/**
 * This class provides support for the data operations for reading and writing
 * AddressRecord data to the text file.
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
			sortByName();
		}
	
	}
	
	private void sortByName() {
		Collections.sort(dataList, (
			AddressRecord ar1, AddressRecord ar2) -> ar1.getName().compareTo(ar2.getName()));
	}
	

	private AddressRecord parseFileLine(String text) {
		String[] dataLine = text.split(",");
		if (dataLine.length == 2) {
			return new AddressRecord(dataLine[0], dataLine[1]);
		}
		return null;
	}
	
	public void saveDataList() {
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
		sortByName();
	}
	
	public List<AddressRecord> findAllAddressRecord() {
		return this.dataList;
	}
	
	public List<AddressRecord> findAddressRecordByName(String phrase) {
		//TODO: implement improved search
		ArrayList<AddressRecord> retVal = new ArrayList<>();
		this.dataList.stream().forEach((addr) -> {
			if (addr.getName().contains(phrase)) {
				retVal.add(addr);
			}
		});
		return retVal;
	}

}
