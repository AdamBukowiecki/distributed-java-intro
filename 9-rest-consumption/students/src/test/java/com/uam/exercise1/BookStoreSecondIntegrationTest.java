package com.uam.exercise1;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.uam.Application;
import com.uam.model.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class BookStoreSecondIntegrationTest {

	@Autowired
	private RestTemplate restTemplate;

	private String uniqueIsbn;
	private Book testBook;

	@Before
	public void setUp() {
		uniqueIsbn = Long.toString(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE / 100, Long.MAX_VALUE));
		testBook = new Book(uniqueIsbn, "testTitle", "testDescription", "testAuthor");
	}

	@Test
	public void bookShouldBeCreatedAndDeleted() {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		List<Book> booksList = restTemplate.exchange(
				Book.URL, 
				HttpMethod.GET, 
				new HttpEntity<Object>(httpHeaders), 
				new ParameterizedTypeReference<List<Book>>() {}
				).getBody();
		
		Assertions.assertThat(booksList).doesNotContain(testBook);
		
		final String testBookUrl = Book.URL;
		restTemplate.postForObject(testBookUrl, testBook, Book.class);
		
		booksList = restTemplate.exchange(
				Book.URL, 
				HttpMethod.GET, 
				new HttpEntity<Object>(httpHeaders), 
				new ParameterizedTypeReference<List<Book>>() {}
				).getBody();
		
		Assertions.assertThat(booksList).contains(testBook);
		
		restTemplate.delete(testBookUrl + testBook.getIsbn());
		
		booksList = restTemplate.exchange(
				Book.URL, 
				HttpMethod.GET, 
				new HttpEntity<Object>(httpHeaders), 
				new ParameterizedTypeReference<List<Book>>() {}
				).getBody();
		
		Assertions.assertThat(booksList).doesNotContain(testBook);
		
	}
}
