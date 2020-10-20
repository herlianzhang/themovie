package com.latihangoding.themovie.di

import android.app.Application
import com.latihangoding.themovie.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, MainActivityModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appliation(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}
