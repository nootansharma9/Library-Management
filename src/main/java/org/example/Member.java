package org.example;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class Member {
    private final String name;
    private final long phoneNumber;
    private int age;
    private final int memberID;
    private ArrayList<Book> booksIssued;
    private int fine;

    Scanner scanner = new Scanner(System.in);

    //constructor
    protected Member(String name, long phoneNumber, int age, int memberID) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.memberID = memberID;
        this.booksIssued = new ArrayList<>();
        this.fine = 0;
    }

    //getters
    protected long getPhoneNumber() {
        return phoneNumber;
    }

    protected String getName() {
        return name;
    }

    protected ArrayList<Book> getBooksIssued() {
        return booksIssued;
    }

    protected int memberID() {
        return memberID;
    }

    protected int getFine()
    {
        return this.fine;
    }

    //toString method
    public String toString()
    {
        System.out.println("Name - " + name);
        System.out.println("Fine - Rs." + fine);
        System.out.println("Books Issued: ");
        for (Book book : booksIssued) {
            System.out.println(book);
        }
        System.out.println("\n");
        return null;
    }

    //methods
    protected void listMyBooks()
    {
        System.out.println("---------------------------------");
        for (Book book : booksIssued) {
            System.out.println(book + "\n");
        }
        System.out.println("---------------------------------");
    }

    protected void issueBook()
    {
        if (this.fine != 0)
        {
            System.out.print("""
                                ---------------------------------
                                Please pay your due fine.
                                ---------------------------------
                                """);
        }

        else if (booksIssued.size() >= 2)
        {
            System.out.print("""
                                ---------------------------------
                                You can issue a maximum of 2 books at a time.
                                ---------------------------------
                                """);
        }

        else {

            System.out.print("Book Name: ");
            String book_name = scanner.nextLine();

            System.out.print("BookID: ");
            int bookID;

            try {
                bookID = Integer.parseInt(scanner.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
                return;
            }

            Book issued_book = null;

            for (Book book : Librarian.books) {
                if (book.bookID() == bookID) {

                    issued_book = book;

                    if (!Objects.equals(book.getTitle(), book_name))
                    {
                        System.out.print("""
                                ---------------------------------
                                Book Name and BookID don't match.
                                ---------------------------------
                                """);
                    }

                    else if (book.getStatus()) {

                        booksIssued.add(book);
                        book.setStatus(true);
                        book.setIssueDate(LocalTime.now());

                        System.out.print("""
                                ---------------------------------
                                Book Issued Successfully!
                                ---------------------------------
                                """);
                    }

                    else {
                        System.out.print("""
                                ---------------------------------
                                Book is issued by someone else at the moment.
                                ---------------------------------
                                """);
                    }
                }
            }

            if (issued_book == null)
            {
                System.out.print("""
                                ---------------------------------
                                Book with the given bookID does not exist.
                                ---------------------------------
                                """);
            }
        }

    }

    protected void returnBook()
    {
        System.out.print("BookID: ");
        int bookID;

        try {
            bookID = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer.");
            return;
        }

        Book returned_book = null;

        Iterator<Book> iterator = this.booksIssued.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.bookID() == bookID) {
                returned_book = book;
                iterator.remove(); // Safe removal using the iterator
                book.setStatus(false);
                int curr_fine = book.calculateFine();
                this.fine += curr_fine;

                System.out.println("---------------------------------");
                System.out.println("Book returned successfully.");

                if (curr_fine != 0) {
                    System.out.println("You have a fine of Rs." + curr_fine + " for a delay of " + (curr_fine/3) + " days.");
                }

                System.out.println("---------------------------------");
            }
        }


        if (returned_book == null)
        {
            System.out.print("""
                                ---------------------------------
                                You don't have an issued book with the given bookID.
                                ---------------------------------
                                """);
        }

    }

    protected void payFine()
    {
        System.out.println("---------------------------------");
        System.out.println("Fine of Rs." + this.fine + " has been paid successfully.");
        System.out.println("---------------------------------");
        this.fine = 0;
    }
}
