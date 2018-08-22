package sdkcash.findcash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Bhaskar on 2/12/17.
 */

public class New_Request extends AppCompatActivity{
    private Button submitButton;
    private EditText deptAirport;
    private EditText deptTime;
    private EditText arrAirport;
    private EditText arrTime;
    private EditText currOffering;
    private EditText amtOffering;
    private EditText currWanted;
    private EditText amtWanted;
    private EditText meetLoc;

    private String fetchedDeptApt;
    private String fetchedDeptTime;
    private String fetchedArrApt;
    private String fetchedArrTime;
    private String fetchedCurrOffering;
    private String fetchedAmtOffering;
    private String fetchedCurrWanted;
    private String fetchedAmtWanted;
    private String fetchedMeetLoc;


    private Current_Requests_Book newBook;
    private DatabaseReference mDatabase;
    //db.addBook(new Current_Requests_Book("$250",
    //"JFK", "LHR", "USD", "GBP", "12:00am", "2:00pm", "Terminal 5", "2"));

    //( String moneyAmount, String airportFrom, String airportTo,
    //String currH, String currW, String timeA, String timeD,
    //String meetingLocation, String id)
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
        } else {
            // No user is signed in
        }
            super.onCreate(savedInstanceState);
            setContentView(R.layout.create_new_request);
            mDatabase = FirebaseDatabase.getInstance().getReference();
            submitButton=(Button) findViewById(R.id.submitButton);
            submitButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    deptAirport = (EditText) findViewById(R.id.deptAirport);
                    deptTime=(EditText) findViewById(R.id.deptTime);
                    arrAirport = (EditText) findViewById(R.id.arrAirport);
                    arrTime=(EditText) findViewById(R.id.ArrTime);
                    currOffering = (EditText) findViewById(R.id.currOffering);
                    amtOffering = (EditText) findViewById(R.id.amtOffering);
                    currWanted = (EditText) findViewById(R.id.currWanted);
                    amtWanted = (EditText) findViewById(R.id.amtWanted);
                    meetLoc=(EditText) findViewById(R.id.meetloc);

                    fetchedDeptApt=deptAirport.getText().toString();
                    fetchedDeptTime=deptTime.getText().toString();
                    fetchedArrApt=arrAirport.getText().toString();
                    fetchedArrTime=arrTime.getText().toString();
                    fetchedCurrOffering=currOffering.getText().toString();
                    fetchedAmtOffering=amtOffering.getText().toString();
                    fetchedCurrWanted=currWanted.getText().toString();
                    fetchedAmtWanted=amtWanted.getText().toString();
                    fetchedMeetLoc=meetLoc.getText().toString();


                    deptAirport.setText("");
                    deptTime.setText("");
                    arrAirport.setText("");
                    arrTime.setText("");
                    currOffering.setText("");
                    amtWanted.setText("");
                    amtOffering.setText("");
                    currWanted.setText("");
                    meetLoc.setText("");

                    newBook=new Current_Requests_Book(user.getUid(), fetchedAmtOffering, fetchedAmtWanted, fetchedDeptApt, fetchedArrApt,
                            fetchedCurrOffering, fetchedCurrWanted, fetchedArrTime, fetchedDeptTime, fetchedMeetLoc);
                    mDatabase.child("requests").push().setValue(newBook);

                    Intent intent = new Intent(New_Request.this, Your_Requests.class);
                    startActivity(intent);
                }
            });



        }

}
