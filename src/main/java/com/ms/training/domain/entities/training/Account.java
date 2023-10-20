package com.ms.training.domain.entities.training;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.UniqueConstraint;
import java.util.Set;

@Entity
@Table(name = "account",uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "password")
    private String password;

//    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinTable(name = "accounts_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
