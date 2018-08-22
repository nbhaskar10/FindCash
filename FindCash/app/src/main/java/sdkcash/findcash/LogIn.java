package sdkcash.findcash;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class LogIn extends AppCompatActivity {

    Button login;
    ColorDrawable loginC;
    ViewGroup.LayoutParams loginL;
    Button register;
    ColorDrawable registerC;
    ViewGroup.LayoutParams registerL;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "LogIn";
    private DatabaseReference mDatabase;

    ViewGroup.LayoutParams loginLS;
    ViewGroup.LayoutParams registerLS;

    ViewGroup toBeFilledLogIn;
    ViewGroup toBeFilledSignUp;

    EditText username;
    EditText password;
    EditText email;
    EditText confirmPass;
    EditText name;

    String name1;
    //Create toasts
    private int duration = Toast.LENGTH_SHORT;
    private Toast blankUserInput;
    private Toast blankPassInput;
    private Toast blankNameInput;
    private Toast blankEmailInput;
    private Toast unmatchedPasswords;

    //Constructor Login page
    public LogIn() {
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        toBeFilledLogIn = (ViewGroup) findViewById(R.id.tofill);
        toBeFilledSignUp = (ViewGroup) findViewById(R.id.tofill2);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater2 = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.login, toBeFilledLogIn);
        inflater2.inflate(R.layout.signup, toBeFilledSignUp);
        toBeFilledSignUp.setVisibility(View.INVISIBLE);
        toBeFilledLogIn.setVisibility(View.VISIBLE);

        loginLS = toBeFilledLogIn.getLayoutParams();
        registerLS = toBeFilledSignUp.getLayoutParams();

        login = (Button) findViewById(R.id.login);
        loginC = (ColorDrawable) login.getBackground();
        loginL = login.getLayoutParams();
        register = (Button) findViewById(R.id.register);
        registerC = (ColorDrawable) register.getBackground();
        registerL = register.getLayoutParams();


        //Instantiate toasts
        blankUserInput = Toast.makeText(this, "Must enter a Username", duration);
        blankPassInput = Toast.makeText(this, "Must enter a Password", duration);
        blankNameInput = Toast.makeText(this, "Must enter a Name", duration);
        blankEmailInput = Toast.makeText(this, "Must enter an Email", duration);
        unmatchedPasswords = Toast.makeText(this, "Passwords do not match", duration);

    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    void Login(View v) {
        //get data (username, password, ect)
        username = (EditText) findViewById(R.id.email_login);
        String user1 = username.getText().toString();
        password = (EditText) findViewById(R.id.password_login);
        String pass1 = password.getText().toString();


        if (user1.isEmpty()) {       // if something is empty show a toast
            blankUserInput.show();
        } else if (pass1.isEmpty()) {
            blankPassInput.show();
        } else {                     //else if not empty
            mAuth.signInWithEmailAndPassword(user1, pass1)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail", task.getException());
                                Toast.makeText(LogIn.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            else{
                                username.setText("");
                                password.setText("");
                                moveToYourRequests();
                            }
                        }
                    });

        }
    }//End Login

    void SignUp(View v) {
        //get data (username, password, ect)
        name = (EditText) findViewById(R.id.name_signup);
        name1 = name.getText().toString();
        email = (EditText) findViewById(R.id.email_signup);
        String email1 = email.getText().toString();
        password = (EditText) findViewById(R.id.password_signup);
        String pass1 = password.getText().toString();
        confirmPass = (EditText) findViewById(R.id.confirmpass_signup);
        String confirmPass1 = confirmPass.getText().toString();


        if (name1.isEmpty()) {// if something is empty show a toast
            blankNameInput.show();
        } else if (email1.isEmpty()) {
            blankEmailInput.show();
        }  else if (pass1.isEmpty()) {
            blankPassInput.show();
        } else if (confirmPass1.isEmpty()) {
            blankPassInput.show();
        } else if (!pass1.equals(confirmPass1)) {// if passwords do not match show a toast
            unmatchedPasswords.show();
        }else{
            //else if not empty and match
            mAuth.createUserWithEmailAndPassword(email1, pass1)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(LogIn.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                            else{
                                newUser(task.getResult().getUser(), name1);

                                password.setText("");
                                email.setText("");
                                name.setText("");
                                confirmPass.setText("");
                                moveToYourRequests();
                            }
                        }
                    });

        }
    }//End SignUp

    //if auth is success, write new user, start next activity
    private void newUser(FirebaseUser user, String name){
        User myUser=new User(name, user.getEmail());
        mDatabase.child("users").child(user.getUid()).setValue(myUser);

    }

    private void moveToYourRequests(){

        Intent intent = new Intent(LogIn.this, Your_Requests.class);
        //add data to intent
        startActivity(intent);
        finish();
    }


    void Switch(View v) {
        //TODO SWITCH FRAGMENTS
        if (v.getTag().equals("login")) {
            login.setBackground(loginC);
            login.setLayoutParams(loginL);
            register.setBackground(registerC);
            register.setLayoutParams(registerL);
            toBeFilledLogIn.setVisibility(View.VISIBLE);
            toBeFilledSignUp.setVisibility(View.INVISIBLE);

            toBeFilledSignUp.setLayoutParams(registerLS);
            toBeFilledLogIn.setLayoutParams(loginLS);
        } else {
            login.setBackground(registerC);
            login.setLayoutParams(registerL);
            register.setBackground(loginC);
            register.setLayoutParams(loginL);
            toBeFilledLogIn.setVisibility(View.INVISIBLE);
            toBeFilledSignUp.setVisibility(View.VISIBLE);

            toBeFilledSignUp.setLayoutParams(loginLS);
            toBeFilledLogIn.setLayoutParams(registerLS);
        }
    }
}
