package michael.com.firebaseapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import michael.com.firebaseapp.R;

/**
 * Created by Mikhail on 1/28/17.
 */

public class LogInActivity extends AppCompatActivity {

    @BindView(R.id.emailField) EditText emailEditText;
    @BindView(R.id.passwordField) EditText passwordEditText;
    @BindView(R.id.loginButton) Button logInButton;
    @BindView(R.id.signUpText) TextView signUpTextView;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mFirebaseAuth = FirebaseAuth.getInstance();
        ButterKnife.bind(this);
        setButtonClickListners();
    }

    private void setButtonClickListners() {
        signUpTextView.setOnClickListener(v -> {
            Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        logInButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            email = email.trim();
            password = password.trim();

            if (email.isEmpty() || password.isEmpty()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this);
                builder.setMessage(R.string.login_error_message)
                        .setTitle(R.string.login_error_title)
                        .setPositiveButton(android.R.string.ok, null);
                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                mFirebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LogInActivity.this, task -> {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this);
                                builder.setMessage(task.getException().getMessage())
                                        .setTitle(R.string.login_error_title)
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        });
            }
        });
    }

}
