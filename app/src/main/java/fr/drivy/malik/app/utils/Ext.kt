package fr.drivy.malik.app.utils

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

/**
 * Provide a lambda on [LiveData#observe] method
 */
fun <T> LiveData<T>.observe(owner: LifecycleOwner, onChanged: (value: T?) -> Unit) {
    observe(owner, Observer<T> { onChanged.invoke(it) })
}