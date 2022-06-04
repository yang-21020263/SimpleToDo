package sg.edu.rp.c346.id21020263.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner options;
    Button add;
    Button delete;
    Button clear;
    EditText etTask;
    ListView toDoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        options = findViewById(R.id.spinner);
        add = findViewById(R.id.buttonAdd);
        delete = findViewById(R.id.buttonDelete);
        clear = findViewById(R.id.buttonClear);
        etTask = findViewById(R.id.editTextTask);
        toDoList = findViewById(R.id.listViewTasks);

        //arraylist
        ArrayList<String> alTasks = new ArrayList<String>();

        //
        ArrayAdapter aaTasks = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alTasks);

        //
        toDoList.setAdapter(aaTasks);

        options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        delete.setEnabled(false);
                        add.setEnabled(true);
                        etTask.setHint("Type in a new task here");
                        break;
                    case 1:
                        add.setEnabled(false);
                        delete.setEnabled(true);
                        etTask.setHint("Type in the index of the task to be removed");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting input from editText
                String task = etTask.getText().toString();

                //adding string to arraylist
                alTasks.add(task);

                //updating viewList
                aaTasks.notifyDataSetChanged();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //checking for if input is empty or doesn't exist
                 if (etTask.getText().toString().isEmpty() ) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                } else if (Integer.parseInt(etTask.getText().toString()) > alTasks.size()) {
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                } else {
                    //getting index number from editText
                    String task = etTask.getText().toString();
                    int pos = Integer.parseInt(etTask.getText().toString());

                    //removing element from in the arraylist
                    alTasks.remove(pos);
                }
                //updating listView
                aaTasks.notifyDataSetChanged();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                alTasks.clear();
                aaTasks.notifyDataSetChanged();
            }
        });

    }
}