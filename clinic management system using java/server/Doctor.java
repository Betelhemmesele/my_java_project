
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Doctor extends Person implements Serializable {
    private String Password;
    public Doctor(){
        super();
    }
    public Doctor(String Name,String ID,String Address,String phoneNumber,String Password){
        super(Name,ID,Address,phoneNumber);
        this.Password=Password;
    }
    public String getPassword(){
        return this.Password;
    }


    }
