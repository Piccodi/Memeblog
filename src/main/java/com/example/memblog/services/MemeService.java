package com.example.memblog.services;

import com.example.memblog.entities.MemEntity;
import com.example.memblog.models.MemeModel;
import com.example.memblog.repositories.MemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemeService {

    @Autowired
    private MemeRepo memeRepo;

    public void saveMemes(List<MemEntity> meme){
        for (MemEntity m: meme) {
            memeRepo.save(m);
        }

    }
    public List<MemeModel> getMemes(int pageNum){
        var lastMeme = memeRepo.findNewestMeme().get();
        var limit = 30;
        var start = pageNum * limit;
        return memeRepo.getPartMemes(start, limit).get().stream().map(MemeModel::toModel).collect(Collectors.toList());
    }
    public String findLastImage(){
        return memeRepo.findNewestMeme().get().getReference();
    }
    public void deleteMeme(Long id){
        memeRepo.deleteById(id);
    }
}
