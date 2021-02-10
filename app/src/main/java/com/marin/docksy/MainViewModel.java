package com.marin.docksy;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainViewModel {

    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<MetaResponse> apiResponse = new MutableLiveData<>();

    public MutableLiveData<String> authorDescription = new MutableLiveData<>();
    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> footer = new MutableLiveData<>();
    public MutableLiveData<String> author = new MutableLiveData<>();
    public MutableLiveData<Boolean> theme  = new MutableLiveData<>();
    public MutableLiveData<String> primaryDarkColor = new MutableLiveData<>();
    public MutableLiveData<String> primaryLightColor = new MutableLiveData<>();
    public MutableLiveData<String> secondaryLightColor = new MutableLiveData<>();
    public MutableLiveData<String> secondaryDarkColor = new MutableLiveData<>();

    private HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

    private OkHttpClient client = new OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build();

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://student-sport-results.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

    private MetaService service = retrofit.create(MetaService.class);

    private Callback<MetaResponse> callback = new Callback<MetaResponse>() {
        @Override
        public void onResponse(Call<MetaResponse> call, Response<MetaResponse> response) {
            if (response.isSuccessful()) {
                MetaResponse apiResponse = response.body();
                author.setValue(apiResponse.author);
                authorDescription.setValue(apiResponse.authorDescription);
                title.setValue(apiResponse.title);
                footer.setValue(apiResponse.footer);
                theme.setValue(apiResponse.theme);
                primaryDarkColor.setValue(apiResponse.primaryDark);
                primaryLightColor.setValue(apiResponse.primaryLight);
                secondaryDarkColor.setValue(apiResponse.secondaryDark);
                secondaryLightColor.setValue(apiResponse.secondaryLight);
            }
            loading.setValue(false);
        }

        @Override
        public void onFailure(Call<MetaResponse> call, Throwable t) {
            Log.d("TAG", "onFailure: ");
        }
    };

    public MainViewModel() {
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public void fetchData() {
        service.getMeta().enqueue(callback);
    }

    public void updateMeta(){
        loading.setValue(true);
        service.updateMeta(new MetaResponse(
                title.getValue(),
                authorDescription.getValue(),
                footer.getValue(), author.getValue(),
                theme.getValue(), primaryLightColor.getValue(),
                primaryDarkColor.getValue(), secondaryLightColor.getValue(),
                secondaryDarkColor.getValue()
        )).enqueue(callback);
    }

}