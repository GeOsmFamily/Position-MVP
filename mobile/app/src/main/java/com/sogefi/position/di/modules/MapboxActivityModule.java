package com.sogefi.position.di.modules;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.sogefi.position.di.scopes.ActivityScope;
import dagger.Module;
import dagger.Provides;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Module

public final class MapboxActivityModule {
    private final PermissionsListener permissionsListener;

    @Provides
    @ActivityScope
    @NotNull
    public final PermissionsManager permissionManager() {
        return new PermissionsManager(this.permissionsListener);
    }

    public MapboxActivityModule(@NotNull PermissionsListener permissionsListener) {
        super();
        this.permissionsListener = permissionsListener;
    }
}
