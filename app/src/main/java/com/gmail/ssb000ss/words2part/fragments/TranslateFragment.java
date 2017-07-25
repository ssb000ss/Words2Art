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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gmail.ssb000ss.words2part.R;
import com.gmail.ssb000ss.words2part.WordConstants;
import com.gmail.ssb000ss.words2part.dao.DAOwordsImpls;
import com.gmail.ssb000ss.words2part.translate.TranslationGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ssb000ss on 11.07.2017.
 */

public class TranslateFragment extends Fragment implements View.OnClickListener {

    private DAOwordsImpls impls;


    public TranslateFragment(DAOwordsImpls impls) {
        this.impls = impls;
    }

    private EditText word;
    private TextView translate;
    private ImageButton btn_add_word, btn_clear_text, btn_done;
    private Animation anim_word_add;
    private ProgressBar progressBar;
    private LinearLayout lt_error_connection;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_translate, container, false);
        initViews(view);

        anim_word_add = AnimationUtils.loadAnimation(getContext(), R.anim.word_add);

        TextWatcher watcher = getTextWatcher();
        word.addTextChangedListener(watcher);

        return view;
    }

    @NonNull
    private TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() >= 1) showButtons(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    showButtons(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 1) {
                    RequestQueue queue = Volley.newRequestQueue(getContext());
                    progressBar.setVisibility(View.VISIBLE);

                    JsonObjectRequest request = new JsonObjectRequest(
                            Request.Method.GET,
                            composeUrl(word.getText().toString()),
                            null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    TranslationGroup group=new TranslationGroup(response);
                                    translate.setText(group.getFirstAvailablePhrase());
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    lt_error_connection.setVisibility(View.VISIBLE);
                                }
                            });
                    queue.add(request);
                }
            }
        };
    }

    private void initViews(View view) {
        word = (EditText) view.findViewById(R.id.et_translate_word);
        translate = (TextView) view.findViewById(R.id.tv_translate_translation);

        lt_error_connection = (LinearLayout) view.findViewById(R.id.lt_connection_error);

        btn_add_word = (ImageButton) view.findViewById(R.id.btn_translate_add_word);
        btn_clear_text = (ImageButton) view.findViewById(R.id.btn_translate_clear_text);

        btn_clear_text.setOnClickListener(this);
        btn_add_word.setOnClickListener(this);
        progressBar = (ProgressBar) view.findViewById(R.id.pb_translation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_translate_add_word:
                String w = word.getText().toString();
                String t = translate.getText().toString();
                if (!(w.isEmpty() && t.isEmpty())) {
                    impls.addWord(w, t);
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

    private void showButtons(int visibility) {
        btn_add_word.setVisibility(visibility);
        btn_clear_text.setVisibility(visibility);
    }

    private String composeUrl(String phrase) {
        return WordConstants.BASE_URL + phrase.toLowerCase();
    }
}
