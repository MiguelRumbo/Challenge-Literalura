// Clase Author
package com.alura.literatura.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String firstName;
    private String lastName;
    
    // Cambiar de 'int' a 'Integer'
    private Integer birthYear;  // Ahora puede ser null
    private Integer deathYear;  // Ahora puede ser null
    
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Book> books;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        // Ahora puede manejar null correctamente
        return lastName + ", " + firstName + " (" + (birthYear != null ? birthYear : "?") + " - " + (deathYear != null ? deathYear : "?") + ")";
    }
}
