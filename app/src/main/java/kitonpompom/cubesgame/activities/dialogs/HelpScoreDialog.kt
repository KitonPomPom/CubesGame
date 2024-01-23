package kitonpompom.cubesgame.activities.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.activities.data.dataArrayBitmap
import kitonpompom.cubesgame.databinding.DialogHelpPwpBinding
import kotlin.collections.ArrayList

class HelpScoreDialog(private val interfaceHelpScoreDialog: InterfaceHelpScoreDialog) {

    lateinit var view: ConstraintLayout

    fun createHelpScoreDialog(act: Activity, arrayBitmap: ArrayList<Bitmap>, arrayBlurBitmap: ArrayList<Bitmap>, score: Int,
                              arrayCollected: ArrayList<Int>, mainArrayView: ArrayList<dataArrayBitmap>, blur: Boolean): AlertDialog {

        val builder = AlertDialog.Builder(act)
        val rootDialogElement = DialogHelpPwpBinding.inflate(act.layoutInflater)
        view = rootDialogElement.root

        var openImage1: Int = 0
        var openImage2: Int = 0
        var openImage3: Int = 0
        var openImage4: Int = 0
        var openImage5: Int = 0
        var openImage6: Int = 0

        //Максимальное кол-во кубиков которое можно повернуть выделеной картинки
        var maxOpenImage: Int = 0
        //Выбраная картинка для передачи в интерфейс
        var selectImage: Int = 1

        val scoreText = view.findViewById<TextView>(R.id.id_tv_score_help)
        val btUp = view.findViewById<ImageButton>(R.id.id_bt_up)
        val btDown = view.findViewById<ImageButton>(R.id.id_bt_down)
        val btCancel = view.findViewById<ImageButton>(R.id.id_bt_cancel_dial_help)
        val btUse = view.findViewById<Button>(R.id.id_bt_use)
        val linear1 = view.findViewById<CardView>(R.id.card_leyout1)
        val linear2 = view.findViewById<CardView>(R.id.card_leyout2)
        val linear3 = view.findViewById<CardView>(R.id.card_leyout3)
        val linear4 = view.findViewById<CardView>(R.id.card_leyout4)
        val linear5 = view.findViewById<CardView>(R.id.card_leyout5)
        val linear6 = view.findViewById<CardView>(R.id.card_leyout6)
        val cardSelectImage1 = view.findViewById<CardView>(R.id.card_select_image1)
        val cardSelectImage2 = view.findViewById<CardView>(R.id.card_select_image2)
        val cardSelectImage3 = view.findViewById<CardView>(R.id.card_select_image3)
        val cardSelectImage4 = view.findViewById<CardView>(R.id.card_select_image4)
        val cardSelectImage5 = view.findViewById<CardView>(R.id.card_select_image5)
        val cardSelectImage6 = view.findViewById<CardView>(R.id.card_select_image6)
        val cardSelectImageArray: Array<CardView> = arrayOf(cardSelectImage1, cardSelectImage2, cardSelectImage3,
            cardSelectImage4, cardSelectImage5, cardSelectImage6)
        var arrayImView = ArrayList<ImageView>()
        arrayImView.add(view.findViewById<ImageView>(R.id.imageV1))
        arrayImView.add(view.findViewById<ImageView>(R.id.imageV2))
        arrayImView.add(view.findViewById<ImageView>(R.id.imageV3))
        arrayImView.add(view.findViewById<ImageView>(R.id.imageV4))
        arrayImView.add(view.findViewById<ImageView>(R.id.imageV5))
        arrayImView.add(view.findViewById<ImageView>(R.id.imageV6))



        if(blur) {

            for(i in 0 until arrayCollected.size - 1){
                if (arrayCollected[i] == 1) arrayImView[i].setImageBitmap(arrayBitmap[i]) else arrayImView[i].setImageBitmap(arrayBlurBitmap[i])
            }
        }

            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //Временно отрисовываем рамки для проверки
        //arrayCollected[0] = 1
        //arrayCollected[1] = 1
        //arrayCollected[2] = 1
        //arrayCollected[3] = 1
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        //Отрисовываем рамки на картинках в зависимости от значения 0 или 1 в массиве arrayCollected
        if (arrayCollected[0] == 1) linear1.setCardBackgroundColor(act.getColor(R.color.green_main)) else linear1.setCardBackgroundColor(act.getColor(R.color.grey))
        if (arrayCollected[1] == 1) linear2.setCardBackgroundColor(act.getColor(R.color.green_main)) else linear2.setCardBackgroundColor(act.getColor(R.color.grey))
        if (arrayCollected[2] == 1) linear3.setCardBackgroundColor(act.getColor(R.color.green_main)) else linear3.setCardBackgroundColor(act.getColor(R.color.grey))
        if (arrayCollected[3] == 1) linear4.setCardBackgroundColor(act.getColor(R.color.green_main)) else linear4.setCardBackgroundColor(act.getColor(R.color.grey))
        if (arrayCollected[4] == 1) linear5.setCardBackgroundColor(act.getColor(R.color.green_main)) else linear5.setCardBackgroundColor(act.getColor(R.color.grey))
        if (arrayCollected[5] == 1) linear6.setCardBackgroundColor(act.getColor(R.color.green_main)) else linear6.setCardBackgroundColor(act.getColor(R.color.grey))

        openImage1 = 0
        openImage2 = 0
        openImage3 = 0
        openImage4 = 0
        openImage5 = 0
        openImage6 = 0
        // записываем количество кубиков повернутых к нам лицом для каждой картинки
        for (i in mainArrayView){
            when (i.arrayNumber[0]) {
                0 -> openImage1++
                1 -> openImage2++
                2 -> openImage3++
                3 -> openImage4++
                4 -> openImage5++
                5 -> openImage6++
            }
        }


        //Высчитываем сколько кубиков нам нужно повернуть и записываем в массив 6 значений
        var openImageArray: ArrayList<Int> = arrayListOf()
        Log.d("MyLog", "Запуск ${mainArrayView.size}")
        openImageArray.clear()
        openImageArray.add(mainArrayView.size - openImage1)
        openImageArray.add(mainArrayView.size - openImage2)
        openImageArray.add(mainArrayView.size - openImage3)
        openImageArray.add(mainArrayView.size - openImage4)
        openImageArray.add(mainArrayView.size - openImage5)
        openImageArray.add(mainArrayView.size - openImage6)

        //Log.d("MyLog", "1:${mainArrayView.size}")

        //Выбираем какая картинка будет выделена автоматически при первом запуске
        // Цикл от 0 до 6
        for (i in 0 until arrayCollected.size - 1){
            //Log.d("MyLog", "Цикл $i")
            //Если значение картинки в массиве = 0, значит картинка ещё не собрана
            if (arrayCollected[i] == 0){
                //Убираем полупрозрачный фон если картинка ещё не собрана
                cardSelectImageArray[i].visibility = View.GONE
                //Записываем в maxOpenImage кол-во кубиков которое можно повернуть
                maxOpenImage = openImageArray[i]
                //Записываем номер картинки кубики которой нужно будет поворачивать
                selectImage = i
                //Если кол-во очков игрока меньше кол-ва кубиков которое нужно повернуть
                //Log.d("MyLog", "score: $score, openImageArray: ${openImageArray[i]}")
                if(score < openImageArray[i]){
                    //в scoreText записываем кол-во очков
                    //Log.d("MyLog", "score: $score")
                    scoreText.text = score.toString()
                }else{
                    //Log.d("MyLog", "openImageArray: ${openImageArray[i]}")
                    //в scoreText записываем кол-во кубиков которое можно повернуть
                    scoreText.text = openImageArray[i].toString()
                }
                break
            }
        }

        cardSelectImage1.setOnClickListener(){
            //Если картинка не собрана то позволить выделить её
            if (arrayCollected[0] == 0){
                cardSelectImage1.visibility = View.GONE
                cardSelectImage2.visibility = View.VISIBLE
                cardSelectImage3.visibility = View.VISIBLE
                cardSelectImage4.visibility = View.VISIBLE
                cardSelectImage5.visibility = View.VISIBLE
                cardSelectImage6.visibility = View.VISIBLE
                maxOpenImage = openImageArray[0]
                selectImage = 0
                if(score < openImageArray[0]){
                    scoreText.text = score.toString()
                }else{
                    scoreText.text = openImageArray[0].toString()
                }
            }
        }

        cardSelectImage2.setOnClickListener(){
            if (arrayCollected[1] == 0){
                cardSelectImage1.visibility = View.VISIBLE
                cardSelectImage2.visibility = View.GONE
                cardSelectImage3.visibility = View.VISIBLE
                cardSelectImage4.visibility = View.VISIBLE
                cardSelectImage5.visibility = View.VISIBLE
                cardSelectImage6.visibility = View.VISIBLE
                maxOpenImage = openImageArray[1]
                selectImage = 1
                if(score < openImageArray[1]){
                    scoreText.text = score.toString()
                }else{
                    scoreText.text = openImageArray[1].toString()
                }
            }
        }

        cardSelectImage3.setOnClickListener(){
            if (arrayCollected[2] == 0){
                cardSelectImage1.visibility = View.VISIBLE
                cardSelectImage2.visibility = View.VISIBLE
                cardSelectImage3.visibility = View.GONE
                cardSelectImage4.visibility = View.VISIBLE
                cardSelectImage5.visibility = View.VISIBLE
                cardSelectImage6.visibility = View.VISIBLE
                maxOpenImage = openImageArray[2]
                selectImage = 2
                if(score < openImageArray[2]){
                    scoreText.text = score.toString()
                }else{
                    scoreText.text = openImageArray[2].toString()
                }
            }
        }

        cardSelectImage4.setOnClickListener(){
            if (arrayCollected[3] == 0){
                cardSelectImage1.visibility = View.VISIBLE
                cardSelectImage2.visibility = View.VISIBLE
                cardSelectImage3.visibility = View.VISIBLE
                cardSelectImage4.visibility = View.GONE
                cardSelectImage5.visibility = View.VISIBLE
                cardSelectImage6.visibility = View.VISIBLE
                maxOpenImage = openImageArray[3]
                selectImage = 3
                if(score < openImageArray[3]){
                    scoreText.text = score.toString()
                }else{
                    scoreText.text = openImageArray[3].toString()
                }
            }
        }

        cardSelectImage5.setOnClickListener(){
            if (arrayCollected[4] == 0){
                cardSelectImage1.visibility = View.VISIBLE
                cardSelectImage2.visibility = View.VISIBLE
                cardSelectImage3.visibility = View.VISIBLE
                cardSelectImage4.visibility = View.VISIBLE
                cardSelectImage5.visibility = View.GONE
                cardSelectImage6.visibility = View.VISIBLE
                maxOpenImage = openImageArray[4]
                selectImage = 4
                if(score < openImageArray[4]){
                    scoreText.text = score.toString()
                }else{
                    scoreText.text = openImageArray[4].toString()
                }
            }
        }

        cardSelectImage6.setOnClickListener(){
            if (arrayCollected[5] == 0){
                cardSelectImage1.visibility = View.VISIBLE
                cardSelectImage2.visibility = View.VISIBLE
                cardSelectImage3.visibility = View.VISIBLE
                cardSelectImage4.visibility = View.VISIBLE
                cardSelectImage5.visibility = View.VISIBLE
                cardSelectImage6.visibility = View.GONE
                maxOpenImage = openImageArray[5]
                selectImage = 5
                if(score < openImageArray[5]){
                    scoreText.text = score.toString()
                }else{
                    scoreText.text = openImageArray[5].toString()
                }
            }
        }

        btUp.setOnClickListener(){
            var scoreTextTemp = scoreText.text.toString().toInt()
            if (scoreTextTemp < maxOpenImage && scoreTextTemp < score){
                scoreTextTemp++
                scoreText.text = scoreTextTemp.toString()
            }
        }

        btDown.setOnClickListener(){
            var scoreTextTemp = scoreText.text.toString().toInt()
            if (scoreTextTemp > 0){
                scoreTextTemp--
                scoreText.text = scoreTextTemp.toString()
            }
        }


        //textVTime.text = "--:--:--"
        //textVScore.text = "16"


        builder.setView(view)
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
        //Log.d("MyLog", "dialog 4 $dialog")

        dialog.setOnDismissListener {
            openImageArray.clear()
        }

        btCancel.setOnClickListener(){
            dialog.dismiss()
        }
        btUse.setOnClickListener() {
            //Если 0 то ничего не делать
            if (scoreText.text.toString().toInt() != 0) {
                var tempScore = scoreText.text.toString().toInt()
                interfaceHelpScoreDialog.interfaceHelpScoreDialog(selectImage, tempScore, 0, 0)
                interfaceHelpScoreDialog.interfaceStopClickAnimationHelp()

                dialog.dismiss()
            }
        }
        return dialog
    }
}