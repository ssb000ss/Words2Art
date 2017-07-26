package com.gmail.ssb000ss.words2part;

import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

import com.gmail.ssb000ss.words2part.dao.DAOwordsImpls;
import com.gmail.ssb000ss.words2part.db.TestUtils;
import com.gmail.ssb000ss.words2part.fragments.DictionaryFragment;
import com.gmail.ssb000ss.words2part.fragments.TestFragment;
import com.gmail.ssb000ss.words2part.fragments.TranslateFragment;

public class MainActivity extends FragmentActivity implements TestFragment.TestListener {

    public static final String TAG="main";

    private Toolbar toolbar;
    private TextView tv_toolbar;
    private TextView tv_toolbar_edit_mode;
    private Switch sw_toolbar;
    private Typeface tf_tv_toolbar;

    private DictionaryFragment dictionaryFragment;
    private TranslateFragment translateFragment;
    private TestFragment testFragment;

    FragmentTransaction ft;
    private DAOwordsImpls words;

    BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        toolbar=(Toolbar)findViewById(R.id.test_toolbar);
        tv_toolbar=(TextView)findViewById(R.id.tv_toolbar);
        tv_toolbar_edit_mode=(TextView)findViewById(R.id.tv_toolbar_edit_mode);
        sw_toolbar=(Switch) findViewById(R.id.sw_toolbar_edit_mode);
        tf_tv_toolbar=Typeface.createFromAsset(getAssets(), WordConstants.Fonts.Roboto_medium);
        words = new DAOwordsImpls(this);



        //Запустим один раз
        //TestUtils.insertTestWord(words.getDatabase());

        dictionaryFragment = new DictionaryFragment(words,sw_toolbar,tv_toolbar_edit_mode);
        translateFragment = new TranslateFragment(words);

        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.content, translateFragment);
        ft1.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft1.commit();

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void initTitleToolBar(String title) {
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
        testFragment = new TestFragment(this);
        initTitleToolBar("Test");
        ft.replace(R.id.content, testFragment);
        ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void initDictionaryFragment() {
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


    @Override
    public void OnClickNextButton() {
        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        ft1.replace(R.id.content, dictionaryFragment);
        ft1.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft1.commit();
        navigation.setSelectedItemId(R.id.navigation_dictionary);
    }
}
