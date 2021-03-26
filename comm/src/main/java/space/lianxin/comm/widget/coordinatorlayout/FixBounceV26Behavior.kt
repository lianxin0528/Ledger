package space.lianxin.comm.widget.coordinatorlayout

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.OverScroller
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout

/**
 * description:
 * @date: 2018-10-09 10:19
 * @author: Grieey
 */
class FixBounceV26Behavior @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null
) : AppBarLayout.Behavior(context, attr) {

    private var mScroller1: OverScroller? = null

    init {
        bindScrollerValue(context)
        setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
            override fun canDrag(p0: AppBarLayout): Boolean {
                return false
            }
        })
    }

    /**
     * 反射注入Scroller以获取其引用
     *
     * @param context
     */
    private fun bindScrollerValue(context: Context) {
        if (mScroller1 != null) return
        mScroller1 = OverScroller(context)
        try {
            val clzHeaderBehavior = javaClass.superclass?.superclass
            val fieldScroller = clzHeaderBehavior?.getDeclaredField("mScroller")
            fieldScroller?.isAccessible = true
            fieldScroller?.set(this, mScroller1)
        } catch (e: Exception) {
        }
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        if (type == ViewCompat.TYPE_NON_TOUCH) {
            // fling上滑appbar然后迅速fling下滑list时, HeaderBehavior的mScroller并未停止, 会导致上下来回晃动
            mScroller1?.let {
                if (it.computeScrollOffset()) {
                    it.abortAnimation()
                }
            }
            // 当target滚动到边界时主动停止target fling,与下一次滑动产生冲突
            if (topAndBottomOffset == 0) {
                ViewCompat.stopNestedScroll(target, type)
            }
        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }
}