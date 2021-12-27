package com.example.memblog.controllers;


import com.example.memblog.entities.FavMemEntity;
import com.example.memblog.services.FavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/fav/{user_id}/{pageNum}")
    public ResponseEntity getFavs(@PathVariable("user_id") Long id, @PathVariable("pageNum") int num){
        try {
            return ResponseEntity.ok(favService.getAll(id, num));
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("goes wrong");
        }
    }
}
