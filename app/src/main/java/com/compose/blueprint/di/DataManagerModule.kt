package com.compose.blueprint.di

import com.compose.blueprint.data.DataManager
import com.compose.blueprint.data.network.NetworkCall
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
/**
 * Jai Khambhayta
 */
@Module
@InstallIn(ViewModelComponent::class)
object DataManagerModule {

    @Provides
    @ViewModelScoped
    fun provideDataManager(networkCall: NetworkCall): DataManager {
        return DataManager(networkCall)
    }
}