package com.example.memblog.repositories;

import com.example.memblog.entities.FavMemEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

//todo сохранение мемов в таблицу- связку и поиск по этой таблице
public interface FavRepo extends CrudRepository<FavMemEntity, Long> {

    Optional<FavMemEntity> findByUserId(Long user_id);

}
