package myapp.jesin.com.blood4life;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class Register extends AppCompatActivity implements OnItemSelectedListener{
    Spinner bloodspinner;
    ArrayAdapter<CharSequence> adapter1;
    Spinner distspinner;
    ArrayAdapter<CharSequence> adapter2;
    Spinner cityspinner;
    ArrayAdapter<CharSequence> adapter3;
    Button reg;
    EditText name,num,mail,pass;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mContext = this;

        bloodspinner = findViewById(R.id.bloodgroup);
        adapter1 = ArrayAdapter.createFromResource(this, R.array.bloodgroup, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        bloodspinner.setAdapter(adapter1);

        bloodspinner.setOnItemSelectedListener(this);
        distspinner=findViewById(R.id.distdrop);
        adapter2=ArrayAdapter.createFromResource(this,R.array.districts,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        distspinner.setAdapter(adapter2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cityspinner=findViewById(R.id.citydrop);
        adapter3=ArrayAdapter.createFromResource(mContext, R.array.cities_ernakulam,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        cityspinner.setAdapter(adapter3);
        distspinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    adapter3=ArrayAdapter.createFromResource(mContext, R.array.cities_ernakulam,android.R.layout.simple_spinner_item);
                    adapter3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    cityspinner.setAdapter(adapter3);
                } else {
                    adapter3=ArrayAdapter.createFromResource(mContext, R.array.cities_idukki,android.R.layout.simple_spinner_item);
                    adapter3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    cityspinner.setAdapter(adapter3);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cityspinner.setOnItemSelectedListener(this);


        Parse.initialize(this);

        name=findViewById(R.id.fname);
        num=findViewById(R.id.num);
        mail=findViewById(R.id.mail);
        pass=findViewById(R.id.pass);

        reg=findViewById(R.id.regbtn);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rname=name.getText().toString();
                String rbld=bloodspinner.getSelectedItem().toString();
                String rnum=num.getText().toString();
                String rdist=distspinner.getSelectedItem().toString();
                String rcity=cityspinner.getSelectedItem().toString();
                String rmail=mail.getText().toString();
                String rpwd=pass.getText().toString();
                String stat="Available";

                ParseObject donor=new ParseObject("Donor");

                donor.put("FullName",rname);
                donor.put("BloodType",rbld);
                donor.put("PhNo",rnum);
                donor.put("District",rdist);
                donor.put("City",rcity);
                donor.put("EMail",rmail);
                donor.put("Password",rpwd);
                donor.put("Status",stat);

                donor.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null)
                        {
                            Toast.makeText(Register.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                            Toast.makeText(Register.this,"Registration Failed",Toast.LENGTH_SHORT).show();
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
}