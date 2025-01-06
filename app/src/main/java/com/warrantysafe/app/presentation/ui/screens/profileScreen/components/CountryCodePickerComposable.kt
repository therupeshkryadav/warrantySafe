package com.warrantysafe.app.presentation.ui.screens.profileScreen.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.hbb20.CountryCodePicker
import com.warrantysafe.app.R

@Composable
fun CountryCodePickerComposable(
    modifier: Modifier = Modifier,
    onCountrySelected: (String) -> Unit
) {
    val defaultCountryCode = 91
    AndroidView(
        modifier = modifier,
        factory = { context ->
            CountryCodePicker(context).apply {
                // Set default country
                setDefaultCountryUsingPhoneCode(defaultCountryCode)
                setArrowColor(R.color.black)
                // Ensure the flag is displayed with the country code (e.g., +91)
                showFlag(true)
                setShowPhoneCode(true)
                showNameCode(false) // Hide country name code if not needed
                setOnCountryChangeListener {
                    onCountrySelected(selectedCountryCodeWithPlus) // Return country code with "+"
                }
            }
        }
    )
}
