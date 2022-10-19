package com.openbootcamp.demojpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openbootcamp.demojpa.models.Book;

@Repository
public interface BookRepository extends JpaRepository <Book,Long> {

}
