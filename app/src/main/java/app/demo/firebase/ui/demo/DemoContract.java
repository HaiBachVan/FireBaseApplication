package app.demo.firebase.ui.demo;

class DemoContract {

    interface IDemoView {

        void showUploadImageActivity();

    }

    interface IDemoPresenter {

        //+ START: demo
        void setValueString();

        void setValueWithObject();

        void setValueWithMap();

        void setValueWithPush();

        void onCompletionListener();

        void addValueEventListener();

        void addChildEventListener();
        //- END: demo

        //+ START: method - register account
        void createUserWithEmailAndPassword(String email, String password);
        //- END: method - register account

    }

}
