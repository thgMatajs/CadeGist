package io.gentalha.code.common.rx

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class CompletableUseCase<in Params> constructor(
    private val postExecutionThread: PostExecutionThread,
    private val observerSchedulerThread: Scheduler = Schedulers.io()
) {

    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseCompletable(params: Params? = null): Completable

    open fun execute(
        params: Params?,
        onSuccess: () -> Unit,
        onError: (e: Throwable) -> Unit,
        onCompletable: (() -> Unit)? = null
    ) {
        val completable = this.buildUseCaseCompletable(params)
            .subscribeOn(observerSchedulerThread)
            .observeOn(postExecutionThread.scheduler)

        addDisposable(completable
            .subscribe(
                {
                    onSuccess.invoke()
                },
                { error ->
                    onError.invoke(error)
                }
            ))

    }

    private fun addDisposable(disposable: Disposable) = disposables.add(disposable)

    fun dispose() = disposables.dispose()
}