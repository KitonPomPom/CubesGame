package kitonpompom.cubesgame.activities.dialogs

interface InterfaceHelpScoreDialog {
    //Интерфейс который запускается из диалога подсказки
    //selectImage - Номер картинки кубики которой выбрали повернуть
    //openImage - Кол-во кубиков которое нужно повернуть
    fun interfaceHelpScoreDialog(selectImage: Int, openImage:Int, posRotationCube: Int)
}