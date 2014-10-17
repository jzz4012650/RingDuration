package com.jzz.ringduration;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class RingtonListenerService extends Service {

	private MyPhoneStateListener listener;
	private static TelephonyManager t_manager;
	
	@Override
	public void onCreate() {
		System.out.println("service created");
		Data.serviceRunning = true;
		listener = new MyPhoneStateListener(this);
		t_manager = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE); // 获取手机服务
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {		
		System.out.println("service started");
		t_manager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE); // 挂载手机状态监听器
		return super.onStartCommand(intent, flags, startId);
	}	

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onDestroy() {
		System.out.println("service destroied");
		Data.serviceRunning = false;
		t_manager.listen(null, PhoneStateListener.LISTEN_NONE); // 取消挂载手机状态监听器
		super.onDestroy();
	}
	
}
