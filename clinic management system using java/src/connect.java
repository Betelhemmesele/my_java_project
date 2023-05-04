import java.io.*;
import java.net.*;
public class connect {
    
   static String host="localhost";
   static int port=5001;
   
    public static boolean connectlogin ( String username,String password,String job)
    {
      boolean returned=false;
      transferedData  data=new transferedData();
            data.action  ="login";
      data.username=username;
      data.password=password;
      data.job=job;
      
      try{
        System.out.println("connecting..");
        Socket socket = new Socket(host, port );
      
    
 ObjectOutputStream clientOutputStream = new ObjectOutputStream(socket.getOutputStream());
clientOutputStream.writeObject(data);
 ObjectInputStream clientInputStream = new  ObjectInputStream(socket.getInputStream());
data= (transferedData)clientInputStream.readObject();
 returned= data.returned;
 GUI.id=data.id;
 GUI.Password=data.password;


      }
    catch (Exception e)
    {
  System.out.println("failed to connect");
    }
        
  return returned;
  }
   
  public static boolean  adddoctor(Doctor doctor)
  {
  
    
    transferedData  data =new transferedData();

    boolean returned=false;
    data.doctorAddress=doctor.getAddress();
    data.doctorID=doctor.getID();
    data.doctorname=doctor.getName();
    data.doctorpassword=doctor.getPassword();
    data.doctorphoneNumber=doctor.getPhoneNumber();
  data.action  ="adddoctor";
  
  
  try{
    System.out.println("connecting..");
    Socket socket = new Socket(host, port );
  

ObjectOutputStream clientOutputStream = new ObjectOutputStream(socket.getOutputStream());
clientOutputStream.writeObject(data);
ObjectInputStream clientInputStream = new  ObjectInputStream(socket.getInputStream());
data= (transferedData)clientInputStream.readObject();
returned= data.returned;


  }
catch (Exception e)
{
System.out.println("failed to connect");
}
    
return returned;
  }
  public static boolean  addreceptionist(Receptionist receptionist)
  {
    
    transferedData  data=new transferedData();
    
    data=new transferedData();
    boolean returned=false;
  
    data.receptionistAddress=receptionist.getAddress();
    data.receptionistID=receptionist.getID();
    data.receptionistname=receptionist.getName();
    data.receptionistpassword=receptionist.getPassword();
    data.receptionistphoneNumber=receptionist.getPhoneNumber();
  data.action  ="addreceptionist";
  
  
  try{
    System.out.println("connecting..");
    Socket socket = new Socket(host, port );
  

ObjectOutputStream clientOutputStream = new ObjectOutputStream(socket.getOutputStream());
clientOutputStream.writeObject(data);
ObjectInputStream clientInputStream = new  ObjectInputStream(socket.getInputStream());
data= (transferedData)clientInputStream.readObject();
returned= data.returned;

socket.close();
  }
catch (Exception e)
{
  System.out.println("failed to connect");
}
    
return returned;
  }
public static boolean  addadmin(Admin admin)
  {
    
    
    

    boolean returned=false;
    transferedData data=new transferedData();

    data.adminAddress=admin.getAddress();
    data.adminID=admin.getID();
    data.adminname=admin.getName();
    data.adminpassword=admin.getPassword();
    data.adminphoneNumber=admin.getPhoneNumber();
  data.action  ="addadmin";
  
  
  try{
    System.out.println("connecting..");
    Socket socket = new Socket(host, port );
  

ObjectOutputStream clientOutputStream = new ObjectOutputStream(socket.getOutputStream());
clientOutputStream.writeObject(data);
ObjectInputStream clientInputStream = new  ObjectInputStream(socket.getInputStream());
data= (transferedData)clientInputStream.readObject();
returned= data.returned;
data=new transferedData();

  }
catch (Exception e)
{
  System.out.println("failed to connect");
}
    
return returned;
  }

  public static boolean  addpatient(Patient patient)
  {
    
    
    

    boolean returned=false;
    transferedData data=new transferedData();

    data.patientAddress=patient.getAddress();
    data.patientID=patient.getID();
    data.patientname=patient.getName();
    data.patientrecentdate=patient.getrecentdate();
    data.patientadmissiondate= patient.getadmissiondate();
    data.patientphoneNumber=patient.getPhoneNumber();
    data.patientdoctorid=patient.getDoctorID();
  data.action  ="addpatient";
  
  
  try{
    System.out.println("connecting..");
    Socket socket = new Socket(host, port );
  

ObjectOutputStream clientOutputStream = new ObjectOutputStream(socket.getOutputStream());
clientOutputStream.writeObject(data);
ObjectInputStream clientInputStream = new  ObjectInputStream(socket.getInputStream());
data= (transferedData)clientInputStream.readObject();
returned= data.returned;
data=new transferedData();

  }
catch (Exception e)
{
  System.out.println("failed to connect");
}
    
return returned;
  }

  public static boolean changepassword(String username,String password,String newpassword, String job)
  {
    boolean returned=false;
    transferedData data=new transferedData();

   
  data.action  ="changepassword"+job;
  data.username=username;
  data.password=password;
  data.newpassword=newpassword;
  data.id= GUI.id;


  
  
  try{
    System.out.println("connecting..");
    Socket socket = new Socket(host, port );
  

ObjectOutputStream clientOutputStream = new ObjectOutputStream(socket.getOutputStream());
clientOutputStream.writeObject(data);
ObjectInputStream clientInputStream = new  ObjectInputStream(socket.getInputStream());
data= (transferedData)clientInputStream.readObject();
returned= data.returned;
GUI.id=data.id;
 GUI.Password=data.password;



  }
catch (Exception e)
{
  System.out.println("failed to connect");
}
    
return returned;
  }
public static Doctor viewdoctor(String id){
  transferedData  data=new transferedData();
    
  data=new transferedData();
  boolean returned=false;

  data.doctorID=id;
data.action  ="viewdoctor";


try{
  System.out.println("connecting..");
  Socket socket = new Socket(host, port );


ObjectOutputStream clientOutputStream = new ObjectOutputStream(socket.getOutputStream());
clientOutputStream.writeObject(data);
ObjectInputStream clientInputStream = new  ObjectInputStream(socket.getInputStream());
data= (transferedData)clientInputStream.readObject();
returned= data.returned;


}
catch (Exception e)
{
System.out.println("failed to connect");
}
  
if (returned)
{
  
  return new Doctor(data.doctorname, data.doctorID, data.doctorAddress, data.doctorphoneNumber, null);
}
else 
{

return null;
}
}
public static Admin viewadmin(String id){
  transferedData  data=new transferedData();
    
  data=new transferedData();
  boolean returned=false;

  data.adminID=id;
data.action  ="viewadmin";


try{
  System.out.println("connecting..");
  Socket socket = new Socket(host, port );


ObjectOutputStream clientOutputStream = new ObjectOutputStream(socket.getOutputStream());
clientOutputStream.writeObject(data);
ObjectInputStream clientInputStream = new  ObjectInputStream(socket.getInputStream());
data= (transferedData)clientInputStream.readObject();
returned= data.returned;
socket.close();

}
catch (Exception e)
{
System.out.println("failed to connect");
}
  
if (returned)
{
  
  return new Admin(data.adminname, data.adminID, data.adminAddress, data.adminphoneNumber, null);
}
else 
{

return null;
}

  
  }
  public static Receptionist viewreceptionist(String id){

    transferedData  data=new transferedData();
    
  data=new transferedData();
  boolean returned=false;

  data.receptionistID=id;
data.action  ="viewreceptionist";


try{
  System.out.println("connecting..");
  Socket socket = new Socket(host, port );


ObjectOutputStream clientOutputStream = new ObjectOutputStream(socket.getOutputStream());
clientOutputStream.writeObject(data);
ObjectInputStream clientInputStream = new  ObjectInputStream(socket.getInputStream());
data= (transferedData)clientInputStream.readObject();
returned= data.returned;
socket.close();

}
catch (Exception e)
{
System.out.println("failed to connect");
}
  
if (returned)
{
  
  return new Receptionist(data.receptionistname, data.receptionistID, data.receptionistAddress, data.receptionistphoneNumber, null);
}
else 
{

return null;
}
    }
public static Patient viewpatient(String id )
{
  transferedData  data=new transferedData();
    
  data=new transferedData();
  boolean returned=false;

  data.patientID=id;
data.action  ="viewpatient";


try{
  System.out.println("connecting..");
  Socket socket = new Socket(host, port );


ObjectOutputStream clientOutputStream = new ObjectOutputStream(socket.getOutputStream());
clientOutputStream.writeObject(data);
ObjectInputStream clientInputStream = new  ObjectInputStream(socket.getInputStream());
data= (transferedData)clientInputStream.readObject();
returned= data.returned;
socket.close();

}
catch (Exception e)
{
System.out.println("failed to connect");
}
  
if (returned)
{
  
  return new Patient(data.patientname, data.patientID, data.patientID, data.patientphoneNumber, data.patientrecentdate, data.patientadmissiondate, data.patientdoctorid);
}
else 
{

return null;
}
}

}

