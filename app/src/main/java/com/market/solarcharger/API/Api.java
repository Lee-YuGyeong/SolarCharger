package com.market.solarcharger.API;

import com.market.solarcharger.GraphInfo;
import com.market.solarcharger.PortResponseInfo;
import com.market.solarcharger.ReportResponseInfo;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {

    @Multipart
    @POST("PortInfoApi.php?apicall=getinfo")
    Call<PortResponseInfo> getPort(@Part("portNum") int num);


    @Multipart
    @POST("PortReportApi.php?apicall=getinfo")
    Call<ReportResponseInfo> updateReport(@Part("Port_num") int Port_num, @Part("Report") int Report, @Part("m") int m);


    @Multipart
    @POST("PortNotReportApi.php?apicall=getinfo")
    Call<ReportResponseInfo> updateNotReport(@Part("Port_num") int Port_num);


    @Multipart
    @POST("GraphApi.php?apicall=getinfo")
    Call<GraphInfo> GraphReport(@Part("Port_num") int Port_num);


}