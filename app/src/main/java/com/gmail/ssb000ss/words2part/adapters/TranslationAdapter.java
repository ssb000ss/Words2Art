package com.gmail.ssb000ss.words2part.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.ssb000ss.words2part.R;
import com.gmail.ssb000ss.words2part.translate.Meaning;
import com.gmail.ssb000ss.words2part.translate.Translation;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssb000ss on 27.07.2017.
 */

@SuppressWarnings("ALL")
public class TranslationAdapter extends RecyclerView.Adapter<TranslationAdapter.TranslationViewHolder>{

    private List<Translation> list=new ArrayList<>();

    public TranslationAdapter(List<Translation> list) {
        this.list = list;
    }

    public TranslationAdapter(ArrayList<Translation> objects) {
        list=objects;
    }

    @Override
    public TranslationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meaning_item, parent, false);
        return new TranslationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TranslationViewHolder holder, int position) {
        Translation temp=list.get(position);
        holder.tv_phrase.setText(temp.getPhrase().toString());
        for (Meaning o:temp.getMeanings()) {
            holder.tv_meanings.setText('\t'+  o.getText().toString()+'\n');
            }
        }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TranslationViewHolder extends RecyclerView.ViewHolder {
        TextView tv_phrase;
        TextView tv_meanings;
        public TranslationViewHolder(View itemView) {
            super(itemView);
            tv_phrase= (TextView) itemView.findViewById(R.id.tv_translation_phrase);
            tv_meanings= (TextView) itemView.findViewById(R.id.tv_translation_meaning);
        }
    }
}
