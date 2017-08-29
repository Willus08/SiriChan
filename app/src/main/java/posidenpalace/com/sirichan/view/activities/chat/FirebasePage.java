package posidenpalace.com.sirichan.view.activities.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import posidenpalace.com.sirichan.R;

public class FirebasePage extends AppCompatActivity {

    private static final String TAG = "FirebasePage";
    //https://facebooktwitterfirebase.firebaseio.com/
    private FirebaseAuth firebaseAuth;
    private TextView tvMessages;
    private DatabaseReference dbaseRoot;
    private String userName;
    private EditText etMessage;
    private String chatRoomName;
    private String tempKey;
    boolean firstRun = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_page);



        tvMessages = (TextView) findViewById(R.id.tvMessages);
        etMessage = (EditText) findViewById(R.id.etMessage);

        Log.d(TAG, "onCreate: create Chat");
        if (firstRun){
            String voice = getIntent().getStringExtra("voice");
            Toast.makeText(this, voice, Toast.LENGTH_SHORT).show();
            etMessage.setText(voice);
            firstRun= false;
        }
        firebaseAuth = FirebaseAuth.getInstance();
        userName = getIntent().getExtras().get("userName").toString();
        chatRoomName = getIntent().getExtras().get("chatRoomName").toString();
        setTitle("Room - " + chatRoomName);
        dbaseRoot = FirebaseDatabase.getInstance().getReference().child(chatRoomName);
        dbaseRoot.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                showMessage(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                showMessage(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showMessage(DataSnapshot dataSnapshot) {
        Iterator i = dataSnapshot.getChildren().iterator();
        String chatMessage, userName;
        while(i.hasNext()){
            chatMessage = (String)((DataSnapshot)i.next()).getValue();
            userName = (String)((DataSnapshot)i.next()).getValue();
            tvMessages.append(userName + " : " + chatMessage + "\n");
        }
    }

    public void postToFirebase(View view) {
        Map<String, Object> map1 = new HashMap<>();
        tempKey = dbaseRoot.push().getKey();
        dbaseRoot.updateChildren(map1);

        DatabaseReference messageRoot = dbaseRoot.child(tempKey);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", userName);
        map2.put("message", etMessage.getText().toString());
        messageRoot.updateChildren(map2);
        etMessage.setText("");
    }

}
