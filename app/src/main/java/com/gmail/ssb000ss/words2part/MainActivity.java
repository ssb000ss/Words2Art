package com.gmail.ssb000ss.words2part;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;

import com.gmail.ssb000ss.objects.Word;
import com.gmail.ssb000ss.objects.WordList;
import com.gmail.ssb000ss.words2part.adapters.WordAdapter;
import com.gmail.ssb000ss.words2part.adapters.WordAdapterCursor;
import com.gmail.ssb000ss.words2part.dao.DAOwords;
import com.gmail.ssb000ss.words2part.dao.DAOwordsImpls;
import com.gmail.ssb000ss.words2part.db.DBWords;
import com.gmail.ssb000ss.words2part.db.TestUtils;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    WordAdapter adapter;

    EditText et_word;
    EditText et_translation;

    DAOwordsImpls words;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Запустим один раз

        words=new DAOwordsImpls(this);
        //TestUtils.insertTestWord(words.getDatabase());
        adapter=new WordAdapter(this,words.getList().getAll());
        recyclerView=(RecyclerView ) findViewById(R.id.rv_words);


        recyclerView.setAdapter(adapter);

        et_translation=(EditText) findViewById(R.id.et_translate);
        et_word=(EditText) findViewById(R.id.et_word);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                long id= (long) viewHolder.itemView.getTag();
                if(words.deleteWord(id)){
                    adapter.swapList(words.getList().getAll());
                }
            }
        }).attachToRecyclerView(recyclerView);
    }
    public void btn_add(View view) {
        String word=et_word.getText().toString();
        String translation=et_translation.getText().toString();
        if(!(word.isEmpty())&&!(translation.isEmpty())){
            words.addWord(word,translation);
            adapter.swapList(words.getList().getAll());
            et_translation.setText("");
            et_word.setText("");
        }
    }

    public void GoToTest(View view) {
        Intent intent=new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);
    }
}
