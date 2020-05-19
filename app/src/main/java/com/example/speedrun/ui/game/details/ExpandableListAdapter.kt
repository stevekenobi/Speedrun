package com.example.speedrun.ui.game.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.example.network.model.dto.LevelDto
import com.example.speedrun.R
import kotlinx.android.synthetic.main.item_level.view.*
import kotlinx.android.synthetic.main.level_header.view.*

class ExpandableListAdapter(
    val viewModel: GameDetailsViewModel?,
    private val listOfHeaderData: List<String>,
    private val listOfChildData: Map<String, List<LevelDto>>
) : BaseExpandableListAdapter() {
    override fun getGroup(groupPosition: Int): Any {
        return listOfHeaderData[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val headerTitle = getGroup(groupPosition) as String

        val view =
            LayoutInflater.from(parent?.context).inflate(R.layout.level_header, parent, false)
        view.list_header.text = headerTitle

        if (groupPosition == 0)
            view.setOnClickListener {
                viewModel?.levelSelectedLiveData?.value = null
            }

        return view
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return listOfChildData[listOfHeaderData[groupPosition]]?.size ?: 0
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return listOfChildData[listOfHeaderData[groupPosition]]?.get(childPosition) ?: ""
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val child = getChild(groupPosition, childPosition) as LevelDto
        val childText = child.name

        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_level, parent, false)

        view.apply {
            level_name.text = childText

            setOnClickListener {
                viewModel?.levelSelectedLiveData?.value = child.id
            }
        }

        return view
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return listOfHeaderData.size
    }

}