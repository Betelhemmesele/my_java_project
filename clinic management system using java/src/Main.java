import java.sql.*;

public class Main {

    public static int LoginPageDB(String username,String password,String job){
        int page=-1;
        boolean userIsFound=connect.connectlogin(username,password,job);
        if(userIsFound==false){
            GUI.hiddenWrongLogin.setVisible(true);
        }
        if(userIsFound==true){
            GUI.hiddenWrongLogin.setVisible(false);
        switch (job)
        {
            case "receptionist":
            {
                page=3;
                break;
            }
            case "doctor":
            {
                page=2;
                break;
            }
            case "admin":
            {
                page=1;
                break;
            }
        }
    }
    return page;
    }
  
    
    public static void main(String[] args) {
        
        GUI myFrame=new GUI();
            int page;
            myFrame.LoginPage();
            
            while((myFrame.Username.equals("")&&myFrame.Password.equals("")&&myFrame.Job.equals("")
                    )||(LoginPageDB(myFrame.Username,myFrame.Password,myFrame.Job)==-1))
                    {
                
                System.out.println("waiting");
            }
            page=LoginPageDB(myFrame.Username,myFrame.Password,myFrame.Job);

            switch(page){
                case 1:
                {
                    System.out.println("Admin Page");
                    myFrame.AdminPage(myFrame.Username);
                    break;
                }
                case 2:
                {
                    System.out.println("Doctor Page");
                    myFrame.doctorPage(myFrame.Username);
                    break;
                }
                case 3:
                   { System.out.println("Receptionist Page");
                    myFrame.receptionistPage(myFrame.Username);
                    break;}
            }        
    }
    
}
