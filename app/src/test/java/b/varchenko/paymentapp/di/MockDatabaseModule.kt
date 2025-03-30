package b.varchenko.paymentapp.di

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import b.varchenko.data.database.RoomDb
import b.varchenko.data.di.DatabaseModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
class MockDatabaseModule {
    internal val database by lazy {
        val context = ApplicationProvider.getApplicationContext<Context>()
        Room
            .inMemoryDatabaseBuilder(
                context,
                RoomDb::class.java,
            )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideRoomDb(): RoomDb = database
}