package app.demo.firebase.ui.register;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import app.demo.firebase.ui.main.MainActivity;

import static android.content.ContentValues.TAG;

public class RegisterPresenter implements RegisterContract.IRegisterPresenter {

    private DatabaseReference mDatabaseReference;
    FirebaseAuth mAuth;
    private MainActivity mMainActivity;

    RegisterPresenter(MainActivity mainActivity, DatabaseReference databaseReference, FirebaseAuth auth) {
        this.mMainActivity = mainActivity;
        this.mDatabaseReference = databaseReference;
        this.mAuth = auth;
    }

    @Override
    public void createUserWithEmailAndPassword(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(mMainActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(mMainActivity, "Create account success.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                Log.d(TAG, "Email = " + user.getEmail());
                            }

                            mMainActivity.showUploadImageActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(mMainActivity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
