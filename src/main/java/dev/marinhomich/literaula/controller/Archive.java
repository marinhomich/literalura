package dev.marinhomich.literaula.controller;

import dev.marinhomich.literaula.model.Book;
import dev.marinhomich.literaula.model.Language;
import dev.marinhomich.literaula.repository.BookRepository;
import dev.marinhomich.literaula.utils.Menu;
import dev.marinhomich.literaula.utils.ScreenClear;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Scanner;

public class Archive {
    private final Scanner scanner = new Scanner(System.in);
    private final BookRepository bookRepository;
    private Page<Book> page;
    private String archiveInput = "";
    private static Integer pageNumber = 1;
    private static String langOption = "all";

    public Archive(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void load() {
        List<Book> books = bookRepository.findAll();

        while (!archiveInput.equals("0")) {
            Pageable pageable = PageRequest.of(pageNumber - 1, 10);

            switch (langOption) {
                case "all":
                    page = bookRepository.findAll(pageable);
                    break;
                case "en":
                    page = bookRepository.findBooksByLanguage(Language.ENGLISH, pageable);
                    break;
                case "pt":
                    page = bookRepository.findBooksByLanguage(Language.PORTUGUESE, pageable);
                    break;
            }

            page.forEach(Book::printBook);
            Menu.archiveMenu();
            Menu.archiveMenuInfo(pageNumber, page, books);
            Menu.askOption();
            archiveInput = scanner.nextLine();
            ScreenClear.clear();
            ArchiveOptions archiveOptions = new ArchiveOptions(archiveInput, page, pageNumber);
            archiveOptions.checkOption();
        }

        archiveInput = "";
    }

    public static String getLangOption() { return langOption; }

    public static void setLangOption(String langOption) { Archive.langOption = langOption; }

    public static void setPageNumber(Integer pageNumber) {
        Archive.pageNumber = pageNumber;
    }
}