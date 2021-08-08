package id.yuana.ministockbit.ui.main.watchlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.yuana.ministockbit.R
import id.yuana.ministockbit.data.model.CoinItemModel
import kotlinx.android.synthetic.main.item_watchlist.view.*

class WatchlistAdapter : RecyclerView.Adapter<WatchlistAdapter.ViewHolder>() {

    val data: MutableList<CoinItemModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_watchlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun replaceData(items: List<CoinItemModel>) {
        data.clear()
        add(items)
    }

    fun add(item: CoinItemModel) {
        data.add(item)
        notifyItemInserted(data.size - 1)
    }

    fun add(item: CoinItemModel, position: Int) {
        data.add(position, item)
        notifyItemInserted(position)
    }

    open fun add(items: List<CoinItemModel>) {
        for (item in items) {
            data.add(item)
        }
        notifyDataSetChanged()
    }

    fun add(items: Array<CoinItemModel>) {
        add(listOf(*items))
        notifyDataSetChanged()
    }

    fun addOrUpdate(item: CoinItemModel) {
        val i = data.indexOf(item)
        if (i >= 0) {
            data[i] = item
            notifyItemChanged(i)
        } else {
            add(item)
        }
    }

    fun addOrUpdate(items: List<CoinItemModel>) {
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

    fun remove(item: CoinItemModel) {
        val position = data.indexOf(item)
        remove(position)
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: CoinItemModel) {
            with(itemView) {
                tvName.text = item.name
                tvFullname.text = item.fullName
                tvPrice.text = item.display?.usd?.get("PRICE")?.asString ?: "N/A"
                tvChangeHour.text = item.display?.usd?.get("CHANGEPCTHOUR")?.asString ?: "N/A"
            }
        }

    }
}