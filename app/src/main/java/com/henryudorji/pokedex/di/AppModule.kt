package com.henryudorji.pokedex.di

import android.app.Application
import androidx.room.Room
import com.henryudorji.pokedex.data.local.db.PokeDatabase
import com.henryudorji.pokedex.data.remote.PokeDexAPI
import com.henryudorji.pokedex.data.remote.PokeDexRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application
    ) = Room.databaseBuilder(
        app,
        PokeDatabase::class.java,
        "poke_dex"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun providePokeDao(db: PokeDatabase) = db.pokeDao()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(PokeDexAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun providePokeDexApi(retrofit: Retrofit): PokeDexAPI =
        retrofit.create(PokeDexAPI::class.java)

    @Singleton
    @Provides
    fun providesRepository(pokeDexAPI: PokeDexAPI) = PokeDexRepository(pokeDexAPI)
}