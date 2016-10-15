package san.com.fetchjson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by Lenovo on 9/7/2016.
 */
public class ParseJSON extends AppCompatActivity implements View.OnClickListener{

    private String myJSONString;

    private static final String JSON_ARRAY ="result";
    private static final String ID = "id";
    private static final String COLL_USERNAME= "name";
    private static final String ADDRESS = "address";
    private static final String VC_NAME = "vicechancellor";

    private JSONArray users = null;

    private int TRACK = 0;

    private EditText editTextId;
    private EditText editTextUserName;
    private EditText editTextAddress;
    private EditText editTextVc;

    Button btnPrev;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pare_activity);

        Intent intent = getIntent();
        myJSONString = intent.getStringExtra(MainActivity.MY_JSON);


        editTextId = (EditText) findViewById(R.id.editTextID);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextVc = (EditText) findViewById(R.id.editTextVc);

        btnPrev = (Button) findViewById(R.id.buttonPrev);
        btnNext = (Button) findViewById(R.id.buttonNext);

        btnPrev.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        extractJSON();

        showData();
    }



    private void extractJSON(){
        try {
            JSONObject jsonObject = new JSONObject(myJSONString);
            users = jsonObject.getJSONArray(JSON_ARRAY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    private void moveNext(){
        if(TRACK<users.length()){
            TRACK++;
        }
        showData();
    }

    private void movePrev(){
        if(TRACK>0){
            TRACK--;
        }
        showData();
    }

    private void showData(){
        try {
            JSONObject jsonObject = users.getJSONObject(TRACK);

            editTextId.setText(jsonObject.getString(ID));
            editTextUserName.setText(jsonObject.getString(COLL_USERNAME));
            editTextAddress.setText(jsonObject.getString(ADDRESS));
            editTextVc.setText(jsonObject.getString(VC_NAME));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_parse_json, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    } */

    @Override
    public void onClick(View v) {
        if(v == btnNext){
            moveNext();
        }
        if(v == btnPrev){
            movePrev();
        }
    }
}
