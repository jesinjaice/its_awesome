package myapp.jesin.com.blood4life;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class PostEmergency extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner pblood;
    ArrayAdapter<CharSequence> adapter1;
    Spinner pdist;
    ArrayAdapter<CharSequence> adapter2;
    Spinner pcity;
    ArrayAdapter<CharSequence> adapter3;
    EditText ublood,cname,cnum;
    Button post;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_emergency);

        mContext = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pblood = findViewById(R.id.postblood);
        adapter1 = ArrayAdapter.createFromResource(this, R.array.bloodgroup, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        pblood.setAdapter(adapter1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pblood.setOnItemSelectedListener(this);
        pdist=findViewById(R.id.postdist);
        adapter2=ArrayAdapter.createFromResource(this,R.array.districts,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        pdist.setAdapter(adapter2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pdist.setOnItemSelectedListener(this);
        pcity=findViewById(R.id.postcity);

        adapter3=ArrayAdapter.createFromResource(mContext, R.array.cities_ernakulam,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        pcity.setAdapter(adapter3);
        pdist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    adapter3=ArrayAdapter.createFromResource(mContext, R.array.cities_ernakulam,android.R.layout.simple_spinner_item);
                    adapter3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    pcity.setAdapter(adapter3);
                } else {
                    adapter3=ArrayAdapter.createFromResource(mContext, R.array.cities_idukki,android.R.layout.simple_spinner_item);
                    adapter3.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    pcity.setAdapter(adapter3);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Parse.initialize(this);

        ublood=findViewById(R.id.unitblood);
        cname=findViewById(R.id.contactname);
        cnum=findViewById(R.id.contactnum);
        post=(Button)findViewById(R.id.postbtn);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tbld=pblood.getSelectedItem().toString();
                String ubld=ublood.getText().toString();
                String pd=pdist.getSelectedItem().toString();
                String pc=pcity.getSelectedItem().toString();
                String cn=cname.getText().toString();
                String cno=cnum.getText().toString();


                ParseObject pemerge=new ParseObject("Emergency");

                pemerge.put("bloodtype",tbld);
                pemerge.put("unitblood",ubld);
                pemerge.put("district",pd);
                pemerge.put("city",pc);
                pemerge.put("nameconatact",cn);
                pemerge.put("numcontact",cno);

                pemerge.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e==null){
                            Toast.makeText(PostEmergency.this,"Emergency Successfully Posted",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                            Toast.makeText(PostEmergency.this,"Wrong Details",Toast.LENGTH_SHORT).show();
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
