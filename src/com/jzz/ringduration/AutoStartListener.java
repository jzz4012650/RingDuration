package com.jzz.ringduration;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * ����������������
 * @author jinzhenzu
 * 
 */
public class AutoStartListener extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent i = new Intent(context, RingtonListenerService.class);
		context.startService(i);
	}

}
