package com.shawn.supervoting.repo;

import com.shawn.supervoting.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface UserRoleRepo extends JpaRepository<UserRole,String> {
}
