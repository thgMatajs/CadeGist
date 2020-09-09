package io.gentalha.code.common.rx

import io.reactivex.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}