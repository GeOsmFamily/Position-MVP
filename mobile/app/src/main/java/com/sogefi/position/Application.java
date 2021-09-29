package com.sogefi.position;

import com.mapbox.mapboxsdk.Mapbox;
import com.sogefi.position.di.components.AppComponent;
import com.sogefi.position.di.components.DaggerAppComponent;
import com.sogefi.position.di.modules.ApplicationModule;

public class Application extends android.app.Application {

    private final AppComponent appComponent = DaggerAppComponent.builder().applicationModule(new ApplicationModule(this)).build();

    public final AppComponent getAppComponent() {
        return this.appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Mapbox.getInstance(this,getString(R.string.mapbox_access_token));
    }
}
