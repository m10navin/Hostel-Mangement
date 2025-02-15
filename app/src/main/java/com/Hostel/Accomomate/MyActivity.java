package com.Hostel.Accomomate;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.IBinder;
import android.widget.Toast;

public class MyActivity extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        determineChargerType();
        return super.onStartCommand(intent, flags, startId);
    }

    private void determineChargerType() {
        BatteryManager batteryManager = (BatteryManager) getSystemService(Context.BATTERY_SERVICE);
        if (batteryManager != null) {
            int chargingType = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
            switch (chargingType) {
                case BatteryManager.BATTERY_PLUGGED_AC:
                    // Charging via AC
                    Toast.makeText(this, "Charging via AC", Toast.LENGTH_SHORT).show();
                    break;
                case BatteryManager.BATTERY_PLUGGED_USB:
                    // Charging via USB
                    Toast.makeText(this, "Charging via USB", Toast.LENGTH_SHORT).show();
                    break;
                case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                    // Charging via wireless
                    Toast.makeText(this, "Charging via wireless", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    // Not charging or unknown charging type
                    Toast.makeText(this, "Not charging or unknown charging type", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
