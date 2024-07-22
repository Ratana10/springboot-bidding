package com.assignment.bidding.repository;

import com.assignment.bidding.enums.Status;
import com.assignment.bidding.model.Item;
import com.assignment.bidding.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByStatus(Status status);
    List<Item> findByCreatedBy(Long clientId);

}
