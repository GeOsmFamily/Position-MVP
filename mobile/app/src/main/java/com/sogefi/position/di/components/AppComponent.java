package com.sogefi.position.di.components;

import com.sogefi.position.di.components.ActivityComponent.Builder;
import com.sogefi.position.di.modules.ApplicationModule;
import com.sogefi.position.di.modules.MapboxModule;
import dagger.Component;
import javax.inject.Singleton;
import org.jetbrains.annotations.NotNull;

@Singleton
@Component(
        modules = {ApplicationModule.class, MapboxModule.class}
)

public interface AppComponent {
    @NotNull
    Builder activityComponent();
}
