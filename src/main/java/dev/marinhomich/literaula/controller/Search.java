package dev.marinhomich.literaula.controller;

import dev.marinhomich.literaula.entry.EntryPoint;
import dev.marinhomich.literaula.model.Book;
import dev.marinhomich.literaula.model.DataIndex;
import dev.marinhomich.literaula.repository.BookRepository;
import dev.marinhomich.literaula.utils.Menu;
import dev.marinhomich.literaula.utils.ScreenClear;

import java.util.List;
import java.util.Scanner;

public class Search {
    private final Scanner scanner = new Scanner(System.in);
    private final DataIndex dataIndex;
    private final BookRepository bookRepository;

    public Search(BookRepository bookRepository) {
        this.dataIndex = EntryPoint.getDataIndex();
        this.bookRepository = bookRepository;
    }

    public void load() {
        if (!dataIndex.books().isEmpty()) {
            ScreenClear.clear();
            List<Book> books = dataIndex.books().stream().map(Book::new).toList();
            books.forEach(Book::printBook);
            Menu.searchMenu();
            String searchMenuOption = scanner.nextLine();
            SearchOptions searchOptions = new SearchOptions(searchMenuOption, bookRepository, books);
            searchOptions.checkOption();
        } else {
            ScreenClear.clear();
            Menu.notFound();
        }
    }
}