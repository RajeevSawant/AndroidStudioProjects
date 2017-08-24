package com.example.rajeevsawant.justjava;


/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.attr.checked;
import static android.R.id.message;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    static int quantity = 0;
    int pricePerCup = 5;
    boolean whippedCream;
    boolean isChocolate;
    String priceMessage;

    //    static int price = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
//    public void submitOrder(View view) {
//        displayMessage(createOrderSummary(quantity));
//    }
//
    public String createOrderSummary(int amount) {

        int price = calculatePrice();

        EditText toEditName = (EditText) findViewById(R.id.name_edit_text);
        String nameText = toEditName.getText().toString();




        if (amount > 0) {
            priceMessage = getString(R.string.name) + " : " + nameText
                    + "\nAdd "+getString(R.string.whipped_cream)+ " ? " + whippedCream
                    + "\nAdd Chocolate? " + isChocolate
                    + "\n"+ getString(R.string.quantity) + " = " + amount
                    + "\n" + getString(R.string.total) + " = $" + price
                    + "\n"+ getString(R.string.thank_you);
        } else {
            priceMessage = getString(R.string.total)+ " = $" + amount * 5;
        }
        return priceMessage;
    }



    public void submitOrder(View view){
        Intent intent = (new Intent(Intent.ACTION_SEND));
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"rajeev89c51@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, " "+ getString(R.string.order_summary));
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(quantity) );
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent.createChooser(intent, "Send mail..."));
        }
        displayPrice(calculatePrice());

    }

    public void incrementQuantity(View view) {
        if (quantity < 100){
            quantity++;
            displayQuantity(quantity);
        }else{
            Toast.makeText(this, "Too Many Cups Of Coffee", Toast.LENGTH_SHORT).show();
        }
    }

    public void decrementQuantity(View view) {
        if (quantity > 1) {
            quantity--;
            displayQuantity(quantity);
        }else{
            Toast.makeText(this, "Too Less Cups Of Coffee", Toast.LENGTH_SHORT).show();
        }
    }

    public void whippedCreamPrice(View view) {

        CheckBox checked = (CheckBox) findViewById(R.id.whipped_cream);
        whippedCream = checked.isChecked();
        if (whippedCream) {
            pricePerCup += 2;
        } else {
            pricePerCup = 5;
        }
    }

    public void chocolatePrice(View view) {

        CheckBox chocolate = (CheckBox) findViewById(R.id.checkbox_chocolate_cream);
        isChocolate = chocolate.isChecked();
        if (isChocolate) {
            pricePerCup += 3;
        } else {
            pricePerCup = 5;
        }
    }

    /**
     * Calculates the price of the order.
     */
    public int calculatePrice() {
        int price = quantity * pricePerCup;
        return price;
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.amount);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


}