package com.example.ticketdesk.repository;

import com.example.ticketdesk.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
}