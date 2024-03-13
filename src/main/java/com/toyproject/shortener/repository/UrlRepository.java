package com.toyproject.shortener.repository;

import com.toyproject.shortener.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
}
