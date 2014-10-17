package com.jzz.ringduration;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class RingtonListenerReciever extends BroadcastReceiver {
	
    private TelephonyManager telephony;

    public void onReceive(Context context, Intent intent) {
    	if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
    		return;
    	}
        MyPhoneStateListener phoneListener = new MyPhoneStateListener();
        telephony = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    public void onDestroy() {
        telephony.listen(null, PhoneStateListener.LISTEN_NONE);
    }

}

