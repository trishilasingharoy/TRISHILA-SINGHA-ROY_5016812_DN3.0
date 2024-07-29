import java.util.*;
 class Book {
    int bookId;
    String title;
    String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }


    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author;
    }
}


 class BookSearch {

    // Linear search method to find a book by title
    public static Book linearSearch(Book[] books, String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Binary search method to find a book by title (assuming the list is sorted)
    public static Book binarySearch(Book[] books, String title) {
        int left = 0;
        int right = books.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = books[mid].title.compareToIgnoreCase(title);

            if (comparison == 0) {
                return books[mid];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Book[] books = {
                new Book(1, "Catch-22", "Joseph Heller"),
                new Book(2, "Charlotte Bronte", "Jane Eyre"),
                new Book(3, "To Kill a Mockingbird", "Harper Lee"),
                new Book(4, "Lolita", "Vladimir Nabokov"),
                new Book(5, "Beloved", "Toni Morrisson")
        };

        // Sort the array by title for binary search
        Arrays.sort(books, Comparator.comparing(book -> book.title));

        // Linear search
        System.out.println("Linear Search:");
        String searchTitle = "Catch-22";
        Book foundBook = linearSearch(books, searchTitle);
        if (foundBook != null) {
            System.out.println("Found: " + foundBook);
        } else {
            System.out.println("Book not found.");
        }

        // Binary search
        System.out.println("\nBinary Search:");
        searchTitle = "Lolita";
        foundBook = binarySearch(books, searchTitle);
        if (foundBook != null) {
            System.out.println("Found: " + foundBook);
        } else {
            System.out.println("Book not found.");
        }
    }
}
