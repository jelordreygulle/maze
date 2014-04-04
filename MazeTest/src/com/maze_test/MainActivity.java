package com.maze_test;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer.OnTimedTextListener;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.maze_test.R;


import android.media.MediaPlayer;
import android.media.MediaPlayer.TrackInfo;
import android.media.TimedText;
import android.os.Handler;
import android.widget.TextView;

/**
 * This activity displays an image on the screen. 
 * The image has three different regions that can be clicked / touched.
 * When a region is touched, the activity changes the view to show a different
 * image.
 *
 */

public class MainActivity extends Activity 
implements View.OnTouchListener,OnTimedTextListener 
{
	
// Variable declarations for MediaPlayer
private static final String TAG = "TimedTextTest";
private TextView txtDisplay;
private static Handler handler = new Handler();
MediaPlayer mediaPlayer;


// Captions and audio file map initializing function
public Map<Integer, Pair<Integer,Integer>> mediaFileInit() {
	Map<Integer, Pair<Integer, Integer>> roomNarratives = new HashMap<Integer, Pair<Integer, Integer>>();
	
	// Add corresponding audio and caption files to the media file map
	roomNarratives.put(R.drawable.room1, new Pair<Integer, Integer>(R.raw.room_audio1, R.raw.sub1));
	roomNarratives.put(R.drawable.room2, new Pair<Integer, Integer>(R.raw.room_audio2, R.raw.sub2));
	roomNarratives.put(R.drawable.room3, new Pair<Integer, Integer>(R.raw.room_audio3, R.raw.sub3));
	roomNarratives.put(R.drawable.room4, new Pair<Integer, Integer>(R.raw.room_audio4, R.raw.sub4));
	roomNarratives.put(R.drawable.room5, new Pair<Integer, Integer>(R.raw.room_audio5, R.raw.sub5));
	roomNarratives.put(R.drawable.room6, new Pair<Integer, Integer>(R.raw.room_audio6, R.raw.sub6));
	roomNarratives.put(R.drawable.room7, new Pair<Integer, Integer>(R.raw.room_audio7, R.raw.sub7));
	roomNarratives.put(R.drawable.room8, new Pair<Integer, Integer>(R.raw.room_audio8, R.raw.sub8));
	roomNarratives.put(R.drawable.room9, new Pair<Integer, Integer>(R.raw.room_audio9, R.raw.sub9));
	roomNarratives.put(R.drawable.room10, new Pair<Integer, Integer>(R.raw.room_audio10, R.raw.sub10));
	roomNarratives.put(R.drawable.room11, new Pair<Integer, Integer>(R.raw.room_audio11, R.raw.sub11));
	roomNarratives.put(R.drawable.room12, new Pair<Integer, Integer>(R.raw.room_audio12, R.raw.sub12));
	roomNarratives.put(R.drawable.room13, new Pair<Integer, Integer>(R.raw.room_audio13, R.raw.sub13));
	roomNarratives.put(R.drawable.room14, new Pair<Integer, Integer>(R.raw.room_audio14, R.raw.sub14));
	roomNarratives.put(R.drawable.room15, new Pair<Integer, Integer>(R.raw.room_audio15, R.raw.sub15));
	roomNarratives.put(R.drawable.room16, new Pair<Integer, Integer>(R.raw.room_audio16, R.raw.sub16));
	roomNarratives.put(R.drawable.room17, new Pair<Integer, Integer>(R.raw.room_audio17, R.raw.sub17));
	roomNarratives.put(R.drawable.room18, new Pair<Integer, Integer>(R.raw.room_audio18, R.raw.sub18));
	roomNarratives.put(R.drawable.room19, new Pair<Integer, Integer>(R.raw.room_audio19, R.raw.sub19));
	roomNarratives.put(R.drawable.room20, new Pair<Integer, Integer>(R.raw.room_audio20, R.raw.sub20));
	roomNarratives.put(R.drawable.room21, new Pair<Integer, Integer>(R.raw.room_audio21, R.raw.sub21));
	roomNarratives.put(R.drawable.room22, new Pair<Integer, Integer>(R.raw.room_audio22, R.raw.sub22));
	roomNarratives.put(R.drawable.room23, new Pair<Integer, Integer>(R.raw.room_audio23, R.raw.sub23));
	roomNarratives.put(R.drawable.room24, new Pair<Integer, Integer>(R.raw.room_audio24, R.raw.sub24));
	roomNarratives.put(R.drawable.room25, new Pair<Integer, Integer>(R.raw.room_audio25, R.raw.sub25));
	roomNarratives.put(R.drawable.room26, new Pair<Integer, Integer>(R.raw.room_audio26, R.raw.sub26));
	roomNarratives.put(R.drawable.room27, new Pair<Integer, Integer>(R.raw.room_audio27, R.raw.sub27));
	roomNarratives.put(R.drawable.room28, new Pair<Integer, Integer>(R.raw.room_audio28, R.raw.sub28));
	roomNarratives.put(R.drawable.room29, new Pair<Integer, Integer>(R.raw.room_audio29, R.raw.sub29));
	roomNarratives.put(R.drawable.room30, new Pair<Integer, Integer>(R.raw.room_audio30, R.raw.sub30));
	roomNarratives.put(R.drawable.room31, new Pair<Integer, Integer>(R.raw.room_audio31, R.raw.sub31));
	roomNarratives.put(R.drawable.room32, new Pair<Integer, Integer>(R.raw.room_audio32, R.raw.sub32));
	roomNarratives.put(R.drawable.room33, new Pair<Integer, Integer>(R.raw.room_audio33, R.raw.sub33));
	roomNarratives.put(R.drawable.room34, new Pair<Integer, Integer>(R.raw.room_audio34, R.raw.sub34));
	roomNarratives.put(R.drawable.room35, new Pair<Integer, Integer>(R.raw.room_audio35, R.raw.sub35));
	roomNarratives.put(R.drawable.room36, new Pair<Integer, Integer>(R.raw.room_audio36, R.raw.sub36));
	roomNarratives.put(R.drawable.room37, new Pair<Integer, Integer>(R.raw.room_audio37, R.raw.sub37));
	roomNarratives.put(R.drawable.room38, new Pair<Integer, Integer>(R.raw.room_audio38, R.raw.sub38));
	roomNarratives.put(R.drawable.room39, new Pair<Integer, Integer>(R.raw.room_audio39, R.raw.sub39));
	roomNarratives.put(R.drawable.room40, new Pair<Integer, Integer>(R.raw.room_audio40, R.raw.sub40));
	roomNarratives.put(R.drawable.room41, new Pair<Integer, Integer>(R.raw.room_audio41, R.raw.sub41));
	roomNarratives.put(R.drawable.room42, new Pair<Integer, Integer>(R.raw.room_audio42, R.raw.sub42));
	roomNarratives.put(R.drawable.room43, new Pair<Integer, Integer>(R.raw.room_audio43, R.raw.sub43));
	roomNarratives.put(R.drawable.room44, new Pair<Integer, Integer>(R.raw.room_audio44, R.raw.sub44));
	roomNarratives.put(R.drawable.room45, new Pair<Integer, Integer>(R.raw.room_audio45, R.raw.sub45));
	
	return roomNarratives;
}

// Get our initialized media file map
Map<Integer, Pair<Integer, Integer>> roomNarratives = mediaFileInit();


@Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ImageView iv = (ImageView) findViewById (R.id.image);
    iv.setImageResource(R.drawable.room1);
    iv.setTag(R.drawable.room1);
    if (iv != null) {
       iv.setOnTouchListener (this);
    }
    
    // Set captions for room 1 to play when view renders.
    // Will have to wrap this block in a conditional once user
    // options panel is created and functional.
    txtDisplay = (TextView) findViewById(R.id.txtDisplay);
	mediaPlayer = MediaPlayer.create(this, roomNarratives.get(R.drawable.room1).first);
	mediaPlayer.start();
	try {
		mediaPlayer.addTimedTextSource(getSubtitleFile(roomNarratives.get(R.drawable.room1).second),
				MediaPlayer.MEDIA_MIMETYPE_TEXT_SUBRIP);
		int textTrackIndex = findTrackIndexFor(
				TrackInfo.MEDIA_TRACK_TYPE_TIMEDTEXT, mediaPlayer.getTrackInfo());
		if (textTrackIndex >= 0) {
			mediaPlayer.selectTrack(textTrackIndex);
		} else {
			Log.w(TAG, "Cannot find text track!");
		}
		mediaPlayer.setOnTimedTextListener(this);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
    
	  
    
}

/**
 * Respond to the user touching the screen.
 * Change images to make things appear and disappear from the screen.
 *
 */    

public boolean onTouch (View v, MotionEvent ev) 
{
    boolean state = false;

    final int action = ev.getAction();

    final int evX = (int) ev.getX();
    final int evY = (int) ev.getY();

    // If we cannot find the imageView, return.
    ImageView imageView = (ImageView) v.findViewById(R.id.image);
    ImageView imageMap = (ImageView) findViewById(R.id.image_areas);
    if (imageView == null) return false;
    
    

    // When the action is Down, see if we should show the "pressed" image for the default image.
    // We do this when the default image is showing. That condition is detectable by looking at the
    // tag of the view. If it is null or contains the resource number of the default image, display the pressed image.


    // Now that we know the current resource being displayed we can handle the DOWN and UP events.

    switch (action) {
    case MotionEvent.ACTION_DOWN :


    case MotionEvent.ACTION_UP :
       // On the UP, we do the click action.
       // The hidden image (image_areas) has three different hotspots on it.
       // The colors are red, blue, and yellow.
       // Use image_areas to determine which region the user touched.
       int touchColor = getHotspotColor (R.id.image_areas, evX, evY);
      
       // Compare the touchColor to the expected values. Switch to a different image, depending on what color was touched.
       // Note that we use a Color Tool object to test whether the observed color is close enough to the real color to
       // count as a match. We do this because colors on the screen do not match the map exactly because of scaling and
       // varying pixel density.
       ColorTool ct = new ColorTool ();
       int tolerance = 25;
      
       if (ct.closeMatch (Color.RED, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "RED"); state=true; toast("RED true");}
       else if (ct.closeMatch (Color.BLUE, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "BLUE"); state = true; toast("BLUE true");}
       else if (ct.closeMatch (Color.WHITE, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "WHITE"); state = true; toast("WHITE true");}
       else if (ct.closeMatch (Color.CYAN, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "CYAN"); state = true; toast("CYAN true");}
       else if (ct.closeMatch (Color.YELLOW, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "YELLOW"); state = true; toast("YELLOW true");}
       else if (ct.closeMatch (Color.MAGENTA, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "MAGENTA"); state = true; toast("MAGENTA true");}
       else if (ct.closeMatch (Color.GREEN, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "GREEN"); state = true; toast("GREEN true");}



    default:
       state = false;
    } // end switch
 
   return state;
}
   

/**
 */
// More methods

private void getNextRoom(ImageView view, ImageView map, String color) {
	//now we set our rooms
	// <R.drawable.room#, map<color, pair<associatedRoom#. associatedRoomMap#>>>
	SparseArray<Map<String, Pair<Integer, Integer>>> rooms = new SparseArray<Map<String, Pair<Integer, Integer>>>();
	
	

	//room 1
	Map<String, Pair<Integer, Integer>> room1 = new HashMap<String, Pair<Integer, Integer>>();
	room1.put("BLUE", new Pair<Integer, Integer>(R.drawable.room20, R.drawable.image_map20));
	room1.put("RED", new Pair<Integer, Integer>(R.drawable.room26, R.drawable.image_map26));
	room1.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room41, R.drawable.image_map41));
	room1.put("MAGENTA", new Pair<Integer, Integer>(R.drawable.room21, R.drawable.image_map21));
	
	//room 2
	Map<String, Pair<Integer, Integer>> room2 = new HashMap<String, Pair<Integer, Integer>>();
	room2.put("BLUE", new Pair<Integer, Integer>(R.drawable.room29, R.drawable.image_map29));
	room2.put("RED", new Pair<Integer, Integer>(R.drawable.room22, R.drawable.image_map22));
	room2.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room12, R.drawable.image_map12));

	//room 3
	Map<String, Pair<Integer, Integer>> room3 = new HashMap<String, Pair<Integer, Integer>>();
	room3.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room33, R.drawable.image_map33));
	room3.put("RED", new Pair<Integer, Integer>(R.drawable.room9, R.drawable.image_map9));
	room3.put("BLUE", new Pair<Integer, Integer>(R.drawable.room18, R.drawable.image_map18));
	
	//room 4
	Map<String, Pair<Integer, Integer>> room4 = new HashMap<String, Pair<Integer, Integer>>();
	room4.put("BLUE", new Pair<Integer, Integer>(R.drawable.room44, R.drawable.image_map44));
	room4.put("RED", new Pair<Integer, Integer>(R.drawable.room29, R.drawable.image_map29));
	room4.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room15, R.drawable.image_map15));
	room4.put("WHITE", new Pair<Integer, Integer>(R.drawable.room11, R.drawable.image_map11));
	room4.put("MAGENTA", new Pair<Integer, Integer>(R.drawable.room16, R.drawable.image_map16));
	room4.put("CYAN", new Pair<Integer, Integer>(R.drawable.room24, R.drawable.image_map24));
	room4.put("GREEN", new Pair<Integer, Integer>(R.drawable.room43, R.drawable.image_map43));


	//room 5
	Map<String, Pair<Integer, Integer>> room5 = new HashMap<String, Pair<Integer, Integer>>();
	room5.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room43, R.drawable.image_map43));
	room5.put("RED", new Pair<Integer, Integer>(R.drawable.room22, R.drawable.image_map22));
	room5.put("BLUE", new Pair<Integer, Integer>(R.drawable.room30, R.drawable.image_map30));
	room5.put("WHITE", new Pair<Integer, Integer>(R.drawable.room20, R.drawable.image_map20));

	//room 6
	// TODO -- image map contains room that doesn't exist
	Map<String, Pair<Integer, Integer>> room6 = new HashMap<String, Pair<Integer, Integer>>();
	room6.put("RED", new Pair<Integer, Integer>(R.drawable.room40, R.drawable.image_map40));

	//room 7
	Map<String, Pair<Integer, Integer>> room7 = new HashMap<String, Pair<Integer, Integer>>();
	room7.put("BLUE", new Pair<Integer, Integer>(R.drawable.room33, R.drawable.image_map33));
	room7.put("RED", new Pair<Integer, Integer>(R.drawable.room36, R.drawable.image_map36));
	room7.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room16, R.drawable.image_map16));
	
	//room 8
	Map<String, Pair<Integer, Integer>> room8 = new HashMap<String, Pair<Integer, Integer>>();
	room8.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room31, R.drawable.image_map31));
	room8.put("RED", new Pair<Integer, Integer>(R.drawable.room6, R.drawable.image_map6));
	room8.put("BLUE", new Pair<Integer, Integer>(R.drawable.room29, R.drawable.image_map29));
	room8.put("WHITE", new Pair<Integer, Integer>(R.drawable.room12, R.drawable.image_map12));

	//room 9
	Map<String, Pair<Integer, Integer>> room9 = new HashMap<String, Pair<Integer, Integer>>();
	room9.put("RED", new Pair<Integer, Integer>(R.drawable.room3, R.drawable.image_map3));
	room9.put("BLUE", new Pair<Integer, Integer>(R.drawable.room18, R.drawable.image_map18));
	
	//room 10
	Map<String, Pair<Integer, Integer>> room10 = new HashMap<String, Pair<Integer, Integer>>();
	room10.put("RED", new Pair<Integer, Integer>(R.drawable.room34, R.drawable.image_map34));
	room10.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room41, R.drawable.image_map41));
	room10.put("BLUE", new Pair<Integer, Integer>(R.drawable.room14, R.drawable.image_map14));
	
	//room 11
	Map<String, Pair<Integer, Integer>> room11 = new HashMap<String, Pair<Integer, Integer>>();
	room11.put("BLUE", new Pair<Integer, Integer>(R.drawable.room40, R.drawable.image_map40));
	room11.put("RED", new Pair<Integer, Integer>(R.drawable.room24, R.drawable.image_map24));

	//room 12
	Map<String, Pair<Integer, Integer>> room12 = new HashMap<String, Pair<Integer, Integer>>();
	room12.put("RED", new Pair<Integer, Integer>(R.drawable.room2, R.drawable.image_map2));
	room12.put("BLUE", new Pair<Integer, Integer>(R.drawable.room21, R.drawable.image_map21));
	room12.put("CYAN", new Pair<Integer, Integer>(R.drawable.room8, R.drawable.image_map8));
	room12.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room39, R.drawable.image_map39));
	
	//room 13
	Map<String, Pair<Integer, Integer>> room13 = new HashMap<String, Pair<Integer, Integer>>();
	room13.put("RED", new Pair<Integer, Integer>(R.drawable.room27, R.drawable.image_map27));
	room13.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room18, R.drawable.image_map18));
	room13.put("BLUE", new Pair<Integer, Integer>(R.drawable.room25, R.drawable.image_map25));
	
	//room 14
	Map<String, Pair<Integer, Integer>> room14 = new HashMap<String, Pair<Integer, Integer>>();
	room14.put("BLUE", new Pair<Integer, Integer>(R.drawable.room10, R.drawable.image_map10));
	room14.put("RED", new Pair<Integer, Integer>(R.drawable.room43, R.drawable.image_map43));
	room14.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room24, R.drawable.image_map24));
	
	//room 15
	Map<String, Pair<Integer, Integer>> room15 = new HashMap<String, Pair<Integer, Integer>>();
	room15.put("BLUE", new Pair<Integer, Integer>(R.drawable.room30, R.drawable.image_map30));
	room15.put("RED", new Pair<Integer, Integer>(R.drawable.room37, R.drawable.image_map37));
	room15.put("BLUE", new Pair<Integer, Integer>(R.drawable.room3, R.drawable.image_map3));
	
	//room 16
	Map<String, Pair<Integer, Integer>> room16 = new HashMap<String, Pair<Integer, Integer>>();
	room16.put("BLUE", new Pair<Integer, Integer>(R.drawable.room36, R.drawable.image_map36));
	room16.put("RED", new Pair<Integer, Integer>(R.drawable.room7, R.drawable.image_map7));
	
	//room 17
	Map<String, Pair<Integer, Integer>> room17 = new HashMap<String, Pair<Integer, Integer>>();
	room17.put("RED", new Pair<Integer, Integer>(R.drawable.room6, R.drawable.image_map6));
	room17.put("BLUE", new Pair<Integer, Integer>(R.drawable.room45, R.drawable.image_map45));	
	room17.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room33, R.drawable.image_map33));	

	//room 18
	Map<String, Pair<Integer, Integer>> room18 = new HashMap<String, Pair<Integer, Integer>>();
	room18.put("RED", new Pair<Integer, Integer>(R.drawable.room13, R.drawable.image_map13));
	room18.put("BLUE", new Pair<Integer, Integer>(R.drawable.room3, R.drawable.image_map3));

	//room 19
	Map<String, Pair<Integer, Integer>> room19 = new HashMap<String, Pair<Integer, Integer>>();	
	room19.put("BLUE", new Pair<Integer, Integer>(R.drawable.room31, R.drawable.image_map31));
	room19.put("RED", new Pair<Integer, Integer>(R.drawable.room11, R.drawable.image_map11));
	
	//room 20
	Map<String, Pair<Integer, Integer>> room20 = new HashMap<String, Pair<Integer, Integer>>();
	room20.put("RED", new Pair<Integer, Integer>(R.drawable.room5, R.drawable.image_map5));
	room20.put("BLUE", new Pair<Integer, Integer>(R.drawable.room27, R.drawable.image_map27));
	room20.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room1, R.drawable.image_map1));
	
	//room 21
	Map<String, Pair<Integer, Integer>> room21 = new HashMap<String, Pair<Integer, Integer>>();
	room21.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room44, R.drawable.image_map44));
	room21.put("RED", new Pair<Integer, Integer>(R.drawable.room24, R.drawable.image_map24));
	room21.put("BLUE", new Pair<Integer, Integer>(R.drawable.room31, R.drawable.image_map31));
	
	//room 22
	Map<String, Pair<Integer, Integer>> room22 = new HashMap<String, Pair<Integer, Integer>>();
	room22.put("BLUE", new Pair<Integer, Integer>(R.drawable.room43, R.drawable.image_map43));
	room22.put("RED", new Pair<Integer, Integer>(R.drawable.room38, R.drawable.image_map38));

	//room 23
	Map<String, Pair<Integer, Integer>> room23 = new HashMap<String, Pair<Integer, Integer>>();
	room23.put("RED", new Pair<Integer, Integer>(R.drawable.room28, R.drawable.image_map28));
	room23.put("BLUE", new Pair<Integer, Integer>(R.drawable.room8, R.drawable.image_map8));
	room23.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room45, R.drawable.image_map45));
	room23.put("CYAN", new Pair<Integer, Integer>(R.drawable.room19, R.drawable.image_map19));
	
	//room 24
	Map<String, Pair<Integer, Integer>> room24 = new HashMap<String, Pair<Integer, Integer>>();
	
	//room 25
	Map<String, Pair<Integer, Integer>> room25 = new HashMap<String, Pair<Integer, Integer>>();
	room25.put("RED", new Pair<Integer, Integer>(R.drawable.room34, R.drawable.image_map34));
	room25.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room13, R.drawable.image_map13));
	room25.put("BLUE", new Pair<Integer, Integer>(R.drawable.room35, R.drawable.image_map35));

	//room 26
	Map<String, Pair<Integer, Integer>> room26 = new HashMap<String, Pair<Integer, Integer>>();
	room26.put("BLUE", new Pair<Integer, Integer>(R.drawable.room30, R.drawable.image_map30));
	room26.put("RED", new Pair<Integer, Integer>(R.drawable.room36, R.drawable.image_map36));
	room26.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room38, R.drawable.image_map38));
	room26.put("WHITE", new Pair<Integer, Integer>(R.drawable.room1, R.drawable.image_map1));

	//room 27
	Map<String, Pair<Integer, Integer>> room27 = new HashMap<String, Pair<Integer, Integer>>();
	room27.put("RED", new Pair<Integer, Integer>(R.drawable.room13, R.drawable.image_map13));
	room27.put("BLUE", new Pair<Integer, Integer>(R.drawable.room9, R.drawable.image_map9));
	
	//room 28
	Map<String, Pair<Integer, Integer>> room28 = new HashMap<String, Pair<Integer, Integer>>();
	room28.put("BLUE", new Pair<Integer, Integer>(R.drawable.room23, R.drawable.image_map23));
	room28.put("RED", new Pair<Integer, Integer>(R.drawable.room43, R.drawable.image_map43));
	room28.put("CYAN", new Pair<Integer, Integer>(R.drawable.room45, R.drawable.image_map45));
	room28.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room32, R.drawable.image_map32));
	
	//room 29
	Map<String, Pair<Integer, Integer>> room29 = new HashMap<String, Pair<Integer, Integer>>();
	room29.put("CYAN", new Pair<Integer, Integer>(R.drawable.room8, R.drawable.image_map8));
	room29.put("RED", new Pair<Integer, Integer>(R.drawable.room40, R.drawable.image_map40));
	room29.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room35, R.drawable.image_map35));
	room29.put("BLUE", new Pair<Integer, Integer>(R.drawable.room2, R.drawable.image_map2));
	
	//room 30
	Map<String, Pair<Integer, Integer>> room30 = new HashMap<String, Pair<Integer, Integer>>();
	room30.put("RED", new Pair<Integer, Integer>(R.drawable.room42, R.drawable.image_map42));
	room30.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room34, R.drawable.image_map34));
	room30.put("CYAN", new Pair<Integer, Integer>(R.drawable.room5, R.drawable.image_map5));
	room30.put("BLUE", new Pair<Integer, Integer>(R.drawable.room15, R.drawable.image_map15));
	
	//room 31
	Map<String, Pair<Integer, Integer>> room31 = new HashMap<String, Pair<Integer, Integer>>();
	room31.put("BLUE", new Pair<Integer, Integer>(R.drawable.room44, R.drawable.image_map44));
	room31.put("RED", new Pair<Integer, Integer>(R.drawable.room19, R.drawable.image_map19));
	room31.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room21, R.drawable.image_map21));

	//room 32
	Map<String, Pair<Integer, Integer>> room32 = new HashMap<String, Pair<Integer, Integer>>();
	room32.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room11, R.drawable.image_map11));
	room32.put("BLUE", new Pair<Integer, Integer>(R.drawable.room6, R.drawable.image_map6));
	room32.put("CYAN", new Pair<Integer, Integer>(R.drawable.room28, R.drawable.image_map28));
	room32.put("RED", new Pair<Integer, Integer>(R.drawable.room16, R.drawable.image_map16));
	
	//room 33
	Map<String, Pair<Integer, Integer>> room33 = new HashMap<String, Pair<Integer, Integer>>();
	room33.put("RED", new Pair<Integer, Integer>(R.drawable.room3, R.drawable.image_map3));
	room33.put("BLUE", new Pair<Integer, Integer>(R.drawable.room35, R.drawable.image_map35));
	room33.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room7, R.drawable.image_map7));
	
	//room 34
	Map<String, Pair<Integer, Integer>> room34 = new HashMap<String, Pair<Integer, Integer>>();
	room34.put("BLUE", new Pair<Integer, Integer>(R.drawable.room10, R.drawable.image_map10));
	room34.put("RED", new Pair<Integer, Integer>(R.drawable.room25, R.drawable.image_map25));

	//room 35
	Map<String, Pair<Integer, Integer>> room35 = new HashMap<String, Pair<Integer, Integer>>();
	room35.put("BLUE", new Pair<Integer, Integer>(R.drawable.room33, R.drawable.image_map33));

	//room 36
	Map<String, Pair<Integer, Integer>> room36 = new HashMap<String, Pair<Integer, Integer>>();
	room36.put("BLUE", new Pair<Integer, Integer>(R.drawable.room7, R.drawable.image_map7));
	room36.put("RED", new Pair<Integer, Integer>(R.drawable.room16, R.drawable.image_map16));
	
	//room 37
	Map<String, Pair<Integer, Integer>> room37 = new HashMap<String, Pair<Integer, Integer>>();
	room37.put("RED", new Pair<Integer, Integer>(R.drawable.room15, R.drawable.image_map15));
	room37.put("BLUE", new Pair<Integer, Integer>(R.drawable.room10, R.drawable.image_map10));
	room37.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room42, R.drawable.image_map42));
	room37.put("CYAN", new Pair<Integer, Integer>(R.drawable.room20, R.drawable.image_map20));
	
	//room 38
	Map<String, Pair<Integer, Integer>> room38 = new HashMap<String, Pair<Integer, Integer>>();
	room38.put("RED", new Pair<Integer, Integer>(R.drawable.room40, R.drawable.image_map40));
	room38.put("BLUE", new Pair<Integer, Integer>(R.drawable.room22, R.drawable.image_map22));
	room38.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room43, R.drawable.image_map43));
	
	//room 39
	Map<String, Pair<Integer, Integer>> room39 = new HashMap<String, Pair<Integer, Integer>>();
	room39.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room11, R.drawable.image_map11));
	room39.put("RED", new Pair<Integer, Integer>(R.drawable.room4, R.drawable.image_map4));
	room39.put("BLUE", new Pair<Integer, Integer>(R.drawable.room12, R.drawable.image_map12));
	
	//room 40
	Map<String, Pair<Integer, Integer>> room40 = new HashMap<String, Pair<Integer, Integer>>();
	room40.put("BLUE", new Pair<Integer, Integer>(R.drawable.room11, R.drawable.image_map11));
	room40.put("RED", new Pair<Integer, Integer>(R.drawable.room6, R.drawable.image_map6));
	room40.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room38, R.drawable.image_map38));
	
	//room 41
	//TODO - ladder needs to go to room 35
	Map<String, Pair<Integer, Integer>> room41 = new HashMap<String, Pair<Integer, Integer>>();
	room41.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room1, R.drawable.image_map1));
	//room41.put("WHITE", new Pair<Integer, Integer>(R.drawable.room35, R.drawable.image_map35));
	room41.put("RED", new Pair<Integer, Integer>(R.drawable.room10, R.drawable.image_map10));
	room41.put("BLUE", new Pair<Integer, Integer>(R.drawable.room38, R.drawable.image_map38));

	//room 42
	Map<String, Pair<Integer, Integer>> room42 = new HashMap<String, Pair<Integer, Integer>>();
	room42.put("BLUE", new Pair<Integer, Integer>(R.drawable.room22, R.drawable.image_map22));
	room42.put("RED", new Pair<Integer, Integer>(R.drawable.room30, R.drawable.image_map30));
	room42.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room4, R.drawable.image_map4));
	room42.put("CYAN", new Pair<Integer, Integer>(R.drawable.room25, R.drawable.image_map25));
	room42.put("WHITE", new Pair<Integer, Integer>(R.drawable.room37, R.drawable.image_map37));
	
	//room 43
	Map<String, Pair<Integer, Integer>> room43 = new HashMap<String, Pair<Integer, Integer>>();
	room43.put("RED", new Pair<Integer, Integer>(R.drawable.room22, R.drawable.image_map22));
	room43.put("BLUE", new Pair<Integer, Integer>(R.drawable.room38, R.drawable.image_map38));
	
	//room 44
	Map<String, Pair<Integer, Integer>> room44 = new HashMap<String, Pair<Integer, Integer>>();
	room44.put("BLUE", new Pair<Integer, Integer>(R.drawable.room21, R.drawable.image_map21));
	room44.put("RED", new Pair<Integer, Integer>(R.drawable.room18, R.drawable.image_map18));
	
	//room 45
	Map<String, Pair<Integer, Integer>> room45 = new HashMap<String, Pair<Integer, Integer>>();
	room45.put("RED", new Pair<Integer, Integer>(R.drawable.room28, R.drawable.image_map28));
	room45.put("BLUE", new Pair<Integer, Integer>(R.drawable.room17, R.drawable.image_map17));
	room45.put("YELLOW", new Pair<Integer, Integer>(R.drawable.room36, R.drawable.image_map36));
	room45.put("CYAN", new Pair<Integer, Integer>(R.drawable.room19, R.drawable.image_map19));
	room45.put("WHITE", new Pair<Integer, Integer>(R.drawable.room23, R.drawable.image_map23));
	
	//now add our room relations to the main room map!
	rooms.put(R.drawable.room1, room1);
	rooms.put(R.drawable.room2, room2);
	rooms.put(R.drawable.room3, room3);
	rooms.put(R.drawable.room4, room4);
	rooms.put(R.drawable.room5, room5);
	rooms.put(R.drawable.room6, room6);
	rooms.put(R.drawable.room7, room7);
	rooms.put(R.drawable.room8, room8);
	rooms.put(R.drawable.room9, room9);
	rooms.put(R.drawable.room10, room10);
	rooms.put(R.drawable.room11, room11);
	rooms.put(R.drawable.room12, room12);
	rooms.put(R.drawable.room13, room13);
	rooms.put(R.drawable.room14, room14);
	rooms.put(R.drawable.room15, room15);
	rooms.put(R.drawable.room16, room16);
	rooms.put(R.drawable.room17, room17);
	rooms.put(R.drawable.room18, room18);
	rooms.put(R.drawable.room19, room19);
	rooms.put(R.drawable.room20, room20);
	rooms.put(R.drawable.room21, room21);
	rooms.put(R.drawable.room22, room22);
	rooms.put(R.drawable.room23, room23);
	rooms.put(R.drawable.room24, room24);
	rooms.put(R.drawable.room25, room25);
	rooms.put(R.drawable.room26, room26);
	rooms.put(R.drawable.room27, room27);
	rooms.put(R.drawable.room28, room28);
	rooms.put(R.drawable.room29, room29);
	rooms.put(R.drawable.room30, room30);
	rooms.put(R.drawable.room31, room31);
	rooms.put(R.drawable.room32, room32);
	rooms.put(R.drawable.room33, room33);
	rooms.put(R.drawable.room34, room34);
	rooms.put(R.drawable.room35, room35);
	rooms.put(R.drawable.room36, room36);
	rooms.put(R.drawable.room37, room37);
	rooms.put(R.drawable.room38, room38);
	rooms.put(R.drawable.room39, room39);
	rooms.put(R.drawable.room40, room40);
	rooms.put(R.drawable.room41, room41);
	rooms.put(R.drawable.room42, room42);
	rooms.put(R.drawable.room43, room43);
	rooms.put(R.drawable.room44, room44);
	rooms.put(R.drawable.room45, room45);
	

	Integer curRoom = (Integer) view.getTag();
	curRoom = (curRoom == null) ? 0 : curRoom;
	Integer destRoom = rooms.get(curRoom).get(color).first;
	
	
	//error check
	//check if our room exists, and that there's something mapped to that color
	if(rooms.get(curRoom) != null && rooms.get(curRoom).get(color) != null && destRoom != null)
	{
		//first is room image
		view.setImageResource(rooms.get(curRoom).get(color).first);
		view.setTag(rooms.get(curRoom).get(color).first);
		//second is image map
		map.setImageResource(rooms.get(curRoom).get(color).second);
		map.setTag(rooms.get(curRoom).get(color).second);
		
		// Set captions and audio
		// We will have to wrap this block in a conditional once
		// the user options panel is created and functional
		mediaPlayer.release();
		mediaPlayer = MediaPlayer.create(this, roomNarratives.get(destRoom).first);
		mediaPlayer.start();
		try {
			mediaPlayer.addTimedTextSource(getSubtitleFile(roomNarratives.get(destRoom).second),
					MediaPlayer.MEDIA_MIMETYPE_TEXT_SUBRIP);
			int textTrackIndex = findTrackIndexFor(
					TrackInfo.MEDIA_TRACK_TYPE_TIMEDTEXT, mediaPlayer.getTrackInfo());
			if (textTrackIndex >= 0) {
				mediaPlayer.selectTrack(textTrackIndex);
			} else {
				Log.w(TAG, "Cannot find text track! The poor bastard that coded this must be an idiot!");
			}
			mediaPlayer.setOnTimedTextListener(this);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

/**
 * Get the color from the hotspot image at point x-y.
 * 
 */

public int getHotspotColor (int hotspotId, int x, int y) {
    ImageView img = (ImageView) findViewById (hotspotId);
    if (img == null) {
       Log.d ("ImageAreasActivity", "Hot spot image not found");
       return 0;
    } else {
      img.setDrawingCacheEnabled(true); 
      Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache()); 
      if (hotspots == null) {
         Log.d ("ImageAreasActivity", "Hot spot bitmap was not created");
         return 0;
      } else {
        img.setDrawingCacheEnabled(false);
        return hotspots.getPixel(x, y);
      }
    }
}

/**
 * Show a string on the screen via Toast.
 * 
 * @param msg String
 * @return void
 */

public void toast (String msg)
{
    Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_LONG).show ();
} // end toast

public int findTrackIndexFor(int mediaTrackType, TrackInfo[] trackInfo) {
	int index = -1;
	for (int i = 0; i < trackInfo.length; i++) {
		if (trackInfo[i].getTrackType() == mediaTrackType) {
			return i;
		}
	}
	return index;
}

public String getSubtitleFile(int resId) {
	String fileName = getResources().getResourceEntryName(resId);
	File subtitleFile = getFileStreamPath(fileName);
	if (subtitleFile.exists()) {
		Log.d(TAG, "Subtitle already exists");
		return subtitleFile.getAbsolutePath();
	}
	Log.d(TAG, "Subtitle does not exists, copy it from res/raw");

	// Copy the file from the res/raw folder to app folder on the
	// device
	InputStream inputStream = null;
	OutputStream outputStream = null;
	try {
		inputStream = getResources().openRawResource(resId);
		outputStream = new FileOutputStream(subtitleFile, false);
		copyFile(inputStream, outputStream);
		return subtitleFile.getAbsolutePath();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeStreams(inputStream, outputStream);
	}
	return "";
}

public void copyFile(InputStream inputStream, OutputStream outputStream)
		throws IOException {
	final int BUFFER_SIZE = 1024;
	byte[] buffer = new byte[BUFFER_SIZE];
	int length = -1;
	while ((length = inputStream.read(buffer)) != -1) {
		outputStream.write(buffer, 0, length);
	}
}


public void closeStreams(Closeable... closeables) {
	if (closeables != null) {
		for (Closeable stream : closeables) {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

@Override
public void onTimedText(final MediaPlayer mp, final TimedText text) {
	if (text != null) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				txtDisplay.setText(text.getText());
			}
		});
	}
}

} // end class

