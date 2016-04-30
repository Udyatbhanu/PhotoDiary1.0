package com.home.ubbs.photodiary.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.home.ubbs.photodiary.R;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

/**
 * Created by udyatbhanu-mac on 4/30/16.
 */
public class ViewAlbumActivity extends AppCompatActivity {

    private static final String TAG = ViewAlbumActivity.class.getSimpleName();

    private FloatingActionButton cameraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_album);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        cameraButton = (FloatingActionButton) findViewById(R.id.camera_button);
        Drawable icon = new IconicsDrawable(this, FontAwesome.Icon.faw_camera).
                color(getResources().getColor(R.color.camera_color));

        cameraButton.setImageDrawable(icon);

        //set the back arrow in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.animator.slide_in_right,
                R.animator.slide_out_right);
    }
}
