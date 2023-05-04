
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Admin extends Person /*implements User*/ {
    private String Password;
    public Admin(){
        
    }
    public Admin(String Name,String ID,String Address,String phoneNumber,String Password){
        super( Name, ID, Address, phoneNumber);
        this.Password=Password;
    }
    
        public String getPassword(){
            return this.Password;
        }


   
    public void logout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    





}
