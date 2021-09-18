package com.example.abdelrahmanamr660.wordlist.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;

import com.example.abdelrahmanamr660.wordlist.R;
import com.example.abdelrahmanamr660.wordlist.adapter.RecyclerViewTouchHelper;
import com.example.abdelrahmanamr660.wordlist.pojo.WordsModel;
import com.example.abdelrahmanamr660.wordlist.adapter.WordsListAdapter;
import com.example.abdelrahmanamr660.wordlist.databinding.ActivityMainBinding;
import com.example.abdelrahmanamr660.wordlist.databinding.WordListItemBinding;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
  private WordViewModel mWordViewModel ;
  ActivityMainBinding mActivityMainBinding;
  private WordsListAdapter wordsListAdapter ;
  RecyclerView recyclerView ;
  Context context = MainActivity.this;
 TextToSpeech speech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mActivityMainBinding.getRoot());
        recyclerView = findViewById(R.id.recyclerView_words);
        WordListItemBinding binding = WordListItemBinding.inflate(getLayoutInflater());

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
                , new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();

                        }
                    }
                });

        speech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i==TextToSpeech.SUCCESS){
                    int result = speech.setLanguage(Locale.ENGLISH);
                    if (i==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        Log.e("TTS","Language not support");
                    }else {binding.listen.setEnabled(true);}
                }
            }
        });

        mActivityMainBinding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddWordActivity.class);
                activityResultLauncher.launch(intent);
            }
        });
        mActivityMainBinding.recyclerViewWords.setLayoutManager(new LinearLayoutManager(this));
        mActivityMainBinding.recyclerViewWords.setHasFixedSize(true);
        wordsListAdapter = new WordsListAdapter();
        mActivityMainBinding.recyclerViewWords.setAdapter(wordsListAdapter);

        wordsListAdapter.OnItemClickListener(new WordsListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(WordsModel words) {
                Intent i = new Intent(MainActivity.this, AddWordActivity.class);
                i.putExtra(AddWordActivity.Extra_id, words.getId());
                i.putExtra(AddWordActivity.Extra_word, words.getWordNAME());
                i.putExtra(AddWordActivity.Extra_wordMeaning, words.getWordMeaning());
                i.putExtra(AddWordActivity.Extra_wordType, words.getWordType());
                startActivity(i);
            }


            @Override
            public void OnImageClick(WordsModel words) {
                String word = words.getWordNAME();
                speech.setSpeechRate(0.10f);
                speech.speak(word,TextToSpeech.QUEUE_FLUSH,null);

            }
        });

        mWordViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, new Observer<List<WordsModel>>() {
            @Override
            public void onChanged(List<WordsModel> words) {
                wordsListAdapter.addList(words);

            }
        });


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerViewTouchHelper(wordsListAdapter,context));
        itemTouchHelper.attachToRecyclerView(mActivityMainBinding.recyclerViewWords);
    }

}