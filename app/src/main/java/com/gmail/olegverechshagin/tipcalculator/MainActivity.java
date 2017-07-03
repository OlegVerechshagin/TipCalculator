// MainActivity.java
// Вычисление общей суммы счета с заданным процентом чаевых
package com.gmail.olegverechshagin.tipcalculator;

import android.os.Bundle; // для хранения информации состояния
import android.renderscript.ScriptGroup;
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
    private BigDecimal ONE_HUNDRED = new BigDecimal(100.0);

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

    //    Вычисление и вывод чаевых и общей суммы
    private void calculate() {
//        Форматирование процентов и вывод в percentTextView
        percentTextView.setText(percentFormat.format(percent));

//        Вычисление чаевых и общей суммы
        BigDecimal tip = billAmount.multiply(percent);
        BigDecimal total = billAmount.add(tip);

//        Вывод чаевых и общей суммы в формате денежной величины
        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));
    }

    //    Объект слушателя для событий изменения состояния SeekBar
    private final OnSeekBarChangeListener seekBarListener =
            new OnSeekBarChangeListener() {
                //            Обновление процента чаевых и возов calculate
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    percent = new BigDecimal(progress).divide(ONE_HUNDRED); // Назначение процента чаевых
                    calculate(); //  Вычисление и вывод чаевых и сумм
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            };

    //        Объект слушателя для событий изменения текста в EditText
    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        //    Вызывается при изменении пользователем величины счета
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try { // получить счет и вывести в форме денежной суммы
                billAmount = new BigDecimal(s.toString()).divide(ONE_HUNDRED);
                amountTextView.setText(currencyFormat.format(billAmount));
            } catch (NumberFormatException e) { // если s пусто или не число
                amountTextView.setText("");
                billAmount = BigDecimal.valueOf(0.0);
            }
            calculate(); // обновление полей с чаевыми и общей суммой
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void afterTextChanged(Editable s) {}
    };
}
