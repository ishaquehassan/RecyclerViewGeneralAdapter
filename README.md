# RecyclerViewGeneralAdapter
A RecyclerView Adapter for general purpose simple lists. It supports all common features including itemViewType.

### Installation
```groovy
implementation 'com.ishaquehassan:recyclerviewgeneraladapter:0.1.1'
```

### Simple Usage
```kotlin
data class ItemModel(val title:String)

//Simple Usage without ViewType
yourRecyclerViewInstance.adapter = RecyclerGeneralTypeAdapter(
    arrayListOf(ItemModel("Item 1"),ItemModel("Item 2")),
    R.layout.your_item_layout
){ itemData,viewHolder ->
    val itemView = viewHolder.itemView
    val position = viewHolder.adapterPosition
    itemView.findViewById<TextView>(R.id.item_title_tv).text = itemData.title
}
```

### Advance Usage
```kotlin
data class ItemModel(val title:String)

/Adavanced Usage with ViewType
yourRecyclerViewInstance.adapter = RecyclerGeneralTypeAdapter(
    arrayListOf(ItemModel("Item 1"),ItemModel("Item 2")),
    mapOf(
        // Key = ViewTypw , Value = Layout Resource File
        1 to R.layout.your_item_layout_1,
        2 to R.layout.your_item_layout_2
    ),{position,itemData ->

        //ViewType Decision on basis of item data
        if(itemData.title == "Item 1"){1}

        //ViewType Decision on basis of item position
        else if(position == 1){2}

        //Default ViewType should be returned too
        else {1}
    }
){ itemData,viewHolder ->
    val itemView = viewHolder.itemView
    val position = viewHolder.adapterPosition
    itemView.findViewById<TextView>(R.id.item_title_tv).text = itemData.title
}
```


