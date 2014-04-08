/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ta.data;

/**
 *
 * @author atombor
 */
public class DataHandlerFactory {

	private static final String DEFAULT_DATAFILE_NAME = "data.txt";
	
	static DataHandler instance = null;
	static String fileName = null;
	
	public static DataHandler initDataHandler(final String fileName) {
		if (fileName == null) {
			DataHandlerFactory.fileName = DEFAULT_DATAFILE_NAME;
		}
		else {
			DataHandlerFactory.fileName = fileName;
		}
		return new DataHandler(DataHandlerFactory.fileName);
	}
	
	
	public static DataHandler getDataHandlerInstance() {
		if (instance == null ) {
			return initDataHandler(fileName);
		}
		return instance;
	}
	
}
