package com.example.network.injection

import com.example.network.SpeedrunService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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
        return GsonConverterFactory.create(builder.create())
    }

    @Provides
    @Singleton
    @Named("SpeedrunServiceHttpClient")
    fun provideSpeedrunServiceHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        return builder.build()
    }
    @Provides
    @Singleton
    @Named("SpeedrunServiceRetrofitBuilder")
    fun provideSpeedrunServiceRetrofitBuilder(@Named("SpeedrunServiceHttpClient") client: OkHttpClient,
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