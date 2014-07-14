package br.com.oncast.service;

import java.util.Set;

import br.com.oncast.exception.OrderingException;
import br.com.oncast.model.Book;
import br.com.oncast.model.Sorting;

public interface ISortingService {
	public Set<Book> sort(Set<Book> books) throws OrderingException;
	public Set<Book> sort(Set<Book> books, Sorting[] ordination) throws OrderingException;
}
