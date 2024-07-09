package dev.marinhomich.literaula.controller;

import dev.marinhomich.literaula.entry.EntryPoint;
import dev.marinhomich.literaula.model.Book;
import dev.marinhomich.literaula.model.DataIndex;
import dev.marinhomich.literaula.repository.BookRepository;
import dev.marinhomich.literaula.utils.Menu;
import dev.marinhomich.literaula.utils.ScreenClear;

import java.util.List;
import java.util.Scanner;

public class Catalogue {
    private final Scanner scanner = new Scanner(System.in);
    private final DataIndex dataIndex;
    private final BookRepository bookRepository;
    private Integer apiPageNumber;
    private String apiPage;

    public Catalogue(DataIndex dataIndex, BookRepository bookRepository) {
        this.dataIndex = dataIndex;
        this.bookRepository = bookRepository;
        this.apiPageNumber = EntryPoint.getApiPageNumber();
        this.apiPage = EntryPoint.getApiPage();
    }

    public void load() {
        if (this.dataIndex.invalidPage() == null) {
            List<Book> books = this.dataIndex.books().stream().map(Book::new).toList();
            books.forEach(Book::printBook);
            Menu.pageAndLang();
            Menu.catalogueMenu();
            String userOption = scanner.nextLine();
            ScreenClear.clear();
            CatalogueOptions catalogueOptions = new CatalogueOptions(userOption, books, bookRepository);
            catalogueOptions.checkOption();
        } else {
            ScreenClear.clear();
            Menu.pageNotFound();
            Menu.backToCatalogue();
            apiPageNumber = 1;
            apiPage = String.valueOf(apiPageNumber);
            EntryPoint.setApiPageNumber(apiPageNumber);
            EntryPoint.setApiPage(apiPage);
        }
    }
}
