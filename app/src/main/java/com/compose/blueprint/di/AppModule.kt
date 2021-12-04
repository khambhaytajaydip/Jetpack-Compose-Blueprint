package com.compose.blueprint.di

import android.content.Context
import com.compose.blueprint.BluePrintApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
/**
 * Jai Khambhayta
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext context : Context): BluePrintApp {
        return context as BluePrintApp
    }
}