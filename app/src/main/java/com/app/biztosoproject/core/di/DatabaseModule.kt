package com.app.biztosoproject.core.di

//import android.content.Context
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object DatabaseModule {
//
//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
//        Room.databaseBuilder(
//            context,
//            AppDatabase::class.java,
//            "social_app_db"
//        ).build()
//
//    @Provides
//    fun providePostDao(db: AppDatabase): PostDao = db.postDao()
//
//    // Add more DAO providers
//}