package com.project.books.Repository;

import com.project.books.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findBooksByAuthorId(Integer authorId);
    Book findBookById(Integer id);
    void deleteBookById(Integer id);

    @Modifying
    @Query("UPDATE Book book SET book.title = :title, book.description = :description, book.authorId = :authorId WHERE book.id = :id")
    void updateBookById(@Param("id") Integer id, @Param("title") String title, @Param("description") String description, @Param("authorId") Integer authorId);
}
