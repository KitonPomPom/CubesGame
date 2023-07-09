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
object DirectionRotationCubeManager {

    fun up (arrayBitmap: ArrayList<Bitmap>, arrayNumber: ArrayList<Int>,
            arrayPosition: ArrayList<Int>, idImViewScale: ImageView,
            idImViewScale2: ImageView, durationAnimationCubeSpeed: Long) { //функ вращает кубик вверх
        var tempBitmap = arrayBitmap[0]
        arrayBitmap[0] = arrayBitmap[4]
        arrayBitmap[4] = arrayBitmap[5]
        arrayBitmap[5] = arrayBitmap[3]
        arrayBitmap[3] = tempBitmap
        var tempNumber = arrayNumber[0]
        arrayNumber[0] = arrayNumber[4]
        arrayNumber[4] = arrayNumber[5]
        arrayNumber[5] = arrayNumber[3]
        arrayNumber[3] = tempNumber
        var tempPosition = arrayPosition[0]
        arrayPosition[0] = arrayPosition[4]
        arrayPosition[4] = arrayPosition[5]
        arrayPosition[5] = arrayPosition[3]
        arrayPosition[3] = tempPosition
        idImViewScale.scaleX = 1.0f
        idImViewScale.scaleY = 1.0f
        idImViewScale2.scaleX = 1.0f
        idImViewScale2.scaleY = 1.0f
        if(idImViewScale.visibility == View.VISIBLE) {
            idImViewScale2.setImageBitmap(arrayBitmap[0])
            idImViewScale2.visibility = View.VISIBLE
            idImViewScale.startAnimation(CubeAnimation.create(
                CubeAnimation.UP, false, durationAnimationCubeSpeed))
            idImViewScale2.startAnimation(CubeAnimation.create(
                CubeAnimation.UP, true, durationAnimationCubeSpeed))
            idImViewScale.visibility = View.GONE
        }else{
            idImViewScale.setImageBitmap(arrayBitmap[0])
            idImViewScale.visibility = View.VISIBLE
            idImViewScale2.startAnimation(CubeAnimation.create(
                CubeAnimation.UP, false, durationAnimationCubeSpeed))
            idImViewScale.startAnimation(CubeAnimation.create(
                CubeAnimation.UP, true, durationAnimationCubeSpeed))
            idImViewScale2.visibility = View.GONE
        }
    }

    fun down (arrayBitmap: ArrayList<Bitmap>, arrayNumber: ArrayList<Int>,
            arrayPosition: ArrayList<Int>, idImViewScale: ImageView,
              idImViewScale2: ImageView,
            durationAnimationCubeSpeed: Long) { //функ вращает кубик вниз
        var tempBitmap = arrayBitmap[0]
        arrayBitmap[0] = arrayBitmap[3]
        arrayBitmap[3] = arrayBitmap[5]
        arrayBitmap[5] = arrayBitmap[4]
        arrayBitmap[4] = tempBitmap
        var tempNumber = arrayNumber[0]
        arrayNumber[0] = arrayNumber[3]
        arrayNumber[3] = arrayNumber[5]
        arrayNumber[5] = arrayNumber[4]
        arrayNumber[4] = tempNumber
        var tempPosition = arrayPosition[0]
        arrayPosition[0] = arrayPosition[3]
        arrayPosition[3] = arrayPosition[5]
        arrayPosition[5] = arrayPosition[4]
        arrayPosition[4] = tempPosition
        idImViewScale.scaleX = 1.0f
        idImViewScale.scaleY = 1.0f
        idImViewScale2.scaleX = 1.0f
        idImViewScale2.scaleY = 1.0f
        if(idImViewScale.visibility == View.VISIBLE) {
            idImViewScale2.setImageBitmap(arrayBitmap[0])
            idImViewScale2.visibility = View.VISIBLE
            idImViewScale.startAnimation(CubeAnimation.create(
                CubeAnimation.DOWN, false, durationAnimationCubeSpeed))
            idImViewScale2.startAnimation(CubeAnimation.create(
                CubeAnimation.DOWN, true, durationAnimationCubeSpeed))
            idImViewScale.visibility = View.GONE
        }else{
            idImViewScale.setImageBitmap(arrayBitmap[0])
            idImViewScale.visibility = View.VISIBLE
            idImViewScale2.startAnimation(CubeAnimation.create(
                CubeAnimation.DOWN, false, durationAnimationCubeSpeed ))
            idImViewScale.startAnimation(CubeAnimation.create(
                CubeAnimation.DOWN, true, durationAnimationCubeSpeed))
            idImViewScale2.visibility = View.GONE
        }
    }

    fun right (arrayBitmap: ArrayList<Bitmap>, arrayNumber: ArrayList<Int>,
              arrayPosition: ArrayList<Int>, idImViewScale: ImageView, idImViewScale2: ImageView,
              durationAnimationCubeSpeed: Long) { //функ вращает кубик вправо
        var tempBitmap = arrayBitmap[0]
        arrayBitmap[0] = arrayBitmap[1]
        arrayBitmap[1] = arrayBitmap[5]
        arrayBitmap[5] = arrayBitmap[2]
        arrayBitmap[2] = tempBitmap
        var tempNumber = arrayNumber[0]
        arrayNumber[0] = arrayNumber[1]
        arrayNumber[1] = arrayNumber[5]
        arrayNumber[5] = arrayNumber[2]
        arrayNumber[2] = tempNumber
        var tempPosition = arrayPosition[0]
        arrayPosition[0] = arrayPosition[1]
        arrayPosition[1] = arrayPosition[5]
        arrayPosition[5] = arrayPosition[2]
        arrayPosition[2] = tempPosition
        idImViewScale.scaleX = 1.0f
        idImViewScale.scaleY = 1.0f
        idImViewScale2.scaleX = 1.0f
        idImViewScale2.scaleY = 1.0f
        if(idImViewScale.visibility == View.VISIBLE) {
            idImViewScale2.setImageBitmap(arrayBitmap[0])
            idImViewScale2.visibility = View.VISIBLE
            idImViewScale.startAnimation(CubeAnimation.create(
                CubeAnimation.RIGHT, false, durationAnimationCubeSpeed ))
            idImViewScale2.startAnimation(CubeAnimation.create(
                CubeAnimation.RIGHT, true, durationAnimationCubeSpeed))
            idImViewScale.visibility = View.GONE
        }else{
            idImViewScale.setImageBitmap(arrayBitmap[0])
            idImViewScale.visibility = View.VISIBLE
            idImViewScale2.startAnimation(CubeAnimation.create(
                CubeAnimation.RIGHT, false, durationAnimationCubeSpeed))
            idImViewScale.startAnimation(CubeAnimation.create(
                CubeAnimation.RIGHT, true, durationAnimationCubeSpeed))
            idImViewScale2.visibility = View.GONE
        }
    }

    fun left (arrayBitmap: ArrayList<Bitmap>, arrayNumber: ArrayList<Int>,
               arrayPosition: ArrayList<Int>, idImViewScale: ImageView,
              idImViewScale2: ImageView, durationAnimationCubeSpeed: Long) { //функ вращает кубик влево
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
        idImViewScale.scaleX = 1.0f
        idImViewScale.scaleY = 1.0f
        idImViewScale2.scaleX = 1.0f
        idImViewScale2.scaleY = 1.0f
        if(idImViewScale.visibility == View.VISIBLE) {
            idImViewScale2.setImageBitmap(arrayBitmap[0])
            idImViewScale2.visibility = View.VISIBLE
            idImViewScale.startAnimation(CubeAnimation.create(
                CubeAnimation.LEFT, false, durationAnimationCubeSpeed))
            idImViewScale2.startAnimation(CubeAnimation.create
                (CubeAnimation.LEFT, true, durationAnimationCubeSpeed))
            idImViewScale.visibility = View.GONE
        }else{
            idImViewScale.setImageBitmap(arrayBitmap[0])
            idImViewScale.visibility = View.VISIBLE
            idImViewScale2.startAnimation(CubeAnimation.create(
                CubeAnimation.LEFT, false, durationAnimationCubeSpeed))
            idImViewScale.startAnimation(CubeAnimation.create(
                CubeAnimation.LEFT, true, durationAnimationCubeSpeed))
            idImViewScale2.visibility = View.GONE
        }
    }
}