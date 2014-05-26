package se.mah.kd330a.project.find.view;
import se.mah.kd330a.project.R;
import se.mah.kd330a.project.find.data.BuildingHelper;
import se.mah.kd330a.project.find.data.GetImage;
import se.mah.kd330a.project.find.data.ImageLoader;
import se.mah.kd330a.project.find.data.ImageLoader.OnImageLoaderListener;
import se.mah.kd330a.project.find.data.RoomDbHandler.Room;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.FloatMath;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;

public final class FragmentFloorMap extends Fragment  implements OnImageLoaderListener {
    private static final String KEY_CONTENT = "TestFragment:Content";
    private ToggledViewPager viewPager;
    private String building_code;
    private Room specificRoom;
    //Creates a new instance of this fragment. It is used in the page viewer.
    public static FragmentFloorMap newInstance(String building_code, int position, Room r, ToggledViewPager tvp) {
        FragmentFloorMap fragment = new FragmentFloorMap();
        fragment.viewPager = tvp;
        fragment.building_code = building_code;
        fragment.specificRoom = r;
        fragment.mPosition = position;
        return fragment;
    }
    private int mPosition = 0;
    private String mContent = "???";
    //Creates a pin based on the data in the class. If the fragment was instantiated with a specific room, a pin on that room will be shown.
    public void PutPinOnBitmap(String imageName)
    {
    	if(specificRoom!=null && specificRoom.GetFloorplanFilename().endsWith(imageName) && specificRoom.x!=0 && specificRoom.y!=0)
		{
		    Canvas canvas = new Canvas(bitmap);
		    Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
		    Bitmap overlay_ori = BitmapFactory.decodeResource(getActivity().getResources(),
                    R.drawable.find_pin);
		    float scale = 2f; //Change the size of the pin.
		    int pinWidth = Math.round(50*scale); //The pin is 50 pixels in width
		    int pinHeight = Math.round(60*scale); //The pin is 60 pixels in height
		    Bitmap overlay = Bitmap.createScaledBitmap(overlay_ori,  pinWidth, pinHeight, true);
		    canvas.drawBitmap(overlay, specificRoom.x-Math.round(pinWidth/2f), specificRoom.y-pinHeight, paint);
		    overlay.recycle();
		    overlay = null;
		    canvas = null;
		}	
    
    }
    //Starts the download of the image, or uses the local storage cached version if it exists.
    public void StartImageDownload()
    {

        spinner.setVisibility(View.VISIBLE);
       

    	String imageName = BuildingHelper.GetFloorPlanImage(building_code, mPosition);
    	//Force garbage collector. Might not be needed anymore.
    	System.gc();
    	if(bitmap!=null) //Make sure we recycle the old image first. Just in case.
    	{
    		bitmap.recycle();
    		bitmap = null;
    	}
    	if(GetImage.doesImageFromLocalStorageExists(imageName, getActivity()))
    	{
    		Log.i("julia", "Cached " + imageName);
    		bitmap = GetImage.getImageFromLocalStorage(imageName, getActivity());
    		//If we find a room and we are on the correct floor plan, we want to show a pin.
    		if(bitmap!=null)
    		{
    			PutPinOnBitmap(imageName);
    			if(myImageView!=null)
    			{
    				spinner.setVisibility(View.GONE);
	    			myImageView.setImageBitmap(bitmap);
	    			return;
    			}
    			else
    			{
    				Log.e("julia", "Why is that one null?");
    			}
    		}
    		else
    			Log.e("julia", "Image was null! We will redownload it instead of crashing.");
    	}
    	Log.i("julia", "Downloading " + imageName);
    	new ImageLoader(getActivity(), this).execute(imageName);
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        } 

    }

    ZoomableImageView myImageView;
    Bitmap bitmap;
    ProgressBar spinner;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View v =  inflater.inflate(R.layout.fragment_screen_find_floorplan, container, false);

    	
    	myImageView = (ZoomableImageView)v.findViewById(R.id.img_floorplan);
    	myImageView.SetToggledViewPager(this.viewPager);
    	myImageView.saveScale = 1;

    	spinner = (ProgressBar) v.findViewById(R.id.pb_find_loading);
    	spinner.setVisibility(View.VISIBLE);  
    	StartImageDownload();

        return v;
    }
  
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
    	
    }
    //Cleans up everything in this fragment. It will be unusable after this has been run.
    public void CleanUp(){
    	if(bitmap!=null)
    	{
    		bitmap.recycle(); //Recycle the bitmap, make sure it is no longer in memory.
    	}
    	if(myImageView!=null) 
    	{
    		myImageView.setImageResource(android.R.color.transparent);//Resets the ImageView to a solid transparent color, to make sure it is not referencing the bitmap.
    	}
    	//Remove all references
    	bitmap = null;
    	viewPager = null;
    	specificRoom = null;
    	myImageView = null;
    	//Force garbage collector. Might not be needed anymore.
    	System.gc();
    	
    }
    
    @Override
    public void onDetach() {
    	super.onDetach();
    	
    	CleanUp();
    };
    
    //When the image has been downloaded, we set it on the image view.
    @Override
	public void onImageReceived(String fileName) {
    	if(bitmap!=null)
    	{
    		Log.e("julia", "We forgot to clean up last time. This should not be called.");
    		bitmap.recycle();
    	}
    	bitmap = GetImage.getImageFromLocalStorage(fileName, getActivity());
    	
		if(bitmap!=null)
		{
			PutPinOnBitmap(fileName);
			if(myImageView!=null)
			{
				spinner.setVisibility(View.GONE);
				myImageView.setImageBitmap(bitmap);
				return;
			}
			else
			{
				Log.e("julia", "So we downloaded the picture but the view has probably been destroyed.?");
			}
			
		}
		else
			Log.e("julia", "Failed to set the image to the control, because the image was null!");
	}
}
