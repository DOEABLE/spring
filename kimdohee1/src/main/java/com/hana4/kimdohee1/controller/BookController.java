package com.hana4.kimdohee1.controller;

import com.hana4.kimdohee1.Lang;
import com.hana4.kimdohee1.dto.Book;
import com.hana4.kimdohee1.dto.LocalDTO;
import com.hana4.kimdohee1.service.BookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/book")//주소창에 /book라고 해야함.
public class BookController {
    private final String SessLocale =
            SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME;
    private final BookService service;

    public BookController(BookService service){
        this.service=service;
    }
    //기본 경로 리다이렉트
    @RequestMapping
    public String defaultRedirect() {
        return "redirect:/book/list";
    }

    //Locale
    @GetMapping("/list")
    public String list(Model model, HttpSession session) {
        List<Book> books = service.getList();
        model.addAttribute("books", books);
        model.addAttribute("Langs", Lang.values());
        model.addAttribute("currLang", session.getAttribute(SessLocale));
        return "book/list";
    }
//    @PostMapping("/changelang")
//    public String changeLang(@ModelAttribute LocalDTO dto, HttpSession session){
//        session.setAttribute(SessLocale, dto.getLocale());
//        return "redirect:/book/list";
//    }
@PostMapping("/changelang")
public String changeLocale(@RequestParam String locale, HttpSession session) {
    session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(locale));
    return "redirect:/book/list";
}
    // 도서 상세보기
    @GetMapping("/{bno}")
    public String getDetail (@PathVariable Integer bno, Model model){
        Book book = service.find(bno);
        model.addAttribute("book", book);
        return "book/read";
    }

    @GetMapping("/read")
    public String read(@RequestParam Integer bno, Model model) {
        Book book = service.find(bno);
        model.addAttribute("book", book);
        return "book/read";
    }

    @PostMapping("/borrow")
    public String borrow(@RequestParam Integer bno, @RequestParam String borrowerId) {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(7);

        boolean success = service.borrowBook(bno, borrowerId, startDate, endDate);
        if (success) {
            return "redirect:/book/result?borrowerId=" + borrowerId;
        } else {
            return "redirect:/book/read?bno=" + bno + "&error=true";
        }
    }

@GetMapping("/result")
public String result(@RequestParam String borrowerId, Model model) {
    List<Book> borrowedBooks = service.getBorrowResult(borrowerId);
    // 대출 결과가 없을 경우 처리
    if (borrowedBooks == null || borrowedBooks.isEmpty()) {
        model.addAttribute("message", "대출한 도서가 없습니다.");
        return "book/emptyResult"; // 별도의 결과 없음 페이지로 이동 가능
    }

    model.addAttribute("books", borrowedBooks);
    return "book/result";
}
    @PostMapping("/updateStatus")
    @ResponseBody
    public ResponseEntity<String> updateStatus(@RequestBody Map<String, Object> params) {
        Integer bno = (Integer) params.get("bno");
        service.updateBookStatus(bno);
        return ResponseEntity.ok("success");
    }
}

