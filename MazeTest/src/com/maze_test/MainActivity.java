package com.maze_test;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer.OnTimedTextListener;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
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
import android.media.MediaPlayer.OnCompletionListener;
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

public class MainActivity extends Activity implements View.OnTouchListener,OnTimedTextListener {
		
	// Variable declarations for MediaPlayer
	private static final String TAG = "TimedTextTest";
	private TextView txtDisplay;
	private static Handler handler = new Handler();
	MediaPlayer mediaPlayer;
	private Counter counter = new Counter();
	private RoomMap roomsMap;
	private Map<Integer, Pair<Integer, Integer>> roomNarratives;
	private SparseArray<Map<String, Pair<Integer, Integer>>> rooms;

	

	
		@Override 
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	
	    ImageView iv = (ImageView) findViewById (R.id.image);
	    iv.setImageResource(R.drawable.room0);
	    iv.setTag(R.drawable.room0);
	    if (iv != null) {
	       iv.setOnTouchListener (this);
	    }
	    
		// Get our initialized media file map
	    roomsMap = new RoomMap();
		roomNarratives = roomsMap.getNarativeMap();
		rooms = roomsMap.getRoomMap();
		
	    // Set captions for room 1 to play when view renders.
	    // Will have to wrap this block in a conditional once user
	    // options panel is created and functional.
		
		
		
	    txtDisplay = (TextView) findViewById(R.id.txtDisplay);
	    txtDisplay.setVisibility(txtDisplay.INVISIBLE);
	    mediaPlayer = MediaPlayer.create(this, roomNarratives.get(R.drawable.room0).first);
		mediaPlayer.start();
	    
		  
	    
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
		      
		       if (ct.closeMatch (Color.RED, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "RED"); state=true; counter.increment();}
		       else if (ct.closeMatch (Color.BLUE, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "BLUE"); state = true; counter.increment();}
		       else if (ct.closeMatch (Color.WHITE, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "WHITE"); state = true; counter.increment();}
		       else if (ct.closeMatch (Color.CYAN, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "CYAN"); state = true; counter.increment();}
		       else if (ct.closeMatch (Color.YELLOW, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "YELLOW"); state = true; counter.increment();}
		       else if (ct.closeMatch (Color.MAGENTA, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "MAGENTA"); state = true; counter.increment();}
		       else if (ct.closeMatch (Color.GREEN, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "GREEN"); state = true; counter.increment();}
		       
		       
		       Toast toast = Toast.makeText(this,"Total Moves:" + counter.total(), Toast.LENGTH_SHORT);
		       toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
		       toast.show();
		
		
		
		    default:
		       state = false;
	    } // end switch
	 
	   return state;
	}
	   
	

	
	private void getNextRoom(ImageView view, ImageView map, String color) {

		
		
	
		Integer curRoom = (Integer) view.getTag();
		curRoom = (curRoom == null) ? 0 : curRoom;

		
		
		//error check
		//check if our room exists, and that there's something mapped to that color
		if(rooms.get(curRoom) != null && rooms.get(curRoom).get(color) != null)
		{
			Integer destRoom = rooms.get(curRoom).get(color).first;
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
			txtDisplay.setVisibility(View.VISIBLE);
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
		        public void onCompletion(MediaPlayer mp) {
		        	// After media file ends, hide captions background box
		    		txtDisplay.setVisibility(txtDisplay.INVISIBLE);
		        }
		    });
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
	
	public void toast (String msg) {
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

