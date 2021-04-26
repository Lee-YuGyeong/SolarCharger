package com.market.solarcharger;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CableAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarInit();

        recyclerViewInit();


    }

    public void toolbarInit() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
    }

    public void recyclerViewInit() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CableAdapter(getApplicationContext());

        adapter.addItem(new CableItem(R.drawable.phone_blue, R.drawable.circle_blue, R.drawable.blue1));
        adapter.addItem(new CableItem(R.drawable.phone_gray, R.drawable.circle_gray, R.drawable.gray2));
        adapter.addItem(new CableItem(R.drawable.phone_gray, R.drawable.circle_gray, R.drawable.gray3));

        recyclerView.setAdapter(adapter);

        int spacingInPixels = 30;
        recyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        adapter.setOnItemClickListener(new CableAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(CableAdapter.ViewHolder holder, View view, int position) {
                CableItem item = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "선택됨 : " + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.peopleButton:
                checkManager();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void checkManager() {
        final EditText txtEdit = new EditText(MainActivity.this);
        txtEdit.setRawInputType(InputType.TYPE_CLASS_NUMBER);
        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setTitle("관리자 모드");
        dlg.setMessage("관리자 모드를 실행하려면 패스워드를 입력하세요.");
        dlg.setView(txtEdit);
        dlg.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String str = txtEdit.getText().toString();
                        if (Integer.parseInt(str) == 1234) {
                            Toast.makeText(getApplicationContext(), "관리자 모드로 변경되었습니다.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "비밀번호를 다시 입력해주세요.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        dlg.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dlg.show();

    }

}