package org.example;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Librarian {
    protected static List<Book> books;
    protected static List<Member> members;
    private static int latest_book_ID;
    private static int latest_member_ID;

    Scanner scanner = new Scanner(System.in);

    //constructor
    public Librarian() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        latest_book_ID = 0;
        latest_member_ID = 0;
    }

    //methods
    protected void addBook() {

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Copies: ");
        int copies;

        try {
            copies = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer.");
            return;
        }

        for (int i = 0; i < copies; i++) {
            latest_book_ID += 1;
            Book added_book = new Book(latest_book_ID, title, author);
            books.add(added_book);
        }

        System.out.print("""
                ---------------------------------
                Book Added Successfully!
                ---------------------------------
                """);
    }

    protected void removeBook() {

        System.out.print("BookID: ");
        int bookID;

        try {
            bookID = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer.");
            return;
        }

        Book removed_book = null;

        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.bookID() == bookID) {
                removed_book = book;
                if (book.getStatus()) {
                    iterator.remove(); // Safe removal using the iterator
                    System.out.println("---------------------------------");
                    System.out.println("Book Removed Successfully!");
                    System.out.println("---------------------------------");
                } else {
                    System.out.print("""
                ---------------------------------
                Book is currently issued by someone and cannot be removed.
                ---------------------------------
                """);
                }
            }
        }


        if (removed_book == null) {

            System.out.print("""
                    ---------------------------------
                    Book with the given BookID does not exist.
                    ---------------------------------
                    """);
        }
    }

    protected void addMember() {

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Age: ");
        int age;

        try {
            age = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException numberFormatException) {
            System.out.println("Invalid input. Please enter an integer.");
            return;
        }

        System.out.print("Phone Number: ");
        long phoneNumber;

        try {
            phoneNumber = Long.parseLong(scanner.nextLine());
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer.");
            return;
        }

        latest_member_ID += 1;
        Member added_member = new Member(name, phoneNumber, age, latest_member_ID);

        System.out.println("---------------------------------");
        System.out.println("Member Added Successfully with MemberID " + latest_member_ID);
        System.out.println("---------------------------------");

        members.add(added_member);
    }

    protected void removeMember() {

        System.out.print("MemberID: ");
        int memberID;

        try {
            memberID = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer.");
            return;
        }

        Member removed_member = null;

        Iterator<Member> iterator = members.iterator();
        while (iterator.hasNext()) {
            Member member = iterator.next();
            if (member.memberID() == memberID) {
                removed_member = member;

                if (member.getFine() != 0) {
                    System.out.print("""
                ---------------------------------
                The member has to clear due fine before being removed.
                ---------------------------------
                """);
                } else if (member.getBooksIssued().size() != 0) {
                    System.out.print("""
                ---------------------------------
                The member has to return all issued books before being removed.
                ---------------------------------
                """);
                } else {
                    iterator.remove(); // Safe removal using the iterator
                    System.out.println("---------------------------------");
                    System.out.println("Member Removed Successfully!");
                    System.out.println("---------------------------------");
                }
            }
        }

        if (removed_member == null) {
            System.out.print("""
                    ---------------------------------
                    Member with the given MemberID does not exist.
                    ---------------------------------
                    """);
        }

    }

    protected void listAllBooks() {

        System.out.println("---------------------------------");
        for (Book book : books) {
            System.out.println(book.toString() + "\n");
        }
        System.out.println("---------------------------------");
    }

    protected void listAvailableBooks() {

        System.out.println("---------------------------------");
        for (Book book : books) {
            if (book.getStatus()) {
                System.out.println(book + "\n");
            }
        }
        System.out.println("---------------------------------");
    }

    protected void listAllMembers() {

        System.out.println("---------------------------------");
        for (Member member : members) {
            member.toString();
        }
        System.out.println("---------------------------------");
    }
}