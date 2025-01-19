package com.alura.literatura.service;

import com.alura.literatura.model.Author;
import com.alura.literatura.model.Book;
import com.alura.literatura.model.SearchHistory;
import com.alura.literatura.repository.AuthorRepository;
import com.alura.literatura.repository.BookRepository;
import com.alura.literatura.repository.SearchHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GutendexService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final SearchHistoryRepository searchHistoryRepository;

    public GutendexService(BookRepository bookRepository, AuthorRepository authorRepository,
            SearchHistoryRepository searchHistoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.searchHistoryRepository = searchHistoryRepository;
    }

    public List<Book> searchBooks(String searchTerm) {
        // Registrar la búsqueda en la base de datos
        SearchHistory searchHistory = new SearchHistory();
        searchHistory.setSearchTerm(searchTerm);
        searchHistoryRepository.save(searchHistory); // Guardar la búsqueda

        String url = "https://gutendex.com/books?search=" + searchTerm;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        if (response == null) {
            throw new RuntimeException("La respuesta de la API es nula");
        }

        JSONObject jsonResponse = new JSONObject(response);
        JSONArray results = jsonResponse.getJSONArray("results");

        List<Book> books = new ArrayList<>();
        for (int i = 0; i < results.length(); i++) {
            JSONObject bookJson = results.getJSONObject(i);
            String title = bookJson.getString("title");

            if (bookRepository.existsByTitle(title)) {
                continue; // Si el libro ya está registrado, lo omite
            }

            // Obtener autores (solo uno, ya que la relación es ManyToOne)
            JSONArray authorsArray = bookJson.getJSONArray("authors");
            JSONObject authorJson = authorsArray.getJSONObject(0); // Solo el primer autor
            String[] nameParts = authorJson.getString("name").split(", ");
            String lastName = nameParts.length > 1 ? nameParts[0] : "";
            String firstName = nameParts.length > 1 ? nameParts[1] : nameParts[0];

            int birthYear = authorJson.optInt("birth_year", 0);
            int deathYear = authorJson.optInt("death_year", 0);

            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);
            author.setBirthYear(birthYear);
            author.setDeathYear(deathYear);

            author = authorRepository.save(author); // Guarda al autor si no existe

            // Crear el libro y establecer autor
            Book book = new Book();
            book.setTitle(title);
            book.setLanguages(String.join(", ", bookJson.getJSONArray("languages")
                    .toList().stream().map(Object::toString).collect(Collectors.toList())));
            book.setDownloadCount(bookJson.getInt("download_count"));
            book.setAuthor(author);

            books.add(bookRepository.save(book)); // Guardar libro
        }

        return books;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> getAuthorsAliveInYear(int year) {
        List<Author> allAuthors = authorRepository.findAll();
        List<Author> aliveAuthors = new ArrayList<>();

        for (Author author : allAuthors) {
            if (author.getDeathYear() == null || author.getDeathYear() > year) {
                aliveAuthors.add(author);
            }
        }

        return aliveAuthors;
    }

    public List<String> getBooksByLanguage(String language) {
        List<Book> books = bookRepository.findAll();
        List<String> booksInLanguage = new ArrayList<>();
        for (Book book : books) {
            if (book.getLanguages().contains(language)) {
                booksInLanguage.add(book.getTitle());
            }
        }
        return booksInLanguage;
    }
}
