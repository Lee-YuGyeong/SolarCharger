package com.market.solarcharger;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lakue.lakuepopupactivity.PopupActivity;
import com.lakue.lakuepopupactivity.PopupGravity;
import com.lakue.lakuepopupactivity.PopupResult;
import com.lakue.lakuepopupactivity.PopupType;
import com.market.solarcharger.API.APIClient;
import com.market.solarcharger.API.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubActivity extends AppCompatActivity {

    int m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        m = getIntent().getIntExtra("m", -1);


        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PopupActivity.class);
                intent.putExtra("type", PopupType.NORMAL);
                intent.putExtra("gravity", PopupGravity.CENTER);
                intent.putExtra("title", "알림");
                intent.putExtra("content", "1번포트 신고가 완료 되었습니다.");
                intent.putExtra("buttonCenter", "확인");

                startActivityForResult(intent, 1);
                updateReport(1);


            }
        });

        button = findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PopupActivity.class);
                intent.putExtra("type", PopupType.NORMAL);
                intent.putExtra("gravity", PopupGravity.CENTER);
                intent.putExtra("title", "알림");
                intent.putExtra("content", "2번포트 신고가 완료 되었습니다.");
                intent.putExtra("buttonCenter", "확인");

                startActivityForResult(intent, 1);
                updateReport(2);
            }
        });

        button = findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), PopupActivity.class);
                intent.putExtra("type", PopupType.NORMAL);
                intent.putExtra("gravity", PopupGravity.CENTER);
                intent.putExtra("title", "알림");
                intent.putExtra("content", "3번포트 신고가 완료 되었습니다.");
                intent.putExtra("buttonCenter", "확인");

                startActivityForResult(intent, 1);
                updateReport(3);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //데이터 받기
            if (requestCode == 1) {
                PopupResult result = (PopupResult) data.getSerializableExtra("result");
                if (result == PopupResult.CENTER) {
                    // 작성 코드
                    finish();
                }
            }


        }
    }

    private void updateReport(int num) {

        Api Api = APIClient.getClient().create(Api.class);
        Call<ReportResponseInfo> call = Api.updateReport(num, 1, m);

        call.enqueue(new Callback<ReportResponseInfo>() {
            @Override
            public void onResponse(Call<ReportResponseInfo> call, Response<ReportResponseInfo> response) {
                if (response.isSuccessful()) {


                } else { //response 실패

                }

            }

            @Override
            public void onFailure(Call<ReportResponseInfo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    } // retrofit 데이터 받아오기


}