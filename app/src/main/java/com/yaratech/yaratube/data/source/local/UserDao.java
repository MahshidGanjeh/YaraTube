package com.yaratech.yaratube.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.yaratech.yaratube.data.model.User;

@Dao
public interface UserDao {

    @Insert
    void insertUserToDb(User user);

    @Update
    void updateUser(User user);

    @Query("SELECT * from user")
    User getUser();

    @Query("SELECT token from user")
    String getUserTokenFromDatabase();

    @Query("SELECT phoneNumber from user")
    String getUserPhoneNumberFromDb();


}
