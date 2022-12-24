package kitonpompom.cubesgame.activities.utils

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.util.LongSparseArray
import android.view.MotionEvent
import android.view.translation.ViewTranslationResponse
import androidx.recyclerview.widget.RecyclerView

//Кастомный рцВью (не пригодился, остается как пример)
class CustomRecycleView(context: Context, attr: AttributeSet) : RecyclerView(context, attr) {
    override fun onTouchEvent(e: MotionEvent?): Boolean {
        //Log.d("MyLog", "onTouchEvent")
        return super.onTouchEvent(e)
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        //Log.d("MyLog", "onInterceptTouchEvent")
        return super.onInterceptTouchEvent(e)
    }
}