package com.cespaul.lspm.ui.pages.list

import androidx.appcompat.widget.PopupMenu
import com.cespaul.lspm.R
import com.cespaul.lspm.base.fragment.BaseFragmentPresenter
import com.cespaul.lspm.data.repository.ListRepository
import com.cespaul.lspm.data.repository.ListRepositoryImpl
import com.cespaul.lspm.model.Item


class ListPresenter(
    listView: ListView
    //listRepository: ListRepository
) : BaseFragmentPresenter<ListView>(listView) {

    //private var repository: ListRepository = listRepository

    private val repository: ListRepository = ListRepositoryImpl(listView.getFragmentContext())

    var listAdapter = ListRvAdapter(
        repository,
        { _, item ->
            onEditItem(item)
        },
        { _, item, it ->
            val popupMenu = PopupMenu(viewFragment.getFragmentContext(), it)
            popupMenu.menuInflater.inflate(R.menu.list_item_long_click_menu, popupMenu.menu)
            popupMenu.gravity = 5
            popupMenu.show()
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.item_edit -> {
                        onEditItem(item)
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
            repository.addItem(it)
            listAdapter.notifyDataSetChanged()
        }
    }

    private fun onDeleteItem(item: Item) {
        viewFragment.showDeleteDialog {
            repository.deleteItem(item)
            listAdapter.notifyDataSetChanged()
        }
    }

    private fun onEditItem(item: Item) {
        viewFragment.showEditDialog(item) {
            if (item.nameItem.isEmpty()) {
                viewFragment.showToast(
                    viewFragment.getFragmentContext().getString(R.string.empty_field)
                )
            } else {
                item.nameItem = it.nameItem
                repository.editItem(item)
                listAdapter.notifyDataSetChanged()
            }
        }
    }
}