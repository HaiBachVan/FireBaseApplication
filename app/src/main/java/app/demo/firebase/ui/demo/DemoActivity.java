package app.demo.firebase.ui.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.demo.firebase.R;
import app.demo.firebase.ui.upload.UploadImageActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DemoActivity extends AppCompatActivity implements DemoContract.IDemoView {

    @BindView(R.id.edEmail)
    EditText edEmail;

    @BindView(R.id.edPassword)
    EditText edPassword;

    private DemoPresenter mMainPresenter;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mUnBinder = ButterKnife.bind(this);

        initComponent();
        //doSomeThing();
    }

    private void initComponent() {
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mMainPresenter = new DemoPresenter(this, mDatabaseReference, mAuth);
    }

    // Code demo
    private void doSomeThing() {
        //+ START: Using setValue
        mMainPresenter.setValueString();
        mMainPresenter.setValueWithObject();
        mMainPresenter.setValueWithMap();
        //- END: Using setValue

        //+ START: Using push method - Create many object have type same as
        mMainPresenter.setValueWithPush();
        //- END: Using push method

        // Catch event when save FireBase database complete
        mMainPresenter.onCompletionListener();


        // observer data change at device and server
        mMainPresenter.addValueEventListener();
        mMainPresenter.addChildEventListener();
    }

    @Override
    public void showUploadImageActivity() {
        Intent intent = new Intent(this, UploadImageActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

}
