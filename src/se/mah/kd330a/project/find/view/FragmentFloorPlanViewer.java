package se.mah.kd330a.project.find.view;


import java.util.ArrayList;
import java.util.List;

import com.viewpagerindicator.TitlePageIndicator;

import se.mah.kd330a.project.R;
import se.mah.kd330a.project.find.data.BuildingHelper;
import se.mah.kd330a.project.find.data.RoomDbHandler;
import se.mah.kd330a.project.find.data.ImageLoader.OnImageLoaderListener;
import se.mah.kd330a.project.find.data.RoomDbHandler.Room;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.SearchView;

public class FragmentFloorPlanViewer extends Fragment implements SearchView.OnQueryTextListener, SearchView.OnCloseListener
{

    private int preSelectedFloor;
    private String building_code;
    ToggledViewPager viewPager;
    PagerTabStrip pagerTabStrip;
	private SearchView mSearchView;
	private Room preSelectedRoom;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listOfFragments = new ArrayList<FragmentFloorMap>();
    // Sets orientation to portrait, temporary fix to horizontal view crash
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        Bundle packet = getArguments();
        building_code = packet.getString(BuildingHelper.ARG_BUILDING);
        preSelectedFloor = packet.getInt(BuildingHelper.ARG_FLOORINDEX, 0);
        preSelectedRoom = RoomDbHandler.getInstance().FindRoom(packet.getString(BuildingHelper.ARG_ROOMNAME, null));
        Log.i("julia", "We want to see stuff for building: " + building_code);
        //setContentView(R.layout.simple_tabs);
        View v =  inflater.inflate(R.layout.fragment_screen_find_floorplan_list, container, false);

        FloorPlanViewerAdapter adapter = new FloorPlanViewerAdapter(getActivity().getSupportFragmentManager());
        
        viewPager = (ToggledViewPager)v.findViewById(R.id.pager);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(preSelectedFloor);
        
        //TitlePageIndicator indicator = (TitlePageIndicator)v.findViewById(R.id.indicator);
        //indicator.setViewPager(viewPager);
        pagerTabStrip = (PagerTabStrip) v.findViewById(R.id.pager_tab_strip);
		pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.green));
        pagerTabStrip.setTextSpacing(1);
		pagerTabStrip.setDrawFullUnderline(true);
		
		
		
        return v;
    }
    List<FragmentFloorMap> listOfFragments;
    //Make sure that we clean up all our child fragments when this fragment is detatched.
    @Override
    public void onDetach() {
    	super.onDetach();
    	FragmentManager frMan = getActivity().getSupportFragmentManager();
    	FragmentTransaction frTr = frMan.beginTransaction();
    	for(FragmentFloorMap fr:listOfFragments)
    	{
    		fr.CleanUp();
    		frTr.remove(fr);
    		fr = null;
    	}
    };
    
      class FloorPlanViewerAdapter extends  FragmentStatePagerAdapter  {
        public FloorPlanViewerAdapter(FragmentManager fm) {
            super(fm);
        }
        //When the page view wants a new element, we create it and put it in a list, so we can easily clean up later.
        @Override
        public Fragment getItem(int position) {
        	FragmentFloorMap createdFragment =  FragmentFloorMap.newInstance(building_code, position, preSelectedRoom, viewPager);   //CONTENT[position % CONTENT.length]);
            listOfFragments.add(createdFragment);
            return createdFragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
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
		
	///SEARCH Code below here until next //SEARCH is for search!
		mSearchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        setupSearchView();
	}
	
  	private void setupSearchView() {

        mSearchView.setIconifiedByDefault(true);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setOnCloseListener(this);
    }

  	public boolean onQueryTextChange(String newText) {
        return false;
    }
    public boolean onQueryTextSubmit(String query) {
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		FragmentSearchResultList newFragment = new FragmentSearchResultList();
		Bundle args = new Bundle();
		args.putString(BuildingHelper.ARG_SEARCHSTRING, query);
		newFragment.setArguments(args);
		newFragment.show(ft, "dialog");

        return false;
    }
    //SEARCH ends here.
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {


		//VARIABLES AND onClick-METHOD FOR LINKING OUT TO GOOGLE MAPS--------------------------------------------------------------------------------
		switch (item.getItemId()) {
		case R.id.find_menu_google:
		String location = BuildingHelper.GetLocation(building_code);
		
			//getting the google map
			Intent i = new Intent(android.content.Intent.ACTION_VIEW,
					Uri.parse("geo:0,0?q="+location+"+Malmo+Sweden"));
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	@Override
	public boolean onClose() {
		// TODO Auto-generated method stub
		return false;
	}
	

}

