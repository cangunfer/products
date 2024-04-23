package com.gunfer.products.di

import android.content.Context
import androidx.room.Room
import com.gunfer.products.data.dao.ProductDAO
import com.gunfer.products.data.database.AppDatabase
import com.gunfer.products.repository.source.ProductLocalRepository
import com.gunfer.products.repository.source.ProductRepositoryInterface
import com.gunfer.products.service.ProductAPI
import com.gunfer.products.service.ProductAPIService
import com.gunfer.products.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, AppDatabase::class.java, Constants.DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideProductDao(database: AppDatabase) = database.productDao()

    @Singleton
    @Provides
    fun provideProductRepo(dao : ProductDAO) = ProductLocalRepository(dao) as ProductRepositoryInterface

    @Singleton
    @Provides
    fun provideProductApi() : ProductAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.PRODUCT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductAPI::class.java)
    }

}