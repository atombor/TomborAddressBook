package org.ta.data;

/**
 * Represents the basic data record of the application.
 * implements java.lang.CharSequence to support Files.write
 * @author atombor
 */
public class AddressRecord implements CharSequence {
	
	private String name;
	private String phoneNumber;
	
	public AddressRecord(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return name + "," + phoneNumber;
	}
	@Override
	public CharSequence subSequence(int start, int end) {
		return this.toString().subSequence(start, end);
	}
	@Override
	public char charAt(int i) {
		return this.toString().charAt(i);
	}
	@Override
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
