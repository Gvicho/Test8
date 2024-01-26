package com.example.test8.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.test8.databinding.ItemNavigationOptionBinding
import com.example.test8.databinding.ItemSettingsOptionsBinding
import com.example.test8.presentation.model.Option
import com.example.test8.presentation.model.Type

class NavigationOptionsRecyclerAdapter(private val data:List<Option>, private val listener:CallBackListener) : RecyclerView.Adapter<ViewHolder>() {

    companion object {

        const val Item_Type_Normal = 1
        const val Item_Type_Switch = 2
    }

    inner class NavigationOptionsViewHolder(private val binding: ItemNavigationOptionBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            val option = data[position]
            setItemOnClickListener(option.id)
            binding.apply {
                optionsTV.text = option.name
                optionIcon.setImageResource(option.icon!!)
            }
        }
        private fun setItemOnClickListener(id:Int){
            binding.root.setOnClickListener {
                listener.onOptionClicked(id)
            }
        }
    }

    inner class SettingsOptionViewHolder(private val binding:ItemSettingsOptionsBinding ):RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            Item_Type_Normal -> NavigationOptionsViewHolder(ItemNavigationOptionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            Item_Type_Switch -> SettingsOptionViewHolder(ItemSettingsOptionsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw IllegalStateException("Invalid view type")
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(holder is NavigationOptionsViewHolder)holder.bind(position)
        else if(holder is SettingsOptionViewHolder) holder.bind(position)
    }

    override fun getItemViewType(position: Int): Int {
        return when (data[position].type) {
            Type.NORMAL->{
                Item_Type_Normal
            }
            Type.Switch->{
                Item_Type_Switch
            }
        }
    }

}

interface CallBackListener{
    fun onOptionClicked(optionId:Int)
}