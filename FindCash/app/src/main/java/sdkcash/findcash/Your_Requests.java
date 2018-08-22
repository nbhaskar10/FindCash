package sdkcash.findcash;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Your_Requests extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    ArrayList<Current_Requests_Book> myRequests=new ArrayList<>();
    String myUid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_your__requests);
        myUid=mAuth.getCurrentUser().getUid();

        //query firebase db, create an arraylist of current request books that are mine, based on above id
        mDatabase.child("requests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myRequests.clear();
                for (DataSnapshot dataSnap: dataSnapshot.getChildren()) {
                    Current_Requests_Book crb = dataSnap.getValue(Current_Requests_Book.class);
                    if(crb.getMyID().equals(myUid))
                        myRequests.add(crb);
                }
                listViewSetup(myRequests);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Your_Requests", "loadPost: onCancelled", databaseError.toException());
            }
        });
    }
    public void listViewSetup(ArrayList<Current_Requests_Book> reqList){
        ListView listView = (ListView)findViewById(R.id.listView1);
        Current_Requests_Adapter threeHorizontalTextViewsAdapter = new Current_Requests_Adapter(this, R.layout.single_request, myRequests);
        listView.setAdapter(threeHorizontalTextViewsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Current_Requests_Book item =(Current_Requests_Book) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(Your_Requests.this,Request_Click.class);
                intent.putExtra("currHave", item.getCurrenyHave());
                intent.putExtra("currWant", item.getCurrenyWant());
                intent.putExtra("arrApt", item.getAirportTo());
                //based on item add info to intent
                startActivity(intent);

            }

        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                final Current_Requests_Book book =(Current_Requests_Book) adapterView.getItemAtPosition(position);
                //db.deleteBook(book.getId());
                startActivity(new Intent(Your_Requests.this, Your_Requests.class));
                //TODO show dialog before deleteing
                return true;
            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_request_menu, menu);
        inflater.inflate(R.menu.sign_out, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        System.out.println(item.getTitle());
        if (item.getTitle().equals("New Request")) {
            Intent intent = new Intent(Your_Requests.this, New_Request.class);
            startActivity(intent);
        }

        else if(item.getTitle().equals("Sign Out")){
            Intent intent =new Intent(Your_Requests.this, LogIn.class);
            mAuth.signOut();
            startActivity(intent);
        }
        // Handle item selection
        //setContentView(R.layout.create_new_request);
        return false;
    }

    

}