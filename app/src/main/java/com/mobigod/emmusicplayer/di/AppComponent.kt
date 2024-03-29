package com.mobigod.emmusicplayer.di

import com.mobigod.emmusicplayer.EMMusicApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.BindsInstance
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class,
    ActivitiesInjectorModule::class, FragmentsInjectorModule::class,
    AppModule::class, ViewModelModule::class])
interface AppComponent: AndroidInjector<EMMusicApp>{

    @Component.Builder
    interface builder {

        @BindsInstance
        fun application(myApplication: EMMusicApp): builder

        fun build(): AppComponent
    }

}