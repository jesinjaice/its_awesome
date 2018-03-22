package myapp.jesin.com.blood4life;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Display1Emerg extends AppCompatActivity {
    TextView ebltyTV,eunTV,ecitTV,edisTV,enameTV,enumbrTV;
    private static final String TAG="hehe";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display1_emerg);

        String bldtype = getIntent().getStringExtra("bloodtype");
        Log.i(TAG, "hello" + bldtype);
        ebltyTV = findViewById(R.id.ebldtyTV);
        eunTV = findViewById(R.id.eunitTV);
        ecitTV = findViewById(R.id.ecitTV);
        edisTV = findViewById(R.id.edistTV);
        enameTV = findViewById(R.id.enameTV);
        enumbrTV = findViewById(R.id.enumbrTV);

        getObjid(bldtype);
    }
    private void getObjid(String bldtype) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Emergency");
        query.whereEqualTo("bloodtype", bldtype);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {


                String obj = object.getObjectId();
                Log.i(TAG, "hello" + obj);
                getDetails(obj);

            }
        });
    }
    private void getDetails(String obj){

        ParseQuery<ParseObject> query=ParseQuery.getQuery("Emergency");
        query.getInBackground(obj, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                String eblty=object.getString("bloodtype");
                String eunit=object.getString("unitblood");
                String ecity=object.getString("city");
                String edist=object.getString("district");
                String ename=object.getString("nameconatact");
                String enumbr=object.getString("numcontact");
                displayDetails(eblty,eunit,ecity,edist,ename,enumbr);
            }
        });



    }
    private void displayDetails(String eblty,String eunit,String ecity,String edist,String ename,String enumbr){
        ebltyTV.setText("Blood Type : ");
        eunTV.setText("Unit of Blood : ");
        ecitTV.setText("City : ");
        edisTV.setText("District : ");
        enameTV.setText("Name of Contact : ");
        enumbrTV.setText("Ph.No. of Contact : ");

        ebltyTV.append(eblty);
        eunTV.append(eunit);
        ecitTV.append(ecity);
        edisTV.append(edist);
        enameTV.append(ename);
        enumbrTV.append(enumbr);

    }
}
