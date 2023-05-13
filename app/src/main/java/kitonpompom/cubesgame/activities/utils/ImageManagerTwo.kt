package kitonpompom.cubesgame.activities.utils

import android.R.id
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.exifinterface.media.ExifInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//Класс для обрезки\преобразования фото по нужному размеру
object ImageManagerTwo {

    private const val MAX_IMAGE_SIZE_H = 1770
    private const val MAX_IMAGE_SIZE_W = 1062
    var rotate_aspect_ratio = 0

    fun getImagesSize(uri: Uri, context: Context): List<Int> { //функ получает картинку и возвращает её ширину и высоту

        //val options = BitmapFactory.Options().apply {
        //    inJustDecodeBounds = true
       // }

        val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, uri))
        } else {
            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        }


        //BitmapFactory.decodeResource(context.resources, uri,)
        //Log.d("MyLog", "uri: $uri ")
        //Log.d("MyLog", "высота: ${bit.height}")
        //Log.d("MyLog", "ширина: ${bit.width}")

        //val rat = imageRotation(uri, context)
        //Log.d("MyLog", "оптион.ширина: ${bitmap.width}")
        //Log.d("MyLog", "оптион.высота: ${bitmap.height}")

        /*return if (bitmap.height > bitmap.width) {
            Log.d("MyLog", "Сработало 1")
            rotate_aspect_ratio = 1
            listOf(bitmap.height, bitmap.width)
        }else{
            rotate_aspect_ratio = 0
            Log.d("MyLog", "Сработало 2")
                listOf(bitmap.width, bitmap.height)
            }*/

        return if (imageRotation(uri, context) == 90) {
            rotate_aspect_ratio = 1 //port
            //Log.d("MyLog", "ориентация портретная")
            listOf(bitmap.height, bitmap.width)
        } else{
            rotate_aspect_ratio = 0 //album
            //Log.d("MyLog", "ориентация альбомная")
            listOf(bitmap.width, bitmap.height)
        }

    }

    private fun imageRotation(uri: Uri, context:Context): Int { //функция при помощи которой узнаем повернуто ли фото при съемке
        val rotation: Int
        //val imageFile = File(uri) // превращаем ссылку в файл при помощи класса Фаил, передавая в него ссылку
        val inputStream = context.contentResolver.openInputStream(uri)

        val exif = inputStream?.let { ExifInterface(it) } //получаем из картинки доп. данные
        val orientation = exif?.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        ) //получаем из атрибутов значение ориентации, второе значение по умалчание если вдруг в атрибутах нет знач. ориентации
        rotation =
            if (orientation == ExifInterface.ORIENTATION_ROTATE_90 || orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                90
            } else {
                0
            }

        //Log.d("MyLog", "Ориентейшн: $orientation")
        //Log.d("MyLog", "Ротатион: $rotation")

        return rotation
    }


    suspend fun imageResize(context: Context, uris: List<Uri>) : List<Bitmap>  = withContext(Dispatchers.IO) { //функция преобразования/уменьшения размера фото
        val tempList = ArrayList<List<Int>>()
        val bitmapList = ArrayList<Bitmap>()



        for (n in uris.indices) {
            var size = getImagesSize(uris[n], context)
            //Log.d("MyLog", "Размер массива коэфициент: ${size[1]}")
            val imageRatio = size[0].toFloat() / size[1].toFloat()
            //Log.d("MyLog", "Ратио: $imageRatio")
            //Log.d("MyLog", "Size 0: ${size[0]}")
            //Log.d("MyLog", "Size 1: ${size[1]}")
            if (imageRatio > 1) {
                if (size[0] > MAX_IMAGE_SIZE_H) {
                    //Log.d("MyLog", "Сработало 1.1")
                    tempList.add(listOf(MAX_IMAGE_SIZE_H, (MAX_IMAGE_SIZE_H * imageRatio).toInt()))
                } else {
                    //Log.d("MyLog", "Сработало 2.2")
                    tempList.add(listOf(size[0], size[1]))
                }
            } else {
                if (size[1] > MAX_IMAGE_SIZE_H) {
                    tempList.add(listOf((MAX_IMAGE_SIZE_H * imageRatio).toInt(), MAX_IMAGE_SIZE_H))
                } else {
                    tempList.add(listOf(size[0], size[1]))
                }
            }
        }

        for (i in uris.indices){ //цикл где мы берем ссылки и при помощи библиотеки пикассо, добавляя в них уже сгенерированые размеры преобразовываем картинки

            //Преобразуем пришедшую ссылку из uris: List<Uri> в Bitmap
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, uris[i]))
            } else {
                MediaStore.Images.Media.getBitmap(context.contentResolver, uris[i])
            }

            val e = kotlin.runCatching {

                //if(rotate_aspect_ratio == 1){

                    /*val bitmapResize1 =  Picasso.get().load(uris[i])
                        .rotate(90f)
                        //.transform(TransformationImage())
                        .resize(tempList[i][0], tempList[i][1])
                        .get()*/

                    //Вычисляем коэфициент пришедшей картинки
                    val kP: Float= bitmap.width.toFloat() / bitmap.height.toFloat()
                    val kNormal = 1062/1770
                //Если ширина и высота больше 1080*1920, уменьшаем и обрезаем
                if (bitmap.width > 1062 && bitmap.height > 1770){
                    //Log.d("MyLog", "kP: $kP")
                    if (kP > 0.6) { //Если коофициент прихода больше, то обрезаем по ширине
                        val sizeW = (bitmap.height.toFloat() * 0.6).toInt()
                        val sizeH = bitmap.height
                        val xCoordinate = (bitmap.width - sizeW) / 2
                        val yCoordinate = 0
                        //Обрезаем битмап по заданым размерам
                        val crBitmap: Bitmap =
                            Bitmap.createBitmap(bitmap, xCoordinate, yCoordinate, sizeW, sizeH)
                        //Уменьшаем пришедший битмап пропорцианально, соответствуя размерам 1080 * 1920
                        val resultScaled: Bitmap =
                            Bitmap.createScaledBitmap(crBitmap, 1062, 1770, false)
                        if(crBitmap != bitmap && crBitmap != resultScaled)
                        crBitmap.recycle()
                        //Добавляем в массив полученый Bitmap
                        bitmapList.add(resultScaled)
                    }else if(kP < 0.6){
                        val sizeW = bitmap.width
                        val sizeH = (bitmap.width / 0.6).toInt()
                        val xCoordinate = 0
                        val yCoordinate = (bitmap.height - sizeH) / 2
                        //Log.d("MyLog", "sizeW: $sizeW")
                        //Log.d("MyLog", "sizeH: $sizeH")
                        //Обрезаем битмап по заданым размерам
                        val crBitmap: Bitmap =
                            Bitmap.createBitmap(bitmap, xCoordinate, yCoordinate, sizeW, sizeH)
                        //Log.d("MyLog", "resultH: ${crBitmap.height}")
                        //Log.d("MyLog", "resultW: ${crBitmap.width}")
                        //Уменьшаем пришедший битмап пропорцианально, соответствуя размерам 1080 * 1920
                        val resultScaled: Bitmap =
                            Bitmap.createScaledBitmap(crBitmap, 1062, 1770, false)
                        crBitmap.recycle()
                        //Log.d("MyLog", "Полученая высота после уменьшения: ${resultScaled.height}")
                        //Log.d("MyLog", "Полученя ширина после уменьшения: ${resultScaled.width}")
                        //Добавляем в массив полученый Bitmap
                        bitmapList.add(resultScaled)
                        //Log.d("MyLog", "Полученая высота после обрезки: ${bitmapList[i].height}")
                        //Log.d("MyLog", "Полученя ширина после обрезки: ${bitmapList[i].width}")
                    }else{
                        val resultScaled: Bitmap =
                            Bitmap.createScaledBitmap(bitmap, 1062, 1770, false)
                        bitmapList.add(resultScaled)
                        //Log.d("MyLog", "Полученая высота после обрезки: ${bitmapList[i].height}")
                        //Log.d("MyLog", "Полученя ширина после обрезки: ${bitmapList[i].width}")
                    }
                }else{
                    if (kP > 0.6) { //Если коофициент прихода больше, то обрезаем по ширине
                        val sizeW = (bitmap.height.toFloat() * 0.6).toInt()
                        val sizeH = bitmap.height
                        val xCoordinate = (bitmap.width - sizeW) / 2
                        val yCoordinate = 0
                        //Log.d("MyLog", "sizeW: $sizeW")
                        //Log.d("MyLog", "sizeH: $sizeH")
                        //Обрезаем битмап по заданым размерам
                        val crBitmap: Bitmap =
                            Bitmap.createBitmap(bitmap, xCoordinate, yCoordinate, sizeW, sizeH)
                        //Log.d("MyLog", "resultH: ${crBitmap.height}")
                        //Log.d("MyLog", "resultW: ${crBitmap.width}")
                        //Уменьшаем пришедший битмап пропорцианально, соответствуя размерам 1080 * 1920
                        val resultScaled: Bitmap =
                            Bitmap.createScaledBitmap(crBitmap, 1062, 1770, true)
                        if(crBitmap != bitmap && crBitmap != resultScaled)
                        crBitmap.recycle()

                        //Log.d("MyLog", "Полученая высота после уменьшения: ${resultScaled.height}")
                        //Log.d("MyLog", "Полученя ширина после уменьшения: ${resultScaled.width}")
                        //Добавляем в массив полученый Bitmap
                        bitmapList.add(resultScaled)
                        Toast.makeText(context, "Изображение должно быть не меньше 1080*1920, качество может пострадать.", Toast.LENGTH_LONG).show()
                        //Log.d("MyLog", "Полученая высота после обрезки: ${bitmapList[i].height}")
                        //Log.d("MyLog", "Полученя ширина после обрезки: ${bitmapList[i].width}")
                    }else if(kP > 0.6){ //Если коэфициент меньше то обрезаем по высоте
                        val sizeW = bitmap.width
                        val sizeH = (bitmap.width / 0.6).toInt()
                        val xCoordinate = 0
                        val yCoordinate = (bitmap.height - sizeH) / 2
                        //Log.d("MyLog", "sizeW: $sizeW")
                        //Log.d("MyLog", "sizeH: $sizeH")
                        //Обрезаем битмап по заданым размерам
                        val crBitmap: Bitmap =
                            Bitmap.createBitmap(bitmap, xCoordinate, yCoordinate, sizeW, sizeH)
                        //Уменьшаем пришедший битмап пропорцианально, соответствуя размерам 1080 * 1920
                        val resultScaled: Bitmap =
                            Bitmap.createScaledBitmap(crBitmap, 1062, 1770, true)
                        if(crBitmap != bitmap && crBitmap != resultScaled)
                        crBitmap.recycle()
                        //Добавляем в массив полученый Bitmap
                        //Чистим битмап хеш
                        //crBitmap.recycle()
                        bitmapList.add(resultScaled)
                        Toast.makeText(context, "Изображение должно быть не меньше 1080*1920, качество может пострадать.", Toast.LENGTH_LONG).show()
                        //Log.d("MyLog", "Полученая высота после обрезки: ${bitmapList[i].height}")
                        //Log.d("MyLog", "Полученя ширина после обрезки: ${bitmapList[i].width}")
                    }else{
                        val resultScaled: Bitmap =
                            Bitmap.createScaledBitmap(bitmap, 1062, 1770, true)
                        bitmapList.add(resultScaled)
                        Toast.makeText(context, "Изображение должно быть не меньше 1080*1920, качество может пострадать.", Toast.LENGTH_LONG).show()
                        //Log.d("MyLog", "Полученая высота после обрезки: ${bitmapList[i].height}")
                        //Log.d("MyLog", "Полученя ширина после обрезки: ${bitmapList[i].width}")
                    }
                }

                /*}else{
                    var bitmapResize = Picasso.get().load(uris[i]).resize(tempList[i][0], tempList[i][1]).get()
                    val result: Bitmap = Bitmap.createBitmap(bitmapResize, 0, 0, 200, 200)
                    bitmapList.add(
                            result
                         //Picasso.get().load(uris[i]).resize(tempList[i][0], tempList[i][1]).get()
                    )
                    Log.d("MyLog", "Полученая высота после уменьшения: ${bitmapList[i].height}")
                    Log.d("MyLog", "Полученя ширина после уменьшения: ${bitmapList[i].width}")
            }*/
            }
            if(!bitmapList.contains(bitmap))
                bitmap.recycle()
                }

        return@withContext bitmapList
    }

    fun chooseScaleType(im : ImageView, bitMap : Bitmap){//функц. которая растягивает картинку в зависимости от ее ориентации. вертикальные не трогает.
        if(bitMap.width > bitMap.height){
            //Log.d("MyLog", "Center_crop")
            im.scaleType = ImageView.ScaleType.CENTER_CROP
        }else{
            //Log.d("MyLog", "Center_inside")
            im.scaleType = ImageView.ScaleType.FIT_CENTER
        }
    }

    suspend fun croppedImageHard(bitMap: Bitmap): List<Bitmap> = withContext(Dispatchers.IO){
        val tempArray = ArrayList<Bitmap>()

        tempArray.add(Bitmap.createBitmap(bitMap, 0, 0, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 177, 0, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 354, 0, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 531, 0, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 708, 0, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 885, 0, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 177, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 177, 177, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 354, 177, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 531, 177, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 708, 177, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 885, 177, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 354, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 177, 354, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 354, 354, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 531, 354, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 708, 354, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 885, 354, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 531, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 177, 531, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 354, 531, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 531, 531, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 708, 531, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 885, 531, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 708, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 177, 708, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 354, 708, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 531, 708, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 708, 708, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 885, 708, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 885, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 177, 885, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 354, 885, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 531, 885, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 708, 885, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 885, 885, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 1062, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 177, 1062, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 354, 1062, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 531, 1062, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 708, 1062, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 885, 1062, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 1239, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 177, 1239, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 354, 1239, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 531, 1239, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 708, 1239, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 885, 1239, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 1416, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 177, 1416, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 354, 1416, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 531, 1416, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 708, 1416, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 885, 1416, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 1593, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 177, 1593, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 354, 1593, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 531, 1593, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 708, 1593, 177, 177))
        tempArray.add(Bitmap.createBitmap(bitMap, 885, 1593, 177, 177))

        return@withContext tempArray
    }

    suspend fun croppedImageMedium(bitMap: Bitmap): List<Bitmap> = withContext(Dispatchers.IO){
        val tempArray = ArrayList<Bitmap>()

        tempArray.add(Bitmap.createBitmap(bitMap, 1, 37, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 213, 37, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 425, 37, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 637, 37, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 849, 37, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 1, 249, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 213, 249, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 425, 249, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 637, 249, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 849, 249, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 1, 461, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 213, 461, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 425, 461, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 637, 461, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 849, 461, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 1, 673, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 213, 673, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 425, 673, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 637, 673, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 849, 673, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 1, 885, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 213, 885, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 425, 885, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 637, 885, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 849, 885, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 1, 1097, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 213, 1097, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 425, 1097, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 637, 1097, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 849, 1097, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 1, 1309, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 213, 1309, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 425, 1309, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 637, 1309, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 849, 1309, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 1, 1521, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 213, 1521, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 425, 1521, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 637, 1521, 212, 212))
        tempArray.add(Bitmap.createBitmap(bitMap, 849, 1521, 212, 212))

        return@withContext tempArray
    }

    suspend fun croppedImageEasy(bitMap: Bitmap): List<Bitmap> = withContext(Dispatchers.IO){
        val tempArray = ArrayList<Bitmap>()

        tempArray.add(Bitmap.createBitmap(bitMap, 0, 0, 354, 354))
        tempArray.add(Bitmap.createBitmap(bitMap, 354, 0, 354, 354))
        tempArray.add(Bitmap.createBitmap(bitMap, 708, 0, 354, 354))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 354, 354, 354))
        tempArray.add(Bitmap.createBitmap(bitMap, 354, 354, 354, 354))
        tempArray.add(Bitmap.createBitmap(bitMap, 708, 354, 354, 354))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 708, 354, 354))
        tempArray.add(Bitmap.createBitmap(bitMap, 354, 708, 354, 354))
        tempArray.add(Bitmap.createBitmap(bitMap, 708, 708, 354, 354))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 1062, 354, 354))
        tempArray.add(Bitmap.createBitmap(bitMap, 354, 1062, 354, 354))
        tempArray.add(Bitmap.createBitmap(bitMap, 708, 1062, 354, 354))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 1416, 354, 354))
        tempArray.add(Bitmap.createBitmap(bitMap, 354, 1416, 354, 354))
        tempArray.add(Bitmap.createBitmap(bitMap, 708, 1416, 354, 354))

        return@withContext tempArray
    }

}