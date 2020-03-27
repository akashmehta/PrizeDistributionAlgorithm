package com.aakash.newprizestructurealgorithm

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_prize_info.view.*

class PrizeInfoAdapter(
    private val itemList: List<PrizeInfoModel>
) : RecyclerView.Adapter<PrizeInfoAdapter.ViewHolder>() {

    private val TAG = PrizeInfoAdapter::class.java.simpleName

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_prize_info, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: PrizeInfoModel = itemList[position]
        holder.view.apply {
            key.text = item.key.toString()
            value.text = item.value.toString()
        }
    }

    override fun getItemCount(): Int = itemList.size
}