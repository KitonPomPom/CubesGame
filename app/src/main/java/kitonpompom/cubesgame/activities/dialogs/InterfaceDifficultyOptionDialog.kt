package kitonpompom.cubesgame.activities.dialogs

import android.graphics.Bitmap

interface InterfaceDifficultyOptionDialog {
    fun interfaceDifficultyOptionDialog(optionDifficulty: Int, mainArray: ArrayList<Bitmap>)
    fun interfaceDifficultyOptionDialogAddImage(optionDifficulty: Int)
}