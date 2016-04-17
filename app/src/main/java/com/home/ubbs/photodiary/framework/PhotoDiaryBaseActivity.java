package com.home.ubbs.photodiary.framework;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.home.ubbs.photodiary.R;

/**
 * Created by udyatbhanu-mac on 4/17/16.
 */
public class PhotoDiaryBaseActivity extends AppCompatActivity {


    protected Toolbar toolbar;
    private RelativeLayout parentLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     *
     * @param layoutResID
     */

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        super.setContentView(R.layout.activity_photo_diary_base);

        parentLayout = (RelativeLayout)findViewById(R.id.parent_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT);
        View child = getLayoutInflater().inflate(layoutResID, null);
        params.addRule(RelativeLayout.BELOW, R.id.toolbar);
        child.setLayoutParams(params);



    }

    /**
     *
     * @param resourceId
     */
    protected void setHeaderTitle(int resourceId){
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle(resourceId);
        }



    }
}
