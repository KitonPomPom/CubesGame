package kitonpompom.cubesgame.activities.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Bitmap
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.activities.utils.Constans
import kitonpompom.cubesgame.databinding.DialogYesNoBinding
import kitonpompom.cubesgame.databinding.DifficultyOptionDialogBinding
import kitonpompom.cubesgame.databinding.FinishAndPrizeGameDialogBinding
import kitonpompom.cubesgame.databinding.ProgressDialogLayoutAddImageBinding
import kitonpompom.cubesgame.databinding.ProgressDialogLayoutPwpBinding

class FinishCongratulationDialog(private val interfaceFinishCongratulationDialog: InterfaceFinishCongratulationDialog) {

    lateinit var view: ConstraintLayout

    fun createFinishCongratulationDialog(act: Activity): AlertDialog {

        val builder = AlertDialog.Builder(act)
        val rootDialogElement = FinishAndPrizeGameDialogBinding.inflate(act.layoutInflater)
        view = rootDialogElement.root
        val textVScore = view.findViewById<TextView>(R.id.id_score_congr_dialog)
        val textVTime = view.findViewById<TextView>(R.id.id_tv_time_congr_dialog)
        val btNext = view.findViewById<Button>(R.id.id_bt_next_congr_dialog)

        textVTime.text = "--:--:--"
        textVScore.text = "16"


        builder.setView(view)
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
        Log.d("MyLog", "dialog 4 $dialog")
        btNext.setOnClickListener() {
            interfaceFinishCongratulationDialog.interfaceFinishCongratulationDialog()
            dialog.dismiss()
        }
        return dialog
    }
}