package com.shahprojects.journal.controller;

import com.shahprojects.journal.entity.JournalEntry;
import com.shahprojects.journal.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;

   @GetMapping
   public ResponseEntity<?> getAll(){

       List<JournalEntry> all = journalEntryService.getAll();
       if(all!=null && !all.isEmpty()){
           return new ResponseEntity<>(all, HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
        try {
            myEntry.setDate(LocalDateTime.now());

            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }
    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryByID(@PathVariable ObjectId myId){

        Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);

       if(journalEntry.isPresent()){
           return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
       }
        return new ResponseEntity<>("Can't found any data with id provided: "+myId,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId){
         journalEntryService.deleteById(myId);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId myId , @RequestBody JournalEntry updateEntry){
         JournalEntry old = journalEntryService.findById(myId).orElse(null);
         if(old !=null){
             if(updateEntry.getTitle()!=null && !updateEntry.getTitle().equals(""))
                 old.setTitle(updateEntry.getTitle());
             if( updateEntry.getContent()!=null && !updateEntry.getContent().equals("") )
                 old.setContent(updateEntry.getContent());


                 old.setDate(LocalDateTime.now());
//             old.setTitle(updateEntry.getTitle() != null && !updateEntry.getTitle().equals("")? updateEntry.getTitle() : old.getTitle());
//             old.setContent(updateEntry.getContent() !=null && !updateEntry.getContent().equals("")?updateEntry.getContent(): old.getContent());
             journalEntryService.saveEntry(old);
             return new ResponseEntity<>(old,HttpStatus.OK);

         }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}


