package com.vcinsidedigital.barbearia_diaz.viewpager;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.vcinsidedigital.barbearia_diaz.fragment.AgendaFragment;

import java.util.ArrayList;
import java.util.List;

public class VPAdapter extends FragmentStateAdapter {


    private List<Fragment> fragmentsList = new ArrayList<Fragment>();

    public VPAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<Fragment> fragments) {
        super(fragmentManager, lifecycle);
        this.fragmentsList = fragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = fragmentsList.get(position);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return fragmentsList.size();
    }
}
