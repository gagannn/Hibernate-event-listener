package com.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.dto.BookDto;
import com.spring.boot.service.BookService;

@RestController
@RequestMapping("book/v1")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("book")
	public String addBook(@RequestBody BookDto book) {
		return bookService.addBook(book);
	}
	
	@GetMapping("getBook/{id}")
	public String getBookById(@PathVariable Long id) {
		return bookService.getBookById(id).toString();
	}

	@DeleteMapping("deleteBook/{id}")
	public String deleteBookById(@PathVariable Long id) {
		 bookService.deleteBookById(id);
		 return "Successfully deleted";
	}
	
	@PutMapping("updateBook")
	public String updateBookById(@RequestBody BookDto bookDto) {
		return bookService.updateBook(bookDto);
	}
}
