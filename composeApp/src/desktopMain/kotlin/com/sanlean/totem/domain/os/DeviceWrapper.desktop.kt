package com.sanlean.totem.domain.os

actual object DeviceWrapper {
    actual fun isTablet(): Boolean {
        return true
    }

    actual fun operatingSystem(): DeviceOperatingSystem {
        return DeviceOperatingSystem.DESKTOP
    }
}
