package com.openbootcamp.demojpa;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.openbootcamp.demojpa.models.Book;
import com.openbootcamp.demojpa.services.BookPriceCalculator;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemojpaApplicationTests {
     private TestRestTemplate testRestTemplate;
	
     @Autowired
     private RestTemplateBuilder restTemplateBuilder;
     
     @LocalServerPort
     private int port;
	
	@Test
	void calculatorPrice() {
		Book book= new Book(1L,"Ilusiones","Bach",150,29.90,LocalDate.of(1997,1,2),true);
		BookPriceCalculator calculate=new BookPriceCalculator();
		double price=calculate.calculatePrice(book);
		System.out.println(price);
		
		assertTrue(price>0);
		assertTrue(price==32.89);
		
	}
	
	@BeforeEach
	void setup() {
		restTemplateBuilder=restTemplateBuilder.rootUri("http://localhost:"+ port);
		testRestTemplate=new TestRestTemplate(restTemplateBuilder);
	}
	
	@Test
	void hello() {
		ResponseEntity<String> response=testRestTemplate.getForEntity("/hola",String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Hola Mundo que tal vamos", response.getBody());
	}
	
	@Test
	void findAll() {
		ResponseEntity<Book[]> response=testRestTemplate.getForEntity("/api/books",Book[].class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(200, response.getStatusCodeValue());
		
	}
   @Test
   void finById() {
	   ResponseEntity<Book> response=testRestTemplate.getForEntity("/api/books/1",Book.class);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(404, response.getStatusCodeValue());
   }
 /* @Test
   void create() {
	   HttpHeaders headers= new HttpHeaders();
	   headers.setContentType(MediaType.APPLICATION_JSON);
	   headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));	   
	   String json= """  
		        { 
		          
				   "title": "libro desde test",
				    "author": "Baradit",
				    "pages": 250,
				    "price": 16.9,
				    "release": "2022-10-15",
				    "online": true
				}
	   		""";
   
     HttpEntity<String> request= new HttpEntity<>(json,headers);
     ResponseEntity<Book> response=testRestTemplate.exchange("/api/books",HttpMethod.GET, request,Book.class);
     Book result=response.getBody();
     assertEquals("libro desde test",result.getTitle());
   
}*/
}