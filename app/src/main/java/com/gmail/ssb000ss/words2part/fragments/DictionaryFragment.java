package com.gmail.ssb000ss.words2part.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.ssb000ss.words2part.R;
import com.gmail.ssb000ss.words2part.adapters.WordAdapter;
import com.gmail.ssb000ss.words2part.dao.DAOwordsImpls;

/**
 * Created by ssb000ss on 11.07.2017.
 */

public class DictionaryFragment extends Fragment {

    public DictionaryFragment(DAOwordsImpls words) {
        this.words = words;
    }

    RecyclerView recyclerView;
    WordAdapter adapter;
    DAOwordsImpls words;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dictionary, container, false);

        adapter=new WordAdapter(getContext(),words.getList().getAll());
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_words);
        recyclerView.setAdapter(adapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id= (long) viewHolder.itemView.getTag();
                if(words.deleteWord(id)){
                    adapter.swapList(words.getList().getAll());
                }
            }
        }).attachToRecyclerView(recyclerView);
        return v;
    }
}
