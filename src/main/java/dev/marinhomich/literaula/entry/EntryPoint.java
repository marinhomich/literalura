package dev.marinhomich.literaula.entry;

import dev.marinhomich.literaula.controller.Archive;
import dev.marinhomich.literaula.controller.AuthorArchive;
import dev.marinhomich.literaula.controller.Search;
import dev.marinhomich.literaula.model.DataIndex;
import dev.marinhomich.literaula.repository.AuthorRepository;
import dev.marinhomich.literaula.repository.BookRepository;
import dev.marinhomich.literaula.controller.Catalogue;
import dev.marinhomich.literaula.utils.Menu;
import dev.marinhomich.literaula.service.ApiService;
import dev.marinhomich.literaula.service.Mapper;
import dev.marinhomich.literaula.utils.ScreenClear;

import java.util.Scanner;


public class EntryPoint {
    private final Scanner scanner = new Scanner(System.in);
    private final ApiService apiService = new ApiService();
    private final Mapper mapper = new Mapper();
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private static DataIndex dataIndex;
    private static Integer apiPageNumber = 1;
    private static String langPt = "pt";
    private static final String langComma = ",";
    private static String langEn = "en";
    private static String apiPage = String.valueOf(apiPageNumber);
    private static String userInput = "";
    private String responseBody;

    public EntryPoint(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void init() {
        ScreenClear.clear();
        boolean exitLoop = false;

        while (!exitLoop) {

            if (userInput.isEmpty()) {
                Menu.mainMenu();
                userInput = scanner.nextLine();
                ScreenClear.clear();
            }

            switch (userInput) {
                case "1":
                    Menu.connecting();
                    responseBody = apiService.getResponseBody(
                            "https://gutendex.com/books/?languages=" +
                                    langPt + langComma + langEn +
                                    "&page=" + apiPage
                    );

                    if (responseBody != null) {
                        dataIndex = mapper.getClassFromJson(responseBody, DataIndex.class);
                        Catalogue catalogue = new Catalogue(dataIndex, bookRepository);
                        catalogue.load();
                    }

                    break;
                case "2":
                    Menu.askName();
                    String apiSearchName = scanner.nextLine().replace(" ", "%20").toLowerCase();
                    ScreenClear.clear();
                    Menu.connecting();
                    responseBody = apiService
                            .getResponseBody("https://gutendex.com/books/?languages=pt,en&search=" + apiSearchName);
                    dataIndex = mapper.getClassFromJson(responseBody, DataIndex.class);
                    Search search = new Search(bookRepository);
                    search.load();
                    break;
                case "3":
                    Archive archive = new Archive(bookRepository);
                    archive.load();
                    userInput = "";
                    break;
                case "4":
                    AuthorArchive authorArchive = new AuthorArchive(authorRepository);
                    authorArchive.load();
                    userInput = "";
                    break;
                case "0":
                    Menu.exit();
                    exitLoop = true;
                    break;
                default:
                    Menu.invalidOption();
                    userInput = "";
                    break;
            }
        }
    }

    public static String getLangPt() {
        return langPt;
    }

    public static void setLangPt(String langPt) {
        EntryPoint.langPt = langPt;
    }

    public static String getLangEn() {
        return langEn;
    }

    public static void setLangEn(String langEn) {
        EntryPoint.langEn = langEn;
    }

    public static String getApiPage() {
        return apiPage;
    }

    public static void setApiPage(String apiPage) {
        EntryPoint.apiPage = apiPage;
    }

    public static Integer getApiPageNumber() {
        return apiPageNumber;
    }

    public static void setApiPageNumber(Integer apiPageNumber) {
        EntryPoint.apiPageNumber = apiPageNumber;
    }

    public static DataIndex getDataIndex() {
        return dataIndex;
    }

    public static void setUserInput(String userInput) {
        EntryPoint.userInput = userInput;
    }
}