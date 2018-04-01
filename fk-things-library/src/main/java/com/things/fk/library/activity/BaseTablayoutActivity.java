package com.things.fk.library.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;

import com.things.fk.library.R;

/**
 * @author tic
 *         created on 18-3-29
 */

public abstract class BaseTablayoutActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void initTablayout() {
        TabLayout tabLayout = findViewById(R.id.tab_layout);
//        viewPager.setAdapter(new ViewPagerAdapter(
//                Arrays.asList("Tab1", "Tab2", "Tab3", "Tab4", "Tab5", "Tab6"),
//                Arrays.asList(new RecyclerViewFragment(), new RecyclerViewFragment(),
//                        new RecyclerViewFragment(), new RecyclerViewFragment(),
//                        new RecyclerViewFragment(), new RecyclerViewFragment()
//
//                )));
//
//        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}
