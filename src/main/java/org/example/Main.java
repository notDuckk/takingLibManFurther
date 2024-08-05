package org.example;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        User user = new User("Daylen", 21);
        library.registerUser(user);

        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 180, "Fiction"));
        library.addBook(new Book("1984", "George Orwell", 1949, 328, "Fiction"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", 1960, 281, "Fiction"));
        library.addBook(new Book("A Brief History of Time", "Stephen Hawking", 1988, 212, "Science"));


        System.out.println("--- most pages ----");
        System.out.println(library.findBookWithMostPages());
        System.out.println("--- more than 250 ---");
        System.out.println(library.findBooksWithMoreThanPages(250));
        System.out.println("--- by Category ---");
        System.out.println(library.findBooksByCategory("Fiction"));


        library.loanBook("1984", 21);


        double fees = library.calculateLateFees(21);
        System.out.println("Late Fees: $" + fees);


        library.printAllBookTitles();




    }
}