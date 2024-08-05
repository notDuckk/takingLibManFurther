package org.example;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private Map<Integer, User> users;

    // Constructor
    public Library() {
        books = new ArrayList<>();
        users = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }
    public boolean removeBookByTitle(String title) {
        return books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }
    public List<Book> findBooksByPublicationYear(int year) {
        return books.stream()
                .filter(book -> book.getPublicationYear() == year)
                .collect(Collectors.toList());
    }


    public List<Book> findBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public Optional<Book> findBookWithMostPages() {
        return books.stream()
                .max(Comparator.comparingInt(Book::getPages));
    }
    public List<Book> findBooksWithMoreThanPages(int n) {
        return books.stream()
                .filter(book -> book.getPages() > n)
                .collect(Collectors.toList());
    }

    public void printAllBookTitles() {
        books.stream()
                .map(Book::getTitle)
                .sorted()
                .forEach(System.out::println);
    }

    public List<Book> findBooksByCategory(String category) {
        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public boolean loanBook(String title, int userId) {
        Optional<Book> bookOpt = books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title) && !book.isOnLoan())
                .findFirst();

        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            book.setOnLoan(true, LocalDate.now());
            User user = users.get(userId);
            if (user != null) {
                user.addBookOnLoan(book);
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(String title, int userId) {
        User user = users.get(userId);
        if (user != null) {
            Optional<Book> bookOpt = user.getBooksOnLoan().stream()
                    .filter(book -> book.getTitle().equalsIgnoreCase(title))
                    .findFirst();

            if (bookOpt.isPresent()) {
                Book book = bookOpt.get();
                book.setOnLoan(false, null);
                user.removeBookOnLoan(book);
                return true;
            }
        }
        return false;
    }

    public void registerUser(User user) {
        users.put(user.getLibraryCardNumber(), user);
    }

    public double calculateLateFees(int userId) {
        User user = users.get(userId);
        if (user == null) {
            return 0.0;
        }
        return user.getBooksOnLoan().stream()
                .mapToDouble(book -> {
                    LocalDate dueDate = book.getLoanDate().plusWeeks(2);
                    long daysLate = Math.max(0, LocalDate.now().toEpochDay() - dueDate.toEpochDay());
                    return daysLate * 0.50;
                })
                .sum();
    }
}
