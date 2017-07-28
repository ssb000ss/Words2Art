package com.gmail.ssb000ss.words2part.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.gmail.ssb000ss.words2part.R;
import com.gmail.ssb000ss.words2part.Constants;
import com.gmail.ssb000ss.words2part.adapters.WordAdapter;
import com.gmail.ssb000ss.words2part.dao.DAOwordsImpls;

/**
 * Created by ssb000ss on 11.07.2017.
 */

public class DictionaryFragment extends Fragment implements WordAdapter.WordAdapterListener {

    public final String TAG = getClass().getName();


    public DictionaryFragment(Context context,DAOwordsImpls words,Switch sw, TextView tv) {
        this.words = words;
        this.sw_edit_mode=sw;
        this.tv_toolbar_edit_mode=tv;
    }

    TextView tv_is_empty;
    TextView tv_toolbar_edit_mode;
    Switch sw_edit_mode;
    LinearLayout lt_dictionary_error;
    RecyclerView recyclerView;
    WordAdapter adapter;
    DAOwordsImpls words;


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sw_edit_mode.setVisibility(View.GONE);
        sw_edit_mode.setChecked(false);
        adapter.notifyDataSetChanged();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dictionary, container, false);
        initViews(v);
        dbIsEmpty();
        sw_edit_mode.setVisibility(View.VISIBLE);
        adapter = new WordAdapter(getContext(), words.getList().getAll(), this);
        recyclerView = (RecyclerView) v.findViewById(R.id.rv_words);
        recyclerView.setAdapter(adapter);
        sw_edit_mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)tv_toolbar_edit_mode.setVisibility(View.VISIBLE);
                else tv_toolbar_edit_mode.setVisibility(View.GONE);
                adapter.setEditMode(isChecked);
                adapter.notifyDataSetChanged();
            }
        });
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
        tv_is_empty = (TextView) v.findViewById(R.id.tv_db_is_empty);
        lt_dictionary_error = (LinearLayout) v.findViewById(R.id.lt_dictionary_error);
        Typeface tf_lbl = Typeface.createFromAsset(getContext().getAssets(), Constants.Fonts.Roboto_regular);
        tv_is_empty.setTypeface(tf_lbl);
    }

    @Override
    public void WordAdapterClick(long id) {
        if (words.deleteWord(id)) {
            Log.d(getClass().getName(), "delete " + id);
            dbIsEmpty();
        }
    }
}
