package com.edd.date.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    private String adminId;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role{
        MASTER
    }

}
