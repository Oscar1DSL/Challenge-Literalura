package com.alurachallenge.literalura.repository;

import com.alurachallenge.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiteraturaRepository extends JpaRepository<Book, Long>{
}


