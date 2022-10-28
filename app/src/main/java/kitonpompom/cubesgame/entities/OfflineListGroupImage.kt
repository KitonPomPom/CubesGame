package kitonpompom.cubesgame.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "offline_list_group_image")

data class OfflineListGroupImage(
    @PrimaryKey(autoGenerate = true)
    val id:Int?,
    @ColumnInfo(name = "image")
    val name_group: ByteArray


): Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as OfflineListGroupImage

        if (!name_group.contentEquals(other.name_group)) return false

        return true
    }

    override fun hashCode(): Int {
        return name_group.contentHashCode()
    }
}
