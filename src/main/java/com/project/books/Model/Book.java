package com.project.books.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue
    private Integer id;
    @NotNull (message = "TITLE SHOULD NOT BE NULL")
    @NotEmpty (message = "TITLE SHOULD NOT BE EMPTY")
    private String title;
    @NotNull (message = "DESCRIPTION SHOULD NOT BE NULL")
    @NotEmpty (message = "DESCRIPTION SHOULD NOT BE EMPTY")
    private String description;
    private Integer authorId;

}
