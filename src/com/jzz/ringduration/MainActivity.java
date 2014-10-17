package com.jzz.ringduration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 软件主界面
 * @author jinzhenzu
 *
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        System.out.println("--------------------------------init--------------------------------");
        
        if (Data.serviceRunning == false) {
        	Intent i = new Intent(this, RingtonListenerService.class);
        	this.startService(i);
        }
    }
}
