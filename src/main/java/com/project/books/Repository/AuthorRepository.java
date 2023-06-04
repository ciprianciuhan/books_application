package com.project.books.Repository;

import com.project.books.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    void deleteAuthorById(Integer id);
    Author findAuthorById(Integer id);
    @Modifying
    @Query("UPDATE Author author SET author.name = :name, author.email = :email WHERE author.id = :id")
    void updateAuthorById(@Param("id") Integer id, @Param("name") String name, @Param("email") String email);
}
