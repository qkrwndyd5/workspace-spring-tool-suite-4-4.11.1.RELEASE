package com.itwill.security.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter 
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user1")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String username;

    @Setter
    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    @Setter
    private Role role;

    @CreationTimestamp  //자동으로 만들어준다
    private Timestamp createTime;


    @Builder(builderClassName = "UserDetailRegister", builderMethodName = "userDetailRegister")
    public User(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}