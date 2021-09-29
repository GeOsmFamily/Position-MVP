package com.sogefi.position.di.modules;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Module

public final class ApplicationModule {
    private final Application application;

    @Provides
    @Singleton
    @NotNull
    public final Application getApplication() {
        return this.application;
    }

    public ApplicationModule(@NotNull Application application) {
        super();
        this.application = application;
    }
}
