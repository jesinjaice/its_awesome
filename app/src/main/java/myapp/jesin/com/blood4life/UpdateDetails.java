package myapp.jesin.com.blood4life;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

public class UpdateDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    TextView fnTV;
    Spinner bloodspinner;
    ArrayAdapter<CharSequence> adapter1;
    Spinner distspinner;
    ArrayAdapter<CharSequence> adapter2;
    Spinner cityspinner;
    ArrayAdapter<CharSequence> adapter3;
    Spinner statspinner;
    ArrayAdapter<CharSequence> adapter4;
    Button upbtn;
    EditText mailET,pnET;
    String objid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);


        String phno=getIntent().getStringExtra("phno");

        Parse.initialize(this);

        fnTV=findViewById(R.id.unmID);
        mailET=findViewById(R.id.umlID);
        pnET=findViewById(R.id.upnID);


        bloodspinner = findViewById(R.id.ubtspID);
        adapter1 = ArrayAdapter.createFromResource(this, R.array.bloodgroup, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        bloodspinner.setAdapter(adapter1);
        bloodspinner.setOnItemSelectedListener(this);


        distspinner=findViewById(R.id.udtspID);
        adapter2=ArrayAdapter.createFromResource(this,R.array.districts,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        distspinner.setAdapter(adapter2);
        distspinner.setOnItemSelectedListener(this);


        cityspinner=findViewById(R.id.uctspID);
        adapter3=ArrayAdapter.createFromResource(this,R.array.cities,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        cityspinner.setAdapter(adapter3);
        cityspinner.setOnItemSelectedListener(this);


        statspinner=findViewById(R.id.ustspID);
        adapter4=ArrayAdapter.createFromResource(this,R.array.status,android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        statspinner.setAdapter(adapter4);
        statspinner.setOnItemSelectedListener(this);


        ParseQuery<ParseObject> query=ParseQuery.getQuery("Donor");
        query.whereEqualTo("PhNo",phno);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                objid=object.getObjectId();
                getDonorDetails(objid);
            }
        });

        upbtn=(Button)findViewById(R.id.upbtnID);
        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String rname=fnTV.getText().toString();
                final String rbld=bloodspinner.getSelectedItem().toString();
                final String rnum=pnET.getText().toString();
                final String rdist=distspinner.getSelectedItem().toString();
                final String rcity=cityspinner.getSelectedItem().toString();
                final String rmail=mailET.getText().toString();
                final String stat=statspinner.getSelectedItem().toString();


                ParseQuery<ParseObject> query=ParseQuery.getQuery("Donor");
                query.getInBackground(objid, new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        object.put("FullName",rname);
                        object.put("BloodType",rbld);
                        object.put("PhNo",rnum);
                        object.put("District",rdist);
                        object.put("City",rcity);
                        object.put("EMail",rmail);
                        object.put("Status",stat);

                        object.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if(e==null){
                                    Toast.makeText(UpdateDetails.this,"Update Successful",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(UpdateDetails.this,Home.class);
                                    intent.putExtra("phno",rnum);
                                    startActivity(intent);
                                }
                                else
                                    Toast.makeText(UpdateDetails.this,"Updation Failed",Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text=adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    private void getDonorDetails(String objid){
        ParseQuery<ParseObject> query=ParseQuery.getQuery("Donor");
        query.getInBackground(objid, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                String fn = object.getString("FullName");
                String mail = object.getString("EMail");
                String num = object.getString("PhNo");
                displayDetails(fn, mail, num);
            }
        });
    }
    private void displayDetails(String fn,String mail,String num){
        fnTV.setText(fn);
        mailET.setText(mail);
        pnET.setText(num);
    }
}
