package dev.marinhomich.literaula.controller;

import dev.marinhomich.literaula.entry.EntryPoint;
import dev.marinhomich.literaula.model.Book;
import dev.marinhomich.literaula.repository.BookRepository;
import dev.marinhomich.literaula.service.ApiService;
import dev.marinhomich.literaula.service.Mapper;
import dev.marinhomich.literaula.utils.CheckNullResponseBody;
import dev.marinhomich.literaula.utils.Menu;
import dev.marinhomich.literaula.utils.ScreenClear;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Scanner;

public class SearchOptions {
    private final Scanner scanner = new Scanner(System.in);
    private final ApiService apiService = new ApiService();
    private final Mapper mapper = new Mapper();
    private final String searchMenuOption;
    private final BookRepository bookRepository;
    private final List<Book> books;

    public SearchOptions(String searchMenuOption, BookRepository bookRepository, List<Book> books) {
        this.searchMenuOption = searchMenuOption;
        this.bookRepository = bookRepository;
        this.books = books;
    }

    public void checkOption() {
        switch (searchMenuOption) {
            case "1":
                ScreenClear.clear();
                Menu.saving();

                try {
                    bookRepository.saveAll(books);
                    Menu.saved();
                } catch (DataIntegrityViolationException e) {
                    Menu.alreadySaved();
                }

                EntryPoint.setUserInput("");
                break;
            case "2":
                ScreenClear.clear();
                Menu.askId();
                String bookId = scanner.nextLine();
                ScreenClear.clear();
                Menu.connecting();
                String responseBody = apiService
                        .getResponseBody("https://gutendex.com/books/" + bookId + "/");
                CheckNullResponseBody.check(responseBody, mapper, bookRepository);
                EntryPoint.setUserInput("");
                break;
            case "0":
                ScreenClear.clear();
                EntryPoint.setUserInput("");
                break;
            default:
                ScreenClear.clear();
                Menu.invalidOption();
                break;
        }
    }
}