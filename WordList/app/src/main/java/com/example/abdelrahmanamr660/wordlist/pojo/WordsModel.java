package com.example.abdelrahmanamr660.wordlist.pojo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "WordsTable")
public class WordsModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "WordName")
    private String wordNAME;
    @ColumnInfo(name = "WordMeaning")
    private String wordMeaning;
    @ColumnInfo(name = "WordType")
    private String wordType;

    public WordsModel(String wordNAME, String wordMeaning, String wordType) {
        this.wordNAME = wordNAME;
        this.wordMeaning = wordMeaning;
        this.wordType = wordType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWordNAME() {
        return wordNAME;
    }

    public void setWordNAME(String wordNAME) {
        this.wordNAME = wordNAME;
    }

    public String getWordMeaning() {
        return wordMeaning;
    }

    public void setWordMeaning(String wordMeaning) {
        this.wordMeaning = wordMeaning;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }
}
