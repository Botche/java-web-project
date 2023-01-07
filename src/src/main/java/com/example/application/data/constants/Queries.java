package com.example.application.data.constants;

public class Queries {
    public static final String EMPLOYEES_COUNT_QUERY = "(select count(c.id) from Contact c where c.company_id = id)";
    public static final String SEARCH_CONTACTS_QUERY = "select c from Contact c " +
            "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))";
}
