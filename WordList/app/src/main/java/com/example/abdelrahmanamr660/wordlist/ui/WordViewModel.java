package com.example.abdelrahmanamr660.wordlist.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.abdelrahmanamr660.wordlist.pojo.WordsModel;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository mWordRepository;
    private LiveData<List<WordsModel>> mAllWords;
    public WordViewModel(@NonNull Application application) {
        super(application);
        mWordRepository = new WordRepository(application);
        mAllWords = mWordRepository.getAllWords;
    }
    public void insert(WordsModel word){
        mWordRepository.insert(word);
    }
    public void update(WordsModel word){
        mWordRepository.update(word);
    }
    public void delete(WordsModel word){
        mWordRepository.delete(word);
    }
    public void deleteAllWords( ){
        mWordRepository.deleteAllWords();
    }

    public LiveData<List<WordsModel>>getAllWords( ){
       return mAllWords;
    }
}
