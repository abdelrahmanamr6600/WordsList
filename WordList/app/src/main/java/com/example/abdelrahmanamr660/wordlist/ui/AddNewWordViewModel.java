package com.example.abdelrahmanamr660.wordlist.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.abdelrahmanamr660.wordlist.pojo.WordsModel;

public class AddNewWordViewModel extends AndroidViewModel {
    private WordRepository mWordRepository;

    public AddNewWordViewModel(@NonNull Application application) {
        super(application);
        mWordRepository = new WordRepository(application);

    }
    public void insert(WordsModel word){
        mWordRepository.insert(word);
    }
    public void update(WordsModel word){
        mWordRepository.update(word);
    }



}
