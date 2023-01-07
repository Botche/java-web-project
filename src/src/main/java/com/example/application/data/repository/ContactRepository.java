package com.example.application.data.repository;

import com.example.application.data.constants.Queries;
import com.example.application.data.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query(Queries.SEARCH_CONTACTS_QUERY)
    List<Contact> search(@Param("searchTerm") String searchTerm);
}
