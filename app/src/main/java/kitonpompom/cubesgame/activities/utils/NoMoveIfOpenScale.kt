package kitonpompom.cubesgame.activities.utils

//Класс для блокировки в адаптере возможности срабатывания ОнТач, блокируется из фрагмета с рцвью когда картинка увеличена
data class NoMoveIfOpenScale(var noMoveIfOpenScale: Boolean = true) {
}