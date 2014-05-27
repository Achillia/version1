package se.mah.kd330a.project.find.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.DisplayMetrics;
import android.util.Log;

public class GetImage {

	public static boolean doesFileExists(String filename,Context c){
		File f = c.getFileStreamPath(filename);
		return f.exists();
	}

	public static boolean deletefile(String filename, Context c){
		c.deleteFile(filename);
		return !doesFileExists(filename,c);
	}
	//Checks if a floorplan is in local storage. Automaticly checking for the correct image version.
	public static boolean doesImageFromLocalStorageExists(String filename,Context c){
		File fname=new File(c.getFilesDir(),BuildingHelper.FLOOR_PLAN_IMAGE_VERSION+"_"+ filename);
		return fname.exists();
	}

	///Gets a picture from local storage. Will return null if the context is null, since this might be called async. Automaticly checking for the correct image version
	public static Bitmap getImageFromLocalStorage(String filename, Context c){
		if(c!=null)
		{
			String fname=new File(c.getFilesDir(), BuildingHelper.FLOOR_PLAN_IMAGE_VERSION+ "_" +filename).getAbsolutePath();
			Options bitmapOptions = new Options();
			bitmapOptions.inMutable = true;
			bitmapOptions.inTempStorage = new byte[16*1024];
			bitmapOptions.inPurgeable = true;
			Bitmap bitmap = BitmapFactory.decodeFile(fname, bitmapOptions);
			
			return bitmap;
		}
		else
		{
			Log.e("find", "Seems like the activity context was null");
			return null;
		}
	}

	//gets a picture from the net should be enclosed in AsyncTask
	public static Bitmap getImageFromNet(String filename, boolean storeImageLocally, Context c){
		String imageUrl = "http://195.178.234.7/mahapp/pictlib.aspx?filename="+filename+"&resolution="+getResolution(c);
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageUrl).getContent());
			if (storeImageLocally){
				storeImageLocal(BuildingHelper.FLOOR_PLAN_IMAGE_VERSION+ "_" +filename,bitmap,c);
				return bitmap;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public static String getResolution(Context c) {
		switch (c.getResources().getDisplayMetrics().densityDpi) {
		case DisplayMetrics.DENSITY_MEDIUM:
			return "mdpi";
		case DisplayMetrics.DENSITY_HIGH:
			return "hdpi";
		case DisplayMetrics.DENSITY_XHIGH:
			return "xhdpi";   
		case DisplayMetrics.DENSITY_XXHIGH:
			return "xxhdpi";   
		default:
			return "mdpi";
		}
	}
	//Stores a bitmap to the file system. (Only called from this class)
	private static boolean storeImageLocal(String filename, Bitmap b, Context c){
		boolean success = false;
		try {
			FileOutputStream fos = c.openFileOutput(filename, Context.MODE_PRIVATE);
			if(filename.endsWith(".png")){
				success = b.compress(Bitmap.CompressFormat.PNG, 100, fos); 
			}else if (filename.endsWith(".jpg")){
				success = b.compress(Bitmap.CompressFormat.JPEG, 100, fos);
			}
			if (success){
				fos.close();
				doesFileExists(filename,c);
				success = true;
			}
		} catch (Exception e) {
			Log.e("find", "We crashed: " + e.getMessage());
			e.printStackTrace();
		} 
		return success;
	}

}
