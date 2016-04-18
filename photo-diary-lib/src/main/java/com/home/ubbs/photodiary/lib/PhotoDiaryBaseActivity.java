package com.home.ubbs.photodiary.lib;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


/**
 * Created by udyatbhanu-mac on 4/17/16.
 */
public class PhotoDiaryBaseActivity extends AppCompatActivity {


    protected Toolbar toolbar;
    private RelativeLayout parentLayout;


    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private FrameLayout container;
    Bundle savedInstanceState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_diary_base);
        this.savedInstanceState = savedInstanceState;
    }

    /**
     *
     */
    class ActionBarCallBack implements ActionMode.Callback {

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.Snow));
            }

//            mode.getMenuInflater().inflate(R.menu.cab, menu);
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }
    }

    /**
     *
     * @param layoutResID
     */

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        super.setContentView(R.layout.activity_photo_diary_base);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        container = (FrameLayout)findViewById(R.id.frame_container);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        View child = getLayoutInflater().inflate(layoutResID, null);
        container.addView(child,params);
        child.setLayoutParams(params);
        final Activity activity = (Activity)child.getContext();


        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.header)
                .withSavedInstance(savedInstanceState)
                .build();

        Drawable background = getResources().getDrawable(R.drawable.menu);
        background.setAlpha(200);

        int color = getResources().getColor(R.color.slider_color);

        ViewGroup rootView = (ViewGroup)child.getParent();

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withTranslucentNavigationBar(true)
                .withFullscreen(true)
                .withHasStableIds(true)
                .withSliderBackgroundColor(getResources().getColor(R.color.slider_color))

                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_new_album).withIcon(FontAwesome.Icon.faw_plus),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_gallery).withIcon(FontAwesome.Icon.faw_eye),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_feedback).withIcon(FontAwesome.Icon.faw_envelope),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_question)

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Class clazz = null;
                        try{
                            clazz = Class.forName("com.home.ubbs.photodiary.NewAlbumActivity");
                        }catch(Exception e){

                        }

                        if (drawerItem != null) {
                            Intent intent = new Intent(activity, clazz);

                            if (intent != null) {
                                activity.startActivity(intent);
                            }
                        }



                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        // set the selection to the item with the identifier 5
        if (savedInstanceState == null) {
            result.setSelection(5, false);
        }

        //set the back arrow in the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);





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
