package com.jzz.ringduration;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

/**
 * �ֻ�״̬������
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
     * ��Ӧ�ֻ�״̬�仯��ͳ������ʱ��
     * @param state �ֻ�״̬
     * @param incomingNumber �������
     */
    public void onCallStateChanged(int state, String incomingNumber) {
        switch (state) {
        case TelephonyManager.CALL_STATE_IDLE: // ����
        	if (Data.incoming == true) {
        		Data.durationTime = System.currentTimeMillis() - startRington;
        		putNotification(Data.durationTime, Data.incomingNumber);
        	}
            System.out.println("--�ֻ��ָ����У�����ʱ��" + Data.durationTime + "ms--");
            break;
        case TelephonyManager.CALL_STATE_OFFHOOK: // ͨ��
        	Data.incoming = false;
            System.out.println("--��ͨ�绰--"); 
            break;
        case TelephonyManager.CALL_STATE_RINGING: // ����
        	Data.incoming = true;
        	Data.incomingNumber = incomingNumber;
            startRington = System.currentTimeMillis();
            System.out.println("--��ʼ���壬������룺" + incomingNumber + "--");
            break;
        }
    }
    
    /**
     * ����һ��δ������֪ͨ
     * @param duration ����ʱ��
     * @param incomingNumber �������
     */
    @SuppressWarnings("deprecation")
	public void putNotification(long duration, String incomingNumber) {
    	double num = duration / 1000.0F;
    	System.out.println(num);
		String str = "����ʱ��" + String.format("%.1f", num) + "s";
		Notification notification = new Notification();
		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		notification.icon = android.R.drawable.sym_call_missed; // ����ϵͳ�Դ���δ������ͼ��
		notification.flags = Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL; // ���֪ͨ�Զ���ʧ 
		notification.setLatestEventInfo(context, "δ�����磺" + incomingNumber, str, null);
		notification.tickerText = str; // ֪ͨ����ʱ֪ͨ����ʾ������
		notification.sound = null; // ������
		manager.notify(765, notification);
	}

}