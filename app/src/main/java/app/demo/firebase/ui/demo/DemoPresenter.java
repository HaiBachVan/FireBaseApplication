package app.demo.firebase.ui.demo;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import app.demo.firebase.model.Student;

import static android.content.ContentValues.TAG;

class DemoPresenter implements DemoContract.IDemoPresenter {

    private DatabaseReference mDatabaseReference;
    FirebaseAuth mAuth;
    private DemoActivity mMainActivity;

    DemoPresenter(DemoActivity mainActivity, DatabaseReference databaseReference, FirebaseAuth auth) {
        this.mMainActivity = mainActivity;
        this.mDatabaseReference = databaseReference;
        this.mAuth = auth;
    }

    @Override
    public void setValueString() {
        mDatabaseReference.child("Name").setValue("NewTon");
    }

    @Override
    public void setValueWithObject() {
        Student student = new Student("NewTon", "USA", 1900);
        mDatabaseReference.child("Student").setValue(student);
    }

    @Override
    public void setValueWithMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Motorbike", 2);
        mDatabaseReference.child("Vehicle").setValue(map);
    }

    @Override
    public void setValueWithPush() {
        Student stu = new Student("Albert", "English", 1800);
        mDatabaseReference.child("Teacher").push().setValue(stu);
    }

    @Override
    public void onCompletionListener() {
        Student student1 = new Student("Mama", "Portugal", 1800);
        mDatabaseReference.child("Teacher").push().setValue(student1, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    Toast.makeText(mMainActivity, "Save complete", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mMainActivity, "Save error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void addValueEventListener() {
        mDatabaseReference.child("Teacher").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Student student = dataSnapshot.getValue(Student.class);
                if (student != null) {
                    Toast.makeText(mMainActivity, student.getName(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void addChildEventListener() {
        mDatabaseReference.child("Teacher").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Student student = dataSnapshot.getValue(Student.class);
                if (student != null) {
                    Toast.makeText(mMainActivity, student.getName(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
