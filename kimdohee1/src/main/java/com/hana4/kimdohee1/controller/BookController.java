package com.hana4.kimdohee1.controller;

import com.hana4.kimdohee1.dto.Book;
import com.hana4.kimdohee1.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/books")//주소창에 /books라고 해야함.
public class BookController {
    private final BookService service;

    public BookController(BookService service){
        this.service=service;
    }
    @RequestMapping("/")
    public String mainPage(Model model){
        List<Book> books = service.getList();
        model.addAttribute("books", books);

        return "book/list";
    }

    @GetMapping("/{bno}")// 도서 상세보기
    public String getDetail (@PathVariable Integer bno, @RequestParam(required = false) Model model){
        return "book/read";
    }

}
