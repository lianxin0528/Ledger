package space.lianxin.comm.widget.coordinatorlayout

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.blankj.utilcode.util.BarUtils
import com.google.android.material.appbar.AppBarLayout
import space.lianxin.base.extention.gone
import space.lianxin.comm.R
import space.lianxin.comm.databinding.CommViewTitleCoordinatorlayoutBinding
import space.lianxin.comm.extention.dp

/**
 * ===========================================
 * 标题栏停留的滑动嵌套布局.
 *
 * @author: lianxin
 * @date: 2021/3/24 20:28
 * ===========================================
 */
class TitleCoordinatorLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    def: Int = 0
) : CoordinatorLayout(context, attrs, def) {

    private val binding: CommViewTitleCoordinatorlayoutBinding // 布局文件

    lateinit var appBarLayout: AppBarLayout
    lateinit var toolbar: Toolbar
    var headerView: View? = null
    var fixedView: View? = null

    @LayoutRes
    private var headerLayout: Int = View.NO_ID
    private var fixedLayout: Int = View.NO_ID
    private var toolbarHeight: Float = 0f
    private var headerHeight: Float = 0f
    private var toolBarIncludeStatusBar: Boolean = true

    init {
        // 加载布局
        val root = LayoutInflater.from(context)
            .inflate(R.layout.comm_view_title_coordinatorlayout, this, true)
        binding = CommViewTitleCoordinatorlayoutBinding.bind(root)

        // 找到属性值
        context.obtainStyledAttributes(attrs, R.styleable.TitleCoordinatorLayout).apply {
            headerLayout = getResourceId(R.styleable.TitleCoordinatorLayout_tcl_header, View.NO_ID)
            fixedLayout = getResourceId(R.styleable.TitleCoordinatorLayout_tcl_fixed, View.NO_ID)
            toolbarHeight =
                getDimension(R.styleable.TitleCoordinatorLayout_tcl_toolbarHeight, 0f.dp())
            headerHeight =
                getDimension(R.styleable.TitleCoordinatorLayout_tcl_headerHeight, 0f.dp())
            toolBarIncludeStatusBar =
                getBoolean(R.styleable.TitleCoordinatorLayout_tcl_toolBarIncludeStatusBar, true)
        }.recycle()

        if (!isInEditMode) { // 编辑模式
            initView()
        }

        if (headerLayout != View.NO_ID) { // 加载头部布局
            headerView =
                LayoutInflater.from(context).inflate(headerLayout, binding.titleClCtl, false)
            val params = binding.titleClHeadVs.layoutParams
            val index = binding.titleClCtl.indexOfChild(binding.titleClHeadVs)
            binding.titleClCtl.removeViewAt(index)
            if (headerHeight != 0.0f) { // 设置了固定header的高度的
                params.height = headerHeight.toInt()
            }
            binding.titleClCtl.addView(headerView, index, params)
        }

        if (fixedLayout != View.NO_ID) {
            fixedView = LayoutInflater.from(context).inflate(fixedLayout, null, false)
            fixedView?.gone()
            val params = binding.titleClFixedVs.layoutParams
            val index = binding.titleClAbl.indexOfChild(binding.titleClFixedVs)
            binding.titleClAbl.removeViewAt(index)
            binding.titleClAbl.addView(fixedView, index, params)
        }
        binding.titleClCtl.setContentScrimColor(Color.TRANSPARENT)
    }

    private fun initView() {
        appBarLayout = binding.titleClAbl
        toolbar = binding.titleClTb
        toolbar.layoutParams.height = if (toolBarIncludeStatusBar) {
            (toolbarHeight + BarUtils.getStatusBarHeight()).toInt()
        } else {
            toolbarHeight.toInt()
        }
    }

    /** 获取头部的高度 */
    fun getHeadHeight(): Int {
        return headerView?.height ?: 0
    }

}