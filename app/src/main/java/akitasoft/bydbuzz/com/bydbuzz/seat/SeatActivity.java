package akitasoft.bydbuzz.com.bydbuzz.seat;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import akitasoft.bydbuzz.com.bydbuzz.R;
import akitasoft.bydbuzz.com.bydbuzz.async.AsyncBidder;
import akitasoft.bydbuzz.com.bydbuzz.auctionselection.AuctionActivity;
import akitasoft.bydbuzz.com.bydbuzz.data.DbHelper;
import akitasoft.bydbuzz.com.bydbuzz.data.dbHelper.BidDbHelper;
import akitasoft.bydbuzz.com.bydbuzz.seatselection.SeatSelectionActivity;

public class SeatActivity extends AppCompatActivity {

    public static DbHelper dbHelper;
    public static SQLiteDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat_activity);

        dbHelper = DbHelper.getsInstance(this);
        sql = dbHelper.sql;

        AsyncBidder asyncBidder = new AsyncBidder(this);
        asyncBidder.execute();

        /* SEAT SELECTION */
        final Button btn_seatSelection = (Button) findViewById(R.id.btn_seat_description);
        btn_seatSelection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SeatSelectionActivity.class);
//                intent.putExtra("sampleText", "thereisnocowlevel");
                startActivity(intent);
            }
        });

        /* BID NOW : Remove intent at later date */
        final Button button = (Button) findViewById(R.id.btn_bid_now);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AuctionActivity.class);
//                intent.putExtra("sampleText", "thereisnocowlevel");
                startActivity(intent);
            }
        });

        /* Update history periodically */
        final Handler handler = new Handler();
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 5000);
                updateBidHistory();
            }
        };
        handler.postDelayed(r, 0000);
    }

    public void updateBidHistory() {
        final EditText et_bid_history = (EditText) findViewById(R.id.et_bid_history);
        et_bid_history.setKeyListener(null);

        BidDbHelper bidDbHelper = new BidDbHelper(sql);
        String bidHistory = bidDbHelper.fetch();
        et_bid_history.setText(bidHistory);
    }
}
