package com.example.memblog.repositories;

import com.example.memblog.entities.MemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemeRepo extends CrudRepository<MemEntity, Long> {

    @Query(value = " SELECT * FROM mem_entity ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<MemEntity> findNewestMeme();
}
