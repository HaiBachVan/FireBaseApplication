package app.demo.firebase.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.demo.firebase.R;
import app.demo.firebase.ui.register.RegisterActivity;
import app.demo.firebase.ui.upload.UploadImageActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainContract.IMainView {

    @BindView(R.id.edEmail)
    EditText mEmail;

    @BindView(R.id.edPassword)
    EditText mPassword;

    @BindView(R.id.loginButton)
    Button mLoginButton;

    private MainPresenter mMainPresenter;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupLayout();
        initComponent();
    }

    private void setupLayout() {
        setContentView(R.layout.activity_login);
        mUnBinder = ButterKnife.bind(this);
    }

    private void initComponent() {
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mMainPresenter = new MainPresenter(this, mDatabaseReference, mAuth);
    }

    @OnClick(R.id.loginButton)
    public void loginAccount() {
        // TODO
    }

    @Override
    public void showUploadImageActivity() {
        Intent intent = new Intent(this, UploadImageActivity.class);
        startActivity(intent);
    }

    @Override
    public void showRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        mUnBinder.unbind();
        super.onDestroy();
    }

}
