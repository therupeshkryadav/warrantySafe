package com.warrantysafe.app.presentation.search.components.searchList.components.recentSearches

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.warrantysafe.app.presentation.search.components.searchList.components.recentSearches.components.RecentItem

data class Recent(
    val recentItem: String
)

@Composable
fun RecentSearches(recentSearches: List<Recent>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(recentSearches) { recentSearch ->
            RecentItem(recent = recentSearch.recentItem)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRecent() {
    val recentList = listOf(
        Recent("recentSearch1"),
        Recent("recentSearch2"),
        Recent("recentSearch3"),
        Recent("recentSearch4")
    )
    RecentSearches(recentList)

}