package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(AppCompatActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new TopStoriesFragment();
            case 1:
                return new SportsFragment();
            case 2:
                return new EntertainmentFragment();
            default:
                return new TopStoriesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}