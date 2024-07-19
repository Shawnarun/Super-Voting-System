package com.shawn.supervoting.service.Impl;

import com.shawn.supervoting.entity.UserRole;
import com.shawn.supervoting.repo.UserRoleRepo;
import com.shawn.supervoting.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepo userRoleRepo;

    public UserRoleServiceImpl(UserRoleRepo userRoleRepo) {
        this.userRoleRepo = userRoleRepo;
    }

    @Override
    public void initializerUserRoles() {
        List<UserRole> list = userRoleRepo.findAll();
        if (list.isEmpty()){
            userRoleRepo.saveAll(List.of(
                    UserRole.builder().roleId(UUID.randomUUID().toString())
                            .roleName("USER").description("Description").build(),
                    UserRole.builder().roleId(UUID.randomUUID().toString())
                            .roleName("ADMIN").description("Description").build()
            ));

    }
}
}
