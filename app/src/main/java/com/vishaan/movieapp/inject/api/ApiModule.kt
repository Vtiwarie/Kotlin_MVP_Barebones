package com.vishaan.movieapp.inject.api

import com.vishaan.movieapp.BuildConfig
import com.vishaan.movieapp.api.AppService
import com.vishaan.movieapp.api.interceptor.BaseInterceptor
import com.vishaan.movieapp.inject.NetworkModule
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Singleton
    @Provides
    @Named("base_auth")
    fun provideAuthInterceptor(interceptor: BaseInterceptor): Interceptor {
        return interceptor
    }

    @Singleton
    @Provides
    @Named("base_client")
    fun provideOkHttpClient(@Named("base_auth") authInterceptor: Interceptor,
                            loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
            .connectTimeout(NetworkModule.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NetworkModule.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NetworkModule.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
//            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideAppService(@Named("base_client") okHttpClient: OkHttpClient,
                          gson: Gson): AppService {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(AppService::class.java)
    }
}