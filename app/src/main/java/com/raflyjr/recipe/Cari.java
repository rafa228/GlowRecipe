package com.raflyjr.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Cari extends AppCompatActivity implements View.OnClickListener {

    private String KEY_QUERY = "EGG";
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari);

        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

@Override
public boolean onSupportNavigateUp() {
    finish();
    return true;
}


@Override
public void onClick(View view) {
    switch (view.getId()) {
        case R.id.btn_back:
            finish();
            break;
    }
}

    public void submitCari(View v) {
        try{
            EditText nameField = (EditText) findViewById(R.id.field_find);
            Editable nameEditable = nameField.getText();
            String query = nameEditable.toString();

            if (query != null && query != ""){
                Intent i = new Intent(Cari.this, Recipes.class);
                i.putExtra(KEY_QUERY, query);
                startActivity(i);

            } else {
                Toast.makeText(getApplication(), "YOU NEED TO FILL YOUR QUERY",Toast.LENGTH_SHORT);
            }

        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getApplication(), "ERROR, TRY AGAIN !",Toast.LENGTH_SHORT);
        }

    }

}
