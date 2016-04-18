package com.home.ubbs.photodiary.activity;

import android.os.Bundle;

import com.home.ubbs.photodiary.R;
import com.home.ubbs.photodiary.lib.PhotoDiaryBaseActivity;

/**
 * Created by udyatbhanu-mac on 4/16/16.
 */
public class NewAlbumActivity  extends PhotoDiaryBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_album);
        setHeaderTitle(R.string.drawer_item_new_album_header);

    }
}
