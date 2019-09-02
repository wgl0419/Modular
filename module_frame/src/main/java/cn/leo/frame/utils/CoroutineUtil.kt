package cn.leo.frame.utils

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.*

/**
 * @author : ling luo
 * @date : 2019-06-10
 */
object CoroutineUtil {

    /**
     * io协程，运行在io线程
     */
    fun io(block: suspend CoroutineScope.() -> Unit): Job {
        return GlobalScope.launch(Dispatchers.IO) {
            block()
        }
    }

    /**
     * 可以绑定生命周期的io协程
     */
    fun io(lifecycleOwner: LifecycleOwner, block: suspend CoroutineScope.() -> Unit): Job {
        val supervisorJob = SupervisorJob()
        CoroutineScope(Dispatchers.IO + supervisorJob).launch {
            block()
        }
        lifecycleOwner.lifecycle.addObserver(object :LifecycleObserver{
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy(){
                supervisorJob.cancel()
                lifecycleOwner.lifecycle.removeObserver(this)
            }
        })
        return supervisorJob
    }

    /**
     * 运行在主线程的协程
     */
    fun main(block: suspend CoroutineScope.() -> Unit): Job {
        return GlobalScope.launch(Dispatchers.Main) {
            block()
        }
    }

    /**
     * 可以绑定生命周期的main协程
     */
    fun main(lifecycleOwner: LifecycleOwner, block: suspend CoroutineScope.() -> Unit): Job {
        val supervisorJob = SupervisorJob()
        CoroutineScope(Dispatchers.Main + supervisorJob).launch {
            block()
        }
        lifecycleOwner.lifecycle.addObserver(object :LifecycleObserver{
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy(){
                supervisorJob.cancel()
                lifecycleOwner.lifecycle.removeObserver(this)
            }
        })
        return supervisorJob
    }

    /**
     * 运行在io线程的有返回值的协程
     */
    fun <T> wait(block: suspend CoroutineScope.() -> T): Deferred<T> {
        return GlobalScope.async(Dispatchers.IO) {
            block()
        }
    }


}

