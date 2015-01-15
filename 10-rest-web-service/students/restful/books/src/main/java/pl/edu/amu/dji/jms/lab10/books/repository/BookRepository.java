package pl.edu.amu.dji.jms.lab10.books.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.edu.amu.dji.jms.lab10.books.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
	
	public List<Book> getBooksList();
	public Book getBookByIsbn(String isbn);
	
	public void createNewBook(Book book);
	public void removeBookByIsbn(String isbn);
	public void updateBookByIsbn(String isbn, Book book);
	
}
