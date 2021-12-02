package com.example.memblog.services;

import com.example.memblog.entities.FavMemEntity;
import com.example.memblog.entities.UserEntity;
import com.example.memblog.exceptions.UserAlreadyExistsException;
import com.example.memblog.exceptions.UserDoesNotExistException;
import com.example.memblog.exceptions.UserIncorrectPassword;
import com.example.memblog.models.UserModel;
import com.example.memblog.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FavService favService;

    public UserModel saveUser(UserEntity user) throws UserAlreadyExistsException {
        if(userRepo.findByUsername(user.getUsername()).isPresent()){
            throw new UserAlreadyExistsException("User already exists");
        }
        var userNew = userRepo.save(user);
        var userFav = new FavMemEntity();
        userFav.setUser(userNew);
        favService.createFavList(userFav);
        return UserModel.toModel(userNew);
    }

    public UserModel checkUser(UserEntity user) throws UserDoesNotExistException, UserIncorrectPassword {
        if(userRepo.findByUsername(user.getUsername()).isEmpty()){
            throw new UserDoesNotExistException("User does not exists");
        }
        if(!Objects.equals(userRepo.findByUsername(user.getUsername()).get().getPassword(), user.getPassword())) {
            throw new UserIncorrectPassword("wrong password");
        }
        return UserModel.toModel(user);
    }

    public void deleteUser(Long id){
        try {
            favService.deleteFavList(id);
        }
        catch (Exception e){e.printStackTrace();}
    }





}
