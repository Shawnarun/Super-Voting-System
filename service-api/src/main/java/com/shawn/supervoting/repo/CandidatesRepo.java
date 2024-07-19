package com.shawn.supervoting.repo;

import com.shawn.supervoting.entity.Candidates;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface CandidatesRepo extends JpaRepository<Candidates,String> {
    @Query(nativeQuery = true, value = "SELECT * FROM candidates WHERE name LIKE %?1%")
    List<Candidates> findByPagination(String searchText, Pageable pageable);
    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM candidates WHERE name LIKE %?1%")
    long countBySearchText(String searchText);
}
