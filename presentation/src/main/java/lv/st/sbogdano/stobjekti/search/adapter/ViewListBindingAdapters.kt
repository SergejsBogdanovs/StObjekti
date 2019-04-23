package lv.st.sbogdano.stobjekti.search.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lv.st.sbogdano.domain.model.StObject

object ViewListBindingAdapters {

    @JvmStatic
    @BindingAdapter("stobjectAdapter", "stobjectCallbacks", requireAll = false)
    fun setStObjectAdapter(recyclerView: RecyclerView, items: List<StObject>?, callbacks: StObjectListAdapter.Callbacks) {
        items?.let {
            recyclerView.apply {
                setHasFixedSize(true)
                adapter = StObjectListAdapter(it, callbacks)
                layoutManager = LinearLayoutManager(context)
            }
        }
    }
}