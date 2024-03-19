package com.shahprojects.journal.controller;

import com.shahprojects.journal.JournalApplication;
import com.shahprojects.journal.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
 private Map<Long , JournalEntry> journalEntries = new HashMap<>();
    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
           journalEntries.put(myEntry.getId(),myEntry);
     return true;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryByID(@PathVariable Long myId){
        return journalEntries.get(myId);
    }

    @DeleteMapping("id/{myid}") public JournalEntry deleteJournalEntryById(@PathVariable Long myId){
        return journalEntries.remove(myId);
    }
    @PutMapping("id/{myId}")
    public boolean updateEntry(@PathVariable Long myId , @RequestBody JournalEntry updateEntry){
        journalEntries.put(myId,updateEntry);
        return true;
    }
    @PutMapping("id/{myID}")
    public JournalEntry updateEntry02(@PathVariable Long myID, @RequestBody JournalEntry updateEntry02){
     return journalEntries.put(myID, updateEntry02);
    }
}

