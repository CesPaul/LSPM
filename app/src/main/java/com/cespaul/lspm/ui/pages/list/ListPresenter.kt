package com.cespaul.lspm.ui.pages.list

import androidx.appcompat.widget.PopupMenu
import com.cespaul.lspm.R
import com.cespaul.lspm.base.fragment.BaseFragmentPresenter
import com.cespaul.lspm.model.ListItem
import com.cespaul.lspm.model.repository.ListRepositoryImpl


class ListPresenter(listView: ListView) : BaseFragmentPresenter<ListView>(listView) {
    private val listRepository = ListRepositoryImpl()
    var listAdapter = ListRvAdapter(
        listRepository,
        { _, item ->
            viewFragment.showToast("Edit")
            //onEditService(item)
        },
        { _, item, it ->
            val popupMenu = PopupMenu(viewFragment.getFragmentContext(), it)
            popupMenu.menuInflater.inflate(R.menu.list_item_long_click_menu, popupMenu.menu)
            popupMenu.gravity = 5
            popupMenu.show()
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.item_edit -> {
                        //onEditService(item)
                        viewFragment.showToast("Edit")
                        return@setOnMenuItemClickListener true
                    }
                    R.id.item_delete -> {
                        onDeleteItem(item)
                        return@setOnMenuItemClickListener true
                    }
                    else -> return@setOnMenuItemClickListener true
                }
            }
            return@ListRvAdapter true
        }
    )

    fun onAddItem() {
        viewFragment.showAddDialog {
            listRepository.addItem(it)
            listAdapter.notifyDataSetChanged()
        }
    }

    private fun onDeleteItem(itemList: ListItem) {
        viewFragment.showDeleteDialog {
            listRepository.deleteItem(itemList)
            listAdapter.notifyDataSetChanged()
        }
    }

    private fun onEditService(listItem: ListItem) {
        viewFragment.showEditDialog(listItem) {
            if (listItem.nameItem.isEmpty()) {
                viewFragment.showToast(
                    viewFragment.getFragmentContext().getString(R.string.empty_field)
                )
            } else {
                listItem.nameItem = it.nameItem
                listRepository.editItem(listItem)
                listAdapter.notifyDataSetChanged()
            }
        }
    }
}