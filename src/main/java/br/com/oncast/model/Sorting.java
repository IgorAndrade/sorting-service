package br.com.oncast.model;

import br.com.oncast.Enum.BookAttribute;

public class Sorting {

	public static final boolean ASCENDING = true;
	public static final boolean DECREASING = false;
	
	private BookAttribute attribute;
	private boolean ORDER;
	/**
	 * 
	 * @param attribute - Attribute of the Book (BookAttribute)
	 * @param order - True is ASCENDING and false is DECREASING
	 */
	public Sorting(BookAttribute attribute, boolean order) {
		super();
		this.attribute = attribute;
		this.ORDER = order;
	}
	
	public String getAttribute() {
		return attribute.getAttribute();
	}
	public void setAttribute(BookAttribute attribute) {
		this.attribute = attribute;
	}
	public boolean isASCENDING() {
		return ORDER;
	}
	public void setOrder(boolean order) {
		this.ORDER = order;
	}
	
	
}
