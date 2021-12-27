package com.example.memblog.models;

import com.example.memblog.entities.MemEntity;

public class MemeModel {
    private Long id;
    private String reference;
    private Integer width;
    private Integer length;

    public static MemeModel toModel(MemEntity entity){
        var meme = new MemeModel();
        meme.setId(entity.getId());
        meme.setReference(entity.getReference());
        meme.setWidth(entity.getWidth());
        meme.setLength(entity.getLength());
        return meme;
    }

    //not used
    public static MemeModel setModel(String ref, int len, int wid){
        MemeModel meme = new MemeModel();
        meme.setReference(ref);
        meme.setLength(len);
        meme.setWidth(wid);
        return meme;
    }

    @Override
    public String toString(){
        return reference + " : " + length + " : " + width;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
