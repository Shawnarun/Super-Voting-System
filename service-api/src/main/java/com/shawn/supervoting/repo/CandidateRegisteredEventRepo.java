package com.shawn.supervoting.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@EnableJpaRepositories
@Repository
public interface CandidateRegisteredEventRepo extends JpaRepository<CandidateRegisteredEventRepo,String> {
}
