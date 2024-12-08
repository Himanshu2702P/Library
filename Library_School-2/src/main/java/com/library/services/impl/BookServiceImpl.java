package com.library.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dto.BookDto;
import com.library.entities.Book;
import com.library.entities.User;
import com.library.exception.BookException;
import com.library.exception.UserException;
import com.library.mapper.BookMapper;
import com.library.repositories.BookRepository;
import com.library.repositories.UserRepository;
import com.library.services.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = BookMapper.mapToBook((bookDto));
        Book savedBook = bookRepository.save(book);
        return BookMapper.mapToBookDto(savedBook);
    }

    @Override
    public List<BookDto> getBooks() {
        List<Book> books =  bookRepository.findAll();
        return books.stream().map((book) -> BookMapper.mapToBookDto(book)).collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException("Book is not found with given id : " + bookId));
        return BookMapper.mapToBookDto(book);
    }

    @Override
    public BookDto updateBook(Long book_id, BookDto book) {
        Book existingBook = bookRepository.findById(book_id)
                .orElseThrow(() -> new BookException("Book is not found with given id : " + book_id));
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setBorrowed(book.isBorrowed());
        existingBook.setBorrowedBy(book.getBorrowedBy());

        Book updatedBook = bookRepository.save(existingBook);
        return BookMapper.mapToBookDto(updatedBook);
    }

    @Override
    public void deleteBook(Long book_id) {
        bookRepository.deleteById(book_id);
    }

    @Override
    public BookDto borrowBook(Long book_id, Long user_id) {
        Book book = bookRepository.findById(book_id)
                .orElseThrow(() -> new BookException("Book is not found with given id : " + book_id));

        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new UserException("User is not found with given id : " + user_id));

        if(book != null && user != null){
            if(!book.isBorrowed()){
                book.setBorrowedBy(user);
                book.setBorrowed(true);
                Book savedBook = bookRepository.save(book);
                return BookMapper.mapToBookDto(savedBook);
            }else{
                System.out.println("Book already borrowed");
            }
        }
        return null;
    }

    @Override
    public BookDto returnBook(Long book_id) {
        Book borrowedBook = bookRepository.findById(book_id)
                .orElseThrow(() -> new BookException("Book is not found with given id : " + book_id));

        if(borrowedBook != null){
            if(borrowedBook.isBorrowed()){
                borrowedBook.setBorrowedBy(null);
                borrowedBook.setBorrowed(false);
                Book returnedBook = bookRepository.save(borrowedBook);
                return  BookMapper.mapToBookDto(returnedBook);
            }else{
                System.out.println("Book not borrowed");
            }
        }
        return null;
    }
}
