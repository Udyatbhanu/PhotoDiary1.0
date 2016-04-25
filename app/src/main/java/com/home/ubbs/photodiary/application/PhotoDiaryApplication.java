package com.home.ubbs.photodiary.application;

import com.home.ubbs.photodiary.R;
import com.home.ubbs.photodiary.lib.application.BaseApplication;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by udyatbhanu-mac on 4/17/16.
 */
public class PhotoDiaryApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        init(R.raw.slider_menu_config);

    }
}
