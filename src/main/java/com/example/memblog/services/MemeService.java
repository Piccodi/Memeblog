package com.example.memblog.services;

import com.example.memblog.entities.MemEntity;
import com.example.memblog.repositories.MemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemeService {

    @Autowired
    private MemeRepo memeRepo;

    public void saveMeme(List<MemEntity> meme){
        for (MemEntity m: meme) {
            memeRepo.save(m);
        }

    }

    public void deleteMeme(Long id){
        memeRepo.deleteById(id);
    }
}
