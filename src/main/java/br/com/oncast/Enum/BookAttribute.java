package br.com.oncast.Enum;

/**
 * keep the same name of the book's Attribute
 *
 */
public enum BookAttribute {
	TITLE("title"),AUTHOR("author"),YEAR("year");
	private String attribute;
	
	BookAttribute(String attribute){
		this.attribute = attribute;
	}
	
	public String getAttribute() {
		return attribute;
	}
}
