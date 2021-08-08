package id.yuana.ministockbit.ui.main.watchlist

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import id.yuana.ministockbit.R
import id.yuana.ministockbit.data.model.CoinItemModel
import kotlinx.android.synthetic.main.item_watchlist.view.*

class WatchlistAdapter(context: Context) : RecyclerView.Adapter<WatchlistAdapter.ViewHolder>() {

    var data: SortedList<CoinItemModel> =
        SortedList(
            CoinItemModel::class.java,
            object : SortedListAdapterCallback<CoinItemModel>(this) {

                override fun compare(o1: CoinItemModel?, o2: CoinItemModel?): Int {
                    val first = o1?.raw?.usd?.get("LASTUPDATE")?.asLong ?: 0
                    val second = o2?.raw?.usd?.get("LASTUPDATE")?.asLong ?: 0

                    return second.compareTo(first)
                }

                override fun areContentsTheSame(
                    oldItem: CoinItemModel?,
                    newItem: CoinItemModel?
                ): Boolean {
                    return newItem?.equals(oldItem) ?: false
                }

                override fun areItemsTheSame(
                    item1: CoinItemModel?,
                    item2: CoinItemModel?
                ): Boolean {
                    return item2?.id.equals(item1?.id)
                }

            })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_watchlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size()


    fun add(item: CoinItemModel) {
        data.add(item)
        notifyItemInserted(data.size() - 1)
    }

    fun add(item: CoinItemModel, position: Int) {
        data.updateItemAt(position, item)
        notifyItemInserted(position)
    }

    fun add(items: List<CoinItemModel>) {
        data.beginBatchedUpdates()
        for (item in items) {
            data.add(item)
        }
        data.endBatchedUpdates()
        notifyDataSetChanged()
    }

    fun add(items: Array<CoinItemModel>) {
        add(listOf(*items))
        notifyDataSetChanged()
    }

    fun addOrUpdate(item: CoinItemModel) {
        val i = data.indexOf(item)
        if (i >= 0) {
            data.updateItemAt(i, item)
            notifyItemChanged(i)
        } else {
            add(item)
        }
    }

    fun addOrUpdate(items: List<CoinItemModel>) {
        data.beginBatchedUpdates()
        for (item in items) {
            addOrUpdate(item)
        }
        data.endBatchedUpdates()
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        if (position >= 0 && position < data.size()) {
            data.removeItemAt(position)
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

        @SuppressLint("SetTextI18n")
        fun bind(item: CoinItemModel) {
            with(itemView) {
                tvName.text = item.name
                tvFullname.text = item.fullName
                tvPrice.text = item.display?.getPrice() ?: "N/A"
                tvChangeHour.text =
                    " ${item.display?.getChangeHour() ?: "N/A"} (${item.display?.getChangePctHour() ?: "N/A"})"
                item.raw?.let {
                    if (it.isChangeHourIncrease()) {
                        tvChangeHour.setTextColor(
                            ContextCompat.getColor(
                                context,
                                R.color.green_400
                            )
                        )
                    } else if (it.isChangeHourDecrease()) {
                        tvChangeHour.setTextColor(ContextCompat.getColor(context, R.color.red_400))
                    }
                }
            }
        }

    }
}