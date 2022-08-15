package com.zaymon.app;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;
import com.yandex.metrica.push.YandexMetricaPush;


public class AppMetrica extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Creating an extended library configuration.
        // 41e8396e-4d30-41fc-b1c1-11915e6256d0
        YandexMetricaConfig config = YandexMetricaConfig.newConfigBuilder("41e8396e-4d30-41fc-b1c1-11915e6256d0").build();
        // Initializing the AppMetrica SDK.
        YandexMetrica.activate(getApplicationContext(), config);
        Log.d("YandexMetrica", "YandexMetricaYandexMetricaYandexMetrica");
        // Automatic tracking of user activity.
        YandexMetrica.enableActivityAutoTracking(this);


        YandexMetricaPush.init(getApplicationContext());

    }
}