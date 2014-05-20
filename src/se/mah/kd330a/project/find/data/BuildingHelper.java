package se.mah.kd330a.project.find.data;

import se.mah.kd330a.project.R;
import android.content.res.Resources;
import android.graphics.Bitmap;

// This class is a helping class where you get informations about the buildings

public class BuildingHelper {

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
			return  "Citadellsvägen 7";
			 
		}
		return "Östra Varvsgatan 11 A";

	}
}
