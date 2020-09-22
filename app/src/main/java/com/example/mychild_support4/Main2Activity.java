package com.example.mychild_support4;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//Import Classes for mySQL database
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;


public class Main2Activity extends AppCompatActivity {

    /**
     * Connection variables for server and database
     */
    //To change where the server is hosted, change the line below to the appropriate host/server.
    private static final String url = "jdbc:mysql://127.0.0.1:3306/child_support_db";
    private static final String user = "child_support";
    private static final String pass = "ys6BCBDgf9Hv2aap";
    //ConnectionClass connectionClass;


    Button button_Home, button_Calc;   //Home and Calculation Buttons on Second Page

    int FChild_parent1, FChild_parent2, NFChild_parent1, NFChild_parent2;    //Int values to store input textfield values
    float parent1_salary, parent2_salary;   //float values to store Parent 1 and Parent 2 salaries
    String salary_parent1, salary_parent2;

    EditText Salary1_Input, Salary2_Input, FullChild1_Input, FullChild2_Input, NonFullChild1_Input, NonFullChild2_Input;    //Textfields on second page

    TextView Result_value;  //Variable to display Value of Amount owed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Insertion of User values for Calculations: Define EditText Fields using fields from xml file
        Salary1_Input = (EditText) findViewById(R.id.etSalary1);
        Salary2_Input = (EditText) findViewById(R.id.etSalary2);
        FullChild1_Input = (EditText) findViewById(R.id.etFChild1);
        FullChild2_Input = (EditText) findViewById(R.id.etFChild2);
        NonFullChild1_Input = (EditText) findViewById(R.id.etNFChild1);
        NonFullChild2_Input = (EditText) findViewById(R.id.NFChild2);

        //Linked variable with Ref.ID
        Result_value = (TextView)findViewById(R.id.textView11);

        //onCLickListener for Home Button
        button_Home = (Button) findViewById(R.id.but_Home);     //Assign Home i.d. to button_Home
        button_Home.setOnClickListener(new View.OnClickListener(){  //On click perform openHome()
            @Override
            public void onClick(View v) {
                openHome();
            }
        });

        //onClickListener for Calculation Button
        button_Calc = (Button) findViewById(R.id.but_Calc); //Assign Calc i.d. to button_Calc
        button_Calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Assign variables to current Textfields to Strings
                parent1_salary = Float.valueOf(Salary1_Input.getText().toString());
                parent2_salary = Float.valueOf(Salary2_Input.getText().toString());
                FChild_parent1 = Integer.valueOf(FullChild1_Input.getText().toString());
                FChild_parent2 = Integer.valueOf(FullChild2_Input.getText().toString());
                NFChild_parent1 = Integer.valueOf(NonFullChild1_Input.getText().toString());
                NFChild_parent2 = Integer.valueOf(NonFullChild2_Input.getText().toString());


                //Test: view the values in a Toast view
                /**
                showToast(String.valueOf(parent1_salary));
                showToast(String.valueOf(parent2_salary));
                showToast(String.valueOf(FChild_parent1));
                showToast(String.valueOf(FChild_parent2));
                showToast(String.valueOf(NFChild_parent1));
                showToast(String.valueOf(NFChild_parent2));
                **/

                //Calculates the Child Support
                testDB();
            }
        });    //On click perform calc_Child_Support

    }


    /**
     * Method: This TEST method is used to display the current Input values in a Toast view
     * @param text the string variable to be displayed
     */
    /**
    private void showToast(String text){
        Toast.makeText(Main2Activity.this, text, Toast.LENGTH_LONG).show();
    }
     **/

    /**
     * Test Method: Used to test the input values for the textfields and displays the results
     */
    public void Test_Input_Values(){
        Result_value.setText("Child Support Amount: " + "Parent 1: " + parent1_salary + ". Parent 2: " + parent2_salary
                + ". Parent1: " + FChild_parent1 + ", " + NFChild_parent1 + ". Parent1: " + FChild_parent2 + ", "
                + NFChild_parent2);        //Displays Child Support Amount

    }

    /**
     * Method: Goes to the home page when button_Home is clicked
     **/
    public void openHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Method: This method calculates the child Support that one parent owes
     */
    public void calculate_Child_Support(){

    }

    /**
     * Method: This Method is used to test the functionality of the database
     */
    public void testDB(){
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);

            String result = "Database connection success\n";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from child_support_on");
            ResultSetMetaData rand = rs.getMetaData();

            //while (rs.next()) {
                result += rand.getColumnName(1) + ":" + rs.getInt(1) + "\n";
                result += rand.getColumnName(2) + ":" + rs.getInt(2) + "\n";
            //}

            Result_value.setText(result);

        }
        catch (Exception e) {
            e.printStackTrace();
            Result_value.setText(e.toString());
        }

    }

}
