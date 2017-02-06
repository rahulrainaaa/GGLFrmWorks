package app.project.gglfrmworks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth = null;
    private FirebaseAuth.AuthStateListener mAuthListener = null;
    String email = "cianhealthcaredrushti@gmail.com";
    //String email = "usdfsdfsdser@domain.ext";
    String password = "cian@123";

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    String name = user.getDisplayName();
                    Uri photo = user.getPhotoUrl();
                    Log.d("ddddddddddddddddddd", "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(MainActivity.this, "" + user.getUid(), Toast.LENGTH_SHORT).show();

                } else {
                    // User is signed out
                    Log.d("ddddddddddddddddddd", "onAuthStateChanged:signed_out");
                    Toast.makeText(MainActivity.this, "no session", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();

        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(mAuthListener);
        //Signout
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("ddddddddddddddddddd", "createUserWithEmail:onComplete:" + task.isSuccessful());

                if (!task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Creation failed.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Creation successful...", Toast.LENGTH_LONG).show();
                }

            }
        });

        //Signin
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("ddddddddddddddddddd", "createUserWithEmail:onComplete:" + task.isSuccessful());

                if (!task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "signin failed.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "signin successful...", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        //FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
//        super.onBackPressed();
    }
}
