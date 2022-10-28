package kitonpompom.cubesgame.activities.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kitonpompom.cubesgame.db.MainDataBase
import kitonpompom.cubesgame.entities.OfflineListGroupImage
import kotlinx.coroutines.launch

class MainViewModel(database: MainDataBase): ViewModel() {

    private val dao = database.getDao()

    //fun insertOfflineListGroupImage(imageList: OfflineListGroupImage) = viewModelScope.launch {
      //  dao.insertImageList(imageList)
    //}
}