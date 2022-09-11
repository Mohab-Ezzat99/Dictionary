package mrandroid.dictionary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mrandroid.dictionary.data.local.Converters
import mrandroid.dictionary.data.local.DictionaryDB
import mrandroid.dictionary.data.remote.DictionaryApi
import mrandroid.dictionary.util.Constants
import mrandroid.dictionary.util.parser.GsonParser
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): DictionaryApi =
        Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(DictionaryApi::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryDB(app: Application): DictionaryDB {
        return Room.databaseBuilder(app, DictionaryDB::class.java, Constants.DATABASE_NAME)
            .addTypeConverter(Converters(GsonParser(Gson())))
            .fallbackToDestructiveMigration()
            .build()
    }

//    @Provides
//    @Singleton
//    fun provideAuthRepository(belloutApi: DictionaryApi): AuthRepository {
//        return AuthRepositoryImpl(belloutApi)
//    }

}