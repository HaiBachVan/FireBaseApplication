package app.demo.firebase.ui.upload;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import app.demo.firebase.R;
import app.demo.firebase.utils.Constants;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class UploadImageActivity extends AppCompatActivity implements UploadImageContract.IUploadImageView {

    @BindView(R.id.imageUpload)
    ImageView mImageUpload;

    private Unbinder mUnBinder;
    private UploadImageContract.IUploadImagePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        mUnBinder = ButterKnife.bind(this);

        initComponent();
    }

    private void initComponent() {
        FirebaseStorage storage = FirebaseStorage.getInstance(Constants.FIRE_BASE_STORAGE_URL);
        StorageReference storageRef = storage.getReference();

        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mPresenter = new UploadImagePresenter(this, storageRef, mDatabaseReference);
    }

    @OnClick({R.id.uploadImage, R.id.imageUpload})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.uploadImage) {
            mPresenter.uploadImage(mImageUpload);
        } else if (id == R.id.imageUpload) {
            mPresenter.selectImage(mImageUpload);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }
}
