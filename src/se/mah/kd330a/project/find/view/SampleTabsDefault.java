package se.mah.kd330a.project.find.view;


import com.viewpagerindicator.TitlePageIndicator;

import se.mah.kd330a.project.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

//import com.viewpagerindicator.TabPageIndicator;
//import com.viewpagerindicator.TitlePageIndicator;

// Use

public class SampleTabsDefault extends Fragment {
    private static final String[] CONTENT = new String[] { "Plan A", "Plan B", "Plan C", "Plan D"};
    private int building_number;
    ToggledViewPager viewPager;
    PagerTabStrip pagerTabStrip;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle packet = getArguments();
        building_number = packet.getInt("names");
        
        //setContentView(R.layout.simple_tabs);
        View v =  inflater.inflate(R.layout.fragment_screen_find_floorplan_list, container, false);

        FragmentPagerAdapter adapter = new FloorPlanViewerAdapter(getActivity().getSupportFragmentManager());

        viewPager = (ToggledViewPager)v.findViewById(R.id.pager);
        viewPager.setAdapter(adapter);
        
        //TitlePageIndicator indicator = (TitlePageIndicator)v.findViewById(R.id.indicator);
        //indicator.setViewPager(viewPager);
        pagerTabStrip = (PagerTabStrip) v.findViewById(R.id.pager_tab_strip);
		pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.green));
        pagerTabStrip.setTextSpacing(1);
		pagerTabStrip.setDrawFullUnderline(true);
        return v;
    }
      class FloorPlanViewerAdapter extends  FragmentPagerAdapter  {
        public FloorPlanViewerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFloorMap_v2.newInstance(building_number, position, viewPager);   //CONTENT[position % CONTENT.length]);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override
        public int getCount() {
          return CONTENT.length;
        }
    }
}

