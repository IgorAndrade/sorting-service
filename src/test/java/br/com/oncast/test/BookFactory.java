package br.com.oncast.test;

import java.util.HashSet;
import java.util.Set;

import br.com.oncast.model.Book;

public class BookFactory {

	
	public static Set<Book> generate(){
		HashSet<Book> set = new HashSet<Book>();
		set.add(new Book(1,"Java How to Program","Deitel & Deitel",2007));
		set.add(new Book(2,"Patterns of Enterprise Application Architecture","Martin Fowler",2002));
		set.add(new Book(3,"Head First Design Patterns","Elisabeth Freeman",2004));
		set.add(new Book(4,"Internet & World Wide Web: How to Program","Deitel & Deitel",2007));
		return set;
	}
	
}
