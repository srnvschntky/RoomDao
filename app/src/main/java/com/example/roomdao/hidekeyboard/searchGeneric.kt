package com.example.roomdao.hidekeyboard

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
// updates livedata object only once and after it will removes its observer
fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner,observer: Observer<T>){
    observe(lifecycleOwner, object:Observer<T>{
        override fun onChanged(t: T) {
            observer.onChanged(t)
            removeObserver(this)

        }

    })
}