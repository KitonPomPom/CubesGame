package kitonpompom.cubesgame.activities.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import kitonpompom.cubesgame.R
import kitonpompom.cubesgame.activities.utils.Constans
import kitonpompom.cubesgame.databinding.DifficultyOptionDialogBinding
import kitonpompom.cubesgame.databinding.ProgressDialogLayoutAddImageBinding
import kitonpompom.cubesgame.databinding.ProgressDialogLayoutPwpBinding
import kotlin.contracts.contract

class DifficultyOptionDialog(private val interfaceDifficultyOptionDialog: InterfaceDifficultyOptionDialog) {
    //Диалог в котором мы выбираем сложность игры перед её началом
    fun createDifficultyOptionDialog(context: Context, mainArray: ArrayList<Bitmap>) {

        val builder = AlertDialog.Builder(context)
        val rootView = LayoutInflater.from(context).inflate(R.layout.difficulty_option_dialog, null)
        val btEasy = rootView.findViewById<Button>(R.id.id_bt_easy)
        val btMedium = rootView.findViewById<Button>(R.id.id_bt_medium)
        val btHard = rootView.findViewById<Button>(R.id.id_bt_hard)

        builder.setView(rootView)
        builder.setCancelable(true)
        val dialog = builder.create()

        btEasy.setOnClickListener() {
            interfaceDifficultyOptionDialog.interfaceDifficultyOptionDialog(Constans.EASY, mainArray)
            dialog.dismiss()
        }
        btMedium.setOnClickListener() {
            interfaceDifficultyOptionDialog.interfaceDifficultyOptionDialog(Constans.MEDIUM, mainArray)
            dialog.dismiss()
        }
        btHard.setOnClickListener() {
            interfaceDifficultyOptionDialog.interfaceDifficultyOptionDialog(Constans.HARD, mainArray)
            dialog.dismiss()
        }

        dialog.show()
    }

    fun createDifficultyOptionDialogAddImage(context: Context) {

        val builder = AlertDialog.Builder(context)
        val rootView = LayoutInflater.from(context).inflate(R.layout.difficulty_option_dialog, null)
        val btEasy = rootView.findViewById<Button>(R.id.id_bt_easy)
        val btMedium = rootView.findViewById<Button>(R.id.id_bt_medium)
        val btHard = rootView.findViewById<Button>(R.id.id_bt_hard)

        builder.setView(rootView)
        builder.setCancelable(true)
        val dialog = builder.create()

        btEasy.setOnClickListener() {
            interfaceDifficultyOptionDialog.interfaceDifficultyOptionDialogAddImage(Constans.EASY)
            dialog.dismiss()
        }
        btMedium.setOnClickListener() {
            interfaceDifficultyOptionDialog.interfaceDifficultyOptionDialogAddImage(Constans.MEDIUM)
            dialog.dismiss()
        }
        btHard.setOnClickListener() {
            interfaceDifficultyOptionDialog.interfaceDifficultyOptionDialogAddImage(Constans.HARD)
            dialog.dismiss()
        }

        dialog.show()
    }
}