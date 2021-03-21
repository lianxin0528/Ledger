package space.lianxin.comm.ui.activity

import androidx.viewbinding.ViewBinding
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.mvrx.BaseMvRxViewModel

/**
 * ===========================================
 * 同时使用MvRx和Epoxy的Activty基类。
 *
 * @author: lianxin
 * @date: 2021/3/21 14:43
 * ===========================================
 */
abstract class ComMvRxEpoxyActivity<T : ViewBinding> : ComMvRxAvtivity<T>() {

    protected val epoxyController by lazy { buildEpoxyController() }

    override fun onStart() {
        super.onStart()
        postInvalidate()
    }

    protected fun subscribeVM(vararg viewModels: BaseMvRxViewModel<*>) {
        viewModels.forEach {
            it.subscribe(owner = this, subscriber = {
                postInvalidate()
            })
        }
    }

    override fun invalidate() {
        epoxyController.requestModelBuild()
    }

    abstract fun buildEpoxyController(): AsyncEpoxyController
}