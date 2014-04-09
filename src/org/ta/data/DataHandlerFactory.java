package org.ta.data;

/**
 * Simple class to make sure, that only one DataHandler object is present
 * in the JVM.
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
