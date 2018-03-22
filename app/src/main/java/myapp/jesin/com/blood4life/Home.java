package myapp.jesin.com.blood4life;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Home extends AppCompatActivity {
    TextView fnTV,btTV,citTV,distTV,mailTV,numTV,statTV;
    Button log;
    Button embtn;
    Button upbtn;
    private static final String TAG="hello";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        log=(Button)findViewById(R.id.logout);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,LoginPage.class);
                startActivity(intent);
            }
        });


        embtn=(Button)findViewById(R.id.ebtnID);
        embtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,Emergency.class);
                startActivity(intent);
            }
        });






        final String phno=getIntent().getStringExtra("phno");
        Log.i(TAG,"Sucessful"+phno);

        Parse.initialize(this);

        fnTV=findViewById(R.id.fnID);
        btTV=findViewById(R.id.bldID);
        citTV=findViewById(R.id.cityID);
        distTV=findViewById(R.id.distID);
        mailTV=findViewById(R.id.mailID);
        numTV=findViewById(R.id.numID);
        statTV=findViewById(R.id.statID);

        ParseQuery<ParseObject> query=ParseQuery.getQuery("Donor");

        query.whereEqualTo("PhNo",phno);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                String obj=object.getObjectId();
                Log.i(TAG,"hello"+obj);
                getUserDetails(obj);
            }
        });


        upbtn=(Button)findViewById(R.id.editbtn);
        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,UpdateDetails.class);
                intent.putExtra("phno",phno);
                startActivity(intent);

            }
        });
    }
    private void getUserDetails(String obj){
        ParseQuery<ParseObject> query=ParseQuery.getQuery("Donor");
        query.getInBackground(obj, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                String fn=object.getString("FullName");
                String bt=object.getString("BloodType");
                String cit=object.getString("City");
                String dist=object.getString("District");
                String mail=object.getString("EMail");
                String num=object.getString("PhNo");
                String st=object.getString("Status");
                displayDetails(fn,bt,cit,dist,mail,num,st);
            }
        });
    }
    private void displayDetails(String fn,String bt,String cit,String dist,String mail,String num,String st){
        fnTV.setText("Name :");
        btTV.setText("Blood Type :");
        citTV.setText("City :");
        distTV.setText("District :");
        mailTV.setText("E-Mail :");
        numTV.setText("Phone No :");
        statTV.setText("Status :");

        fnTV.append(fn);
        btTV.append(bt);
        citTV.append(cit);
        distTV.append(dist);
        mailTV.append(mail);
        numTV.append(num);
        statTV.append(st);

    }
}
