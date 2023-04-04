package libraryApp.controllers;

import jakarta.validation.Valid;
import libraryApp.models.Book;
import libraryApp.models.Person;
import libraryApp.services.BookService;
import libraryApp.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.peopleService = peopleService;
        this.bookService = bookService;
    }


    @GetMapping
    public String index(Model model,
                        @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "itemPerPage", required = false) Integer itemPerPage,
                        @RequestParam(value = "sorted", required = false) boolean sortByYear) {
        if (page == null || itemPerPage == null) {
            model.addAttribute("books", bookService.getAllBooks(sortByYear));
        } else {
            model.addAttribute("books", bookService.getBookWithPagination(page, itemPerPage, sortByYear));
        }

        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model,
                       @ModelAttribute("person1") Person person) {
        Book book = bookService.getOneBook(id);
        model.addAttribute("book", book);
        Person owner = book.getOwningPerson();
        if (owner != null) {
            model.addAttribute("person", owner);
        } else {
            model.addAttribute("people", peopleService.getAllPeople());
        }
        return "book/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/new";
        }
        bookService.save(book);
        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.getOneBook(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/edit";
        }
        bookService.update(id, book);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id,
                         @ModelAttribute("person1") Person person) {
        bookService.assign(id, person);
        return "redirect:/book/" + id;

    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookService.release(id);
        return "redirect:/book/" + id;
    }

    @GetMapping("/search")
    public String searchPage() {
        return "book/search";
    }

    @PostMapping("/search")
    public String makeSearch(@RequestParam(value = "search", defaultValue = "null") String query, Model model) {
        if (query.equals("null")) {
            model.addAttribute("null", null);
            return "book/search";
        }
        List<Book> books = bookService.searchByTitle(query);
        model.addAttribute("page", books);
        return "book/search";
    }
}
