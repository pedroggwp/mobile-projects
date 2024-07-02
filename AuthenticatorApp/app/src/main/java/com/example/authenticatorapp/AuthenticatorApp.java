package com.example.authenticatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class AuthenticatorApp extends AppCompatActivity {

    EditText name, email, phone, password;
    Random random;
    int generatedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticator_app);

        name = findViewById(R.id.input_name);
        email = findViewById(R.id.input_email);
        phone = findViewById(R.id.input_phone);

        random = new Random();
    }

    public void sendSMS(View view) {
        generatedPassword = random.nextInt(99999);

        String phoneNumber = phone.getText().toString().trim();

        if (phoneNumber.isEmpty()) {
            return;
        } else if (!isActivityRunning()) {
//            Intent intent2 = new Intent(getApplicationContext().toString());
            Intent intent = new Intent(getApplicationContext(), AuthenticatorApp.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        // criando o gerenciador de SMS
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phone.getText().toString(), null, String.valueOf(generatedPassword), null, null);
    }

    public void sendEmail(View view) {
        generatedPassword = random.nextInt(99999);

        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {email.getText().toString()});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Authenticator code");
        intent.putExtra(Intent.EXTRA_TEXT, String.valueOf(generatedPassword));
        startActivity(intent);
    }

    // Método para verificar se a atividade está em execução
    private boolean isActivityRunning() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

        for (ActivityManager.RunningTaskInfo task : tasks) {
            if (task.topActivity.getClassName().equals(AuthenticatorApp.class.getName())) {
                return true;
            }
        }
        return false;
    }


    public void confirmPassword(View view) {
        password = findViewById(R.id.input_password);

        if (password.getText().toString().equals(String.valueOf(generatedPassword))) {
            Bundle envelopData = new Bundle();
            envelopData.putString("name", name.getText().toString());


            Intent intent = new Intent(this, WelcomeApp.class);
            intent.putExtras(envelopData);
            startActivity(intent);
        } else {
            Toast.makeText(AuthenticatorApp.this,
                    "Authenticator code incorrect!", Toast.LENGTH_SHORT).show();
        }
    }
}
