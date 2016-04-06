package com.example.michaelli.squadup;

import android.app.DialogFragment;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.bailey.mobile.squadup.R;

/*
public class mainEmail extends DialogFragment {

    public static mainEmail newInstance() {
        mainEmail frag = new mainEmail();
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.email, container);
        Button shareButton = (Button) view.findViewById(R.id.shareButton);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), mainEmail.class); //Add the summary class
                startActivity(i);
            }
        });

*/

public class mainEmail extends Activity {
    private EditText recipient;
    private EditText subject;
    private EditText body;
    private DictionaryHelper dictionaryHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        dictionaryHelper = new DictionaryHelper(this);
        recipient = (EditText) findViewById(R.id.recipient);
        subject = (EditText) findViewById(R.id.subject);
        body = (EditText) findViewById(R.id.body);
        body.append("Wow! What an awesome match!" + "");
        Button sendBtn = (Button) findViewById(R.id.sendEmail);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
                recipient.setText("");
                subject.setText("");
                body.setText("");
            }
        });

    }
    protected void sendEmail() {
        SQLiteDatabase db = dictionaryHelper.getReadableDatabase();
        int gameID = getIntent().getExtras().getInt("gameID");
        Cursor cursor = db.rawQuery("select * from Games where _id="+gameID, null);
        String gameInfo = DatabaseUtils.dumpCursorToString(cursor);
        String[] recipients = {recipient.getText().toString()};
        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, recipients);
        email.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
        email.putExtra(Intent.EXTRA_TEXT, body.getText().toString() + "\n\n\n" + gameInfo);

        try {
            startActivity(Intent.createChooser(email, "Choose an email client from..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(mainEmail.this, "No email client installed.",
                    Toast.LENGTH_LONG).show();
        }
    }

}