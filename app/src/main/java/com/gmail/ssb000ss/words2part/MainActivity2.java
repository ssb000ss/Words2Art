package com.gmail.ssb000ss.words2part;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.gmail.ssb000ss.words2part.dao.DAOwordsImpls;
import com.gmail.ssb000ss.words2part.db.TestUtils;
import com.gmail.ssb000ss.words2part.fragments.DictionaryFragment;
import com.gmail.ssb000ss.words2part.fragments.TestFragment;
import com.gmail.ssb000ss.words2part.fragments.TranslateFragment;

public class MainActivity2 extends FragmentActivity {

    DictionaryFragment dictionaryFragment;
    TranslateFragment translateFragment;
    TestFragment testFragment;
    DAOwordsImpls words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        words = new DAOwordsImpls(this);

        //Запустим один раз
        //TestUtils.insertTestWord(words.getDatabase());
        dictionaryFragment = new DictionaryFragment(words);
        translateFragment = new TranslateFragment();
        testFragment = new TestFragment();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
