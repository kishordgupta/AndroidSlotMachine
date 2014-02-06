package com.lilait.anime.bigthree.naruto.ichigo.luffy.onepiece.bleach.slot.machine;

import android.content.Context;
import android.content.Intent;

public class Share {

	public static void share(String wish, Context context)
	{
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, wish);
		sendIntent.setType("text/plain");
	
		sendIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Despicableapps");
		sendIntent.putExtra(android.content.Intent.EXTRA_TEXT,wish);
		context.startActivity(sendIntent);
	}
}
