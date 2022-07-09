package kitonpompom.cubesgame.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kitonpompom.cubesgame.entities.OfflineListGroupImage

@Database (entities = [OfflineListGroupImage::class],version = 1)
abstract class MainDataBase: RoomDatabase() {
   abstract fun getDao(): Dao



    companion object{
        @Volatile
        private var INSTANCE: MainDataBase? = null
        fun getDataBase(context:Context): MainDataBase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDataBase::class.java,
                    "cube_games_offline_data.db"
                )
                    //.addMigrations(Migration.MIGRATION_1_2)
                    .build()
                instance
            }
        }
    }

    /*@Query ("SELECT * FROM offline_list_group_image") //Для считывания
    fun getAllOfflineListGroupImage(): Flow<List<OfflineListGroupImage>> //Получаем всю таблицу,
    // Flow позволяет обновлять данные каждый раз когда обновляются данные в таблице

    @Insert //Для записи
    suspend fun insertOfflineListGroupImage(note:OfflineListGroupImage) //Записываем в онлайн таблицу
    //suspend позволяет записывать в подключая автоматически карутины*/
}