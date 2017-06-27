package app.demo.firebase.ui.main;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

class MainPresenter implements MainContract.IMainPresenter {

    private DatabaseReference mDatabaseReference;
    FirebaseAuth mAuth;
    private MainActivity mMainActivity;

    MainPresenter(MainActivity mainActivity, DatabaseReference databaseReference, FirebaseAuth auth) {
        this.mMainActivity = mainActivity;
        this.mDatabaseReference = databaseReference;
        this.mAuth = auth;
    }

}
