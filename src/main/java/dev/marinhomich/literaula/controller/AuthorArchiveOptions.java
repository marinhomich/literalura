package dev.marinhomich.literaula.controller;

import dev.marinhomich.literaula.model.Author;
import dev.marinhomich.literaula.repository.AuthorRepository;
import dev.marinhomich.literaula.utils.Menu;
import dev.marinhomich.literaula.utils.ScreenClear;
import org.springframework.data.domain.Page;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AuthorArchiveOptions {
    private final Scanner scanner = new Scanner(System.in);
    private final AuthorRepository authorRepository;
    private String authorInput;
    private Integer pageNumber;
    private Page<Author> page;

    public AuthorArchiveOptions(AuthorRepository authorRepository, String authorInput, Page<Author> page, Integer pageNumber) {
        this.authorRepository = authorRepository;
        this.authorInput = authorInput;
        this.page = page;
        this.pageNumber = pageNumber;
    }

    public void checkOption() {
        switch (authorInput) {
            case "1":
                if (!page.isFirst()) {
                    pageNumber--;
                    AuthorArchive.setPageNumber(pageNumber);
                }
                break;
            case "2":
                if (!page.isLast()) {
                    pageNumber++;
                    AuthorArchive.setPageNumber(pageNumber);
                }
                break;
            case "3":
                boolean checkOption = false;

                while (!checkOption) {
                    Menu.askPage();

                    try {
                        pageNumber = scanner.nextInt();
                        scanner.nextLine();

                        if (pageNumber >= 1 && pageNumber <= page.getTotalPages()) {
                            AuthorArchive.setPageNumber(pageNumber);
                            checkOption = true;
                            ScreenClear.clear();
                        } else {
                            ScreenClear.clear();
                            Menu.invalidPage(page);
                        }

                    } catch (InputMismatchException e) {
                        Menu.invalidPage(page);
                        break;
                    }

                }
                break;
            case "4":
                boolean exitYearSearch = false;

                while (!exitYearSearch) {
                    System.out.println("Informe o ano para busca: ");

                    try {
                        Integer year = scanner.nextInt();
                        scanner.nextLine();
                        List<Author> authorsAlive = authorRepository.findAuthorsAliveInYear(year);
                        if (!authorsAlive.isEmpty()) {
                            authorsAlive.forEach(Author::printAuthor);
                        } else {
                            System.out.println("Nenhum autor armazenado vivo em " + year + "!");
                        }
                        AuthorArchive.setAuthorInput("0");
                        exitYearSearch = true;
                    } catch (InputMismatchException e) {
                        Menu.invalidOption();
                        break;
                    }

                }

                break;
            case "0":
                AuthorArchive.setPageNumber(1);
                authorInput = "0";
                break;
            default:
                Menu.invalidOption();
                break;
        }
    }
}