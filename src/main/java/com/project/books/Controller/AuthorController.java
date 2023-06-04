package com.project.books.Controller;

import com.project.books.Model.Author;
import com.project.books.Service.AuthorService;
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
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    private static final Logger LOG = LoggerFactory.getLogger(AuthorController.class);

    @PostMapping("/insertAuthor")
    public Optional<ResponseEntity<String>> insertAuthor(@RequestBody Author author){
        try {
            LOG.info(INSERTING_AUTHOR + author.toString());
            authorService.insertAuthor(author);
            LOG.info(AUTHOR_INSERTED);
            return Optional.of(ResponseEntity.status(HttpStatus.OK).body(AUTHOR_INSERTED));
        } catch (Exception e){
            e.printStackTrace();
            LOG.error(ERROR + e.getMessage());
            return Optional.of(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(INSERTION_ERROR + e.getMessage()));
        }
    }

    @GetMapping("/getAuthors")
    public Optional<ResponseEntity> getAllAuthors(){
        try {
            LOG.info(GETTING_AUTHORS);
            return Optional.of(ResponseEntity.status(HttpStatus.OK).body(authorService.getAuthors()));
        } catch (Exception e){
            LOG.error(ERROR + e.getMessage());
            return Optional.of(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CANNOT_GET_AUTHORS));
        }
    }

    @GetMapping("/getAuthorsPagination/{offset}/{pageSize}")
    public Optional<ResponseEntity> getAuthorsPagination(@PathVariable int offset, @PathVariable int pageSize){
        try {
            LOG.info(GETTING_AUTHORS);
            return Optional.of(ResponseEntity.status(HttpStatus.OK).body(authorService.getAuthorsPagination(offset, pageSize)));
        } catch (Exception e){
            LOG.error(ERROR + e.getMessage());
            return Optional.of(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CANNOT_GET_AUTHORS));
        }
    }

    @GetMapping("/getAuthorById")
    public Optional<ResponseEntity> getAuthorById(@RequestParam Integer id){
        try {
            LOG.info(GETTING_AUTHOR_WITH_ID + id);
            LOG.info(AUTHOR_INFO + authorService.findAuthorById(id).toString());
            return Optional.of(ResponseEntity.status(HttpStatus.OK).body(authorService.findAuthorById(id)));
        } catch (Exception e){
            LOG.error(ERROR + e.getMessage());
            return Optional.of(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CANNOT_GET_AUTHOR));
        }
    }

    @DeleteMapping("/deleteAuthor")
    @Transactional
    public Optional<ResponseEntity> deteteAuthor(@RequestParam Integer authorId){
        try{
            LOG.info(DELETING_AUTHOR_WITH_ID + authorId);
            authorService.deleteAuthorById(authorId);
            return Optional.of(ResponseEntity.status(HttpStatus.OK).body(AUTHOR_DELETED));
        } catch (Exception e){
            LOG.error(ERROR + e.getMessage());
            return Optional.of(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CANNOT_DELETE_AUTHOR));
        }
    }

    @PutMapping("/updateAuthor")
    @Transactional
    public Optional<ResponseEntity> updateAuthor(@RequestBody Author newAuthor, @RequestParam Integer authorId){
        try {
            LOG.info(UPDATING_AUTHOR_WITH_ID + authorId);
            authorService.updateAuthorById(authorId, newAuthor.getName(), newAuthor.getEmail());
            return Optional.of(ResponseEntity.status(HttpStatus.OK).body(AUTHOR_UPDATED));
        } catch (Exception e){
            LOG.error(ERROR + e.getMessage());
            return Optional.of(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CANNOT_UPDATE_AUTHOR));
        }
    }
}
