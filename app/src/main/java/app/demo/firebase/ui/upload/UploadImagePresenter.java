package app.demo.firebase.ui.upload;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import app.demo.firebase.model.ImageUpload;

class UploadImagePresenter implements UploadImageContract.IUploadImagePresenter {

    private static final String TAG = "UploadImagePresenter";

    private StorageReference mStorageRef;
    private UploadImageActivity mActivity;
    private DatabaseReference mDatabaseReference;

    UploadImagePresenter(UploadImageActivity activity, StorageReference storageRef, DatabaseReference databaseReference) {
        this.mActivity = activity;
        this.mStorageRef = storageRef;
        this.mDatabaseReference = databaseReference;
    }

    @Override
    public void selectImage(ImageView imageUpload) {

    }

    @Override
    public void uploadImage(ImageView imageUpload) {
        // Get the data from an ImageView as bytes
        imageUpload.setDrawingCacheEnabled(true);
        imageUpload.buildDrawingCache();

        Bitmap bitmap = imageUpload.getDrawingCache();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] data = byteArrayOutputStream.toByteArray();

        final String name = "image" + Calendar.getInstance().getTimeInMillis() + ".png";
        StorageReference mImageRef = mStorageRef.child(name);

        UploadTask uploadTask = mImageRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(mActivity, "Upload image fail!!",
                        Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                Log.d(TAG, "Upload image success with url = " + downloadUrl);

                ImageUpload image = new ImageUpload(name, String.valueOf(downloadUrl));
                mDatabaseReference.child("Image").push().setValue(image, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError == null) {
                            Toast.makeText(mActivity, "Save image success",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

}
