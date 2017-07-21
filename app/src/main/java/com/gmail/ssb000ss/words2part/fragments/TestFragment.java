package com.gmail.ssb000ss.words2part.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gmail.ssb000ss.exceptions.WordException;
import com.gmail.ssb000ss.objects.Question;
import com.gmail.ssb000ss.objects.WordList;
import com.gmail.ssb000ss.utils.QuestionManager;
import com.gmail.ssb000ss.words2part.MainActivity;
import com.gmail.ssb000ss.words2part.R;
import com.gmail.ssb000ss.words2part.WordConstants;
import com.gmail.ssb000ss.words2part.dao.DAOwordsImpls;

import java.util.List;

/**
 * Created by ssb000ss on 11.07.2017.
 */

public class TestFragment extends Fragment implements View.OnClickListener {
    
    public static final String TAG="TestFragment";

    private Typeface tf_roboto_regular;
    private Typeface tf_question;
    private Typeface tf_test_error;

    private LinearLayout lt_test_error;
    private LinearLayout lt_test_result;
    private LinearLayout lt_test_testing;

    private Button btn_next;

    private TextView tv_test_empty_words;
    private TextView tv_question;
    private TextView tv_result;

    private TextView[] answers = new TextView[4];

    int count = 1;
    int correctAnswer = 0;
    int size = 0;

    QuestionManager questionManager;
    List<Question> questions;
    Question temp = new Question();
    WordList list;
    DAOwordsImpls words;

    Vibrator vibr;

    MainActivity context;
    Animation shakeanimation;
    Animation scaleanimation;


    TestFragment.TestListener TestListener;

    public interface TestListener {
        void OnClickNextButton();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TestListener = (TestFragment.TestListener) context;
        this.list = words.getList();
        Log.d(TAG, "onAttach");
    }


    public TestFragment(DAOwordsImpls words) {
        this.words = words;
    }

    public TestFragment(DAOwordsImpls words, MainActivity context) {
        this.words = words;
        this.context = context;
        Log.d(TAG, "TestFragment: Constructor ");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);

        size = list.getAll().size();
        Log.d(TAG, "onCreateView: ");
        initAnim();
        vibr = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
        initViews(view);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestListener.OnClickNextButton();
            }
        });
        setFonts();
        try {
            if ((!list.getAll().isEmpty()) && list.getAll().size() > 3) {
                lt_test_error.setVisibility(View.INVISIBLE);
                lt_test_testing.setVisibility(View.VISIBLE);
                questionManager = new QuestionManager(list);
                questions = questionManager.getQuestions();
                setQuestionItems(questions.get(0));
            } else {
                lt_test_result.setVisibility(View.INVISIBLE);
                lt_test_testing.setVisibility(View.INVISIBLE);
                lt_test_error.setVisibility(View.VISIBLE);

            }
        } catch (WordException e) {
            e.printStackTrace();
        }

        initListener();


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

    private void initAnim() {
        shakeanimation = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
        scaleanimation = AnimationUtils.loadAnimation(getContext(), R.anim.scale);
    }

    private void initListener() {
        for (int i = 0; i < 4; i++) {
            answers[i].setOnClickListener(this);
        }
    }

    private void setFonts() {
        tv_question.setTypeface(tf_question);
        answers[0].setTypeface(tf_roboto_regular);
        answers[1].setTypeface(tf_roboto_regular);
        answers[2].setTypeface(tf_roboto_regular);
        answers[3].setTypeface(tf_roboto_regular);
        tv_test_empty_words.setTypeface(tf_roboto_regular);
        tv_result.setTypeface(tf_roboto_regular);
    }

    private void initViews(View view) {
        tf_roboto_regular = Typeface.createFromAsset(getContext().getAssets(), WordConstants.Fonts.Roboto_regular);
        tf_question = Typeface.createFromAsset(getContext().getAssets(), WordConstants.Fonts.Montserrat_medium);

        tv_question = (TextView) view.findViewById(R.id.tv_question);
        tv_test_empty_words = (TextView) view.findViewById(R.id.tv_test_empty_word);
        tv_result = (TextView) view.findViewById(R.id.tv_test_result);

        btn_next = (Button) view.findViewById(R.id.btn_test_result_next);

        lt_test_error = (LinearLayout) view.findViewById(R.id.lt_test_error);
        lt_test_result = (LinearLayout) view.findViewById(R.id.lt_test_result);
        lt_test_testing = (LinearLayout) view.findViewById(R.id.lt_test_testing);

        answers[0] = (TextView) view.findViewById(R.id.tv_answer_1);
        answers[1] = (TextView) view.findViewById(R.id.tv_answer_2);
        answers[2] = (TextView) view.findViewById(R.id.tv_answer_3);
        answers[3] = (TextView) view.findViewById(R.id.tv_answer_4);
    }

    public void setQuestionItems(Question q) {
        temp = q;
        tv_question.setText(temp.getCorrectWord().getWord());
        for (int i = 0; i < 4; i++) {
            answers[i].setText(temp.getItemsList().get(i).getTranslation());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        words=null;
        list=null;
        System.gc();
        Log.d(TAG, "onDestroyView: ");
    }


    @Override
    public void onClick(View v) {
        long answerid = 0;
        switch (v.getId()) {
            case R.id.tv_answer_1:
                answerid = temp.getItemsList().get(0).getId();
                break;
            case R.id.tv_answer_2:
                answerid = temp.getItemsList().get(1).getId();
                break;
            case R.id.tv_answer_3:
                answerid = temp.getItemsList().get(2).getId();
                break;
            case R.id.tv_answer_4:
                answerid = temp.getItemsList().get(3).getId();
                break;
        }
        if (temp.getAnswer(answerid).isCorrect()) {
            tv_question.startAnimation(scaleanimation);
            correctAnswer++;
            try {
                words.upCount(temp.getCorrectWord().getId());
            } catch (WordException e) {
                e.printStackTrace();
            }
            // Toast.makeText(getContext(), "Правильно!!!", Toast.LENGTH_SHORT).show();
        } else {
            vibr.vibrate(new long[]{100L, 100L, 100L}, 2);
            tv_question.startAnimation(shakeanimation);
            // Toast.makeText(getContext(), "Не правильно!!!", Toast.LENGTH_SHORT).show();
        }

        if (count < questions.size()) {
            setQuestionItems(questions.get(count));
            count++;
        } else {
            lt_test_testing.setVisibility(View.INVISIBLE);
            lt_test_result.setVisibility(View.VISIBLE);
            tv_result.setText(correctAnswer + "/" + size);
            context.initTitleToolBar("Result");
        }

    }

}
