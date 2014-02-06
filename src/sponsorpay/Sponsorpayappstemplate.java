package sponsorpay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

import com.sponsorpay.sdk.android.SponsorPay;
import com.sponsorpay.sdk.android.credentials.SPCredentials;
import com.sponsorpay.sdk.android.publisher.SponsorPayPublisher;
import com.sponsorpay.sdk.android.publisher.currency.CurrencyServerAbstractResponse;
import com.sponsorpay.sdk.android.publisher.currency.CurrencyServerDeltaOfCoinsResponse;
import com.sponsorpay.sdk.android.publisher.currency.SPCurrencyServerListener;
import com.sponsorpay.sdk.android.publisher.currency.VirtualCurrencyConnector;
import com.sponsorpay.sdk.android.utils.SponsorPayLogger;

public class Sponsorpayappstemplate  {
	private static final String TAG ="sponsorpay";// SponsorpayAndroidTestAppActivity.class.getSimpleName();


    public Context v =null;
	private String mCurrencyName="Cooper coin";
	private String overridingAppId ="19191"; //mAppIdField.getText().toString();
	private String userId = "3";//mUserIdField.getText().toString();
	private String securityToken = "20f0737e7ad4cf51e2e744dbeb478f00";//mSecurityTokenField.getText().toStrin

	
	public Sponsorpayappstemplate(Context c) {
	v=c;	
	User u = new User(c);
	userId=u.userid;
	}


	public void onLaunchOfferwallClick() {
		try {
			
			
			SponsorPay.start(overridingAppId, userId, securityToken, (Activity) v);
		} catch (RuntimeException e){
			showCancellableAlertBox("Exception from SDK", e.getMessage(),v);
			SponsorPayLogger.e(TAG,
					"SponsorPay SDK Exception: ", e);
		}
		
		try {
			v.startActivity(
					SponsorPayPublisher.getIntentForOfferWallActivity(
							v, true, mCurrencyName, null));
		} catch (RuntimeException ex) {
			showCancellableAlertBox("Exception from SDK", ex.getMessage(),v);
			SponsorPayLogger.e(TAG,
					"SponsorPay SDK Exception: ", ex);
		}
	}


	public void onRequestNewCoinsClick() {
		final Context c=v;
		try {
			
			
			SponsorPay.start(overridingAppId, userId, securityToken, (Activity) c);
		} catch (RuntimeException e){
			showCancellableAlertBox("Exception from SDK", e.getMessage(),c);
			SponsorPayLogger.e(TAG,
					"SponsorPay SDK Exception: ", e);
		}

		try {
			SPCredentials credentials = SponsorPay.getCurrentCredentials();
			
			final String usedTransactionId = VirtualCurrencyConnector.fetchLatestTransactionId(
					v, credentials.getCredentialsToken());
			
			SPCurrencyServerListener requestListener = new SPCurrencyServerListener() {
				
				@Override
				public void onSPCurrencyServerError(CurrencyServerAbstractResponse response) {
					/*showCancellableAlertBox("Response or Request Error", String.format("%s\n%s\n%s\n",
							response.getErrorType(), response.getErrorCode(), response
							.getErrorMessage()),c);*/
				}
				
				@Override
				public void onSPCurrencyDeltaReceived(CurrencyServerDeltaOfCoinsResponse response) {
			/*		showCancellableAlertBox("Response From Currency Server", String.format(
							"Delta of Coins: %s\n\n" + "Used Latest Transaction ID: %s\n\n"
									+ "Returned Latest Transaction ID: %s\n\n", response
									.getDeltaOfCoins(), usedTransactionId, response
									.getLatestTransactionId()),c);*/
					
					User.Coins_points =User.Coins_points+(long)response.getDeltaOfCoins();
					
				}
			};
			SponsorPayPublisher.requestNewCoins(v, requestListener, mCurrencyName);
		} catch (RuntimeException ex) {
			showCancellableAlertBox("Exception from SDK", ex.getMessage(),c);
			SponsorPayLogger.e(TAG, "SponsorPay SDK Exception: ",
					ex);
		}
	}

	
	public void showCancellableAlertBox(String title, String text,Context v) {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(v);
		dialogBuilder.setTitle(title).setMessage(text).setCancelable(true);
		dialogBuilder.show();
	}

///////////////manifest add below lines in manifest/////
/*	  <activity
      android:configChanges="orientation"
      android:name="com.sponsorpay.sdk.android.publisher.ofw.OfferWallActivity" />
	
  <activity 
      android:configChanges="screenSize|orientation"
      android:name="com.sponsorpay.sdk.android.publisher.mbe.SPBrandEngageActivity"
      android:hardwareAccelerated="true"/>
	//////
*/	
	///use///
/*	////////////////lunch offerwall///
	final sponsorpay.Sponsorpayappstemplate sp = new Sponsorpayappstemplate(this);
    sp.onLaunchOfferwallClick();
    
    //////get coins///////
    
    sp.onRequestNewCoinsClick();*/
}
