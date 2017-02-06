package app.project.gglfrmworks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userName, password;
    private Button button;

    private FirebaseDatabase database = null;
    private DatabaseReference myRef = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(this);
        userName = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReferenceFromUrl("https://gglfrmworks.firebaseio.com/a/b/bc");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                long a = dataSnapshot.getChildrenCount();
                JSONObject value = dataSnapshot.getValue(JSONObject.class);
                Log.d("gggggggggggggggggggg", "Value is: " + value.toString() + "---" + a);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("gggggggggggggggggggg", "Failed to read value.", error.toException());
            }
        });
    }


    @Override
    public void onClick(View view) {

    }
}
