package com.argotedev.triviaapp.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi

class VibrationManager(private val context: Context) {

    private val vibrator: Vibrator? = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?

    @RequiresApi(Build.VERSION_CODES.O)
    fun vibrate(duration: Long) {
        if (isVibratorSupported()) {
            val vibrationEffect = createVibrationEffect(duration)
            vibrator?.vibrate(vibrationEffect)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun vibratePattern(pattern: LongArray, repeat: Int = -1) {
        if (isVibratorSupported()) {
            val vibrationEffect = createVibrationEffectPattern(pattern, repeat)
            vibrator?.vibrate(vibrationEffect)
        }
    }

    fun cancelVibration() {
        vibrator?.cancel()
    }

    private fun isVibratorSupported(): Boolean {
        return vibrator?.hasVibrator() == true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createVibrationEffect(duration: Long): VibrationEffect {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE)
        } else {
            VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createVibrationEffectPattern(pattern: LongArray, repeat: Int): VibrationEffect {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            VibrationEffect.createWaveform(pattern, repeat)
        } else {
            VibrationEffect.createWaveform(pattern, repeat)
        }
    }
}
