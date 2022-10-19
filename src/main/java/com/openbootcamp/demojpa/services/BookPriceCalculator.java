package com.openbootcamp.demojpa.services;

import com.openbootcamp.demojpa.models.Book;


public class BookPriceCalculator {

	public double calculatePrice(Book book) {
		double price= book.getPrice();
		if(book.getPages()>400){
			price+=5;
		}
		price +=2.99;
		return price;
	}
}
