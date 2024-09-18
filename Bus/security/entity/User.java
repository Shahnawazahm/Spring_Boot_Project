package com.redbus.security.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Data
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(min=3,max = 15,message = "Name should contain atleast 3 charcters & atmost 15 charcters")
    @Column(name="name")
    private String name;

    @NotNull
    @Size(min=3,max = 8,message = "Username should contain atleast 3 charcters & atmost 8 charcters")
    @Column(name="username",unique = true)
    private String username;
    @NotNull
    @Column(name="email",unique = true)
    @Email(message = "Please provide valid email")
    private String email;

    @NotNull(message = "password should not be null")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
}
