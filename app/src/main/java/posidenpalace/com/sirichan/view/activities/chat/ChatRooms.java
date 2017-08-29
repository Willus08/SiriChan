package posidenpalace.com.sirichan.view.activities.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import posidenpalace.com.sirichan.R;

public class ChatRooms extends AppCompatActivity {

    private static final String TAG = "ChatRooms";
    //https://facebooktwitterfirebase.firebaseio.com/
    private FirebaseAuth firebaseAuth;
    private DatabaseReference dbaseRoot;
    private EditText etChatName;
    private ListView chatListView;
    private FirebaseUser currentUser;
    private ArrayList<String> listOfChatRooms = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    private String userEmail;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_rooms);

        chatListView = (ListView) findViewById(R.id.chatListView);
        etChatName = (EditText) findViewById(R.id.etChatName);
        firebaseAuth = FirebaseAuth.getInstance();
        dbaseRoot = FirebaseDatabase.getInstance().getReference();

        currentUser = firebaseAuth.getCurrentUser();
        userEmail = currentUser.getEmail();
        String[] userNameArray = userEmail.split("@");
        userName = userNameArray[0];

        dbaseRoot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue()!=null){
                    Set<String> set = new HashSet<>();
                    Iterator i = dataSnapshot.getChildren().iterator();

                    while(i.hasNext()){
                        set.add(((DataSnapshot)i.next()).getKey());
                    }
                    listOfChatRooms.clear();
                    listOfChatRooms.addAll(set);
                    arrayAdapter.notifyDataSetChanged();
                    String voice = getIntent().getStringExtra("voice");
                    Toast.makeText(ChatRooms.this, voice, Toast.LENGTH_SHORT).show();

                    if(voice != null&& listOfChatRooms.size()>0){
                        for (int j = 0; j < listOfChatRooms.size() ; j++) {
                            if (voice.startsWith(listOfChatRooms.get(j).toLowerCase())){
                                Intent intent = new Intent(ChatRooms.this,FirebasePage.class);
                                intent.putExtra("chatRoomName", listOfChatRooms.get(j));
                                intent.putExtra("userName", userName);
                                intent.putExtra("voice", voice.substring(listOfChatRooms.get(j).length()));
                                startActivity(intent);
                                Toast.makeText(ChatRooms.this, "Going to: " + listOfChatRooms.get(j), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: " + databaseError.toString());
            }
        });



        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfChatRooms);
        chatListView.setAdapter(arrayAdapter);

        chatListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChatRooms.this, FirebasePage.class);
                intent.putExtra("chatRoomName", ((TextView)view).getText().toString());
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });
    }

    public void createChat(View view) {
        Map<String, Object> map = new HashMap<>();
        map.put(etChatName.getText().toString(),"");
        dbaseRoot.updateChildren(map);
        etChatName.setText("");
    }

}
