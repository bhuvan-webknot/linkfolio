package com.bhuvan.linkfolio.repository;

import com.bhuvan.linkfolio.model.SocialMedia;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialMediaRepository extends JpaRepository<SocialMedia, Long> {
}
