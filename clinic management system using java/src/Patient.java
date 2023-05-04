
public class Patient extends Person{
    private String recentdate="";
    private String admissiondate="";
    private String doctorID="";
    public Patient(){}
    public Patient(String Name,String ID,String Address,String phoneNumber,String recentdate,String admissiondate,String doctorID){
        super(Name, ID, Address, phoneNumber);
        this.recentdate=recentdate;
        this.admissiondate=admissiondate;
        this.doctorID=doctorID;
    }
    public String getrecentdate(){
        return this.recentdate;
    }
    public void setrecentdate(String recentdate){
        this.recentdate=recentdate;
    }
    public String getadmissiondate(){
        return this.admissiondate;
    }
    public void setadmissiondate(String admissiondate){
        this.admissiondate=admissiondate;
    }
    public String getDoctorID(){
        return this.doctorID;
    }
    public void setDoctorID(String doctorID){
        this.doctorID=doctorID;
    }
}
