package com.example.memblog.services;

import com.example.memblog.entities.MemEntity;
import com.example.memblog.repositories.MemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemeService {

    @Autowired
    private MemeRepo memeRepo;

    public void saveMeme(MemEntity meme){
        memeRepo.save(meme);
    }

    public void deleteMeme(Long id){
        memeRepo.deleteById(id);
    }
}
