package kitonpompom.cubesgame.activities.utils

import android.view.View

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

}