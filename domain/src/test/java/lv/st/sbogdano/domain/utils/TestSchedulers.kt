package lv.st.sbogdano.domain.utils

import io.reactivex.Scheduler
import lv.st.sbogdano.domain.Schedulers

class TestSchedulers : Schedulers {

    override val subscribeOn: Scheduler
        get() = io.reactivex.schedulers.Schedulers.trampoline()

    override val observeOn: Scheduler
        get() = io.reactivex.schedulers.Schedulers.trampoline()
}