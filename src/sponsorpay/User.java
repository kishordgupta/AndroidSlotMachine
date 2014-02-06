package sponsorpay;

import java.util.UUID;

import android.content.Context;
import android.telephony.TelephonyManager;

public class User {

	public String userid="";
	public static int level=0;
	public static long  Coins_points=0;
	public User(Context c)
	{ final TelephonyManager tm = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);

    final String tmDevice, tmSerial, androidId;
    tmDevice = "" + tm.getDeviceId();
    tmSerial = "" + tm.getSimSerialNumber();
    androidId = "" + android.provider.Settings.Secure.getString(c.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

    UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
    String deviceId = deviceUuid.toString();
    
	userid=deviceId;
	}
}
