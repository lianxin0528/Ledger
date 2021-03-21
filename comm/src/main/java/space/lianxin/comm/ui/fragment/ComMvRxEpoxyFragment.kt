package space.lianxin.comm.ui.fragment

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.airbnb.mvrx.BaseMvRxViewModel
import space.lianxin.base.ui.controller.MvRxEpoxyController

/**
 * ===========================================
 * 同时使用Epoxy和MvRx的Fragment基类。
 *
 * @author: lianxin
 * @date: 2021/3/21 13:22
 * ===========================================
 */
abstract class ComMvRxEpoxyFragment<T : ViewBinding> : ComMvRxFragment<T>() {

    protected val epoxyController by lazy { buildEpoxyController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        epoxyController.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        epoxyController.onSaveInstanceState(outState)
    }

    override fun invalidate() {
        epoxyController.requestModelBuild()
    }

    override fun onDestroyView() {
        epoxyController.cancelPendingModelBuild()
        super.onDestroyView()
    }

    protected fun subscribeVM(vararg viewModels: BaseMvRxViewModel<*>) {
        viewModels.forEach {
            it.subscribe(owner = this, subscriber = {
                postInvalidate()
            })
        }
    }

    abstract fun buildEpoxyController(): MvRxEpoxyController
}