package com.example.bookapi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookapi.Class.Book;
import com.example.bookapi.Class.MockBookService;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private MockBookService mockBookService;

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @GetMapping("")
    public List<Book> listBooks() {
        return mockBookService.getList();
    }

    @PostMapping("")
    public void addBook(@RequestBody Book book){
        book.setId(mockBookService.getNextId());
        mockBookService.getList().add(book);
        mockBookService.setNextId(mockBookService.getNextId() + 1);
    }

    @RequestMapping("/{id}")
    public Book getBook(@PathVariable Long id){
        for (Book b : mockBookService.getList()){
            if (b.getId() == id){
                return b;
            }
        }
        return null;
    }

    @PutMapping("")
    public void putBook(@RequestBody Book book){
        List<Book> list = mockBookService.getList();
        for (Book b : list){
            if (b.getId() == book.getId()){
                list.set(list.indexOf(b), book);
            }
        }
    }
}
