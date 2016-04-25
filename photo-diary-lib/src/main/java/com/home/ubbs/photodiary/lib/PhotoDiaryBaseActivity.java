package com.home.ubbs.photodiary.lib;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.home.ubbs.photodiary.lib.config.MenuConfiguration;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;


/**
 * Created by udyatbhanu-mac on 4/17/16.
 */
public class PhotoDiaryBaseActivity extends AppCompatActivity {
    private static final String TAG = PhotoDiaryBaseActivity.class.getSimpleName();

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
//        setContentView(R.layout.activity_photo_diary_base);
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
        setUpSlidingMenu(activity);

    }


    /**
     *
     * @param activity
     */

    private void setUpSlidingMenu(final Activity activity){
        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.header)
                .withSavedInstance(savedInstanceState)
                .build();

//        Drawable background = getResources().getDrawable(R.drawable.menu);
//        background.setAlpha(40);


        List<IDrawerItem> listItems = MenuConfiguration.getInstance().getItems();



//        IDrawerItem[] listArray = MenuConfiguration.getInstance().getItems();
//
//        listArray
//        new DividerDrawerItem()

//        Object[] itemsObjectArray = listItems.toArray();
//        listArray = listItems.toArray();

//        IDrawerItem[] listArray = new IDrawerItem[MenuConfiguration.getInstance().getSize()*2];
//
//        Object[] itemsObjectArray = listItems.toArray();
//        int j = -1;
//        for(int i=0; i<listArray.length; i++){
//
//            if(itemsObjectArray[i] instanceof PrimaryDrawerItem){
//                listArray[i] = (PrimaryDrawerItem)itemsObjectArray[i];
//                listArray[i].withIdentifier(++j);
//            }else{
//                listArray[i] = (DividerDrawerItem)itemsObjectArray[i];
//            }
//        }






        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withTranslucentNavigationBar(true)
                .withFullscreen(true)
                .withHasStableIds(true)
                .withSliderBackgroundColor(getResources().getColor(R.color.slider_color))

                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .withHeaderPadding(true)
                .withHeaderDivider(false)
                .withDrawerItems(
                        listItems

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Class clazz = null;
                        try{
                        if(drawerItem.getIdentifier()== MenuConfiguration.getInstance().getItems().size()-1){
                            clazz = AboutActivity.class;
                        }else{
                            clazz = MenuConfiguration.getInstance().getActivities()[(int)drawerItem.getIdentifier()];
                        }



                        }catch(Exception e){
                                Log.e(TAG, "Exception");
                        }

                        if (drawerItem != null) {
                            Intent intent = new Intent(activity, clazz);

                            if (intent != null) {
                                activity.startActivity(intent);
                                activity.finish();
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
