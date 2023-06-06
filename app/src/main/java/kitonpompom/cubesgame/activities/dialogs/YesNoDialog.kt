package kitonpompom.cubesgame.activities.dialogs

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Bitmap
import androidx.constraintlayout.widget.ConstraintLayout
import kitonpompom.cubesgame.activities.utils.Constans
import kitonpompom.cubesgame.databinding.DifficultyOptionDialogBinding
import kitonpompom.cubesgame.databinding.ProgressDialogLayoutAddImageBinding
import kitonpompom.cubesgame.databinding.ProgressDialogLayoutPwpBinding

object YesNoDialog {

    lateinit var view: ConstraintLayout

    fun createYesNoDialog(act: Activity, nameFragment: Int) : AlertDialog{

        val builder = AlertDialog.Builder(act)
        val rootDialogElement = DifficultyOptionDialogBinding.inflate(act.layoutInflater)

        view = rootDialogElement.root
        when(nameFragment){
            Constans.FRAGMENT_ADD_IMAGE -> {
                val rootDialogElement = ProgressDialogLayoutAddImageBinding.inflate(act.layoutInflater)
                view = rootDialogElement.root
            }
            Constans.FRAGMENT_PLAYING_WITH_PICTURE -> {
                val rootDialogElement = ProgressDialogLayoutPwpBinding.inflate(act.layoutInflater)
                view = rootDialogElement.root
            }
        }
        builder.setView(view)
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
        return dialog
    }
}