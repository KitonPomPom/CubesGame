package kitonpompom.cubesgame.activities.utils

import android.util.Log
import android.view.View

//Координаты перемещения картинок и рисования анимации для поля 6х10
object MoveItemScaleTwo {
    fun movingItemScaleHard(position: Int, withRc: Int, heightRc: Int, withIm: Int, heightIm: Int): ArrayList<Int> {
        val arrayTemp = ArrayList<Int>()
        when (position) {
            0 -> {
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(heightRc - heightRc)
            }
            1 ->{
                val x: Float = ((withRc/(6f/1f)+((withRc/6f)/2f))-(heightIm/2f)).toFloat()
                val y: Float = (heightRc - heightRc).toFloat()
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            2 ->{
                val x: Float = ((withRc/(6f/2f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = (heightRc - heightRc).toFloat()
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            3 ->{
                val x: Float = ((withRc/(6f/3f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = (heightRc - heightRc).toFloat()
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            4 ->{
                val x: Float = ((withRc/(6f/4f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = (heightRc - heightRc).toFloat()
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            5 ->{
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(heightRc - heightRc)
            }
            6 ->{
                val y: Float = ((heightRc/(10f/1f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add((withRc - withRc))
                arrayTemp.add(y.toInt())
            }
            7 ->{
                val x: Float = ((withRc/(6f/1f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/1f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            8 ->{
                val x: Float = ((withRc/(6f/2f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/1f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            9 ->{
                val x: Float = ((withRc/(6f/3f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/1f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            10 ->{
                val x: Float = ((withRc/(6f/4f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/1f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            11 ->{
                val y: Float = ((heightRc/(10f/1f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            12 ->{
                val y: Float = ((heightRc/(10f/2f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            13 ->{
                val x: Float = ((withRc/(6f/1f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/2f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            14 ->{
                val x: Float = ((withRc/(6f/2f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/2f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            15 ->{
                val x: Float = ((withRc/(6f/3f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/2f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            16 ->{
                val x: Float = ((withRc/(6f/4f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/2f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            17 ->{
                val y: Float = ((heightRc/(10f/2f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            18 ->{
                val y: Float = ((heightRc/(10f/3f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            19 ->{
                val x: Float = ((withRc/(6f/1f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/3f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            20 ->{
                val x: Float = ((withRc/(6f/2f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/3f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            21 ->{
                val x: Float = ((withRc/(6f/3f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/3f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            22 ->{
                val x: Float = ((withRc/(6f/4f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/3f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            23 ->{
                val y: Float = ((heightRc/(10f/3f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            24 ->{
                val y: Float = ((heightRc/(10f/4f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            25 ->{
                val x: Float = ((withRc/(6f/1f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/4f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            26 ->{
                val x: Float = ((withRc/(6f/2f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/4f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            27 ->{
                val x: Float = ((withRc/(6f/3f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/4f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            28 ->{
                val x: Float = ((withRc/(6f/4f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/4f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            29 ->{
                val y: Float = ((heightRc/(10f/4f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            30 ->{
                val y: Float = ((heightRc/(10f/5f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            31 ->{
                val x: Float = ((withRc/(6f/1f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/5f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            32 ->{
                val x: Float = ((withRc/(6f/2f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/5f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            33 ->{
                val x: Float = ((withRc/(6f/3f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/5f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            34 ->{
                val x: Float = ((withRc/(6f/4f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/5f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            35 ->{
                val y: Float = ((heightRc/(10f/5f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            36 ->{
                val y: Float = ((heightRc/(10f/6f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            37 ->{
                val x: Float = ((withRc/(6f/1f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/6f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            38 ->{
                val x: Float = ((withRc/(6f/2f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/6f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            39 ->{
                val x: Float = ((withRc/(6f/3f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/6f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            40 ->{
                val x: Float = ((withRc/(6f/4f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/6f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            41 ->{
                val y: Float = ((heightRc/(10f/6f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            42 ->{
                val y: Float = ((heightRc/(10f/7f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            43 ->{
                val x: Float = ((withRc/(6f/1f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/7f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            44 ->{
                val x: Float = ((withRc/(6f/2f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/7f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            45 ->{
                val x: Float = ((withRc/(6f/3f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/7f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            46 ->{
                val x: Float = ((withRc/(6f/4f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/7f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            47 ->{
                val y: Float = ((heightRc/(10f/7f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            48 ->{
                val y: Float = ((heightRc/(10f/8f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            49 ->{
                val x: Float = ((withRc/(6f/1f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/8f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            50 ->{
                val x: Float = ((withRc/(6f/2f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/8f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            51 ->{
                val x: Float = ((withRc/(6f/3f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/8f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            52 ->{
                val x: Float = ((withRc/(6f/4f)+((withRc/6f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(10f/8f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            53 ->{
                val y: Float = ((heightRc/(10f/8f)+((heightRc/10f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            54 ->{
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(heightRc - withIm)
            }
            55 ->{
                val x: Float = ((withRc/(6f/1f)+((withRc/6f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(heightRc - withIm)
            }
            56 ->{
                val x: Float = ((withRc/(6f/2f)+((withRc/6f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(heightRc - withIm)
            }
            57 ->{
                val x: Float = ((withRc/(6f/3f)+((withRc/6f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(heightRc - withIm)
            }
            58 ->{
                val x: Float = ((withRc/(6f/4f)+((withRc/6f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(heightRc - withIm)
            }
            59 -> {
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(heightRc - heightIm)

            }
        }
        return arrayTemp
    }


    fun moveItemRcHard(x2:Float, y2:Float, viewRc: View): Int{
        var tempPos = 60

        if(x2 > 0f && x2 < viewRc.width/6f && y2 > 0f && y2 < viewRc.height/10f){
            tempPos = 0
        }else if (x2 > viewRc.width/6f && x2 < viewRc.width/(6f/2f) && y2 > 0f && y2 < viewRc.height/10f){
            tempPos = 1
        }else if (x2 > viewRc.width/(6f/2f) && x2 < viewRc.width/(6f/3f) && y2 > 0f && y2 < viewRc.height/10f){
            tempPos = 2
        }else if (x2 > viewRc.width/(6f/3f) && x2 < viewRc.width/(6f/4f) && y2 > 0f && y2 < viewRc.height/10f){
            tempPos = 3
        }else if (x2 > viewRc.width/(6f/4f) && x2 < viewRc.width/(6f/5f) && y2 > 0f && y2 < viewRc.height/10f){
            tempPos = 4
        }else if (x2 > viewRc.width/(6f/5f) && x2 < viewRc.width && y2 > 0f && y2 < viewRc.height/10f){
            tempPos = 5
        }else if (x2 > 0f && x2 < viewRc.width / 6f && y2 < viewRc.height/(10f/2f) && y2 > viewRc.height/(10f) ){
            tempPos = 6
        }else if (x2 > viewRc.width/6f && x2 < viewRc.width/(6f/2f) && y2 < viewRc.height/(10f/2f) && y2 > viewRc.height/10f) {
            tempPos = 7
        }else if (x2 > viewRc.width/(6f/2f) && x2 < viewRc.width/(6f/3f) && y2 < viewRc.height/(10f/2f) && y2 > viewRc.height/10f) {
            tempPos = 8
        }else if (x2 > viewRc.width/(6f/3f) && x2 < viewRc.width/(6f/4f) && y2 < viewRc.height/(10f/2f) && y2 > viewRc.height/10f) {
            tempPos = 9
        }else if (x2 > viewRc.width/(6f/4f) && x2 < viewRc.width/(6f/5f) && y2 < viewRc.height/(10f/2f) && y2 > viewRc.height/10f) {
            tempPos = 10
        }else if (x2 > viewRc.width/(6f/5f) && x2 < viewRc.width && y2 < viewRc.height/(10f/2f) && y2 > viewRc.height/10f){
            tempPos = 11
        }else if (x2 > 0f && x2 < viewRc.width / 6f && y2 < viewRc.height/(10f/3f) && y2 > viewRc.height/(10f/2f)){
            tempPos = 12
        }else if (x2 > viewRc.width/6f && x2 < viewRc.width/(6f/2f) && y2 < viewRc.height/(10f/3f) && y2 > viewRc.height/(10f/2)) {
            tempPos = 13
        }else if (x2 > viewRc.width/(6f/2f) && x2 < viewRc.width/(6f/3f) && y2 < viewRc.height/(10f/3f) && y2 > viewRc.height/(10f/2)) {
            tempPos = 14
        }else if (x2 > viewRc.width/(6f/3f) && x2 < viewRc.width/(6f/4f) && y2 < viewRc.height/(10f/3f) && y2 > viewRc.height/(10f/2)) {
            tempPos = 15
        }else if (x2 > viewRc.width/(6f/4f) && x2 < viewRc.width/(6f/5f) && y2 < viewRc.height/(10f/3f) && y2 > viewRc.height/(10f/2)) {
            tempPos = 16
        }else if (x2 > viewRc.width/(6f/5f) && x2 < viewRc.width && y2 < viewRc.height/(10f/3f) && y2 > viewRc.height/(10f/2)){
            tempPos = 17
        }else if (x2 > 0f && x2 < viewRc.width / 6f && y2 < viewRc.height/(10f/4f) && y2 > viewRc.height/(10f/3f)){
            tempPos = 18
        }else if (x2 > viewRc.width/6f && x2 < viewRc.width/(6f/2f) && y2 < viewRc.height/(10f/4f) && y2 > viewRc.height/(10f/3)) {
            tempPos = 19
        }else if (x2 > viewRc.width/(6f/2f) && x2 < viewRc.width/(6f/3f) && y2 < viewRc.height/(10f/4f) && y2 > viewRc.height/(10f/3)) {
            tempPos = 20
        }else if (x2 > viewRc.width/(6f/3f) && x2 < viewRc.width/(6f/4f) && y2 < viewRc.height/(10f/4f) && y2 > viewRc.height/(10f/3)) {
            tempPos = 21
        }else if (x2 > viewRc.width/(6f/4f) && x2 < viewRc.width/(6f/5f) && y2 < viewRc.height/(10f/4f) && y2 > viewRc.height/(10f/3)) {
            tempPos = 22
        }else if (x2 > viewRc.width/(6f/5f) && x2 < viewRc.width && y2 < viewRc.height/(10f/4f) && y2 > viewRc.height/(10f/3f)){
            tempPos = 23
        }else if (x2 > 0f && x2 < viewRc.width / 6f && y2 < viewRc.height/(10f/5f) && y2 > viewRc.height/(10f/4f)){
            tempPos = 24
        }else if (x2 > viewRc.width/6f && x2 < viewRc.width/(6f/2f) && y2 < viewRc.height/(10f/5f) && y2 > viewRc.height/(10f/4)) {
            tempPos = 25
        }else if (x2 > viewRc.width/(6f/2f) && x2 < viewRc.width/(6f/3f) && y2 < viewRc.height/(10f/5f) && y2 > viewRc.height/(10f/4)) {
            tempPos = 26
        }else if (x2 > viewRc.width/(6f/3f) && x2 < viewRc.width/(6f/4f) && y2 < viewRc.height/(10f/5f) && y2 > viewRc.height/(10f/4)) {
            tempPos = 27
        }else if (x2 > viewRc.width/(6f/4f) && x2 < viewRc.width/(6f/5f) && y2 < viewRc.height/(10f/5f) && y2 > viewRc.height/(10f/4)) {
            tempPos = 28
        }else if (x2 > viewRc.width/(6f/5f) && x2 < viewRc.width && y2 < viewRc.height/(10f/5f) && y2 > viewRc.height/(10f/4)){
            tempPos = 29
        }else if (x2 > 0f && x2 < viewRc.width / 6f && y2 < viewRc.height/(10f/6f) && y2 > viewRc.height/(10f/5f)){
            tempPos = 30
        }else if (x2 > viewRc.width/6f && x2 < viewRc.width/(6f/2f) && y2 < viewRc.height/(10f/6f) && y2 > viewRc.height/(10f/5)) {
            tempPos = 31
        }else if (x2 > viewRc.width/(6f/2f) && x2 < viewRc.width/(6f/3f) && y2 < viewRc.height/(10f/6f) && y2 > viewRc.height/(10f/5)) {
            tempPos = 32
        }else if (x2 > viewRc.width/(6f/3f) && x2 < viewRc.width/(6f/4f) && y2 < viewRc.height/(10f/6f) && y2 > viewRc.height/(10f/5)) {
            tempPos = 33
        }else if (x2 > viewRc.width/(6f/4f) && x2 < viewRc.width/(6f/5f) && y2 < viewRc.height/(10f/6f) && y2 > viewRc.height/(10f/5)) {
            tempPos = 34
        }else if (x2 > viewRc.width/(6f/5f) && x2 < viewRc.width && y2 < viewRc.height/(10f/6f) && y2 > viewRc.height/(10f/5)){
            tempPos = 35
        }else if (x2 > 0f && x2 < viewRc.width / 6f && y2 < viewRc.height/(10f/7f) && y2 > viewRc.height/(10f/6f)){
            tempPos = 36
        }else if (x2 > viewRc.width/6f && x2 < viewRc.width/(6f/2f) && y2 < viewRc.height/(10f/7) && y2 > viewRc.height/(10f/6)) {
            tempPos = 37
        }else if (x2 > viewRc.width/(6f/2f) && x2 < viewRc.width/(6f/3f) && y2 < viewRc.height/(10f/7) && y2 > viewRc.height/(10f/6)) {
            tempPos = 38
        }else if (x2 > viewRc.width/(6f/3f) && x2 < viewRc.width/(6f/4f) && y2 < viewRc.height/(10f/7) && y2 > viewRc.height/(10f/6)) {
            tempPos = 39
        }else if (x2 > viewRc.width/(6f/4f) && x2 < viewRc.width/(6f/5f) && y2 < viewRc.height/(10f/7) && y2 > viewRc.height/(10f/6)) {
            tempPos = 40
        }else if (x2 > viewRc.width/(6f/5f) && x2 < viewRc.width && y2 < viewRc.height/(10f/7) && y2 > viewRc.height/(10f/6)){
            tempPos = 41
        }else if (x2 > 0f && x2 < viewRc.width / 6f && y2 < viewRc.height/(10f/8) && y2 > viewRc.height/(10f/7)){
            tempPos = 42
        }else if (x2 > viewRc.width/6f && x2 < viewRc.width/(6f/2f) && y2 < viewRc.height/(10f/8) && y2 > viewRc.height/(10f/7)) {
            tempPos = 43
        }else if (x2 > viewRc.width/(6f/2f) && x2 < viewRc.width/(6f/3f) && y2 < viewRc.height/(10f/8) && y2 > viewRc.height/(10f/7)) {
            tempPos = 44
        }else if (x2 > viewRc.width/(6f/3f) && x2 < viewRc.width/(6f/4f) && y2 < viewRc.height/(10f/8) && y2 > viewRc.height/(10f/7)) {
            tempPos = 45
        }else if (x2 > viewRc.width/(6f/4f) && x2 < viewRc.width/(6f/5f) && y2 < viewRc.height/(10f/8) && y2 > viewRc.height/(10f/7)) {
            tempPos = 46
        }else if (x2 > viewRc.width/(6f/5f) && x2 < viewRc.width && y2 < viewRc.height/(10f/8) && y2 > viewRc.height/(10f/7)){
            tempPos = 47
        }else if (x2 > 0f && x2 < viewRc.width / 6f && y2 < viewRc.height/(10f/9f) && y2 > viewRc.height/(10f/8f)){
            tempPos = 48
        }else if (x2 > viewRc.width/6f && x2 < viewRc.width/(6f/2f) && y2 < viewRc.height/(10f/9) && y2 > viewRc.height/(10f/8)) {
            tempPos = 49
        }else if (x2 > viewRc.width/(6f/2f) && x2 < viewRc.width/(6f/3f) && y2 < viewRc.height/(10f/9) && y2 > viewRc.height/(10f/8)) {
            tempPos = 50
        }else if (x2 > viewRc.width/(6f/3f) && x2 < viewRc.width/(6f/4f) && y2 < viewRc.height/(10f/9) && y2 > viewRc.height/(10f/8)) {
            tempPos = 51
        }else if (x2 > viewRc.width/(6f/4f) && x2 < viewRc.width/(6f/5f) && y2 < viewRc.height/(10f/9) && y2 > viewRc.height/(10f/8)) {
            tempPos = 52
        }else if (x2 > viewRc.width/(6f/5f) && x2 < viewRc.width && y2 < viewRc.height/(10f/9) && y2 > viewRc.height/(10f/8)){
            tempPos = 53
        }else if (x2 > 0f && x2 < viewRc.width / 6f && y2 < viewRc.height && y2 > viewRc.height/(10f/9)){
            tempPos = 54
        }else if (x2 > viewRc.width/6f && x2 < viewRc.width/(6f/2f) && y2 < viewRc.height && y2 > viewRc.height/(10f/9)) {
            tempPos = 55
        }else if (x2 > viewRc.width/(6f/2f) && x2 < viewRc.width/(6f/3f) && y2 < viewRc.height && y2 > viewRc.height/(10f/9)) {
            tempPos = 56
        }else if (x2 > viewRc.width/(6f/3f) && x2 < viewRc.width/(6f/4f) && y2 < viewRc.height && y2 > viewRc.height/(10f/9)) {
            tempPos = 57
        }else if (x2 > viewRc.width/(6f/4f) && x2 < viewRc.width/(6f/5f) && y2 < viewRc.height && y2 > viewRc.height/(10f/9)) {
            tempPos = 58
        }else if (x2 > viewRc.width/(6f/5f) && x2 < viewRc.width && y2 < viewRc.height && y2 > viewRc.height/(10f/9)){
            tempPos = 59
        }
        return tempPos
    }

    fun movingItemScaleMedium(position: Int, withRc: Int, heightRc: Int, withIm: Int, heightIm: Int): ArrayList<Int> {
        val arrayTemp = ArrayList<Int>()
        when (position) {
            0 -> {
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(heightRc - heightRc)
            }
            1 ->{
                val x: Float = ((withRc/(5f/1f)+((withRc/5f)/2f))-(heightIm/2f)).toFloat()
                val y: Float = (heightRc - heightRc).toFloat()
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            2 ->{
                val x: Float = ((withRc/(5f/2f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = (heightRc - heightRc).toFloat()
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            3 ->{
                val x: Float = ((withRc/(5f/3f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = (heightRc - heightRc).toFloat()
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            4 ->{
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(heightRc - heightRc)
            }
            5 ->{
                val y: Float = ((heightRc/(8f/1f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add((withRc - withRc))
                arrayTemp.add(y.toInt())
            }
            6 ->{
                val x: Float = ((withRc/(5f/1f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(8f/1f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            7 ->{
                val x: Float = ((withRc/(5f/2f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(8f/1f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            8 ->{
                val x: Float = ((withRc/(5f/3f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(8f/1f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            9 ->{
                val y: Float = ((heightRc/(8f/1f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            10 ->{
                val y: Float = ((heightRc/(8f/2f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            11 ->{
                val x: Float = ((withRc/(5f/1f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(8f/2f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            12 ->{
                val x: Float = ((withRc/(5f/2f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(8f/2f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            13 ->{
                val x: Float = ((withRc/(5f/3f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(8f/2f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            14 ->{
                val y: Float = ((heightRc/(8f/2f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            15 ->{
                val y: Float = ((heightRc/(8f/3f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            16 ->{
                val x: Float = ((withRc/(5f/1f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(8f/3f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            17 ->{
                val x: Float = ((withRc/(5f/2f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(8f/3f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            18 ->{
                val x: Float = ((withRc/(5f/3f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(8f/3f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            19 ->{
                val y: Float = ((heightRc/(8f/3f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            20 ->{
                val y: Float = ((heightRc/(8f/4f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            21 ->{
                val x: Float = ((withRc/(5f/1f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(8f/4f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            22 ->{
                val x: Float = ((withRc/(5f/2f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(8f/4f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            23 ->{
                val x: Float = ((withRc/(5f/3f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(8f/4f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            24 ->{
                val y: Float = ((heightRc/(8f/4f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            25 ->{
                val y: Float = ((heightRc/(8f/5f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            26 ->{
                val x: Float = ((withRc/(5f/1f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(8f/5f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            27 ->{
                val x: Float = ((withRc/(5f/2f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(8f/5f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            28 ->{
                val x: Float = ((withRc/(5f/3f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(8f/5f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            29 ->{
                val y: Float = ((heightRc/(8f/5f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            30 ->{
                val y: Float = ((heightRc/(8f/6f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            31 ->{
                val x: Float = ((withRc/(5f/1f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(8f/6f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            32 ->{
                val x: Float = ((withRc/(5f/2f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(8f/6f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            33 ->{
                val x: Float = ((withRc/(5f/3f)+((withRc/5f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(8f/6f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            34 ->{
                val y: Float = ((heightRc/(8f/6f)+((heightRc/8f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            35 ->{
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(heightRc - withIm)
            }
            36 ->{
                val x: Float = ((withRc/(5f/1f)+((withRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(heightRc - withIm)
            }
            37 ->{
                val x: Float = ((withRc/(5f/2f)+((withRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(heightRc - withIm)
            }
            38 ->{
                val x: Float = ((withRc/(5f/3f)+((withRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(heightRc - withIm)
            }
            39 -> {
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(heightRc - heightIm)
            }
        }
        return arrayTemp
    }

    fun moveItemRcMedium(x2:Float, y2:Float, viewRc: View): Int{
        var tempPos = 40

        if(x2 > 0f && x2 < viewRc.width/5f && y2 > 0f && y2 < viewRc.height/8f){
            tempPos = 0
        }else if (x2 > viewRc.width/5f && x2 < viewRc.width/(5f/2f) && y2 > 0f && y2 < viewRc.height/8f){
            tempPos = 1
        }else if (x2 > viewRc.width/(5f/2f) && x2 < viewRc.width/(5f/3f) && y2 > 0f && y2 < viewRc.height/8f){
            tempPos = 2
        }else if (x2 > viewRc.width/(5f/3f) && x2 < viewRc.width/(5f/4f) && y2 > 0f && y2 < viewRc.height/8f){
            tempPos = 3
        }else if (x2 > viewRc.width/(5f/4f) && x2 < viewRc.width && y2 > 0f && y2 < viewRc.height/8f){
            tempPos = 4
        }else if (x2 > 0f && x2 < viewRc.width / 5f && y2 < viewRc.height/(8f/2f) && y2 > viewRc.height/(8f) ){
            tempPos = 5
        }else if (x2 > viewRc.width/5f && x2 < viewRc.width/(5f/2f) && y2 < viewRc.height/(8f/2f) && y2 > viewRc.height/8f) {
            tempPos = 6
        }else if (x2 > viewRc.width/(5f/2f) && x2 < viewRc.width/(5f/3f) && y2 < viewRc.height/(8f/2f) && y2 > viewRc.height/8f) {
            tempPos = 7
        }else if (x2 > viewRc.width/(5f/3f) && x2 < viewRc.width/(5f/4f) && y2 < viewRc.height/(8f/2f) && y2 > viewRc.height/8f) {
            tempPos = 8
        }else if (x2 > viewRc.width/(5f/4f) && x2 < viewRc.width && y2 < viewRc.height/(8f/2f) && y2 > viewRc.height/8f){
            tempPos = 9
        }else if (x2 > 0f && x2 < viewRc.width / 5f && y2 < viewRc.height/(8f/3f) && y2 > viewRc.height/(8f/2f)){
            tempPos = 10
        }else if (x2 > viewRc.width/5f && x2 < viewRc.width/(5f/2f) && y2 < viewRc.height/(8f/3f) && y2 > viewRc.height/(8f/2)) {
            tempPos = 11
        }else if (x2 > viewRc.width/(5f/2f) && x2 < viewRc.width/(5f/3f) && y2 < viewRc.height/(8f/3f) && y2 > viewRc.height/(8f/2)) {
            tempPos = 12
        }else if (x2 > viewRc.width/(5f/3f) && x2 < viewRc.width/(5f/4f) && y2 < viewRc.height/(8f/3f) && y2 > viewRc.height/(8f/2)) {
            tempPos = 13
        }else if (x2 > viewRc.width/(5f/4f) && x2 < viewRc.width && y2 < viewRc.height/(8f/3f) && y2 > viewRc.height/(8f/2)){
            tempPos = 14
        }else if (x2 > 0f && x2 < viewRc.width / 5f && y2 < viewRc.height/(8f/4f) && y2 > viewRc.height/(8f/3f)){
            tempPos = 15
        }else if (x2 > viewRc.width/5f && x2 < viewRc.width/(5f/2f) && y2 < viewRc.height/(8f/4f) && y2 > viewRc.height/(8f/3)) {
            tempPos = 16
        }else if (x2 > viewRc.width/(5f/2f) && x2 < viewRc.width/(5f/3f) && y2 < viewRc.height/(8f/4f) && y2 > viewRc.height/(8f/3)) {
            tempPos = 17
        }else if (x2 > viewRc.width/(5f/3f) && x2 < viewRc.width/(5f/4f) && y2 < viewRc.height/(8f/4f) && y2 > viewRc.height/(8f/3)) {
            tempPos = 18
        }else if (x2 > viewRc.width/(5f/4f) && x2 < viewRc.width && y2 < viewRc.height/(8f/4f) && y2 > viewRc.height/(8f/3f)){
            tempPos = 19
        }else if (x2 > 0f && x2 < viewRc.width / 5f && y2 < viewRc.height/(8f/5f) && y2 > viewRc.height/(8f/4f)){
            tempPos = 20
        }else if (x2 > viewRc.width/5f && x2 < viewRc.width/(5f/2f) && y2 < viewRc.height/(8f/5f) && y2 > viewRc.height/(8f/4)) {
            tempPos = 21
        }else if (x2 > viewRc.width/(5f/2f) && x2 < viewRc.width/(5f/3f) && y2 < viewRc.height/(8f/5f) && y2 > viewRc.height/(8f/4)) {
            tempPos = 22
        }else if (x2 > viewRc.width/(5f/3f) && x2 < viewRc.width/(5f/4f) && y2 < viewRc.height/(8f/5f) && y2 > viewRc.height/(8f/4)) {
            tempPos = 23
        }else if (x2 > viewRc.width/(5f/4f) && x2 < viewRc.width && y2 < viewRc.height/(8f/5f) && y2 > viewRc.height/(8f/4)){
            tempPos = 24
        }else if (x2 > 0f && x2 < viewRc.width / 5f && y2 < viewRc.height/(8f/6f) && y2 > viewRc.height/(8f/5f)){
            tempPos = 25
        }else if (x2 > viewRc.width/5f && x2 < viewRc.width/(5f/2f) && y2 < viewRc.height/(8f/6f) && y2 > viewRc.height/(8f/5)) {
            tempPos = 26
        }else if (x2 > viewRc.width/(5f/2f) && x2 < viewRc.width/(5f/3f) && y2 < viewRc.height/(8f/6f) && y2 > viewRc.height/(8f/5)) {
            tempPos = 27
        }else if (x2 > viewRc.width/(5f/3f) && x2 < viewRc.width/(5f/4f) && y2 < viewRc.height/(8f/6f) && y2 > viewRc.height/(8f/5)) {
            tempPos = 28
        }else if (x2 > viewRc.width/(5f/4f) && x2 < viewRc.width && y2 < viewRc.height/(8f/6f) && y2 > viewRc.height/(8f/5)){
            tempPos = 29
        }else if (x2 > 0f && x2 < viewRc.width / 5f && y2 < viewRc.height/(8f/7f) && y2 > viewRc.height/(8f/6f)){
            tempPos = 30
        }else if (x2 > viewRc.width/5f && x2 < viewRc.width/(5f/2f) && y2 < viewRc.height/(8f/7) && y2 > viewRc.height/(8f/6)) {
            tempPos = 31
        }else if (x2 > viewRc.width/(5f/2f) && x2 < viewRc.width/(5f/3f) && y2 < viewRc.height/(8f/7) && y2 > viewRc.height/(8f/6)) {
            tempPos = 32
        }else if (x2 > viewRc.width/(5f/3f) && x2 < viewRc.width/(5f/4f) && y2 < viewRc.height/(8f/7) && y2 > viewRc.height/(8f/6)) {
            tempPos = 33
        }else if (x2 > viewRc.width/(5f/4f) && x2 < viewRc.width && y2 < viewRc.height/(8f/7) && y2 > viewRc.height/(8f/6)){
            tempPos = 34
        }else if (x2 > 0f && x2 < viewRc.width / 5f && y2 < viewRc.height && y2 > viewRc.height/(8f/7)){
            tempPos = 35
        }else if (x2 > viewRc.width/5f && x2 < viewRc.width/(5f/2f) && y2 < viewRc.height && y2 > viewRc.height/(8f/7)) {
            tempPos = 36
        }else if (x2 > viewRc.width/(5f/2f) && x2 < viewRc.width/(5f/3f) && y2 < viewRc.height && y2 > viewRc.height/(8f/7)) {
            tempPos = 37
        }else if (x2 > viewRc.width/(5f/3f) && x2 < viewRc.width/(5f/4f) && y2 < viewRc.height && y2 > viewRc.height/(8f/7)) {
            tempPos = 38
        }else if (x2 > viewRc.width/(5f/4f) && x2 < viewRc.width && y2 < viewRc.height && y2 > viewRc.height/(8f/7)){
            tempPos = 39
        }
        return tempPos
    }

    fun movingItemScaleEasy(position: Int, withRc: Int, heightRc: Int, withIm: Int, heightIm: Int): ArrayList<Int> {
        val arrayTemp = ArrayList<Int>()
        when (position) {
            0 -> {
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(heightRc - heightRc)
            }
            1 ->{
                val x: Float = ((withRc/(3f/1f)+((withRc/3f)/2f))-(heightIm/2f)).toFloat()
                val y: Float = (heightRc - heightRc).toFloat()
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            2 ->{
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(heightRc - heightRc)
            }
            3 ->{
                val y: Float = ((heightRc/(5f/1f)+((heightRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add((withRc - withRc))
                arrayTemp.add(y.toInt())
            }
            4 ->{
                val x: Float = ((withRc/(3f/1f)+((withRc/3f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(5f/1f)+((heightRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            5 ->{
                val y: Float = ((heightRc/(5f/1f)+((heightRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            6 ->{
                val y: Float = ((heightRc/(5f/2f)+((heightRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            7 ->{
                val x: Float = ((withRc/(3f/1f)+((withRc/3f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(5f/2f)+((heightRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            8 ->{
                val y: Float = ((heightRc/(5f/2f)+((heightRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            9 ->{
                val y: Float = ((heightRc/(5f/3f)+((heightRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            10 ->{
                val x: Float = ((withRc/(3f/1f)+((withRc/3f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(5f/3f)+((heightRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            11 ->{
                val y: Float = ((heightRc/(5f/3f)+((heightRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            12 ->{
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(heightRc - withIm)
            }
            13 ->{
                val x: Float = ((withRc/(3f/1f)+((withRc/3f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(heightRc - withIm)
            }
            14 -> {
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(heightRc - heightIm)
            }
        }
        return arrayTemp
    }

    fun moveItemRcEasy(x2:Float, y2:Float, viewRc: View): Int{
        var tempPos = 15

        if(x2 > 0f && x2 < viewRc.width/3f && y2 > 0f && y2 < viewRc.height/5f){
            tempPos = 0
        }else if (x2 > viewRc.width/3f && x2 < viewRc.width/(3f/2f) && y2 > 0f && y2 < viewRc.height/5f){
            tempPos = 1
        }else if (x2 > viewRc.width/(3f/2f) && x2 < viewRc.width && y2 > 0f && y2 < viewRc.height/5f){
            tempPos = 2
        }else if (x2 > 0f && x2 < viewRc.width / 3f && y2 < viewRc.height/(5f/2f) && y2 > viewRc.height/(5f) ){
            tempPos = 3
        }else if (x2 > viewRc.width/3f && x2 < viewRc.width/(3f/2f) && y2 < viewRc.height/(5f/2f) && y2 > viewRc.height/5f) {
            tempPos = 4
        }else if (x2 > viewRc.width/(3f/2f) && x2 < viewRc.width && y2 < viewRc.height/(5f/2f) && y2 > viewRc.height/5f){
            tempPos = 5
        }else if (x2 > 0f && x2 < viewRc.width / 3f && y2 < viewRc.height/(5f/3f) && y2 > viewRc.height/(5f/2f)){
            tempPos = 6
        }else if (x2 > viewRc.width/3f && x2 < viewRc.width/(3f/2f) && y2 < viewRc.height/(5f/3f) && y2 > viewRc.height/(5f/2)) {
            tempPos = 7
        }else if (x2 > viewRc.width/(3f/2f) && x2 < viewRc.width && y2 < viewRc.height/(5f/3f) && y2 > viewRc.height/(5f/2)){
            tempPos = 8
        }else if (x2 > 0f && x2 < viewRc.width / 3f && y2 < viewRc.height/(5f/4f) && y2 > viewRc.height/(5f/3f)){
            tempPos = 9
        }else if (x2 > viewRc.width/3f && x2 < viewRc.width/(3f/2f) && y2 < viewRc.height/(5f/4f) && y2 > viewRc.height/(5f/3)) {
            tempPos = 10
        }else if (x2 > viewRc.width/(3f/2f) && x2 < viewRc.width && y2 < viewRc.height/(5f/4f) && y2 > viewRc.height/(5f/3f)){
            tempPos = 11
        }else if (x2 > 0f && x2 < viewRc.width / 3f && y2 < viewRc.height && y2 > viewRc.height/(5f/4)){
            tempPos = 12
        }else if (x2 > viewRc.width/3f && x2 < viewRc.width/(3f/2f) && y2 < viewRc.height && y2 > viewRc.height/(5f/4)) {
            tempPos = 13
        }else if (x2 > viewRc.width/(3f/2f) && x2 < viewRc.width && y2 < viewRc.height && y2 > viewRc.height/(5f/4)){
            tempPos = 14
        }
        return tempPos
    }

    /*
    fun movingShadowEasy(position: Int, withRc: Int, heightRc: Int, withIm: Int, heightIm: Int): ArrayList<Int> {
        val arrayTemp = ArrayList<Int>()
        when (position) {
            0 -> {
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(heightRc - heightRc)
            }
            1 ->{
                val x: Float = ((withRc/(3f/1f)+((withRc/3f)/2f))-(heightIm/2f)).toFloat()
                val y: Float = (heightRc - heightRc).toFloat()
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            2 ->{
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(heightRc - heightRc)
            }
            3 ->{
                val y: Float = ((heightRc/(5f/1f)+((heightRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add((withRc - withRc))
                arrayTemp.add(y.toInt())
            }
            4 ->{
                val x: Float = ((withRc/(3f/1f)+((withRc/3f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(5f/1f)+((heightRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            5 ->{
                val y: Float = ((heightRc/(5f/1f)+((heightRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            6 ->{
                val y: Float = ((heightRc/(5f/2f)+((heightRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            7 ->{
                val x: Float = ((withRc/(3f/1f)+((withRc/3f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(5f/2f)+((heightRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            8 ->{
                val y: Float = ((heightRc/(5f/2f)+((heightRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            9 ->{
                val y: Float = ((heightRc/(5f/3f)+((heightRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(y.toInt())
            }
            10 ->{
                val x: Float = ((withRc/(3f/1f)+((withRc/3f)/2f))-(heightIm/2f))
                val y: Float = ((heightRc/(5f/3f)+((heightRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(y.toInt())
            }
            11 ->{
                val y: Float = ((heightRc/(5f/3f)+((heightRc/5f)/2f))-(heightIm/2f))
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(y.toInt())
            }
            12 ->{
                arrayTemp.add(withRc - withRc)
                arrayTemp.add(heightRc - withIm)
            }
            13 ->{
                val x: Float = ((withRc/(3f/1f)+((withRc/3f)/2f))-(heightIm/2f))
                arrayTemp.add(x.toInt())
                arrayTemp.add(heightRc - withIm)
            }
            14 -> {
                arrayTemp.add(withRc - withIm)
                arrayTemp.add(heightRc - heightIm)
            }
        }
        return arrayTemp
    }
     */

}