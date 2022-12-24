package kitonpompom.cubesgame.activities.utils


import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

//Кастомный гридлайоут менеджер, создан что бы убрать блокировку перетаскивания по оси Х,
// блокировка из за встроеного скрола.
open class CustomGridLayoutManager(context: Context)  : GridLayoutManager(context,9, LinearLayoutManager.VERTICAL, false) {
    private var isScrollEnabled = true
    fun setScrollEnabled(flag: Boolean) {
        isScrollEnabled = flag
    }

    override fun canScrollHorizontally(): Boolean {
        //Log.d("MyLog", "canScrollHorizontally()")
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically()
    }

    override fun canScrollVertically(): Boolean {
        //Log.d("MyLog", "canScrollVertically")
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically()
    }
}