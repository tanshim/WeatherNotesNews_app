package com.example.weanotnew.weather.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.weanotnew.R
import com.example.weanotnew.util.*
import com.example.weanotnew.weather.model.Main
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext


class WeatherFragment : Fragment(), WeatherContract.WeatherView, CoroutineScope {

    private lateinit var job: Job

    // context for io thread
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private lateinit var presenter: WeatherContract.WeatherPresenter
    private var city: String = ""
    private var main: Main? = null

    private var tvCelcius: TextView? = null
    private var ivIcon: ImageView? = null
    private var tvTemp: TextView? = null
    private var tvWeather: TextView? = null
    private var tvFeelsLike: TextView? = null
    private var tvHumidity: TextView? = null
    private var tvCurrentCity: TextView? = null
    private var tvSunrise: TextView? = null
    private var tvSunset: TextView? = null
    private var tvClouds: TextView? = null
    private var tvWindSpeed: TextView? = null
    private lateinit var contentView: CardView
    private lateinit var contentViewHourly: CardView
    private var tvTempHourly3: TextView? = null
    private var tvTempHourly6: TextView? = null
    private var tvTempHourly9: TextView? = null
    private var ivIconHourly3: ImageView? = null
    private var ivIconHourly6: ImageView? = null
    private var ivIconHourly9: ImageView? = null
    private var tvWeatherHourly3: TextView? = null
    private var tvWeatherHourly6: TextView? = null
    private var tvWeatherHourly9: TextView? = null
    private var tvTimeHourly3: TextView? = null
    private var tvTimeHourly6: TextView? = null
    private var tvTimeHourly9: TextView? = null
    private lateinit var contentViewDaily: CardView
    private var tvDateAndTempDaily1: TextView? = null
    private var tvDateAndTempDaily2: TextView? = null
    private var tvDateAndTempDaily3: TextView? = null
    private var ivIconDaily1: ImageView? = null
    private var ivIconDaily2: ImageView? = null
    private var ivIconDaily3: ImageView? = null
    private var tvWeatherDaily1: TextView? = null
    private var tvWeatherDaily2: TextView? = null
    private var tvWeatherDaily3: TextView? = null
    private lateinit var loadingView: RelativeLayout
    private lateinit var btnChangeHomeCity: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        job = Job()

        contentView = view.findViewById(R.id.cardViewCurrent)
        contentViewHourly = view.findViewById(R.id.cardViewHourly)
        contentViewDaily = view.findViewById(R.id.cardViewDaily)
        loadingView = view.findViewById(R.id.loadingPanel)

        contentView.visibility = View.GONE
        contentViewHourly.visibility = View.GONE
        contentViewDaily.visibility = View.GONE

        ivIcon = view.findViewById(R.id.imageViewWeatherIcon)
        tvTemp = view.findViewById(R.id.textViewTemp)
        tvCelcius = view.findViewById(R.id.textViewCelcius)
        tvWeather = view.findViewById(R.id.textViewWeather)
        tvFeelsLike = view.findViewById(R.id.textViewFeelsLike)
        tvHumidity = view.findViewById(R.id.textViewHumidity)
        tvCurrentCity = view.findViewById(R.id.textViewCurrentCity)
        tvSunrise = view.findViewById(R.id.textViewSunrise)
        tvSunset = view.findViewById(R.id.textViewSunset)
        tvClouds = view.findViewById(R.id.textViewClouds)
        tvWindSpeed = view.findViewById(R.id.textViewWindSpeed)
        //hourly
        tvTempHourly3 = view.findViewById(R.id.textViewTempHourly3)
        ivIconHourly3 = view.findViewById(R.id.imageViewIconHourly3)
        tvWeatherHourly3 = view.findViewById(R.id.textViewWeatherHourly3)
        tvTimeHourly3 = view.findViewById(R.id.textViewTimeHourly3)
        tvTempHourly6 = view.findViewById(R.id.textViewTempHourly6)
        ivIconHourly6 = view.findViewById(R.id.imageViewIconHourly6)
        tvWeatherHourly6 = view.findViewById(R.id.textViewWeatherHourly6)
        tvTimeHourly6 = view.findViewById(R.id.textViewTimeHourly6)
        tvTempHourly9 = view.findViewById(R.id.textViewTempHourly9)
        ivIconHourly9 = view.findViewById(R.id.imageViewIconHourly9)
        tvWeatherHourly9 = view.findViewById(R.id.textViewWeatherHourly9)
        tvTimeHourly9 = view.findViewById(R.id.textViewTimeHourly9)
        //daily
        tvDateAndTempDaily1 = view.findViewById(R.id.textViewDailyDateAndTemp1)
        ivIconDaily1 = view.findViewById(R.id.imageViewDailyIcon1)
        tvWeatherDaily1 = view.findViewById(R.id.textViewDailyWeather1)
        tvDateAndTempDaily2 = view.findViewById(R.id.textViewDailyDateAndTemp2)
        ivIconDaily2 = view.findViewById(R.id.imageViewDailyIcon2)
        tvWeatherDaily2 = view.findViewById(R.id.textViewDailyWeather2)
        tvDateAndTempDaily3 = view.findViewById(R.id.textViewDailyDateAndTemp3)
        ivIconDaily3 = view.findViewById(R.id.imageViewDailyIcon3)
        tvWeatherDaily3 = view.findViewById(R.id.textViewDailyWeather3)

        val editTextCity: EditText = view.findViewById(R.id.editTextCity)
        val btnSearch: Button = view.findViewById(R.id.buttonSearch)
        btnChangeHomeCity = view.findViewById(R.id.buttonChangeHomeCity)

        btnChangeHomeCity.visibility = View.GONE

        setPresenter(WeatherPresenterImpl(this, view.context))

        launch {
            // task, do smth in io thread
            presenter.onViewCreated()
            withContext(Dispatchers.Main) {
                // do smth in main thread after task is finished
                setContent()
                loadingView.visibility = View.GONE
                contentView.visibility = View.VISIBLE
                contentViewHourly.visibility = View.VISIBLE
                contentViewDaily.visibility = View.VISIBLE
            }
        }

        btnSearch.setOnClickListener {
            city = editTextCity.text.toString().trim()
            if (city.isNotEmpty()) {
                searchCity(city)
            }
        }

        btnChangeHomeCity.setOnClickListener {
            btnChangeHomeCity.visibility = View.GONE
            launch {
                presenter.onHomeCityChange(city)
            }
            Toast.makeText(activity, "Home city changed!", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    override fun setViews(main: Main?, city: String) {
        this.city = city
        this.main = main
    }

    override fun setPresenter(presenter: WeatherContract.WeatherPresenter) {
        this.presenter = presenter
    }

    override fun searchCity(city: String) {
        var validCity: Boolean
        launch {
            validCity = presenter.loadWeather(city.capitalize(Locale.ROOT))
            withContext(Dispatchers.Main) {
                setContent()
                if (validCity) {
                    hideKeyboard()
                    btnChangeHomeCity.visibility = View.VISIBLE
                } else {
                    btnChangeHomeCity.visibility = View.GONE
                    Toast.makeText(activity, "Please, enter valid city name!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setContent() {
        main?.let {
            val timeZone = it.timezone
            it.current?.let {
                tvTemp?.text = it.temp?.toInt().toString()
                tvWeather?.text = it.weather?.get(0)?.description
                val urlIcon = "https://openweathermap.org/img/wn/${it.weather?.get(0)?.icon}@2x.png"
                ivIcon?.let { it1 -> Glide.with(this).load(urlIcon).into(it1) }
                tvFeelsLike?.text = String.format("Ощущается: %s°C", it.feelsLike?.toInt().toString())
                tvHumidity?.text = String.format("Влажность: %s%%", it.humidity.toString())
                tvCurrentCity?.text = city
                it.sunrise?.let {
                    val sunrise = it.toLong() * 1000
                    tvSunrise?.text = String.format("Рассвет: %s", convertTimestampToHour(sunrise, timeZone))
                }
                it.sunset?.let {
                    val sunset = it.toLong() * 1000
                    tvSunset?.text = String.format("Закат: %s", convertTimestampToHour(sunset, timeZone))
                }
                tvClouds?.text = String.format("Облачность: %s%%", it.clouds.toString())
                tvWindSpeed?.text = String.format("Ветер: %s км/ч", (it.windSpeed?.times(3.6))?.toInt())
            }
            // Hourly cardView
            it.hourly?.let {
                tvTempHourly3?.text = String.format("%s°", it[3].temp?.toInt())
                val url3 = "https://openweathermap.org/img/wn/${it[3].weather?.get(0)?.icon}@2x.png"
                ivIconHourly3?.let { it1 -> Glide.with(this).load(url3).into(it1) }
                tvWeatherHourly3?.text = it[3].weather?.get(0)?.description
                tvTimeHourly3?.text = convertTimestampToHour(it[3].dt?.toLong()?.times(1000), timeZone)

                tvTempHourly6?.text = String.format("%s°", it[6].temp?.toInt())
                val url6 = "https://openweathermap.org/img/wn/${it[6].weather?.get(0)?.icon}@2x.png"
                ivIconHourly6?.let { it1 -> Glide.with(this).load(url6).into(it1) }
                tvWeatherHourly6?.text = it[6].weather?.get(0)?.description
                tvTimeHourly6?.text = convertTimestampToHour(it[6].dt?.toLong()?.times(1000), timeZone)

                tvTempHourly9?.text = String.format("%s°", it[9].temp?.toInt())
                val url9 = "https://openweathermap.org/img/wn/${it[9].weather?.get(0)?.icon}@2x.png"
                ivIconHourly9?.let { it1 -> Glide.with(this).load(url9).into(it1) }
                tvWeatherHourly9?.text = it[9].weather?.get(0)?.description
                tvTimeHourly9?.text = convertTimestampToHour(it[9].dt?.toLong()?.times(1000), timeZone)

            }
            // Daily cardView
            it.daily?.let {
                val dayTemp1 = it[1].temp?.day?.toInt()
                val nightTemp1 = it[1].temp?.night?.toInt()
                tvDateAndTempDaily1?.text = tomorrowAndTempStringBuilder(dayTemp1, nightTemp1)
                val urlDaily1 = "https://openweathermap.org/img/wn/${it[1].weather?.get(0)?.icon}@2x.png"
                ivIconDaily1?.let { it1 -> Glide.with(this).load(urlDaily1).into(it1) }
                tvWeatherDaily1?.text = it[1].weather?.get(0)?.description

                val day2Timestamp = it[2].dt
                val dayTemp2 = it[2].temp?.day?.toInt()
                val nightTemp2 = it[2].temp?.night?.toInt()
                tvDateAndTempDaily2?.text = dayAndTempStringBuilder(day2Timestamp, timeZone, dayTemp2, nightTemp2)
                val urlDaily2 = "https://openweathermap.org/img/wn/${it[2].weather?.get(0)?.icon}@2x.png"
                ivIconDaily2?.let { it1 -> Glide.with(this).load(urlDaily2).into(it1) }
                tvWeatherDaily2?.text = it[2].weather?.get(0)?.description

                val day3Timestamp = it[3].dt
                val dayTemp3 = it[3].temp?.day?.toInt()
                val nightTemp3 = it[3].temp?.night?.toInt()
                tvDateAndTempDaily3?.text = dayAndTempStringBuilder(day3Timestamp, timeZone, dayTemp3, nightTemp3)
                val urlDaily3 = "https://openweathermap.org/img/wn/${it[3].weather?.get(0)?.icon}@2x.png"
                ivIconDaily3?.let { it1 -> Glide.with(this).load(urlDaily3).into(it1) }
                tvWeatherDaily3?.text = it[3].weather?.get(0)?.description
            }
        }
    }

    override fun onDestroy() {
        job.cancel()
        presenter.onDestroy()
        super.onDestroy()
    }
}