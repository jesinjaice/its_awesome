package myapp.jesin.com.blood4life;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;



public class ProfileView extends AppCompatActivity {

    TextView ndnmTV,ndbtTV,ndctTV,nddtTV,ndmlTV,ndphTV,ndstTV;
    Button callbt;
    String objid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);


        String fn=getIntent().getStringExtra("name");

        Parse.initialize(this);

        ndnmTV=findViewById(R.id.ndnmID);
        ndbtTV=findViewById(R.id.ndbtID);
        ndctTV=findViewById(R.id.ndctID);
        nddtTV=findViewById(R.id.nddtID);
        ndmlTV=findViewById(R.id.ndmlID);
        ndphTV=findViewById(R.id.ndphID);
        ndstTV=findViewById(R.id.ndstID);

        ParseQuery<ParseObject> query=ParseQuery.getQuery("Donor");
        query.whereEqualTo("FullName",fn);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                objid=object.getObjectId();
                getDonorDetails(objid);
            }
        });


        callbt = (Button) findViewById(R.id.callbtn);
        callbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num=ndphTV.getText().toString();
                String call="tel:" +num;
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(call));
                startActivity(intent);
            }
        });
    }
    private void getDonorDetails(String objid){
        ParseQuery<ParseObject> query=ParseQuery.getQuery("Donor");
        query.getInBackground(objid, new GetCallback<ParseObject>() {
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
        ndnmTV.setText("Name :");
        ndbtTV.setText("Blood Type :");
        ndctTV.setText("City :");
        nddtTV.setText("District :");
        ndmlTV.setText("E-Mail :");
        ndphTV.setText("+");
        ndstTV.setText("Status :");

        ndnmTV.append(fn);
        ndbtTV.append(bt);
        ndctTV.append(cit);
        nddtTV.append(dist);
        ndmlTV.append(mail);
        ndphTV.append(num);
        ndstTV.append(st);
    }
}
