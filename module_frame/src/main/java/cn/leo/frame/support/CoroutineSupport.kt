package cn.leo.frame.support

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.*

/**
 * @author : ling luo
 * @date : 2019-06-10
 */


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
fun LifecycleOwner.io(block: suspend CoroutineScope.() -> Unit): Job {
    val supervisorJob = SupervisorJob()
    CoroutineScope(Dispatchers.IO + supervisorJob).launch {
        block()
    }
    lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            supervisorJob.cancel()
            lifecycle.removeObserver(this)
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
fun LifecycleOwner.main(block: suspend CoroutineScope.() -> Unit): Job {
    val supervisorJob = SupervisorJob()
    CoroutineScope(Dispatchers.Main + supervisorJob).launch {
        block()
    }
    lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            supervisorJob.cancel()
            lifecycle.removeObserver(this)
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




