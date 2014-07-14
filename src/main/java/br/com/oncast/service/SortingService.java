package br.com.oncast.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import br.com.oncast.Enum.BookAttribute;
import br.com.oncast.comparator.BookComparator;
import br.com.oncast.exception.OrderingException;
import br.com.oncast.model.Book;
import br.com.oncast.model.Sorting;

public class SortingService implements ISortingService {

	private Sorting[] orderDefaut= {new Sorting(BookAttribute.TITLE ,Sorting.ASCENDING),new Sorting(BookAttribute.AUTHOR ,Sorting.ASCENDING)};
	
	public SortingService() {
		
	}

	public Set<Book> sort(Set<Book> books) throws OrderingException{
		return sort(books,orderDefaut);
	}

	public Set<Book> sort(Set<Book> books, Sorting[] ordination) throws OrderingException {
		if(books == null || ordination==null|| ordination.length==0)
			throw new OrderingException();
		else if(books.isEmpty())
			return books;
		
				TreeSet<Book> sortedSet = new TreeSet<Book>(new BookComparator(ordination));
				sortedSet.addAll(books);
				
		return sortedSet;
	}

}
