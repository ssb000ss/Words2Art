package com.gmail.ssb000ss.words2part;

import android.Manifest;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

import com.gmail.ssb000ss.words2part.dao.DAOwordsImpls;
import com.gmail.ssb000ss.words2part.fragments.DictionaryFragment;
import com.gmail.ssb000ss.words2part.fragments.TestFragment;
import com.gmail.ssb000ss.words2part.fragments.TranslateFragment;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;

public class MainActivity extends FragmentActivity implements TestFragment.TestListener {

    public static final String TAG="main";

    private TextView tv_toolbar;
    private Typeface tf_tv_toolbar;

    private DictionaryFragment dictionaryFragment;
    private TranslateFragment translateFragment;

    private FragmentTransaction ft;
    private DAOwordsImpls words;

    private BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        
        tv_toolbar=(TextView)findViewById(R.id.tv_toolbar);
        TextView tv_toolbar_edit_mode = (TextView) findViewById(R.id.tv_toolbar_edit_mode);
        Switch sw_toolbar = (Switch) findViewById(R.id.sw_toolbar_edit_mode);
        tf_tv_toolbar=Typeface.createFromAsset(getAssets(), Constants.Fonts.Roboto_medium);
        words = new DAOwordsImpls(this);

        translateFragment = new TranslateFragment();
        translateFragment.setArguments(words,this);
        dictionaryFragment = new DictionaryFragment();
        dictionaryFragment.setArguments(words, sw_toolbar, tv_toolbar_edit_mode);

        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
        initTitleToolBar("Translate");
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

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
        TestFragment testFragment = new TestFragment();
        testFragment.setArguments(this,words);
        initTitleToolBar(getResources().getString(R.string.title_test));
        ft.replace(R.id.content, testFragment);
        ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    private void initDictionaryFragment() {
        initTitleToolBar(getResources().getString(R.string.title_dictionary));
        ft.replace(R.id.content, dictionaryFragment);
        ft.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    private void initTranslateFragment() {
        initTitleToolBar(getResources().getString(R.string.title_translate));
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
