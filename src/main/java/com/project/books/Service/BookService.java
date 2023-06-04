package com.project.books.Service;

import com.project.books.Model.Author;
import com.project.books.Model.Book;
import com.project.books.Model.BookWithAuthorResponse;
import com.project.books.Repository.AuthorRepository;
import com.project.books.Repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.project.books.Constants.ExceptionConstants.BOOK_DOES_NOT_EXIST_EXCEPTION;
import static com.project.books.Constants.ExceptionConstants.INSERTION_EXCEPTION;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public void insertBook(Book book) {
        if (authorRepository.findAuthorById(book.getAuthorId()) == null){
            throw new RuntimeException(INSERTION_EXCEPTION);
        }

        bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
       return bookRepository.findAll();
    }


    public List<Book> getBooksByAuthorId(Integer authorId) {
        return bookRepository.findBooksByAuthorId(authorId);
    }

    public Book getBookById(Integer id) {
        if (bookRepository.findBookById(id) == null){
            throw new RuntimeException(BOOK_DOES_NOT_EXIST_EXCEPTION);
        }
        return bookRepository.findBookById(id);
    }

    public void deleteBookById(Integer id) {
        if (bookRepository.findBookById(id) == null){
            throw new RuntimeException(BOOK_DOES_NOT_EXIST_EXCEPTION);
        }
        bookRepository.deleteBookById(id);
    }

    public void updateBookById(Integer id, String title, String description, Integer authorId) {
        if (bookRepository.findBookById(id) == null){
            throw new RuntimeException(BOOK_DOES_NOT_EXIST_EXCEPTION);
        }
        bookRepository.updateBookById(id, title, description, authorId);
    }

    public List<BookWithAuthorResponse> getBooksWithAuthorInfo() {
        List<BookWithAuthorResponse> booksWithAuthors = new ArrayList<>();
        bookRepository.findAll().forEach(
                (book) -> {
                    Author author = authorRepository.findAuthorById(book.getAuthorId());
                    BookWithAuthorResponse fullBook = new BookWithAuthorResponse(book.getId(), book.getTitle(), book.getDescription(), author.getName(), author.getEmail());
                    booksWithAuthors.add(fullBook);
                }
        );
        return booksWithAuthors;
    }

    public Page<Book> getBooksPagination(int offset, int pageSize) {
        return bookRepository.findAll(PageRequest.of(offset, pageSize));
    }

    public List<BookWithAuthorResponse> getBooksWithAuthorInfoPagination(int offset, int pageSize) {
        List<BookWithAuthorResponse> booksWithAuthors = new ArrayList<>();

        Page<Book> bookPage = bookRepository.findAll(PageRequest.of(offset, pageSize));

        for (Book book : bookPage.getContent()) {
            Author author = authorRepository.findAuthorById(book.getAuthorId());
            BookWithAuthorResponse fullBook = new BookWithAuthorResponse(book.getId(), book.getTitle(), book.getDescription(), author.getName(), author.getEmail());
            booksWithAuthors.add(fullBook);
        }

        return booksWithAuthors;
    }

}
