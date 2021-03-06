package se.mah.kd330a.project.find.view;

import java.util.ArrayList;
import java.util.List;

import se.mah.kd330a.project.R;
import se.mah.kd330a.project.find.data.BuildingHelper;
import se.mah.kd330a.project.find.data.RoomDbHandler;
import se.mah.kd330a.project.find.data.RoomDbHandler.Room;
import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

public class FragmentSearchResultList extends DialogFragment {

	List<Room> list;
	@Override
	public void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	};
	@Override
	public View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container, android.os.Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View v =  inflater.inflate(R.layout.fragment_screen_find_searchresultlist, container, false);
		Bundle packet = getArguments();
		String searchString = packet.getString(BuildingHelper.ARG_SEARCHSTRING);

		ListView listview = (ListView)v.findViewById(R.id.searchResultList);

		list = RoomDbHandler.getInstance().SearchForRooms(searchString);//new ArrayList<Room>();

		if(list.size()==0)
			getDialog().setTitle("No results!");
		else
			getDialog().setTitle("Search results");

		final StableArrayAdapter adapter = new StableArrayAdapter(getActivity(),R.layout.fragment_screen_find_searchresultlist_item, list);
		listview.setAdapter(adapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			//When an item in the list is clicked, we open the floorplan with a pin on the room.
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				
				Log.i("find", "You clicked on " + position);

				Fragment fragment = BuildingHelper.getFragmentBuildingMapForRoom( list.get(position));
				FragmentManager	 fragmentManager = getActivity().getSupportFragmentManager();
				FragmentTransaction fragmentTrans = fragmentManager.beginTransaction();
				//Popping the stack so we don't have multiple of the fragmentfloormaps open, from clicking around or searching multiple times.
				fragmentManager.popBackStack();
				fragmentTrans.replace(R.id.content_frame, fragment);
				fragmentTrans.addToBackStack(null);
				fragmentTrans.commit();	
				dismiss();

			}
		});

		return v;
	};

	private class StableArrayAdapter extends ArrayAdapter<Room> {
		List<Room> mRooms =  new ArrayList<Room>();
		public StableArrayAdapter(Context context, int textViewResourceId,
				List<Room> objects) {
			super(context, textViewResourceId, objects);
			for (int i = 0; i < objects.size(); ++i) {
				mRooms.add(objects.get(i));
			}
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

	}

}
