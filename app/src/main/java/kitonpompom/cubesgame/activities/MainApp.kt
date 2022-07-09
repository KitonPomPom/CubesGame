package kitonpompom.cubesgame.activities

import android.app.Application
import kitonpompom.cubesgame.db.MainDataBase

class MainApp: Application() {

    val database by lazy { MainDataBase.getDataBase(this) }
}