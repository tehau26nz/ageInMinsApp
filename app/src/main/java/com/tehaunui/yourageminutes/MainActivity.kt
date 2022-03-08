package com.tehaunui.yourageminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener {
            clickDatePicker()

        }

    }
    private fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{_, selectedYear, selectedMonth, selectedDayOfMonth ->

                /*Toast.makeText(this,
                    "Year was $selectedYear," +
                            "month was ${selectedMonth+1}," +
                            "day of month $selectedDayOfMonth",
                    Toast.LENGTH_LONG).show()*/
                Toast.makeText(this,
                    "Hooray, here are your results",
                    Toast.LENGTH_LONG).show()


                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                tvSelectedDate?.text = selectedDate

                val sDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sDateFormat.parse(selectedDate)
                theDate?.let{
                    // Time from selected date
                    val selectedDateInMinutes = theDate.time/60000

                    // Current time passed
                    val currentDate = sDateFormat.parse(sDateFormat.format(System.currentTimeMillis()))
                    currentDate?.let{
                        val currentDateInMinutes = currentDate.time/60000

                        // Time between the two time points
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        tvAgeInMinutes?.text = differenceInMinutes.toString()
                    }

                }
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()




    }

}