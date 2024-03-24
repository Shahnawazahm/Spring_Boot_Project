package com.shahprojects.journal.service;

import com.shahprojects.journal.entity.JournalEntry;
import com.shahprojects.journal.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalEntryService {

 @Autowired
 private JournalEntryRepository journalEntryRepository;

 public void saveEntry(JournalEntry journalEntry){
  journalEntryRepository.save(journalEntry);
 }
public List<JournalEntry> getAll(){
  return journalEntryRepository.findAll();
}
}
