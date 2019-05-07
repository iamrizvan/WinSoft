package work.andrd.com.winsofttest;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import work.andrd.com.winsofttest.model.Result;
import work.andrd.com.winsofttest.webservice.RetroConfig;

public class RegistrationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    @BindView(R.id.profile_image)
    ImageView profileImage;
    @BindView(R.id.upload_image_text)
    TextView uploadImageText;
    @BindView(R.id.first_name)
    EditText firstName;
    @BindView(R.id.last_name)
    EditText lastName;
    @BindView(R.id.email_edt)
    EditText emailEdt;
    @BindView(R.id.password_edt)
    EditText passwordEdt;
    @BindView(R.id.phone_edt)
    EditText phoneEdt;
    @BindView(R.id.address_edt)
    EditText addressEdt;
    @BindView(R.id.gender_edt)
    EditText genderEdt;
    @BindView(R.id.dob_edt)
    EditText dobEdt;
    @BindView(R.id.registration_btn)
    Button registrationBtn;
    @BindView(R.id.login_textview)
    TextView loginTextview;


    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);


        dobEdt.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {

                if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                {
                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(RegistrationActivity.this,RegistrationActivity.this,mYear,mMonth,mDay);
                    DatePicker datepicker=datePickerDialog.getDatePicker();
                    mYear=datepicker.getYear();
                    mMonth=datepicker.getMonth();
                    mDay=datepicker.getDayOfMonth();

                    datePickerDialog.show();
                }
                return true;
            }
        });

    }

    @OnClick({R.id.registration_btn, R.id.login_textview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.registration_btn:
            {
                if (!firstName.getText().toString().equalsIgnoreCase("")
                && !lastName.getText().toString().equalsIgnoreCase("")
                && !emailEdt.getText().toString().equalsIgnoreCase("")
                && !passwordEdt.getText().toString().equalsIgnoreCase("")
                && !phoneEdt.getText().toString().equalsIgnoreCase("")
                && !addressEdt.getText().toString().equalsIgnoreCase("")
                && !genderEdt.getText().toString().equalsIgnoreCase("")
                && !dobEdt.getText().toString().equalsIgnoreCase(""))
                {
                    Call<Result> call = RetroConfig.retrofit().doRegister(firstName.getText().toString(),
                            lastName.getText().toString(),
                            emailEdt.getText().toString(),
                            passwordEdt.getText().toString(),
                            phoneEdt.getText().toString(),
                            addressEdt.getText().toString(),
                            genderEdt.getText().toString(),
                            dobEdt.getText().toString());
                    call.enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {

                            if (response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),"Registration Success", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Not successful",Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Details are missing.", Toast.LENGTH_SHORT).show();
                }
            }
                break;
            case R.id.login_textview:
            {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
        int m=0;
        m=month+1;
        String datestring=String.valueOf(m)+"/"+String.valueOf(date)+"/"+String.valueOf(year);
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
        //   String dateString=sdf.format(datestring);

        dobEdt.setText(datestring);
    }
}