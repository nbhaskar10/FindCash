package sdkcash.findcash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Bhaskar on 8/14/17.
 */

public class Matched_Request_Info extends AppCompatActivity {

    private String name;
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matched_request_info);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                name = null;
                email = null;
            } else {
                name = extras.getString("name");
                email = extras.getString("email");
            }
        }

        TextView textViewName = (TextView)findViewById(R.id.name);
        textViewName.setTextIsSelectable(true);

        TextView textViewEmail=(TextView)findViewById(R.id.email);
        textViewEmail.setTextIsSelectable(true);
        SpannableString emailstring = new SpannableString("");
        if (email != null) {
            emailstring = new SpannableString(email);
            emailstring.setSpan(new UnderlineSpan(), 0, emailstring.length(), 0);
        }
        textViewEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    //Do your work
                    Intent emailintent = new Intent(android.content.Intent.ACTION_SEND);
                    emailintent.setType("plain/text");
                    emailintent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] {email});
                    emailintent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                    emailintent.putExtra(android.content.Intent.EXTRA_TEXT,"");
                    startActivity(Intent.createChooser(emailintent, "Send mail..."));
                }

            }
        });

        textViewName.setText(name);
        textViewEmail.setText(emailstring);

    }
}