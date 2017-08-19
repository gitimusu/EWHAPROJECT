package org.ros.android.android_tutorial_pubsub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import org.ros.cafe_Boss_Emp.app.R;

/**
 * Created by Muslimat on 2017-04-08
 *
 */

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    //private TextView txtDetails;
    private EditText inputID, inputPWD;
    private Button btnLogin, btnCancel;
    private DatabaseReference mFDatabase;
    private FirebaseDatabase mFInstance;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("ROBOTBUCKS");
        setContentView(R.layout.login_activity);

        // Displaying toolbar icon
        inputID = (EditText) findViewById(R.id.editText_ID);
        inputPWD = (EditText) findViewById(R.id.editText_PWD);
        btnLogin = (Button) findViewById(R.id.login_button);
        btnCancel = (Button) findViewById(R.id.cancel);

        mFInstance = FirebaseDatabase.getInstance();
        // get reference to 'users' node
        mFDatabase = mFInstance.getReference("users");
        //store app title to 'app_title' node
        mFInstance.getReference("app_title").setValue("Coffee Please");
        // Login the user
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = inputID.getText().toString();
                String pwd = inputPWD.getText().toString();
                //Check for already existing userId
                if (TextUtils.isEmpty(userId)) {
                    createUser(id, pwd);
                } else {
                    updateUser(id, pwd);
                }
                Intent intent = new Intent(getApplicationContext(), StoreActivity.class);
                startActivity(intent);
            }
        });
        // toggleButton();

        btnCancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), StoreActivity.class);
                startActivity(intent);
            }
        });

    }

    // changing button text
    private void toggleButton() {
     /*   if (TextUtils.isEmpty(userId)) {
            btnLogin.setText("Login");
        } else {
        //    btnLogin.setText("registered");

        }*/

    }

    /**
     * Creating new user node under 'users'
     */
    private void createUser(String id, String pwd) {
        // In real apps this userId should be fetched
        // by implementing FireBase auth
        if (TextUtils.isEmpty(userId)) {
            userId = mFDatabase.push().getKey();
        }
        UserInfo user = new UserInfo(id, pwd);
        mFDatabase.child(userId).setValue(user);
        addUserChangeListener();
    }

    /**
     * User data change Listener
     */
    private void addUserChangeListener() {
        // User data change Listener
        mFDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserInfo user = dataSnapshot.getValue(UserInfo.class);
                // Check for null
                if (user == null) {
                    Log.e(TAG, "User data is null");
                    return;
                }
                Log.e(TAG, "User data is changed!" + user.id + ", " + user.pwd);
                // clear edit text
                inputID.setText("");
                inputPWD.setText("");

                //toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", databaseError.toException());
            }
        });
    }
    private void updateUser(String id, String pwd){
        // updating the user via child nodes
        if(!TextUtils.isEmpty(id))
            mFDatabase.child(userId).child("id").setValue(id);
        if(!TextUtils.isEmpty(pwd))
            mFDatabase.child(userId).child("pwd").setValue(pwd);
    }


}