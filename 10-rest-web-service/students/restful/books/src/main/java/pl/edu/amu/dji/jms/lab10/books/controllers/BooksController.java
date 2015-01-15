package pl.edu.amu.dji.jms.lab10.books.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.amu.dji.jms.lab10.books.model.Book;
import pl.edu.amu.dji.jms.lab10.books.repository.BookRepository;

@RestController
//@EnableAutoConfiguration
@RequestMapping(value = "/books")
public class BooksController {

	private final BookRepository bookRepository;
	
	@Autowired
	public BooksController(BookRepository b) {
		bookRepository = b;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Book> getBooksList() {
		return bookRepository.getBooksList();
	}
	
	@RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
	public Book getBookByIsbn(@PathVariable String isbn) {
		return bookRepository.getBookByIsbn(isbn);
	}
	
	@RequestMapping(value = "/{isbn}", method = RequestMethod.DELETE)
	public void deleteBookByIsbn(@PathVariable String isbn) {
		bookRepository.removeBookByIsbn(isbn);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public void createNewBook(@RequestBody Book book) {
		bookRepository.createNewBook(book);
	}
	
	@RequestMapping(value = "/{isbn}", method = RequestMethod.PUT)
	public void updateBookByIsbn(@PathVariable String isbn, @RequestBody Book book) {
		bookRepository.updateBookByIsbn(isbn, book);
	}
	
}
