package org.example;
import java.util.Objects;
import java.util.Scanner;

// all the redundant getters and setters in the classes are for future expandability of the code

public class Main {
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        Librarian librarian = new Librarian();

        System.out.println( "\nLibrary Portal Initialized….\n" );

        int choice;

        while(true)
        {
            System.out.print("""
                    ---------------------------------
                    1. Enter as a librarian
                    2. Enter as a member
                    3. Exit
                    ---------------------------------
                    """);

            try {
                choice = Integer.parseInt(scanner.nextLine());
            }

            catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
                continue;
            }

            if (choice == 1)
            {
                while(true)
                {

                    System.out.print("""
                            ---------------------------------
                            1. Register a member
                            2. Remove a member
                            3. Add a book
                            4. Remove a book
                            5. View all members along with their books and fines to be paid
                            6. View all books
                            7. Back
                            ---------------------------------
                            """);

                    try {
                        choice = Integer.parseInt(scanner.nextLine());
                    }

                    catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter an integer.");
                        continue;
                    }

                    if (choice == 1) {
                        librarian.addMember();
                    }

                    else if (choice == 2) {
                        librarian.removeMember();
                    }

                    else if (choice == 3) {
                        librarian.addBook();
                    }

                    else if (choice == 4) {
                        librarian.removeBook();
                    }

                    else if (choice == 5) {
                        librarian.listAllMembers();
                    }

                    else if (choice == 6) {
                        librarian.listAllBooks();
                    }

                    else if (choice == 7) {
                        break;
                    }

                    else
                    {
                        System.out.println("Invalid input. Please enter an integer from 1-7.");
                    }

                }
            }

            else if (choice == 2)
            {
                Member curr_member = null;

                System.out.print("Name: ");
                String name = scanner.nextLine();

                System.out.print("Phone Number: ");
                long phoneNumber;

                try {
                    phoneNumber = Long.parseLong(scanner.nextLine());
                }
                catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                    continue;
                }

                System.out.print("MemberID: ");
                int memberID;

                try {
                    memberID = Integer.parseInt(scanner.nextLine());
                }

                catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                    continue;
                }

                for (Member member : Librarian.members) {
                    if (member.memberID() == memberID)
                    {
                        curr_member = member;
                    }
                }

                if (curr_member == null)
                {
                    System.out.println("---------------------------------");
                    System.out.println("Member with name " + name + " and phone number " + phoneNumber + " does not exist.");
                    System.out.println("---------------------------------");
                    continue;
                }

                if ((curr_member.getPhoneNumber() == phoneNumber) && (Objects.equals(curr_member.getName(), name)))
                {
                    System.out.println("---------------------------------");
                    System.out.println("Welcome, " + name + "! " + "MemberID: " + curr_member.memberID());
                    System.out.println("---------------------------------");
                }

                else
                {
                    System.out.print("""
                            ---------------------------------
                            Incorrect Credentials!!
                            ---------------------------------
                            """);
                    continue;
                }

                while(true)
                {

                    System.out.print("""
                            ---------------------------------
                            1. List Available Books
                            2. List My Books
                            3. Issue book
                            4. Return book
                            5. Pay Fine
                            6. Back
                            ---------------------------------
                            """);

                    try {
                        choice = Integer.parseInt(scanner.nextLine());
                    }

                    catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter an integer.");
                        continue;
                    }

                    if (choice == 1) {
                        librarian.listAvailableBooks();
                    }

                    else if (choice == 2) {
                        curr_member.listMyBooks();
                    }

                    else if (choice == 3) {
                        curr_member.issueBook();
                    }

                    else if (choice == 4) {
                        curr_member.returnBook();
                    }

                    else if (choice == 5) {
                        curr_member.payFine();
                    }

                    else if (choice == 6) {
                        break;
                    }

                    else
                    {
                        System.out.println("Invalid input. Please enter an integer from 1-6.");
                    }
                }
            }

            else if (choice == 3)
            {
                System.out.print("""
                    ---------------------------------
                    Thanks for visiting!
                    ---------------------------------
                    """);
                break;
            }

            else
            {
                System.out.println("Invalid input. Please enter an integer from 1-3.");
            }

        }

        scanner.close();
    }
}
