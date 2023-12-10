package com.orainjistudio.ugit.di

import android.content.Context
import com.orainjistudio.ugit.data.api.ApiConfig
import com.orainjistudio.ugit.data.repository.GithubUserRepositoryImpl
import com.orainjistudio.ugit.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): GithubUserRepositoryImpl {
        val apiService = ApiConfig.getApiService()
        val appExecutors = AppExecutors()
        return GithubUserRepositoryImpl.getInstance(apiService, appExecutors)
    }
}