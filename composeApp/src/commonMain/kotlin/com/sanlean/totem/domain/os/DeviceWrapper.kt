package com.sanlean.totem.domain.os

expect object DeviceWrapper {
    fun isTablet(): Boolean
    fun operatingSystem(): DeviceOperatingSystem
}
