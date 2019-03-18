package lv.st.sbogdano.stobjekti.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import lv.st.sbogdano.domain.model.StObject
import lv.st.sbogdano.stobjekti.R
import lv.st.sbogdano.stobjekti.databinding.StobjectListItemBinding

class StObjectListAdapter(
        private val items: List<StObject>,
        private val callback: Callbacks?
) : RecyclerView.Adapter<StObjectListAdapter.ViewHolder>() {

    interface Callbacks {
        fun onItemClick(view: View, item: StObject)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: StobjectListItemBinding = DataBindingUtil.inflate(inflater, R.layout.stobject_list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.stObject = items[position]
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(val binding: StobjectListItemBinding) : RecyclerView.ViewHolder(binding.root){
        init {
            itemView.setOnClickListener { callback?.onItemClick(it, items[adapterPosition]) }
        }
    }

}