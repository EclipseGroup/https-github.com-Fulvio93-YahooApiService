package com.example.fulvio.myapplication;

import android.app.ProgressDialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements FinanceServiceCallback, View.OnClickListener {

    private TextView tvSymbol;
    private TextView tvName;
    private TextView tvDaysLow;
    private TextView tvDaysHigh;
    private TextView tvLastTrade;
    private EditText etSymbol;
    private Button btnGo;

    private YahooFinanceService service;
    private ProgressDialog dialog;

    private String txtSymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSymbol = (TextView) findViewById(R.id.tvSymbol);
        tvName = (TextView) findViewById(R.id.tvName);
        tvDaysLow = (TextView) findViewById(R.id.tvDaysLow);
        tvDaysHigh = (TextView) findViewById(R.id.tvDaysHigh);
        tvLastTrade = (TextView) findViewById(R.id.tvLastTrade);
        etSymbol = (EditText) findViewById(R.id.etSymbol);
        btnGo = (Button) findViewById(R.id.btnGo);

        btnGo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String strSymbol = etSymbol.getText().toString();
        etSymbol.setText("");
        service = new YahooFinanceService(this, getApplicationContext());
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        service.refreshQuote(strSymbol);
    }

    @Override
    public void serviceSuccess(Quote quote) {
        dialog.hide();

        tvSymbol.setText(quote.getSymbol());
        tvName.setText(quote.getName());
        tvDaysLow.setText(quote.getDaysLow());
        tvDaysHigh.setText(quote.getDaysHigh());
        tvLastTrade.setText(quote.getLastTrade());
    }

    @Override
    public void serviceFailure(Exception exception) {
        dialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}
