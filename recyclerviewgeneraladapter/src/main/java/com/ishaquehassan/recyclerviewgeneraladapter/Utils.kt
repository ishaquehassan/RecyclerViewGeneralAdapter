package com.ishaquehassan.recyclerviewgeneraladapter

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView

fun RecyclerView.addListDivider(){
    addItemDecoration(
        DividerItemDecoration(
            context,
            DividerItemDecoration.VERTICAL
        )
    )
}