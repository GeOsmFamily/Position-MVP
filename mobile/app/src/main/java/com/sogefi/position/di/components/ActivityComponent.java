package com.sogefi.position.di.components;

import com.sogefi.position.di.modules.MapboxActivityModule;
import com.sogefi.position.di.scopes.ActivityScope;
import com.sogefi.position.ui.viewsModels.MainAndroidViewModel;
import dagger.Subcomponent;
import org.jetbrains.annotations.NotNull;

@ActivityScope
@Subcomponent(
        modules = {MapboxActivityModule.class}
)

public interface ActivityComponent {
    void inject(@NotNull MainAndroidViewModel var1);

    @dagger.Subcomponent.Builder
    interface Builder {
        @NotNull
        ActivityComponent.Builder mapBoxModule(@NotNull MapboxActivityModule var1);

        @NotNull
        ActivityComponent build();
    }
}
