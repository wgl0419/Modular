package cn.leo.frame.network.http

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.awaitAll

/**
 * @author : ling luo
 * @date : 2019-12-18
 */

/**
 * 协程延迟任务
 */
@Suppress("UNUSED", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate")
@Throws(Exception::class)
suspend fun <R : Any> MJob<R>.await(): R? {
    (this.job as? Deferred<R>)?.let {
        return it.await()
    }
    return null
}

/**
 * 多任务同时await
 */
@Suppress("UNUSED", "UNCHECKED_CAST", "MemberVisibilityCanBePrivate")
suspend fun <T> Collection<MJob<T>>.awaitAll(): List<T> =
    map { it.job as Deferred<T> }.awaitAll()