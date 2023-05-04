
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Receptionist extends Person /*implements User*/{
        private String Password;
        public Receptionist(){}
        public Receptionist(String Name,String ID,String Address,String phoneNumber,String Password){
            super( Name, ID, Address, phoneNumber);
            this.Password=Password;
        }

        public String getPassword(){
            return this.Password;
        }


    


    
}
