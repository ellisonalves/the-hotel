package com.ellisonalves.thehotel.domain.repositories;

import com.ellisonalves.thehotel.domain.entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
