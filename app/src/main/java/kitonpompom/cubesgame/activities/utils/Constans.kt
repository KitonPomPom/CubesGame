package kitonpompom.cubesgame.activities.utils

object Constans {

    //Константа, для проверки игрового поля на старте загрузки адаптера или при перемещении
    //или обновлении кубика, на совпадения между собой и рисования линий между квадратами
    const val START_UPDATE_LINE = 345
    const val NO_START_UPDATE_LINE = 346


    //Константа для заглушка, что бы не блокировать обновление итема для проверки на
    // соответствие линий между кубиками, не передавать обратную позицию в адаптер
    const val NO_POSITION_MOVE = 1000

    //Константа определяющая какой лайоут запускать в диалоге загрузки (ProgressDialog) в зависимости
    //в зависимости от того где мы запускаем этот диалог загрузки
    const val FRAGMENT_ADD_IMAGE = 742
    const val FRAGMENT_PLAYING_WITH_PICTURE = 45
    //Скорость анимации вращения кубика
    const val SPEED_DURATION_ANIMATION_CUBE = 200
    //Выбор сложности
    const val EASY = 566
    const val MEDIUM = 567
    const val HARD = 568
    //Название кнопки откуда запускаем диалог, что бы загрузить нужный View в диалоге
    const val BT_SHUFFLE = 53
    const val BT_BACK = 58
    const val YES = 90
    const val NO = 91
}