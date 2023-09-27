package kitonpompom.cubesgame.activities.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.activities.utils.Constans
import kitonpompom.cubesgame.databinding.DialogYesNoBinding

class YesNoDialog(private val interfaceYesNoDialog: InterfaceYesNoDialog) {

    lateinit var view: ConstraintLayout

    fun createYesNoDialog(act: Activity, nameButton: Int): AlertDialog {

        val builder = AlertDialog.Builder(act)
        val rootDialogElement = DialogYesNoBinding.inflate(act.layoutInflater)
        view = rootDialogElement.root
        val textV = view.findViewById<TextView>(R.id.tv_text_dailog_yes_no)
        val btYes = view.findViewById<Button>(R.id.bt_yes)
        val btNo = view.findViewById<Button>(R.id.bt_no)
        when (nameButton) {
            Constans.BT_SHUFFLE -> {
                //val rootDialogElement = ProgressDialogLayoutAddImageBinding.inflate(act.layoutInflater)
                //view = rootDialogElement.root
                textV.text = "Перемешать все кубики?"
            }

            Constans.BT_BACK -> {
                //val rootDialogElement = ProgressDialogLayoutPwpBinding.inflate(act.layoutInflater)
                //view = rootDialogElement.root
                textV.text = "Вы точно хотите выйти?"
            }

            Constans.BT_ADS -> {
                //val rootDialogElement = ProgressDialogLayoutPwpBinding.inflate(act.layoutInflater)
                //view = rootDialogElement.root
                textV.text = "Посмотреть рекламу?"
            }
        }

        builder.setView(view)
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()

        btYes.setOnClickListener() {
            interfaceYesNoDialog.interfaceYesNoDialog(Constans.YES, nameButton )
            dialog.dismiss()
        }
        btNo.setOnClickListener() {
            //interfaceYesNoDialog.interfaceYesNoDialog(Constans.NO, nameButton)
            dialog.dismiss()
        }

        return dialog
    }
}