package com.lilait.chat;

import com.lilait.anime.bigthree.naruto.ichigo.luffy.onepiece.bleach.slot.machine.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends Activity  {
	private WebView  webView;
	 int pagenumber=1;
	  EditText ed;
	 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Context c=this;
		Dialoge.runDialog(5,this);
		 setContentView(R.layout.chat);
		  webView =(WebView) findViewById(R.id.webV1);
		// webView.getSettings().setAppCacheEnabled(true);
		// webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		   webView.getSettings().setJavaScriptEnabled(true);
		   //webView.getSettings().setLoadWithOverviewMode(true);
		   webView.getSettings().setPluginsEnabled(true);
		   webView.getSettings().setPluginState(PluginState.ON);
		
		   webView.setWebViewClient(new WebViewClient() {
			
		            private View mCustomView;
		          
		        @Override
		        public boolean shouldOverrideUrlLoading(WebView view, String url) {
		        	
		        
		        	if(!GetNetworkStatus.isNetworkAvailable(c))
		        	{
		        		Toast.makeText(c, "Need working internet to use this app", Toast.LENGTH_LONG);
		        	}
			      
			        if(url.contains("exit"))
			        {
			        	view.goBack();
			        	finish();
			       
			        }
			        else
			        {
		            view.loadUrl(url);
			        }
		            return false;
		        }
		        @Override
		        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
	                
		        	Toast.makeText(c, webView.getUrl() + description,Toast.LENGTH_SHORT).show();
		     
		        	webView.loadData(description, "text/html; charset=UTF-8", null);
	            
                  // Toast.makeText(c, "Sorry, You need net Connection to get full itemlist", Toast.LENGTH_LONG).show();
	            }
		        
		        @Override
		        public void onPageFinished(WebView view, String url) {
		        // TODO Auto-generated method stub
		        super.onPageFinished(view, url);
		        }
		        
		  
		    });
	
		    webView.setPadding(0, 0, 0, 0);
		    webView.setBackgroundColor(Color.WHITE);
	
webView.loadUrl("http://www.videofeeds.lilait.com/chat/animemusicvideo/");
		
	}
	

	
	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
	    @Override
	    public void onClick(DialogInterface dialog, int which) {
	        switch (which){
	        case DialogInterface.BUTTON_POSITIVE:
	            //Yes button clicked
	        //	webView.clearView();
	        	dialog.dismiss();
	        	finish();
	            break;

	        case DialogInterface.BUTTON_NEGATIVE:
	            //No button clicked
	        	dialog.dismiss();
	            break;
	        }
	    }
	};
	
	/*@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		webView.loadUrl(webView.getUrl());
		super.onConfigurationChanged(newConfig);
	}*/
	
	@Override
	public void onBackPressed()
	{
	    
	        if (webView.canGoBack())
	        {
	            webView.goBack();
	        }
	        else
	        {
	            // Close app (presumably)
	      //      super.onBackPressed();
	        }
	    
	}
	@Override

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
           
            return true;
        }
        if((keyCode == KeyEvent.KEYCODE_BACK) && (webView.canGoBack()!=true)) 
        {
        	finish();
        	 return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }


	

}
