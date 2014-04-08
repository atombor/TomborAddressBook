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
public class AddressRecord implements CharSequence{
	
	private String name;
	private String phoneNumber;
	
	public AddressRecord(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	public String toString() {
		return name + "," + phoneNumber;
	}
	public CharSequence subSequence(int start, int end) {
		return this.toString().subSequence(start, end);
	}
	public char charAt(int i) {
		return this.toString().charAt(i);
	}
	public int length() {
		return this.toString().length();
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
