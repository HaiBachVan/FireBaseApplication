package app.demo.firebase.ui.upload;

import android.widget.ImageView;

class UploadImageContract {

    interface IUploadImageView {

    }

    interface IUploadImagePresenter {

        void selectImage(ImageView imageUpload);

        void uploadImage(ImageView imageUpload);

    }

}
