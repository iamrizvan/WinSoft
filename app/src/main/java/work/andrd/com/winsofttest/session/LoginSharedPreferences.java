package work.andrd.com.winsofttest.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import work.andrd.com.winsofttest.LoginActivity;


public class LoginSharedPreferences
{
    public static SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    Bitmap bitmap;

    int PRIVATE_MODE=0;

    private static final String PREF_NAME="loginSharedPreferences";
    private static final String IS_LOGIN="IsLoggedIn";
    public static final String USER_EMAIL="token";
    public static final String USER_ID="login_id";
    public static final String NAME = "location_id";
    public static final String CHECKBOX_STATUS="status";




    // constructor
    public LoginSharedPreferences(Context context)
    {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor=sharedPreferences.edit();
    }


    public void updateLogin(String checkbox)
    {
        // store login value as true
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(CHECKBOX_STATUS,checkbox);
        editor.commit();
    }



    public static String getApplicantId()
    {
        String userId = sharedPreferences.getString(USER_ID,"412");
        return userId;
    }






    public void checkLogin()
    {
        // check login status here

        if (isLoggedIn() == false)
        {
            Intent intent=new Intent(context, LoginActivity.class);

            // closing all the activities
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // add new_dia flag to start new_dia activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);
        }
    }




    public String getUserName()
    {
        String name = sharedPreferences.getString(NAME, "");
        return name;
    }
    public String getUserEmail()
    {
        String email = sharedPreferences.getString(USER_EMAIL, "");
        return email;
    }

    public String getUserId()
    {
        String userId=sharedPreferences.getString(USER_ID,null);
        return userId;
    }



    public String getCheckBoxStatus()
    {

        // store token
        String status=sharedPreferences.getString(CHECKBOX_STATUS,null);

        // return token  yes/no
        return status;

    }









    //   clear session details (logout)
    public void logOutUser()
    {

        // wiping all data from shared preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to login activity
        Intent intent = new Intent(context, LoginActivity.class);

        //closing all the activities
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // craeting a new_dia activity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }


    public void finishSession()
    {
        // wiping all data from shared preferences
        editor.clear();
        editor.commit();

    }




    public boolean isLoggedIn()
    {
        // the second parameter is for alternate value if first hasn't value then second will be the result
        return sharedPreferences.getBoolean(IS_LOGIN,false);
    }
}
