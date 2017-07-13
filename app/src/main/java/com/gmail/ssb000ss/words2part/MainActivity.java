package com.gmail.ssb000ss.words2part;

import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.gmail.ssb000ss.words2part.dao.DAOwordsImpls;
import com.gmail.ssb000ss.words2part.fragments.DictionaryFragment;
import com.gmail.ssb000ss.words2part.fragments.TestFragment;
import com.gmail.ssb000ss.words2part.fragments.TranslateFragment;

public class MainActivity extends FragmentActivity {

    private Toolbar toolbar;
    private TextView tv_toolbar;
    Typeface tf_tv_toolbar;

    private DictionaryFragment dictionaryFragment;
    private TranslateFragment translateFragment;
    private TestFragment testFragment;

    FragmentTransaction ft;
    private DAOwordsImpls words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        toolbar=(Toolbar)findViewById(R.id.test_toolbar);
        tv_toolbar=(TextView)findViewById(R.id.tv_toolbar);
        tf_tv_toolbar=Typeface.createFromAsset(getAssets(), WordConstants.Fonts.Roboto_medium);

        words = new DAOwordsImpls(this);

        //Запустим один раз
        //TestUtils.insertTestWord(words.getDatabase());
        dictionaryFragment = new DictionaryFragment(words);
        translateFragment = new TranslateFragment(words);



        //initDictionaryFragment();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initTitleToolBar(String title) {
        tv_toolbar.setText(title);
        tv_toolbar.setTypeface(tf_tv_toolbar);
    }

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            ft = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_translate:
                    initTranslateFragment();
                    return true;
                case R.id.navigation_dictionary:
                    initDictionaryFragment();
                    return true;
                case R.id.navigation_test:
                    initTestFragment();
                    return true;
            }
            return false;
        }

    };

    private void initTestFragment() {
        testFragment = new TestFragment(words);
        initTitleToolBar("Test");
        ft.replace(R.id.content, testFragment);
        ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    private void initDictionaryFragment() {
        initTitleToolBar("Dictionary");
        ft.replace(R.id.content, dictionaryFragment);
        ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    private void initTranslateFragment() {
        initTitleToolBar("Translate");
        ft.replace(R.id.content, translateFragment);
        ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }


}
