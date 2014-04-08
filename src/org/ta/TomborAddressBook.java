/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.ta;

import java.util.List;
import java.util.Scanner;
import org.ta.data.AddressRecord;
import org.ta.data.DataHandler;
import org.ta.data.DataHandlerFactory;

/**
 *
 * @author Attila Tombor
 */
public class TomborAddressBook {

	private enum Menu { 
		PRINT_ALL("p", "Print all"),
		SEARCH("s", "Search"),
		ADD("a", "Add new address"),
		QUIT("q", "Quit");
		
		private final String key;
		private final String label;
		Menu(String key, String label) {
			this.key = key;
			this.label = label;
		}
		private String key() {
			return key;
		}
		private String label() {
			return label + "               ".substring(0, 15-label.length());
		}
		private static Menu getMenuElementByKey(String key) {
			for(Menu menu: Menu.values()) {
				if (menu.key().equals(key)) {
					return menu;
				}
			}
			return null;
		}
	} ;	
	
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
			
		
		Scanner systemInput = new Scanner(System.in);
		String inputStr = null;
		Menu inputMenu = null;
		do {
			//Printing menu
			for(Menu menu: Menu.values()) {
				System.out.println(menu.label() + "\t\t (" + menu.key() + ")");
			}
			System.out.print("Choose from the menu options: ");
			inputStr = systemInput.next();
			inputMenu = Menu.getMenuElementByKey(inputStr);

			if (inputMenu != null) {
				switch (inputMenu) {
					case ADD: {
						String inputLine = systemInput.next();
						String[] data = inputLine.split(",");
						dataHandler.addAddressRecord(data[0], data[1]);
						break;
					}
					case PRINT_ALL: {
						List<AddressRecord> addressList = dataHandler.getAllAddressRecord();
						for(AddressRecord addr: addressList) {
							System.out.println(addr.toString());
						}
						break;
					}
				}
			}
			
		} while (! Menu.QUIT.equals(inputMenu));

		dataHandler.saveDataList();
		
		//exit
	}
	
}
