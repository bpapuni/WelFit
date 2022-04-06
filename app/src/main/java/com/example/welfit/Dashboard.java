package com.example.welfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity implements TabLayoutMediator.TabConfigurationStrategy {
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    ArrayList<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        titles = new ArrayList<String>();
        titles.add("Weight Graph");
        titles.add("Calendar");
        titles.add("Inbox");

        viewPager2 = findViewById(R.id.viewPager2);
        setViewPagerAdapter();

        tabLayout = findViewById(R.id.dashboard_tab);
        new TabLayoutMediator(tabLayout, viewPager2, this).attach();
    }

    private void setViewPagerAdapter() {
        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(this);
        ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

        fragmentList.add(new WeightGraphFragment());
        fragmentList.add(new CalendarFragment());
        fragmentList.add(new InboxFragment());

        viewPager2Adapter.setData(fragmentList);
        viewPager2.setAdapter(viewPager2Adapter);

    }

    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        tab.setText(titles.get(position));

    }
}