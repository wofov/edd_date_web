package com.edd.date.config.security;

import com.edd.date.constants.WebConstants;
import com.edd.date.entity.Admin;
import com.edd.date.repository.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class SecurityUserDetailService implements UserDetailsService {
    private final AdminRepository adminRepository;

    SecurityUserDetailService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        return new SecurityUser(adminRepository.findByAdminId(username));

    }

}


