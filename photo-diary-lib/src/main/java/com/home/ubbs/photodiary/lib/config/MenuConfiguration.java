package com.home.ubbs.photodiary.lib.config;

import android.app.Activity;
import android.util.Log;

import com.home.ubbs.photodiary.lib.R;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by udyatbhanu-mac on 4/17/16.
 */
public class MenuConfiguration {
    private static final String TAG = MenuConfiguration.class.getSimpleName();
    private String[] titles;
    private IIcon[] icons;
    private Class[] activities;


    private List<IDrawerItem>  items;


    private int size;
    private static MenuConfiguration INSTANCE = new MenuConfiguration();

    private MenuConfiguration() {

    }

    public static void initialize(String[] titles, String[] activities, String[] icons) {

        getInstance().setTitles(titles);
        getInstance().setActivities(activities);
        getInstance().setIcons(icons);
        getInstance().setItems();

    }


    /**
     *
     * @return
     */
    public List<IDrawerItem> getItems() {
        return items;
    }

    /**
     *
     */
    public void setItems() {
        items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(new PrimaryDrawerItem().withName(titles[i]).
                    withIcon(icons[i]).withIdentifier(i));
//            items[i] = new PrimaryDrawerItem().withName(titles[i]).
//                    withIcon(icons[i]).withIdentifier(i);
//            items.add(new PrimaryDrawerItem().withName(titles[i]).
//                    withIcon(icons[i]).withIdentifier(i));
//            items.add(new DividerDrawerItem());
        }

        items.add(new PrimaryDrawerItem().withName("About").withIcon(FontAwesome.Icon.faw_info).withIdentifier(size));
//        items.add(new SecondaryDrawerItem().withName("License").withIcon(FontAwesome.Icon.faw_cog));



    }

    /**
     * @return
     */
    public static MenuConfiguration getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        } else {
            return new MenuConfiguration();
        }
    }


    public Class<Activity>[] getActivities() {
        return activities;
    }

    /**
     * @param activitiesClazzes
     */
    public void setActivities(String[] activitiesClazzes) {
        activities = new Class[activitiesClazzes.length];
        for (int i = 0; i < size; i++) {
            try {
                this.activities[i] = Class.forName(activitiesClazzes[i]);
            } catch (ClassNotFoundException e) {
                Log.e(TAG, e.getMessage());
            }
        }

    }

    public IIcon[] getIcons() {
        return this.icons;
    }

    /**
     * @param icons
     */
    public void setIcons(String[] menuIcons) {
        icons = new FontAwesome.Icon[menuIcons.length];
        for (int i = 0; i < size; i++) {
            this.icons[i] = FontAwesome.Icon.valueOf(menuIcons[i]);
        }

    }

    public String[] getTitles() {
        return this.titles;
    }


    /**
     * @param titles
     */
    public void setTitles(String[] titles) {
        this.titles = titles;
        setSize(titles.length);
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
