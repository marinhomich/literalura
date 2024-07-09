package dev.marinhomich.literaula.controller;

import dev.marinhomich.literaula.model.Author;
import dev.marinhomich.literaula.repository.AuthorRepository;
import dev.marinhomich.literaula.utils.Menu;
import dev.marinhomich.literaula.utils.ScreenClear;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Scanner;

public class AuthorArchive {
    private final Scanner scanner = new Scanner(System.in);
    private final AuthorRepository authorRepository;
    private static String authorInput = "";
    private static Integer pageNumber = 1;

    public AuthorArchive(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void load () {
        while (!authorInput.equals("0")) {
            Pageable pageable = PageRequest.of(pageNumber - 1, 10);
            Page<Author> page = authorRepository.findAll(pageable);
            page.forEach(Author::printAuthor);
            Menu.authorMenu();
            Menu.authorMenuInfo(pageNumber, page);
            Menu.askOption();
            authorInput = scanner.nextLine();
            ScreenClear.clear();
            AuthorArchiveOptions authorArchiveOptions = new AuthorArchiveOptions(authorRepository, authorInput, page, pageNumber);
            authorArchiveOptions.checkOption();
        }

        authorInput = "";
    }

    public static void setPageNumber(Integer pageNumber) {
        AuthorArchive.pageNumber = pageNumber;
    }

    public static void setAuthorInput(String authorInput) {
        AuthorArchive.authorInput = authorInput;
    }
}