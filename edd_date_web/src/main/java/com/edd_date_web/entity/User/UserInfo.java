package com.edd.date.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userSeq;

    private String username;
    private String nickname;
    private String phNumber;
    private String email;


}
