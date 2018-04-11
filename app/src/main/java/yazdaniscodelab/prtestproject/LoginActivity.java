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

public class LoginActivity extends AppCompatActivity {

    private EditText emailLogin;
    private EditText passLogin;
    private Button btnLogin;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();

//        if (mAuth.getCurrentUser()!=null){
//            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
//        }


        login();

    }

    private void login() {

        emailLogin=findViewById(R.id.email_login_xml);
        passLogin=findViewById(R.id.pass_login_xml);
        btnLogin=findViewById(R.id.btn_login_xml);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=emailLogin.getText().toString().trim();
                String pass=passLogin.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    emailLogin.setError("Required Field");
                    return;
                }

                if (TextUtils.isEmpty(pass)){
                    passLogin.setError("Required Field");
                    return;
                }

                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Login Complete",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                        }else {
                            Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();

                        }

                    }
                });


            }
        });


    }
}
