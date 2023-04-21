package com.zhangteng.mvvm.utils

import androidx.lifecycle.ViewModel
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * 获取当前类绑定的泛型ViewModel-clazz
 */
@Suppress("UNCHECKED_CAST")
fun <VM> getVmClazz(obj: Any): VM {
    var type: VM? = null
    (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.forEach {
        if (isVm(it)) {
            type = it as VM
            return@forEach
        }
    }
    return type!!
}

fun isVm(type: Type?): Boolean {
    if (type is Class<*>?) {
        return when (type) {
            ViewModel::class.java -> {
                true
            }

            null -> {
                false
            }

            else -> {
                isVm(type.superclass)
            }
        }
    }
    return false
}