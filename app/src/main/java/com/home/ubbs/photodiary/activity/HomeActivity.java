package com.home.ubbs.photodiary.activity;

import android.os.Bundle;

import com.home.ubbs.photodiary.R;
import com.home.ubbs.photodiary.lib.PhotoDiaryBaseActivity;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;

public class HomeActivity extends PhotoDiaryBaseActivity {

    private static final int PROFILE_SETTING = 1;

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setHeaderTitle(R.string.drawer_item_compact_header);
    }
}
