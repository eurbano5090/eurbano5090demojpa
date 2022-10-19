package com.openbootcamp.demojpa;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.openbootcamp.demojpa.models.Book;
import com.openbootcamp.demojpa.repositories.BookRepository;

@SpringBootApplication
public class DemojpaApplication {
	@Autowired
	BookRepository repository;


	public static void main(String[] args) {
		ApplicationContext context= SpringApplication.run(DemojpaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);



		Book book1= new Book(null,"Ilusiones","Bach",150,29.90,LocalDate.of(1997,1,2),true);
		Book book2= new Book(null,"el puente al infinito","Bach",250,19.90,LocalDate.of(2003,12,2),true);
		repository.save(book1);
		repository.save(book2);
		
		System.out.println("numero de libros en base de datos " + repository.findAll().size());
		System.out.println(repository.findAll());
	}
	         
	  }


