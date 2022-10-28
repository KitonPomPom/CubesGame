package kitonpompom.cubesgame.activities.utils

import android.graphics.Bitmap
import android.util.Log
import com.squareup.picasso.Transformation

public class TransformationImage: Transformation {
    override fun transform(source: Bitmap?): Bitmap {
        var result: Bitmap? = null
        val size = Math.min(source!!.width, source.height)
        var sourceWith = source.width
        var sourceHeight = source.height
        Log.d("MyLog", "source.width: $sourceWith")
        Log.d("MyLog", "source.height: $sourceHeight")
        //val x = (source.width - size) / 2
        //val y = (source.height - size) / 2
        val kFull = 0.5625
        var kP = sourceWith.toFloat() / sourceHeight.toFloat()
        Log.d("MyLog", "Коэф прихода: $kP")
        if (kP >= kFull){
          val sizeW = (source.height * kFull).toInt()
            Log.d("MyLog", "sizeW: $sizeW")
            val x = (source.width - sizeW)
            val y = (source.height - source.height)
            Log.d("MyLog", "x: $x")
            Log.d("MyLog", "y: $y")
            result = Bitmap.createBitmap(source, x, y, sizeW, source.height)
        }else{
            val sizeH = (source.width/kFull).toInt()
            Log.d("MyLog", "SizeH: $sizeH")
            val x = (source.width) / 2
            val y = (source.height - sizeH) / 2
            Log.d("MyLog", "x: $x")
            Log.d("MyLog", "y: $y")
          result = Bitmap.createBitmap(source, x, y, source.width.toInt(), sizeH)
        }

        //val x = (source.width - size) / 2
        //val y = (source.height - size) / 2
        //result = Bitmap.createBitmap(source, x, y, size, size)
        if (result != source) {
            source.recycle()
        }
        return result
     }

    override fun key(): String {
        return "square()";
    }
}