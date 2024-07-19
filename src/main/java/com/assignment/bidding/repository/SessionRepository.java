package com.assignment.bidding.repository;

import com.assignment.bidding.enums.Status;
import com.assignment.bidding.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByStatus(Status status);
}
