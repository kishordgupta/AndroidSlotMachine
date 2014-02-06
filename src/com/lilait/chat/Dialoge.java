package com.lilait.chat;

import android.app.ProgressDialog;
import android.content.Context;

public class Dialoge {
	public static void runDialog(final int seconds,Context ctx)
	{
		final ProgressDialog   progressDialog = ProgressDialog.show(ctx, "Please wait let us setup everything, think it is spawntime..", "Here your message");
		 
		        new Thread(new Runnable(){
		            public void run(){
		                try {
		                            Thread.sleep(seconds * 1000);
		                    progressDialog.dismiss();
		                } catch (InterruptedException e) {
		                    e.printStackTrace();
		                }
		            }
		        }).start();
		}
}
