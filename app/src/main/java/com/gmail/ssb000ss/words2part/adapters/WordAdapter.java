package com.gmail.ssb000ss.words2part.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.ssb000ss.objects.Word;
import com.gmail.ssb000ss.words2part.R;
import com.gmail.ssb000ss.words2part.Constants;
import com.gmail.ssb000ss.words2part.fragments.DictionaryFragment;

import java.util.List;

/**
 * Created by ssb000ss on 03.07.2017.
 */

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private Context context;
    private List<Word> list;
    private WordAdapterListener adapterListener;
    private boolean isChecked=false;

    public WordAdapter(Context context, List<Word> list, DictionaryFragment dictionaryFragment) {
        this.context = context;
        this.list = list;
        adapterListener= dictionaryFragment;

    }


    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.word_item, parent, false);
        return new WordViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        Word tempWord = list.get(position);
        holder.tv_word.setText(tempWord.getWord());
        holder.tv_translation.setText(tempWord.getTranslation());
        holder.tv_statistic.setText(tempWord.getStatistic() * 100 / Constants.MEMORIZATION_LEVEL + "%");
        holder.itemView.setTag(tempWord.getId());
        setEditMode(isChecked,holder);
    }

    public void setEditMode(boolean b,WordViewHolder holder){
        if(b){
            holder.btn_item_remove.setVisibility(View.VISIBLE);
            holder.tv_statistic.setVisibility(View.INVISIBLE);
        }else {
            holder.btn_item_remove.setVisibility(View.INVISIBLE);
            holder.tv_statistic.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void deleteItem(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }
    
    public void swapList(List<Word> newlist) {
        list = newlist;
        if (list != null) {
            this.notifyDataSetChanged();
            Toast.makeText(context,""+list.size(),Toast.LENGTH_SHORT).show();
        }
    }

    public void setEditMode(boolean isChecked) {
        this.isChecked=isChecked;
    }

    public  interface WordAdapterListener{
        void WordAdapterClick(long id);
    }

    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_word;
        TextView tv_translation;
        TextView tv_statistic;
        ImageButton btn_item_remove;

        public WordViewHolder(View itemView) {
            super(itemView);
            tv_translation = (TextView) itemView.findViewById(R.id.tv_item_translate);
            tv_word = (TextView) itemView.findViewById(R.id.tv_item_word);
            tv_statistic = (TextView) itemView.findViewById(R.id.tv_statistic);
            btn_item_remove=(ImageButton)itemView.findViewById(R.id.btn_item_remove);
            btn_item_remove.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            long id=list.get(position).getId();
            deleteItem(position);
            adapterListener.WordAdapterClick(id);
        }
       
    }
}
