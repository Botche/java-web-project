package com.example.application.data.service;

import com.example.application.data.constants.ExceptionMessages;
import com.example.application.data.entity.Contact;
import com.example.application.data.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactsService {
    private final ContactRepository contactRepository;

    public ContactsService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> findAllContacts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return contactRepository.findAll();
        }

        return contactRepository.search(stringFilter);
    }

    public long countContacts() {
        return contactRepository.count();
    }

    public void deleteContact(Contact contact) {
        contactRepository.delete(contact);
    }

    public void saveContact(Contact contact) {
        if (contact == null) {
            System.err.println(ExceptionMessages.SAVE_CONTACT_NULL_EXCEPTION_MESSAGE);
            return;
        }

        contactRepository.save(contact);
    }
}
