package com.example.network.injection

import com.example.network.apis.SpeedrunService
import com.example.network.apis.SplitsService
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
    fun provideSpeedrunServiceHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addNetworkInterceptor(logging)
        return builder.build()
    }

    @Provides
    @Singleton
    @Named("SpeedrunServiceRetrofitBuilder")
    fun provideSpeedrunServiceRetrofitBuilder(
        client: OkHttpClient,
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
        retrofitBuilder.baseUrl("https://www.speedrun.com/api/v1/")
        return retrofitBuilder.build().create(SpeedrunService::class.java)
    }

    @Provides
    @Singleton
    @Named("SplitsServiceRetrofitBuilder")
    fun provideSplitsServiceRetrofitBuilder(
        client: OkHttpClient,
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
    fun provideSplitsService(@Named ("SplitsServiceRetrofitBuilder") retrofitBuilder: Retrofit.Builder): SplitsService {
        retrofitBuilder.baseUrl("https://splits.io/api/v3/")
        return retrofitBuilder.build().create(SplitsService::class.java)
    }
}