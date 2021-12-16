package com.example.memblog.services;

import com.example.memblog.entities.MemEntity;
import com.example.memblog.models.MemeModel;
import com.example.memblog.repositories.MemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        var memes = new ArrayList<MemeModel>();
        var lastMeme = memeRepo.findNewestMeme().get();
        var num = 30;
        var startsNum = lastMeme.getId().intValue() - pageNum * num;
        var endNum = lastMeme.getId().intValue() - pageNum * num - num;
        if(endNum < 0)endNum = 0;
        for(long i = lastMeme.getId().intValue() - pageNum * num; i > endNum; i--){
            memes.add(MemeModel.toModel(memeRepo.findById(i).get()));
        }

        return memes;
    }
    public String findLastImage(){
        return memeRepo.findNewestMeme().get().getReference();
    }
    public void deleteMeme(Long id){
        memeRepo.deleteById(id);
    }
}
