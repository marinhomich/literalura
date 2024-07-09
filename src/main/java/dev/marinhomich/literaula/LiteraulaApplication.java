package dev.marinhomich.literaula;

import dev.marinhomich.literaula.entry.EntryPoint;
import dev.marinhomich.literaula.repository.AuthorRepository;
import dev.marinhomich.literaula.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraulaApplication implements CommandLineRunner {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraulaApplication.class, args);
	}

	@Override
	public void run (String ...args) throws Exception{
		try {
			EntryPoint entryPoint = new EntryPoint(bookRepository, authorRepository);
			entryPoint.init();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
