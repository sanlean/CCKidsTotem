package com.sanlean.totem.domain.os

import com.sanlean.totem.Application
import com.sanlean.totem.domain.constants.SIX_HUNDRED

actual object DeviceWrapper {
    actual fun isTablet(): Boolean {
        val metrics = Application.getContext().resources.displayMetrics
        val widthDp = metrics.widthPixels / metrics.density
        return widthDp >= SIX_HUNDRED
    }

    actual fun operatingSystem(): DeviceOperatingSystem {
        return DeviceOperatingSystem.ANDROID
    }
}
