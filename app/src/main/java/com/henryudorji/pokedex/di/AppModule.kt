package com.henryudorji.pokedex.di

import android.content.Context
import androidx.room.Room
import com.hadiyarajesh.flower.calladpater.FlowCallAdapterFactory
import com.henryudorji.pokedex.data.local.datastore.PrefsDataStore
import com.henryudorji.pokedex.data.local.db.PokeDatabase
import com.henryudorji.pokedex.data.remote.PokeDexAPI
import com.henryudorji.pokedex.data.repository.PokeDexRepository
import com.henryudorji.pokedex.utils.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
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
            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePokeDexApi(retrofit: Retrofit): PokeDexAPI =
        retrofit.create(PokeDexAPI::class.java)

    @Provides
    @Singleton
    fun providesRepository(
        pokeDexAPI: PokeDexAPI,
        pokeDatabase: PokeDatabase
    ) = PokeDexRepository(pokeDexAPI, pokeDatabase)

    @Provides
    @Singleton
    fun provideNetworkManager(@ApplicationContext context: Context) = NetworkManager(context)

    @Provides
    @Singleton
    fun providePrefsDatastore(@ApplicationContext context: Context) = PrefsDataStore(context)

}