package br.com.oncast.comparator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

import br.com.oncast.model.Book;
import br.com.oncast.model.Sorting;

public class BookComparator implements Comparator<Book> {

	private static final String METHOD_GET_PREFIX = "get";
	private static final String DATATYPE_STRING = "java.lang.String";
	private static final String DATATYPE_DATE = "java.util.Date";
	private static final String DATATYPE_INTEGER = "java.lang.Integer";
	private static final String DATATYPE_LONG = "java.lang.Long";
	private static final String DATATYPE_FLOAT = "java.lang.Float";
	private static final String DATATYPE_DOUBLE = "java.lang.Double";
	Sorting[] ordinations;

	public BookComparator(Sorting[] ordinations) {
		this.ordinations = ordinations;
	}

	public int compare(Book book1, Book book2) {
		int result = 0;
		Object value1;
		Object value2;

		try {
			for (Sorting ordination : ordinations) {

				value1 = getValue(book1, ordination.getAttribute());
				value2 = getValue(book2, ordination.getAttribute());
				
				result = compareType(value1,value2,ordination.isASCENDING());
				
				//if the attribute is the same, compare the next
				if(result != 0)
					return result;
			}
			
			
		} catch (SecurityException e) {
			return 0;
		} catch (IllegalArgumentException e) {
			return 0;
		} catch (IllegalAccessException e) {
			return 0;
		} catch (NoSuchMethodException e) {
			return 0;
		} catch (InvocationTargetException e) {
			return 0;
		}

		return result;
	}


	private int compareType(Object value1, Object value2,boolean ascending) {
		
		String returnType = getReturnType(value1);
		
		if (returnType.equals(DATATYPE_INTEGER)) {
			return  (((Integer) value1).compareTo((Integer) value2) * determinePosition(ascending));
		} else if (returnType.equals(DATATYPE_LONG)) {
			return  (((Long) value1).compareTo((Long) value2) * determinePosition(ascending));
		} else if (returnType.equals(DATATYPE_STRING)) {
			return (((String) value1).compareTo((String) value2) * determinePosition(ascending));
		} else if (returnType.equals(DATATYPE_DATE)) {
			return  (((Date) value1).compareTo((Date) value2) * determinePosition(ascending));
		} else if (returnType.equals(DATATYPE_FLOAT)) {
			return  (((Float) value1).compareTo((Float) value2) * determinePosition(ascending));
		} else if (returnType.equals(DATATYPE_DOUBLE)) {
			return  (((Double) value1).compareTo((Double) value2) * determinePosition(ascending));
		}else
			return 0;
		
	}

	private String getReturnType(Object value) {
		
		return value.getClass().getName();
	}

	private final static String prepareTargetMethod(String name) {
		StringBuffer fieldName =  new StringBuffer(METHOD_GET_PREFIX);
		fieldName.append(name.substring(0, 1).toUpperCase());
		fieldName.append(name.substring(1));
		return fieldName.toString();
	}	

	
	private final Method getMethod(Object obj, String attribute) throws NoSuchMethodException {
		return obj.getClass().getMethod(prepareTargetMethod(attribute), null);
	}


	private Object getValue(Object obj, String attribute) throws IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		return getMethod(obj,attribute).invoke(obj, null);
	}
	
	private int determinePosition(boolean ascending){
		if(ascending)
			return 1;
		else
			return -1;
	}
}
