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
import java.math.BigDecimal;
import java.text.NumberFormat; // для форматирования денежных сумм

// Класс MainActivity приложения Tip Calculator
public class MainActivity extends AppCompatActivity {
//    TODO вставить локаль (вытащить), если не подтянет
//    Форматировщики денежных сумм и процентов
    private static final NumberFormat currencyFormat =
        NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat =
            NumberFormat.getPercentInstance();

    private BigDecimal billAmount = new BigDecimal(0.0); // сумма счета, введенная пользователем
    private BigDecimal percent = new BigDecimal(0.15); // исходный процент чаевых
    private TextView amountTextView; // для отформатированной суммы счета (вводимой)
    private TextView percentTextView; // для вывода процента чаевых
    private TextView tipTextView; // для вывода вычисленных чаевых
    private TextView totalTextView; // для вычисленной общей суммы

//    Вызывается при первом создании активности
    @Override
    protected void onCreate(Bundle savedInstanceState) { // savedInstanceState - сохранить состояние экземпляра
        super.onCreate(savedInstanceState); // вызов версии суперкласса
        setContentView(R.layout.activity_main); // заполнение GUI
//        Получение ссылок на компоненты TextView, с которыми
//        MainActivity взаимодействует на программном уровне
        amountTextView = (TextView) findViewById(R.id.amountTextView);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        tipTextView.setText(currencyFormat.format(0));
        totalTextView.setText(currencyFormat.format(0));

//        Назначение слушателя TextWatcher для amountEditText
        EditText amountEditText =
                (EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

//        Назначение слушателя OnSeekBarChangeListener для percentSeekBar
        SeekBar percentSeekBar =
                (SeekBar) findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);
    }
}
