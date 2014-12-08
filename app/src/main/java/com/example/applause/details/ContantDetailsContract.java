package com.example.applause.details;


public interface ContantDetailsContract {

    interface View {

        void setHeader(String title);

        void setFirstName(String firstName);

        void setLastName(String lastName);

        void setEmail(String email);

        void loadPicture(String url);

        void setLocationStreet(String street);

        void setLocationCity(String city);

        void setLocationState(String state);

        void setLocationZip(String zip);

        void setLocationMap(String loadUrl);

        void setEasterEgg(android.view.View.OnTouchListener onTouchListener);
    }

    interface Presenter {
        void bind(View view);

        void unbind();
    }


}
