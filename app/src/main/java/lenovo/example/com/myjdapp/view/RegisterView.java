package lenovo.example.com.myjdapp.view;


public interface RegisterView {

    void nameError(String msg);

    void passError(String msg);

    void registerSuccess(String code, String msg);

    void registerFail(String code, String msg);

}
