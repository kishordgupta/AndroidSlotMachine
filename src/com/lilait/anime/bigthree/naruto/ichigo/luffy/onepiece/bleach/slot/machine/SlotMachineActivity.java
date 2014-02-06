package com.lilait.anime.bigthree.naruto.ichigo.luffy.onepiece.bleach.slot.machine;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import sponsorpay.Sponsorpayappstemplate;
import sponsorpay.User;

import com.ironsource.mobilcore.MobileCore;
import com.ironsource.mobilcore.MobileCore.LOG_TYPE;
import com.lilait.anime.bigthree.naruto.ichigo.luffy.onepiece.bleach.slot.machine.R;
import com.lilait.chat.GetNetworkStatus;
import com.lilait.chat.MainActivity;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.OnWheelClickedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.AbstractWheelAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SlotMachineActivity extends Activity {
	public static long  Totalscore =500;
	public int currentscore =0;
	public Context c=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.slot_machine_layout);

       
    	 //////////////////score
    	 
    	 usercoinupdate();
    	    c=this;
    	    sponsorpayupdate();
    	   
    	    TextView	all=(TextView)findViewById(R.id.score);
    			all.setText("Current Score " + User.Coins_points);
    	 
    	 ///////////////
        initWheel(R.id.slot_1);
        initWheel(R.id.slot_2);
        initWheel(R.id.slot_3);
        final Context c =this;
        Button mix = (Button)findViewById(R.id.btn_mix);
        mix.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	 TextView text = (TextView) findViewById(R.id.pwd_status);
            	 text.setText("..");
                mixWheel(R.id.slot_1);
                mixWheel(R.id.slot_2);
                mixWheel(R.id.slot_3);
               
            }
        });
        Button share = (Button)findViewById(R.id.btn_share);
        share.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
               Share.share("I have" + Totalscore +"coins in Anime Big Three slot ........#despicableapps", c);
            }
        });
        Button shae = (Button)findViewById(R.id.btn_shout);
        shae.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(GetNetworkStatus.isNetworkAvailable(c))
            	startActivity(new Intent(c, MainActivity.class));
            	else
            		Toast.makeText(c, "You know something called internet? thats need to use it", Toast.LENGTH_LONG).show();
             //  Share.share("I have" + Totalscore +"coins in Anime Big Three slot ........#despicableapps", c);
            }
        });
       // updateStatus();
    }
    //////sponsorpay
    private void usercoinupdate() {
    	// TODO Auto-generated method stub
    	  SharedPreferences prefs = this.getSharedPreferences(
        	      "com.example.app", Context.MODE_PRIVATE);
       User.Coins_points= prefs.getLong("userpoint", 500);
       User.Coins_points=User.Coins_points+5;
    }
      private void usercoincomit() {
    		// TODO Auto-generated method stub
    		  SharedPreferences prefs = this.getSharedPreferences(
    	    	      "com.example.app", Context.MODE_PRIVATE);
    	Editor ed=prefs.edit();
    	ed.putLong("userpoint",User.Coins_points);
    	ed.commit();
    	   
    	}
    private void sponsorpayupdate() {
    	// TODO Auto-generated method stub
    	  final sponsorpay.Sponsorpayappstemplate sp = new Sponsorpayappstemplate(this);
    	  sp.onRequestNewCoinsClick();
    	  usercoincomit() ;
    	  
    }
    
    
    ////
    // Wheel scrolled flag
    private boolean wheelScrolled = false;
    
    // Wheel scrolled listener
    OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
        public void onScrollingStarted(WheelView wheel) {
            wheelScrolled = true;
        }
        public void onScrollingFinished(WheelView wheel) {
            wheelScrolled = false;
            updateStatus();
        }
    };
    
    // Wheel changed listener
    private OnWheelChangedListener changedListener = new OnWheelChangedListener() {
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            if (!wheelScrolled) {
             // updateStatus();
            }
        }
    };
    
    /**
     * Updates status
     */
    private void updateStatus() {
        TextView text = (TextView) findViewById(R.id.pwd_status);
        TextView text1 = (TextView) findViewById(R.id.score);
        test();
        String t ="";
        switch (currentscore) {
		case 1000:
			t="You Got the Big Three. 100 bonus Coins";
			break;
		case 100:
			t="You Got the Big ONE. 30 bonus Coins";
			break;
		case 50:
			t="You Got ONE in ALL. 15 bonus Coins";
			break;
		case 25:
			t="You Got ALL from Same World. 10 bonus Coins";
			break;
		case 10:
			t="You Got two common. 5 bonus Coins";
			break;
		case -5:
			t="You Got in a mess. minus 25 Coins";
			break;
		default:
			break;
		}
        Totalscore = Totalscore+(long)currentscore;
        currentscore=0;
        
        	
      
            text.setText(t);
            text1.setText("You have : " + User.Coins_points+" coins");
            if(Totalscore<10)
            	text.setText("You lost the slot, Try again or use our offerwall to buy some points");
      
    }

    /**
     * Initializes wheel
     * @param id the wheel widget Id
     */
    private void initWheel(int id) {
        WheelView wheel = getWheel(id);
        wheel.setViewAdapter(new SlotMachineAdapter(this));
        wheel.setCurrentItem((int)(Math.random() * 10));
        
        wheel.addChangingListener(changedListener);
        wheel.addScrollingListener(scrolledListener);
       
        wheel.setCyclic(true);
        wheel.setEnabled(false);
    }
    
    /**
     * Returns wheel by Id
     * @param id the wheel Id
     * @return the wheel with passed Id
     */
    private WheelView getWheel(int id) {
        return (WheelView) findViewById(id);
    }
    
    /**
     * Tests wheels
     * @return true 
     */
    private boolean test() {
        int value = getWheel(R.id.slot_1).getCurrentItem();
        int value1 = getWheel(R.id.slot_2).getCurrentItem();
        int value2 = getWheel(R.id.slot_3).getCurrentItem();
        ////
        if(value==0&&value1==2&&value2==3)
        	{currentscore=1000;
        	
        	return true;}
        	
         if(value==0&&value1==1&&value2==2)
        	{currentscore=1000;return true;}
         if(value==2&&value1==0&&value2==1)
        	{currentscore=1000;return true;}
         if(value==2&&value1==1&&value2==0)
        	{currentscore=1000;return true;}
         if(value==1&&value1==2&&value2==0)
        	{currentscore=1000;return true;}
         if(value==1&&value1==0&&value2==2)
        	{currentscore=1000;return true;}
         if(value==value1&& value1==value2&&value==0)
        	{currentscore=100;return true;}
         if(value==value1&& value1==value2&&value==1)
        	{currentscore=100;return true;}
         if(value==value1&& value1==value2&&value==2)
        	{currentscore=100;return true;}
         if(value==value1&& value1==value2)
        	{currentscore=50;return true;}
         if(value==0||(3<=value && value<=5))
        {
        	if(value1==0||(3<=value1 && value1<=5))
        	{
        		if(value2==0||(3<=value2 && value2<=5))
        			{currentscore=25;return true;}
        	}
        		
        }
        
         if(value==1||(6<=value && value<=10))
        {
        	if(value1==1||(6<=value1 && value1<=10))
        	{
        		if(value2==1||(6<=value2 && value2<=10))
        			{currentscore=25;return true;}
        	}
        		
        }
        
         if(value==2||(11<=value ))
        {
        	if(value1==2||(11<=value1 ))
        	{
        		if(value2==2||(11<=value2 ))
        			{currentscore=25;return true;}
        	}
        		
        }
        
        if(currentscore==0){
        	
        if(value==value1)
        {	   currentscore=10;return true;}
        else if(value==value2)
        { currentscore=10;return true;}
        else if(value2==value1)
        { currentscore=10;return true;}
        else
        	{currentscore=-5;return true;}}
        
       // Toast.makeText(this, ""+value, Toast.LENGTH_LONG).show();
        return testWheelValue(R.id.slot_2, value) && testWheelValue(R.id.slot_3, value);
    }
    
    /**
     * Tests wheel value
     * @param id the wheel Id
     * @param value the value to test
     * @return true if wheel value is equal to passed value
     */
    private boolean testWheelValue(int id, int value) {
        return getWheel(id).getCurrentItem() == value;
    }
    
    /**
     * Mixes wheel
     * @param id the wheel id
     */
    private void mixWheel(int id) {
        WheelView wheel = getWheel(id);
        wheel.scroll(-350 + (int)(Math.random() * 50), 2000);
    }
    
    /**
     * Slot machine adapter
     */
    private class SlotMachineAdapter extends AbstractWheelAdapter {
        // Image size
        final int IMAGE_WIDTH = 57;
        final int IMAGE_HEIGHT = 57;
        
        // Slot machine symbols
        private final int items[] = new int[] {
              
                R.drawable.bleach_m, //0
                R.drawable.naruto_m,//1
                R.drawable.onepice_m,//2
                R.drawable.bleac_c2,//3
                R.drawable.bleach_c,//4
                R.drawable.bleach_c1,//5
                R.drawable.naruto_c,//6
                R.drawable.naruto_c1,//7
                R.drawable.naruto_c2,//8
                R.drawable.naruto_c3,//9
                R.drawable.naruto_c4,//10
                R.drawable.onepice_c,//11
                R.drawable.onepiece_c2,//12
                R.drawable.onepice_c3//13
                
        };
        
        // Cached images
        private List<SoftReference<Bitmap>> images;
        
        // Layout inflater
        private Context context;
        
        /**
         * Constructor
         */
        public SlotMachineAdapter(Context context) {
            this.context = context;
            images = new ArrayList<SoftReference<Bitmap>>(items.length);
            for (int id : items) {
                images.add(new SoftReference<Bitmap>(loadImage(id)));
            }
        }
        
        /**
         * Loads image from resources
         */
        private Bitmap loadImage(int id) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);
            Bitmap scaled = Bitmap.createScaledBitmap(bitmap, IMAGE_WIDTH, IMAGE_HEIGHT, true);
            bitmap.recycle();
            return scaled;
        }

        @Override
        public int getItemsCount() {
            return items.length;
        }

        // Layout params for image view
        final LayoutParams params = new LayoutParams(IMAGE_WIDTH, IMAGE_HEIGHT);
        
        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            ImageView img;
            if (cachedView != null) {
                img = (ImageView) cachedView;
            } else {
                img = new ImageView(context);
            }
            img.setLayoutParams(params);
            SoftReference<Bitmap> bitmapRef = images.get(index);
            Bitmap bitmap = bitmapRef.get();
            if (bitmap == null) {
                bitmap = loadImage(items[index]);
                images.set(index, new SoftReference<Bitmap>(bitmap));
            }
            img.setImageBitmap(bitmap);
            
            return img;
        }
    }
}
