package com.example.abdelrahmanamr660.wordlist.adapter;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abdelrahmanamr660.wordlist.pojo.WordsModel;
import com.example.abdelrahmanamr660.wordlist.ui.WordViewModel;


public class RecyclerViewTouchHelper extends ItemTouchHelper.SimpleCallback {
   private WordsListAdapter adapter;
   WordViewModel mWordViewModel;
   Context context;
   Application application;


    public RecyclerViewTouchHelper(WordsListAdapter adapter , Context context ) {
        super(0,  ItemTouchHelper.RIGHT);
        this.adapter = adapter;
        this.context = context;

    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

         mWordViewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(WordViewModel.class);

        int i  = viewHolder.getBindingAdapterPosition();
        WordsModel w = adapter.words(i);
     mWordViewModel.delete(w);

    }

    }

