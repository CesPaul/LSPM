package com.cespaul.lspm.ui.pages.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cespaul.lspm.R
import com.cespaul.lspm.base.fragment.BaseFragment
import com.cespaul.lspm.model.Item
import kotlinx.android.synthetic.main.dialog_add_list_item.view.*
import kotlinx.android.synthetic.main.dialog_delete_list_item.view.*
import kotlinx.android.synthetic.main.dialog_edit_list_item.*
import kotlinx.android.synthetic.main.dialog_edit_list_item.view.*
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : BaseFragment<ListPresenter>(), ListView {

    /*@Inject
    lateinit var listPresenter: ListPresenter*/

    private lateinit var toast: Toast

    private val layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //DaggerListScreenComponent.factory().create(App.appComponent).inject(this)
        val listRecycler = list_recycler
        listRecycler.layoutManager = layoutManager
        listRecycler.adapter = presenter.listAdapter
        val dividerItemDecoration = DividerItemDecoration(
            listRecycler.context,
            layoutManager.orientation
        )
        toast = Toast.makeText(context, "", Toast.LENGTH_LONG)
        listRecycler.addItemDecoration(dividerItemDecoration)

        add_item_fab.show()
        add_item_fab.setOnClickListener {
            presenter.onAddItem()
        }
    }

    override fun instantiatePresenter(): ListPresenter {
        return ListPresenter(this)
    }

    override fun showAddDialog(onConfirmListener: (Item) -> Unit) {
        val addDialogView =
            LayoutInflater.from(context).inflate(R.layout.dialog_add_list_item, null)
        val addDialogBuilder = AlertDialog.Builder(context)
            .setView(addDialogView)
            .setTitle(R.string.add_title)
            .setCancelable(false)
        val addAlertDialog = addDialogBuilder.show()

        addDialogView.cancelAddButton.setOnClickListener {
            addAlertDialog.dismiss()
        }
        addDialogView.confirmAddButton.setOnClickListener {
            if (addDialogView.add_name_item.text.toString().isEmpty()) {
                showToast(getString(R.string.empty_field))
                return@setOnClickListener
            }
            val name = addDialogView.add_name_item.text.toString()
            val serviceRent = Item(0, name)
            addAlertDialog.dismiss()
            onConfirmListener.invoke(serviceRent)
        }
    }

    override fun showDeleteDialog(onConfirmListener: () -> Unit) {
        val deleteDialogView =
            LayoutInflater.from(context).inflate(R.layout.dialog_delete_list_item, null)

        val deleteDialogBuilder = AlertDialog.Builder(context)
            .setView(deleteDialogView)
            .setTitle(R.string.delete_title)
            .setCancelable(false)
        val deleteAlertDialog = deleteDialogBuilder.show()

        deleteDialogView.cancelDeleteButton.setOnClickListener {
            deleteAlertDialog.dismiss()
        }
        deleteDialogView.confirmDeleteButton.setOnClickListener {
            deleteAlertDialog.dismiss()
            onConfirmListener.invoke()
        }
    }

    override fun showEditDialog(
        item: Item,
        onConfirmListener: (Item) -> Unit
    ) {
        val editDialogView =
            LayoutInflater.from(context).inflate(R.layout.dialog_edit_list_item, null)

        val editDialogBuilder = AlertDialog.Builder(context)
            .setView(editDialogView)
            .setTitle(R.string.edit_title)
            .setCancelable(false)
        val editAlertDialog = editDialogBuilder.show()

        editAlertDialog.edit_name_item.setText(
            item.nameItem,
            TextView.BufferType.EDITABLE
        )

        editDialogView.cancelEditButton.setOnClickListener {
            editAlertDialog.dismiss()
        }
        editDialogView.confirmEditButton.setOnClickListener {
            if (editDialogView.edit_name_item.text.toString().isEmpty()) {
                showToast(getString(R.string.empty_field))
                return@setOnClickListener
            }
            val nameItem = editDialogView.edit_name_item.text.toString()
            val tempListItem = Item(-1, nameItem)
            onConfirmListener.invoke(tempListItem)
            editAlertDialog.dismiss()
        }
    }

    override fun showToast(message: String?) {
        toast.setText(message)
        toast.show()
    }
}