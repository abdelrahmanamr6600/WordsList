package com.example.abdelrahmanamr660.wordlist.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.abdelrahmanamr660.wordlist.pojo.WordsModel;

@Database(entities = WordsModel.class,version = 1)
public abstract class WordRoomDb extends RoomDatabase {
  private static WordRoomDb instance;
  public abstract WordsDao wordsDao();

  public static synchronized WordRoomDb getInstance(Context context)
  {
      if (instance == null)
      {
          instance = Room.databaseBuilder(context.getApplicationContext(),WordRoomDb.class,"word-database")
                  .fallbackToDestructiveMigration()
                  .addCallback(roomDatabaseCallback)
                  .build();

      }
      return instance;
  }
  private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback()
  {
      @Override
      public void onCreate(@NonNull SupportSQLiteDatabase db) {
          new PopulateData(instance).execute();
          super.onCreate(db);
      }

      @Override
      public void onOpen(@NonNull SupportSQLiteDatabase db) {
          super.onOpen(db);
      }
  };

  private static class PopulateData extends AsyncTask<Void , Void,Void>
  {
       private WordsDao mWordsDao ;
      public PopulateData(WordRoomDb db) {
          mWordsDao = db.wordsDao();
      }
      @Override
      protected Void doInBackground(Void... voids) {
          mWordsDao.insertWord(new WordsModel("Book","Book","noun"));

          return null;
      }
  }
}
