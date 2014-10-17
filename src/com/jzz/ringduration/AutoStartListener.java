package com.jzz.ringduration;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 开机自启动监听器
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
