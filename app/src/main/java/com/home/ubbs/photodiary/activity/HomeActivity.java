package com.home.ubbs.photodiary.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.home.ubbs.photodiary.R;
import com.home.ubbs.photodiary.adapter.AlbumsAdapter;
import com.home.ubbs.photodiary.lib.PhotoDiaryBaseActivity;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;

public class HomeActivity extends PhotoDiaryBaseActivity {

    private static final int PROFILE_SETTING = 1;


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton cameraButton;

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setHeaderTitle(R.string.drawer_item_compact_header);
        mRecyclerView = (RecyclerView) findViewById(R.id.albums_recycler_view);
        cameraButton = (FloatingActionButton) findViewById(R.id.camera_button);
        mRecyclerView.setHasFixedSize(false);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new AlbumsAdapter();
        mRecyclerView.setAdapter(mAdapter);
        Drawable icon = new IconicsDrawable(this, FontAwesome.Icon.faw_camera).
                color(getResources().getColor(R.color.camera_color));

        cameraButton.setImageDrawable(icon);

        cameraButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ViewAlbumActivity.class);
                startActivity(intent);
                overridePendingTransition(R.animator.slide_in_left,
                        R.animator.slide_out_left);

            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase));
    }
}
