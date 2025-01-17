package com.shawn.supervoting.repo;

import com.shawn.supervoting.entity.UserRoleHasUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface UserRoleHasUserRepo extends JpaRepository<UserRoleHasUser,String> {
}
