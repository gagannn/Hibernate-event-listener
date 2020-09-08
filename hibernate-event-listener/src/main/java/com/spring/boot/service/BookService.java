package com.spring.boot.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.dto.BookDto;
import com.spring.boot.model.Book;
import com.spring.boot.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;

	public String addBook(BookDto bookDto) {
		Book book = new Book();
		book.setCreatedon(new Date());
		return addOrUpdateBook(book, bookDto);
	}

	private String addOrUpdateBook(Book book, BookDto bookDto) {
		book.setTitle(bookDto.getTitle());
		bookRepository.save(book);
		return book.toString();

	}

	public String updateBook(BookDto bookDto) {
		Book book = getBookById(bookDto.getId());
		if (book == null)
			return "Book not found";
		return addOrUpdateBook(book, bookDto);

	}

	public Book getBookById(Long id) {
		Optional<Book> bookObj = bookRepository.findById(id);
		if (bookObj.isPresent())
			return bookObj.get();
		else
			return null;
	}

	public String deleteBookById(Long id) {
		Book book = getBookById(id);
		if(book == null)
			return "Book not found";
		else
			bookRepository.deleteById(id);
		return "Book Deleted";
		
	}
}
