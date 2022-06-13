package com.example.englishapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class VPAdapterDictionary extends FragmentStateAdapter {

    WordListFragment wordListFragment;
    CategoryListFragment categoryListFragment;

    public VPAdapterDictionary(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        wordListFragment = new WordListFragment();
        categoryListFragment = new CategoryListFragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if(position == 0){
            return wordListFragment;
        }
        return categoryListFragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
