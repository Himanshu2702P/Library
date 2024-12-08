package com.library.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.dto.BookDto;
@Service
public interface BookService {

	 BookDto createBook(BookDto bookDto);
	    List<BookDto> getBooks();
	    BookDto getBookById(Long bookId);
	    BookDto updateBook(Long book_id, BookDto book);
	    void deleteBook(Long book_id);
	    BookDto borrowBook(Long book_id, Long user_id);
	    BookDto returnBook(Long book_id);
}
