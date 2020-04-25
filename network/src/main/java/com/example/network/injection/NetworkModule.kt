package com.example.network.injection

import com.example.network.SpeedrunService
import com.example.network.utils.DateConverter
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val builder = GsonBuilder()
        builder.registerTypeAdapter(Date::class.java, DateConverter())
        return GsonConverterFactory.create(builder.create())
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    @Named("SpeedrunServiceHttpClient")
    fun provideSpeedrunServiceHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addNetworkInterceptor(logging)
        return builder.build()
    }

    @Provides
    @Singleton
    @Named("SpeedrunServiceRetrofitBuilder")
    fun provideSpeedrunServiceRetrofitBuilder(
        @Named("SpeedrunServiceHttpClient") client: OkHttpClient,
        adapterFactory: RxJava2CallAdapterFactory,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(adapterFactory)
            .addConverterFactory(gsonConverterFactory)
    }

    @Provides
    @Singleton
    fun provideSpeedrunService(@Named("SpeedrunServiceRetrofitBuilder") retrofitBuilder: Retrofit.Builder): SpeedrunService {
        retrofitBuilder.baseUrl("http://192.168.2.16:5000")
        return retrofitBuilder.build().create(SpeedrunService::class.java)
    }
}