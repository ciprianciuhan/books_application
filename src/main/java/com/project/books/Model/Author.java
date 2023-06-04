package com.project.books.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull(message = "NAME SHOULD NOT BE NULL")
    @NotEmpty(message = "NAME SHOULD NOT BE EMPTY")
    private String name;
    @Email(message = "EMAIL SHOULD BE VALID")
    @NotNull(message = "EMAIL SHOULD NOT BE NULL")
    @NotEmpty(message = "EMAIL SHOULD NOT BE EMPTY")
    private String email;

}
