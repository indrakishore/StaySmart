package com.indra.StaySmart.repository;

import com.indra.StaySmart.entity.AdharDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdharRepository extends JpaRepository<AdharDetails, UUID> {

}