package se.mah.kd330a.project.find.view;


import com.viewpagerindicator.TitlePageIndicator;

import se.mah.kd330a.project.R;
import se.mah.kd330a.project.find.data.BuildingHelper;
import se.mah.kd330a.project.find.data.ImageLoader.OnImageLoaderListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

//import com.viewpagerindicator.TabPageIndicator;
//import com.viewpagerindicator.TitlePageIndicator;

// Use

public class SampleTabsDefault extends Fragment //implements OnImageLoaderListener
{
    //private static final String[] CONTENT = new String[] { "Plan A", "Plan B", "Plan C", "Plan D"};

    
    
    private String building_code;
    ToggledViewPager viewPager;
    PagerTabStrip pagerTabStrip;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    // Sets orientation to portrait, temporary fix to horizontal view crash
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        Bundle packet = getArguments();
        building_code = packet.getString(FragmentMaps.ARG_BUILDING);
        
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
            return FragmentFloorMap_v2.newInstance(building_code, position, viewPager);   //CONTENT[position % CONTENT.length]);
        }

        @Override
        public CharSequence getPageTitle(int position) {
           // return CONTENT[position % CONTENT.length].toUpperCase();
        	return BuildingHelper.GetBuildingFloorPlanTitle(building_code, position, getResources());
        }

        @Override
        public int getCount() {
        	return BuildingHelper.FloorCount(building_code, getResources());
        }
    }
      
  	@Override
  	public void onCreate(Bundle savedInstanceState) {
  		super.onCreate(savedInstanceState);
  		setHasOptionsMenu(true);
  	}
  	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.find, menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {


		//VARIABLES AND onClick-METHOD FOR LINKING OUT TO GOOGLE MAPS--------------------------------------------------------------------------------
		switch (item.getItemId()) {
		case R.id.find_menu_google:
		String location = BuildingHelper.GetLocation(building_code);
		/*
			String[] buildingNames = getResources().getStringArray(R.array.find_building_code_array);
			String location = buildingNames[building];

			if(location.equals("Klerken (Kl)")){
				Log.i("gmaps","klerken");
				location = "Carl Gustafs vag 34";
			}else if(location.equals("University Hospital (As)")){
				Log.i("gmaps","university hospital");
				location = "Jan Waldenstroms gata 25";
			}else if(location.equals("Kranen (K2)") || location.equals("Ub�tshallen (K8)")){
				Log.i("gmaps","kranen & ub�ten");
				location = "�stra Varvsgatan 11 A";
			}else if(location.equals("Orkanen (Or)")){
				Log.i("gmaps","orkanen");
				location = "Nordenski�ldsgatan 10";
			}else if(location.equals("G�ddan (G8)")){
				Log.i("gmaps","g�ddan");
				location = "Citadellsv�gen 7";
			}
*/
			//getting the google map
			Intent i = new Intent(android.content.Intent.ACTION_VIEW,
					Uri.parse("geo:0,0?q="+location+"+Malmo+Sweden"));

			startActivity(i);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	

}

