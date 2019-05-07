package work.andrd.com.winsofttest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class User {


    @Expose
    @SerializedName("data")
    private List<Data> data;
    @Expose
    @SerializedName("msg")
    private String msg;
    @Expose
    @SerializedName("error")
    private boolean error;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public static class Data {
        @Expose
        @SerializedName("DOB")
        private String DOB;
        @Expose
        @SerializedName("Gender")
        private String Gender;
        @Expose
        @SerializedName("Address")
        private String Address;
        @Expose
        @SerializedName("Phone")
        private String Phone;
        @Expose
        @SerializedName("Password")
        private String Password;
        @Expose
        @SerializedName("Email")
        private String Email;
        @Expose
        @SerializedName("LastName")
        private String LastName;
        @Expose
        @SerializedName("FirstName")
        private String FirstName;
        @Expose
        @SerializedName("id")
        private String id;

        public String getDOB() {
            return DOB;
        }

        public void setDOB(String DOB) {
            this.DOB = DOB;
        }

        public String getGender() {
            return Gender;
        }

        public void setGender(String gender) {
            Gender = gender;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String phone) {
            Phone = phone;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String password) {
            Password = password;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }

        public String getLastName() {
            return LastName;
        }

        public void setLastName(String lastName) {
            LastName = lastName;
        }

        public String getFirstName() {
            return FirstName;
        }

        public void setFirstName(String firstName) {
            FirstName = firstName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
