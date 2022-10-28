package kitonpompom.cubesgame.activities.data

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel() : ViewModel() {

    val listBitmapForAdapterFragPWP: MutableLiveData<List<Bitmap>> by lazy {
        MutableLiveData<List<Bitmap>>()
    }
}