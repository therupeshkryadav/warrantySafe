package com.warrantysafe.app.presentation.ui.screens.main.profileScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.hbb20.CountryCodePicker

@Composable
fun CountryCodePickerComposable(
    defaultCountryCode: Int = 91, // Default to India
    onCountrySelected: (String) -> Unit,
    enable: Boolean = true
) {
    Box(modifier = Modifier.wrapContentWidth()) {
        AndroidView(
            modifier = Modifier.wrapContentWidth(),
            factory = { context ->
                CountryCodePicker(context).apply {
                    setCountryForPhoneCode(defaultCountryCode)
                    showFlag(true)
                    showArrow(enable) // Arrow visible only when enabled
                    setShowPhoneCode(true)
                    showNameCode(false)
                    if (enable) {
                        setOnCountryChangeListener {
                            onCountrySelected(selectedCountryCodeWithPlus)
                        }
                    }
                }
            }
        )
        // Overlay to intercept touch events if disabled
        if (!enable) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Transparent)
                    .pointerInput(Unit) {
                        awaitPointerEventScope {
                            while (true) {
                                // This loop consumes all pointer events
                                awaitPointerEvent()
                            }
                        }
                    }
            )
        }
    }
}

