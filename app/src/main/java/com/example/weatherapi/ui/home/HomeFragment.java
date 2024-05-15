package com.example.weatherapi.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.example.weatherapi.R;
import com.example.weatherapi.databinding.FragmentHomeBinding;
import com.example.weatherapi.models.Clouds;
import com.example.weatherapi.models.Main;
import com.example.weatherapi.models.Model;
import com.example.weatherapi.models.Sys;
import com.example.weatherapi.models.Wind;
import com.example.weatherapi.remote_data.RetrofitBuilder;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Integer temperature;
    Integer tempMaximal;
    Integer tempMinimal;
    int humidity_c;
    LottieAnimationView snow;
    LottieAnimationView rain;

    String currentTime = java.text.DateFormat.getDateTimeInstance().format(new Date());

    final String apiKey = "a0ef0a91ed320aa9ffd6c22f58e806d3";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        snow = binding.snowLotty;
        snow.setAnimation(R.raw.snow_lotty);

        rain = binding.rainLotty;
        rain.setAnimation(R.raw.rain_lotty);

        binding.localtime.setText(currentTime);

        Call<Model> call = RetrofitBuilder.getInstance().getCurrentWeather("Bishkek", apiKey);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Main main_model = response.body().getModel();
                    Wind wind_model = response.body().getWind_model();
                    Clouds clouds_model = response.body().getClouds_model();
                    Sys sys_model = response.body().getSys_model();

                    Double temp = main_model.getTemp();
                    Double tempMax = main_model.getTempMax();
                    Double tempMin = main_model.getTempMin();

                    temperature = makeFromFaringate(temp);
                    tempMaximal = makeFromFaringate(tempMax);
                    tempMinimal = makeFromFaringate(tempMin);


                    binding.tempC.setText(String.valueOf(temperature) + "°C");
                    if(temperature<=14){
                        setNoHotWeather();
                    }
                    binding.maxMinTemp.setText(String.valueOf(tempMaximal) + " °C↑ \n" + String.valueOf(tempMinimal) + " °C↓");

                    binding.cityName.setText("Bishkek");

                    binding.humidity.setText(main_model.getHumidity() + " %");
                    humidity_c = main_model.getHumidity();
                    if(humidity_c>=55){
                        rainy_possible();
                    }
                    binding.pressure.setText(main_model.getPressure() + "\nmBar");

                    binding.wind.setText(wind_model.getSpeed() + " m/s");
                    binding.cloud.setText(clouds_model.getAll() + " %");

                    binding.sunrise.setText(String.valueOf(getCurrDateTime(sys_model.getSunrise())));
                    binding.sunset.setText(String.valueOf(getCurrDateTime(sys_model.getSunset())));

                    binding.timeZone.setText(String.valueOf(response.body().getTime_zone()));

                    if(response.body().getTime_zone()<=6500 && response.body().getTime_zone()>=-27500){
                        setNight();
                    }else{
                        setDay();
                    }
                }
                setCondition();
            }


            @Override
            public void onFailure(Call<Model> call, Throwable throwable) {
                Toast.makeText(requireActivity(), "No data" + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }


    private void rainy_possible() {
        binding.isRainOrNot.setVisibility(View.VISIBLE);
        binding.isRainOrNot.setText("rain is \npossible ");
        binding.rainLotty.setVisibility(View.VISIBLE);
        binding.badWeatherSky.setVisibility(View.VISIBLE);

    }

    private void snow_possible() {
        binding.isRainOrNot.setVisibility(View.VISIBLE);
        binding.snowLotty.setVisibility(View.VISIBLE);
        binding.isRainOrNot.setText("snow is \npossible ");
        binding.rainLotty.setVisibility(View.INVISIBLE);
        binding.badWeatherSky.setVisibility(View.VISIBLE);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.slideUpBottomSheet.setOnClickListener(v -> {
            if (binding.bottomSheet.getVisibility() == View.GONE) {
                binding.bottomSheet.setVisibility(View.VISIBLE);
            } else {
                binding.bottomSheet.setVisibility(View.GONE);
            }

            binding.rainLotty.setVisibility(View.INVISIBLE);
            binding.blueSky.setVisibility(View.VISIBLE);
            binding.badWeatherSky.setVisibility(View.INVISIBLE);
            binding.inputCity.setText("");
            binding.condition.setText("...");
            binding.isRainOrNot.setVisibility(View.INVISIBLE);
        });

        binding.search.setOnClickListener(v1 -> {
            if (!binding.inputCity.getText().toString().isEmpty()) {
                Call<Model> call = RetrofitBuilder.getInstance().getCurrentWeather(binding.inputCity.getText().toString(), apiKey);

                call.enqueue(new Callback<Model>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<Model> call, @NonNull Response<Model> response) {
                        Main main_model = response.body().getModel();
                        Wind wind_model = response.body().getWind_model();
                        Clouds clouds_model = response.body().getClouds_model();
                        Sys sys_model = response.body().getSys_model();



                        Double temp = main_model.getTemp();
                        Double tempMax = main_model.getTempMax();
                        Double tempMin = main_model.getTempMin();

                        temperature = makeFromFaringate(temp);
                        tempMaximal = makeFromFaringate(tempMax);
                        tempMinimal = makeFromFaringate(tempMin);


                        binding.tempC.setText(String.valueOf(temperature) + "°C");
                        binding.maxMinTemp.setText(String.valueOf(tempMaximal) + " °C↑ \n" + String.valueOf(tempMinimal) + " °C↓");

                        binding.cityName.setText(binding.inputCity.getText().toString());

                        binding.humidity.setText(main_model.getHumidity() + " %");
                        binding.pressure.setText(main_model.getPressure() + "\nmBar");

                        binding.wind.setText(wind_model.getSpeed() + " m/s");
                        binding.cloud.setText(clouds_model.getAll() + " %");

                        binding.sunrise.setText(String.valueOf(getCurrDateTime(sys_model.getSunrise())));
                        binding.sunset.setText(String.valueOf(getCurrDateTime(sys_model.getSunset())));
                        binding.timeZone.setText(String.valueOf(response.body().getTime_zone()));


                        setCondition();
                        if(response.body().getTime_zone()<=7200 && response.body().getTime_zone()>=-27500){
                            setNight();
                        }else{
                            setDay();
                        }

                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable throwable) {
                        Toast.makeText(requireActivity(), "No data" + throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                binding.bottomSheet.setVisibility(View.GONE);
            }else{
                Toast.makeText(requireActivity(), "Input name os city!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDay() {
        binding.nightSky.setVisibility(View.INVISIBLE);
        binding.blueSky.setVisibility(View.VISIBLE);
    }

    private void setNight() {
        binding.nightSky.setVisibility(View.VISIBLE);
        binding.blueSky.setVisibility(View.INVISIBLE);

    }

    public int makeFromFaringate(double tt) {
        Integer gr = (int)(tt-273.15);
        return gr;
    }

    public void setCondition() {
        if (temperature > 20 && temperature<=60) {
            binding.blueSky.setVisibility(View.VISIBLE);
            binding.condition.setText("hotter");
            dryWeather();
        }
        if (temperature <= 20 && temperature > 14) {
            binding.blueSky.setVisibility(View.VISIBLE);
            binding.condition.setText("light \nsunny");
            dryWeather();
        } else {
            if (temperature > 12 && temperature <= 14) {
                setNoHotWeather();
                binding.condition.setText("cloudy");
                rainy_monitoring();
            } else {
                if (temperature > 7 && temperature <= 12) {
                    setNoHotWeather();
                    binding.condition.setText("cold");
                    rainy_monitoring();
                } else {
                    if (temperature <= 7) {
                        setNoHotWeather();
                        snow_monitoring();
                        binding.condition.setText("very \ncold");
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void snow_monitoring(){
        if(temperature<=0){
            binding.snowLotty.setVisibility(View.VISIBLE);
            binding.isRainOrNot.setText("snowing");
            binding.isRainOrNot.setVisibility(View.INVISIBLE);
            binding.rainLotty.setVisibility(View.INVISIBLE);
            snow_possible();
        }else{
            snow_possible();
        }
    }


    private void rainy_monitoring() {
        if(humidity_c<=55){
            binding.rainLotty.setVisibility(View.INVISIBLE);
            binding.isRainOrNot.setText("");
            binding.isRainOrNot.setVisibility(View.INVISIBLE);
            dryWeather();
        }else{
            rainy_possible();
        }
    }

    private void dryWeather() {
        binding.blueSky.setVisibility(View.VISIBLE);
        binding.snowLotty.setVisibility(View.INVISIBLE);
        binding.badWeatherSky.setVisibility(View.INVISIBLE);
        binding.rainLotty.setVisibility(View.INVISIBLE);


    }

    public void setNoHotWeather() {
        binding.blueSky.setVisibility(View.INVISIBLE);
        binding.badWeatherSky.setVisibility(View.VISIBLE);
    }

    public String getCurrDateTime(long m) {
        String new_m = java.text.DateFormat.getDateTimeInstance().format(new Date(m));
        return new_m;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
