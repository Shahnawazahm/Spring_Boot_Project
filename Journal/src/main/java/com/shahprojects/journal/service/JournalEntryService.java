package com.shahprojects.journal.service;

import com.shahprojects.journal.entity.JournalEntry;
import com.shahprojects.journal.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
public Optional<JournalEntry> findById(ObjectId myId){
  return journalEntryRepository.findById(myId);

}
public void  deleteById(ObjectId myId){
  journalEntryRepository.deleteById(myId);

}
//public JournalEntry updateById(JournalEntry updateEntry){
//  return journalEntryRepository.
//}

}
