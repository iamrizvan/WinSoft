package work.andrd.com.winsofttest.webservice;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import work.andrd.com.winsofttest.constant.Constant;
import work.andrd.com.winsofttest.model.Result;
import work.andrd.com.winsofttest.model.User;


public interface RetroAPI {
    @FormUrlEncoded
    @POST(Constant.GET_LOGIN)
    Call<User> getLogin(@Field("email") String id,
                        @Field("password") String passworsd);

    @FormUrlEncoded
    @POST(Constant.DO_REGISTER)
    Call<Result> doRegister(@Field("firstname") String firstname,
                            @Field("lastname") String lastname,
                            @Field("email") String email,
                            @Field("password") String password,
                            @Field("phone") String phone,
                            @Field("address") String address,
                            @Field("gender") String gender,
                            @Field("dob") String dob);



}