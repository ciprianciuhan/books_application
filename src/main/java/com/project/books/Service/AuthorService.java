package com.project.books.Service;

import com.project.books.Model.Author;
import com.project.books.Repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project.books.Constants.ExceptionConstants.AUTHOR_DOES_NOT_EXIST_EXCEPTION;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public void insertAuthor(Author author) {
        authorRepository.save(author);
    }

    public List<Author> getAuthors() {
       return authorRepository.findAll();
    }

    public Author findAuthorById(Integer authorId) {
        if (authorRepository.findAuthorById(authorId) == null){
            throw new RuntimeException(AUTHOR_DOES_NOT_EXIST_EXCEPTION);
        }
        return authorRepository.findAuthorById(authorId);
    }

    public void deleteAuthorById(Integer authorId) {
        if (authorRepository.findAuthorById(authorId) == null){
            throw new RuntimeException(AUTHOR_DOES_NOT_EXIST_EXCEPTION);
        }
        authorRepository.deleteAuthorById(authorId);
    }

    public void updateAuthorById(Integer id, String name, String email) {
        if (authorRepository.findAuthorById(id) == null){
            throw new RuntimeException(AUTHOR_DOES_NOT_EXIST_EXCEPTION);
        }
        authorRepository.updateAuthorById(id, name, email);
    }

    public Page<Author> getAuthorsPagination(int offset, int pageSize) {
        return authorRepository.findAll(PageRequest.of(offset, pageSize));
    }
}
