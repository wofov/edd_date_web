package com.edd.date.config.security;

import com.edd.date.entity.Admin;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {

    private final Admin admin;

    public SecurityUser(Admin admin) {

        //Spring Security에 사용자를 제공함으로써 Spring Security에 임시 비밀번호를 생성하지 않는다.
        super(admin.getAdminId(), admin.getPassword(),
                AuthorityUtils.createAuthorityList(admin.getRole().toString()));
        this.admin = admin;

    }

}
