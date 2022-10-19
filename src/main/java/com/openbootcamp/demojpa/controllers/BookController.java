package com.openbootcamp.demojpa.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.openbootcamp.demojpa.models.Book;
import com.openbootcamp.demojpa.repositories.BookRepository;

//import springfox.documentation.annotations.ApiIgnore;

@RestController
public class BookController {

	private final Logger log=LoggerFactory.getLogger(BookController.class);
	
	private BookRepository bookrepo;

	public BookController(BookRepository bookrepo) {
		super();
		this.bookrepo = bookrepo;
	}
	
	@GetMapping("/api/books")
	public List<Book>finAll(){
		return bookrepo.findAll();
		
	}
	@GetMapping("/api/books/{id}")
	public ResponseEntity<Book> findOneById(@PathVariable Long id) {
		Optional<Book> opbook = bookrepo.findById(id);
	// return opbook.orElse(null);
		if(opbook.isPresent()) 
			return ResponseEntity.ok(opbook.get());
		else 
			return ResponseEntity.notFound().build();
		}
	//	return opbook.map(ResponseEntity:ok).orElse(()->ResponseEntity.notFound().build());
	
	@PostMapping("/api/books")
	public ResponseEntity<Object> crear(@RequestBody Book book) {
		if (book.getId()!=null) {
			log.warn("trying to create a book with id");
			return ResponseEntity.badRequest().build();
		}
		Book result=bookrepo.save(book);
		return ResponseEntity.ok(result);
	}
	
	@PutMapping("/api/books")
	public ResponseEntity<Book> update(@RequestBody Book book) {
		if (book.getId()!=null) {
			log.warn("trying to update a non existent book ");
			return ResponseEntity.badRequest().build();
		}
		if(!bookrepo.existsById(book.getId())) {
			log.warn("trying to update a non existent book ");
			return ResponseEntity.notFound().build();
		}
		Book result=bookrepo.save(book);
		return ResponseEntity.ok(result);
		
		
	}
	
	@DeleteMapping("/api/books/{id}")
	public ResponseEntity<Book>delete(@PathVariable Long id){
		if(!bookrepo.existsById(id)) {
			log.warn("trying to delete a non existent book ");
			return ResponseEntity.notFound().build();
			}
		bookrepo.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
	//@ApiIgnore
	@DeleteMapping("/api/books")
	public ResponseEntity<Book>deleteAll(){
		log.info("Rest request for deleting all books");
		bookrepo.deleteAll();
		return ResponseEntity.noContent().build();
		
	}
	}

