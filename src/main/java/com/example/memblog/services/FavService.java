package com.example.memblog.services;

import com.example.memblog.entities.FavMemEntity;
import com.example.memblog.entities.MemEntity;
import com.example.memblog.models.MemeModel;
import com.example.memblog.repositories.FavRepo;
import com.example.memblog.repositories.MemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavService {

    @Autowired
    private FavRepo favRepo;

    @Autowired
    private MemeRepo memeRepo;

    public void createFavList(FavMemEntity favMem){favRepo.save(favMem);}

    public void addToFav(Long user_id, Long mem_id){
        var favMem = favRepo.findByUserId(user_id).get();
        var meme = memeRepo.findById(mem_id).get();
        meme.saveFav(favMem);
        memeRepo.save(meme);
        favMem.saveMeme(meme);
        favRepo.save(favMem);
    }

    public void deleteFromFav(Long user_id, Long mem_id){
        var fav = favRepo.findByUserId(user_id).get();
        var mem = memeRepo.findById(mem_id).get();
        fav.deleteMeme(mem);
        mem.deleteFrom(fav);
        favRepo.save(fav);
        memeRepo.save(mem);
    }

    public void deleteFavList(Long user_id){
        var fav = favRepo.findByUserId(user_id).get();
        fav.deleteAll();
        favRepo.save(fav);
        favRepo.deleteById(fav.getId());
    }
    //fixme багует если сохраненки не в последовательности
    public List<MemeModel> getAll(Long userId, int pageNum){
        //var favMemes = favRepo.findByUserId(id).get().getFavMemes();
        var favMemes = new ArrayList<MemEntity>();
        var countSaves = favRepo.getSavesCount(userId).get();

        var countPart = 2;
        var startNum = pageNum * countPart;

        var savedPart = favRepo.getSomeSaves(userId, startNum, countPart).get();
        for (Long memeId: savedPart) {
            favMemes.add(memeRepo.findById(memeId).get());
        }
        return favMemes.stream().map(MemeModel::toModel).collect(Collectors.toList());
    }

}
