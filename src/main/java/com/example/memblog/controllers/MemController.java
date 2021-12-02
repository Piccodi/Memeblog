package com.example.memblog.controllers;

import com.example.memblog.entities.MemEntity;
import com.example.memblog.services.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//todo определиться маппингами (ссылками) и написать парсер для мемов
@RestController
@RequestMapping("/memes")
public class MemController {

    private MemeService memeService;

    @Autowired
    public void setMemeService(MemeService memeService) {
        this.memeService = memeService;
    }

    @GetMapping
    public ResponseEntity getMainPage() {
        try {
            return ResponseEntity.ok("working");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("goes wrong");
        }
    }

    @PostMapping
    public ResponseEntity saveMeme(@RequestBody MemEntity meme) {
        try {
            memeService.saveMeme(meme);
            return ResponseEntity.ok("done");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("goes wrong");
        }
    }



    /*
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMeme(@PathVariable("id") Long id) {
        try {
            memeService.deleteMeme(id);
            return ResponseEntity.ok("deleted");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("goes wrong");
        }
    } */

}
