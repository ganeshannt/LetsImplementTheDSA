package com.practise.java.lambda.streams;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
class Author {
    private Integer id;
    private String name;
    private String email;
    private LocalDate dob;
    private List<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        return Objects.equals(id, author.id) && Objects.equals(name, author.name) && Objects.equals(email, author.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}

@AllArgsConstructor
@Getter
@Setter
class Book {
    private Integer id;
    private String title;
    private String isbn;
    private LocalDate publishedDate;
    private Author author;
}


public class StreamsObjProblem1 {


    /**
     * 1. Remove duplicates from a list of authors based on their email,name, id
     * 2. Sort authors by their name in descending order
     * 3. Find the author with a book published in the last 5 years or title containing the word "Ganeshan" and written more than 2 books
     */
    private static void authorProblems(List<Author> authors) {
        // 1. Remove duplicates based on email, name, and id
        List<Author> uniqueAuthors = authors.stream().distinct().sorted(Comparator.comparing(Author::getId))
                .toList();

        // 2. Sort authors by name in descending order
        List<Author> sortedAuthors = uniqueAuthors.stream()
                .sorted((a1, a2) -> a2.getName().compareTo(a1.getName()))
                .toList();

        // 3. Find authors with specific conditions
        List<Author> filteredAuthors = sortedAuthors.stream()
                .filter(author -> author.getBooks().stream()
                        .anyMatch(book -> book.getPublishedDate().isAfter(LocalDate.now().minusYears(5)) ||
                                book.getTitle().contains("Ganeshan")) &&
                        author.getBooks().size() > 2)
                .toList();

        // Print results
        filteredAuthors.forEach(e-> System.out.println(e.getName()));
    }


    /**
     * 1. Remove duplicates from a list of authors based on their email,name, id
     * 2. Sort authors by their name in descending order
     * 3. Find the author with a book published in the last 5 years or title containing the word "Ganeshan" and written more than 2 books
     */
    private static void authorProblems1(List<Author> authors) {
        // 1. Remove duplicates based on email, name, and id

        authors.stream()
                .distinct()
                .sorted(Comparator.comparing(Author::getName).reversed())
                .filter(e -> e.getBooks()
                        .stream()
                        .anyMatch(book -> book.getPublishedDate().isAfter(LocalDate.now().minusYears(5))) || (e.getBooks().size() > 2 && e.getBooks().stream().anyMatch(book -> book.getTitle().contains("Ganeshan"))))
                .forEach(e-> System.out.println(e.getName()));

    }


    public static void main(String[] args) {
        Author author1 = new Author(
                1,
                "Alice",
                "alice@example.com",
                LocalDate.of(1980, 5, 15),
                List.of(
                        new Book(101, "Intro to Java", "ISBN-001", LocalDate.of(2010, 1, 10), null),
                        new Book(102, "Advanced Java", "ISBN-002", LocalDate.of(2021, 3, 20), null),
                        new Book(103, "Ganeshan in Coding", "ISBN-003", LocalDate.of(2022, 7, 10), null)
                )
        );

        Author author2 = new Author(
                2,
                "Bob",
                "bob@example.com",
                LocalDate.of(1975, 8, 10),
                List.of(
                        new Book(201, "Spring Boot Basics", "ISBN-004", LocalDate.of(2019, 9, 12), null),
                        new Book(202, "Cloud with Java", "ISBN-005", LocalDate.of(2018, 2, 5), null)
                )
        );

        Author author3 = new Author(
                1,
                "Alice",
                "alice@example.com",
                LocalDate.of(1980, 5, 15),
                List.of( // Duplicate of author1
                        new Book(101, "Intro to Java", "ISBN-001", LocalDate.of(2010, 1, 10), null)
                )
        );

        Author author4 = new Author(
                3,
                "Charlie",
                "charlie@example.com",
                LocalDate.of(1990, 3, 25),
                List.of(
                        new Book(301, "Ganeshan's Algorithms", "ISBN-006", LocalDate.of(2020, 11, 1), null),
                        new Book(302, "DSA Mastery", "ISBN-007", LocalDate.of(2024, 1, 15), null),
                        new Book(303, "Java for Experts", "ISBN-008", LocalDate.of(2023, 6, 30), null)
                )
        );

        // Assign back author to each book to complete the object graph
        author1.getBooks().forEach(book -> book.setAuthor(author1));
        author2.getBooks().forEach(book -> book.setAuthor(author2));
        author3.getBooks().forEach(book -> book.setAuthor(author3));
        author4.getBooks().forEach(book -> book.setAuthor(author4));

        List<Author> authors = List.of(author1, author2, author3, author4);

        System.out.println("===== Output from authorProblems =====");
        authorProblems(authors);

        System.out.println("\n===== Output from authorProblems1 =====");
        authorProblems1(authors);
    }

}
