package ca.mcgill.ecse321.librarysystem;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    // for error handling, add here ...
    private String error = null;

    private JSONObject currentCustomer = null;
    private JSONObject newCustomer = null;

    // Login Customer
    public void login(View v) {
        final EditText et = (EditText) findViewById(R.id.editTextAccountId);
        String id = et.getText().toString();
        final EditText et2 = (EditText) findViewById(R.id.editTextTextPassword);
        String password = et2.getText().toString();

        HttpUtils.get("/loginCustomer/" + id+"/"+ password, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                currentCustomer = response;
                    try {
                        setContentView(R.layout.customer_home_page);
                        //test: delete until catch
                        ((TextView) findViewById(R.id.displayId)).setText(currentCustomer.getString("customerId"));
                        ((TextView) findViewById(R.id.displayFirstName)).setText(currentCustomer.getString("firstName"));
                        ((TextView) findViewById(R.id.displayLastName)).setText(currentCustomer.getString("lastName"));
                        ((TextView) findViewById(R.id.displayAddress)).setText(currentCustomer.getString("address"));
                        ((TextView) findViewById(R.id.displayEmail)).setText(currentCustomer.getString("email"));
                        ((TextView) findViewById(R.id.displayBalance)).setText(currentCustomer.getString("accountBalance") + "$");
                    } catch(Exception e) {
                        error += e.getMessage();
                    }
                    refreshErrorMessage();
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

    // Signup Customer
    public void signup(View v) {
        final EditText firstNameInput = (EditText) findViewById(R.id.editTextAccountId3);
        String firstName = firstNameInput.getText().toString();
        final EditText lastNameInput = (EditText) findViewById(R.id.editTextAccountId4);
        String lastName = lastNameInput.getText().toString();
        final EditText emailInput = (EditText) findViewById(R.id.editTextAccountId5);
        String email = emailInput.getText().toString();
        final EditText addressInput = (EditText) findViewById(R.id.editTextAccountId6);
        String address = addressInput.getText().toString();
        final EditText passwordInput = (EditText) findViewById(R.id.editTextTextPassword2);
        String password = passwordInput.getText().toString();

        // auto-generated values on customer creation
        String isVerified = "false";
        String isLocal = "false";
        String balance = "50";

        String createCustomerPath = firstName + "/" + lastName + "/" + password + "/" + email + "/" + isVerified + "/" + isLocal + "/" + address + "/" + balance;
        createCustomerPath = createCustomerPath.replaceAll(" ", "%20");

        HttpUtils.post("/createCustomer/" + createCustomerPath, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                newCustomer = response;
                try {
                    setContentView(R.layout.customer_home_page);
                } catch(Exception e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
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

 //sign out
    public void signout(View v) {
        try {
            setContentView(R.layout.login);
            currentCustomer = null;
        } catch (Exception e) {
            error += e.getMessage();
        }
    }

    //go to make res
    public void goToReservation(View v) {
        try {
            setContentView(R.layout.reserve);
            initItemTable();
            initReservationTable();
        } catch (Exception e) {
            error += e.getMessage();
        }
    }
    //go to make res
    public void goToProfile(View v) {
        try {
            setContentView(R.layout.customer_home_page);
            ((TextView) findViewById(R.id.displayId)).setText(currentCustomer.getString("customerId"));
            ((TextView) findViewById(R.id.displayFirstName)).setText(currentCustomer.getString("firstName"));
            ((TextView) findViewById(R.id.displayLastName)).setText(currentCustomer.getString("lastName"));
            ((TextView) findViewById(R.id.displayAddress)).setText(currentCustomer.getString("address"));
            ((TextView) findViewById(R.id.displayEmail)).setText(currentCustomer.getString("email"));
            ((TextView) findViewById(R.id.displayBalance)).setText(currentCustomer.getString("accountBalance") + "$");
        } catch (Exception e) {
            error += e.getMessage();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //initItemTable();
        //refreshErrorMessage();
    }



    public void initItemTable() {

        TableLayout tbl = (TableLayout) findViewById(R.id.table_items);
        tbl.removeAllViews();
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

    public void initReservationTable() {

        TableLayout tbl = (TableLayout) findViewById(R.id.table_reservations);
        tbl.removeAllViews();
        TableRow head = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText("ID");
        tv0.setTextColor(Color.WHITE);
        tv0.setTextSize(20);
        tv0.setBackgroundColor(Color.BLACK);
        head.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" ITEM ");
        tv1.setTextColor(Color.WHITE);
        tv1.setBackgroundColor(Color.BLACK);
        tv1.setTextSize(20);
        head.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" DUE DATE ");
        tv2.setTextColor(Color.WHITE);
        tv2.setBackgroundColor(Color.BLACK);
        tv2.setTextSize(20);
        head.addView(tv2);
        tbl.addView(head);
        String currentId = null;
        try{
            currentId = currentCustomer.getString("customerId");
        } catch(Exception e){
            error = e.getMessage();
            refreshErrorMessage();
        }

        HttpUtils.get("/getReservationByCustomer/" + currentId, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {

                for(int i = 0; i < response.length(); i++) {
                    JSONObject thisRes;
                    int color;
                    if ( i%2 == 0) {
                        color = Color.LTGRAY;
                    } else {
                        color = Color.WHITE;
                    }
                    try {
                        thisRes = response.getJSONObject(i);
                        addReservationToTable(thisRes, color);
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

    private void addReservationToTable(JSONObject res, int color) {
        JSONObject item;
        int id;
        String dueDate;
        String title;
        TableLayout tbl = (TableLayout) findViewById(R.id.table_reservations);
        try {
            item = res.getJSONObject("item");
            title = item.getString("title");
            id = res.getInt("id");
            dueDate = res.getString("endDate");
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
        t3v.setText(dueDate);
        t3v.setTextColor(Color.BLACK);
        t3v.setGravity(Gravity.CENTER);
        t3v.setBackgroundColor(color);
        row.addView(t3v);
        //row.setBackground();
        tbl.addView(row);
    }

    public void makeReservation(View v){
        final EditText firstNameInput = (EditText) findViewById(R.id.editTextAccountId4);
        String itemId = firstNameInput.getText().toString();
        String customerId = null;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(date));
        String dateString = formatter.format(date);
        try{
            customerId = currentCustomer.getString("customerId");
        } catch(Exception e){
            error = e.getMessage();
            refreshErrorMessage();
        }

        HttpUtils.post("/createReservation/" + customerId +"/"+ itemId +"/false/"+ dateString , new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                newCustomer = response;
                try {
                    initItemTable();
                    initReservationTable();
                } catch(Exception e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
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

    public void deleteReservation(View v){
        final EditText resInput = (EditText) findViewById(R.id.editTextResId);
        String resId = resInput.getText().toString();


        HttpUtils.delete("/deleteReservation/" + resId , new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                newCustomer = response;
                try {
                    initItemTable();
                    initReservationTable();
                } catch(Exception e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
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