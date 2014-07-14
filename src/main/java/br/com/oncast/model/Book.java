package br.com.oncast.model;

public class Book  {
	private Integer id;
	private String title;
	private String author;
	private Integer year;
	
	
	public Book(Integer id,String title, String author, Integer year) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.year = year;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	
	public boolean equals(Book book) {

		return this.id.equals(book.getId());
	}
	

	
}
