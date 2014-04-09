package org.ta;

import java.util.List;
import java.util.Scanner;
import org.ta.data.AddressRecord;
import org.ta.data.DataHandler;
import org.ta.data.DataHandlerFactory;

/**
 * Main class of the Address Book application, that handles name/phone number 
 * data, stored in a text file.
 * @author atombor
 */
public class TomborAddressBook {
	
	private static final Scanner systemInput = new Scanner(System.in);;
	private static DataHandler dataHandler = null;

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
			return label + "....................".substring(0, 20-label.length());
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
		dataHandler = DataHandlerFactory.initDataHandler(dataFileName);
		
		String inputStr = null;
		Menu choosenMenu = null;
		do {

			choosenMenu = printMenu();

			if (choosenMenu != null) {
				handleUserAction(choosenMenu);
			}
			
		} while (! Menu.QUIT.equals(choosenMenu));

		dataHandler.saveDataList();
	}

	private static Menu printMenu() {
		String inputStr = null;
		//Printing menu
		System.out.println();
		for(Menu menu: Menu.values()) {
			System.out.println(menu.label() + "(" + menu.key() + ")");
		}
		System.out.print("Choose from the menu options: ");
		inputStr = systemInput.next();
		System.out.println();
		
		return Menu.getMenuElementByKey(inputStr);
	}
	
	private static void handleUserAction(final Menu choosenMenu) {
		
		switch (choosenMenu) {
			case ADD: {
				System.out.println("Enter new record in format <name>, <phone>:");
				String inputLine = systemInput.next();
				String[] data = inputLine.split(",");
				if (data.length == 2) {
					dataHandler.addAddressRecord(data[0], data[1]);
				}
				else {
					System.out.println("Incorrect input format, address is not saved.");
				}						
				break;
			}
			case PRINT_ALL: {
				List<AddressRecord> addressList = dataHandler.findAllAddressRecord();
				if (addressList.size() > 0) {
					System.out.println("Name\tPhone number");
					addressList.stream().forEach((addr) -> {
						System.out.println(addr.getName() + "\t" + addr.getPhoneNumber());
					});
				}
				else {
					System.out.println("Address list is empty.");
				}
				break;
			}
			case SEARCH: {
				System.out.print("Enter name to search for: ");
				String inputLine = systemInput.next();
				List<AddressRecord> searchResultList = dataHandler.findAddressRecordByName(inputLine);
				searchResultList.stream().forEach((addr) -> {
					System.out.println(addr.toString());
				});
				break;
			}
		}
	}
}
