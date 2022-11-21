package kitonpompom.cubesgame.activities.utils


import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

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