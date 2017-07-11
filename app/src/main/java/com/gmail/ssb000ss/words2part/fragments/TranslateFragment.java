package com.gmail.ssb000ss.words2part.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmail.ssb000ss.words2part.R;

/**
 * Created by ssb000ss on 11.07.2017.
 */

public class TranslateFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_translate,container,false);
        return view;
    }
}
