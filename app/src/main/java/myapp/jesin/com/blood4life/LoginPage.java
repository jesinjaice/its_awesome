package myapp.jesin.com.blood4life;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button login;
        final EditText num,pwd;

        Parse.initialize(this);

        num=findViewById(R.id.phonenumber);
        pwd=findViewById(R.id.pass);
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lnum=num.getText().toString();
                final String lpwd=pwd.getText().toString();



               ParseQuery<ParseObject> query=ParseQuery.getQuery("Donor");
               query.whereEqualTo("PhNo",lnum);
               query.whereEqualTo("Password",lpwd);
               query.getFirstInBackground(new GetCallback<ParseObject>() {
                   @Override
                   public void done(ParseObject object, ParseException e) {

                       if (e==null) {
                           Toast.makeText(LoginPage.this, "Welcome Donor", Toast.LENGTH_SHORT).show();
                           Intent intent=new Intent(LoginPage.this,Home.class);
                           startActivity(intent);
                       }
                       else
                           Toast.makeText(LoginPage.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();



                   }
               });


            }
        });

        Button register;
        register=(Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg=new Intent(LoginPage.this, Register.class);
                startActivity(reg);
            }
        });

    }

}