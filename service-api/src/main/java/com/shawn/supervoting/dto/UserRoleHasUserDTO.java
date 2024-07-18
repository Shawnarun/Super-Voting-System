package com.shawn.supervoting.dto;

import com.shawn.supervoting.entity.User;
import com.shawn.supervoting.entity.UserRole;
import com.shawn.supervoting.entity.UserRoleHasUserKey;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleHasUserDTO{

    private UserDTO user;
    private UserRoleDTO userRole;
}
