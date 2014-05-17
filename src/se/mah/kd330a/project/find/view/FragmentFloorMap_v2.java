package se.mah.kd330a.project.find.view;
import se.mah.kd330a.project.R;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

// Use

public final class FragmentFloorMap_v2 extends Fragment {
    private static final String KEY_CONTENT = "TestFragment:Content";
    private ToggledViewPager viewPager;
    private int building_number;
    public static FragmentFloorMap_v2 newInstance(int building_number, int position, ToggledViewPager tvp) {
        FragmentFloorMap_v2 fragment = new FragmentFloorMap_v2();
        fragment.viewPager = tvp;
        fragment.building_number = building_number;
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
    	
    	
    	//Get img from database(Will be used later):	bitmap = getImageFromDatabase(building_number, mPosition);
    	
    	
    	// Temporary code. Will get replaced with code pulling image from database
    	if(building_number == 0)
    	{
			switch(mPosition){
			case 0:
				bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.home_block);
				break;
			case 1:
				bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mah_logotyp_original);
				break;
			case 2:
				bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.map_view);
				break;
			case 3:
				bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.splash_view);
				break;
	
			default:
				Log.i("julia", "Not implemented");
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
				break;
			}
    	}
		myImageView.setImageBitmap(bitmap);
        return v;
    }
    
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
    }
}
