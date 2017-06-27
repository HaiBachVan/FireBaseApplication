package app.demo.firebase.ui.register;

class RegisterContract {

    interface IRegisterView {

    }

    interface IRegisterPresenter {

        void createUserWithEmailAndPassword(String email, String password);

    }

}
