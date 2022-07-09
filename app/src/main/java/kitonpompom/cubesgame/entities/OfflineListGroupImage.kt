package kitonpompom.cubesgame.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "offline_list_group_image")
data class OfflineListGroupImage(
    @PrimaryKey(autoGenerate = true)
    val id:Int?,
    @ColumnInfo(name = "name_group")
    val name_group:String?


): Serializable
