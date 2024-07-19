package com.shawn.supervoting.repo;

import com.shawn.supervoting.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRoleRepo extends JpaRepository<UserRole,String> {
    @Query(nativeQuery = true, value = "SELECT * FROM user_role WHERE role_name=?1")
    Optional<UserRole> findUserRoleByRoleName(String admin);
}
