package com.gmail.ssb000ss.words2part.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.gmail.ssb000ss.words2part.R;
import com.gmail.ssb000ss.words2part.dao.DAOwordsImpls;

/**
 * Created by ssb000ss on 11.07.2017.
 */

public class TranslateFragment extends Fragment {

    DAOwordsImpls impls;

    public TranslateFragment(DAOwordsImpls impls) {
        this.impls = impls;
    }

    EditText word, translate;
    Button btn_add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_translate, container, false);
        word = (EditText) view.findViewById(R.id.et_word);
        translate = (EditText) view.findViewById(R.id.et_translate);

        btn_add = (Button) view.findViewById(R.id.btn_add_word);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                impls.addWord(word.getText().toString(), translate.getText().toString());
                word.setText("");
                translate.setText("");
            }
        });
        return view;
    }
}
