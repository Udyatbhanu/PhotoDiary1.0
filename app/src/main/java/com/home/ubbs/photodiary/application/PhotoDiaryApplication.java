package com.home.ubbs.photodiary.application;

import com.facebook.stetho.Stetho;
import com.home.ubbs.photodiary.R;
import com.home.ubbs.photodiary.lib.application.BaseApplication;

/**
 * Created by udyatbhanu-mac on 4/17/16.
 */
public class PhotoDiaryApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
//        LeakCanary.install(this);
        Stetho.initializeWithDefaults(this);
        init(R.raw.slider_menu_config);

    }
}
