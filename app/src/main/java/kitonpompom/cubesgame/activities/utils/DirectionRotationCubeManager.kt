package kitonpompom.cubesgame.activities.utils

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Bitmap
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import kitonpompom.cubesgame.activities.AdapterFragPWPEasy
import kitonpompom.cubesgame.databinding.DrawerLayoutPwpEasyBinding

//Класс для обрезки\преобразования фото по нужному размеру
object DirectionRotationCubeManager {


    fun up(posRotationCube: Int,
           arrayBitmap: ArrayList<Bitmap>, arrayNumber: ArrayList<Int>,
           arrayPosition: ArrayList<Int>, idImViewScale: ImageView,
           idImViewScale2: ImageView, durationAnimationCubeSpeed: Long, numberRotation: Int, adapterEasy: AdapterFragPWPEasy,
           binding: DrawerLayoutPwpEasyBinding, selectImage: Int, openImage: Int, countCubeRotated:Int
    ) { //функ вращает кубик вверх
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
        if (idImViewScale.visibility == View.VISIBLE) {
            idImViewScale2.setImageBitmap(arrayBitmap[0])
            idImViewScale2.visibility = View.VISIBLE
            idImViewScale.startAnimation(
                CubeAnimation.create(
                    CubeAnimation.UP, false, durationAnimationCubeSpeed
                )
            )
            /*idImViewScale2.startAnimation(CubeAnimation.create(
                CubeAnimation.UP, true, durationAnimationCubeSpeed))
            idImViewScale.visibility = View.GONE*/
            val animTrue = CubeAnimation.create(CubeAnimation.UP, true, durationAnimationCubeSpeed)
            animTrue.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    // Анимация началась
                }

                override fun onAnimationEnd(animation: Animation) {
                    // Анимация закончилась
                    idImViewScale.visibility = View.GONE
                    if (numberRotation != Constans.NO_HELPSCORESTART) {
                        animObjectMinus(adapterEasy, posRotationCube, binding, arrayBitmap, arrayNumber, arrayPosition, selectImage, openImage, countCubeRotated)
                    }
                }

                override fun onAnimationRepeat(animation: Animation) {
                    // Анимация повторяется
                }
            })
            idImViewScale2.startAnimation(animTrue)

        } else {
            idImViewScale.setImageBitmap(arrayBitmap[0])
            idImViewScale.visibility = View.VISIBLE
            idImViewScale2.startAnimation(
                CubeAnimation.create(
                    CubeAnimation.UP, false, durationAnimationCubeSpeed
                )
            )
            /*idImViewScale.startAnimation(CubeAnimation.create(
                CubeAnimation.UP, true, durationAnimationCubeSpeed))
            idImViewScale2.visibility = View.GONE*/
            val animTrue = CubeAnimation.create(
                CubeAnimation.UP, true, durationAnimationCubeSpeed
            )
            animTrue.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    // Анимация началась
                }

                override fun onAnimationEnd(animation: Animation) {
                    // Анимация закончилась
                    idImViewScale2.visibility = View.GONE
                    if (numberRotation != Constans.NO_HELPSCORESTART) {
                        animObjectMinus(adapterEasy, posRotationCube, binding, arrayBitmap, arrayNumber, arrayPosition, selectImage, openImage, countCubeRotated)
                    }
                }

                override fun onAnimationRepeat(animation: Animation) {
                    // Анимация повторяется
                }
            })
            idImViewScale.startAnimation(animTrue)

        }
    }

    fun down(posRotationCube: Int,
             arrayBitmap: ArrayList<Bitmap>, arrayNumber: ArrayList<Int>,
             arrayPosition: ArrayList<Int>, idImViewScale: ImageView,
             idImViewScale2: ImageView,
             durationAnimationCubeSpeed: Long, numberRotation: Int, adapterEasy: AdapterFragPWPEasy,
             binding: DrawerLayoutPwpEasyBinding, selectImage: Int, openImage: Int, countCubeRotated: Int
    ) { //функ вращает кубик вниз
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
        if (idImViewScale.visibility == View.VISIBLE) {
            idImViewScale2.setImageBitmap(arrayBitmap[0])
            idImViewScale2.visibility = View.VISIBLE
            idImViewScale.startAnimation(
                CubeAnimation.create(
                    CubeAnimation.DOWN, false, durationAnimationCubeSpeed
                )
            )
            /*idImViewScale2.startAnimation(CubeAnimation.create(
                CubeAnimation.DOWN, true, durationAnimationCubeSpeed))
            idImViewScale.visibility = View.GONE*/
            val animTrue =
                CubeAnimation.create(CubeAnimation.DOWN, true, durationAnimationCubeSpeed)
            animTrue.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    // Анимация началась
                }

                override fun onAnimationEnd(animation: Animation) {
                    // Анимация закончилась
                    idImViewScale.visibility = View.GONE
                    if (numberRotation != Constans.NO_HELPSCORESTART) {
                        animObjectMinus(adapterEasy, posRotationCube, binding, arrayBitmap,
                            arrayNumber, arrayPosition, selectImage, openImage, countCubeRotated)
                    }
                }

                override fun onAnimationRepeat(animation: Animation) {
                    // Анимация повторяется
                }
            })
            idImViewScale2.startAnimation(animTrue)
        } else {
            idImViewScale.setImageBitmap(arrayBitmap[0])
            idImViewScale.visibility = View.VISIBLE
            idImViewScale2.startAnimation(
                CubeAnimation.create(
                    CubeAnimation.DOWN, false, durationAnimationCubeSpeed
                )
            )
            /*idImViewScale.startAnimation(CubeAnimation.create(
                CubeAnimation.DOWN, true, durationAnimationCubeSpeed))
            idImViewScale2.visibility = View.GONE*/
            val animTrue = CubeAnimation.create(
                CubeAnimation.DOWN, true, durationAnimationCubeSpeed
            )
            animTrue.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    // Анимация началась
                }

                override fun onAnimationEnd(animation: Animation) {
                    // Анимация закончилась
                    idImViewScale2.visibility = View.GONE
                    if (numberRotation != Constans.NO_HELPSCORESTART) {
                        animObjectMinus(adapterEasy, posRotationCube, binding, arrayBitmap, arrayNumber,
                            arrayPosition, selectImage, openImage, countCubeRotated)
                    }
                }

                override fun onAnimationRepeat(animation: Animation) {
                    // Анимация повторяется
                }
            })
            idImViewScale.startAnimation(animTrue)
        }
    }

    fun right(posRotationCube: Int,
              arrayBitmap: ArrayList<Bitmap>, arrayNumber: ArrayList<Int>,
              arrayPosition: ArrayList<Int>, idImViewScale: ImageView, idImViewScale2: ImageView,
              durationAnimationCubeSpeed: Long, numberRotation: Int, adapterEasy: AdapterFragPWPEasy,
              binding: DrawerLayoutPwpEasyBinding, selectImage: Int, openImage: Int, countCubeRotated: Int
    ) { //функ вращает кубик вправо
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
        if (idImViewScale.visibility == View.VISIBLE) {
            idImViewScale2.setImageBitmap(arrayBitmap[0])
            idImViewScale2.visibility = View.VISIBLE
            idImViewScale.startAnimation(
                CubeAnimation.create(
                    CubeAnimation.RIGHT, false, durationAnimationCubeSpeed
                )
            )
            /*idImViewScale2.startAnimation(CubeAnimation.create(
                CubeAnimation.RIGHT, true, durationAnimationCubeSpeed))
            idImViewScale.visibility = View.GONE*/
            val animTrue =
                CubeAnimation.create(CubeAnimation.RIGHT, true, durationAnimationCubeSpeed)
            animTrue.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    // Анимация началась
                }

                override fun onAnimationEnd(animation: Animation) {
                    // Анимация закончилась
                    idImViewScale.visibility = View.GONE
                    if (numberRotation != Constans.NO_HELPSCORESTART) {
                        animObjectMinus(adapterEasy, posRotationCube, binding, arrayBitmap,
                            arrayNumber, arrayPosition, selectImage, openImage, countCubeRotated)
                    }
                }

                override fun onAnimationRepeat(animation: Animation) {
                    // Анимация повторяется
                }
            })
            idImViewScale2.startAnimation(animTrue)
        } else {
            idImViewScale.setImageBitmap(arrayBitmap[0])
            idImViewScale.visibility = View.VISIBLE
            idImViewScale2.startAnimation(
                CubeAnimation.create(
                    CubeAnimation.RIGHT, false, durationAnimationCubeSpeed
                )
            )
            /*idImViewScale.startAnimation(CubeAnimation.create(
                CubeAnimation.RIGHT, true, durationAnimationCubeSpeed))
            idImViewScale2.visibility = View.GONE*/
            val animTrue = CubeAnimation.create(
                CubeAnimation.RIGHT, true, durationAnimationCubeSpeed
            )
            animTrue.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    // Анимация началась
                }

                override fun onAnimationEnd(animation: Animation) {
                    // Анимация закончилась
                    idImViewScale2.visibility = View.GONE
                    if (numberRotation != Constans.NO_HELPSCORESTART) {
                        animObjectMinus(adapterEasy, posRotationCube, binding,
                            arrayBitmap, arrayNumber, arrayPosition, selectImage, openImage, countCubeRotated)
                    }
                }

                override fun onAnimationRepeat(animation: Animation) {
                    // Анимация повторяется
                }
            })
            idImViewScale.startAnimation(animTrue)
        }
    }

    fun left(posRotationCube: Int,
             arrayBitmap: ArrayList<Bitmap>, arrayNumber: ArrayList<Int>,
             arrayPosition: ArrayList<Int>, idImViewScale: ImageView,
             idImViewScale2: ImageView, durationAnimationCubeSpeed: Long, numberRotation: Int, adapterEasy: AdapterFragPWPEasy,
             binding: DrawerLayoutPwpEasyBinding, selectImage: Int, openImage: Int, countCubeRotated: Int
    ) { //функ вращает кубик влево
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
        if (idImViewScale.visibility == View.VISIBLE) {
            idImViewScale2.setImageBitmap(arrayBitmap[0])
            idImViewScale2.visibility = View.VISIBLE
            idImViewScale.startAnimation(
                CubeAnimation.create(
                    CubeAnimation.LEFT, false, durationAnimationCubeSpeed
                )
            )
            /*idImViewScale2.startAnimation(CubeAnimation.create
                (CubeAnimation.LEFT, true, durationAnimationCubeSpeed))
            idImViewScale.visibility = View.GONE*/
            val animTrue =
                CubeAnimation.create(CubeAnimation.LEFT, true, durationAnimationCubeSpeed)
            animTrue.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    // Анимация началась
                }

                override fun onAnimationEnd(animation: Animation) {
                    // Анимация закончилась
                    idImViewScale.visibility = View.GONE
                    if (numberRotation != Constans.NO_HELPSCORESTART) {
                        animObjectMinus(adapterEasy, posRotationCube, binding, arrayBitmap,
                            arrayNumber, arrayPosition, selectImage, openImage, countCubeRotated)
                    }
                }

                override fun onAnimationRepeat(animation: Animation) {
                    // Анимация повторяется
                }
            })
            idImViewScale2.startAnimation(animTrue)
        } else {
            idImViewScale.setImageBitmap(arrayBitmap[0])
            idImViewScale.visibility = View.VISIBLE
            idImViewScale2.startAnimation(
                CubeAnimation.create(
                    CubeAnimation.LEFT, false, durationAnimationCubeSpeed
                )
            )
            /*idImViewScale.startAnimation(CubeAnimation.create(
                CubeAnimation.LEFT, true, durationAnimationCubeSpeed))
            idImViewScale2.visibility = View.GONE*/
            val animTrue = CubeAnimation.create(
                CubeAnimation.LEFT, true, durationAnimationCubeSpeed
            )
            animTrue.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    // Анимация началась
                }

                override fun onAnimationEnd(animation: Animation) {
                    // Анимация закончилась
                    idImViewScale2.visibility = View.GONE
                    if (numberRotation != Constans.NO_HELPSCORESTART) {
                        animObjectMinus(adapterEasy, posRotationCube, binding, arrayBitmap,
                            arrayNumber, arrayPosition, selectImage, openImage, countCubeRotated)
                    }
                }

                override fun onAnimationRepeat(animation: Animation) {
                    // Анимация повторяется
                }
            })
            idImViewScale.startAnimation(animTrue)
        }
    }

    fun leftLeft(posRotationCube: Int,
                 arrayBitmap: ArrayList<Bitmap>, arrayNumber: ArrayList<Int>,
                 arrayPosition: ArrayList<Int>, idImViewScale: ImageView,
                 idImViewScale2: ImageView, durationAnimationCubeSpeed: Long, numberRotation: Int, adapterEasy: AdapterFragPWPEasy,
                 binding: DrawerLayoutPwpEasyBinding, selectImage: Int, openImage: Int, countCubeRotated: Int
    ) { //функ вращает кубик влево
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
        if (idImViewScale.visibility == View.VISIBLE) {
            idImViewScale2.setImageBitmap(arrayBitmap[0])
            idImViewScale2.visibility = View.VISIBLE
            idImViewScale.startAnimation(
                CubeAnimation.create(
                    CubeAnimation.LEFT, false, durationAnimationCubeSpeed
                )
            )
            /*idImViewScale2.startAnimation(CubeAnimation.create
                (CubeAnimation.LEFT, true, durationAnimationCubeSpeed))
            idImViewScale.visibility = View.GONE*/
            val animTrue =
                CubeAnimation.create(CubeAnimation.LEFT, true, durationAnimationCubeSpeed)
            animTrue.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    // Анимация началась
                }

                override fun onAnimationEnd(animation: Animation) {
                    // Анимация закончилась
                    idImViewScale.visibility = View.GONE
                    left(posRotationCube,
                        arrayBitmap,
                        arrayNumber,
                        arrayPosition,
                        idImViewScale,
                        idImViewScale2,
                        durationAnimationCubeSpeed,
                        numberRotation, adapterEasy, binding, selectImage, openImage, countCubeRotated)
                }

                override fun onAnimationRepeat(animation: Animation) {
                    // Анимация повторяется
                }
            })
            idImViewScale2.startAnimation(animTrue)
        } else {
            idImViewScale.setImageBitmap(arrayBitmap[0])
            idImViewScale.visibility = View.VISIBLE
            idImViewScale2.startAnimation(
                CubeAnimation.create(
                    CubeAnimation.LEFT, false, durationAnimationCubeSpeed
                )
            )
            //idImViewScale.startAnimation(CubeAnimation.create(
            /*CubeAnimation.LEFT, true, durationAnimationCubeSpeed))
        idImViewScale2.visibility = View.GONE*/
            val animTrue = CubeAnimation.create(
                CubeAnimation.LEFT, true, durationAnimationCubeSpeed
            )
            animTrue.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    // Анимация началась
                }

                override fun onAnimationEnd(animation: Animation) {
                    // Анимация закончилась
                    idImViewScale2.visibility = View.GONE
                    left(posRotationCube,
                        arrayBitmap,
                        arrayNumber,
                        arrayPosition,
                        idImViewScale,
                        idImViewScale2,
                        durationAnimationCubeSpeed,
                        numberRotation, adapterEasy, binding, selectImage, openImage, countCubeRotated)
                }

                override fun onAnimationRepeat(animation: Animation) {
                    // Анимация повторяется
                }
            })
            idImViewScale.startAnimation(animTrue)
        }
    }

    fun rotationHelpScore(
        directionRotation: Int, posRotationCube: Int, arrayBitmap: ArrayList<Bitmap>, arrayNumber: ArrayList<Int>,
        arrayPosition: ArrayList<Int>, idImViewScale: ImageView,
        idImViewScale2: ImageView, durationAnimationCubeSpeed: Long, adapterEasy: AdapterFragPWPEasy,
        binding: DrawerLayoutPwpEasyBinding, selectImage: Int,
        openImage: Int, countCubeRotated: Int
    ) {
        when (directionRotation) {
            0 -> {}
            1 -> {
                right(posRotationCube,
                    arrayBitmap,
                    arrayNumber,
                    arrayPosition,
                    idImViewScale,
                    idImViewScale2,
                    durationAnimationCubeSpeed,
                    directionRotation,
                    adapterEasy,
                    binding,
                    selectImage,
                    openImage,
                    countCubeRotated
                )
            }

            2 -> {
                left(posRotationCube,
                    arrayBitmap,
                    arrayNumber,
                    arrayPosition,
                    idImViewScale,
                    idImViewScale2,
                    durationAnimationCubeSpeed,
                    directionRotation,
                    adapterEasy,
                    binding,
                    selectImage,
                    openImage,
                    countCubeRotated
                )
            }

            3 -> {
                down(posRotationCube,
                    arrayBitmap,
                    arrayNumber,
                    arrayPosition,
                    idImViewScale,
                    idImViewScale2,
                    durationAnimationCubeSpeed,
                    directionRotation,
                    adapterEasy,
                    binding,
                    selectImage,
                    openImage,
                    countCubeRotated
                )
            }

            4 -> {
                up(posRotationCube,
                    arrayBitmap,
                    arrayNumber,
                    arrayPosition,
                    idImViewScale,
                    idImViewScale2,
                    durationAnimationCubeSpeed,
                    directionRotation,
                    adapterEasy,
                    binding,
                    selectImage,
                    openImage,
                    countCubeRotated
                )
            }

            5 -> {
                leftLeft(posRotationCube,
                    arrayBitmap,
                    arrayNumber,
                    arrayPosition,
                    idImViewScale,
                    idImViewScale2,
                    durationAnimationCubeSpeed,
                    directionRotation, adapterEasy,
                    binding,
                    selectImage,
                    openImage,
                    countCubeRotated
                )
            }
        }
    }

    fun animObjectMinus(adapterEasy: AdapterFragPWPEasy, posRotationCube: Int, binding: DrawerLayoutPwpEasyBinding,
                        arrayBitmap: ArrayList<Bitmap>, arrayNumber: ArrayList<Int>, arrayPosition: ArrayList<Int>, selectImage: Int, openImage: Int, countCubeRotated: Int){
        adapterEasy?.updateAdapterPosition(arrayBitmap, arrayNumber, arrayPosition, posRotationCube, Constans.NO_POSITION_MOVE)
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, (binding.layFragPlayPwpEasy.idRcViewFragPWP.width/3f) / binding.layFragPlayPwpEasy.idImViewScale.width)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, (binding.layFragPlayPwpEasy.idRcViewFragPWP.width/3f) / binding.layFragPlayPwpEasy.idImViewScale.width)
        //Определяем какую из двух картинок уменьшать, в зависимости от того какая картинка видна
        var posRotationCube1 = posRotationCube
        var countCubeRotated1 = countCubeRotated
        if (binding.layFragPlayPwpEasy.idImViewScale.visibility == View.VISIBLE) {
            //Анимация уменьшения кубика который крутили
            ObjectAnimator.ofPropertyValuesHolder(binding.layFragPlayPwpEasy.idImViewScale, scaleX, scaleY).apply {
                duration = 400
                doOnStart {
                    //Log.d("MyLog", "start animation minus scale")
                    adapterEasy?.click?.clickable = false
                    //!!!!!!!!!!!!!!!!noClickItemScale = false//Выключить возможность нажатия на увеличеный итем пока идет анимация уменьшения
                    //включится только когда заново увеличится картинка
                }
                start()
                doOnEnd {
                    //Log.d("MyLog", "end animation minus scale")
                    binding.layFragPlayPwpEasy.idImViewScale.visibility = View.GONE
                    binding.layFragPlayPwpEasy.idImViewScale2.visibility = View.GONE
                    adapterEasy?.click?.clickable = true
                    //touchHelper.attachToRecyclerView(binding.idRcViewFragPWP)
                    //noClickItemScale = true
                        adapterEasy?.noMove?.noMoveIfOpenScale = true
                    posRotationCube1++
                    countCubeRotated1++
                    adapterEasy?.helpScoreToDirectionRotationCubeNext(selectImage, openImage, posRotationCube1, countCubeRotated1)
                    }
            }
        }else{
            //Анимация уменьшения второй картинки если она на кубике видна в данный мемент
            ObjectAnimator.ofPropertyValuesHolder(binding.layFragPlayPwpEasy.idImViewScale2, scaleX, scaleY).apply {
                duration = 400
                doOnStart {
                    //Log.d("MyLog", "start animation minus scale2")
                    adapterEasy?.click?.clickable = false
                    //!!!!!!!!!!!!!!!!!!noClickItemScale = false//Выключить возможность нажатия на увеличеный итем пока идет анимация уменьшения
                    //включится только когда заново увеличится картинка
                }
                start()
                doOnEnd {
                    //Log.d("MyLog", "end animation minus scale2")
                    binding.layFragPlayPwpEasy.idImViewScale2.visibility = View.GONE
                    binding.layFragPlayPwpEasy.idImViewScale.visibility = View.GONE
                    adapterEasy?.click?.clickable = true
                    //touchHelper.attachToRecyclerView(binding.idRcViewFragPWP)
                    //noClickItemScale = true
                        adapterEasy?.noMove?.noMoveIfOpenScale = true
                    posRotationCube1++
                    countCubeRotated1++
                    adapterEasy?.helpScoreToDirectionRotationCubeNext(selectImage, openImage, posRotationCube1, countCubeRotated1)
                }
            }
        }
    }

}