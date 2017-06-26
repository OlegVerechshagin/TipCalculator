// MainActivity.java
// Вычисление общей суммы счета с заданным процентом чаевых
package com.gmail.olegverechshagin.tipcalculator;

import android.os.Bundle; // для хранения информации состояния
import android.support.v7.app.AppCompatActivity; // базовый класс
import android.text.Editable; // для обработки событий EditText
import android.text.TextWatcher; // слушатель EditText
import android.widget.SeekBar.OnSeekBarChangeListener; // слушатель SeekBar
import android.widget.SeekBar; // для изменения процента чаевых
import android.widget.EditText; // Для ввода счета
import android.widget.TextView; // для вывода текста

import java.text.NumberFormat; // для форматирования денежных сумм

// Класс MainActivity приложения Tip Calculator
public class MainActivity extends AppCompatActivity {
//    TODO вставить локаль (вытащить), если не подтянет
//    Форматировщики денежных сумм и процентов
    private static final NumberFormat currencyFormat =
        NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat =
            NumberFormat.getPercentInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
