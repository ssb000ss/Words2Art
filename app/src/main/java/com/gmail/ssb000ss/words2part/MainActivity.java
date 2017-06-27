package com.gmail.ssb000ss.words2part;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gmail.ssb000ss.objects.Word;
import com.gmail.ssb000ss.words2part.adapters.WordAdapter;
import com.gmail.ssb000ss.words2part.db.DBWords;
import com.gmail.ssb000ss.words2part.db.DBWordsHelper;
import com.gmail.ssb000ss.words2part.db.TestUtils;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    WordAdapter adapter;

    DBWordsHelper helper;

    SQLiteDatabase database;

    DBWords dbWords;

    Cursor cursor;

    EditText et_word;
    EditText et_translation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper =new DBWordsHelper(this);

        database=helper.getWritableDatabase();
        //Запустим один раз
        //TestUtils.insertTestWord(database);

        dbWords=new DBWords(database);
        cursor=dbWords.getAll();
        adapter=new WordAdapter(this,cursor);
        recyclerView=(RecyclerView ) findViewById(R.id.rv_words);
        layoutManager=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
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
                if(dbWords.deleteWord(id)){
                    adapter.swapCursor(dbWords.getAll());
                }
            }
        }).attachToRecyclerView(recyclerView);




    }
    public void btn_add(View view) {
        String word=et_word.getText().toString();
        String translation=et_translation.getText().toString();
        if(!(word.isEmpty())&&!(translation.isEmpty())){
            dbWords.addWord(new Word(word,translation));
            adapter.swapCursor(dbWords.getAll());
        }
    }
}
