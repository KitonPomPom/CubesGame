package kitonpompom.cubesgame.activities.utils

import kitonpompom.cubesgame.activities.data.DataModel

//Используется при добавлении фото с камеры
object Interface {
    interface AddValueListBitmap{
        fun addValueViewModelListBitmap(dataModel: DataModel)
    }
}