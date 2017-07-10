package com.gmail.ssb000ss.words2part;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.ssb000ss.exceptions.WordException;
import com.gmail.ssb000ss.objects.Answer;
import com.gmail.ssb000ss.objects.Question;
import com.gmail.ssb000ss.utils.QuestionManager;
import com.gmail.ssb000ss.words2part.dao.DAOwordsImpls;

import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView tv_question;
    Button btn_next,btn_variant_1,btn_variant_2,btn_variant_3,btn_variant_4;
    int count=1;
    QuestionManager questionManager;
    List<Question> questions;
    Question temp=new Question();

    DAOwordsImpls words;
//// TODO: 10.07.2017 Решить проблему отправки Wordlist в другую активность
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        words=new DAOwordsImpls(this);
        try {
            questionManager=new QuestionManager(words.getList());
            questions=questionManager.getQuestions();
        } catch (WordException e) {
            e.printStackTrace();
        }

        tv_question=(TextView)findViewById(R.id.tv_question);

        btn_next=(Button) findViewById(R.id.btn_next);
        btn_variant_1=(Button) findViewById(R.id.btn_variant_1);
        btn_variant_2=(Button) findViewById(R.id.btn_variant_2);
        btn_variant_3=(Button) findViewById(R.id.btn_variant_3);
        btn_variant_4=(Button) findViewById(R.id.btn_variant_4);
        btn_variant_1.setOnClickListener(this);
        btn_variant_2.setOnClickListener(this);
        btn_variant_3.setOnClickListener(this);
        btn_variant_4.setOnClickListener(this);

        setQuestionItems(questions.get(0));
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setQuestionItems(Question q){
        temp=q;
        tv_question.setText(temp.getCorrectWord().getWord());
        btn_variant_1.setText(temp.getItemsList().get(0).getId()+" " +temp.getItemsList().get(0).getTranslation());
        btn_variant_2.setText(temp.getItemsList().get(1).getId()+" " +temp.getItemsList().get(1).getTranslation());
        btn_variant_3.setText(temp.getItemsList().get(2).getId()+" " +temp.getItemsList().get(2).getTranslation());
        btn_variant_4.setText(temp.getItemsList().get(3).getId()+" " +temp.getItemsList().get(3).getTranslation());
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        long answerid=0;
        switch (id){
            case R.id.btn_variant_1:
                answerid=temp.getItemsList().get(0).getId();
                break;
            case R.id.btn_variant_2:
                answerid=temp.getItemsList().get(1).getId();
                break;
            case R.id.btn_variant_3:
                answerid=temp.getItemsList().get(2).getId();
                break;
            case R.id.btn_variant_4:
                answerid=temp.getItemsList().get(3).getId();
                break;
        }
        if(temp.getAnswer(answerid).isCorrect()){
            Toast.makeText(SecondActivity.this, "Правильно!!!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(SecondActivity.this, "Не правильно!!!", Toast.LENGTH_SHORT).show();
        }

        if (count < questions.size()) {
            setQuestionItems(questions.get(count));
            count++;
        } else {
            Toast.makeText(SecondActivity.this, "Тест окончен!!!", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(SecondActivity.this,MainActivity.class);
            startActivity(intent);
        }

    }
}
