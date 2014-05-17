package se.mah.kd330a.project.find;

import java.io.InputStream;
import java.util.Locale;

import se.mah.kd330a.project.R;
import se.mah.kd330a.project.find.data.RoomDbHandler;
import se.mah.kd330a.project.find.view.FragmentBuilding;
import se.mah.kd330a.project.find.view.FragmentFloorMap;
import se.mah.kd330a.project.find.view.FragmentMaps;
import se.mah.kd330a.project.find.view.FragmentResult;
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
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

/**
 * THIS JAVA CLASS IS OBSOLETE - IT'S FUNCTIONALITY HAS BEEN REPLACED BY FragmentBuildings.java
 * 
 * @author CWS
 *
 */

public class FragmentFind extends Fragment implements OnClickListener {

	private static final String FIND_SPINNER_STATE = "spinChoice";

	private String selposFind = null;
	private int spin_selected = -1;
	private Spinner spinnerFind;
	
//	private LinearLayout house1;
//	private LinearLayout house2;
//	private LinearLayout house3;
//	private LinearLayout house4;
//	private LinearLayout house5;
//	private LinearLayout house6;
//	private LinearLayout house7;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
        
        setHasOptionsMenu(true);
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		ViewGroup rootView = (ViewGroup) inflater
				//.inflate(R.layout.fragment_screen_find, container, false);			//this loads the old fragment
				.inflate(R.layout.fragment_screen_find_buildings, container, false);	//this loads a test fragment made by CW
		
		
		// Initialize the UI components and set listener        
//        house1 = (LinearLayout) rootView.findViewById(R.id.house1);
//        house1.setOnClickListener(this);
//        
//        house2 = (LinearLayout) rootView.findViewById(R.id.house2);
//        house2.setOnClickListener(this);
//        
//        house3 = (LinearLayout) rootView.findViewById(R.id.house3);
//        house3.setOnClickListener(this);
//
//        house4 = (LinearLayout) rootView.findViewById(R.id.house4);
//        house4.setOnClickListener(this);
//
//        house5 = (LinearLayout) rootView.findViewById(R.id.house5);
//        house5.setOnClickListener(this);
//
//        house6 = (LinearLayout) rootView.findViewById(R.id.house6);
//        house6.setOnClickListener(this);
//        
//        house7 = (LinearLayout) rootView.findViewById(R.id.house7);
//        house7.setOnClickListener(this);
        
		return rootView;
	}

	//THE COMMENTED CODE BELOW IS HIDDEN FOR TEST PURPOSES AND BELONGS TO THE OLD DESIGN, REMOVE WHEN NEW DESIGN IS COMPLETELY IMPLEMENTED************************************************************************
	@Override
	public void onStart() {
		super.onStart();

//		spinnerFind = (Spinner) getView().findViewById(R.id.spinner_find_building);
//		ArrayAdapter<CharSequence> spinFindadapter = ArrayAdapter
//				.createFromResource(getActivity(), R.array.find_building_array,
//						android.R.layout.simple_spinner_item);
//		spinFindadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinnerFind.setAdapter(spinFindadapter);
//		
//
//		spinnerFind.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//			/**
//			 * Called when a new item is selected (in the Spinner)
//			 */
//			public void onItemSelected(AdapterView<?> parent,
//					View view, int pos, long id) {
//
//				parent.getItemAtPosition(pos);
//				Resources res = getResources();
//				String[] findCode = res
//						.getStringArray(R.array.find_building_code_array);
//
//				selposFind = findCode[pos];
//				spin_selected = pos;
//
//			}
//
//			public void onNothingSelected(AdapterView<?> parent) {
//				// Do nothing, just another required interface callback
//			}
//		});
//		
//
//		if (spin_selected > -1) 
//			spinnerFind.setSelection(spin_selected, true);
//		
//		//We're not going to use the search function in this fragment
//		Button btn_Search = (Button) getView().findViewById(R.id.button_find_navigation);
//		btn_Search.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				find_button_navigation(view);
//			}
//		});
//
//		AutoCompleteTextView etRoomNr = (AutoCompleteTextView) getView().findViewById(R.id.editText_find_room);
//		etRoomNr.setOnEditorActionListener(new OnEditorActionListener() {
//
//			@Override
//			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//				if(actionId == EditorInfo.IME_ACTION_GO){
//					find_button_navigation(v);
//				}
//				return false;
//			}		
//		});
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	    // TODO Add your menu entries here
	    super.onCreateOptionsMenu(menu, inflater);
	    inflater.inflate(R.menu.find_all, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.find_menu_google_all:
			
//			 for(int pin=0; pin<pins.size(); pin++)
//	            {
//	                LatLng pinLocation = new LatLng(Float.parseFloat(pins.get(pin).latitude), Float.parseFloat(pins.get(pin).longitude));
//	                Marker storeMarker = map.addMarker(new MarkerOptions()
//	                .position(pinLocation)
//	                .title(pins.get(pin).pinname)
//	                .snippet(pins.get(pin).address)
//	                );
//	            }

			//ALTERNATIVE SOLUTION VIA WEB
//			String kmlWebAddress = "http://www.afischer-online.de/sos/AFTrack/tracks/e1/01.24.Soltau2Wietzendorf.kml";
//			String uri = String.format(Locale.ENGLISH, "geo:0,0?q=%s",kmlWebAddress);
//			Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=android.resource://com.androidbook.samplevideo/raw/myvideo"));
//			startActivity(i);
			
			Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=android.resource://res/raw/mah_buildings"));
			startActivity(i);

			//ALTERNATIVE SOLUTION VIA WEB
//			Intent mapIntent = new Intent(Intent.ACTION_VIEW); 
//			Uri uri = Uri.parse("geo:0,0?q=http://code.google.com/apis/kml/documentation/KML_Samples.kml"); 
//			mapIntent.setData(uri); 
//			startActivity(Intent.createChooser(mapIntent, "Sample")); 
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	//---search room function---
	public void find_button_navigation(View v) {

		// ---get the EditText view---
		AutoCompleteTextView txt_room_code = (AutoCompleteTextView) getView().findViewById(R.id.editText_find_room);
		// ---set the data to pass---
		RoomDbHandler dbHandler;
		String textInput = txt_room_code.getText().toString().toLowerCase(Locale.getDefault());
		String roomNr = selposFind + textInput;
		dbHandler = new RoomDbHandler(getActivity());
		Log.i("search room", "finished building dependencies");
		
		if (spinnerFind.getSelectedItemPosition() < 1){
			if(roomNr.isEmpty()) {
				Toast.makeText(getActivity(), getString(R.string.find_no_building_selected), Toast.LENGTH_LONG).show();
				return;
			}
			else{
				runNavigation(dbHandler, roomNr, R.string.find_no_building_selected);
				Log.i("search room", "text field filled, room number registered");
			}
		}
		else{
			if(textInput.isEmpty()){
				Log.i("search room", "no room number entered, only showing building");
				showBuilding(selposFind);
			}
			else if(textInput.matches("(or|g8|k2|k8|kl|as).*")){

				if(selposFind.equals(textInput.substring(0, 2))){
					//Log.i("testString", "substring: " + textInput.substring(0, 2));
					runNavigation(dbHandler, textInput, R.string.find_db_error);
				}
				else
					Toast.makeText(getActivity(), getString(R.string.find_building_dont_match), Toast.LENGTH_LONG).show();

			}
			else{
				runNavigation(dbHandler, roomNr, R.string.find_db_error);
			}
		}


		//Hiding the keyboard
		InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
	}

	private void runNavigation(RoomDbHandler dbHandler, String roomNr, int errorString) {
		Log.i("search room", "method to redirect to correct room initiated");
		if (dbHandler.isRoomExists(roomNr)) {
			//THIS IS WHERE THE REDIRECTION HAPPENS TO THE CORRECT FLOOR PLAN WITH MARKED PIN----------------------------------------------------------------------
			//MAYBE MODIFY THE METHOD showFloorMap OR MAKE A NEW ONE
			startNavigation(roomNr);
		}
		else if (dbHandler.isRoomExistsAll(roomNr)) {
			//go to floor maps
			Log.i("search room", "floor map shown");
			showFloorMap(dbHandler.getMapName());
			//Toast.makeText(getActivity(), "floorMapCode: "+dbHandler.getMapName(), Toast.LENGTH_LONG).show();
		}
		else
			Toast.makeText(getActivity(), getString(errorString), Toast.LENGTH_LONG).show();


	}
	
	

	private void showFloorMap(String floorMapCode) {
		Log.i("search room", "method to show correct floor map initiated");
		Fragment fragment = new FragmentFloorMap();
		Bundle args = new Bundle();
		args.putString(FragmentFloorMap.ARG_FLOORMAP, floorMapCode);
		fragment.setArguments(args);

		FragmentManager	 fragmentManager = getActivity().getSupportFragmentManager();

		FragmentTransaction fragmentTrans = fragmentManager.beginTransaction();	
		fragmentTrans.replace(R.id.content_frame, fragment);
		fragmentTrans.addToBackStack(null);
		fragmentTrans.commit();		
	}

	private void showBuilding(String buildingCode) {
		//THIS METHOD OPENS THE HOUSE FRAGMENT WITH THE SELECTED HOUSE DEPENDING ON INPUT PARAMETER buildingCode-----------------------------------------------------
		Log.i("search room", "method for showing building initiated");
		Fragment fragment = new FragmentBuilding();
		Bundle args = new Bundle();
		args.putString(FragmentBuilding.ARG_BUILDING, buildingCode);
		fragment.setArguments(args);

		FragmentManager	 fragmentManager = getActivity().getSupportFragmentManager();

		FragmentTransaction fragmentTrans = fragmentManager.beginTransaction();	
		fragmentTrans.replace(R.id.content_frame, fragment);
		fragmentTrans.addToBackStack(null);
		fragmentTrans.commit();
	}

	private void startNavigation(String roomNr) {

		Fragment fragment = new FragmentResult();
		Bundle args = new Bundle();
		args.putString(FragmentResult. ARG_ROOMNR, roomNr);
		args.putInt(FragmentResult.ARG_BUILDINGPOS, spin_selected);
		fragment.setArguments(args);
		FragmentManager	 fragmentManager = getActivity().getSupportFragmentManager();

		FragmentTransaction fragmentTrans = fragmentManager.beginTransaction();	
		fragmentTrans.replace(R.id.content_frame, fragment);
		fragmentTrans.addToBackStack(null);
		fragmentTrans.commit();
	}
	
	@Override
	public void onClick(View v) { 
//
//		if(v.getId() == R.id.house1){
//			selectBuilding(1);
//		}else if(v.getId() == R.id.house2){
//			selectBuilding(2);
//		}else if(v.getId() == R.id.house3){
//			selectBuilding(3);
//		}else if(v.getId() == R.id.house4){
//			selectBuilding(4);
//		}else if(v.getId() == R.id.house5){
//			selectBuilding(5);
//		}else if(v.getId() == R.id.house6){
//			selectBuilding(6);
//		}else if(v.getId() == R.id.house7){
//			selectBuilding(7);
//		}
//		
	}
//	
//	private void selectBuilding(int pos) {
//		String buildingCode;
//		Resources res = getResources();
//		String[] findCode = res
//				.getStringArray(R.array.find_building_code_array);
//		buildingCode = findCode[pos];
//		showBuildingMap(buildingCode);
//	}
//	
//	
//	private void showBuildingMap(String buildingCode) {
//		//COPIED FROM THE OLD CODE*******************************************************
//		Log.i("search room", "method to show correct building's floor map initiated");
//		Fragment fragment = new FragmentMaps();
//		Bundle args = new Bundle();
//		args.putString(FragmentMaps.ARG_BUILDING, buildingCode);
//		fragment.setArguments(args);
//
//		FragmentManager	 fragmentManager = getActivity().getSupportFragmentManager();
//		FragmentTransaction fragmentTrans = fragmentManager.beginTransaction();	
//		fragmentTrans.replace(R.id.content_frame, fragment);
//		fragmentTrans.addToBackStack(null);
//		fragmentTrans.commit();		
//	}
	
	

	@Override
 public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(FIND_SPINNER_STATE, spin_selected);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		//Log.i("project", "onActivityCreated");
		super.onActivityCreated(savedInstanceState);

		if (savedInstanceState != null) {
			//Log.i("project", "onActivityCreated save1 " + savedInstanceState.getInt(FIND_SPINNER_STATE));
			spin_selected = savedInstanceState.getInt(FIND_SPINNER_STATE);
		}

	}
}
