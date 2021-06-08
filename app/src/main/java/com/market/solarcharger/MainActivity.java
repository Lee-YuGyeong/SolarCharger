package com.market.solarcharger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.market.solarcharger.API.APIClient;
import com.market.solarcharger.API.Api;

import android.graphics.Color;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CableAdapter adapter;
    MCableAdapter Madapter;
    Toolbar toolbar;

    private LineChart chart;

    TextView text_weather;
    String msg;
    int image;
    CardView chartLayout;
    CardView powerLayout;
    int m = 0;

    @Override
    protected void onStart() {
        super.onStart();
        getPortInfo();
        getGraphInfo();
        getSolarGraphInfo();
        getMode();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        msg = intent.getStringExtra("weather");
        image = intent.getIntExtra("image", 0);
        text_weather = findViewById(R.id.text_weather);
        text_weather.setText(msg);

        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPortInfo();
                getGraphInfo();
                getSolarGraphInfo();
                getMode();
                refreshLayout.setRefreshing(false);

            }
        });

        ImageButton button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("m", m);
                startActivity(intent);
            }
        });

        recyclerViewInit();

        ImageView imageView = findViewById(R.id.imageView2);
        imageView.setImageResource(image);

        toolbarInit();
        getMode();

        chartLayout = findViewById(R.id.chartLayout);
        powerLayout = findViewById(R.id.powerLayout);

        chart = findViewById(R.id.linechart);

        getGraphInfo();
        getSolarGraphInfo();


        getPortInfo();


    }

    private void getPortInfo() {


        Api Api = APIClient.getClient().create(Api.class);
        Call<PortResponseInfo> call = Api.getPort(1);

        call.enqueue(new Callback<PortResponseInfo>() {
            @Override
            public void onResponse(Call<PortResponseInfo> call, Response<PortResponseInfo> response) {
                if (response.isSuccessful()) {

                    PortResponseInfo portResponseInfo = response.body();
                    List<portDetailInfo> portDetailInfoList = new ArrayList<portDetailInfo>(portResponseInfo.getPortDetailInfoList());

                    adapter.items.clear();
                    Madapter.items.clear();
                    adapter.notifyDataSetChanged();
                    Madapter.notifyDataSetChanged();

                    for (int i = 0; i < portDetailInfoList.size(); i++) {
                        adapter.addItem(new CableItem(portDetailInfoList.get(i).getPort_num(), portDetailInfoList.get(i).getReport(), portDetailInfoList.get(i).getBroken(), portDetailInfoList.get(i).getStatusInfo()));
                        Madapter.addItem(new CableItem(portDetailInfoList.get(i).getPort_num(), portDetailInfoList.get(i).getReport(), portDetailInfoList.get(i).getBroken(), portDetailInfoList.get(i).getStatusInfo()));
                    }

                    adapter.notifyDataSetChanged();
                    Madapter.notifyDataSetChanged();

                } else { //response 실패

                }

            }

            @Override
            public void onFailure(Call<PortResponseInfo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    } // retrofit 데이터 받아오기

    private void getGraphInfo() {


        Api Api = APIClient.getClient().create(Api.class);
        Call<GraphInfo> call = Api.GraphReport(1);

        TextView power = findViewById(R.id.power);


        call.enqueue(new Callback<GraphInfo>() {
            @Override
            public void onResponse(Call<GraphInfo> call, Response<GraphInfo> response) {
                if (response.isSuccessful()) {
                    GraphInfo graphInfo = response.body();
                    List<GraphDetailInfo> graphDetailInfoList = new ArrayList<GraphDetailInfo>(graphInfo.getGraphDetailInfoList());
                    power.setText("   ▶ " + graphDetailInfoList.get(0).getPowerpower()+" Wh");


                } else { //response 실패

                }

            }

            @Override
            public void onFailure(Call<GraphInfo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    } // retrofit 데이터 받아오기


    private void getMode() {


        Api Api = APIClient.getClient().create(Api.class);
        Call<ModeInfo> call = Api.getMode(1);
        TextView textView_mode = findViewById(R.id.textView_mode);

        call.enqueue(new Callback<ModeInfo>() {
            @Override
            public void onResponse(Call<ModeInfo> call, Response<ModeInfo> response) {
                if (response.isSuccessful()) {
                    ModeInfo modeInfo = response.body();
                    List<ModeDetailInfo> modeDetailInfoList = new ArrayList<>(modeInfo.getModeDetailInfoList());

                    int mode = modeDetailInfoList.get(0).getMode();
                    if (mode == 0) {
                        textView_mode.setText("현재 일반 전력 충전 모드입니다.");
                    } else if (mode == 1) {
                        textView_mode.setText("현재 태양광 충전 모드입니다.");
                    } else if (mode == 2) {
                        textView_mode.setText("현재 정전 상태입니다.");
                    }
                } else { //response 실패

                }

            }

            @Override
            public void onFailure(Call<ModeInfo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    } // retrofit 데이터 받아오기


    private void getSolarGraphInfo() {


        Api Api = APIClient.getClient().create(Api.class);
        Call<SolarGraphInfo> call = Api.SolarReport(1);


        call.enqueue(new Callback<SolarGraphInfo>() {
            @Override
            public void onResponse(Call<SolarGraphInfo> call, Response<SolarGraphInfo> response) {
                if (response.isSuccessful()) {
                    SolarGraphInfo solarGraphInfo = response.body();
                    List<SolarGraphDetailInfo> solarGraphDetailInfoList = new ArrayList<>(solarGraphInfo.getSolarGraphDetailInfoList());

                    ArrayList<Entry> values = new ArrayList<>();

                    for (int i = 0; i < solarGraphDetailInfoList.size(); i++) {

                        float val = (float) (solarGraphDetailInfoList.get(i).getSolar());
                        values.add(new Entry(i, val));
                    }
                    LineDataSet set1;
                    set1 = new LineDataSet(values, "DataSet 1");

                    ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                    dataSets.add(set1); // add the data sets

                    // create a data object with the data sets
                    LineData data = new LineData(dataSets);

                    // black lines and points
                    set1.setColor(Color.BLACK);
                    set1.setCircleColor(Color.BLACK);

                    // set data
                    chart.setData(data);

                } else { //response 실패

                }

            }

            @Override
            public void onFailure(Call<SolarGraphInfo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    } // retrofit 데이터 받아오기


    private void getNotPortInfo(int num) {

        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setTitle("정상 작동");
        dlg.setMessage("정상 작동 상태로 복구하시겠습니까?");
        dlg.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Api Api = APIClient.getClient().create(Api.class);
                        Call<ReportResponseInfo> call = Api.updateNotReport(num);

                        call.enqueue(new Callback<ReportResponseInfo>() {
                            @Override
                            public void onResponse(Call<ReportResponseInfo> call, Response<ReportResponseInfo> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "복구되었습니다.", Toast.LENGTH_LONG).show();
                                    getPortInfo();
                                } else { //response 실패

                                }

                            }

                            @Override
                            public void onFailure(Call<ReportResponseInfo> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                });
        dlg.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        dlg.show();


    } // retrofit 데이터 받아오기


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
        Madapter = new MCableAdapter(getApplicationContext());

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


        Madapter.setOnItemClickListener(new MCableAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(MCableAdapter.ViewHolder holder, View view, int position) {
                getNotPortInfo(position + 1);
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

        if (m == 1) {
            chartLayout.setVisibility(View.INVISIBLE);
            recyclerView.setAdapter(adapter);
            m = 0;
            getPortInfo();
        } else {
            final EditText txtEdit = new EditText(MainActivity.this);
            txtEdit.setRawInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
            txtEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
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
                                chartLayout.setVisibility(View.VISIBLE);
                                powerLayout.setVisibility(View.VISIBLE);
                                recyclerView.setAdapter(Madapter);
                                m = 1;
                                getPortInfo();
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

}