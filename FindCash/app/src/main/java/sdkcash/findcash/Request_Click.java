package sdkcash.findcash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Bhaskar on 11/13/16.
 */

public class Request_Click extends AppCompatActivity {

    private DatabaseReference mDatabase=FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ArrayList<Current_Requests_Book> matchedRequests=new ArrayList<>();
    String arrApt;
    String currHave;
    String currWant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matched_request);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                arrApt= null;
                currHave=null;
                currWant=null;
            } else {
                arrApt= extras.getString("arrApt");
                currHave=extras.getString("currHave");
                currWant=extras.getString("currWant");
            }
        } else {
            arrApt= (String) savedInstanceState.getSerializable("arrApt");
        }

        final String myUid=mAuth.getCurrentUser().getUid();
        mDatabase.child("requests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnap: dataSnapshot.getChildren()) {
                    Current_Requests_Book crb = dataSnap.getValue(Current_Requests_Book.class);
                    if(!(crb.getMyID().equals(myUid))) {
                        if((crb.getAirportTo().equals(arrApt))||(crb.getAirportFrom().equals(arrApt))) {
                            if ((crb.getCurrenyHave().equals(currWant)) && (crb.getCurrenyWant().equals(currHave)))
                                matchedRequests.add(crb);
                        }
                    }
                }
                listViewSetup(matchedRequests);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Your_Requests", "loadPost: onCancelled", databaseError.toException());
            }
        });

    }

    private void listViewSetup(ArrayList<Current_Requests_Book> matchedReqs){
        ListView listView = (ListView)findViewById(R.id.listView1);
        Current_Requests_Adapter threeHorizontalTextViewsAdapter = new Current_Requests_Adapter(this, R.layout.single_request, matchedReqs);
        listView.setAdapter(threeHorizontalTextViewsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Current_Requests_Book item =(Current_Requests_Book) adapterView.getItemAtPosition(position);
                String reqUID=item.getMyID();
                System.out.println(reqUID);
                mDatabase.child("users").child(reqUID).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                            User user = dataSnapshot.getValue(User.class);
                            System.out.println(user.getName());
                            matchedReqInfoIntent(user);

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("Your_Requests", "loadPost: onCancelled", databaseError.toException());
                }
                });
            }

        });
    }

    private void matchedReqInfoIntent(User user){
        Intent intent = new Intent(Request_Click.this, Matched_Request_Info.class);
        intent.putExtra("name", user.getName());
        intent.putExtra("email", user.getEmail());

        //based on item add info to intent
        startActivity(intent);

    }


}
