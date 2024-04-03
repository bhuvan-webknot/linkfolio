package com.bhuvan.linkfolio.repository;

import com.bhuvan.linkfolio.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> {
}
