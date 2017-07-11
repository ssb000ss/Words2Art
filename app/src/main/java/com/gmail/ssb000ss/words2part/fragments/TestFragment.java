package com.gmail.ssb000ss.words2part.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.ssb000ss.words2part.R;
import com.gmail.ssb000ss.words2part.WordConstants;

/**
 * Created by ssb000ss on 11.07.2017.
 */

public class TestFragment extends Fragment implements View.OnClickListener{

    private Typeface tf_answers;
    private Typeface tf_question;
    private TextView tv_question;
    private TextView []answers=new TextView[4];
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_test,container,false);

        initViews(view);
        setFonts();




//todo После реализации всех плюшек, приступим к pagecontrol
//        SamplePagerAdapter adapter = new SamplePagerAdapter(getContext());
//        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
//        viewPager.setAdapter(adapter);
//
//        final PageControl pageControl = (PageControl) view.findViewById(R.id.page_control);
//        pageControl.setViewPager(viewPager);
//        pageControl.setPosition(1);

        return view;
    }

    private void setFonts() {
        tv_question.setTypeface(tf_question);
        answers[0].setTypeface(tf_answers);
        answers[1].setTypeface(tf_answers);
        answers[2].setTypeface(tf_answers);
        answers[3].setTypeface(tf_answers);
    }

    private void initViews(View view) {
        tf_answers = Typeface.createFromAsset(getContext().getAssets(), WordConstants.Fonts.Roboto_regular);
        tf_question = Typeface.createFromAsset(getContext().getAssets(), WordConstants.Fonts.Montserrat_medium);
        tv_question=(TextView) view.findViewById(R.id.tv_question);
        answers[0]=(TextView) view.findViewById(R.id.tv_answer_1);
        answers[1]=(TextView) view.findViewById(R.id.tv_answer_1);
        answers[2]=(TextView) view.findViewById(R.id.tv_answer_1);
        answers[3]=(TextView) view.findViewById(R.id.tv_answer_1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_answer_1:
                break;
            case R.id.tv_answer_2:
                break;
            case R.id.tv_answer_3:
                break;
            case R.id.tv_answer_4:
                break;
        }
    }
}
