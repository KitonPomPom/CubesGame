package kitonpompom.cubesgame.activities.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Bitmap
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.databinding.DialogHelpPwpBinding
import java.util.ArrayList

class HelpScoreDialog(private val interfaceFinishCongratulationDialog: InterfaceFinishCongratulationDialog) {

    lateinit var view: ConstraintLayout

    fun createHelpScoreDialog(act: Activity, image1: Bitmap, image2: Bitmap, image3: Bitmap,
                              image4: Bitmap,image5: Bitmap, image6: Bitmap, score: Int,
                              arrayCollected: ArrayList<Int>): AlertDialog {

        val builder = AlertDialog.Builder(act)
        val rootDialogElement = DialogHelpPwpBinding.inflate(act.layoutInflater)
        view = rootDialogElement.root
        val score = view.findViewById<TextView>(R.id.id_tv_score_help)
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
        val imView1 = view.findViewById<ImageView>(R.id.imageV1)
        val imView2 = view.findViewById<ImageView>(R.id.imageV2)
        val imView3 = view.findViewById<ImageView>(R.id.imageV3)
        val imView4 = view.findViewById<ImageView>(R.id.imageV4)
        val imView5 = view.findViewById<ImageView>(R.id.imageV5)
        val imView6 = view.findViewById<ImageView>(R.id.imageV6)

        imView1.setImageBitmap(image1)
        imView2.setImageBitmap(image2)
        imView3.setImageBitmap(image3)
        imView4.setImageBitmap(image4)
        imView5.setImageBitmap(image5)
        imView6.setImageBitmap(image6)

        if (arrayCollected[0] == 1) linear1.setCardBackgroundColor(act.getColor(R.color.green_main)) else linear1.setCardBackgroundColor(act.getColor(R.color.grey))
        if (arrayCollected[1] == 1) linear2.setCardBackgroundColor(act.getColor(R.color.green_main)) else linear2.setCardBackgroundColor(act.getColor(R.color.grey))
        if (arrayCollected[2] == 1) linear3.setCardBackgroundColor(act.getColor(R.color.green_main)) else linear3.setCardBackgroundColor(act.getColor(R.color.grey))
        if (arrayCollected[3] == 1) linear4.setCardBackgroundColor(act.getColor(R.color.green_main)) else linear4.setCardBackgroundColor(act.getColor(R.color.grey))
        if (arrayCollected[4] == 1) linear5.setCardBackgroundColor(act.getColor(R.color.green_main)) else linear5.setCardBackgroundColor(act.getColor(R.color.grey))
        if (arrayCollected[5] == 1) linear6.setCardBackgroundColor(act.getColor(R.color.green_main)) else linear6.setCardBackgroundColor(act.getColor(R.color.grey))

        //textVTime.text = "--:--:--"
        //textVScore.text = "16"


        builder.setView(view)
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
        Log.d("MyLog", "dialog 4 $dialog")
        btUse.setOnClickListener() {
            //interfaceFinishCongratulationDialog.interfaceFinishCongratulationDialog()
            dialog.dismiss()
        }
        return dialog
    }
}