package com.cespaul.lspm.ui.pages.list

import com.cespaul.lspm.base.fragment.BaseFragmentView
import com.cespaul.lspm.model.Item

interface ListView : BaseFragmentView {

    fun showToast(message: String?)

    fun showAddDialog(onConfirmListener: (Item) -> Unit)

    fun showDeleteDialog(onConfirmListener: () -> Unit)

    fun showEditDialog(
        item: Item,
        onConfirmListener: (Item) -> Unit
    )
}