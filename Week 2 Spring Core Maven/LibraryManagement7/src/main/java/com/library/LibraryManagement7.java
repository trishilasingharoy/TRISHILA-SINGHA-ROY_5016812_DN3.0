package com.library;


import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryManagement7 {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = context.getBean("bookService",BookService.class);
        if (bookService.getBookRepository() != null) {
            System.out.println("BookRepository has been successfully injected.");
        } else {
            System.out.println("BookRepository injection failed.");
        }
        bookService.listBooks();
    }
}