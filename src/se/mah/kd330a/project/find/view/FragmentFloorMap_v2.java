package se.mah.kd330a.project.find.view;
import se.mah.kd330a.project.R;
import se.mah.kd330a.project.find.data.BuildingHelper;
import se.mah.kd330a.project.find.data.GetImage;
import se.mah.kd330a.project.find.data.ImageLoader;
import se.mah.kd330a.project.find.data.ImageLoader.OnImageLoaderListener;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

// Use

public final class FragmentFloorMap_v2 extends Fragment  implements OnImageLoaderListener {
    private static final String KEY_CONTENT = "TestFragment:Content";
    private ToggledViewPager viewPager;
    private String building_code;
    public static FragmentFloorMap_v2 newInstance(String building_code, int position, ToggledViewPager tvp) {
        FragmentFloorMap_v2 fragment = new FragmentFloorMap_v2();
        fragment.viewPager = tvp;
        fragment.building_code = building_code;
        
/*	
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            builder.append(content).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        fragment.mContent = builder.toString();
*/
        fragment.mPosition = position;
        return fragment;
    }
    private int mPosition = 0;
    private String mContent = "???";
    public void StartImageDownload()
    {
    	String imageName = BuildingHelper.GetFloorPlanImage(building_code, mPosition);
    	if(bitmap!=null)
    		bitmap.recycle();
    	if(GetImage.doesImageFromLocalStorageExists(imageName, getActivity()))
    	{
    		Log.i("julia", "Using cached image: "+ imageName);
    		bitmap = GetImage.getImageFromLocalStorage(imageName, getActivity());
    		if(bitmap!=null)
    		{
    			if(myImageView!=null)
    			{
	    			myImageView.setImageBitmap(bitmap);
	    			//return;
    			}
    			else
    			{
    				Log.e("julia", "Why is that one null?");
    			}
    		}
    		else
    			Log.e("julia", "Image was null! We will redownload it instead of crashing.");
    	}
    	Log.i("julia", "Downloading image: "+ imageName);
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
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View v =  inflater.inflate(R.layout.fragment_screen_find_floorplan, container, false);

    	
    //mPosition is the position in the pager.
   	
     //  int position = getArguments().getInt("position");
    	
   
    	//Touch event related variables
    	myImageView = (ZoomableImageView)v.findViewById(R.id.img_floorplan);
    	//myImageView.setScaleType(ImageView.ScaleType.MATRIX);
    	myImageView.SetToggledViewPager(this.viewPager);
    	myImageView.saveScale = 1;
    	StartImageDownload();
    	
    	//Get img from database(Will be used later):	bitmap = getImageFromDatabase(building_number, mPosition);
    	
    	
    	// Temporary code. Will get replaced with code pulling image from database
    	/*if(building_code != "")
    	{
			switch(mPosition){
			case 0:
				bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.orkanen_plan_4_400px);
				break;
			case 1:
				bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.orkanen_plan_4_300px);
				break;
			case 2:
				bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.orkanen_plan_4_200px);
				break;
			case 3:
				bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.orkanen_plan4_pride);
				break;
	
			default:
				Log.i("julia", "Not implemented");
				bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.home_block);
				break;
			}
    	}
    	else
    	{
			switch(mPosition){
			case 0:
				bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.splash_view);
				break;
			case 1:
				bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.map_view);
				break;
			case 2:
				bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mah_logotyp_original);
				break;
			case 3:
				bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.home_block);
				break;
	
			default:
				Log.i("julia", "Not implemented");
				bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.home_block);
				break;
			}
    	}*/
		
        return v;
    }
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	if(bitmap!=null)
    		bitmap.recycle();
    };
    @Override
    public void onDestroyView() {
    	super.onDestroyView();
    	if(bitmap!=null)
    		bitmap.recycle();
    }; 
   
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
    	
    }
    @Override
	public void onImageReceived(String fileName) {
    	if(bitmap!=null)
    		bitmap.recycle();
    	bitmap = GetImage.getImageFromLocalStorage(fileName, getActivity());

		if(bitmap!=null)
		{
			if(myImageView!=null)
			{
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
