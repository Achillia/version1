package se.mah.kd330a.project.find.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import se.mah.kd330a.project.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

/*
 *	Database handler class for searching for rooms 
 *	The database created when the app runs the first time 
 *	and it adds all the data for the rooms too. (Need to update)
 *	With the function isRoomExists(String roomNr) can make a search
 *	in the database. If it returns true then with the appropriate 
 *	function you can access the stored data for the room.
 *	functions are: getRoomPathPics, getPathPicTexts, getMapName ...
 *
 *
EXAMPLE OF HOW TO FIND A ROOM AND A FRAGMENT
		Room r = RoomDbHandler.getInstance().FindRoom("ORF208");
		if(r!=null)
		{
			Fragment fragment = BuildingHelper.getFragmentBuildingMapForRoom(r);
			
			//Open the fragment as usual:
			FragmentManager	 fragmentManager = getActivity().getSupportFragmentManager();
			FragmentTransaction fragmentTrans = fragmentManager.beginTransaction();
			fragmentTrans.replace(R.id.content_frame, fragment);
			fragmentTrans.addToBackStack(null);
			fragmentTrans.commit();	
			//End of open fragment.
		}else
		{
			//No room found.
		}
 *
 *
 *
 *
 *
 */

public class RoomDbHandler extends SQLiteOpenHelper {
	//Class for containing a room
    public class Room{
    	public String roomNr;
    	public int x;
    	public int y;
    	public String building_code;
    	public String floor_name;
    	public Room(String rNr)
    	{
    		this.roomNr = rNr;
    	}    	
    	//Overriding toString for when it is used in the search listview, each element there will be written with this string.
    	@Override
    	public String toString() {
    		
    		return roomNr;
    	}
    	//Bruteforce way of finding the floorplan png name for this room.
    	public String GetFloorplanFilename()
    	{
    		return building_code + "_" + floor_name +  ".png";
    	}
    }
	private static final String DATABASE_NAME = "find_rooms_DB";
	private static final int DATABASE_VERSION =5; //Remember to increment this number if there has been made changes to the database!
	private static RoomDbHandler instance = null;

	//Basic singleton. This class can only be instantiated by the Init static function.
	public static RoomDbHandler getInstance() {
		if(instance == null) {
			Log.e("julia", "database is not ready PREPARE FOR CRASH!!");
			return  null;
		}
		return instance;
	}

	static final String TABLE_CREATE = "CREATE TABLE rooms (" + BaseColumns._ID + " int primary key, roomNr TEXT, x INTEGER, y INTEGER, building_code TEXT, floor_name TEXT);";
	//Call this function in the beginning of the application, before anyone accesses the database. To make sure it is prepaired and ready for use.
	public static void Init(Context context)
	{
		Log.i("julia", "creating a db reference");
		instance = new RoomDbHandler(context);
	}
	//Private constructor that intializes the database.
	private RoomDbHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		Log.i("julia", "Fixing the database");
	}
	//Creates all the data in the database.
	private void dbCreate(SQLiteDatabase db) {
		Log.i("julia", "Creating database");
		db.execSQL(TABLE_CREATE);

		addRow(db, "K2C107", 0, 0, "k2", "c");
		addRow(db, "K2C108", 0, 0, "k2", "c");
		addRow(db, "K2C109", 0, 0, "k2", "c");
		addRow(db, "K2C110", 0, 0, "k2", "c");
		addRow(db, "K2C117", 0, 0, "k2", "c");
		addRow(db, "K2C119", 0, 0, "k2", "c");
		addRow(db, "K2C121", 0, 0, "k2", "c");
		addRow(db, "K2C122", 0, 0, "k2", "c");
		addRow(db, "K2C123", 0, 0, "k2", "c");
		addRow(db, "K2C124A", 0, 0, "k2", "c");
		addRow(db, "K2C124B", 0, 0, "k2", "c");
		addRow(db, "K2C133", 0, 0, "k2", "c");
		addRow(db, "K2C135A", 0, 0, "k2", "c");
		addRow(db, "K2C135B", 0, 0, "k2", "c");
		addRow(db, "K2C136", 0, 0, "k2", "c");
		addRow(db, "K2C138", 0, 0, "k2", "c");
		addRow(db, "K2C307", 0, 0, "k2", "c");
		addRow(db, "K2C308", 0, 0, "k2", "c");
		addRow(db, "K2C309", 0, 0, "k2", "c");
		addRow(db, "K2C310", 0, 0, "k2", "c");
		addRow(db, "K2C311", 0, 0, "k2", "c");
		addRow(db, "K2C314", 0, 0, "k2", "c");
		addRow(db, "K2C316", 0, 0, "k2", "c");
		addRow(db, "K2C320", 0, 0, "k2", "c");
		addRow(db, "K2C323", 0, 0, "k2", "c");
		addRow(db, "K2C325", 0, 0, "k2", "c");
		addRow(db, "K2C332", 0, 0, "k2", "c");
		addRow(db, "K2C333", 0, 0, "k2", "c");
		addRow(db, "K2C339", 0, 0, "k2", "c");
		addRow(db, "K2C340", 0, 0, "k2", "c");
		addRow(db, "K2C341", 0, 0, "k2", "c");
		addRow(db, "K2C342", 0, 0, "k2", "c");
		addRow(db, "K2C343", 0, 0, "k2", "c");
		addRow(db, "K2C345", 0, 0, "k2", "c");
		addRow(db, "K2C346", 0, 0, "k2", "c");
		addRow(db, "K2C347", 0, 0, "k2", "c");
		addRow(db, "K2C348", 0, 0, "k2", "c");
		addRow(db, "K2C349", 0, 0, "k2", "c");
		addRow(db, "K2C350", 0, 0, "k2", "c");
		addRow(db, "K2C351", 0, 0, "k2", "c");
		addRow(db, "K2C352", 0, 0, "k2", "c");
		addRow(db, "K2C353", 0, 0, "k2", "c");
		addRow(db, "K2B105", 0, 0, "k2", "b");
		addRow(db, "K2B106", 0, 0, "k2", "b");
		addRow(db, "K2B107", 0, 0, "k2", "b");
		addRow(db, "K2B108", 0, 0, "k2", "b");
		addRow(db, "K2B109", 0, 0, "k2", "b");
		addRow(db, "K2B110", 0, 0, "k2", "b");
		addRow(db, "K2B111", 0, 0, "k2", "b");
		addRow(db, "K2B117", 0, 0, "k2", "b");
		addRow(db, "K2B118", 0, 0, "k2", "b");
		addRow(db, "K2B119", 0, 0, "k2", "b");
		addRow(db, "K2B202", 0, 0, "k2", "b");
		addRow(db, "K2B203", 0, 0, "k2", "b");
		addRow(db, "K2B204", 0, 0, "k2", "b");
		addRow(db, "K2B210", 0, 0, "k2", "b");
		addRow(db, "K2B211", 0, 0, "k2", "b");
		addRow(db, "K2B212", 545, 290, "k2", "b");
		addRow(db, "K2B205", 0, 0, "k2", "b");
		addRow(db, "K2B206", 0, 0, "k2", "b");
		addRow(db, "K2B207", 0, 0, "k2", "b");
		addRow(db, "K2B208", 0, 0, "k2", "b");
		addRow(db, "K2B209", 0, 0, "k2", "b");
		addRow(db, "K2B302", 0, 0, "k2", "b");
		addRow(db, "K2B303", 0, 0, "k2", "b");
		addRow(db, "K2B304", 0, 0, "k2", "b");
		addRow(db, "K2B305", 0, 0, "k2", "b");
		addRow(db, "K2B306", 0, 0, "k2", "b");
		addRow(db, "K2B352", 0, 0, "k2", "b");
		addRow(db, "K2B353", 0, 0, "k2", "b");
		addRow(db, "K2A126", 0, 0, "k2", "a");
		addRow(db, "K2A129", 0, 0, "k2", "a");
		addRow(db, "K2A130", 0, 0, "k2", "a");
		addRow(db, "K2A131", 0, 0, "k2", "a");
		addRow(db, "K2A132", 0, 0, "k2", "a");
		addRow(db, "K2A104", 0, 0, "k2", "a");
		addRow(db, "K2A134", 0, 0, "k2", "a");
		addRow(db, "K2A136", 0, 0, "k2", "a");
		addRow(db, "K2A133", 0, 0, "k2", "a");
		addRow(db, "K2A135", 0, 0, "k2", "a");
		addRow(db, "K2A137", 0, 0, "k2", "a");
		addRow(db, "K2A138", 0, 0, "k2", "a");
		addRow(db, "K2A139", 0, 0, "k2", "a");
		addRow(db, "K2A140", 0, 0, "k2", "a");
		addRow(db, "K2A146", 0, 0, "k2", "a");
		addRow(db, "K2A150", 0, 0, "k2", "a");
		addRow(db, "K2A164", 0, 0, "k2", "a");
		addRow(db, "K2A142", 0, 0, "k2", "a");
		addRow(db, "K2A160", 0, 0, "k2", "a");
		addRow(db, "K2A161", 0, 0, "k2", "a");
		addRow(db, "K2A162", 0, 0, "k2", "a");
		addRow(db, "K2A163", 0, 0, "k2", "a");
		addRow(db, "K2A202", 0, 0, "k2", "a");
		addRow(db, "K2A203", 0, 0, "k2", "a");
		addRow(db, "K2A204", 0, 0, "k2", "a");
		addRow(db, "K2A209", 0, 0, "k2", "a");
		addRow(db, "K2A205", 0, 0, "k2", "a");
		addRow(db, "K2A206", 0, 0, "k2", "a");
		addRow(db, "K2A207", 0, 0, "k2", "a");
		addRow(db, "K2A302", 0, 0, "k2", "a");
		addRow(db, "K2A303", 0, 0, "k2", "a");
		addRow(db, "K2A304", 0, 0, "k2", "a");
		addRow(db, "K2A305", 0, 0, "k2", "a");
		addRow(db, "K2A306", 0, 0, "k2", "a");
		addRow(db, "K2A307", 0, 0, "k2", "a");
		addRow(db, "K2A308", 0, 0, "k2", "a");
		addRow(db, "K2A309", 0, 0, "k2", "a");
		addRow(db, "K2D155", 0, 0, "k2", "d");
		addRow(db, "K2D156", 0, 0, "k2", "d");
		addRow(db, "K2D157", 0, 0, "k2", "d");
		addRow(db, "K2D202", 0, 0, "k2", "d");
		addRow(db, "K2D204", 0, 0, "k2", "d");
		addRow(db, "K2D205", 0, 0, "k2", "d");
		addRow(db, "K2D213", 0, 0, "k2", "d");
		addRow(db, "K2D214", 0, 0, "k2", "d");
		addRow(db, "ORD131", 0, 0, "or", "1");
		addRow(db, "ORD138", 0, 0, "or", "1");
		addRow(db, "ORC236", 0, 0, "or", "2");
		addRow(db, "ORD222", 0, 0, "or", "2");
		addRow(db, "ORE222", 0, 0, "or", "2");
		addRow(db, "ORE223", 0, 0, "or", "2");
		addRow(db, "ORE235", 0, 0, "or", "2");
		addRow(db, "ORE239", 0, 0, "or", "2");
		addRow(db, "ORE240", 0, 0, "or", "2");
		addRow(db, "ORE477", 0, 0, "or", "2");
		addRow(db, "ORF206", 0, 0, "or", "2");
		addRow(db, "ORF208", 0, 0, "or", "2");
		addRow(db, "ORF209", 0, 0, "or", "2");
		addRow(db, "ORF211", 0, 0, "or", "2");
		addRow(db, "ORF215", 0, 0, "or", "2");
		addRow(db, "ORF219", 0, 0, "or", "2");
		addRow(db, "ORF220", 0, 0, "or", "2");
		addRow(db, "ORC323", 0, 0, "or", "3");
		addRow(db, "ORC333", 0, 0, "or", "3");
		addRow(db, "ORC334", 0, 0, "or", "3");
		addRow(db, "ORC335", 0, 0, "or", "3");
		addRow(db, "ORC336", 0, 0, "or", "3");
		addRow(db, "ORC339", 0, 0, "or", "3");
		addRow(db, "ORC340", 0, 0, "or", "3");
		addRow(db, "ORC341", 0, 0, "or", "3");
		addRow(db, "ORC344", 0, 0, "or", "3");
		addRow(db, "ORC345", 0, 0, "or", "3");
		addRow(db, "ORC377", 0, 0, "or", "3");
		addRow(db, "ORD326", 0, 0, "or", "3");
		addRow(db, "ORD328", 0, 0, "or", "3");
		addRow(db, "ORD331", 0, 0, "or", "3");
		addRow(db, "ORD337", 0, 0, "or", "3");
		addRow(db, "ORD377", 0, 0, "or", "3");
		addRow(db, "ORE323", 0, 0, "or", "3");
		addRow(db, "ORE336", 0, 0, "or", "3");
		addRow(db, "ORE337", 0, 0, "or", "3");
		addRow(db, "ORE340", 0, 0, "or", "3");
		addRow(db, "ORE341", 0, 0, "or", "3");
		addRow(db, "ORF306", 0, 0, "or", "3");
		addRow(db, "ORF307", 0, 0, "or", "3");
		addRow(db, "ORF309", 0, 0, "or", "3");
		addRow(db, "ORF312", 0, 0, "or", "3");
		addRow(db, "ORF314", 0, 0, "or", "3");
		addRow(db, "ORF315", 0, 0, "or", "3");
		addRow(db, "ORC435", 0, 0, "or", "4");
		addRow(db, "ORC436", 0, 0, "or", "4");
		addRow(db, "ORC440", 0, 0, "or", "4");
		addRow(db, "ORD436", 0, 0, "or", "4");
		addRow(db, "ORE436", 0, 0, "or", "4");
		addRow(db, "ORE439", 0, 0, "or", "4");
		addRow(db, "ORE477", 0, 0, "or", "4");
		addRow(db, "ORC525", 0, 0, "or", "5");
		addRow(db, "ORC526", 0, 0, "or", "5");
		addRow(db, "ORC527", 0, 0, "or", "5");
		addRow(db, "ORC528", 0, 0, "or", "5");
		addRow(db, "K8U042", 0, 0, "k8", "1");
		addRow(db, "K8U301", 0, 0, "k8", "3");
		addRow(db, "K8U302", 0, 0, "k8", "3");
		addRow(db, "K8U303", 0, 0, "k8", "3");
		addRow(db, "K8U304", 0, 0, "k8", "3");
		addRow(db, "K8U305", 0, 0, "k8", "3");
		addRow(db, "K8U306", 0, 0, "k8", "3");
		addRow(db, "K8U307", 0, 0, "k8", "3");
		addRow(db, "K8U401", 0, 0, "k8", "4");
		addRow(db, "K8U402", 0, 0, "k8", "4");
		addRow(db, "K8U403", 0, 0, "k8", "4");
		addRow(db, "K8U404", 0, 0, "k8", "4");
		addRow(db, "K8U405", 0, 0, "k8", "4");
		addRow(db, "K8U406", 0, 0, "k8", "4");
		addRow(db, "K8U407", 0, 0, "k8", "4");
		addRow(db, "K8U408", 0, 0, "k8", "4");
		addRow(db, "K8U428", 0, 0, "k8", "4");
		addRow(db, "K8U429", 0, 0, "k8", "4");
		addRow(db, "K8U430", 0, 0, "k8", "4");
		addRow(db, "K8U431", 0, 0, "k8", "4");
		addRow(db, "K8U432", 0, 0, "k8", "4");
		addRow(db, "K8U433", 0, 0, "k8", "4");
		addRow(db, "K8U434", 0, 0, "k8", "4");
		addRow(db, "K8U455", 0, 0, "k8", "4");
		addRow(db, "K8U456", 0, 0, "k8", "4");
		addRow(db, "K8U457", 0, 0, "k8", "4");
		addRow(db, "K8U458", 0, 0, "k8", "4");
		addRow(db, "K8U459", 0, 0, "k8", "4");
		addRow(db, "K8U460", 0, 0, "k8", "4");
		addRow(db, "K8U461", 0, 0, "k8", "4");
		addRow(db, "K8U520", 0, 0, "k8", "5");
		addRow(db, "K8U521", 0, 0, "k8", "5");
		addRow(db, "K8U522", 0, 0, "k8", "5");
		addRow(db, "K8U523", 0, 0, "k8", "5");
		addRow(db, "K8U524", 0, 0, "k8", "5");
		addRow(db, "K8U527", 0, 0, "k8", "5");
		addRow(db, "K8U528", 0, 0, "k8", "5");
		addRow(db, "K8U529", 0, 0, "k8", "5");
		addRow(db, "K8U530", 0, 0, "k8", "5");
		addRow(db, "K8U531", 0, 0, "k8", "5");
		addRow(db, "K8U532", 0, 0, "k8", "5");
		addRow(db, "K8U533", 0, 0, "k8", "5");
		addRow(db, "G8104", 0, 0, "g8", "1");
		addRow(db, "G8107", 0, 0, "g8", "1");
		addRow(db, "G8124", 0, 0, "g8", "1");
		addRow(db, "G8125", 0, 0, "g8", "1");
		addRow(db, "G8174", 0, 0, "g8", "1");
		addRow(db, "G8305", 0, 0, "g8", "3");
		addRow(db, "G8307", 0, 0, "g8", "3");
		addRow(db, "G8308", 0, 0, "g8", "3");
		addRow(db, "G8323", 0, 0, "g8", "3");
		addRow(db, "G8324", 0, 0, "g8", "3");
		addRow(db, "G8325", 0, 0, "g8", "3");
		addRow(db, "G8355", 0, 0, "g8", "3");
		addRow(db, "G8360", 0, 0, "g8", "3");
		addRow(db, "G8405", 0, 0, "g8", "4");
		addRow(db, "G8407", 0, 0, "g8", "4");
		addRow(db, "G8408", 0, 0, "g8", "4");
		addRow(db, "G8423", 0, 0, "g8", "4");
		addRow(db, "G8424", 0, 0, "g8", "4");
		addRow(db, "G8425", 0, 0, "g8", "4");
		addRow(db, "G8471", 0, 0, "g8", "4");
		addRow(db, "G8505", 0, 0, "g8", "5");
		addRow(db, "G8507", 0, 0, "g8", "5");
		addRow(db, "G8508", 0, 0, "g8", "5");
		addRow(db, "G8523", 0, 0, "g8", "5");
		addRow(db, "G8524", 0, 0, "g8", "5");
		addRow(db, "G8525", 0, 0, "g8", "5");
		addRow(db, "G8549", 0, 0, "g8", "5");
		addRow(db, "AS9U103", 0, 0, "as", "1");
		addRow(db, "AS9U104", 0, 0, "as", "1");
		addRow(db, "AS9U106", 0, 0, "as", "1");
		addRow(db, "AS9U107", 0, 0, "as", "1");
		addRow(db, "AS9U108", 0, 0, "as", "1");
		addRow(db, "AS9U123", 0, 0, "as", "1");
		addRow(db, "AS9U202", 0, 0, "as", "2");
		addRow(db, "AS9U204", 0, 0, "as", "2");
		addRow(db, "AS9U206", 0, 0, "as", "2");
		addRow(db, "AS9U208", 0, 0, "as", "2");
		addRow(db, "AS9U210", 0, 0, "as", "2");
		addRow(db, "AS9U211", 0, 0, "as", "2");
		addRow(db, "AS9U212", 0, 0, "as", "2");
		addRow(db, "AS9U214", 0, 0, "as", "2");
		addRow(db, "AS9U216", 0, 0, "as", "2");
		addRow(db, "AS9U217", 0, 0, "as", "2");
		addRow(db, "AS9U306", 0, 0, "as", "3");
		addRow(db, "AS9U310", 0, 0, "as", "3");
		addRow(db, "AS9U316", 0, 0, "as", "3");
		addRow(db, "AS9U317", 0, 0, "as", "3");
		addRow(db, "AS9U402", 0, 0, "as", "4");
		addRow(db, "AS9U404", 0, 0, "as", "4");
		addRow(db, "AS9U406", 0, 0, "as", "4");
		addRow(db, "AS9U407", 0, 0, "as", "4");
		addRow(db, "AS9U408", 0, 0, "as", "4");
		addRow(db, "AS9U410", 0, 0, "as", "4");
		addRow(db, "AS9U416", 0, 0, "as", "4");
		addRow(db, "AS9U417", 0, 0, "as", "4");
		addRow(db, "KL1097", 0, 0, "kl", "1");
		addRow(db, "KL1151", 0, 0, "kl", "1");
		addRow(db, "KL1237", 0, 0, "kl", "1");
		addRow(db, "KL1382", 0, 0, "kl", "1");
		addRow(db, "KL2110", 0, 0, "kl", "2");
		addRow(db, "KL2370", 0, 0, "kl", "2");
		addRow(db, "KL2690", 0, 0, "kl", "2");
		addRow(db, "KL3047", 0, 0, "kl", "3");
		addRow(db, "KL3080", 0, 0, "kl", "3");
		addRow(db, "KL3181", 0, 0, "kl", "3");
		addRow(db, "KL3450", 0, 0, "kl", "3");
		addRow(db, "KL3451", 0, 0, "kl", "3");
		addRow(db, "KL3482", 0, 0, "kl", "3");
		addRow(db, "KL3520", 0, 0, "kl", "3");
		addRow(db, "KL3521", 0, 0, "kl", "3");
		addRow(db, "KL3540", 0, 0, "kl", "3");
		addRow(db, "KL3541", 0, 0, "kl", "3");
		addRow(db, "KL3550", 0, 0, "kl", "3");
		addRow(db, "KL3560", 0, 0, "kl", "3");
		addRow(db, "KL3561", 0, 0, "kl", "3");
		addRow(db, "KL3580", 0, 0, "kl", "3");
		addRow(db, "KL3583", 0, 0, "kl", "3");
		addRow(db, "KL3600", 0, 0, "kl", "3");
		addRow(db, "KL3601", 0, 0, "kl", "3");
		addRow(db, "KL3620", 0, 0, "kl", "3");
		addRow(db, "KL3621", 0, 0, "kl", "3");
		addRow(db, "KL3690", 0, 0, "kl", "3");
		addRow(db, "KL4091", 0, 0, "kl", "4");
		addRow(db, "KL4146", 0, 0, "kl", "4");
		addRow(db, "KL4201", 0, 0, "kl", "4");
		addRow(db, "KL4321", 0, 0, "kl", "4");
		addRow(db, "KL4391", 0, 0, "kl", "4");
		addRow(db, "KL4400", 0, 0, "kl", "4");
		addRow(db, "KL4410", 0, 0, "kl", "4");
		addRow(db, "KL4511", 0, 0, "kl", "4");
		addRow(db, "KL4561", 0, 0, "kl", "4");
		addRow(db, "KL4621", 0, 0, "kl", "4");
		addRow(db, "KL4690", 0, 0, "kl", "4");
		addRow(db, "KL5091", 0, 0, "kl", "5");
		addRow(db, "KL5231", 0, 0, "kl", "5");
		addRow(db, "KL5301", 0, 0, "kl", "5");
		addRow(db, "KL5511", 0, 0, "kl", "5");
		addRow(db, "KL5531", 0, 0, "kl", "5");
		addRow(db, "KL5540", 0, 0, "kl", "5");
		addRow(db, "KL5643", 0, 0, "kl", "5");
		addRow(db, "KL5690", 0, 0, "kl", "5");
		
		Log.i("julia", "Database should be ready to use!");
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
	
		dbCreate(db);
	} 
	//Adds a row to the database, for convinience.
	private void addRow(SQLiteDatabase db, String roomNr, int x, int y, String building_code, String floor_name) {

		ContentValues values = new ContentValues();
		values.put("roomNr", roomNr); 
		values.put("x", x);
		values.put("y", y);
		values.put("building_code", building_code);
		values.put("floor_name", floor_name);


		// Inserting Row
		try{
		db.insert("rooms", null, values);
		}
		catch(Exception e)
		{
			Log.e("julia", "Couldn't do it! " + e.getMessage());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS rooms");
		dbCreate(db);
	}
	//Finds a room, returns null if it wasn't found.
	public Room FindRoom(String roomNr) {
		if(roomNr == null)
			return null;
		String selectQuery = "SELECT  * FROM rooms WHERE roomNr = '" + roomNr.toUpperCase(Locale.getDefault()) + "'";
		SQLiteDatabase db = this.getReadableDatabase();
		Room room;
		try {
			Cursor c = db.rawQuery(selectQuery, null);

			if (c != null) {
				c.moveToFirst();
				
				room = new Room(roomNr);
				room.x = c.getInt(c.getColumnIndex("x"));
				room.y = c.getInt(c.getColumnIndex("y"));
				room.building_code = c.getString(c.getColumnIndex("building_code"));
				room.floor_name = c.getString(c.getColumnIndex("floor_name"));
				
				
				db.close();
				return room;
			}
		}
		catch (Exception e) {
			db.close();
			e.printStackTrace();
		}
		return null;
	}
	//Returns a list of rooms based on a search string.
	public List<Room> SearchForRooms(String searchString){
		List<Room> strs = new ArrayList<Room>();
		
		String selectQuery = "SELECT * FROM rooms where roomNr like '%"+searchString+"%'";
		SQLiteDatabase db = this.getReadableDatabase();
		try {
			Cursor c = db.rawQuery(selectQuery, null);

			if (c != null) {
				while(c.moveToNext())
				{
					
					Room room = new Room(c.getString(c.getColumnIndex("roomNr")));
					room.x = c.getInt(c.getColumnIndex("x"));
					room.y = c.getInt(c.getColumnIndex("y"));
					room.building_code = c.getString(c.getColumnIndex("building_code"));
					room.floor_name = c.getString(c.getColumnIndex("floor_name"));
					strs.add(room);				
				}
				db.close();
			}
		}
		catch (Exception e) {
			db.close();
			e.printStackTrace();
		}
		Log.i("julia", "We found " + strs.size() + " rooms!");
		return strs;
	}
	//Returns all room names in a list. Can be used in an AutoCompleteView.
	public List<String> GetAllRoomNumbers()
	{
		List<String> strs = new ArrayList<String>();
		
		String selectQuery = "SELECT roomNr FROM rooms";
		SQLiteDatabase db = this.getReadableDatabase();
		try {
			Cursor c = db.rawQuery(selectQuery, null);

			if (c != null) {
				while(c.moveToNext())
				{
					strs.add(c.getString(c.getColumnIndex("roomNr")));				
				}
				db.close();
			}
		}
		catch (Exception e) {
			db.close();
			e.printStackTrace();
		}
		Log.i("julia", "We found " + strs.size() + " rooms!");
		return strs;
	}
	//Do not use this function.
	@Deprecated
	public boolean isRoomExistsAll(String roomNr) {

		/*String selectQuery = "SELECT  * FROM " + TABLE_ROOMS + " WHERE "
				+ ROW_ROOMNR + " = '" + roomNr.toUpperCase(Locale.getDefault()) + "'";

		SQLiteDatabase db = this.getReadableDatabase();

		try {
			Cursor c = db.rawQuery(selectQuery, null);

			if (c != null) {
				c.moveToFirst();
				room = new PathToRoom(roomNr);
				room.mMapPic = c.getString(c.getColumnIndex(ROW_MAP));
				room.mCoord_x = c.getInt(c.getColumnIndex(ROW_X));
				room.mCoord_y = c.getInt(c.getColumnIndex(ROW_Y));
				db.close();
				return true;
			}
		}
		catch (Exception e) {
			db.close();
			e.printStackTrace();
		}*/
		return false;
	}
/*
	public PathToRoom getRoomDetails() {
		return room;
	}

	public List<String> getPathImg() {
		if (room != null)
			return room.getPath();
		else
			return null;
	}

	public List<String> getPathImgTexts() {
		if (room != null)
			return room.getTexts();
		else
			return null;
	}

	public List<String> getArrows() {
		if (room != null)
			return room.getArrows();
		else
			return null;
	}

	public String getMapName() {
		if (room != null)
			return room.mMapPic;
		else
			return null;
	}

	public String getRoomNr() {
		if (room != null)
			return room.mRoomNr;
		else
			return null;
	}

	public int getCoordX() {
		if (room != null)
			return room.mCoord_x;
		else
			return -1;
	}

	public int getCoordY() {
		if (room != null)
			return room.mCoord_y;
		else 
			return -1;
	}*/
}
