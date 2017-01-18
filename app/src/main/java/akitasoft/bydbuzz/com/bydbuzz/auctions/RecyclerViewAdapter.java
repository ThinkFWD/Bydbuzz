package akitasoft.bydbuzz.com.bydbuzz.auctions;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import akitasoft.bydbuzz.com.bydbuzz.R;
import akitasoft.bydbuzz.com.bydbuzz.data.AuctionContract;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    Context mContext;
    View mView;
    RecyclerView.ViewHolder viewHolder1;
    private Cursor mCursor;

    public RecyclerViewAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_auction_description;
        public TextView tv_auction_ticket_count;
        public TextView tv_auction_cost_per;

        public ViewHolder(View v) {
            super(v);
            tv_auction_description = (TextView)v.findViewById(R.id.tv_auctions_item_description);
            tv_auction_ticket_count = (TextView)v.findViewById(R.id.tv_auctions_item_ticket_count);
            tv_auction_cost_per = (TextView)v.findViewById(R.id.tv_auctions_item_cost_per);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.auctions_list_item, parent, false);
        viewHolder1 = new ViewHolder(mView);
        return (ViewHolder) viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position))
            return;

        String desc = mCursor.getString(mCursor.getColumnIndex(AuctionContract.AuctionEntry.COLUMN_DESCRIPTION));
        String ticket_count = mCursor.getString(mCursor.getColumnIndex(AuctionContract.AuctionEntry.COLUMN_TICKET_COUNT));
        String cost_per = mCursor.getString(mCursor.getColumnIndex(AuctionContract.AuctionEntry.COLUMN_ITEM_PER_COST));

        holder.tv_auction_description.setText(desc);
        holder.tv_auction_ticket_count.setText(ticket_count);
        holder.tv_auction_cost_per.setText(cost_per);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
}