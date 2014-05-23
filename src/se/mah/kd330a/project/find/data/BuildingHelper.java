package se.mah.kd330a.project.find.data;

import se.mah.kd330a.project.R;
import se.mah.kd330a.project.find.data.RoomDbHandler.Room;
import se.mah.kd330a.project.find.view.FragmentMaps;
import se.mah.kd330a.project.find.view.SampleTabsDefault;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

// This class is a helping class where you get informations about the buildings

public class BuildingHelper {

	public static final String ARG_BUILDING = "building";
	public static final String ARG_FLOORINDEX= "floorindex";
	public static final String ARG_SEARCHSTRING= "searchstring";
	public static final String ARG_ROOMNAME= "roomnamestring";
	
	public static final int FLOOR_PLAN_IMAGE_VERSION = 2;
	public static String GetBuildingFloorPlanTitle(String building_code, int position, Resources res)
	{

		String[] floorPlans =GetFloorPlanArray(building_code, res);
		if(floorPlans!=null)
			return floorPlans[position];
		else return "We don't know.";
		
		
	}
	 public static String[] GetFloorPlanArray(String building_code, Resources res)
	 {
			if(building_code.equals("k2"))
			{
				return res.getStringArray(R.array.find_floorK2_array);
				 
			}
			if(building_code.equals("k8"))
			{
				return  res.getStringArray(R.array.find_floorK8_array);
				 
			}
			if(building_code.equals("or"))
			{
				return  res.getStringArray(R.array.find_floorOr_array);
				 
			}
			if(building_code.equals("as"))
			{
				return  res.getStringArray(R.array.find_floorAs_array);
				 
			}
			if(building_code.equals("kl"))
			{
				return  res.getStringArray(R.array.find_floorKl_array);
				 
			}
			if(building_code.equals("g8"))
			{
				return  res.getStringArray(R.array.find_floorG8_array);
				 
			}
			return null;
	 }
	public static int FloorCount(String building_code, Resources res){
				
		String[] currentFloorPlans = GetFloorPlanArray(building_code, res);
		if(currentFloorPlans!=null)
			return currentFloorPlans.length;
		return 0;
	}
	
	public static String GetLocation(String building_code){
		if(building_code.equals("k2"))
		{
			return "Östra Varvsgatan 11 A";
			 
		}
		if(building_code.equals("k8"))
		{
			return  "Östra Varvsgatan 11 A";
			 
		}
		if(building_code.equals("or"))
		{
			return  "Nordenskiöldsgatan 10";
			 
		}
		if(building_code.equals("as"))
		{
			return  "Jan Waldenstroms gata 25";
			 
		}
		if(building_code.equals("kl"))
		{
			return  "Carl Gustafs vag 34";
			 
		}
		if(building_code.equals("g8"))
		{
			return  "Citadellsvägen 7, 21118, ";
			 
		}
		return "Östra Varvsgatan 11 A";

	}
	public static String GetFloorPlanImage(String building_code, int floorIndex){
		String resultString = building_code +"_";
		if(building_code.equals("k2"))
			switch(floorIndex)	
			{
				case 0: 
					resultString+="a";
					break;
				case 1:
					resultString+="b";
					break;
				case 2:
					resultString+="c";
					break;
				case 3:
					resultString+="d";
					break;
					
			}
		else
			resultString+=(floorIndex+1)+"";
		resultString += ".png";
		return resultString;
	}
	public static int ConvertFloorNameToFloorIndex(String floor_name)
	{
		int floor_index=0;
		try{
			floor_index = Integer.parseInt(floor_name);
			floor_index--;
		}
		catch(NumberFormatException nfe)
		{
			if(floor_name.equalsIgnoreCase("a"))
				floor_index = 0;
			if(floor_name.equalsIgnoreCase("b"))
				floor_index = 1;
			if(floor_name.equalsIgnoreCase("c"))
				floor_index = 2;
			if(floor_name.equalsIgnoreCase("d"))
				floor_index = 3;
			if(floor_name.equalsIgnoreCase("e"))
				floor_index = 4;
			if(floor_name.equalsIgnoreCase("f"))
				floor_index = 5;
		}
		return floor_index;
	}

	//returns the fragment with the building map of the specified building.
	public static Fragment getFragmentBuildingMap(String buildingCode) {
		SampleTabsDefault fragment = new SampleTabsDefault();
		Bundle args = new Bundle();
		args.putString(BuildingHelper.ARG_BUILDING, buildingCode);
		fragment.setArguments(args);
		return fragment;

	}
	//returns the fragment with the building map of the specified building. And opens the floor at floor INDEX.
	public static Fragment getFragmentBuildingMapForRoom(Room room) {
		int floorindex = ConvertFloorNameToFloorIndex(room.floor_name);
		SampleTabsDefault fragment = new SampleTabsDefault();
		Bundle args = new Bundle();
		args.putString(BuildingHelper.ARG_BUILDING, room.building_code);
		args.putInt(BuildingHelper.ARG_FLOORINDEX, floorindex);
		args.putString(BuildingHelper.ARG_ROOMNAME, room.roomNr);
		fragment.setArguments(args);
		return fragment;
		
	}
}
