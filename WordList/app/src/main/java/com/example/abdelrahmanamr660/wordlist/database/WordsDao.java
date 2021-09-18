package com.example.abdelrahmanamr660.wordlist.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.abdelrahmanamr660.wordlist.pojo.WordsModel;

import java.util.List;

@Dao
public interface WordsDao {
    @Insert
    void insertWord(WordsModel word);
    @Update
    void updateWord(WordsModel word);
    @Delete
    void deleteWord(WordsModel word);
    @Query("DELETE FROM WordsModel")
    void deleteAllWords();
    @Query("SELECT * FROM WordsModel")
     LiveData<List<WordsModel>> getAllWords();
}
