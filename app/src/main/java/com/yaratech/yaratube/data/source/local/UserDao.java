package com.yaratech.yaratube.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.yaratech.yaratube.data.model.User;

@Dao
public interface UserDao {
    @Insert
    void insertUserToDb(User user);

    @Query("SELECT token from user")
    String getUserTokenFromDatabase();
}
