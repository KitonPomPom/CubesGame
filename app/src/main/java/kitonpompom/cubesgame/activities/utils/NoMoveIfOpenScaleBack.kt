package kitonpompom.cubesgame.activities.utils

//Класс для пблокировки в адаптере возможности срабатывания ОнТач при обратной анимации, блокируется из фрагмета с рцвью
data class NoMoveIfOpenScaleBack(var noMoveIfOpenScale: Boolean = true) {
}