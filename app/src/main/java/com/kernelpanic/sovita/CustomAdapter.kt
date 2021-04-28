package com.kernelpanic.sovita

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import java.util.*
import com.kernelpanic.sovita.ui.exercise.Exercise

//This class is what is responsible for displaying the exercise list along with the checkboxes
class CustomAdapter(private val dataSet: ArrayList<Exercise>, mContext: Context) :
        ArrayAdapter<Exercise>(mContext, R.layout.row_item, dataSet) {

    private class ViewHolder {
        lateinit var txtName: TextView
        lateinit var checkBox: CheckBox
    }
    override fun getCount(): Int {
        return dataSet.size
    }
    override fun getItem(position: Int): Exercise {
        return dataSet[position] as Exercise
    }
    override fun getView(
            position: Int,
            convertView: View?,
            parent: ViewGroup
    ): View {
        var convertView = convertView
        val viewHolder: ViewHolder
        val result: View
        if (convertView == null) {
            viewHolder = ViewHolder()
            convertView =
                    LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
            viewHolder.txtName =
                    convertView.findViewById(R.id.txtName)
            viewHolder.checkBox =
                    convertView.findViewById(R.id.checkBox)
            result = convertView
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
            result = convertView
        }
        //val item: Exercise = getItem(position)
        viewHolder.txtName.text = dataSet[position].getName()
        viewHolder.checkBox.tag = position
        //viewHolder.checkBox!!.isChecked = dataSet[position].getSelected()
        viewHolder.checkBox.isChecked = dataSet[position].isChecked()
        //dataSet[position].setCheck(viewHolder.checkBox.isChecked)
        viewHolder.checkBox.setOnClickListener {
            val pos = viewHolder.checkBox.tag as Int
            if (dataSet[pos].isChecked()) {
                dataSet[pos].setCheck(false)
            }
            else {
                dataSet[pos].setCheck(true)
            }
        }
        //if (viewHolder.checkBox.isChecked) {
        //dataSet[position].setCheck(viewHolder.checkBox.isChecked)

        //}
        //item.isChecked()





        return result
    }
}