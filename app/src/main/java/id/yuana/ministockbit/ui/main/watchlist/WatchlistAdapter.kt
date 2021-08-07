package id.yuana.ministockbit.ui.main.watchlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.yuana.ministockbit.R
import id.yuana.ministockbit.data.api.response.CoinItem
import kotlinx.android.synthetic.main.item_watchlist.view.*
import java.util.*

class WatchlistAdapter : RecyclerView.Adapter<WatchlistAdapter.ViewHolder>() {

    val data: MutableList<CoinItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_watchlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun replaceData(items: List<CoinItem>) {
        data.clear()
        add(items)
    }

    fun add(item: CoinItem) {
        data.add(item)
        notifyItemInserted(data.size - 1)
    }

    fun add(item: CoinItem, position: Int) {
        data.add(position, item)
        notifyItemInserted(position)
    }

    open fun add(items: List<CoinItem>) {
        for (item in items) {
            data.add(item)
        }
        notifyDataSetChanged()
    }

    fun add(items: Array<CoinItem>) {
        add(listOf(*items))
        notifyDataSetChanged()
    }

    fun addOrUpdate(item: CoinItem) {
        val i = data.indexOf(item)
        if (i >= 0) {
            data[i] = item
            notifyItemChanged(i)
        } else {
            add(item)
        }
    }

    fun addOrUpdate(items: List<CoinItem>) {
        for (item in items) {
            addOrUpdate(item)
        }
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        if (position >= 0 && position < data.size) {
            data.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun remove(item: CoinItem) {
        val position = data.indexOf(item)
        remove(position)
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: CoinItem) {
            with(itemView) {
                tvName.text = item.coinInfo.name
                tvFullname.text = item.coinInfo.fullName
                tvPrice.text = item.display?.usd?.get("PRICE")?.asString ?: "N/A"
                tvChangeHour.text = item.display?.usd?.get("VOLUMEDAY")?.asString ?: "N/A"
            }
        }

    }
}