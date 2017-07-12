package com.gmail.ssb000ss.words2part;

import android.app.ActionBar;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.gmail.ssb000ss.words2part.dao.DAOwordsImpls;
import com.gmail.ssb000ss.words2part.db.TestUtils;
import com.gmail.ssb000ss.words2part.fragments.DictionaryFragment;
import com.gmail.ssb000ss.words2part.fragments.TestFragment;
import com.gmail.ssb000ss.words2part.fragments.TranslateFragment;

public class MainActivity extends FragmentActivity {

    private Toolbar toolbar;
    private DictionaryFragment dictionaryFragment;
    private TranslateFragment translateFragment;
    private TestFragment testFragment;
    private DAOwordsImpls words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initToolBar();


        words = new DAOwordsImpls(this);

        //Запустим один раз
        //TestUtils.insertTestWord(words.getDatabase());
        dictionaryFragment = new DictionaryFragment(words);
        translateFragment = new TranslateFragment(words);
        testFragment = new TestFragment(words);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initToolBar() {
        toolbar=(Toolbar)findViewById(R.id.test_toolbar);
        toolbar.setTitle("Word2Part");
        toolbar.setTitleTextColor(getColor(R.color.colorText));
    }

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_translate:
                    ft1.replace(R.id.content, translateFragment);
                    ft1.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft1.commit();
                    return true;

                case R.id.navigation_dictionary:
                    ft1.replace(R.id.content, dictionaryFragment);
                    ft1.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft1.commit();
                    return true;
                case R.id.navigation_test:
                    ft1.replace(R.id.content, testFragment);
                    ft1.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft1.commit();
                    return true;
            }
            return false;
        }

    };


}
