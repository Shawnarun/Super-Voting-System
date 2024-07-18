package com.shawn.supervoting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleHasUserKey implements Serializable {
    @Column(name = "user_id", length = 80)
    private String user;
    @Column(name = "role_id", length = 80)
    private String userRole;


}
