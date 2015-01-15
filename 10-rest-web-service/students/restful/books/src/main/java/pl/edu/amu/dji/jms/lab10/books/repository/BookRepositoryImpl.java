package pl.edu.amu.dji.jms.lab10.books.repository;

import java.util.ArrayList;
import java.util.List;

import pl.edu.amu.dji.jms.lab10.books.model.Book;

public class BookRepositoryImpl implements BookRepository {

	private List<Book> booksList;
	
	public BookRepositoryImpl() {
		booksList = new ArrayList<>();
	}
	
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Book arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends Book> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Book> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Book> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Book> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Book> Iterable<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksList() {
		return booksList;
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		for(Book book: booksList)
			if(book.getIsbn().equals(isbn))
				return book;
		return null;
	}

	@Override
	public void createNewBook(Book book) {
		booksList.add(book);		
	}

	@Override
	public void updateBookByIsbn(String isbn, Book book) {
		Book prevBook = null;
		for(Book b: booksList)
			if(b.getIsbn().equals(isbn)) {
				prevBook = b;
				break;
			}
		
		prevBook.setAuthors(book.getAuthors());
		prevBook.setDescription(book.getDescription());
		prevBook.setTitle(book.getTitle());
				
	}

	@Override
	public void removeBookByIsbn(String isbn) {
		Book bookToRemove = null;
		for(Book b: booksList)
			if(b.getIsbn().equals(isbn)) {
				bookToRemove = b;
				break;
			}
		booksList.remove(bookToRemove);
	}

}
