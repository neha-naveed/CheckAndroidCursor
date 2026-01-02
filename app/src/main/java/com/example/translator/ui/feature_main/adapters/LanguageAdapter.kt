package com.example.translator.ui.feature_main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.translator.domain.model.Language

class LanguageAdapter(
    context: Context,
    private val languages: List<Language>
) : ArrayAdapter<Language>(context, android.R.layout.simple_dropdown_item_1line, languages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_dropdown_item_1line, parent, false)
        
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = languages[position].name
        
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getView(position, convertView, parent)
    }

    fun getLanguageCode(position: Int): String {
        return if (position >= 0 && position < languages.size) {
            languages[position].code
        } else {
            "en"
        }
    }

    fun getPositionByCode(code: String): Int {
        return languages.indexOfFirst { it.code == code }
    }
}

