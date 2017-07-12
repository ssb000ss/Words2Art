package com.gmail.ssb000ss.words2part.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.ssb000ss.exceptions.WordException;
import com.gmail.ssb000ss.objects.Question;
import com.gmail.ssb000ss.objects.WordList;
import com.gmail.ssb000ss.utils.QuestionManager;
import com.gmail.ssb000ss.words2part.R;
import com.gmail.ssb000ss.words2part.WordConstants;
import com.gmail.ssb000ss.words2part.dao.DAOwords;
import com.gmail.ssb000ss.words2part.dao.DAOwordsImpls;

import java.util.List;

/**
 * Created by ssb000ss on 11.07.2017.
 */

public class TestFragment extends Fragment implements View.OnClickListener {
//// TODO: 12.07.2017 Баг с обновлением вопросов
    private Typeface tf_answers;
    private Typeface tf_question;
    private Typeface tv_test_error;
    private LinearLayout lt_test_error;

    private TextView tv_test;
    private TextView tv_question;

    private Button[] answers = new Button[4];

    int count = 1;
    QuestionManager questionManager;
    List<Question> questions;
    Question temp = new Question();
    WordList list;
    DAOwordsImpls words;

    public TestFragment(DAOwordsImpls words) {
        this.words = words;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        this.list = this.words.getList();
        initViews(view);
        setFonts();
        try {
            if ((!list.getAll().isEmpty())&&list.getAll().size() > 3) {
                lt_test_error.setVisibility(View.INVISIBLE);
                questionManager = new QuestionManager(list);
                questions = questionManager.getQuestions();
                setQuestionItems(questions.get(0));
            } else {
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

    private void initListener() {
        for (int i = 0; i < 4; i++) {
            answers[i].setOnClickListener(this);
        }
    }

    private void setFonts() {
        tv_question.setTypeface(tf_question);
        answers[0].setTypeface(tf_answers);
        answers[1].setTypeface(tf_answers);
        answers[2].setTypeface(tf_answers);
        answers[3].setTypeface(tf_answers);
        tv_test.setTypeface(tv_test_error);
    }

    private void initViews(View view) {
        tf_answers = Typeface.createFromAsset(getContext().getAssets(), WordConstants.Fonts.Roboto_regular);
        tf_question = Typeface.createFromAsset(getContext().getAssets(), WordConstants.Fonts.Montserrat_medium);
        tv_test_error = Typeface.createFromAsset(getContext().getAssets(), WordConstants.Fonts.Roboto_black);
        tv_question = (TextView) view.findViewById(R.id.tv_question);
        tv_test = (TextView) view.findViewById(R.id.tv_test);
        lt_test_error=(LinearLayout)view.findViewById(R.id.lt_test_error);
        answers[0] = (Button) view.findViewById(R.id.tv_answer_1);
        answers[1] = (Button) view.findViewById(R.id.tv_answer_2);
        answers[2] = (Button) view.findViewById(R.id.tv_answer_3);
        answers[3] = (Button) view.findViewById(R.id.tv_answer_4);
    }

    public void setQuestionItems(Question q) {
        temp = q;
        tv_question.setText(temp.getCorrectWord().getWord());
        for (int i = 0; i < 4; i++) {
            answers[i].setText(temp.getItemsList().get(i).getTranslation());
        }
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
            Toast.makeText(getContext(), "Правильно!!!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Не правильно!!!", Toast.LENGTH_SHORT).show();
        }

        if (count < questions.size()) {
            setQuestionItems(questions.get(count));
            count++;
        } else {
            //todo Что должно быть по окончанию теста?, может быть результаты показать или что то подобное
            Toast.makeText(getContext(), "Тест окончен!!!", Toast.LENGTH_LONG).show();
        }

    }
}
