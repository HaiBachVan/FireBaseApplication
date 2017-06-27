package app.demo.firebase.ui.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import app.demo.firebase.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.IRegisterView {

    @BindView(R.id.edRegisterEmail)
    EditText mRegisterEmail;

    @BindView(R.id.edRegisterPassword)
    EditText mRegisterPassword;

    @BindView(R.id.edPasswordRepeat)
    EditText mPasswordRepeat;

    @BindView(R.id.registerButton)
    EditText mRegisterButton;


    private Unbinder mUnBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupLayout();
    }

    private void setupLayout() {
        setContentView(R.layout.activity_register);
        mUnBinder = ButterKnife.bind(this);
    }

    @OnClick(R.id.registerButton)
    public void registerAccount() {
        // TODO
    }

    @Override
    protected void onDestroy() {
        mUnBinder.unbind();
        super.onDestroy();
    }

}
