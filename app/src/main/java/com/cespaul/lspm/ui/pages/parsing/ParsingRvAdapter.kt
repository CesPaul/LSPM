package com.cespaul.lspm.ui.pages.parsing

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cespaul.lspm.R
import com.cespaul.lspm.data.repository.ParsingRepository
import com.cespaul.lspm.model.Quotes
import kotlinx.android.synthetic.main.quote_parsing_row.view.*
import org.jsoup.Jsoup


class ParsingRvAdapter(
    private val context: Context,
    parsingRepository: ParsingRepository
) : RecyclerView.Adapter<ParsingRvAdapter.ParsingVH>() {

    private var quotesList = parsingRepository.getQuotesList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParsingVH {
        return ParsingVH(
            LayoutInflater.from(context).inflate(
                R.layout.quote_parsing_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int =
        quotesList.size

    override fun onBindViewHolder(vh: ParsingVH, position: Int) {
        val quoteItem = quotesList[position]

        // Исключение html-тегов из текста
        val description: String? = html2text(quoteItem.description)

        vh.descriptionQuote.text = description
        vh.timeQuote.text = quoteItem.time
        vh.ratingQuote.text = quoteItem.rating.toString()

    }

    fun updateList(quotes: Quotes) {
        this.quotesList = quotes.quotes
        notifyDataSetChanged()
    }

    class ParsingVH(
        itemLayoutView: View
    ) : RecyclerView.ViewHolder(itemLayoutView) {

        var descriptionQuote: TextView = itemLayoutView.description_quote
        var timeQuote: TextView = itemLayoutView.time_quote
        var ratingQuote: TextView = itemLayoutView.rating_quote

    }

    private fun html2text(html: String?): String? {
        return Jsoup.parse(html).text()
    }

}