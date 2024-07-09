package dev.marinhomich.literaula.utils;

import dev.marinhomich.literaula.controller.Archive;
import dev.marinhomich.literaula.entry.EntryPoint;
import dev.marinhomich.literaula.model.Author;
import dev.marinhomich.literaula.model.Book;
import dev.marinhomich.literaula.model.Language;
import org.springframework.data.domain.Page;
import java.util.List;

public class Menu {
    private static final String mainMenu = """
            
            ***** Literalura *****
            
            +++ Escolha uma opção:
            
            1 - Exibir catálogo online
            2 - Buscar título/autor no catálogo online
            3 - Consultar livros salvos no banco de dados
            4 - Consultar autores salvos no banco de dados
            0 - Sair
            """;
    private static final String searchMenu = """
            +++ Menu da Busca por Título/Autor:
            
            1 - Salvar todos os livros desta página
            2 - Salvar livro por id
            0 - Voltar ao menu principal
            """;
    private static final String catalogueMenu = """
            +++ Menu do Catálogo:
            
            1 - Página anterior
            2 - Próxima página
            3 - Buscar página no catálogo
            4 - Salvar todos os livros desta página
            5 - Salvar livro por id
            6 - Filtrar livros por idioma
            0 - Voltar ao menu principal
            """;
    private static final String archiveMenu = """
            +++ Livros do Banco de Dados:
            
            1 - Página anterior
            2 - Pŕoxima página
            3 - Buscar página no banco de dados
            4 - Filtrar livros por idioma
            0 - Voltar ao menu principal
            """;
    private static final String authorMenu = """
            +++ Autores do Banco de Dados:
            
            1 - Página anterior
            2 - Pŕoxima página
            3 - Buscar página no banco de dados
            4 - Filtrar autores vivos em determinado ano
            0 - Voltar ao menu principal
            """;

    public static void authorMenu() { System.out.println(authorMenu); }

    public static void authorMenuInfo (int pageNumber, Page<Author> page) {
        System.out.println("Exibindo página " + pageNumber + " de " + page.getTotalPages());
        System.out.println("Autores nesta página: " + page.getNumberOfElements());
        System.out.println("Total de Autores Armazenados: " + page.getTotalElements() + "\n");
    }


    // EntryPoint
    public static void mainMenu() {
        System.out.println(mainMenu);
    }
    // EntryPoint
    public static void searchMenu() {
        System.out.println(searchMenu);
    }
    // EntryPoint
    public static void archiveMenu() { System.out.println(archiveMenu); }
    // EntryPoint
    public static void askName() { System.out.println("Digite um nome para busca: "); }
    // EntryPoint
    public static void exit() { System.out.println("Até a próxima :) ...\n"); }
    // EntryPoint
    public static void archiveMenuInfo (int pageNumber, Page<Book> page, List<Book> books) {
        System.out.println("Exibindo página " + pageNumber + " de " + page.getTotalPages());
        System.out.println("Livros nesta página: " + page.getNumberOfElements());
        System.out.println("Total de Livros Armazenados: " + page.getTotalElements());
        System.out.println("Exibindo os idiomas: ");
        List<Book> booksEn = books.stream().filter(book -> book.getLanguage().equals(Language.ENGLISH)).toList();
        List<Book> booksPt = books.stream().filter(book -> book.getLanguage().equals(Language.PORTUGUESE)).toList();

        if (Archive.getLangOption().equals("en")) {
            System.out.println("+ Inglês: " + booksEn.size() + " livros\n");
        } else if (Archive.getLangOption().equals("pt")) {
            System.out.println("+ Português: " + booksPt.size() + " livros\n");
        } else {
            System.out.println("+ Inglês: " + booksEn.size() + " livros");
            System.out.println("+ Português: " + booksPt.size() + " livros\n");
        }
    }

    // ArchiveOptions
    public static <T> void invalidPage (Page<T> page) {
        System.out.println("Digite uma página entre 1 e " + page.getTotalPages() + ".");
    }

    // Catalogue
    public static void pageNotFound() { System.out.println("Página: " + EntryPoint.getApiPage() + " não encontrada"); }
    // Catalogue
    public static void pageAndLang() {
        System.out.println("***** Página: " + EntryPoint.getApiPage() + " *****");
        System.out.println("* Exibindo os idiomas *");
        if (EntryPoint.getLangEn().equals("en") && EntryPoint.getLangPt().isEmpty()) {
            System.out.println("+ Inglês\n");
        } else if (EntryPoint.getLangPt().equals("pt") && EntryPoint.getLangEn().isEmpty()) {
            System.out.println("+ Português\n");
        } else {
            System.out.println("+ Inglês");
            System.out.println("+ Português\n");
        }
    }

    // CatalogueOptions
    public static void askId() { System.out.println("Digite o id do livro: "); }
    // CatalogueOptions
    public static void askLanguage() {
        System.out.println("\n1 - Inglês");
        System.out.println("2 - Português");
        System.out.println("3 - Inglês e Português");
        System.out.println("0 - Voltar\n");
    }

    // CatalogueOptions
    public static void saving() { System.out.println("Salvando...\n"); }
    // CatalogueOptions
    public static void saved() { System.out.println("Salvo com sucesso!\n"); }
    // CatalogueOptions
    public static void alreadySaved() { System.out.println("Livro/Livros já armazenado(s) anteriormente!\n"); }

    // EntryPoint and Catalogue
    public static void catalogueMenu() { System.out.println(catalogueMenu); }
    // Catalogue and CatalogueOptions
    public static void backToCatalogue() { System.out.println("Voltando ao catálogo...\n"); }
    // EntryPoint and CatalogueOptions
    public static void askOption() { System.out.println("Selecione uma opção: "); }
    // EntryPoint and CatalogueOptions
    public static void connecting() { System.out.println("Estabelecendo conexão...\n"); }
    // EntryPoint and CatalogueOptions
    public static void invalidOption() { System.out.println("Digite uma opção válida."); }
    // EntryPoint and CatalogueOptions
    public static void notFound() { System.out.println("Livro/Autor não encontrado."); }
    // EntryPoint and CatalogueOptions
    public static void askPage() { System.out.println("Digite o número da página para busca: "); }

}