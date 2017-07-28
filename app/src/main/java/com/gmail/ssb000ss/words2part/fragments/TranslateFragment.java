package com.gmail.ssb000ss.words2part.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import com.gmail.ssb000ss.words2part.Constants;
import com.gmail.ssb000ss.words2part.adapters.TranslationAdapter;
import com.gmail.ssb000ss.words2part.dao.DAOwordsImpls;
import com.gmail.ssb000ss.words2part.translate.Translation;
import com.gmail.ssb000ss.words2part.translate.TranslationGroup;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ssb000ss on 11.07.2017.
 */

public class TranslateFragment extends Fragment implements View.OnClickListener,EditText.OnFocusChangeListener {

    private DAOwordsImpls impls;


    public TranslateFragment(DAOwordsImpls impls) {
        this.impls = impls;
    }

    private EditText word;
    private TextView translate;
    private ImageButton btn_add_word, btn_clear_text, btn_add_success;
    private Animation anim_word_add;
    private ProgressBar progressBar;
    private LinearLayout lt_error_connection;
    private RecyclerView rv_translation;
    private TranslationAdapter adapter;
    private FrameLayout ft_content_layout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_translate, container, false);
        initViews(view);
        if (!isOnline()) {
            lt_error_connection.setVisibility(View.VISIBLE);
        }else lt_error_connection.setVisibility(View.GONE);
        anim_word_add = AnimationUtils.loadAnimation(getContext(), R.anim.alpha);

        TextWatcher watcher = getTextWatcher();
        word.addTextChangedListener(watcher);

        return view;
    }

    @NonNull
    private TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() > 1) {
                    showButtons(View.VISIBLE);
                    translate.setText("Подождите...");
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    showButtons(View.INVISIBLE);
                }else showButtons(View.VISIBLE);
            }

            private Timer timer = new Timer();

            @Override
            public void afterTextChanged(final Editable s) {

                timer.cancel();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                                   @Override
                                   public void run() {
                                       if (s.length() > 1) getTranslate();
                                   }
                               },
                        Constants.DELAY);

            }
        };
    }

    private void getTranslate() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                composeUrl(word.getText().toString()),
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        TranslationGroup group = new TranslationGroup(response);
                        progressBar.setVisibility(View.INVISIBLE);
                        if (group.getFirstAvailablePhrase().equals("No Translation Available")) {
                            translate.setText(getResources().getString(R.string.no_translation_available));
                        } else translate.setText(group.getFirstAvailablePhrase());
                        adapter = new TranslationAdapter(group.getTranslations());
                        rv_translation.setAdapter(adapter);
                        rv_translation.hasFixedSize();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        clearTexts();
                        translate.setText(getString(R.string.error_response));
                    }
                });
        queue.add(request);
    }


    private void initViews(View view) {
        word = (EditText) view.findViewById(R.id.et_translate_word);
        word.setOnFocusChangeListener(this);
        translate = (TextView) view.findViewById(R.id.tv_translate_translation);
        rv_translation = (RecyclerView) view.findViewById(R.id.rv_translation);

        lt_error_connection = (LinearLayout) view.findViewById(R.id.lt_connection_error);
        ft_content_layout=(FrameLayout)view.findViewById(R.id.ft_translate_content);

        btn_add_word = (ImageButton) view.findViewById(R.id.btn_translate_add_word);
        btn_clear_text = (ImageButton) view.findViewById(R.id.btn_translate_clear_text);
        btn_add_success = (ImageButton) view.findViewById(R.id.btn_translate_add_success);

        translate.setOnClickListener(this);
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
                if (!(w.isEmpty() & t.isEmpty())) {
                    impls.addWord(w, t);
                    btn_add_word.setVisibility(View.GONE);
                    //btn_add_success.setVisibility(View.VISIBLE);
                    clearTexts();
                }
                break;
            case R.id.btn_translate_clear_text:
                clearTexts();
                break;
            case R.id.tv_translate_translation:
                word.clearFocus();
                break;
        }
    }

    private void clearTexts() {
        progressBar.setVisibility(View.INVISIBLE);
        word.setText("");
        rv_translation.setAdapter(new TranslationAdapter(new ArrayList<Translation>()));
        translate.setText("");
        btn_add_success.setVisibility(View.GONE);
    }

    private void showButtons(int visibility) {
        btn_add_word.setVisibility(visibility);
        btn_clear_text.setVisibility(visibility);
    }

    private String composeUrl(String phrase) {
        return Constants.BASE_URL + phrase.toLowerCase();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        int id=v.getId();
        switch (id){
            case R.id.et_translate_word:
                ft_content_layout.clearFocus();
                break;
            case R.id.ft_translate_content:
                word.clearFocus();
                break;
        }
    }
}
