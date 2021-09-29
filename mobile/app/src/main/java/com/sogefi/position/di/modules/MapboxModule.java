package com.sogefi.position.di.modules;

import android.app.Application;

import com.sogefi.position.R;

import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Module

public final class MapboxModule {
    @NotNull
    public static final String MAPBOX_TOKEN = "mapbox_token";

    @Provides
    @Singleton
    @Named(MAPBOX_TOKEN)
    @NotNull
    public final String getMapboxToken(@NotNull Application application) {
        return application.getString(R.string.mapbox_access_token);
    }

}
