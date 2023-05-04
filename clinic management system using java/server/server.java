import java.io.*;
import java.net.*;
import java.sql.*;

import com.mysql.cj.protocol.Resultset;

public class server {

   public static void main(String[] args) {
    transferedData data= new transferedData();
    try{
   ServerSocket server = new ServerSocket(5001);
while(true)
{
   System.out.println("waiting");
Socket conn = server.accept(); 
System.out.println("connected");
try{
  
    ObjectInputStream serverInputStream = new  ObjectInputStream(conn.getInputStream());
    
 ObjectOutputStream serverOutputStream = new ObjectOutputStream(conn.getOutputStream());
 
 data = (transferedData)serverInputStream.readObject();
 try{
   
   Class.forName("com.mysql.cj.jdbc.Driver");
   
   
   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinic","root","15511551aA/");
   switch (data.action)
   {
       case "login":
       {
   java.sql.Statement st=con.createStatement();
   String adminStatement;
   adminStatement = "select * from admin";
   String doctorStatement;
   doctorStatement = "select * from doctor";
   String receptionistStatement;
   receptionistStatement="select * from receptionist";
   ResultSet rs;
   switch(data.job){
       default :{
data.returned=false;
serverOutputStream.writeObject(data);
break;
       }
       case "admin":
       {
           rs=st.executeQuery(adminStatement);
           while(rs.next()){
               if(rs.getString("name").equals(data.username)&&rs.getString("password").equals(data.password)){
                   
                   data.returned=true;
                   data.id=rs.getString("id");
                   data.password=rs.getString("password");

                   
                   break;
               }
           }
       break;
        }
       case "doctor":
       {
           rs=st.executeQuery(doctorStatement);
           while(rs.next()){
               if(rs.getString("name").equals(data.username)&&rs.getString("password").equals(data.password)){
                   
                   data.returned=true;
                   data.id=rs.getString("id");
                   data.password=rs.getString("password");

                   break;
               }
           }
       break;
        }
           
       case "receptionist":
       {
           rs=st.executeQuery(receptionistStatement);
           while(rs.next()){
               if(rs.getString("name").equals(data.username)&&rs.getString("password").equals(data.password)){
                   
                   data.returned=true;
                   data.id=rs.getString("id");
                   data.password=rs.getString("password");
                   break;
               }
           }
       break;
        }
        
   }
  
break;
}
case "addadmin":
{
   
    java.sql.Statement st=con.createStatement();
    String adminStatement;
    adminStatement = "insert into admin (id,name,phonenumber,address,password) values (\'"
                    +data.adminID+"\',\'"+data.adminname+"\',\'"+data.adminphoneNumber+"\',\'"
                    +data.adminAddress+"\',\'"+data.adminpassword+"\')";
                    st.executeUpdate(adminStatement);
data.returned=true;
 break;
}
case "adddoctor":
{
    java.sql.Statement st=con.createStatement();
    String doctorStatement;
    doctorStatement = "insert into doctor (id,name,phonenumber,address,password) values (\'"
    +data.doctorID+"\',\'"+data.doctorname+"\',\'"+data.doctorphoneNumber+"\',\'"
    +data.doctorAddress+"\',\'"+data.doctorpassword+"\')";
    st.executeUpdate(doctorStatement);
    data.returned=true;
 break;
                }
case "addreceptionist":
{
    java.sql.Statement st=con.createStatement();
    String receptionistStatement;
    receptionistStatement = "insert into receptionist (id,name,phonenumber,address,password) values (\'"
    +data.receptionistID+"\',\'"+data.receptionistname+"\',\'"+data.receptionistphoneNumber+"\',\'"
    +data.receptionistAddress+"\',\'"+data.receptionistpassword+"\')";
    st.executeUpdate(receptionistStatement);

    data.returned=true;
    break;
}
case "addpatient":
{
    java.sql.Statement st=con.createStatement();
    String doctorStatement;
    String patientStatement;
    doctorStatement = "select * from doctor";
    ResultSet rs;
    rs=st.executeQuery(doctorStatement);
    
    while(rs.next()){
        
      if(rs.getString("id").equals(data.patientdoctorid))
        {
        patientStatement = "insert into patient (id,name,phonenumber,address,admissiondate,recentdate,doctorid) values (\'"
        +data.patientID+"\',\'"+data.patientname+"\',\'"+data.patientphoneNumber+"\',\'"
        +data.patientAddress+"\',\'"+data.patientadmissiondate+"\',\'"+data.patientrecentdate+"\',\'"+data.patientdoctorid+"\')";
       
        st.executeUpdate(patientStatement);
        data.returned=true;
        break;
    }
}

   
    
    break;
}
case "viewdoctor":
{
    
    java.sql.Statement st=con.createStatement();
                        String doctorStatement;
                        doctorStatement = "select * from doctor";
                        ResultSet rs;
                        rs=st.executeQuery(doctorStatement);
                        while(rs.next()){
                            if(rs.getString("id").equals(data.doctorID)){
                                data.doctorAddress=rs.getString("address");
                                data.doctorname=rs.getString("name");
                                data.doctorphoneNumber=rs.getString("phonenumber");
                                data.returned=true;
                            
                             break;
                            }
                        }
    break;


}
case "viewadmin":
{
    java.sql.Statement st=con.createStatement();
                        String adminStatement;
                        adminStatement = "select * from admin";
                        ResultSet rs;
                        rs=st.executeQuery(adminStatement);
                        while(rs.next()){
                            if(rs.getString("id").equals(data.adminID)){
                                data.adminAddress=rs.getString("address");
                                data.adminname=rs.getString("name");
                                data.adminphoneNumber=rs.getString("phonenumber");
                                data.returned=true;
                            
                             break;
                            }
                        }
    break;

}
case "viewreceptionist":
{
    java.sql.Statement st=con.createStatement();
                        String receptionistStatement;
                        receptionistStatement = "select * from receptionist";
                        ResultSet rs;
                        rs=st.executeQuery(receptionistStatement);
                        while(rs.next()){
                            if(rs.getString("id").equals(data.receptionistID)){
                                data.receptionistAddress=rs.getString("address");
                                data.receptionistname=rs.getString("name");
                                data.receptionistphoneNumber=rs.getString("phonenumber");
                                data.returned=true;
                            
                             break;
                            }
                        }
                        break;
}
case "viewpatient":
{
    java.sql.Statement st = con.createStatement();
                    String patientStatement;
                    patientStatement = "select * from patient";
                    ResultSet rs;
                    rs = st.executeQuery(patientStatement);
                    while (rs.next()) {
                        if (rs.getString("id").equals(data.patientID)) {
                            data.returned = true;
                            
                            data.patientname=rs.getString("name");
                            data.patientID= rs.getString("id");
                            data.patientAddress=rs.getString("address");
                            data.patientphoneNumber=rs.getString("phonenumber");
                            data.patientrecentdate=rs.getString("recentdate");
                            data.patientadmissiondate=rs.getString("admissiondate");
                                   data.patientdoctorid= rs.getString("doctorid");
                            break;
                        }
                    }
}
case "changepasswordadmin":
{
    java.sql.Statement st=con.createStatement();
                    String update;
                    update = "UPDATE admin SET password = \'"+data.newpassword+"\' WHERE name = \'"+data.username+"\' AND id = \'"+data.id+"\' AND password = \'"+data.password+"\' ";
                    st.executeUpdate(update);
                    System.out.println(data.newpassword);
                    System.out.println(data.id);
                    System.out.println(data.password);
                    data.password=data.newpassword;
                    data.returned=true;
                    break;
}
case "changepassworddoctor":
{
    java.sql.Statement st=con.createStatement();
                    String update;
                    update = "UPDATE doctor SET password = \'"+data.newpassword+"\' WHERE name = \'"+data.username+"\' AND id = \'"+data.id+"\' AND password = \'"+data.password+"\' ";
                    st.executeUpdate(update);
                    System.out.println(data.newpassword);

                    data.password=data.newpassword;
                    data.returned=true;
                    break;
}
case "changepasswordreceptionist":
{
    java.sql.Statement st=con.createStatement();
                    String update;
                    update = "UPDATE receptionist SET password = \'"+data.newpassword+"\' WHERE name = \'"+data.username+"\' AND id = \'"+data.id+"\' AND password = \'"+data.password+"\' ";
                    st.executeUpdate(update);
                    data.password=data.newpassword;
                    data.returned=true;
                    break;
}
}
}
catch(SQLException e){
   System.out.println(e);
   data.returned=false;
}
 
serverOutputStream.writeObject(data);
}
catch (Exception e)
{
   System.out.println(e);
}
    
   

}
   }
   catch (Exception e)
   {

   }
}

   
}
 
