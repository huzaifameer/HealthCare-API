package com.huzaifa.healthcare.system.service.impl;

import com.huzaifa.healthcare.system.auth.ApplicationUser;
import com.huzaifa.healthcare.system.entity.User;
import com.huzaifa.healthcare.system.entity.UserRoleHasUser;
import com.huzaifa.healthcare.system.repo.UserRepo;
import com.huzaifa.healthcare.system.repo.UserRoleHasUserRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.huzaifa.healthcare.system.security.ApplicationUserRole.ADMIN;
import static com.huzaifa.healthcare.system.security.ApplicationUserRole.DOCTOR;

@Service
public class ApplicationUserServiceImpl implements UserDetailsService {
    private final UserRepo userRepo;
    private final UserRoleHasUserRepo userRoleHasUserRepo;

    public ApplicationUserServiceImpl(UserRepo userRepo, UserRoleHasUserRepo userRoleHasUserRepo) {
        this.userRepo = userRepo;
        this.userRoleHasUserRepo = userRoleHasUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User selectedUser = userRepo.findByUserName(username);
        if (selectedUser==null){
            throw new UsernameNotFoundException(String.format("Username %s not found..!",username));
        }

        List<UserRoleHasUser> userRoles = userRoleHasUserRepo.findByUserId(selectedUser.getId());
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();

        for(UserRoleHasUser userRole : userRoles){
            if (userRole.getUserRole().getRoleName().equals("ADMIN")){
                grantedAuthorities.addAll(ADMIN.getGrantedAuthorities());
            }
            if (userRole.getUserRole().getRoleName().equals("DOCTOR")){
                grantedAuthorities.addAll(DOCTOR.getGrantedAuthorities());
            }
        }
        return new ApplicationUser(
                grantedAuthorities,
                selectedUser.getPassword(),
                selectedUser.getEmail(),
                selectedUser.isAccountNotExpired(),
                selectedUser.isAccountsNotLocked(),
                selectedUser.isCredentialsNotExpired(),
                selectedUser.isEnabled()
        );

    }
}
