package kitonpompom.cubesgame.activities.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.activities.AdapterFragPWPEasy
import kitonpompom.cubesgame.activities.data.dataArrayBitmap
import kitonpompom.cubesgame.databinding.DrawerLayoutPwpEasyBinding

//Класс для запуска перевората кубиков после нажатия в драйвер лэйоуте на подсказку
object HelpScoreManager {

    fun startDirectionRotationCube (selectImage: Int, openImage: Int, mainArrayView: ArrayList<dataArrayBitmap>,
                                    binding: DrawerLayoutPwpEasyBinding, posRotation: Int) {
        var posRotationCube = posRotation
        //Log.d("MyLog",  "connect = $posRotation")
        //Log.d("MyLog",  "selectImage = $selectImage")
        //Log.d("MyLog",  "openImage = $openImage")
        //Log.d("MyLog",  "mainArrayView[conect].arrayNumber[0] = ${mainArrayView[1].arrayNumber[0]}")
        //Запускаем если позиция которую хотим вращать меньше чем кол-во кубиков которое нужно вращать
        if (posRotationCube < openImage) {
            //Зпускаем цикл для того что бы если первая картинка повёрнута нужной нам стороной, то её пропустило
            //и начало проверять следующую, если норм то начать вращать следующую
            for (i in 0..openImage) {
                //Проверяем, если картинка на позиции 0 не равна картинке которую мы выбрали для поворота,
                // то начать вращение
                if (mainArrayView[posRotationCube].arrayNumber[0] != selectImage) {
                    //С помощью цикла опрделяем на какой позиции находится выбраная нами картинка для поворота
                    //posDirection - позиция по которой определяем в какую сторону вращать кубик
                    //value - номер изображения
                    for ((posDirection, value) in mainArrayView[posRotationCube].arrayNumber.withIndex()) {
                        //Log.d("MyLog",  "i = $posDirection, v = $value")
                        //Если value = номеру изображение которое нужно открыть
                        if (value == selectImage) {
                            //получаем позицию posDirection на которой на данный момент стоит наша картинка
                            //Log.d("MyLog",  "posDirection if value = selectImage: $posDirection")
                            //прячем все картинки что бы они не отображались под вращающимся кубиком
                            binding.layFragPlayPwpEasy.idImViewScale2.visibility = View.GONE
                            binding.layFragPlayPwpEasy.idImViewMove2.visibility = View.GONE
                            binding.layFragPlayPwpEasy.idImViewMove.visibility = View.GONE
                            //Получаем весь View из Adapter вытаскивая его из RecycleView по позиции и записываем в fr
                            val fr =
                                binding.layFragPlayPwpEasy.idRcViewFragPWP.findViewHolderForAdapterPosition(
                                    posRotationCube
                                )
                            //Получаем изображение из fr и записываем в itemOneImage
                            val itemOneImage =
                                fr?.itemView?.findViewById<ImageView>(R.id.id_item_play_with_pictures_one)
                            //Получаем itemView из fr
                            val itemView = fr?.itemView
                            //Получаем adapter из binding, что бы запустить в нём метод adapter.helpScore
                            val adapter =
                                binding.layFragPlayPwpEasy.idRcViewFragPWP.adapter as AdapterFragPWPEasy
                            if (itemView != null && itemOneImage != null) {
                                //posRotation - кубик который собираемся вращать
                                //posDirection - позиция на которой стоит нужный нам кубик, по которой мы выбираем сколько раз
                                //вращать кубик на позиции posRotation
                                adapter.helpScore(posRotationCube, itemView, itemOneImage, posDirection, selectImage, openImage)
                            }
                            Log.d("MyLog", "break2 повернуло")
                            break
                        }
                    }
                    Log.d("MyLog", "break1")
                    break
                } else {
                    posRotationCube++
                    Log.d("MyLog", "Повторка пошла $posRotationCube")

                }
            }
        }

    }
}