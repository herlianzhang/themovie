package com.latihangoding.themovie.di

import dagger.Module

@Module(includes = [CoreDataModule::class, ViewModelModule::class])
class AppModule