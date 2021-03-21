package space.lianxin.comm.ui.fragment

import androidx.viewbinding.ViewBinding
import space.lianxin.base.ui.fragment.BaseFragment
import space.lianxin.comm.ui.activity.ComActivity
import space.lianxin.comm.ui.dialog.ActionLoadingDialog
import space.lianxin.comm.ui.dialog.LoadingDialog

/**
 * ===========================================
 * 增加弹窗相关的基类。
 *
 * @author: lianxin
 * @date: 2021/3/21 12:15
 * ===========================================
 */
abstract class ComFragment<T : ViewBinding> : BaseFragment<T>() {

    protected var fragmentHeight = 0

    /** 给布局设置状态栏高度的TopMargin */
    fun setTopStatusBarPadding() {
        binding.root.let {
            it.setPadding(
                it.paddingLeft,
                it.paddingTop + mStatusBarHeight,
                it.paddingRight,
                it.paddingBottom
            )
        }
    }

    protected open fun showLoading(style: (LoadingDialog.() -> Unit)? = null) {
        if (mActivity is ComActivity<*>) {
            (mActivity as ComActivity<*>).showLoading(style)
        }
    }

    protected open fun showActionLoading(
        tips: String? = null,
        style: (ActionLoadingDialog.() -> Unit)? = null
    ) {
        if (mActivity is ComActivity<*>) {
            (mActivity as ComActivity<*>).showActionLoading(tips, style)
        }
    }

    protected open fun dismissLoading() {
        if (mActivity is ComActivity<*>) {
            (mActivity as ComActivity<*>).dismissLoading()
        }
    }

    /** 动态设置Fragment内容的高度 */
    open fun setContentHeight(height: Int) {
    }

}