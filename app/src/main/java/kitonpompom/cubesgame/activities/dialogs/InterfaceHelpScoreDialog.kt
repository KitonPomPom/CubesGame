package kitonpompom.cubesgame.activities.dialogs

interface InterfaceHelpScoreDialog {

    //Интерфейс который запускается из диалога подсказки
    //selectImage - Номер картинки кубики которой выбрали повернуть
    //openImage - Кол-во кубиков которое нужно повернуть
    //countCubeRotated - Количество Кубиков которое повернули
    fun interfaceHelpScoreDialog(selectImage: Int, openImage:Int, posRotationCube: Int, countCubeRotated: Int)

    //интерфейс который вызывается из диалога помощи поворота кубиков
    //для блокировки нажатия на фрагменте пока идет анимация вращения кубиков
    fun interfaceStopClickAnimationHelp()
    fun interfaceNoStopClickAnimationHelp()
}