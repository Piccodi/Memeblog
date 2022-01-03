package com.example.memblog.repositories;

import com.example.memblog.entities.MemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface MemeRepo extends CrudRepository<MemEntity, Long> {

    @Query(value = " SELECT * FROM mem_entity ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<MemEntity> findNewestMeme();

    @Query(value = " SELECT * FROM(SELECT ROW_NUMBER() OVER(ORDER BY id DESC) " +
            "AS RowNum, id, length, reference, width FROM mem_entity) " +
            "mem_entity WHERE RowNum > ?1 LIMIT ?2", nativeQuery = true)
    Optional<ArrayList<MemEntity>> getPartMemes(int start, int limit);
}
