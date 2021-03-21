package space.lianxin.comm.ui.fragment

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.airbnb.mvrx.MvRxView
import com.airbnb.mvrx.MvRxViewId

/**
 * ===========================================
 * 使用MvRx的基类。
 *
 * @author: lianxin
 * @date: 2021/3/21 13:18
 * ===========================================
 */
abstract class ComMvRxFragment<T : ViewBinding> : ComFragment<T>(), MvRxView {

    private val mvrxViewIdProperty = MvRxViewId()
    final override val mvrxViewId: String by mvrxViewIdProperty

    override fun onCreate(savedInstanceState: Bundle?) {
        mvrxViewIdProperty.restoreFrom(savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    /**
     * Fragments should override the subscriptionLifecycle owner so that subscriptions made after onCreate
     * are properly disposed as fragments are moved from/to the backstack.
     */
    override val subscriptionLifecycleOwner: LifecycleOwner
        get() = this.viewLifecycleOwnerLiveData.value ?: this

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mvrxViewIdProperty.saveTo(outState)
    }

    override fun onStart() {
        super.onStart()
        // This ensures that invalidate() is called for static screens that don't
        // subscribe to a ViewModel.
        postInvalidate()
    }

}