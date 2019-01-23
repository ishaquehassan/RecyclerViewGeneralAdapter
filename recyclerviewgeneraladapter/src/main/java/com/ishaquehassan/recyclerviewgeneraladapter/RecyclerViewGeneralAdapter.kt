package com.ishaquehassan.recyclerviewgeneraladapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RecyclerViewGeneralAdapter<T>(val data:ArrayList<T>, private val layoutFiles:Map<Int,Int>, val onGetViewType:(position:Int, itemData:T)->Int, val onBindItem:(itemData:T, viewHolder:RecyclerViewGeneralAdapter<T>.RecyclerGeneralViewHolder)->Unit) : RecyclerView.Adapter<RecyclerViewGeneralAdapter<T>.RecyclerGeneralViewHolder>(){

    constructor(data:ArrayList<T>,layoutFile: Int,onBindItem:(itemData:T,viewHolder:RecyclerViewGeneralAdapter<T>.RecyclerGeneralViewHolder)->Unit):this(data, mapOf(-10 to layoutFile),{_,_->-10},onBindItem)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecyclerGeneralViewHolder(
            LayoutInflater.from(parent.context).inflate(
                layoutFiles[viewType]!!,
                parent,
                false
            )
        )
    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: RecyclerGeneralViewHolder, position: Int) =  onBindItem(data[position],holder)
    override fun getItemViewType(position: Int): Int = onGetViewType(position,data[position])
    inner class RecyclerGeneralViewHolder(v:View) : RecyclerView.ViewHolder(v)
    fun add(item:T){
        data.add(item)
        notifyItemInserted(data.size-1)
    }
    fun remove(position: Int){
        data.removeAt(position)
        notifyItemRemoved(position)
    }
    fun update(position: Int,item: T){
        data.set(position,item)
        notifyItemChanged(position)
    }
}

