package com.andreitop.newco.repository;

import com.andreitop.newco.dto.TripDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(isolation = Isolation.SERIALIZABLE)
public interface TripRepository extends JpaRepository<TripDto, Long> {
}