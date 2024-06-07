package com.huzaifa.healthcare.system.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    private long id;
    private String fullName;
    private String email;
    private String password;
    private boolean isAccountNotExpired;
    private boolean isCredentialsNotExpired;
    private boolean isAccountsNotLocked;
    private boolean isEnabled;

    @OneToMany(mappedBy = "user")
    private Set<UserRoleHasUser> userRoleHasUsers;
}
