package com.project.books.Controller;

import com.project.books.Model.Book;
import com.project.books.Service.BookService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.project.books.Constants.LoggingConstants.*;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @PostMapping("/insertBook")
    public Optional<ResponseEntity<String>> insertBook(@RequestBody Book book){
        try {
            LOG.info(INSERTING_BOOK + book.toString());
            bookService.insertBook(book);
            return Optional.of(ResponseEntity.status(HttpStatus.OK).body(BOOK_INSERTED));
        } catch (Exception e){
            e.printStackTrace();
            LOG.error(ERROR + e.getMessage());
            return Optional.of(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INSERTION_ERROR + e.getMessage()));
        }
    }

    @GetMapping("/getBooks")
    public Optional<ResponseEntity> getAllBooks(){
        try {
            LOG.info(GETTING_BOOKS);
            return Optional.of(ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks()));
        } catch (Exception e){
            LOG.error(ERROR + e.getMessage());
            return Optional.of(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CANNOT_GET_BOOKS));
        }
    }

    @GetMapping("/getBooksPagination/{offset}/{pageSize}")
    public Optional<ResponseEntity> getBooksPagination(@PathVariable int offset, @PathVariable int pageSize){
        try {
            LOG.info(GETTING_BOOKS);
            return Optional.of(ResponseEntity.status(HttpStatus.OK).body(bookService.getBooksPagination(offset, pageSize)));
        } catch (Exception e){
            LOG.error(ERROR + e.getMessage());
            return Optional.of(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CANNOT_GET_BOOKS));
        }
    }

    @GetMapping("/getAllBooksWithAuthorInfo")
    public Optional<ResponseEntity> getAllBooksWithAuthorInfo(){
        try {
            LOG.info(GETTING_BOOKS_WITH_AUTHOR);
            return Optional.of(ResponseEntity.status(HttpStatus.OK).body(bookService.getBooksWithAuthorInfo()));
        } catch (Exception e){
            LOG.error(ERROR + e.getMessage());
            return Optional.of(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CANNOT_GET_BOOKS));
        }
    }

    @GetMapping("/getAllBooksWithAuthorInfoPagination/{offset}/{pageSize}")
    public Optional<ResponseEntity> getAllBooksWithAuthorInfoPagination(@PathVariable int offset, @PathVariable int pageSize){
        try {
            LOG.info(GETTING_BOOKS_WITH_AUTHOR);
            return Optional.of(ResponseEntity.status(HttpStatus.OK).body(bookService.getBooksWithAuthorInfoPagination(offset, pageSize)));
        } catch (Exception e){
            LOG.error(ERROR + e.getMessage());
            return Optional.of(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CANNOT_GET_BOOKS));
        }
    }

    @GetMapping("/getBooksByAuthor")
    public Optional<ResponseEntity> getBooksByAuthor(@RequestParam Integer authorId){
        try {
            LOG.info(GETTING_BOOKS_BY_AUTHOR + authorId);
            return Optional.of(ResponseEntity.status(HttpStatus.OK).body(bookService.getBooksByAuthorId(authorId)));
        } catch (Exception e){
            LOG.error(ERROR + e.getMessage());
            return Optional.of(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CANNOT_GET_BOOKS));
        }
    }

    @GetMapping("/getBookById")
    public Optional<ResponseEntity> getBookById(@RequestParam Integer id){
        try {
            LOG.info(GETTING_BOOK_WITH_ID + id);
            LOG.info(BOOK_INFO + bookService.getBookById(id).toString());
            return Optional.of(ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(id)));
        } catch (Exception e){
            LOG.error(ERROR + e.getMessage());
            return Optional.of(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CANNOT_GET_BOOKS));
        }
    }

    @DeleteMapping("/deleteBook")
    @Transactional
    public Optional<ResponseEntity> deteteBook(@RequestParam Integer id){
        try{
            LOG.info(DELETING_BOOK + id);
            bookService.deleteBookById(id);
            return Optional.of(ResponseEntity.status(HttpStatus.OK).body(BOOK_DELETED));
        } catch (Exception e){
            LOG.error(ERROR + e.getMessage());
            return Optional.of(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CANNOT_DELETE_BOOK));
        }
    }

    @PutMapping("/updateBook")
    @Transactional
    public Optional<ResponseEntity> updateBook(@RequestBody Book newBook, @RequestParam Integer id){
        try {
            LOG.info(UPDATING_BOOK + id);
            bookService.updateBookById(id, newBook.getTitle(), newBook.getDescription(), newBook.getAuthorId());
            return Optional.of(ResponseEntity.status(HttpStatus.OK).body(BOOK_UPDATED));
        } catch (Exception e){
            LOG.error(ERROR + e.getMessage());
            return Optional.of(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CANNOT_UPDATE_BOOK));
        }
    }
}
