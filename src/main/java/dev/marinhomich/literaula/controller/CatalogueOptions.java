package dev.marinhomich.literaula.controller;

import dev.marinhomich.literaula.entry.EntryPoint;
import dev.marinhomich.literaula.model.Book;
import dev.marinhomich.literaula.model.DataIndex;
import dev.marinhomich.literaula.repository.BookRepository;
import dev.marinhomich.literaula.service.ApiService;
import dev.marinhomich.literaula.service.Mapper;
import dev.marinhomich.literaula.utils.CheckNullResponseBody;
import dev.marinhomich.literaula.utils.Menu;
import dev.marinhomich.literaula.utils.ScreenClear;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CatalogueOptions {
    private final Scanner scanner = new Scanner(System.in);
    private final ApiService apiService = new ApiService();
    private final Mapper mapper = new Mapper();
    private final DataIndex dataIndex;
    private final String userOption;
    private final List<Book> books;
    private final BookRepository bookRepository;
    private String apiPage;
    private Integer apiPageNumber;


    public CatalogueOptions(String userOption, List<Book> books, BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.dataIndex = EntryPoint.getDataIndex();
        this.userOption = userOption;
        this.books = books;
        this.apiPage = EntryPoint.getApiPage();
        this.apiPageNumber = EntryPoint.getApiPageNumber();
    }

    public void checkOption() {
        switch (this.userOption) {
            case "1":
                if (dataIndex.previousPage() != null) {
                    apiPageNumber--;
                    apiPage = String.valueOf(apiPageNumber);
                    EntryPoint.setApiPageNumber(apiPageNumber);
                    EntryPoint.setApiPage(apiPage);
                }
                break;
            case "2":
                if (dataIndex.nextPage() != null) {
                    apiPageNumber++;
                    apiPage = String.valueOf(apiPageNumber);
                    EntryPoint.setApiPageNumber(apiPageNumber);
                    EntryPoint.setApiPage(apiPage);
                }
                break;
            case "3":
                Menu.askPage();

                try {
                    apiPageNumber = scanner.nextInt();
                    scanner.nextLine();
                    apiPage = String.valueOf(apiPageNumber).trim();
                    EntryPoint.setApiPageNumber(apiPageNumber);
                    EntryPoint.setApiPage(apiPage);
                } catch (InputMismatchException e) {
                    System.out.println("A busca deve ser por número.");
                    break;
                }
                break;
            case "4":
                ScreenClear.clear();
                Menu.saving();

                try {
                    bookRepository.saveAll(books);
                    Menu.saved();
                } catch (DataIntegrityViolationException e) {
                    Menu.alreadySaved();
                }

                break;
            case "5":
                Menu.askId();
                String bookId = scanner.nextLine();
                ScreenClear.clear();
                Menu.connecting();
                String responseBody = apiService
                        .getResponseBody("https://gutendex.com/books/" + bookId + "/");
                CheckNullResponseBody.check(responseBody, mapper, bookRepository);
                break;
            case "6":
                Menu.askOption();
                Menu.askLanguage();
                String langOption = scanner.nextLine();
                ScreenClear.clear();

                switch (langOption) {
                    case "1":
                        EntryPoint.setLangEn("en");
                        EntryPoint.setLangPt("");
                        break;
                    case "2":
                        EntryPoint.setLangEn("");
                        EntryPoint.setLangPt("pt");
                        break;
                    case "3":
                        EntryPoint.setLangEn("en");
                        EntryPoint.setLangPt("pt");
                        break;
                    case "0":
                        Menu.backToCatalogue();
                        break;
                    default:
                        EntryPoint.setLangEn("en");
                        EntryPoint.setLangPt("pt");
                        Menu.invalidOption();
                        break;
                }

                break;
            case "0":
                EntryPoint.setUserInput("");
                break;
            default:
                Menu.invalidOption();
                break;
        }
    }
}