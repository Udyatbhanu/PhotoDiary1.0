package com.home.ubbs.photodiary.lib.config;

import android.app.Activity;

import com.mikepenz.iconics.typeface.IIcon;

/**
 * Created by udyatbhanu-mac on 4/17/16.
 */
public class MenuConfiguration {

    private  String[] titles;
    private  IIcon[] icons;
    private  Class<Activity>[] activities;
    public static MenuConfiguration INSTANCE = new MenuConfiguration();

    private MenuConfiguration(){

    }
    public static void initialize(String[] titles,String[] icons, String[] activities ){
//        MenuConfiguration.INSTANCE.setActivities(activities);
//        MenuConfiguration.INSTANCE.setIcons(icons);
//        MenuConfiguration.INSTANCE.setActivities(activities);
    }

    public  Class<Activity>[] getActivity() {
        return activities;
    }

    public  void setActivities(Class<Activity>[] activities) {
        this.activities = activities;
    }

    public  IIcon[] getIcons() {
        return this.icons;
    }

    public  void setIcons(IIcon[] icons) {
        this.icons = icons;
    }

    public  String[] getTitles() {
        return this.titles;
    }

    public  void setTitles(String[] titles) {
        this.titles = titles;
    }




 }
