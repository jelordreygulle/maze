package com.maze_test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * This activity displays an image on the screen. 
 * The image has three different regions that can be clicked / touched.
 * When a region is touched, the activity changes the view to show a different
 * image.
 *
 */

public class MainActivity extends Activity 
    implements View.OnTouchListener 
{

/**
 * Create the view for the activity.
 *
 */

@Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ImageView iv = (ImageView) findViewById (R.id.image);
    iv.setImageResource(R.drawable.room1);
    iv.setTag(R.drawable.room1);
    if (iv != null) {
       iv.setOnTouchListener (this);
    }
    
    //ImageView im = (ImageView) findViewById(R.id.image_areas);
    //im.setImageResource(R.drawable.image_map1);
    //im.setTag(R.drawable.image_map1);
    
    toast ("Touch the screen to discover where the regions are.");
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
    //int nextImage = -1;			// resource id of the next image to display

    // If we cannot find the imageView, return.
    ImageView imageView = (ImageView) v.findViewById(R.id.image);
    ImageView imageMap = (ImageView) findViewById(R.id.image_areas);
    if (imageView == null) return false;

    // When the action is Down, see if we should show the "pressed" image for the default image.
    // We do this when the default image is showing. That condition is detectable by looking at the
    // tag of the view. If it is null or contains the resource number of the default image, display the pressed image.
    //Integer tagNum = (Integer) imageView.getTag ();
    //int currentResource = (tagNum == null) ? R.drawable.room1 : tagNum.intValue ();

    // Now that we know the current resource being displayed we can handle the DOWN and UP events.

    switch (action) {
    case MotionEvent.ACTION_DOWN :
       //if (currentResource == R.drawable.room1) {
         // nextImage = R.drawable.room20;
         // handledHere = true;
       /*
       } else if (currentResource != R.drawable.p2_ship_default) {
         nextImage = R.drawable.p2_ship_default;
         handledHere = true;
       */
      // } else handledHere = true;
       //break;

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
       else if (ct.closeMatch (Color.YELLOW, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "YELLOW"); state = true; toast("YELLOW true");}
       else if (ct.closeMatch (Color.WHITE, touchColor, tolerance)) {getNextRoom(imageView, imageMap, "WHITE"); state = true; toast("WHITE true");}
  

    default:
       state = false;
    } // end switch
 
   return state;
}
   

/**
 */
// More methods

private void getNextRoom(ImageView view, ImageView map, String color) {
	
	//ImageView cr = (ImageView) view;
	//ImageView cm = (ImageView) map;
	Integer curRoom = (Integer) view.getTag();
	//Integer curMap = (Integer) cm.getTag();
	curRoom = (curRoom == null) ? 0 : curRoom;
	//curMap = (cm == null) ? 0 : curMap;
	switch(curRoom) {
		case R.drawable.room1:
		{	
			if(color == "RED") {
				view.setImageResource(R.drawable.room20);
				view.setTag(R.drawable.room20);
				map.setImageResource(R.drawable.image_map20);
				map.setTag(R.drawable.image_map20);
				
				
			}
			break;
		}
		case R.drawable.room20:
		{	
			if(color == "RED") {
				
			}
			else if(color == "YELLOW") {
				view.setImageResource(R.drawable.room1);
				view.setTag(R.drawable.room1);
				map.setImageResource(R.drawable.image_map1);
				map.setTag(R.drawable.image_map1);
				
			}
			else if(color == "BLUE") {
			}
			break;
		}
		default:
		{
				
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

} // end class
