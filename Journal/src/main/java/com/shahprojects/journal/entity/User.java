package com.shahprojects.journal.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collation = "users")
@Data
public class User {
  @Id
  private ObjectId id;
  @Indexed(unique = true)
  @NonNull
  private String username;
  @NonNull
  private String password;
  @DBRef
  private List<JournalEntry> journalEntries = new ArrayList<>();
}
