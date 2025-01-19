package com.alura.literatura.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SearchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String searchTerm;
    
    @Column(name = "search_date")
    private LocalDateTime searchDate = LocalDateTime.now(); // Se asigna la fecha actual por defecto

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public LocalDateTime getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(LocalDateTime searchDate) {
        this.searchDate = searchDate;
    }
}
