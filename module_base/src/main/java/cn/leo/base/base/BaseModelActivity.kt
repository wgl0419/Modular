package cn.leo.base.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.asynclayoutinflater.view.AsyncLayoutInflater
import androidx.lifecycle.lifecycleScope
import cn.leo.base.dialog.LoadingDialog
import cn.leo.frame.network.viewmodel.MViewModel
import cn.leo.frame.network.viewmodel.ModelCreator
import cn.leo.frame.support.main
import cn.leo.frame.ui.ILoading
import cn.leo.frame.utils.ClassUtils
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

/**
 * @author : ling luo
 * @date : 2019-11-27
 */
@Suppress("UNUSED", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate")
abstract class BaseModelActivity<T : MViewModel<*>> : AppCompatActivity(),
    ILoading {

    var initialized = false

    val model by ModelCreator<T>(
        ClassUtils.getSuperClassGenericType(
            this::class.java
        )
    )
    //加载弹窗
    private var mLoadingDialog: LoadingDialog? = null
    var showLoadingJob: Job? = null
    //延时弹loading框，如果在500毫秒内返回结果则不弹出loading框
    val loadingLazyFun: (isShow: Boolean) -> Unit = { isShow ->
        if (isShow) {
            showLoadingJob = main {
                delay(500L)
                showLoading()
            }
        } else {
            dismissLoading()
        }
    }
    //及时弹出loading框
    val loadingFun: (isShow: Boolean) -> Unit = { isShow ->
        if (isShow) {
            showLoading()
        } else {
            dismissLoading()
        }
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutRes = getLayoutRes()
        if (layoutRes != -1) {
            if (isAsyncLayoutInflater()) {
                //异步加载布局，不能在 onResume 和 onStart 里面操作UI
                AsyncLayoutInflater(this).inflate(layoutRes, null)
                { view, _, _ ->
                    setContentView(view)
                    //创建完毕后，在协程进行初始化
                    lifecycleScope.launchWhenCreated { onInitialize() }
                }
            } else {
                setContentView(layoutRes)
                lifecycleScope.launchWhenCreated { onInitialize() }
            }
        } else {
            lifecycleScope.launchWhenCreated { onInitialize() }
        }
    }

    /**
     * 是否异步加载布局
     * 谨慎开启，某些自定义控件在初始化UI时操作UI会导致bug
     */
    open fun isAsyncLayoutInflater() = false

    /**
     * 异步加载布局，不能在 onResume 和 onStart 里面操作UI
     */
    final override fun onResume() {
        super.onResume()
        if (initialized) {
            onResumeSafe()
        }
    }

    /**
     * 异步加载布局，不能在 onResume 和 onStart 里面操作UI
     */
    final override fun onStart() {
        super.onStart()
    }

    /**
     * 安全的onResume
     */
    open fun onResumeSafe() {

    }

    @LayoutRes
    open fun getLayoutRes(): Int = -1

    open fun onInitView() {}

    open fun onInitObserve() {}

    @CallSuper
    open fun onInitialize() {
        ARouter.getInstance().inject(this)
        onInitObserve()
        onInitView()
        onResumeSafe()
        initialized = true
    }

    override fun showLoading(message: CharSequence?) {
        if (mLoadingDialog != null && mLoadingDialog?.isShowing == true) {
            return
        }
        mLoadingDialog = LoadingDialog(this, message)
        mLoadingDialog?.show()
    }

    override fun dismissLoading() {
        showLoadingJob?.cancel()
        if (mLoadingDialog?.isShowing == true) {
            mLoadingDialog?.dismiss()
        }
        mLoadingDialog = null
    }

}