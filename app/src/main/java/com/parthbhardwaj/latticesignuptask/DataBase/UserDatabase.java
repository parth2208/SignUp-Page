package com.parthbhardwaj.latticesignuptask.DataBase;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = UserTable.class, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {


    public abstract UserDao userDao();

}
