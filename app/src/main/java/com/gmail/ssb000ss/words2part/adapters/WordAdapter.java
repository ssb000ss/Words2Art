package com.gmail.ssb000ss.words2part.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.ssb000ss.objects.Word;
import com.gmail.ssb000ss.words2part.R;

import java.util.List;

/**
 * Created by ssb000ss on 03.07.2017.
 */

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder>{
    Context context;
    List<Word> list;

    public WordAdapter(Context context, List<Word> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.word_item, parent, false);
        return new WordViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Word tempWord=list.get(position);
        holder.tv_word.setText(tempWord.getWord());
        holder.tv_translation.setText(tempWord.getTranslation());
        holder.itemView.setTag(tempWord.getId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void swapList(List<Word> newlist){
        list=newlist;
        if (list != null) {
            this.notifyDataSetChanged();
        }
    }

    public class WordViewHolder extends RecyclerView.ViewHolder {
        TextView tv_word;
        TextView tv_translation;

        public WordViewHolder(View itemView) {
            super(itemView);
            tv_translation = (TextView) itemView.findViewById(R.id.tv_item_translate);
            tv_word = (TextView) itemView.findViewById(R.id.tv_item_word);
        }
    }
}
