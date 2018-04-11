package yazdaniscodelab.prtestproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private EditText userName;
    private EditText email;
    private EditText password;
    private Button btnreg;

    private FirebaseAuth mAuth;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth=FirebaseAuth.getInstance();

        FirebaseUser muser=mAuth.getCurrentUser();

        String uid=muser.getUid();

        mDatabase= FirebaseDatabase.getInstance().getReference().child("UserInformation").child(uid);

        reg();

    }

    private void reg(){

        userName=findViewById(R.id.username_xml);
        email=findViewById(R.id.email_xml);
        password=findViewById(R.id.password_xml);
        btnreg=findViewById(R.id.btn_reg_xml);


        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String mEmail=email.getText().toString().trim();
                final String mPass=password.getText().toString().trim();
              final  String mUserName=userName.getText().toString().trim();


                if (TextUtils.isEmpty(mEmail)){
                    email.setError("Required Field..");
                    return;
                }

                if (TextUtils.isEmpty(mPass)){
                    password.setError("Required Field..");
                    return;
                }


                mAuth.createUserWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(getApplicationContext(),"Registration Complete",Toast.LENGTH_SHORT).show();



                            String id=mDatabase.push().getKey();

                            Data data=new Data(mUserName,mPass,mEmail,id);

                            mDatabase.child(id).setValue(data);

                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));

                        } else {

                            Toast.makeText(getApplicationContext(),"Registration Faield",Toast.LENGTH_SHORT).show();
                        }

                    }
                });



            }
        });



    }


}
