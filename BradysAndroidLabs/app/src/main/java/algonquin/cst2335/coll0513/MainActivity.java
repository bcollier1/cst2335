package algonquin.cst2335.coll0513;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The MainActivity class represents the main activity of the application.
 * It displays a TextView, EditText, and Button for password validation.
 *
 * @author bradycollier
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     * TextView object used for displaying text.
     */
    TextView tv = null;
    /**
     * EditText object used for user input.
     */
    EditText et = null;
    /**
     * Button object used for triggering actions.
     */
    Button btn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.passwordView);
        et = findViewById(R.id.passwordField);
        btn = findViewById(R.id.loginButton);

        btn.setOnClickListener(vw -> {
            String password = et.getText().toString();
            checkPasswordComplexity(password);
        });
    }

    /**
     * checkPasswordComplexity() checks the users password to validate the specification requirements
     * for the password.
     *
     * @param password The String object that we are checking
     * @return Returns true if the password satisfies all complexity requirements, false otherwise.
     */
    protected boolean checkPasswordComplexity(String password) {
        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;
        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;
        int duration = Toast.LENGTH_SHORT;

        for(int i=0; i < password.length(); i++){
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                foundUpperCase = true;
            } else if (Character.isLowerCase(ch)) {
                foundLowerCase = true;
            } else if (Character.isDigit(ch)) {
                foundNumber = true;
            } else if (isSpecialChar(ch)) {
                foundSpecial = true;
            }
        }

        if (!foundUpperCase) {
            tv.setText(R.string.failMessage);
            Toast.makeText(this, "Error: Missing an uppercase letter in your password.", duration).show();// Say that they are missing an upper case letter;
            return false;
        } else if (!foundLowerCase) {
            tv.setText(R.string.failMessage);
            Toast.makeText(this, "Error: Missing a lowercase letter in your password.", duration).show(); // Say that they are missing a lower case letter;
            return false;

        } else if (!foundNumber) {
            tv.setText(R.string.failMessage);
            Toast.makeText(this, "Error: Missing a number in your password.", duration).show();
            return false;
        } else if (!foundSpecial) {
            tv.setText(R.string.failMessage);
            Toast.makeText(this, "Error: Missing a special character in your password", duration).show();
        } else{
            tv.setText(R.string.successMessage);
        }
        return true;
    }

    /**
     * Checks if a character is a special character.
     *
     * @param x The character to be checked.
     * @return Returns true if the character is a special character, false otherwise.
     */
    boolean isSpecialChar(char x){
        switch(x){
            case '!':
            case '@':
            case '#':
            case '$':
            case '%':
            case '^':
            case '&':
            case '*':
            case '(':
            case ')':
            case '-':
            case '=':
            case '`':
                return true;
            default:
                return false;
        }
    }
}