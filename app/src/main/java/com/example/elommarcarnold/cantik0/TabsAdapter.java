package com.example.elommarcarnold.cantik0;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by tutlane on 19-12-2017.
 */

public class TabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public TabsAdapter(FragmentManager fm, int NoofTabs){
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                KeySelectorFragment home = new KeySelectorFragment();
                return home;
            case 1:
                SongswithkeyFragment about = new SongswithkeyFragment();
                return about;
            default:
                return null;
        }
    }
}
