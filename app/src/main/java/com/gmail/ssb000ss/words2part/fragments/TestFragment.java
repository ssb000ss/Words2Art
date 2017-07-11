package com.gmail.ssb000ss.words2part.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidpagecontrol.PageControl;
import com.gmail.ssb000ss.words2part.R;
import com.gmail.ssb000ss.words2part.adapters.SamplePagerAdapter;

/**
 * Created by ssb000ss on 11.07.2017.
 */

public class TestFragment extends Fragment {
    Button textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_test,container,false);

        textView=(Button) view.findViewById(R.id.tv_answer_1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Вариант 1",Toast.LENGTH_SHORT).show();
            }
        });

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
}
