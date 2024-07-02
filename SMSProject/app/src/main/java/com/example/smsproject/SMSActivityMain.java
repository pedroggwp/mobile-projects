package com.example.smsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMSActivityMain extends AppCompatActivity {

    private EditText numPhone, message;
    private Button sendSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_activity_main);

        numPhone = findViewById(R.id.num_phone);
        message = findViewById(R.id.msg);
        sendSms = findViewById(R.id.btn_send_sms);

        sendSms.setOnClickListener(view -> {

            // preparando para rodar segundo plano o SMS
            Intent intent = new Intent(getApplicationContext(), SMSActivityMain.class);

            PendingIntent pendingIntent = PendingIntent.getActivity
                    (getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);

            // criando o gerenciador de SMS
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(numPhone.getText().toString(), null, message.getText().toString(), pendingIntent, null);

            Toast.makeText(SMSActivityMain.this, "Foi!!", Toast.LENGTH_SHORT).show();
        });


    }
}