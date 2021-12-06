package ca.mcgill.ecse321.librarysystem;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.librarysystem.databinding.ActivityMainBinding;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    // for error handling, add here ...
    private String error = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve);
        initItemTable();
        //refreshErrorMessage();
    }

    public void initItemTable() {

        TableLayout tbl = (TableLayout) findViewById(R.id.table_items);
        TableRow head = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" ID");
        tv0.setTextColor(Color.WHITE);
        tv0.setTextSize(20);
        tv0.setBackgroundColor(Color.BLACK);
        head.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" TITLE ");
        tv1.setTextColor(Color.WHITE);
        tv1.setBackgroundColor(Color.BLACK);
        tv1.setTextSize(20);
        head.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" STATUS ");
        tv2.setTextColor(Color.WHITE);
        tv2.setBackgroundColor(Color.BLACK);
        tv2.setTextSize(20);
        head.addView(tv2);
        tbl.addView(head);

        HttpUtils.get("/getAllItems", new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                for(int i = 0; i < response.length(); i++) {
                    JSONObject thisItem;
                    int color;
                    if ( i%2 == 0) {
                        color = Color.LTGRAY;
                    } else {
                        color = Color.WHITE;
                    }
                    try {
                        thisItem = response.getJSONObject(i);
                        addItemToTable(thisItem, color);
                    } catch(Exception e) {
                        error = e.getMessage();
                    }
                    refreshErrorMessage();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                try {
                    error += errorResponse.get("message").toString();
                } catch(Exception e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }

        });
    }
    private void addItemToTable(JSONObject item, int color) {
        String title;
        int id;
        String status;
        TableLayout tbl = (TableLayout) findViewById(R.id.table_items);
        try {
            title = item.getString("title");
            id = item.getInt("id");
            status = item.getString("status");
        } catch(Exception e) {
            error += e.getMessage();
            refreshErrorMessage();
            return;
        }
        TableRow row = new TableRow(this);
        TextView t1v = new TextView(this);
        t1v.setText(String.valueOf(id));
        t1v.setTextColor(Color.BLACK);
        t1v.setGravity(Gravity.CENTER);
        t1v.setBackgroundColor(color);
        row.addView(t1v);
        TextView t2v = new TextView(this);
        t2v.setText(title);
        t2v.setTextColor(Color.BLACK);
        t2v.setGravity(Gravity.CENTER);
        t2v.setBackgroundColor(color);
        row.addView(t2v);
        TextView t3v = new TextView(this);
        t3v.setText(status);
        t3v.setTextColor(Color.BLACK);
        t3v.setGravity(Gravity.CENTER);
        t3v.setBackgroundColor(color);
        row.addView(t3v);
        //row.setBackground();
        tbl.addView(row);
    }


    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }
}