package com.sanlean.totem.domain.usecase

import com.sanlean.totem.domain.os.DeviceOperatingSystem.ANDROID
import com.sanlean.totem.domain.os.DeviceOperatingSystem.DESKTOP
import com.sanlean.totem.domain.os.DeviceWrapper

class KeyboardTypeUseCase(private val deviceWrapper: DeviceWrapper) {

    fun shouldUseComposeKeyboard(): Boolean {
        val isAndroidTablet = deviceWrapper.isTablet() && deviceWrapper.operatingSystem() == ANDROID
        val isDesktop = deviceWrapper.operatingSystem() == DESKTOP
        return isAndroidTablet || isDesktop
    }
}
