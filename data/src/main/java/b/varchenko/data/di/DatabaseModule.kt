package b.varchenko.data.di

import android.content.Context
import androidx.room.Room
import b.varchenko.data.database.RoomDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): RoomDb {
        return Room
            .databaseBuilder(context, RoomDb::class.java, RoomDb.DB_NAME)
            .build()
    }
}