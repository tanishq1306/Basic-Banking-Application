package com.example.basicbankingapp.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basicbankingapp.DB.TransactionContract;
import com.example.basicbankingapp.DB.TransactionHelper;
import com.example.basicbankingapp.R;

public class UserData extends AppCompatActivity {

    TextView name, email, accountNo, balance, ifscCode, phoneNo;
    Button transferMoney;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email_id);
        accountNo = findViewById(R.id.account_no);
        balance = findViewById(R.id.avail_balance);
        ifscCode = findViewById(R.id.ifsc_id);
        phoneNo = findViewById(R.id.phone_no);
        transferMoney = findViewById(R.id.transfer_money);

        // Getting the intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        // Extracting the data
        if (extras != null){
            name.setText(extras.getString("NAME"));
            accountNo.setText(String.valueOf(extras.getInt("ACCOUNT_NO")));
            email.setText(extras.getString("EMAIL"));
            phoneNo.setText(extras.getString("PHONE_NO"));
            ifscCode.setText(extras.getString("IFSC_CODE"));
            balance.setText(extras.getString("BALANCE"));
        }
        else {
            Log.d("TAG", "Empty Intent");
        }

        transferMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterAmount();
            }
        });
    }

    private void enterAmount() {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(UserData.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_box, null);
        mBuilder.setTitle("Enter Amount").setView(mView).setCancelable(false);

        final EditText mAmount = (EditText) mView.findViewById(R.id.enter_money);
        mBuilder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { }
                                    }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                transactionCancel();
            }
        });

        dialog = mBuilder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Checking whether amount entered is correct or not
                int currentBalance = Integer.parseInt(String.valueOf(balance.getText()));

                if (mAmount.getText().toString().isEmpty()) {
                    mAmount.setError("Amount can't be empty");
                } else if (Integer.parseInt(mAmount.getText().toString()) > currentBalance){
                    mAmount.setError("Your account don't have enough balance");
                } else {
                    Intent intent = new Intent(UserData.this, SendToUserList.class);
                    intent.putExtra("FROM_USER_ACCOUNT_NO", Integer.parseInt(accountNo.getText().toString()));    // PRIMARY_KEY
                    intent.putExtra("FROM_USER_NAME", name.getText());
                    intent.putExtra("FROM_USER_ACCOUNT_BALANCE", balance.getText());
                    intent.putExtra("TRANSFER_AMOUNT", mAmount.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void transactionCancel() {
        AlertDialog.Builder builder_exitbutton = new AlertDialog.Builder(UserData.this);
        builder_exitbutton.setTitle("Do you want to cancel the transaction?").setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(UserData.this, "Transaction Cancelled!", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                enterAmount();
            }
        });
        AlertDialog alertexit = builder_exitbutton.create();
        alertexit.show();
    }
}