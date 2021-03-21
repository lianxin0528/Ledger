package space.lianxin.comm.ui.activity

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.airbnb.mvrx.MvRxView
import com.airbnb.mvrx.MvRxViewId

/**
 * ===========================================
 * MvRx的Activity的基类。如果存在列表集合应当选择继承
 * [ComMvRxEpoxyActivity]或者是[TitleListActivity]。
 * 其实也不建议继承当前类。可选择继承[TitleActivity]。
 *
 * @author: lianxin
 * @date: 2021/2/25 13:03
 * ===========================================
 */
abstract class ComMvRxAvtivity<T : ViewBinding> : ComActivity<T>(), MvRxView {

    private val mvrxViewIdProperty = MvRxViewId()
    final override val mvrxViewId: String by mvrxViewIdProperty

    override fun onCreate(savedInstanceState: Bundle?) {
        mvrxViewIdProperty.restoreFrom(savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mvrxViewIdProperty.saveTo(outState)
    }

    /**
     * Fragments should override the subscriptionLifecycle owner so that subscriptions made after onCreate
     * are properly disposed as fragments are moved from/to the backstack.
     */
    override val subscriptionLifecycleOwner: LifecycleOwner
        get() = this

    override fun onStart() {
        super.onStart()
        // This ensures that invalidate() is called for static screens that don't
        // subscribe to a ViewModel.
        postInvalidate()
    }

    override fun invalidate() {}
}