package com.cespaul.lspm.ui.pages.list

import com.cespaul.lspm.base.fragment.BaseFragmentView
import com.cespaul.lspm.model.ListItem

interface ListView : BaseFragmentView {

    fun showToast(message: String?)

    fun showAddDialog(onConfirmListener: (ListItem) -> Unit)

    fun showDeleteDialog(onConfirmListener: () -> Unit)

    fun showEditDialog(
        listItem: ListItem,
        onConfirmListener: (ListItem) -> Unit
    )
}