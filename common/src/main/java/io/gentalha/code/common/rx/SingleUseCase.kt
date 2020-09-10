package io.gentalha.code.common.rx

import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<in Params, T> constructor(
    private val postExecutionThread: PostExecutionThread,
    private val observerSchedulerThread: Scheduler = Schedulers.io()
) {

    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseSingle(params: Params? = null): Single<T>

    open fun execute(
        params: Params? = null,
        onSuccess: (T) -> Unit,
        onError: (e: Throwable) -> Unit,
        onCompletable: (() -> Unit)? = null
    ) {
        val single = this.buildUseCaseSingle(params)
            .subscribeOn(observerSchedulerThread)
            .observeOn(postExecutionThread.scheduler)

        addDisposable(single
            .subscribe(
                { t: T ->
                    onSuccess.invoke(t)
                },
                { error ->
                    onError.invoke(error)
                }
            ))

    }

    private fun addDisposable(disposable: Disposable) = disposables.add(disposable)

    fun dispose() = disposables.dispose()
}