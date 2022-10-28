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


object ImageManager {

    private const val MAX_IMAGE_SIZE_H = 1920
    private const val MAX_IMAGE_SIZE_W = 1080
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
                    val kNormal = 1080/1920
                //Если ширина и высота больше 1080*1920, уменьшаем и обрезаем
                if (bitmap.width > 1080 && bitmap.height > 1920){
                    //Log.d("MyLog", "kP: $kP")
                    if (kP > 0.5625) { //Если коофициент прихода больше, то обрезаем по ширине
                        val sizeW = (bitmap.height.toFloat() * 0.5625).toInt()
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
                            Bitmap.createScaledBitmap(crBitmap, 1080, 1920, false)
                        //Log.d("MyLog", "Полученая высота после уменьшения: ${resultScaled.height}")
                        //Log.d("MyLog", "Полученя ширина после уменьшения: ${resultScaled.width}")
                        //Добавляем в массив полученый Bitmap
                        bitmapList.add(resultScaled)
                        //Log.d("MyLog", "Полученая высота после обрезки: ${bitmapList[i].height}")
                        //Log.d("MyLog", "Полученя ширина после обрезки: ${bitmapList[i].width}")
                    }else if(kP < 0.5625){
                        val sizeW = bitmap.width
                        val sizeH = (bitmap.width / 0.5625).toInt()
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
                            Bitmap.createScaledBitmap(crBitmap, 1080, 1920, false)
                        //Log.d("MyLog", "Полученая высота после уменьшения: ${resultScaled.height}")
                        //Log.d("MyLog", "Полученя ширина после уменьшения: ${resultScaled.width}")
                        //Добавляем в массив полученый Bitmap
                        bitmapList.add(resultScaled)
                        //Log.d("MyLog", "Полученая высота после обрезки: ${bitmapList[i].height}")
                        //Log.d("MyLog", "Полученя ширина после обрезки: ${bitmapList[i].width}")
                    }else{
                        val resultScaled: Bitmap =
                            Bitmap.createScaledBitmap(bitmap, 1080, 1920, false)
                        bitmapList.add(resultScaled)
                        //Log.d("MyLog", "Полученая высота после обрезки: ${bitmapList[i].height}")
                        //Log.d("MyLog", "Полученя ширина после обрезки: ${bitmapList[i].width}")
                    }
                }else{
                    if (kP > 0.5625) { //Если коофициент прихода больше, то обрезаем по ширине
                        val sizeW = (bitmap.height.toFloat() * 0.5625).toInt()
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
                            Bitmap.createScaledBitmap(crBitmap, 1080, 1920, true)
                        //Log.d("MyLog", "Полученая высота после уменьшения: ${resultScaled.height}")
                        //Log.d("MyLog", "Полученя ширина после уменьшения: ${resultScaled.width}")
                        //Добавляем в массив полученый Bitmap
                        bitmapList.add(resultScaled)
                        Toast.makeText(context, "Изображение должно быть не меньше 1080*1920, качество может пострадать.", Toast.LENGTH_LONG).show()
                        //Log.d("MyLog", "Полученая высота после обрезки: ${bitmapList[i].height}")
                        //Log.d("MyLog", "Полученя ширина после обрезки: ${bitmapList[i].width}")
                    }else if(kP > 0.5625){ //Если коэфициент меньше то обрезаем по высоте
                        val sizeW = bitmap.width
                        val sizeH = (bitmap.width / 0.5625).toInt()
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
                            Bitmap.createScaledBitmap(crBitmap, 1080, 1920, true)
                        //Log.d("MyLog", "Полученая высота после уменьшения: ${resultScaled.height}")
                        //Log.d("MyLog", "Полученя ширина после уменьшения: ${resultScaled.width}")
                        //Добавляем в массив полученый Bitmap
                        bitmapList.add(resultScaled)
                        Toast.makeText(context, "Изображение должно быть не меньше 1080*1920, качество может пострадать.", Toast.LENGTH_LONG).show()
                        //Log.d("MyLog", "Полученая высота после обрезки: ${bitmapList[i].height}")
                        //Log.d("MyLog", "Полученя ширина после обрезки: ${bitmapList[i].width}")
                    }else{
                        val resultScaled: Bitmap =
                            Bitmap.createScaledBitmap(bitmap, 1080, 1920, true)
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

    suspend fun croppedImage(bitMap: Bitmap): List<Bitmap> = withContext(Dispatchers.IO){
        val tempArray = ArrayList<Bitmap>()

        tempArray.add(Bitmap.createBitmap(bitMap, 0, 0, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 120, 0, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 240, 0, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 360, 0, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 480, 0, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 600, 0, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 720, 0, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 840, 0, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 960, 0, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 120, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 120, 120, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 240, 120, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 360, 120, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 480, 120, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 600, 120, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 720, 120, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 840, 120, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 960, 120, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 240, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 120, 240, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 240, 240, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 360, 240, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 480, 240, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 600, 240, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 720, 240, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 840, 240, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 960, 240, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 360, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 120, 360, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 240, 360, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 360, 360, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 480, 360, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 600, 360, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 720, 360, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 840, 360, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 960, 360, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 480, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 120, 480, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 240, 480, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 360, 480, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 480, 480, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 600, 480, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 720, 480, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 840, 480, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 960, 480, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 600, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 120, 600, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 240, 600, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 360, 600, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 480, 600, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 600, 600, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 720, 600, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 840, 600, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 960, 600, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 720, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 120, 720, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 240, 720, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 360, 720, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 480, 720, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 600, 720, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 720, 720, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 840, 720, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 960, 720, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 840, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 120, 840, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 240, 840, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 360, 840, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 480, 840, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 600, 840, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 720, 840, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 840, 840, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 960, 840, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 960, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 120, 960, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 240, 960, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 360, 960, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 480, 960, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 600, 960, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 720, 960, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 840, 960, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 960, 960, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 1080, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 120, 1080, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 240, 1080, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 360, 1080, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 480, 1080, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 600, 1080, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 720, 1080, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 840, 1080, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 960, 1080, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 1200, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 120, 1200, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 240, 1200, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 360, 1200, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 480, 1200, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 600, 1200, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 720, 1200, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 840, 1200, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 960, 1200, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 1320, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 120, 1320, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 240, 1320, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 360, 1320, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 480, 1320, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 600, 1320, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 720, 1320, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 840, 1320, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 960, 1320, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 1440, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 120, 1440, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 240, 1440, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 360, 1440, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 480, 1440, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 600, 1440, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 720, 1440, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 840, 1440, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 960, 1440, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 1560, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 120, 1560, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 240, 1560, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 360, 1560, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 480, 1560, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 600, 1560, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 720, 1560, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 840, 1560, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 960, 1560, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 1680, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 120, 1680, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 240, 1680, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 360, 1680, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 480, 1680, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 600, 1680, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 720, 1680, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 840, 1680, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 960, 1680, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 0, 1800, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 120, 1800, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 240, 1800, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 360, 1800, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 480, 1800, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 600, 1800, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 720, 1800, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 840, 1800, 120, 120))
        tempArray.add(Bitmap.createBitmap(bitMap, 960, 1800, 120, 120))

        return@withContext tempArray
    }

}