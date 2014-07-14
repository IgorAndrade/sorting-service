package br.com.oncast.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import br.com.oncast.Enum.BookAttribute;
import br.com.oncast.exception.OrderingException;
import br.com.oncast.model.Book;
import br.com.oncast.model.Sorting;
import br.com.oncast.service.ISortingService;
import br.com.oncast.service.SortingService;

public class ISortingServiceTest {

	private ISortingService service;
	private Set<Book> books;
	
	
	@Before
	public void before(){
		this.service = new SortingService();
	}
	
	@Test 
	public void titleAscendingTest() throws OrderingException{
		Sorting[] ordination= {new Sorting(BookAttribute.TITLE ,Sorting.ASCENDING)};
		
		Book[] expecteds = {new Book(3,"Head First Design Patterns","Elisabeth Freeman",2004),
				new Book(4,"Internet & World Wide Web: How to Program","Deitel & Deitel",2007),
				new Book(1,"Java How to Program","Deitel & Deitel",2007),
				new Book(2,"Patterns of Enterprise Application Architecture","Martin Fowler",2002)};
		Set<Book> setOrdened = service.sort(BookFactory.generate(),ordination);
		//assertArrayEquals("Not ordened by Title",expecteds, service.sort(BookFactory.generate(),order).toArray());
		
		int i = 0;
		for (Iterator book = setOrdened.iterator(); book.hasNext();) {
			if (!expecteds[i].equals((Book) book.next()) ){
				fail("Not ordened by Title");
				break;
			}
			i++;
		}
		
		
	}
	
	@Test 
	public void authorAscending_And_TitleDescendingTest() throws OrderingException{
	
		Sorting[] ordination= {new Sorting(BookAttribute.AUTHOR , Sorting.ASCENDING),new Sorting(BookAttribute.TITLE , Sorting.DECREASING)};
		
		
		Book[] expecteds = {new Book(1,"Java How to Program","Deitel & Deitel",2007),
				new Book(4,"Internet & World Wide Web: How to Program","Deitel & Deitel",2007),
				new Book(3,"Head First Design Patterns","Elisabeth Freeman",2004),
				new Book(2,"Patterns of Enterprise Application Architecture","Martin Fowler",2002)};
		
		//assertArrayEquals("Not ordened by Author and Title",expecteds, service.sort(BookFactory.generate(),order).toArray());
		Set<Book> setOrdened = service.sort(BookFactory.generate(),ordination);
		int i = 0;
		for (Iterator book = setOrdened.iterator(); book.hasNext();) {
			if (!expecteds[i].equals((Book) book.next()) ){
				fail("Not ordened by Author and Title");
				break;
			}
			i++;
		}
	}
	
	@Test
	public void editionDescending_And_AuthorDescending_And_TitleAscendingTest() throws OrderingException{
		Sorting[] ordination= {new Sorting(BookAttribute.YEAR , Sorting.DECREASING),new Sorting(BookAttribute.AUTHOR , Sorting.DECREASING),new Sorting(BookAttribute.TITLE , Sorting.ASCENDING)};
		Book[] expecteds = {new Book(4,"Internet & World Wide Web: How to Program","Deitel & Deitel",2007),
				new Book(1,"Java How to Program","Deitel & Deitel",2007),
				new Book(3,"Head First Design Patterns","Elisabeth Freeman",2004),
				new Book(2,"Patterns of Enterprise Application Architecture","Martin Fowler",2002)};
		
		
	//	Book[] array2 = service.sort(BookFactory.generate(),order).toArray(new Book[4]);
		
		int i = 0;
		for (Iterator book = service.sort(BookFactory.generate(),ordination).iterator(); book.hasNext();) {
			if (!expecteds[i].equals((Book) book.next()) ){
				fail("Not ordened by Author and Title");
				break;
			}
			i++;
		}
	}
	
	
	@Test(expected=OrderingException.class) 
	public void nullSetTest() throws OrderingException{
		Sorting[] ordination= {};
		service.sort(null, ordination);
	}
	
	@Test 
	public void emptySetTest() throws OrderingException{
		Sorting[] ordination= {new Sorting(BookAttribute.TITLE ,Sorting.ASCENDING)};
		assertTrue("Not empty", service.sort(new HashSet<Book>(),ordination).isEmpty());
	}
	

}
