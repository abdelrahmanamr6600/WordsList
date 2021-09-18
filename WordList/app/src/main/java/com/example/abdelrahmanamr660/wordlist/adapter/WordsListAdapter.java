package com.example.abdelrahmanamr660.wordlist.adapter;

import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abdelrahmanamr660.wordlist.databinding.WordListItemBinding;
import com.example.abdelrahmanamr660.wordlist.pojo.WordsModel;

import java.util.ArrayList;
import java.util.List;

public class WordsListAdapter extends RecyclerView.Adapter<WordsListAdapter.ViewHolder> {
 private List<WordsModel> mWordList = new ArrayList<>();
  private OnItemClickListener mListener ;
  public int index;
  TextToSpeech speech;



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
             WordListItemBinding wordListItemBinding =    WordListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
                return new ViewHolder(wordListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        WordsModel currentWords = mWordList.get(position);
          holder.wordListItemBinding.tvEnglishWord.setText(currentWords.getWordNAME());
          holder.wordListItemBinding.tvMeaningWord.setText(currentWords.getWordMeaning());
          holder.wordListItemBinding.tvWordType.setText(currentWords.getWordType());

    }

    public void addList(List<WordsModel> words)
    {
        this.mWordList = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
      WordListItemBinding wordListItemBinding;
        public ViewHolder(@NonNull WordListItemBinding wordListItemBinding) {
            super(wordListItemBinding.getRoot());
            this.wordListItemBinding = wordListItemBinding;
            wordListItemBinding.tvEnglishWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     index = getBindingAdapterPosition();
                    if (mListener!=null&& index!=RecyclerView.NO_POSITION)
                    {
                        mListener.OnItemClick(mWordList.get(index));
                    }
                }
            });

            wordListItemBinding.listen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    index = getBindingAdapterPosition();
                    if (mListener!=null&& index!=RecyclerView.NO_POSITION)
                    {
                        mListener.OnImageClick(mWordList.get(index));
                    }
                }
            });


        }
    }

    public interface OnItemClickListener{
        void OnItemClick(WordsModel words);
        void OnImageClick(WordsModel words);

    }
    public void OnItemClickListener(OnItemClickListener onItemClickListener){
        mListener = onItemClickListener;

    }

    public WordsModel words (int pos){
        return mWordList.get(pos);
    }

}
