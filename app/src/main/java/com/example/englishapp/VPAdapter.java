package com.example.englishapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class VPAdapter extends FragmentStateAdapter {

    public VPAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if(position == 0){
            return new WordListFragment();
        }
        return new CategoryListFragment();
        /*switch (position){
            case 0:
                return new WordListFragment();
            case 1:
                return new CategoryListFragment();
            default:
                return new WordListFragment();
        }*/
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
