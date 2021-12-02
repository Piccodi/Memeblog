package com.example.memblog.repositories;

import com.example.memblog.entities.MemEntity;
import org.springframework.data.repository.CrudRepository;

public interface MemeRepo extends CrudRepository<MemEntity, Long> {}
