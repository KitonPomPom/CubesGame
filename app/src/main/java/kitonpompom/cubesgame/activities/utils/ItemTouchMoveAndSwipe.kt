package kitonpompom.cubesgame.activities.utils


import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kitonpompom.cubesgame.activities.AdapterFragPWP

//Класс для перемещения\удаления итемов в рцвью путем смахивания и перетаскивания
class ItemTouchMoveAndSwipe(private val itemTouchInterface: ItemTouchDragAdapterPWP?) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        return makeMovementFlags(dragFlag,0)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        itemTouchInterface?.onMove(viewHolder.adapterPosition,target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        //Log.d("MyLog", "Position: ${viewHolder.position}")
    }

    interface ItemTouchDragAdapterPWP{
        fun onMove(startPos: Int, targetPos: Int)
    }

}