package com.zaymon.app.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.zaymon.app.BuildConfig
import com.zaymon.app.R

import com.zaymon.app.api_manager.APIInterface
import com.zaymon.app.api_manager.RetrofitImpl
import com.zaymon.app.api_manager.RootModel
import com.zaymon.app.databinding.ActivityConverterBinding
import com.zaymon.app.extesion.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DecimalFormat


class ConverterActivity : AppCompatActivity() {

    var fromCurrencyCode: String = "USD"
    var fromCurrencySymbol: String = "$"
    var toCurrencyCode: String = "EUR"
    var toCurrencySymbol: String = "€"

    var body: RootModel? = null


    var isFromCurrency = true

    private lateinit var binding: ActivityConverterBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConverterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvToCurrencyCountry.text = "U.S. Dollar"
        binding.tvFromCurrencyCountry.text = "Euro"
    }

    private fun showCurrencyDialog() {
        val currencySymbolList =
            arrayOf("$", "€", "£", "CHF", "¥", "₽", "$", "$", "¥", "zł")
        val currencyList =
            arrayOf("USD", "EUR", "GBP", "CHF", "CNY", "RUB", "CAD", "AUD", "JPY", "PLN")
        val countryList = arrayOf(
            "U.S. Dollar",
            "Euro",
            "Pound Sterling",
            "Swiss Frank",
            "China Yuan",
            "Russian Ruble",
            "Canadian Dollar",
            "Australian Dollar",
            "Japanese Yien",
            "Poland Zloty"
        )
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Select Currency")
        builder.setItems(countryList) { dialog, item ->
            if (isFromCurrency) {
                fromCurrencyCode = currencyList[item]
                fromCurrencySymbol = currencySymbolList[item]
                binding.tvToCurrencyCountry.text = countryList[item]
                binding.tvToSymbol.text = fromCurrencySymbol

            } else {
                toCurrencySymbol = currencySymbolList[item]
                toCurrencyCode = currencyList[item]
                binding.tvFromCurrencyCountry.text = countryList[item]
                binding.tvFromSymbol.text = toCurrencyCode
            }
        }
        builder.show()


    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnConvert -> {
                if (isNetworkAvailable()) {
                    when {
                        (toCurrencyCode == fromCurrencyCode) -> {
                            Toast.makeText(this, "Please select different Currency code", Toast.LENGTH_SHORT).show()
                        }
                        binding.etAmount.text.toString().equals("", true) -> {
                            Toast.makeText(this, "Enter amount", Toast.LENGTH_SHORT).show()
                        }
                        binding.etAmount.text.toString() == "0" -> {
                            Toast.makeText(this, "Enter valid amount", Toast.LENGTH_SHORT).show()
                        }
                        else -> convertAmount()

                    }
                } else {
                    Toast.makeText(this, "Check Internet connection", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.tv_to_currency_country, R.id.appCompatImageView2 -> {
                isFromCurrency = true
                showCurrencyDialog()
            }
            R.id.tvFromCurrencyCountry, R.id.appCompatImageView3 -> {
                isFromCurrency = false
                showCurrencyDialog()
            }
            R.id.btnSave -> {
                body?.let {
                    setLastCurrency(it)
                }
            }
        }
    }

    private fun convertAmount() {

        binding.btnConvert.invisible()
        binding.progressBar.visible()

        val retrofit = RetrofitImpl.retrofit.create(APIInterface::class.java)
        val headerMap = hashMapOf<String, String>()
        headerMap["apikey"] = BuildConfig.API_KEY

        val queryMap = hashMapOf<String, String>()
        queryMap["to"] = toCurrencyCode
        queryMap["from"] = fromCurrencyCode
        queryMap["amount"] = binding.etAmount.text.toString()
        val call: Call<RootModel> =
            retrofit.convertAmount(BuildConfig.BASE_URL, headerMap, queryMap)
        call.enqueue(object : Callback<RootModel> {
            override fun onResponse(call: Call<RootModel>, response: Response<RootModel>) {
                loadValue(response.body())
            }

            override fun onFailure(call: Call<RootModel>, t: Throwable) {
                Toast.makeText(this@ConverterActivity, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()

                binding.btnConvert.visible()
                binding.progressBar.invisible()
            }

        })

    }

    @SuppressLint("SetTextI18n")
    private fun loadValue(body: RootModel?) {

        binding.btnConvert.visible()
        binding.progressBar.invisible()

        body?.let { body1 ->
            val convertedAmount = DecimalFormat.getInstance().format(body1.result)

            binding.cardShowAmount.visibility = View.VISIBLE
            binding.tvConvertedAmount.text = convertedAmount
            binding.tvCurrentResult.text =
                "‣ ${binding.etAmount.text.toString()}$fromCurrencySymbol = ${convertedAmount}$toCurrencySymbol"

            getLastCurrency()?.let { body2 ->
                val oldAmount = DecimalFormat.getInstance().format(body2.oldAmount)
                val convertedAmount1 = DecimalFormat.getInstance().format(body2.result)
                binding.tvCurrentResult1.text =
                    "‣ ${oldAmount}${body2.toCurrencySymbol} = ${convertedAmount1}${body2.fromCurrencySymbol}"
                body1.oldAmount = binding.etAmount.text.toString().toDouble()
            }

            body1.oldAmount = binding.etAmount.text.toString().toDouble()
            body1.toCurrencySymbol = fromCurrencySymbol
            body1.fromCurrencySymbol = toCurrencySymbol
            this@ConverterActivity.body = body1

        } ?: kotlin.run {
            Toast.makeText(this@ConverterActivity, "Something went wrong", Toast.LENGTH_SHORT)
                .show()
        }
    }


}