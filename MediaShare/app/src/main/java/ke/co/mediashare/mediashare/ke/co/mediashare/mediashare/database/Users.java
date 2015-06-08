package ke.co.mediashare.mediashare.ke.co.mediashare.mediashare.database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by guidovanrossum on 6/6/15.
 */
public class Users extends RealmObject {

    @PrimaryKey
    private int user_id;
    private String first_name;
    private String last_name;
    private String email_address;
    private String password;

    // Get User_id
    public int getUser_id() {
        return user_id;
    }

    // Set User_id
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    // Get First_name
    public String getFirst_name() {
        return first_name;
    }

    // Set First_name
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    // Get Last_name
    public String getLast_name() {
        return last_name;
    }

    // Set Last_name
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    // Get Email_address
    public String getEmail_address() {
        return email_address;
    }

    // Set Email_address
    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    // Get Password
    public String getPassword() {
        return password;
    }

    // Set Password
    public void setPassword(String password) {
        this.password = password;
    }
}
