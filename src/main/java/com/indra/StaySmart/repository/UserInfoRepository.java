package com.indra.StaySmart.repository;

import com.indra.StaySmart.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);
}
