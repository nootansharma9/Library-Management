# 📚 Library Management System

This Java-based **Library Management System** simulates a real-world library environment with functionalities for both **Librarians** and **Members**. It supports adding/removing books and members, issuing/returning books, fine management, and tracking library inventory through an interactive menu-driven console.

---

## 🏗️ Project Structure

### 🔸 **Main Classes**

1. **Librarian**
2. **Member**
3. **Book**
4. **Main (Driver Program)**

---

## 🧑‍💼 Librarian Class

### **Attributes**
- `static List<Book> books` – All books in the library
- `static List<Member> members` – All registered members
- `static int latest_book_ID` – Auto-incremented unique ID for new books
- `static int latest_member_ID` – Auto-incremented unique ID for new members

> **Design Note:** Book/Member IDs are never reused, even if an entity is removed.

### **Methods**
- `addBook(title, author)`
- `removeBook(bookID)`  
  > Cannot remove if the book is issued or ID is invalid.
- `addMember(name, phoneNumber)`
- `removeMember(memberID)`  
  > Cannot remove if the member has issued books or pending fines.
- `listAllBooks()`  
  > Calls `toString()` of all books.
- `listAvailableBooks()`  
  > Lists books with `status = false` (available).
- `listAllMembers()`  
  > Calls `toString()` of all members.

---

## 👤 Member Class

### **Attributes**
- `String name`
- `String phoneNumber`
- `int age` *(redundant but included)*
- `int memberID`
- `List<Book> booksIssued` – Currently issued books
- `int fine` – Accumulated fines after return

### **Methods**
- `listMyBooks()`  
  > Calls `toString()` for each book in `booksIssued`.
- `issueBook(bookID, title)`  
  > Restrictions: due fine, >2 books issued, book already issued, mismatched details, invalid ID.
- `returnBook(bookID)`  
  > Restriction: cannot return if the book was not issued by this member.
- `payFine()`  
  > Resets `fine = 0`

> **Design Note:** Fine is only calculated at return time. There’s no tracking of overdue books before return.

---

## 📖 Book Class

### **Attributes**
- `int bookID`
- `String title`
- `String author`
- `boolean status` – `true`: issued, `false`: available
- `LocalDateTime issue_date` – Timestamp of issue

### **Methods**
- `calculateFine()`  
  > Called when a book is returned. Fine is added to the respective member’s `fine` attribute (non-destructive if already fined).

---

## 🖥️ Main Class (Interactive Console)

- Menu-driven CLI using **nested if-else** structures
- Users can **log in as Librarian or Member**
- Validation for:
  - Member credentials
  - Input ranges for menu choices
  - Restrictions on operations (e.g., issue limit, fine checks)

> ⚠️ **Note:** Input is not fully sanitized — invalid values outside allowed ranges may crash the program.

---

## ✅ Key Design Decisions

- Book/Member IDs are **never reused** even if entities are removed.
- Fines are only calculated when books are **returned** (not tracked live).
- Fine must be paid **manually** after return.
- Each **book copy** has a **unique ID**, even with the same title.

---

## 🔧 How to Run

1. Compile all `.java` files:
   ```bash
   javac *.java
