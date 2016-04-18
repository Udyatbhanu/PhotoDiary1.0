package com.home.ubbs.photodiary.lib.application;

import android.app.Application;
import android.support.annotation.RawRes;
import android.util.Log;

import com.home.ubbs.photodiary.lib.config.MenuConfiguration;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by udyatbhanu-mac on 4/17/16.
 */
public class BaseApplication extends Application {
    private static final String TAG = BaseApplication.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();



    }

    /**
     *
     * @param resource
     */
    protected void init(@RawRes int resource){

        String[] titles = null;
        String[] activities = null;
        String[] icons = null;

        InputStream inputStream = getResources().openRawResource(resource);

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        StringBuilder text = new StringBuilder();
        try {
            while (( line = bufferedReader.readLine()) != null) {
                text.append(line);
            }
            JSONObject jObject = new JSONObject(text.toString());
            JSONObject jObjectResult = jObject.getJSONObject("MenuItems");
            titles = new String[jObjectResult.length()];
            activities = new String[jObjectResult.length()];
            icons = new String[jObjectResult.length()];
            for(int i=0; i<jObjectResult.length();i++){
                JSONObject jItem = jObjectResult.getJSONObject("activity"+(i+1));
                String title = jItem.getString("title");
                String activity = jItem.getString("activity");
                String icon = jItem.getString("icon");
                titles[i] = title;
                activities[i] = activity;
                icons[i] = icon;
            }
            MenuConfiguration.initialize(titles, activities, icons);


        }catch (JSONException ex) {
            Log.e(TAG,"Exception");
        }

        catch (IOException e) {
            Log.e(TAG,"Exception");
        }

    }
}
