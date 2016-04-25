package com.home.ubbs.photodiary.lib;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

/**
 * Created by udyatbhanu-mac on 4/21/16.
 */
public class AboutActivity extends PhotoDiaryBaseActivity {


    TextView licenseText, iconicsLiscense, leakCanary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        licenseText = (TextView)findViewById(R.id.material_drawer);
        iconicsLiscense = (TextView)findViewById(R.id.android_iconics);
        leakCanary = (TextView)findViewById(R.id.leak_canary);



        licenseText.setText(Html.fromHtml(getString(R.string.mike_penz_apache_2_0)));
        iconicsLiscense.setText(Html.fromHtml(getString(R.string.mike_penz_apache_2_0)));
        leakCanary.setText(Html.fromHtml(getString(R.string.leak_canary_2_0)));

        setHeaderTitle(R.string.drawer_item_compact_header);
    }

}
