package com.gmail.ssb000ss.words2part.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gmail.ssb000ss.words2part.R;
import com.gmail.ssb000ss.words2part.WordConstants;
import com.gmail.ssb000ss.words2part.adapters.WordAdapter;
import com.gmail.ssb000ss.words2part.dao.DAOwordsImpls;

/**
 * Created by ssb000ss on 11.07.2017.
 */

public class DictionaryFragment extends Fragment {

    public DictionaryFragment(DAOwordsImpls words) {
        this.words = words;
    }

    TextView lbl_is_empty;
    LinearLayout lt_dictionary_error;
    RecyclerView recyclerView;
    WordAdapter adapter;
    DAOwordsImpls words;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dictionary, container, false);
        initViews(v);
        dbIsEmpty();
        adapter = new WordAdapter(getContext(), words.getList().getAll());
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_words);
        recyclerView.setAdapter(adapter);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long) viewHolder.itemView.getTag();
                if (words.deleteWord(id)) {
                    // TODO: 12.07.2017 При удалений, либо внесений изменений нужно полностью менять содержимое !!!(а то ты добавляешь а в тестах старая информация)
                    adapter.swapList(words.getList().getAll());
                    dbIsEmpty();
                }
            }
        }).attachToRecyclerView(recyclerView);
        return v;
    }

    private void dbIsEmpty() {
        if (words.getList().getAll().isEmpty()) {
            lt_dictionary_error.setVisibility(View.VISIBLE);
        } else {
            lt_dictionary_error.setVisibility(View.INVISIBLE);
        }
    }

    private void initViews(View v) {
        lbl_is_empty = (TextView) v.findViewById(R.id.tv_db_is_empty);
        lt_dictionary_error = (LinearLayout) v.findViewById(R.id.lt_dictionary_error);
        Typeface tf_lbl = Typeface.createFromAsset(getContext().getAssets(), WordConstants.Fonts.Roboto_regular);
        lbl_is_empty.setTypeface(tf_lbl);
    }
}
