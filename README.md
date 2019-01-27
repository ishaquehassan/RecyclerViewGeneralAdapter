# RecyclerViewGeneralAdapter [![Download](https://api.bintray.com/packages/ishaquehassan/RecyclerViewGeneralAdapter/com.ishaquehassan.recyclerviewgeneraladapter/images/download.svg)](https://bintray.com/ishaquehassan/RecyclerViewGeneralAdapter/com.ishaquehassan.recyclerviewgeneraladapter)
A RecyclerView Adapter for general purpose simple lists. It supports all common features including itemViewType. This adapter let you create any type of list using RecyclerView without creating a actual adapter class like we used to with default ArrayAdapter ut with custom data / View handling.

#### [Check out Sample Project !](https://github.com/ishaquehassan/RecyclerViewGeneralAdapterSample)

![Screenshot 1](https://raw.githubusercontent.com/ishaquehassan/RecyclerViewGeneralAdapter/master/images/sc_1.png)

## Installation
```groovy
implementation 'com.ishaquehassan:recyclerviewgeneraladapter:0.1.5'
```

### There are two constructors for this adapter
**Without itemViewType**
```kotlin
RecyclerGeneralTypeAdapter(
    // pass your data as list or any type of array / collection
    data:ArrayList<T>,

    // set your layout file resource like (R.layout.my_list_item_view)
    layoutFile: Int,

    // finally pass a callback function as bindViewholder which gives you 2 inputs
    // 1. Your item data for current item,
    // 2. Your viewHolder ref of current item
    onBindItem:(itemData:T,viewHolder:RecyclerViewGeneralAdapter<T>.RecyclerGeneralViewHolder)->Unit
)
```
**With itemViewType**
```kotlin
RecyclerGeneralTypeAdapter(
    // pass your data as list or any type of array / collection
    data:ArrayList<T>,

    // map of your layoutfiles with key as their ViewType like
    // mapOf(1 to R.layout.view_type_one, ... and so on)
    val layoutFiles:Map<Int,Int>,

    // Here you can pass a call back to decide which itemViewType should be rendered & it gives you 2 inputs for that
    // 1. Position of the current item
    // 2. Data of current item to decide with
    // in response you should return am int which should be one of the keys of your map which you'd defined above
    val onGetViewType:(position:Int, itemData:T)->Int

    // finally pass a callback function as bindViewholder which gives you 2 inputs
    // 1. Your item data for current item,
    // 2. Your viewHolder ref of current item
    onBindItem:(itemData:T,viewHolder:RecyclerViewGeneralAdapter<T>.RecyclerGeneralViewHolder)->Unit
)
```

### Simple Usage
```kotlin
// find recyclerView
val myRecyclerList = findViewById<RecyclerView>(R.id.myRecyclerListId)

//Set its layout manager (Linear/Grid or any other)
myRecyclerList.layoutManager = LinearLayoutManager(this)

//Now just set the adapter like this
myRecyclerList.adapter = RecyclerGeneralTypeAdapter(

    // Data (In this example its string but you can use any type)
    arrayListOf("Item 1","Item 2","Item 3"),

    //Set the layout for your item
    R.layout.your_item_layout

)

//Finally attach the bindViewHolder callback
{ itemData,viewHolder ->
    // your item view instance for this item
    val itemView = viewHolder.itemView

    // your adapter position for this item
    val position = viewHolder.adapterPosition
    itemView.findViewById<TextView>(R.id.item_title_tv).text = itemData
}
```

### Advance Usage
```kotlin
// Sample data class
data class ItemModel(val title:String)

// find recyclerView
val myRecyclerList = findViewById<RecyclerView>(R.id.myRecyclerListId)

//Set its layout manager (Linear/Grid or any other)
myRecyclerList.layoutManager = LinearLayoutManager(this)

//Adavanced Usage with ViewType
yourRecyclerViewInstance.adapter = RecyclerGeneralTypeAdapter(

    // Data (In this example its string but you can use any type)
    arrayListOf(ItemModel("Item 1"),ItemModel("Item 2")),

    // map of your layoutfiles with key as their ViewType
    mapOf(
        // Key = ViewType , Value = Layout Resource File
        1 to R.layout.your_item_layout_1,
        2 to R.layout.your_item_layout_2
    ),

    // Decide which laout should be rendered on which position
    {position,itemData ->

        //ViewType Decision on basis of item data
        if(itemData.title == "Item 1"){1}

        //ViewType Decision on basis of item position
        else if(position == 1){2}

        //Default ViewType should be returned too
        else {1}
    }
)

//Finally attach the bindViewHolder callback
{ itemData,viewHolder ->
    val itemView = viewHolder.itemView
    val position = viewHolder.adapterPosition
    itemView.findViewById<TextView>(R.id.item_title_tv).text = itemData.title
}
```

### Additional Features
```kotlin

// To add item, just pass your data. View will be automatically updated
adapterInstance.add(ItemModel("new item"))

// To remove item, just pass your index/position. View will be automatically updated
adapterInstance.remove(position)

// To update item, just pass your index/position & data. View will be automatically updated
adapterInstance.update(position,ItemModel("new item updated"))

//Add list item divider
yourRecyclerViewInstance.addListDivider()
```

## Complete Sample

#### activity_main.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".SimpleListActivity">

    <android.support.v7.widget.RecyclerView
            android:id="@+id/mySimpleList"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>

</android.support.constraint.ConstraintLayout>
```

#### MainActivity.kt
```kotlin
//Model class for each item
private data class SimpleItemModel(val name:String,val releaseYear:String)

//Declaring list for list to render
private lateinit var simpleData:ArrayList<SimpleItemModel>

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    //Initialized data list with sample data
    simpleData = arrayListOf(
        SimpleItemModel("The Shawshank Redemption","1994"),
        SimpleItemModel("The Godfather","1972"),
        SimpleItemModel("The Godfather: Part II","1974"),
        SimpleItemModel("The Dark Knight ","2008"),
        SimpleItemModel("12 Angry Men","1957"),
        SimpleItemModel("Schindler's List","1993"),
        SimpleItemModel("The Shawshank Redemption","1994"),
        SimpleItemModel("The Godfather","1972"),
        SimpleItemModel("The Godfather: Part II","1974"),
        SimpleItemModel("The Dark Knight ","2008"),
        SimpleItemModel("12 Angry Men","1957"),
        SimpleItemModel("Schindler's List","1993")
    )

    mySimpleList.layoutManager = LinearLayoutManager(this)

    //Added list divider in just a simple call to this method
    mySimpleList.addListDivider()

    //Here we can just create & set the adapter in few lines
    mySimpleList.adapter = RecyclerViewGeneralAdapter(
        simpleData,R.layout.simple_list_item
    ) {itemData, viewHolder ->
        val mainView = viewHolder.itemView
        val nameTv = mainView.findViewById<TextView>(R.id.simple_name_tv)
        val yearTv = mainView.findViewById<TextView>(R.id.simple_year_tv)

        nameTv.text = itemData.name
        yearTv.text = itemData.releaseYear
    }
}
```

##  How to Contribute
1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -am 'Add some feature')
4. Push to the branch (git push origin my-new-feature)
5. Create new Pull Request


## License

    Copyright 2019 Ishaq Hassan

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.