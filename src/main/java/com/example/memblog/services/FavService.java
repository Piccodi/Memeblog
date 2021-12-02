package com.example.memblog.services;

import com.example.memblog.entities.FavMemEntity;
import com.example.memblog.entities.MemEntity;
import com.example.memblog.models.MemeModel;
import com.example.memblog.repositories.FavRepo;
import com.example.memblog.repositories.MemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

// todo добавить метод для добавления связки в liked memes
@Service
public class FavService {

    @Autowired
    private FavRepo favRepo;

    @Autowired
    private MemeRepo memeRepo;

    public void createFavList(FavMemEntity favMem){favRepo.save(favMem);}
    //public Set<?> getFavMemes(Long id){favRepo.getAll}
    //todo put in try catch and make some exceptions
    public void addToFav(Long user_id, Long mem_id){
        var favMem = favRepo.findByUserId(user_id).get();
        //todo метод поиска в таблице связке
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
    //fixme возможно надо и для всех мемов почистить
    public void deleteFavList(Long user_id){
        var fav = favRepo.findByUserId(user_id).get();
        fav.deleteAll();
        favRepo.save(fav);
        favRepo.deleteById(fav.getId());
    }

    public Set<MemeModel> getAll(Long id){
        var favMemes = favRepo.findByUserId(id).get().getFavMemes();
        return favMemes.stream().map(MemeModel::toModel).collect(Collectors.toSet());
    }

}
