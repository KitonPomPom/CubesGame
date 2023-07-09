package kitonpompom.cubesgame.activities.utils

import android.R.id
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.exifinterface.media.ExifInterface
import kitonpompom.cubesgame.databinding.DrawerLayoutPwpEasyBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//Класс для обрезки\преобразования фото по нужному размеру
object HelpScoreManager {



    fun directionRotationCube (arrayBitmap: ArrayList<Bitmap>, arrayNumber: ArrayList<Int>,
                               arrayPosition: ArrayList<Int>, binding: DrawerLayoutPwpEasyBinding,
                               durationAnimationCubeSpeed: Long,
                               context: Context) { //функ получает картинку и возвращает её ширину и высоту
        var tempBitmap = arrayBitmap[0]
        arrayBitmap[0] = arrayBitmap[2]
        arrayBitmap[2] = arrayBitmap[5]
        arrayBitmap[5] = arrayBitmap[1]
        arrayBitmap[1] = tempBitmap
        var tempNumber = arrayNumber[0]
        arrayNumber[0] = arrayNumber[2]
        arrayNumber[2] = arrayNumber[5]
        arrayNumber[5] = arrayNumber[1]
        arrayNumber[1] = tempNumber
        var tempPosition = arrayPosition[0]
        arrayPosition[0] = arrayPosition[2]
        arrayPosition[2] = arrayPosition[5]
        arrayPosition[5] = arrayPosition[1]
        arrayPosition[1] = tempPosition
        binding.layFragPlayPwpEasy.idImViewScale.scaleX = 1.0f
        binding.layFragPlayPwpEasy.idImViewScale.scaleY = 1.0f
        binding.layFragPlayPwpEasy.idImViewScale2.scaleX = 1.0f
        binding.layFragPlayPwpEasy.idImViewScale2.scaleY = 1.0f
        if(binding.layFragPlayPwpEasy.idImViewScale.visibility == View.VISIBLE) {
            binding.layFragPlayPwpEasy.idImViewScale2.setImageBitmap(arrayBitmap[0])
            binding.layFragPlayPwpEasy.idImViewScale2.visibility = View.VISIBLE
            binding.layFragPlayPwpEasy.idImViewScale.startAnimation(CubeAnimation.create(
                CubeAnimation.LEFT, false, durationAnimationCubeSpeed))
            binding.layFragPlayPwpEasy.idImViewScale2.startAnimation(CubeAnimation.create
                (CubeAnimation.LEFT, true, durationAnimationCubeSpeed))
            binding.layFragPlayPwpEasy.idImViewScale.visibility = View.GONE
        }else{
            binding.layFragPlayPwpEasy.idImViewScale.setImageBitmap(arrayBitmap[0])
            binding.layFragPlayPwpEasy.idImViewScale.visibility = View.VISIBLE
            binding.layFragPlayPwpEasy.idImViewScale2.startAnimation(CubeAnimation.create(
                CubeAnimation.LEFT, false, durationAnimationCubeSpeed))
            binding.layFragPlayPwpEasy.idImViewScale.startAnimation(CubeAnimation.create(
                CubeAnimation.LEFT, true, durationAnimationCubeSpeed))
            binding.layFragPlayPwpEasy.idImViewScale2.visibility = View.GONE
        }
    }
}