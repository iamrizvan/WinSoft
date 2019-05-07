package work.andrd.com.winsofttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import work.andrd.com.winsofttest.constant.Constant;
import work.andrd.com.winsofttest.model.User;
import work.andrd.com.winsofttest.session.LoginSharedPreferences;
import work.andrd.com.winsofttest.webservice.RetroAPI;
import work.andrd.com.winsofttest.webservice.RetroConfig;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.login_email_edittext)
    EditText loginEmailEdittext;
    @BindView(R.id.login_email_textinputlayout)
    TextInputLayout loginEmailTextinputlayout;
    @BindView(R.id.login_pass_edittext)
    EditText loginPassEdittext;
    @BindView(R.id.login_password_textinputlayout)
    TextInputLayout loginPasswordTextinputlayout;
    @BindView(R.id.remember_check)
    CheckBox rememberCheck;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.forget_password_textview)
    TextView forgetPasswordTextview;
    @BindView(R.id.register_textview)
    TextView registerTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.login_btn,R.id.register_textview, R.id.forget_password_textview})
    public void onViewClicked(View view) {

        switch (view.getId())
        {
            case R.id.login_btn:
            {
                if (!loginEmailEdittext.getText().toString().equalsIgnoreCase("")
                && !loginPassEdittext.getText().toString().equalsIgnoreCase(""))
                {
                    retrofit2.Call<User> call = RetroConfig.retrofit().getLogin(loginEmailEdittext.getText().toString(),
                            loginPassEdittext.getText().toString());
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(retrofit2.Call<User> call, Response<User> response) {

                            if (response.isSuccessful()) {

                                new LoginSharedPreferences(LoginActivity.this).updateLogin("yes");
                                User.Data model = response.body().getData().get(0);

                                String firstName = model.getFirstName();
                                String lastName = model.getLastName();
                                String email = model.getEmail();
                                String phone = model.getPhone();
                                String Address = model.getAddress();
                                String Gender = model.getGender();
                                String DOB = model.getDOB();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra(Constant.FIRST_NAME,firstName);
                                intent.putExtra(Constant.LAST_NAME,lastName);
                                intent.putExtra(Constant.EMAIL,email);
                                intent.putExtra(Constant.PHONE,phone);
                                intent.putExtra(Constant.ADDRESS,Address);
                                intent.putExtra(Constant.GENDER,Gender);
                                intent.putExtra(Constant.DATE_OF_BIRTH,DOB);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(),firstName+" Login success.",Toast.LENGTH_SHORT).show();
                                finish();

                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Not successful",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please enter details",Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case R.id.register_textview:
            {
               startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
            break;
            case R.id.forget_password_textview:
            {
                Toast.makeText(getApplicationContext(), "Forget password clicked.",Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }
}
