package kitonpompom.cubesgame.activities.utils

import android.util.Log
import android.view.View

//Координаты перемещения картинок и рисования анимации
object MoveItemScale {
    fun movingItemScale(position: Int, withRc: Int, heightRc: Int, withIm: Int, heightIm: Int): ArrayList<Int> {
        val arrayTemp = ArrayList<Int>()
        when (position) {
            0 -> {
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(heightRc - heightRc)
            }
            1 ->{
                val x: Float = ((withRc/(9f/1f)+((withRc/9f)/2f))-(heightIm/2f)).toFloat()
                val y: Float = (heightRc - heightRc).toFloat()
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            2 ->{
                val x: Float = ((withRc/(9f/2f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = (heightRc - heightRc).toFloat()
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            3 ->{
                val x: Float = ((withRc/(9f/3f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = (heightRc - heightRc).toFloat()
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            4 ->{
                val x: Float = ((withRc/(9f/4f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = (heightRc - heightRc).toFloat()
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            5 ->{
                val x: Float = ((withRc/(9f/5f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = (heightRc - heightRc).toFloat()
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            6 ->{
                val x: Float = ((withRc/(9f/6f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = (heightRc - heightRc).toFloat()
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            7 ->{
                val x: Float = ((withRc/(9f/7f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = (heightRc - heightRc).toFloat()
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            8 ->{
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(heightRc - heightRc)
            }
            9 ->{
                val y: Float = ((heightRc/(16f/1f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add((withRc - withRc))
                arrayTemp.add(y.toInt())
            }
            10 ->{
                val x: Float = ((withRc/(9f/1f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/1f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            11 ->{
                val x: Float = ((withRc/(9f/2f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/1f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            12 ->{
                val x: Float = ((withRc/(9f/3f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/1f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            13 ->{
                val x: Float = ((withRc/(9f/4f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/1f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            14 ->{
                val x: Float = ((withRc/(9f/5f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/1f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            15 ->{
                val x: Float = ((withRc/(9f/6f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/1f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            16 ->{
                val x: Float = ((withRc/(9f/7f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/1f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            17 ->{
                val y: Float = ((heightRc/(16f/1f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            18 ->{
                val y: Float = ((heightRc/(16f/2f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            19 ->{
                val x: Float = ((withRc/(9f/1f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/2f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            20 ->{
                val x: Float = ((withRc/(9f/2f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/2f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            21 ->{
                val x: Float = ((withRc/(9f/3f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/2f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            22 ->{
                val x: Float = ((withRc/(9f/4f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/2f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            23 ->{
                val x: Float = ((withRc/(9f/5f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/2f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            24 ->{
                val x: Float = ((withRc/(9f/6f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/2f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            25 ->{
                val x: Float = ((withRc/(9f/7f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/2f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            26 ->{
                val y: Float = ((heightRc/(16f/2f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            27 ->{
                val y: Float = ((heightRc/(16f/3f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            28 ->{
                val x: Float = ((withRc/(9f/1f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/3f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            29 ->{
                val x: Float = ((withRc/(9f/2f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/3f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            30 ->{
                val x: Float = ((withRc/(9f/3f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/3f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            31 ->{
                val x: Float = ((withRc/(9f/4f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/3f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            32 ->{
                val x: Float = ((withRc/(9f/5f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/3f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            33 ->{
                val x: Float = ((withRc/(9f/6f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/3f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            34 ->{
                val x: Float = ((withRc/(9f/7f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/3f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            35 ->{
                val y: Float = ((heightRc/(16f/3f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            36 ->{
                val y: Float = ((heightRc/(16f/4f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            37 ->{
                val x: Float = ((withRc/(9f/1f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/4f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            38 ->{
                val x: Float = ((withRc/(9f/2f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/4f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            39 ->{
                val x: Float = ((withRc/(9f/3f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/4f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            40 ->{
                val x: Float = ((withRc/(9f/4f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/4f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            41 ->{
                val x: Float = ((withRc/(9f/5f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/4f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            42 ->{
                val x: Float = ((withRc/(9f/6f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/4f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            43 ->{
                val x: Float = ((withRc/(9f/7f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/4f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            44 ->{
                val y: Float = ((heightRc/(16f/4f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            45 ->{
                val y: Float = ((heightRc/(16f/5f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            46 ->{
                val x: Float = ((withRc/(9f/1f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/5f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            47 ->{
                val x: Float = ((withRc/(9f/2f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/5f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            48 ->{
                val x: Float = ((withRc/(9f/3f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/5f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            49 ->{
                val x: Float = ((withRc/(9f/4f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/5f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            50 ->{
                val x: Float = ((withRc/(9f/5f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/5f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            51 ->{
                val x: Float = ((withRc/(9f/6f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/5f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            52 ->{
                val x: Float = ((withRc/(9f/7f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/5f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            53 ->{
                val y: Float = ((heightRc/(16f/5f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            54 ->{
                val y: Float = ((heightRc/(16f/6f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            55 ->{
                val x: Float = ((withRc/(9f/1f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/6f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            56 ->{
                val x: Float = ((withRc/(9f/2f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/6f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            57 ->{
                val x: Float = ((withRc/(9f/3f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/6f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            58 ->{
                val x: Float = ((withRc/(9f/4f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/6f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            59 ->{
                val x: Float = ((withRc/(9f/5f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/6f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            60 ->{
                val x: Float = ((withRc/(9f/6f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/6f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            61 ->{
                val x: Float = ((withRc/(9f/7f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/6f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            62 ->{
                val y: Float = ((heightRc/(16f/6f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            63 ->{
                val y: Float = ((heightRc/(16f/7f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            64 ->{
                val x: Float = ((withRc/(9f/1f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/7f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            65 ->{
                val x: Float = ((withRc/(9f/2f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/7f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            66 ->{
                val x: Float = ((withRc/(9f/3f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/7f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            67 ->{
                val x: Float = ((withRc/(9f/4f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/7f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            68 ->{
                val x: Float = ((withRc/(9f/5f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/7f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            69 ->{
                val x: Float = ((withRc/(9f/6f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/7f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            70 ->{
                val x: Float = ((withRc/(9f/7f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/7f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            71 ->{
                val y: Float = ((heightRc/(16f/7f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            72 ->{
                val y: Float = ((heightRc/(16f/8f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            73 ->{
                val x: Float = ((withRc/(9f/1f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/8f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            74 ->{
                val x: Float = ((withRc/(9f/2f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/8f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            75 ->{
                val x: Float = ((withRc/(9f/3f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/8f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            76 ->{
                val x: Float = ((withRc/(9f/4f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/8f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            77 ->{
                val x: Float = ((withRc/(9f/5f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/8f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            78 ->{
                val x: Float = ((withRc/(9f/6f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/8f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            79 ->{
                val x: Float = ((withRc/(9f/7f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/8f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            80 ->{
                val y: Float = ((heightRc/(16f/8f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            81 ->{
                val y: Float = ((heightRc/(16f/9f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            82 ->{
                val x: Float = ((withRc/(9f/1f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/9f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            83 ->{
                val x: Float = ((withRc/(9f/2f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/9f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            84 ->{
                val x: Float = ((withRc/(9f/3f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/9f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            85 ->{
                val x: Float = ((withRc/(9f/4f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/9f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            86 ->{
                val x: Float = ((withRc/(9f/5f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/9f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            87 ->{
                val x: Float = ((withRc/(9f/6f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/9f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            88 ->{
                val x: Float = ((withRc/(9f/7f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/9f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            89 ->{
                val y: Float = ((heightRc/(16f/9f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            90 ->{
                val y: Float = ((heightRc/(16f/10f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            91 ->{
                val x: Float = ((withRc/(9f/1f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/10f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            92 ->{
                val x: Float = ((withRc/(9f/2f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/10f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            93 ->{
                val x: Float = ((withRc/(9f/3f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/10f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            94 ->{
                val x: Float = ((withRc/(9f/4f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/10f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            95 ->{
                val x: Float = ((withRc/(9f/5f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/10f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            96 ->{
                val x: Float = ((withRc/(9f/6f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/10f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            97 ->{
                val x: Float = ((withRc/(9f/7f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/10f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            98 ->{
                val y: Float = ((heightRc/(16f/10f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            99 ->{
                val y: Float = ((heightRc/(16f/11f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            100 ->{
                val x: Float = ((withRc/(9f/1f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/11f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            101 ->{
                val x: Float = ((withRc/(9f/2f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/11f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            102 ->{
                val x: Float = ((withRc/(9f/3f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/11f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            103 ->{
                val x: Float = ((withRc/(9f/4f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/11f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            104 ->{
                val x: Float = ((withRc/(9f/5f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/11f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            105 ->{
                val x: Float = ((withRc/(9f/6f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/11f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            106 ->{
                val x: Float = ((withRc/(9f/7f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/11f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            107 ->{
                val y: Float = ((heightRc/(16f/11f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            108 ->{
                val y: Float = ((heightRc/(16f/12f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            109 ->{
                val x: Float = ((withRc/(9f/1f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/12f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            110 ->{
                val x: Float = ((withRc/(9f/2f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/12f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            111 ->{
                val x: Float = ((withRc/(9f/3f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/12f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            112 ->{
                val x: Float = ((withRc/(9f/4f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/12f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            113 ->{
                val x: Float = ((withRc/(9f/5f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/12f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            114 ->{
                val x: Float = ((withRc/(9f/6f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/12f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            115 ->{
                val x: Float = ((withRc/(9f/7f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/12f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            116 ->{
                val y: Float = ((heightRc/(16f/12f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            117 ->{
                val y: Float = ((heightRc/(16f/13f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            118 ->{
                val x: Float = ((withRc/(9f/1f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/13f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            119 ->{
                val x: Float = ((withRc/(9f/2f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/13f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            120 ->{
                val x: Float = ((withRc/(9f/3f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/13f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            121 ->{
                val x: Float = ((withRc/(9f/4f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/13f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            122 ->{
                val x: Float = ((withRc/(9f/5f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/13f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            123 ->{
                val x: Float = ((withRc/(9f/6f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/13f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            124 ->{
                val x: Float = ((withRc/(9f/7f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/13f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            125 ->{
                val y: Float = ((heightRc/(16f/13f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            126 ->{
                val y: Float = ((heightRc/(16f/14f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            127 ->{
                val x: Float = ((withRc/(9f/1f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/14f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            128 ->{
                val x: Float = ((withRc/(9f/2f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/14f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            129 ->{
                val x: Float = ((withRc/(9f/3f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/14f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            130 ->{
                val x: Float = ((withRc/(9f/4f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/14f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            131 ->{
                val x: Float = ((withRc/(9f/5f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/14f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            132 ->{
                val x: Float = ((withRc/(9f/6f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/14f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            133 ->{
                val x: Float = ((withRc/(9f/7f)+((withRc/9f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(16f/14f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            134 ->{
                val y: Float = ((heightRc/(16f/14f)+((heightRc/16f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            135 ->{
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(heightRc - withIm)
            }
            136 ->{
                val x: Float = ((withRc/(9f/1f)+((withRc/9f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(heightRc - withIm)
            }
            137 ->{
                val x: Float = ((withRc/(9f/2f)+((withRc/9f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(heightRc - withIm)
            }
            138 ->{
                val x: Float = ((withRc/(9f/3f)+((withRc/9f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(heightRc - withIm)
            }
            139 ->{
                val x: Float = ((withRc/(9f/4f)+((withRc/9f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(heightRc - withIm)
            }
            140 ->{
                val x: Float = ((withRc/(9f/5f)+((withRc/9f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(heightRc - withIm)
            }
            141 ->{
                val x: Float = ((withRc/(9f/6f)+((withRc/9f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(heightRc - withIm)
            }
            142 ->{
                val x: Float = ((withRc/(9f/7f)+((withRc/9f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(heightRc - withIm)
            }

            143 -> {
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(heightRc - heightIm)

            }
        }
        return arrayTemp
    }

    fun moveItemRc(x2:Float, y2:Float, viewRc: View): Int{
        var tempPos = 144

        if(x2 > 0f && x2 < viewRc.width/9f && y2 > 0f && y2 < viewRc.height/16f){
            tempPos = 0
        }else if (x2 > viewRc.width/9f && x2 < viewRc.width/(9f/2f) && y2 > 0f && y2 < viewRc.height/16f){
            tempPos = 1
        }else if (x2 > viewRc.width/(9f/2f) && x2 < viewRc.width/(9f/3f) && y2 > 0f && y2 < viewRc.height/16f){
            tempPos = 2
        }else if (x2 > viewRc.width/(9f/3f) && x2 < viewRc.width/(9f/4f) && y2 > 0f && y2 < viewRc.height/16f){
            tempPos = 3
        }else if (x2 > viewRc.width/(9f/4f) && x2 < viewRc.width/(9f/5f) && y2 > 0f && y2 < viewRc.height/16f){
            tempPos = 4
        }else if (x2 > viewRc.width/(9f/5f) && x2 < viewRc.width/(9f/6f) && y2 > 0f && y2 < viewRc.height/16f){
            tempPos = 5
        }else if (x2 > viewRc.width/(9f/6f) && x2 < viewRc.width/(9f/7f) && y2 > 0f && y2 < viewRc.height/16f){
            tempPos = 6
        }else if (x2 > viewRc.width/(9f/7f) && x2 < viewRc.width/(9f/8f) && y2 > 0f && y2 < viewRc.height/16f){
            tempPos = 7
        }else if (x2 > viewRc.width/(9f/8f) && x2 < viewRc.width && y2 > 0f && y2 < viewRc.height/16f){
            tempPos = 8
        }else if (x2 > 0f && x2 < viewRc.width / 9f && y2 < viewRc.height/(16f/2f) && y2 > viewRc.height/(16f) ){
            tempPos = 9
        }else if (x2 > viewRc.width/9f && x2 < viewRc.width/(9f/2f) && y2 < viewRc.height/(16f/2f) && y2 > viewRc.height/16f) {
            tempPos = 10
        }else if (x2 > viewRc.width/(9f/2f) && x2 < viewRc.width/(9f/3f) && y2 < viewRc.height/(16f/2f) && y2 > viewRc.height/16f) {
            tempPos = 11
        }else if (x2 > viewRc.width/(9f/3f) && x2 < viewRc.width/(9f/4f) && y2 < viewRc.height/(16f/2f) && y2 > viewRc.height/16f) {
            tempPos = 12
        }else if (x2 > viewRc.width/(9f/4f) && x2 < viewRc.width/(9f/5f) && y2 < viewRc.height/(16f/2f) && y2 > viewRc.height/16f) {
            tempPos = 13
        }else if (x2 > viewRc.width/(9f/5f) && x2 < viewRc.width/(9f/6f) && y2 < viewRc.height/(16f/2f) && y2 > viewRc.height/16f) {
            tempPos = 14
        }else if (x2 > viewRc.width/(9f/6f) && x2 < viewRc.width/(9f/7f) && y2 < viewRc.height/(16f/2f) && y2 > viewRc.height/16f) {
            tempPos = 15
        }else if (x2 > viewRc.width/(9f/7f) && x2 < viewRc.width/(9f/8f) && y2 < viewRc.height/(16f/2f) && y2 > viewRc.height/16f) {
            tempPos = 16
        }else if (x2 > viewRc.width/(9f/8f) && x2 < viewRc.width && y2 < viewRc.height/(16f/2f) && y2 > viewRc.height/16f){
            tempPos = 17
        }else if (x2 > 0f && x2 < viewRc.width / 9f && y2 < viewRc.height/(16f/3f) && y2 > viewRc.height/(16f/2f)){
            tempPos = 18
        }else if (x2 > viewRc.width/9f && x2 < viewRc.width/(9f/2f) && y2 < viewRc.height/(16f/3f) && y2 > viewRc.height/(16f/2)) {
            tempPos = 19
        }else if (x2 > viewRc.width/(9f/2f) && x2 < viewRc.width/(9f/3f) && y2 < viewRc.height/(16f/3f) && y2 > viewRc.height/(16f/2)) {
            tempPos = 20
        }else if (x2 > viewRc.width/(9f/3f) && x2 < viewRc.width/(9f/4f) && y2 < viewRc.height/(16f/3f) && y2 > viewRc.height/(16f/2)) {
            tempPos = 21
        }else if (x2 > viewRc.width/(9f/4f) && x2 < viewRc.width/(9f/5f) && y2 < viewRc.height/(16f/3f) && y2 > viewRc.height/(16f/2)) {
            tempPos = 22
        }else if (x2 > viewRc.width/(9f/5f) && x2 < viewRc.width/(9f/6f) && y2 < viewRc.height/(16f/3f) && y2 > viewRc.height/(16f/2)) {
            tempPos = 23
        }else if (x2 > viewRc.width/(9f/6f) && x2 < viewRc.width/(9f/7f) && y2 < viewRc.height/(16f/3f) && y2 > viewRc.height/(16f/2)) {
            tempPos = 24
        }else if (x2 > viewRc.width/(9f/7f) && x2 < viewRc.width/(9f/8f) && y2 < viewRc.height/(16f/3f) && y2 > viewRc.height/(16f/2)) {
            tempPos = 25
        }else if (x2 > viewRc.width/(9f/8f) && x2 < viewRc.width && y2 < viewRc.height/(16f/3f) && y2 > viewRc.height/(16f/2)){
            tempPos = 26
        }else if (x2 > 0f && x2 < viewRc.width / 9f && y2 < viewRc.height/(16f/4f) && y2 > viewRc.height/(16f/3f)){
            tempPos = 27
        }else if (x2 > viewRc.width/9f && x2 < viewRc.width/(9f/2f) && y2 < viewRc.height/(16f/4f) && y2 > viewRc.height/(16f/3)) {
            tempPos = 28
        }else if (x2 > viewRc.width/(9f/2f) && x2 < viewRc.width/(9f/3f) && y2 < viewRc.height/(16f/4f) && y2 > viewRc.height/(16f/3)) {
            tempPos = 29
        }else if (x2 > viewRc.width/(9f/3f) && x2 < viewRc.width/(9f/4f) && y2 < viewRc.height/(16f/4f) && y2 > viewRc.height/(16f/3)) {
            tempPos = 30
        }else if (x2 > viewRc.width/(9f/4f) && x2 < viewRc.width/(9f/5f) && y2 < viewRc.height/(16f/4f) && y2 > viewRc.height/(16f/3)) {
            tempPos = 31
        }else if (x2 > viewRc.width/(9f/5f) && x2 < viewRc.width/(9f/6f) && y2 < viewRc.height/(16f/4f) && y2 > viewRc.height/(16f/3)) {
            tempPos = 32
        }else if (x2 > viewRc.width/(9f/6f) && x2 < viewRc.width/(9f/7f) && y2 < viewRc.height/(16f/4f) && y2 > viewRc.height/(16f/3)) {
            tempPos = 33
        }else if (x2 > viewRc.width/(9f/7f) && x2 < viewRc.width/(9f/8f) && y2 < viewRc.height/(16f/4f) && y2 > viewRc.height/(16f/3)) {
            tempPos = 34
        }else if (x2 > viewRc.width/(9f/8f) && x2 < viewRc.width && y2 < viewRc.height/(16f/4f) && y2 > viewRc.height/(16f/3f)){
            tempPos = 35
        }else if (x2 > 0f && x2 < viewRc.width / 9f && y2 < viewRc.height/(16f/5f) && y2 > viewRc.height/(16f/4f)){
            tempPos = 36
        }else if (x2 > viewRc.width/9f && x2 < viewRc.width/(9f/2f) && y2 < viewRc.height/(16f/5f) && y2 > viewRc.height/(16f/4)) {
            tempPos = 37
        }else if (x2 > viewRc.width/(9f/2f) && x2 < viewRc.width/(9f/3f) && y2 < viewRc.height/(16f/5f) && y2 > viewRc.height/(16f/4)) {
            tempPos = 38
        }else if (x2 > viewRc.width/(9f/3f) && x2 < viewRc.width/(9f/4f) && y2 < viewRc.height/(16f/5f) && y2 > viewRc.height/(16f/4)) {
            tempPos = 39
        }else if (x2 > viewRc.width/(9f/4f) && x2 < viewRc.width/(9f/5f) && y2 < viewRc.height/(16f/5f) && y2 > viewRc.height/(16f/4)) {
            tempPos = 40
        }else if (x2 > viewRc.width/(9f/5f) && x2 < viewRc.width/(9f/6f) && y2 < viewRc.height/(16f/5f) && y2 > viewRc.height/(16f/4)) {
            tempPos = 41
        }else if (x2 > viewRc.width/(9f/6f) && x2 < viewRc.width/(9f/7f) && y2 < viewRc.height/(16f/5f) && y2 > viewRc.height/(16f/4)) {
            tempPos = 42
        }else if (x2 > viewRc.width/(9f/7f) && x2 < viewRc.width/(9f/8f) && y2 < viewRc.height/(16f/5f) && y2 > viewRc.height/(16f/4)) {
            tempPos = 43
        }else if (x2 > viewRc.width/(9f/8f) && x2 < viewRc.width && y2 < viewRc.height/(16f/5f) && y2 > viewRc.height/(16f/4)){
            tempPos = 44
        }else if (x2 > 0f && x2 < viewRc.width / 9f && y2 < viewRc.height/(16f/6f) && y2 > viewRc.height/(16f/5f)){
            tempPos = 45
        }else if (x2 > viewRc.width/9f && x2 < viewRc.width/(9f/2f) && y2 < viewRc.height/(16f/6f) && y2 > viewRc.height/(16f/5)) {
            tempPos = 46
        }else if (x2 > viewRc.width/(9f/2f) && x2 < viewRc.width/(9f/3f) && y2 < viewRc.height/(16f/6f) && y2 > viewRc.height/(16f/5)) {
            tempPos = 47
        }else if (x2 > viewRc.width/(9f/3f) && x2 < viewRc.width/(9f/4f) && y2 < viewRc.height/(16f/6f) && y2 > viewRc.height/(16f/5)) {
            tempPos = 48
        }else if (x2 > viewRc.width/(9f/4f) && x2 < viewRc.width/(9f/5f) && y2 < viewRc.height/(16f/6f) && y2 > viewRc.height/(16f/5)) {
            tempPos = 49
        }else if (x2 > viewRc.width/(9f/5f) && x2 < viewRc.width/(9f/6f) && y2 < viewRc.height/(16f/6f) && y2 > viewRc.height/(16f/5)) {
            tempPos = 50
        }else if (x2 > viewRc.width/(9f/6f) && x2 < viewRc.width/(9f/7f) && y2 < viewRc.height/(16f/6f) && y2 > viewRc.height/(16f/5)) {
            tempPos = 51
        }else if (x2 > viewRc.width/(9f/7f) && x2 < viewRc.width/(9f/8f) && y2 < viewRc.height/(16f/6f) && y2 > viewRc.height/(16f/5)) {
            tempPos = 52
        }else if (x2 > viewRc.width/(9f/8f) && x2 < viewRc.width && y2 < viewRc.height/(16f/6f) && y2 > viewRc.height/(16f/5)){
            tempPos = 53
        }else if (x2 > 0f && x2 < viewRc.width / 9f && y2 < viewRc.height/(16f/7f) && y2 > viewRc.height/(16f/6f)){
            tempPos = 54
        }else if (x2 > viewRc.width/9f && x2 < viewRc.width/(9f/2f) && y2 < viewRc.height/(16f/7) && y2 > viewRc.height/(16f/6)) {
            tempPos = 55
        }else if (x2 > viewRc.width/(9f/2f) && x2 < viewRc.width/(9f/3f) && y2 < viewRc.height/(16f/7) && y2 > viewRc.height/(16f/6)) {
            tempPos = 56
        }else if (x2 > viewRc.width/(9f/3f) && x2 < viewRc.width/(9f/4f) && y2 < viewRc.height/(16f/7) && y2 > viewRc.height/(16f/6)) {
            tempPos = 57
        }else if (x2 > viewRc.width/(9f/4f) && x2 < viewRc.width/(9f/5f) && y2 < viewRc.height/(16f/7) && y2 > viewRc.height/(16f/6)) {
            tempPos = 58
        }else if (x2 > viewRc.width/(9f/5f) && x2 < viewRc.width/(9f/6f) && y2 < viewRc.height/(16f/7) && y2 > viewRc.height/(16f/6)) {
            tempPos = 59
        }else if (x2 > viewRc.width/(9f/6f) && x2 < viewRc.width/(9f/7f) && y2 < viewRc.height/(16f/7) && y2 > viewRc.height/(16f/6)) {
            tempPos = 60
        }else if (x2 > viewRc.width/(9f/7f) && x2 < viewRc.width/(9f/8f) && y2 < viewRc.height/(16f/7) && y2 > viewRc.height/(16f/6)) {
            tempPos = 61
        }else if (x2 > viewRc.width/(9f/8f) && x2 < viewRc.width && y2 < viewRc.height/(16f/7) && y2 > viewRc.height/(16f/6)){
            tempPos = 62
        }else if (x2 > 0f && x2 < viewRc.width / 9f && y2 < viewRc.height/(16f/8) && y2 > viewRc.height/(16f/7)){
            tempPos = 63
        }else if (x2 > viewRc.width/9f && x2 < viewRc.width/(9f/2f) && y2 < viewRc.height/(16f/8) && y2 > viewRc.height/(16f/7)) {
            tempPos = 64
        }else if (x2 > viewRc.width/(9f/2f) && x2 < viewRc.width/(9f/3f) && y2 < viewRc.height/(16f/8) && y2 > viewRc.height/(16f/7)) {
            tempPos = 65
        }else if (x2 > viewRc.width/(9f/3f) && x2 < viewRc.width/(9f/4f) && y2 < viewRc.height/(16f/8) && y2 > viewRc.height/(16f/7)) {
            tempPos = 66
        }else if (x2 > viewRc.width/(9f/4f) && x2 < viewRc.width/(9f/5f) && y2 < viewRc.height/(16f/8) && y2 > viewRc.height/(16f/7)) {
            tempPos = 67
        }else if (x2 > viewRc.width/(9f/5f) && x2 < viewRc.width/(9f/6f) && y2 < viewRc.height/(16f/8) && y2 > viewRc.height/(16f/7)) {
            tempPos = 68
        }else if (x2 > viewRc.width/(9f/6f) && x2 < viewRc.width/(9f/7f) && y2 < viewRc.height/(16f/8) && y2 > viewRc.height/(16f/7)) {
            tempPos = 69
        }else if (x2 > viewRc.width/(9f/7f) && x2 < viewRc.width/(9f/8f) && y2 < viewRc.height/(16f/8) && y2 > viewRc.height/(16f/7)) {
            tempPos = 70
        }else if (x2 > viewRc.width/(9f/8f) && x2 < viewRc.width && y2 < viewRc.height/(16f/8) && y2 > viewRc.height/(16f/7)){
            tempPos = 71
        }else if (x2 > 0f && x2 < viewRc.width / 9f && y2 < viewRc.height/(16f/9f) && y2 > viewRc.height/(16f/8f)){
            tempPos = 72
        }else if (x2 > viewRc.width/9f && x2 < viewRc.width/(9f/2f) && y2 < viewRc.height/(16f/9) && y2 > viewRc.height/(16f/8)) {
            tempPos = 73
        }else if (x2 > viewRc.width/(9f/2f) && x2 < viewRc.width/(9f/3f) && y2 < viewRc.height/(16f/9) && y2 > viewRc.height/(16f/8)) {
            tempPos = 74
        }else if (x2 > viewRc.width/(9f/3f) && x2 < viewRc.width/(9f/4f) && y2 < viewRc.height/(16f/9) && y2 > viewRc.height/(16f/8)) {
            tempPos = 75
        }else if (x2 > viewRc.width/(9f/4f) && x2 < viewRc.width/(9f/5f) && y2 < viewRc.height/(16f/9) && y2 > viewRc.height/(16f/8)) {
            tempPos = 76
        }else if (x2 > viewRc.width/(9f/5f) && x2 < viewRc.width/(9f/6f) && y2 < viewRc.height/(16f/9) && y2 > viewRc.height/(16f/8)) {
            tempPos = 77
        }else if (x2 > viewRc.width/(9f/6f) && x2 < viewRc.width/(9f/7f) && y2 < viewRc.height/(16f/9) && y2 > viewRc.height/(16f/8)) {
            tempPos = 78
        }else if (x2 > viewRc.width/(9f/7f) && x2 < viewRc.width/(9f/8f) && y2 < viewRc.height/(16f/9) && y2 > viewRc.height/(16f/8)) {
            tempPos = 79
        }else if (x2 > viewRc.width/(9f/8f) && x2 < viewRc.width && y2 < viewRc.height/(16f/9) && y2 > viewRc.height/(16f/8)){
            tempPos = 80
        }else if (x2 > 0f && x2 < viewRc.width / 9f && y2 < viewRc.height/(16f/10) && y2 > viewRc.height/(16f/9)){
            tempPos = 81
        }else if (x2 > viewRc.width/9f && x2 < viewRc.width/(9f/2f) && y2 < viewRc.height/(16f/10) && y2 > viewRc.height/(16f/9)) {
            tempPos = 82
        }else if (x2 > viewRc.width/(9f/2f) && x2 < viewRc.width/(9f/3f) && y2 < viewRc.height/(16f/10) && y2 > viewRc.height/(16f/9)) {
            tempPos = 83
        }else if (x2 > viewRc.width/(9f/3f) && x2 < viewRc.width/(9f/4f) && y2 < viewRc.height/(16f/10) && y2 > viewRc.height/(16f/9)) {
            tempPos = 84
        }else if (x2 > viewRc.width/(9f/4f) && x2 < viewRc.width/(9f/5f) && y2 < viewRc.height/(16f/10) && y2 > viewRc.height/(16f/9)) {
            tempPos = 85
        }else if (x2 > viewRc.width/(9f/5f) && x2 < viewRc.width/(9f/6f) && y2 < viewRc.height/(16f/10) && y2 > viewRc.height/(16f/9)) {
            tempPos = 86
        }else if (x2 > viewRc.width/(9f/6f) && x2 < viewRc.width/(9f/7f) && y2 < viewRc.height/(16f/10) && y2 > viewRc.height/(16f/9)) {
            tempPos = 87
        }else if (x2 > viewRc.width/(9f/7f) && x2 < viewRc.width/(9f/8f) && y2 < viewRc.height/(16f/10) && y2 > viewRc.height/(16f/9)) {
            tempPos = 88
        }else if (x2 > viewRc.width/(9f/8f) && x2 < viewRc.width && y2 < viewRc.height/(16f/10) && y2 > viewRc.height/(16f/9)){
            tempPos = 89
        }else if (x2 > 0f && x2 < viewRc.width / 9f && y2 < viewRc.height/(16f/11) && y2 > viewRc.height/(16f/10)){
            tempPos = 90
        }else if (x2 > viewRc.width/9f && x2 < viewRc.width/(9f/2f) && y2 < viewRc.height/(16f/11) && y2 > viewRc.height/(16f/10)) {
            tempPos = 91
        }else if (x2 > viewRc.width/(9f/2f) && x2 < viewRc.width/(9f/3f) && y2 < viewRc.height/(16f/11) && y2 > viewRc.height/(16f/10)) {
            tempPos = 92
        }else if (x2 > viewRc.width/(9f/3f) && x2 < viewRc.width/(9f/4f) && y2 < viewRc.height/(16f/11) && y2 > viewRc.height/(16f/10)) {
            tempPos = 93
        }else if (x2 > viewRc.width/(9f/4f) && x2 < viewRc.width/(9f/5f) && y2 < viewRc.height/(16f/11) && y2 > viewRc.height/(16f/10)) {
            tempPos = 94
        }else if (x2 > viewRc.width/(9f/5f) && x2 < viewRc.width/(9f/6f) && y2 < viewRc.height/(16f/11) && y2 > viewRc.height/(16f/10)) {
            tempPos = 95
        }else if (x2 > viewRc.width/(9f/6f) && x2 < viewRc.width/(9f/7f) && y2 < viewRc.height/(16f/11) && y2 > viewRc.height/(16f/10)) {
            tempPos = 96
        }else if (x2 > viewRc.width/(9f/7f) && x2 < viewRc.width/(9f/8f) && y2 < viewRc.height/(16f/11) && y2 > viewRc.height/(16f/10)) {
            tempPos = 97
        }else if (x2 > viewRc.width/(9f/8f) && x2 < viewRc.width && y2 < viewRc.height/(16f/11) && y2 > viewRc.height/(16f/10)){
            tempPos = 98
        }else if (x2 > 0f && x2 < viewRc.width / 9f && y2 < viewRc.height/(16f/12) && y2 > viewRc.height/(16f/11)){
            tempPos = 99
        }else if (x2 > viewRc.width/9f && x2 < viewRc.width/(9f/2f) && y2 < viewRc.height/(16f/12) && y2 > viewRc.height/(16f/11)) {
            tempPos = 100
        }else if (x2 > viewRc.width/(9f/2f) && x2 < viewRc.width/(9f/3f) && y2 < viewRc.height/(16f/12) && y2 > viewRc.height/(16f/11)) {
            tempPos = 101
        }else if (x2 > viewRc.width/(9f/3f) && x2 < viewRc.width/(9f/4f) && y2 < viewRc.height/(16f/12) && y2 > viewRc.height/(16f/11)) {
            tempPos = 102
        }else if (x2 > viewRc.width/(9f/4f) && x2 < viewRc.width/(9f/5f) && y2 < viewRc.height/(16f/12) && y2 > viewRc.height/(16f/11)) {
            tempPos = 103
        }else if (x2 > viewRc.width/(9f/5f) && x2 < viewRc.width/(9f/6f) && y2 < viewRc.height/(16f/12) && y2 > viewRc.height/(16f/11)) {
            tempPos = 104
        }else if (x2 > viewRc.width/(9f/6f) && x2 < viewRc.width/(9f/7f) && y2 < viewRc.height/(16f/12) && y2 > viewRc.height/(16f/11)) {
            tempPos = 105
        }else if (x2 > viewRc.width/(9f/7f) && x2 < viewRc.width/(9f/8f) && y2 < viewRc.height/(16f/12) && y2 > viewRc.height/(16f/11)) {
            tempPos = 106
        }else if (x2 > viewRc.width/(9f/8f) && x2 < viewRc.width && y2 < viewRc.height/(16f/12) && y2 > viewRc.height/(16f/11)){
            tempPos = 107
        }else if (x2 > 0f && x2 < viewRc.width / 9f && y2 < viewRc.height/(16f/13) && y2 > viewRc.height/(16f/12)){
            tempPos = 108
        }else if (x2 > viewRc.width/9f && x2 < viewRc.width/(9f/2f) && y2 < viewRc.height/(16f/13f) && y2 > viewRc.height/(16f/12)) {
            tempPos = 109
        }else if (x2 > viewRc.width/(9f/2f) && x2 < viewRc.width/(9f/3f) && y2 < viewRc.height/(16f/13f) && y2 > viewRc.height/(16f/12)) {
            tempPos = 110
        }else if (x2 > viewRc.width/(9f/3f) && x2 < viewRc.width/(9f/4f) && y2 < viewRc.height/(16f/13f) && y2 > viewRc.height/(16f/12)) {
            tempPos = 111
        }else if (x2 > viewRc.width/(9f/4f) && x2 < viewRc.width/(9f/5f) && y2 < viewRc.height/(16f/13f) && y2 > viewRc.height/(16f/12)) {
            tempPos = 112
        }else if (x2 > viewRc.width/(9f/5f) && x2 < viewRc.width/(9f/6f) && y2 < viewRc.height/(16f/13f) && y2 > viewRc.height/(16f/12)) {
            tempPos = 113
        }else if (x2 > viewRc.width/(9f/6f) && x2 < viewRc.width/(9f/7f) && y2 < viewRc.height/(16f/13f) && y2 > viewRc.height/(16f/12)) {
            tempPos = 114
        }else if (x2 > viewRc.width/(9f/7f) && x2 < viewRc.width/(9f/8f) && y2 < viewRc.height/(16f/13f) && y2 > viewRc.height/(16f/12)) {
            tempPos = 115
        }else if (x2 > viewRc.width/(9f/8f) && x2 < viewRc.width && y2 < viewRc.height/(16f/13f) && y2 > viewRc.height/(16f/12)){
            tempPos = 116
        }else if (x2 > 0f && x2 < viewRc.width / 9f && y2 < viewRc.height/(16f/14) && y2 > viewRc.height/(16f/13)){
            tempPos = 117
        }else if (x2 > viewRc.width/9f && x2 < viewRc.width/(9f/2f) && y2 < viewRc.height/(16f/14) && y2 > viewRc.height/(16f/13)) {
            tempPos = 118
        }else if (x2 > viewRc.width/(9f/2f) && x2 < viewRc.width/(9f/3f) && y2 < viewRc.height/(16f/14) && y2 > viewRc.height/(16f/13)) {
            tempPos = 119
        }else if (x2 > viewRc.width/(9f/3f) && x2 < viewRc.width/(9f/4f) && y2 < viewRc.height/(16f/14) && y2 > viewRc.height/(16f/13)) {
            tempPos = 120
        }else if (x2 > viewRc.width/(9f/4f) && x2 < viewRc.width/(9f/5f) && y2 < viewRc.height/(16f/14) && y2 > viewRc.height/(16f/13)) {
            tempPos = 121
        }else if (x2 > viewRc.width/(9f/5f) && x2 < viewRc.width/(9f/6f) && y2 < viewRc.height/(16f/14) && y2 > viewRc.height/(16f/13)) {
            tempPos = 122
        }else if (x2 > viewRc.width/(9f/6f) && x2 < viewRc.width/(9f/7f) && y2 < viewRc.height/(16f/14) && y2 > viewRc.height/(16f/13)) {
            tempPos = 123
        }else if (x2 > viewRc.width/(9f/7f) && x2 < viewRc.width/(9f/8f) && y2 < viewRc.height/(16f/14) && y2 > viewRc.height/(16f/13)) {
            tempPos = 124
        }else if (x2 > viewRc.width/(9f/8f) && x2 < viewRc.width && y2 < viewRc.height/(16f/14) && y2 > viewRc.height/(16f/13)){
            tempPos = 125
        }else if (x2 > 0f && x2 < viewRc.width / 9f && y2 < viewRc.height/(16f/15) && y2 > viewRc.height/(16f/14)){
            tempPos = 126
        }else if (x2 > viewRc.width/9f && x2 < viewRc.width/(9f/2f) && y2 < viewRc.height/(16f/15) && y2 > viewRc.height/(16f/14)) {
            tempPos = 127
        }else if (x2 > viewRc.width/(9f/2f) && x2 < viewRc.width/(9f/3f) && y2 < viewRc.height/(16f/15) && y2 > viewRc.height/(16f/14)) {
            tempPos = 128
        }else if (x2 > viewRc.width/(9f/3f) && x2 < viewRc.width/(9f/4f) && y2 < viewRc.height/(16f/15) && y2 > viewRc.height/(16f/14)) {
            tempPos = 129
        }else if (x2 > viewRc.width/(9f/4f) && x2 < viewRc.width/(9f/5f) && y2 < viewRc.height/(16f/15) && y2 > viewRc.height/(16f/14)) {
            tempPos = 130
        }else if (x2 > viewRc.width/(9f/5f) && x2 < viewRc.width/(9f/6f) && y2 < viewRc.height/(16f/15) && y2 > viewRc.height/(16f/14)) {
            tempPos = 131
        }else if (x2 > viewRc.width/(9f/6f) && x2 < viewRc.width/(9f/7f) && y2 < viewRc.height/(16f/15) && y2 > viewRc.height/(16f/14)) {
            tempPos = 132
        }else if (x2 > viewRc.width/(9f/7f) && x2 < viewRc.width/(9f/8f) && y2 < viewRc.height/(16f/15) && y2 > viewRc.height/(16f/14)) {
            tempPos = 133
        }else if (x2 > viewRc.width/(9f/8f) && x2 < viewRc.width && y2 < viewRc.height/(16f/15) && y2 > viewRc.height/(16f/14)){
            tempPos = 134
        }else if (x2 > 0f && x2 < viewRc.width / 9f && y2 < viewRc.height && y2 > viewRc.height/(16f/15)){
            tempPos = 135
        }else if (x2 > viewRc.width/9f && x2 < viewRc.width/(9f/2f) && y2 < viewRc.height && y2 > viewRc.height/(16f/15)) {
            tempPos = 136
        }else if (x2 > viewRc.width/(9f/2f) && x2 < viewRc.width/(9f/3f) && y2 < viewRc.height && y2 > viewRc.height/(16f/15)) {
            tempPos = 137
        }else if (x2 > viewRc.width/(9f/3f) && x2 < viewRc.width/(9f/4f) && y2 < viewRc.height && y2 > viewRc.height/(16f/15)) {
            tempPos = 138
        }else if (x2 > viewRc.width/(9f/4f) && x2 < viewRc.width/(9f/5f) && y2 < viewRc.height && y2 > viewRc.height/(16f/15)) {
            tempPos = 139
        }else if (x2 > viewRc.width/(9f/5f) && x2 < viewRc.width/(9f/6f) && y2 < viewRc.height && y2 > viewRc.height/(16f/15)) {
            tempPos = 140
        }else if (x2 > viewRc.width/(9f/6f) && x2 < viewRc.width/(9f/7f) && y2 < viewRc.height && y2 > viewRc.height/(16f/15)) {
            tempPos = 141
        }else if (x2 > viewRc.width/(9f/7f) && x2 < viewRc.width/(9f/8f) && y2 < viewRc.height && y2 > viewRc.height/(16f/15)) {
            tempPos = 142
        }else if (x2 > viewRc.width/(9f/8f) && x2 < viewRc.width && y2 < viewRc.height && y2 > viewRc.height/(16f/15)){
            tempPos = 143
        }
        return tempPos
    }

}