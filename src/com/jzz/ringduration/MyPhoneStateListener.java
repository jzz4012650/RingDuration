package com.jzz.ringduration;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * 手机状态监听器
 * @author jinzhenzu
 *
 */
public class MyPhoneStateListener extends PhoneStateListener {
    
    private long startRington;
    private Context context;

    public MyPhoneStateListener(Context context) {
    	this.context = context;
    }
    
    /**
     * 响应手机状态变化，统计来电时长
     * @param state 手机状态
     * @param incomingNumber 来电号码
     */
    public void onCallStateChanged(int state, String incomingNumber) {
        switch (state) {
        case TelephonyManager.CALL_STATE_IDLE: // 空闲
        	if (Data.incoming == true) {
        		Data.durationTime = System.currentTimeMillis() - startRington;
        		putNotification(Data.durationTime, Data.incomingNumber);
        	}
            System.out.println("--手机恢复空闲，响铃时长" + Data.durationTime + "ms--");
            break;
        case TelephonyManager.CALL_STATE_OFFHOOK: // 通话
        	Data.incoming = false;
            System.out.println("--接通电话--"); 
            break;
        case TelephonyManager.CALL_STATE_RINGING: // 响铃
        	Data.incoming = true;
        	Data.incomingNumber = incomingNumber;
            startRington = System.currentTimeMillis();
            System.out.println("--开始响铃，来电号码：" + incomingNumber + "--");
            break;
        }
    }
    
    /**
     * 弹出一条未接来电通知
     * @param duration 响铃时长
     * @param incomingNumber 来电号码
     */
    @SuppressWarnings("deprecation")
	public void putNotification(long duration, String incomingNumber) {
    	double num = duration / 1000.0F;
    	System.out.println(num);
		String str = "响铃时长" + String.format("%.1f", num) + "s";
		Notification notification = new Notification();
		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		notification.icon = android.R.drawable.sym_call_missed; // 采用系统自带的未接来电图标
		notification.flags = Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL; // 点击通知自动消失 
		notification.setLatestEventInfo(context, "未接来电：" + incomingNumber, str, null);
		notification.tickerText = str; // 通知出现时通知栏显示的内容
		notification.sound = null; // 不响铃
		manager.notify(765, notification);
	}

}