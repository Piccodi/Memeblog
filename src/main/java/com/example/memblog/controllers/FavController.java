package com.example.memblog.controllers;


import com.example.memblog.entities.FavMemEntity;
import com.example.memblog.services.FavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//todo определиться маппингами (ссылками)
@RestController
public class FavController {

    private FavService favService;

    @Autowired
    public void setFavService(FavService favService){this.favService = favService;}

    @GetMapping("/memes/{user_id}/{mem_id}")
    public ResponseEntity addToFavMeme(@PathVariable("user_id") Long user_id, @PathVariable("mem_id") Long mem_id){
        try {
            favService.addToFav(user_id, mem_id);
            return ResponseEntity.ok("saved");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("goes wrong");
        }
    }
    @DeleteMapping("/fav/{user_id}/{mem_id}")
    public ResponseEntity deleteFromFav(@PathVariable("user_id") Long user_id, @PathVariable("mem_id") Long mem_id){
        try {
            favService.deleteFromFav(user_id, mem_id);
            return ResponseEntity.ok("saved");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("goes wrong");
        }
    }

    @GetMapping("/fav/{user_id}")
    public ResponseEntity getFavs(@PathVariable("user_id") Long id){
        try {
            return ResponseEntity.ok(favService.getAll(id));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("goes wrong");
        }
    }
}
