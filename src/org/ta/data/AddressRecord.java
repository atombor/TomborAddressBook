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
public class AddressRecord {
	
	private String name;
	private String phoneNumber;
	
	public AddressRecord(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	protected void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	protected void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
}
