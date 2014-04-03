/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ta;

import org.ta.data.DataHandler;
import org.ta.data.DataHandlerFactory;

/**
 *
 * @author Attila Tombor
 */
public class TomborAddressBook {

	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
		String dataFileName = null;
		// param validation
		
		if (args.length > 1) {
			System.out.println("The application can have one parameter, the data filename");
			System.exit(1);
		} else if (args. length == 1) {
			dataFileName = args[0];
		} else {
			//stay null, as undefined data file
		}

		// init DB
		DataHandler dataHandler = DataHandlerFactory.initDataHandler(dataFileName);
		
		
				
		//menu
		  //  add - search loop
		
		//exit
	}
	
}
