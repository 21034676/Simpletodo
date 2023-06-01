package sg.edu.rp.c346.simpletodochallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etNewTodo;
    Button btnAdd;
    Button btnClearAll;
    ListView lvTodo;

    ArrayList<String> alTodo;
    ArrayAdapter<String> aaTodo;

    //Enhancement
    Spinner spinner;
    Button btnDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Simple To Do");

        etNewTodo = findViewById(R.id.editNewTodo);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnClearAll = findViewById(R.id.buttonClearItem);
        lvTodo = findViewById(R.id.listViewTodo);

        //Enhancement
        spinner = findViewById(R.id.spinner);
        btnDel = findViewById(R.id.buttonDelItem);

        alTodo = new ArrayList<>();

        aaTodo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTodo);

        lvTodo.setAdapter(aaTodo);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTodo = etNewTodo.getText().toString();
                alTodo.add(newTodo);
                aaTodo.notifyDataSetChanged();
                etNewTodo.setText("");
            }
        });

        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTodo.clear();
                aaTodo.notifyDataSetChanged();
            }
        });


        //Enhancement
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alTodo.size() == 0) {
                    Toast.makeText(getApplicationContext(), "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    int index = Integer.parseInt(etNewTodo.getText().toString());
                    if (alTodo.size() <= index) {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        alTodo.remove(index);
                        aaTodo.notifyDataSetChanged();
                        etNewTodo.setText(null);
                    }
                }
            }
        });

        //Enhancement
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etNewTodo.setHint(R.string.hintAdd);
                        btnAdd.setEnabled(true);
                        btnDel.setEnabled(false);
                        break;
                    case 1:
                        etNewTodo.setHint(R.string.hintDel);
                        btnAdd.setEnabled(false);
                        btnDel.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}