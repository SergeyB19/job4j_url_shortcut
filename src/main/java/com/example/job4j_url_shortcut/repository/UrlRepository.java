package com.example.job4j_url_shortcut.repository;

import com.example.job4j_url_shortcut.model.Site;
import com.example.job4j_url_shortcut.model.Url;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface UrlRepository extends CrudRepository<Url, Integer> {
    boolean existsByShortUrl(String shortUrl);

    Optional<Url> findByLongUrl(String longUrl);

    Optional<Url> findByShortUrl(String shortUrl);

    /**
     * Update
     * @param shortUrl used for query filter to update counter.
     */
    @Modifying
    @Transactional
    @Query("UPDATE Url u SET u.callsCounter = u.callsCounter + 1 WHERE u.shortUrl = :shortUrl")
    void updateCallsCounterByShortUrl(String shortUrl);

    List<Url> findAllBySite(Site site);
}
