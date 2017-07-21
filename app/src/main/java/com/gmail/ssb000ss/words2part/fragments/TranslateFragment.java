package com.gmail.ssb000ss.words2part.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gmail.ssb000ss.words2part.R;
import com.gmail.ssb000ss.words2part.dao.DAOwordsImpls;

/**
 * Created by ssb000ss on 11.07.2017.
 */

public class TranslateFragment extends Fragment implements View.OnClickListener{

    DAOwordsImpls impls;

    public TranslateFragment(DAOwordsImpls impls) {
        this.impls = impls;
    }

    EditText word;
    TextView translate;
    ImageButton btn_add_word,btn_clear_text;

    Animation anim_word_add;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_translate, container, false);
        initViews(view);

        anim_word_add= AnimationUtils.loadAnimation(getContext(), R.anim.word_add);

        TextWatcher watcher = getTextWatcher();
        word.addTextChangedListener(watcher);

        return view;
    }

    @NonNull
    private TextWatcher getTextWatcher() {
        return new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    showButtons(View.VISIBLE);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length()==0){
                        showButtons(View.INVISIBLE);
                    }else translate.setText(s);
                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            };
    }

    private void initViews(View view) {
        word=(EditText)view.findViewById(R.id.et_translate_word);
        translate=(TextView) view.findViewById(R.id.tv_translate_translation);

        btn_add_word=(ImageButton)view.findViewById(R.id.btn_translate_add_word);
        btn_clear_text= (ImageButton) view.findViewById(R.id.btn_translate_clear_text);

        btn_clear_text.setOnClickListener(this);
        btn_add_word.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_translate_add_word:
                String w=word.getText().toString();
                String t=translate.getText().toString();
                if (!(w.isEmpty()&&t.isEmpty())){
                    impls.addWord(w,t);
                    word.startAnimation(anim_word_add);
                    clearTexts();
                }
                break;
            case R.id.btn_translate_clear_text:
                clearTexts();
                break;
        }
    }

    private void clearTexts() {
        word.setText("");
        translate.setText("");
    }

    private void showButtons(int visibility){
        btn_add_word.setVisibility(visibility);
        btn_clear_text.setVisibility(visibility);
    }
}
