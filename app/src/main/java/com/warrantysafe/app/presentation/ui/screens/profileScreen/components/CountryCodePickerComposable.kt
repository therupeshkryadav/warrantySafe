package com.warrantysafe.app.presentation.ui.screens.profileScreen.components

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.hbb20.CountryCodePicker

@Composable
fun CountryCodePickerComposable(
    defaultCountryCode: Int = 91, // Default country code (e.g., India)
    onCountrySelected: (String) -> Unit
) {
    // Country Code Picker
    AndroidView(
        modifier = Modifier.wrapContentWidth(),
        factory = { context ->
            CountryCodePicker(context).apply {
                // Set default country code using phone code
                setDefaultCountryUsingPhoneCode(defaultCountryCode)
                showFlag(true) // Show country flag
                showArrow(true)
                setShowPhoneCode(true) // Show phone code (+91)
                showNameCode(false) // Hide country name code
                setOnCountryChangeListener {
                    onCountrySelected(selectedCountryCodeWithPlus) // Callback with the selected country code
                }
            }
        }
    )
}
