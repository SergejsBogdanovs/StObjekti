package lv.st.sbogdano.stobjekti.search.provider

import android.content.SearchRecentSuggestionsProvider

class StObjectsSuggestionProvider : SearchRecentSuggestionsProvider() {

    init {
        setupSuggestions(AUTHORITY, MODE)
    }

    companion object {
        const val AUTHORITY = "lv.st.sbogdano.stobjekti.search.provider.StObjectsSuggestionProvider"
        const val MODE: Int = SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES
    }
}