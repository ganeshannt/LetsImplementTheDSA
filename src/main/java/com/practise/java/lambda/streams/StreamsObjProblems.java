package com.practise.java.lambda.streams;


import lombok.*;

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

@Data
class Book {
    private Integer id;
    private String title;
    private String isbn;
    private LocalDate publishedDate;
    private Author author;
}


public class StreamsObjProblems {


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
        filteredAuthors.forEach(System.out::println);
    }



    public static void main(String[] args) {



    }
}
