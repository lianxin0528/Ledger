package space.lianxin.comm.constants.arouter

import android.app.Activity
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import space.lianxin.comm.constants.arouter.RouterStart.navigation

/**
 * ===========================================
 * 如果跳转到的一个页面需要的参数比较多。为了防止写错，应当在
 * 当前类中声明start方法。如不需要参数或者参数较少，可以使用
 * [navigation]来直接跳转，而不需要再当前类中声明。
 *
 *
 * @author: lianxin
 * @date: 2021/3/7 17:20
 * ===========================================
 */
object RouterStart {

    /**
     * 跳转到下一个界面。如果不需要带回调的方式启动。就不需要传[activity]。
     * 只传[path]就行。如果需要带回调启动就传递[activity]并且重写onActivityResult方法。
     *
     * @param path 路由地址
     * @param activity 需要和[requestCode]一起传递。
     * @param requestCode 请求code。
     */
    fun navigation(path: String, activity: Activity? = null, requestCode: Int = 0) {
        ARouter.getInstance().build(path).run {
            if (activity != null) {
                navigation(activity, requestCode)
            } else {
                navigation()
            }

        }
    }

    /**
     * 获取一个Fragment实例
     * @param path 路由地址
     */
    fun getFragment(path: String): Fragment {
        return ARouter.getInstance()
            .build(path)
            .navigation() as Fragment
    }

    /**
     * 获取一个Activity或者Fragment的Postcard对象
     * 需要传值时使用。
     * @param path 路由地址
     */
    fun newIntent(path: String): Postcard {
        return ARouter.getInstance().build(path)
    }

    object App {

    }


}