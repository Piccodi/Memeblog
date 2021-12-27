package com.example.memblog.repositories;

import com.example.memblog.entities.FavMemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface FavRepo extends CrudRepository<FavMemEntity, Long> {

    Optional<FavMemEntity> findByUserId(Long user_id);

    @Query(value = "SELECT COUNT(*) FROM liked_memes where fav_mem_id = ?1", nativeQuery = true)
    Optional<Integer> getSavesCount(Long userId);

    @Query(value = "SELECT mem_id FROM(SELECT ROW_NUMBER() " +
            "OVER(ORDER BY mem_id DESC) AS RowNum, mem_id " +
            "FROM liked_memes where fav_mem_id = ?1) " +
            "liked_memes where RowNum > ?2 LIMIT ?3", nativeQuery = true)
    Optional<ArrayList<Long>> getSomeSaves(Long userId, int startRow, int limit);

    @Query(value = "SELECT mem_id FROM liked_memes WHERE fav_mem_id = ?1 " +
            "ORDER BY mem_id DESC LIMIT 1", nativeQuery = true)
    Optional<Long> lastSavedMemeId(Long userId);

    @Query(value = "SELECT mem_id FROM liked_memes WHERE fav_mem_id = ?1 " +
            "AND mem_id < ?2 ORDER BY mem_id DESC LIMIT ?3",
            nativeQuery = true)
    Optional<ArrayList<Long>> getPartOfSaves(Long userId, Long memeId, int limit);
}
