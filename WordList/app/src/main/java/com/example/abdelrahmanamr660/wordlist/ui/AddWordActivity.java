package com.example.abdelrahmanamr660.wordlist.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.abdelrahmanamr660.wordlist.R;
import com.example.abdelrahmanamr660.wordlist.pojo.WordsModel;
import com.example.abdelrahmanamr660.wordlist.databinding.ActivityAddNoteBinding;

public class AddWordActivity extends AppCompatActivity {
    private ActivityAddNoteBinding mActivityAddNoteBinding ;
    private AddNewWordViewModel mAddNewWordViewModel;
    public static final String Extra_id = "ExtraId";
    public static final String Extra_word = "ExtraWord";

    public static final String Extra_wordMeaning = "ExtraMeaning";
    public static final String Extra_wordType = "ExtraType";

    private   boolean mEditMode ;
    private int mId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAddNoteBinding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(mActivityAddNoteBinding.getRoot());
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        mAddNewWordViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(AddNewWordViewModel.class);

        Intent intent = getIntent();
        if (intent.hasExtra(Extra_id))
        {
            //update
            setTitle("Edit Word");
            mEditMode = true;
            mId = intent.getIntExtra(Extra_id,-1);
            mActivityAddNoteBinding.editTextWord.setText(intent.getStringExtra(Extra_word));
            mActivityAddNoteBinding.editTextWordMeaning.setText(intent.getStringExtra(Extra_wordMeaning));
            mActivityAddNoteBinding.editTextTextWordType.setText(intent.getStringExtra(Extra_wordType));
        }
        else
        {
            //Insert
            setTitle("Add New Word");
            mEditMode = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId())
      {
          case R.id.save_menu:
              saveWord();
              return true;
          default:
              return super.onOptionsItemSelected(item);
      }
    }

    private void saveWord() {
        String word = mActivityAddNoteBinding.editTextWord.getText().toString().trim();
        String wordMeaning = mActivityAddNoteBinding.editTextWordMeaning.getText().toString();
        String wordType = mActivityAddNoteBinding.editTextTextWordType.getText().toString();
        WordsModel words = new WordsModel(word,wordMeaning,wordType);
        if (word.isEmpty()||wordMeaning.isEmpty()||wordType.isEmpty())
        {
            Toast.makeText(this,"Please fill All Fields",Toast.LENGTH_SHORT).show();
        }
        if (mEditMode)
        {
            words.setId(mId);
            mAddNewWordViewModel.update(words);

        }
        else {
            mAddNewWordViewModel.insert(words);

        }
        finish();

    }
}