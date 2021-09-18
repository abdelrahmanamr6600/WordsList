package com.example.abdelrahmanamr660.wordlist.ui;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.abdelrahmanamr660.wordlist.database.WordRoomDb;
import com.example.abdelrahmanamr660.wordlist.pojo.WordsModel;
import com.example.abdelrahmanamr660.wordlist.database.WordsDao;

import java.util.List;

public class WordRepository {
    private WordsDao mWordsDao;
    LiveData<List<WordsModel>> getAllWords;
    public WordRepository(Application app)
    {
        WordRoomDb db = WordRoomDb.getInstance(app);
        mWordsDao = db.wordsDao();
        getAllWords = mWordsDao.getAllWords();
    }
    //operation
    //insert
    public void insert(WordsModel word){
      new InsertAsyncTask(mWordsDao).execute(word);
    }
    //delete
    public void delete(WordsModel word){
        new DeleteAsyncTask(mWordsDao).execute(word);
    }
    //update
    public void update(WordsModel word){
        new UpdateAsyncTask(mWordsDao).execute(word);
    }
    //getallwords
    public LiveData<List<WordsModel>>getAllWords(){
             return getAllWords;
    }
    //delete all words
    public void deleteAllWords (){
     new DeleteAllAsyncTask(mWordsDao).execute();
    }

    private static  class InsertAsyncTask extends AsyncTask<WordsModel,Void, Void>
    {
        private WordsDao mWordsDao;

        public InsertAsyncTask(WordsDao WordsDao) {
            mWordsDao = WordsDao;
        }

        @Override
        protected Void doInBackground(WordsModel... words) {
            mWordsDao.insertWord(words[0]);
            return null;
        }
    }
    private static  class DeleteAsyncTask extends AsyncTask<WordsModel,Void, Void>
    {
        private WordsDao mWordsDao;

        public DeleteAsyncTask(WordsDao WordsDao) {
            mWordsDao = WordsDao;
        }

        @Override
        protected Void doInBackground(WordsModel... words) {
            mWordsDao.deleteWord(words[0]);
            return null;
        }
    }
    private static  class UpdateAsyncTask extends AsyncTask<WordsModel,Void, Void>
    {
        private WordsDao mWordsDao;

        public UpdateAsyncTask(WordsDao WordsDao) {
            mWordsDao = WordsDao;
        }

        @Override
        protected Void doInBackground(WordsModel... words) {
            mWordsDao.updateWord(words[0]);
            return null;
        }
    }

    private static  class DeleteAllAsyncTask extends AsyncTask<Void,Void, Void>
    {
        private WordsDao mWordsDao;

        public DeleteAllAsyncTask(WordsDao WordsDao) {
            mWordsDao = WordsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mWordsDao.deleteAllWords();
            return null;
        }
    }


}
