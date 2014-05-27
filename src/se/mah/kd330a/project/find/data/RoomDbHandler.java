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

		addRow(db, "K2C107", 270, 1300, "k2", "c");
		addRow(db, "K2C108", 0, 0, "k2", "c");
		addRow(db, "K2C109", 400, 1385, "k2", "c");
		addRow(db, "K2C110", 400, 1240, "k2", "c");
		addRow(db, "K2C117", 270, 985, "k2", "c");
		addRow(db, "K2C119", 270, 770, "k2", "c");
		addRow(db, "K2C121", 270, 580, "k2", "c");
		addRow(db, "K2C122", 400, 555, "k2", "c");		
		addRow(db, "K2C123", 400, 690, "k2", "c");
		addRow(db, "K2C124A", 400, 955, "k2", "c");
		addRow(db, "K2C124B", 400, 795,"k2", "c");
		addRow(db, "K2C133", 270, 240, "k2", "c");
		addRow(db, "K2C135A", 0, 0, "k2", "c");
		addRow(db, "K2C135B", 430, 315, "k2", "c");
		addRow(db, "K2C136", 410, 240, "k2", "c");
		addRow(db, "K2C138", 370, 420, "k2", "c");
		addRow(db, "K2C214", 680, 480, "k2", "c");
		addRow(db, "K2C307", 1140, 250, "k2", "c");
		addRow(db, "K2C308", 1140, 180, "k2", "c");
		addRow(db, "K2C309", 1140, 100, "k2", "c");
		addRow(db, "K2C310", 990, 115, "k2", "c");
		addRow(db, "K2C311", 990, 240, "k2", "c");
		addRow(db, "K2C314", 990, 520, "k2", "c");
		addRow(db, "K2C316", 0, 0, "k2", "c");
		addRow(db, "K2C320", 0, 0, "k2", "c");
		addRow(db, "K2C323", 990, 1000, "k2", "c");
		addRow(db, "K2C325", 970, 900, "k2", "c");
		addRow(db, "K2C332", 990, 1350, "k2", "c");
		addRow(db, "K2C333", 990, 1180, "k2", "c");
		addRow(db, "K2C339", 0, 0, "k2", "c");
		addRow(db, "K2C340", 1140, 1380, "k2", "c");
		addRow(db, "K2C341", 1140, 1320, "k2", "c");
		addRow(db, "K2C342", 1140, 1280, "k2", "c");
		addRow(db, "K2C343", 1140, 1220, "k2", "c");
		addRow(db, "K2C345", 1140, 980, "k2", "c");
		addRow(db, "K2C346", 1140, 830, "k2", "c");
		addRow(db, "K2C347", 1140, 790, "k2", "c");
		addRow(db, "K2C348", 1140, 750, "k2", "c");
		addRow(db, "K2C349", 1140, 715, "k2", "c");
		addRow(db, "K2C350", 1140, 650, "k2", "c");
		addRow(db, "K2C351", 1140, 605, "k2", "c");
		addRow(db, "K2C352", 1140, 550, "k2", "c");
		addRow(db, "K2C353", 1140, 480, "k2", "c");

		addRow(db, "K2B105", 306, 1320, "k2", "b");
		addRow(db, "K2B106", 288, 1022, "k2", "b");
		addRow(db, "K2B107", 288, 968, "k2", "b");
		addRow(db, "K2B108", 288, 932, "k2", "b");
		addRow(db, "K2B109", 288, 830, "k2", "b");
		addRow(db, "K2B110", 288, 714, "k2", "b");
		addRow(db, "K2B111", 288, 593, "k2", "b");
		addRow(db, "K2B117", 0, 0, "k2", "b");
		addRow(db, "K2B118", 310, 214, "k2", "b");
		addRow(db, "K2B119", 422, 214, "k2", "b");
		addRow(db, "K2B202", 548, 174, "k2", "b");
		addRow(db, "K2B203", 672, 147, "k2", "b");
		addRow(db, "K2B204", 672, 147, "k2", "b");
		addRow(db, "K2B210", 682, 301, "k2", "b");
		addRow(db, "K2B211", 606, 301, "k2", "b");
		addRow(db, "K2B212", 545, 301, "k2", "b");
		addRow(db, "K2B205", 768, 177, "k2", "b");
		addRow(db, "K2B206", 890, 301, "k2", "b");
		addRow(db, "K2B207", 838, 301, "k2", "b");
		addRow(db, "K2B208", 0, 0, "k2", "b");
		addRow(db, "K2B209", 762, 301, "k2", "b");
		addRow(db, "K2B302", 978, 301, "k2", "b");
		addRow(db, "K2B303", 902, 175, "k2", "b");
		addRow(db, "K2B304", 1018, 175, "k2", "b");
		addRow(db, "K2B305", 1118, 175, "k2", "b");
		addRow(db, "K2B306", 1128, 281, "k2", "b");
		addRow(db, "K2B352", 1144, 518, "k2", "b");
		addRow(db, "K2B353", 1028, 595, "k2", "b");
		
		addRow(db, "K2A126", 0, 0, "k2", "a");
		addRow(db, "K2A129", 0, 0, "k2", "a");
		addRow(db, "K2A130", 0, 0, "k2", "a");
		addRow(db, "K2A131", 400, 1230, "k2", "a");
		addRow(db, "K2A132", 400, 1050, "k2", "a");
		addRow(db, "K2A104", 0, 0, "k2", "a");
		addRow(db, "K2A134", 300, 825, "k2", "a");
		addRow(db, "K2A136", 0, 0, "k2", "a");
		addRow(db, "K2A133", 400, 830, "k2", "a");
		addRow(db, "K2A135", 390, 620, "k2", "a");
		addRow(db, "K2A137", 0, 0, "k2", "a");
		addRow(db, "K2A138", 260, 700, "k2", "a");
		addRow(db, "K2A139", 300, 615, "k2", "a");
		addRow(db, "K2A140", 395, 425, "k2", "a");
		addRow(db, "K2A146", 0, 0, "k2", "a");
		addRow(db, "K2A150", 0, 0, "k2", "a");
		addRow(db, "K2A164", 0, 0, "k2", "a");
		addRow(db, "K2A142", 0, 0, "k2", "a");
		addRow(db, "K2A160", 280, 190, "k2", "a");
		addRow(db, "K2A161", 0, 0, "k2", "a");
		addRow(db, "K2A162", 400, 200, "k2", "a");
		addRow(db, "K2A163", 0, 0, "k2", "a");
		addRow(db, "K2A202", 530, 200, "k2", "a");
		addRow(db, "K2A203", 0, 0, "k2", "a");
		addRow(db, "K2A204", 665, 170, "k2", "a");
		addRow(db, "K2A209", 0, 0, "k2", "a");
		addRow(db, "K2A205", 765, 200, "k2", "a");
		addRow(db, "K2A206", 0, 0, "k2", "a");
		addRow(db, "K2A207", 0, 0, "k2", "a");
		addRow(db, "K2A302", 870, 200, "k2", "a");
		addRow(db, "K2A303", 975, 200, "k2", "a");
		addRow(db, "K2A304", 1090, 200, "k2", "a");
		addRow(db, "K2A305", 0, 0, "k2", "a");
		addRow(db, "K2A306", 1080, 390, "k2", "a");
		addRow(db, "K2A307", 0, 0, "k2", "a");
		addRow(db, "K2A308", 0, 0, "k2", "a");
		addRow(db, "K2A309", 0, 0, "k2", "a");
		
		addRow(db, "K2D155", 290, 215, "k2", "d");
		addRow(db, "K2D156", 400, 125, "k2", "d");
		addRow(db, "K2D157", 395, 290, "k2", "d");
		addRow(db, "K2D202", 670, 280, "k2", "d");
		addRow(db, "K2D204", 660, 425, "k2", "d");
		addRow(db, "K2D205", 660, 525, "k2", "d");
		addRow(db, "K2D213", 660, 785, "k2", "d");
		addRow(db, "K2D214", 660, 980, "k2", "d");
		
		addRow(db, "ORD131", 803, 1262, "or", "1");
		addRow(db, "ORD138", 1151, 1262, "or", "1");
		addRow(db, "ORC236", 945, 921, "or", "2");
		addRow(db, "ORD222", 535, 1261, "or", "2");
		addRow(db, "ORE222", 535, 1697, "or", "2");
		addRow(db, "ORE223", 535, 1595, "or", "2");
		addRow(db, "ORE235", 963, 1717, "or", "2");
		addRow(db, "ORE239", 1211, 1603, "or", "2");
		addRow(db, "ORE240", 1211, 1715, "or", "2");
		addRow(db, "ORF206", 529, 2098, "or", "2");
		addRow(db, "ORF208", 0, 0, "or", "2");
		addRow(db, "ORF209", 509, 1958, "or", "2");
		addRow(db, "ORF211", 729, 1998, "or", "2");
		addRow(db, "ORF215", 1029, 1998, "or", "2");
		addRow(db, "ORF219", 1223, 2098, "or", "2");
		addRow(db, "ORF220", 1223, 1988, "or", "2");
		addRow(db, "ORC323", 471, 847, "or", "3");
		addRow(db, "ORC333", 875, 849, "or", "3");
		addRow(db, "ORC334", 919, 849, "or", "3");
		addRow(db, "ORC335", 965, 849, "or", "3");
		addRow(db, "ORC336", 1009, 849, "or", "3");
		addRow(db, "ORC339", 765, 903, "or", "3");
		addRow(db, "ORC340", 855, 903, "or", "3");
		addRow(db, "ORC341", 939, 903, "or", "3");
		addRow(db, "ORC344", 1149, 841, "or", "3");
		addRow(db, "ORC345", 1149, 943, "or", "3");
		addRow(db, "ORC377", 1187, 1075, "or", "3");
		addRow(db, "ORD326", 703, 1287, "or", "3");
		addRow(db, "ORD328", 465, 1235, "or", "3");
		addRow(db, "ORD331", 911, 1281, "or", "3");
		addRow(db, "ORD337", 1147, 1235, "or", "3");
		addRow(db, "ORD377", 1181, 1468, "or", "3");
		addRow(db, "ORE323", 465, 1617, "or", "3");
		addRow(db, "ORE336", 833, 1721, "or", "3");
		addRow(db, "ORE337", 917, 1721, "or", "3");
		addRow(db, "ORE340", 1153, 1613, "or", "3");
		addRow(db, "ORE341", 1153, 1705, "or", "3");
		addRow(db, "ORF306", 473, 2097, "or", "3");
		addRow(db, "ORF307", 471, 1995, "or", "3");
		addRow(db, "ORF309", 669, 1995, "or", "3");
		addRow(db, "ORF312", 947, 1995, "or", "3");
		addRow(db, "ORF314", 1151, 1995, "or", "3");
		addRow(db, "ORF315", 1153, 2097, "or", "3");
		addRow(db, "ORC435", 1003, 800, "or", "4");
		addRow(db, "ORC436", 975, 748, "or", "4");
		addRow(db, "ORC440", 1207, 892, "or", "4");
		addRow(db, "ORD436", 963, 1230, "or", "4");
		addRow(db, "ORE436", 975, 1702, "or", "4");
		addRow(db, "ORE439", 1203, 1608, "or", "4");
		addRow(db, "ORE477", 1237, 1824, "or", "4");
		addRow(db, "ORC525", 859, 946, "or", "5");
		addRow(db, "ORC526", 713, 824, "or", "5");
		addRow(db, "ORC527", 851, 824, "or", "5");
		addRow(db, "ORC528", 989, 824, "or", "5");
		
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
		addRow(db, "G8104", 405, 220, "g8", "1");
		addRow(db, "G8107", 405, 485, "g8", "1");
		addRow(db, "G8124", 405, 870, "g8", "1");
		addRow(db, "G8125", 405, 1165, "g8", "1");
		addRow(db, "G8174", 980, 226, "g8", "1");
		addRow(db, "G8305", 434, 212, "g8", "3");
		addRow(db, "G8307", 391, 447, "g8", "3");
		addRow(db, "G8308", 391, 685, "g8", "3");
		addRow(db, "G8323", 370, 1006, "g8", "3");
		addRow(db, "G8324", 370, 1233, "g8", "3");
		addRow(db, "G8325", 421, 1483, "g8", "3");
		addRow(db, "G8355", 0, 0, "g8", "3");
		addRow(db, "G8360", 0, 0, "g8", "3");
		addRow(db, "G8405", 503, 361, "g8", "4");
		addRow(db, "G8407", 470, 613, "g8", "4");
		addRow(db, "G8408", 470, 850, "g8", "4");
		addRow(db, "G8423", 470, 1158, "g8", "4");
		addRow(db, "G8424", 470, 1391, "g8", "4");
		addRow(db, "G8425", 506, 1630, "g8", "4");
		addRow(db, "G8471", 0, 0, "g8", "4");
		addRow(db, "G8505", 435, 199, "g8", "5");
		addRow(db, "G8507", 395, 441, "g8", "5");
		addRow(db, "G8508", 395, 673, "g8", "5");
		addRow(db, "G8523", 395, 1037, "g8", "5");
		addRow(db, "G8524", 395, 1278, "g8", "5");
		addRow(db, "G8525", 0, 0, "g8", "5");
		addRow(db, "G8549", 0, 0, "g8", "5");
		addRow(db, "AS9U103", 363, 714, "as", "1");
		addRow(db, "AS9U104", 363, 864, "as", "1");
		addRow(db, "AS9U106", 331, 1168, "as", "1");
		addRow(db, "AS9U107", 187, 1168, "as", "1");
		addRow(db, "AS9U108", 187, 994, "as", "1");
		addRow(db, "AS9U123", 287, 125, "as", "1");
		addRow(db, "AS9U202", 447, 828, "as", "2");
		addRow(db, "AS9U204", 447, 1042, "as", "2");
		addRow(db, "AS9U206", 341, 1385, "as", "2");
		addRow(db, "AS9U208", 233, 1095, "as", "2");
		addRow(db, "AS9U210", 233, 908, "as", "2");
		addRow(db, "AS9U211", 233, 781, "as", "2");
		addRow(db, "AS9U212", 233, 557, "as", "2");
		addRow(db, "AS9U214", 233, 345, "as", "2");
		addRow(db, "AS9U216", 279, 143, "as", "2");
		addRow(db, "AS9U217", 443, 143, "as", "2");
		addRow(db, "AS9U306", 161, 1238, "as", "3");
		addRow(db, "AS9U310", 73, 819, "as", "3");
		addRow(db, "AS9U316", 109, 119, "as", "3");
		addRow(db, "AS9U317", 267, 119, "as", "3");
		addRow(db, "AS9U403", 303, 827, "as", "4");
		addRow(db, "AS9U404", 303, 1039, "as", "4");
		addRow(db, "AS9U406", 261, 1375, "as", "4");
		addRow(db, "AS9U407", 87, 1375, "as", "4");
		addRow(db, "AS9U408", 87, 1094, "as", "4");
		addRow(db, "AS9U410", 87, 827, "as", "4");
		addRow(db, "AS9U416", 1115, 135, "as", "4");
		addRow(db, "AS9U417", 295, 135, "as", "4");
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
