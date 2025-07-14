package org.example;
import java.time.LocalTime;

public class Book {
    private final int bookID;
    private final String title;
    private final String author;
    private boolean status;
    private LocalTime issue_date;

    //constructor
    protected Book(int bookID, String title, String author) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.status = false;
    }

    //getters
    protected int bookID() {
        return bookID;
    }
    protected String getTitle() {
        return title;
    }
    protected boolean getStatus(){
        return !status;
    }

    //setters
    protected void setStatus(boolean status) {
        this.status = status;
    }

    protected void setIssueDate(LocalTime issueDate)
    {
        this.issue_date = issueDate;
    }

    //toString method
    public String toString()
    {
        return ("Book ID - " + this.bookID + "\nTitle - " + this.title + "\nAuthor - " + this.author);
    }

    //methods
    protected int calculateFine()
    {
        int fine = (LocalTime.now().toSecondOfDay() - issue_date.toSecondOfDay());

        if (fine > 10)
        {
            return (fine - 10)*3;
        }

        else
        {
            return 0;
        }
    }
}

