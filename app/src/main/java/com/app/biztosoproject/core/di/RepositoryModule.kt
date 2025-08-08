package com.app.biztosoproject.di

import com.app.biztosoproject.data.api.LanguageApi
import com.app.biztosoproject.data.api.PostApi
import com.app.biztosoproject.data.api.UserApi
import com.app.biztosoproject.data.repositories.LanguageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideLanguageRepository(api: LanguageApi): LanguageRepository =
        LanguageRepository(api)

//    @Provides
//    fun providePostRepository(api: PostApi, dao: PostDao): PostRepository =
//        PostRepository(api, dao)
//
//    @Provides
//    fun provideUserRepository(api: UserApi, dao: UserDao): UserRepository =
//        UserRepository(api, dao)
}