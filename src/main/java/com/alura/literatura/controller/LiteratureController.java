package com.alura.literatura.controller;

import com.alura.literatura.model.Author;
import com.alura.literatura.model.Book;
import com.alura.literatura.service.GutendexService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class LiteratureController {
    private final GutendexService gutendexService;

    public LiteratureController(GutendexService gutendexService) {
        this.gutendexService = gutendexService;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int option = -1;

        do {
            System.out.println("\n=== Menú ===");
            System.out.println("1- Buscar libro por título");
            System.out.println("2- Listar libros registrados");
            System.out.println("3- Listar autores registrados");
            System.out.println("4- Listar autores vivos en un determinado año");
            System.out.println("5- Listar libros por idioma");
            System.out.println("0- Salir");
            System.out.print("Elija la opción a través de su número: ");
            try {
                option = Integer.parseInt(scanner.nextLine()); 
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido.");
                continue;
            }

            switch (option) {
                case 1:
                    System.out.print("Ingrese el nombre del libro que desea buscar: ");
                    String searchTerm = scanner.nextLine();
                    List<Book> books = gutendexService.searchBooks(searchTerm);
                    if (books.isEmpty()) {
                        System.out.println("No se encontraron libros.");
                    } else {
                        System.out.println("\nLibros encontrados:");
                        books.forEach(book -> {
                            // Acceder al autor del libro (ya que es @ManyToOne, solo hay un autor)
                            String authors = book.getAuthor() != null ? book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName() : "Desconocido";
                            System.out.println("- " + book.getTitle() + " | Autor: " + authors);
                        });
                    }
                    break;

                case 2:
                    System.out.println("\nLibros registrados en la base de datos:");
                    List<Book> allBooks = gutendexService.getAllBooks();
                    if (allBooks.isEmpty()) {
                        System.out.println("No hay libros registrados.");
                    } else {
                        allBooks.forEach(book -> {
                            // Acceder al autor del libro
                            String authors = book.getAuthor() != null ? book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName() : "Desconocido";
                            System.out.println("- " + book.getTitle() + " | Autor: " + authors);
                        });
                    }
                    break;

                case 3:
                    System.out.println("\nAutores registrados:");
                    List<Author> allAuthors = gutendexService.getAllAuthors();
                    if (allAuthors.isEmpty()) {
                        System.out.println("No hay autores registrados.");
                    } else {
                        for (Author author : allAuthors) {
                            // Acceder a la información del autor
                            System.out.println("- " + author.getLastName() + ", " + author.getFirstName() +
                                    " | Nacido: " + author.getBirthYear() + " | Fallecido: " +
                                    (author.getDeathYear() != null ? author.getDeathYear() : "Vivo"));
                                    
                            // Mostrar los libros del autor
                            List<Book> booksByAuthor = author.getBooks();
                            if (booksByAuthor != null && !booksByAuthor.isEmpty()) {
                                System.out.println("  Libros:");
                                booksByAuthor.forEach(book -> System.out.println("   - " + book.getTitle()));
                            } else {
                                System.out.println("  No tiene libros registrados.");
                            }
                        }
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el año para ver autores vivos: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    List<Author> aliveAuthors = gutendexService.getAuthorsAliveInYear(year);
                    if (aliveAuthors.isEmpty()) {
                        System.out.println("No hay autores vivos en ese año.");
                    } else {
                        aliveAuthors.forEach(author -> System.out.println("- " + author.getLastName() + ", " + author.getFirstName() +
                                " | Nacido: " + author.getBirthYear() + " | Fallecido: " +
                                (author.getDeathYear() != null ? author.getDeathYear() : "Vivo")));
                    }
                    break;

                case 5:
                    System.out.print("Ingrese el idioma (ej. 'en', 'es'): ");
                    String language = scanner.nextLine();
                    List<String> booksByLanguage = gutendexService.getBooksByLanguage(language);
                    if (booksByLanguage.isEmpty()) {
                        System.out.println("No se encontraron libros en ese idioma.");
                    } else {
                        System.out.println("\nLibros en idioma " + language + ":");
                        booksByLanguage.forEach(System.out::println);
                    }
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida. Por favor, elija un número entre 0 y 5.");
                    break;
            }
        } while (option != 0);

        scanner.close();
    }
}
