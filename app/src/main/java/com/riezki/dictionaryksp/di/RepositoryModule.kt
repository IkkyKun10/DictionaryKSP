package com.riezki.dictionaryksp.di

import com.riezki.dictionaryksp.data.repository.DictionaryRepositoryImpl
import com.riezki.dictionaryksp.domain.repository.DictionaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideDictionaryRepository(
        dictionaryRepositoryImpl: DictionaryRepositoryImpl
    ) : DictionaryRepository
}