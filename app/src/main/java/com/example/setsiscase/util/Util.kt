package com.example.setsiscase.util

import android.view.View




fun View.enable(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}
