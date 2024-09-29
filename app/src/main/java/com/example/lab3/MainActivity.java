package com.example.lab3;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn;
    Button btn2;
    Button btn3;
    Button btn4;

    protected void showToast(String text, int length, int gravity) {
        Toast toast = Toast.makeText(getApplicationContext(), text, length);
        toast.setGravity(gravity, 0, 0);
        toast.show();
    }

    DialogInterface.OnClickListener YesListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
            btn.setTextColor(Color.RED);
            btn2.setTextColor(Color.RED);
            btn3.setTextColor(Color.RED);
            btn4.setTextColor(Color.RED);
        }
    };

    DialogInterface.OnClickListener NoListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
            showToast("Сообщение закрыто", Toast.LENGTH_SHORT, Gravity.BOTTOM);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Кнопка номер 1 нажата", Toast.LENGTH_SHORT, Gravity.BOTTOM);
            }
        });

        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("Кнопка номер 2 нажата", Toast.LENGTH_LONG, Gravity.TOP);
            }
        });

        btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Кнопка 3")
                        .setIcon(R.drawable.test_icon)
                        .setMessage("Да?")
                        .setPositiveButton("Да", YesListener)
                        .setCancelable(false)
                        .setNegativeButton("Нет", NoListener);

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btn4 = findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] tanks = {"Т-34", "ИС-3", "Т-10"};
                boolean[] tanks_c = {false, false, false};

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Какой танк находится на 5 уровне в World of Tanks")
                        .setPositiveButton("Принять", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                                if (tanks_c[0] && !tanks_c[1] && !tanks_c[2]) {
                                    showToast("Всё верно", Toast.LENGTH_SHORT, Gravity.CENTER);
                                    btn.setVisibility(View.VISIBLE);
                                    btn2.setVisibility(View.VISIBLE);
                                    btn3.setVisibility(View.VISIBLE);
                                }
                                else {
                                    btn.setVisibility(View.INVISIBLE);
                                    btn2.setVisibility(View.INVISIBLE);
                                    btn3.setVisibility(View.INVISIBLE);
                                }
                            }
                        })
                        .setCancelable(false)
                        .setMultiChoiceItems(tanks, tanks_c, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                tanks_c[i] = b;
                            }
                        });


                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}