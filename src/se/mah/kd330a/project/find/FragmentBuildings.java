package se.mah.kd330a.project.find;

import java.io.InputStream;
import java.util.Locale;
import se.mah.kd330a.project.R;
import se.mah.kd330a.project.faq.FragmentFaq;
import se.mah.kd330a.project.find.data.BuildingHelper;
import se.mah.kd330a.project.find.data.RoomDbHandler;
import se.mah.kd330a.project.find.data.RoomDbHandler.Room;
import se.mah.kd330a.project.find.view.FragmentSearchResultList;
import se.mah.kd330a.project.find.view.FragmentFloorPlanViewer;
import se.mah.kd330a.project.help.FragmentCredits;
import se.mah.kd330a.project.home.FragmentHome;
import se.mah.kd330a.project.itsl.FragmentITSL;
import se.mah.kd330a.project.schedule.view.FragmentScheduleWeekPager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;


public class FragmentBuildings extends Fragment implements OnClickListener, SearchView.OnQueryTextListener, SearchView.OnCloseListener {

	//creating objects for each item in the buildings list
	private LinearLayout house1;
	private LinearLayout house2;
	private LinearLayout house3;
	private LinearLayout house4;
	private LinearLayout house5;
	private LinearLayout house6;
	private LinearLayout house7;
	private SearchView mSearchView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		//inflating layout
		ViewGroup rootView = (ViewGroup) inflater
				.inflate(R.layout.fragment_screen_find_buildings, container, false);
		
		//Initializing the UI components by linking the objects to their view and setting click listener        
        house1 = (LinearLayout) rootView.findViewById(R.id.house1);
        house1.setOnClickListener(this);
        
        house2 = (LinearLayout) rootView.findViewById(R.id.house2);
        house2.setOnClickListener(this);
        
        house3 = (LinearLayout) rootView.findViewById(R.id.house3);
        house3.setOnClickListener(this);

        house4 = (LinearLayout) rootView.findViewById(R.id.house4);
        house4.setOnClickListener(this);

        house5 = (LinearLayout) rootView.findViewById(R.id.house5);
        house5.setOnClickListener(this);

        house6 = (LinearLayout) rootView.findViewById(R.id.house6);
        house6.setOnClickListener(this);
        
        
        //renames the building items accordingly
        Resources res = getResources();
		String[] buildingNameArray = res.getStringArray(R.array.find_building_array);
		((TextView) rootView.findViewById(R.id.building_name1)).setText(buildingNameArray[1]);
		((TextView) rootView.findViewById(R.id.building_name2)).setText(buildingNameArray[2]);
		((TextView) rootView.findViewById(R.id.building_name3)).setText(buildingNameArray[3]);
		((TextView) rootView.findViewById(R.id.building_name4)).setText(buildingNameArray[4]);
		((TextView) rootView.findViewById(R.id.building_name5)).setText(buildingNameArray[5]);
		((TextView) rootView.findViewById(R.id.building_name6)).setText(buildingNameArray[6]);
		 
		
		return rootView;
	}
	
	//needed to produce an option item in the menu bar (google maps icon)
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	//creating the option item in the menu bar (google maps icon)
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    // TODO Add your menu entries here
	    super.onCreateOptionsMenu(menu, inflater);
	    inflater.inflate(R.menu.find_all, menu);
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
    //SEARCH Till here.
    
	//setting an action to what happens when the option item is clicked
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.find_menu_google_all:
			//opening a kml file (google maps file) from res/raw that contains the pre-marked positions of all buildings
			Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/maps?q=http://195.178.234.7/mahapp/images/raw/mahbyggnader.kml"));
			startActivity(i);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	//handles clicks
	@Override
	public void onClick(View v) { 

		//calls on the method that loads a building's GUI depending on which item in the list was clicked
		if(v.getId() == R.id.house1){
			selectBuilding(1);
		}else if(v.getId() == R.id.house2){
			selectBuilding(2);
		}else if(v.getId() == R.id.house3){
			selectBuilding(3);
		}else if(v.getId() == R.id.house4){
			selectBuilding(4);
		}else if(v.getId() == R.id.house5){
			selectBuilding(5);
		}else if(v.getId() == R.id.house6){
			selectBuilding(6);
		}
	}
	
	//gets the building code form a position in an array and puts it as the parameter in the method that loads a fragment with the respective building's GUI
	private void selectBuilding(int pos) {
		String buildingCode;
		Resources res = getResources();
		String[] findCode = res
				.getStringArray(R.array.find_building_code_array);
		buildingCode = findCode[pos];
		Fragment fragment = BuildingHelper.getFragmentBuildingMap(buildingCode);
		FragmentManager	 fragmentManager = getActivity().getSupportFragmentManager();
		FragmentTransaction fragmentTrans = fragmentManager.beginTransaction();	
		fragmentTrans.replace(R.id.content_frame, fragment);
		fragmentTrans.addToBackStack(null);
		fragmentTrans.commit();	
	}

	@Override
	public boolean onClose() {
		return false;
	}
	

}
