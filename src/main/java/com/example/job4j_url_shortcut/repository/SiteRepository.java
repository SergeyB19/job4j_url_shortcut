package com.example.job4j_url_shortcut.repository;

import com.example.job4j_url_shortcut.model.Site;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiteRepository extends CrudRepository<Site, Integer> {
    Optional<Site> findByDomain(String name);

    boolean existsByLogin(String login);

    Optional<Site> findByLogin(String login);
}
