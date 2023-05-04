import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.*;
import java.sql.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {
    boolean visiblity = true;

    public GUI() {
        window = new JFrame("clinic managment");
        tabbedPane = new JTabbedPane();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setJMenuBar(menuBar);
        settings.add(changepassword);
        settings.add(logout);
        menuBar.add(settings);
        window.add(tabbedPane);
        JMenuItem info= new JMenuItem("info");
        JMenu About = new JMenu("About");
        About.add(info);
        menuBar.add(About);
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                String members =" clinic managment system 1.0 \n\n \t Authors \n \tsection A\n Daniel Mekuria  ETS 0210/12\n Dandi Abeya ETS 0207/12\n Kidus Fikru  ETS 0409/12\n Betelhem Mesele  ETS 0138/12 \n Abel Tegegn  ETS 0028/12 \n Betelhem Melkamu ETS 0139/12 \n"; 
                JOptionPane.showMessageDialog(null, members, "About", JOptionPane.PLAIN_MESSAGE);
              
            }
            
        });
        window.setFont(new Font("Arial", Font.BOLD, 30));
        window.setBounds(100, 0, 800, 900);
// tabbedPane.addChangeListener(new ChangeListener() {
//     @Override
    
//     public void stateChanged(ChangeEvent e) {
//    for( int count= tabbedPane.getTabCount() -1;count>=0;count--)
//    {
//        tabbedPane.removeTabAt(count);
//    }
//        AdminPage(Username);
        
//     }
// });

        logout.addActionListener(this);
        changepassword.addActionListener(this);
    }

    // Login Page Start
    JTabbedPane tabbedPane;
    JFrame window;
    JMenuBar menuBar = new JMenuBar();
    JMenu settings = new JMenu("Settings");
    JMenuItem changepassword = new JMenuItem("Change password");
    JMenuItem logout = new JMenuItem("Logout");
    JFrame logInFrame;
    JLabel loginHeaderLabel;
    JLabel usernameLabel;

    JTextField usernameTextField;
    JLabel passwordLabel;

    JPasswordField passwordTextField;
    JLabel jobLabel;

    JRadioButton adminRadioButton;
    JRadioButton doctorRadioButton;
    JRadioButton receptionistRadioButton;
    ButtonGroup jobButtonGroup;
    JButton loginButton;
    static JLabel hiddenWrongLogin;

    public void LoginPage() {
        // this Variable is used to
        // choose the role of the user(admin,doctor,receptionist)
        // and then take the user to the page of it's role
        Username = "";
        Password = "";
        Job = "";
        logInFrame = new JFrame();
        logInFrame.setTitle("Login Page");
        logInFrame.setSize(500, 600);
        logInFrame.setLocation(600, 100);
        logInFrame.setResizable(false);
        logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logInFrame.setLayout(null);

        loginHeaderLabel = new JLabel("Login");
        loginHeaderLabel.setBounds(210, 20, 200, 40);
        loginHeaderLabel.setFont(new Font("Arial", Font.BOLD, 30));
        logInFrame.add(loginHeaderLabel);

        usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(110, 120, 110, 30);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        logInFrame.add(usernameLabel);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(230, 115, 230, 40);
        usernameTextField.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
        usernameTextField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        logInFrame.add(usernameTextField);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(110, 220, 200, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        logInFrame.add(passwordLabel);

        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(230, 215, 230, 40);
        passwordTextField.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
        passwordTextField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        logInFrame.add(passwordTextField);

        jobLabel = new JLabel("User job: ");
        jobLabel.setBounds(110, 310, 200, 40);
        jobLabel.setFont(new Font("Arial", Font.BOLD, 20));
        logInFrame.add(jobLabel);

        adminRadioButton = new JRadioButton("Admin");
        adminRadioButton.setBounds(200, 315, 100, 30);
        adminRadioButton.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
        logInFrame.add(adminRadioButton);

        doctorRadioButton = new JRadioButton("Doctor");
        doctorRadioButton.setBounds(350, 315, 90, 30);
        doctorRadioButton.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
        logInFrame.add(doctorRadioButton);

        receptionistRadioButton = new JRadioButton("Receptionist");
        receptionistRadioButton.setBounds(280, 345, 140, 30);
        receptionistRadioButton.setFont(new Font("Arial", Font.ROMAN_BASELINE, 20));
        logInFrame.add(receptionistRadioButton);

        jobButtonGroup = new ButtonGroup();
        jobButtonGroup.add(adminRadioButton);
        jobButtonGroup.add(doctorRadioButton);
        jobButtonGroup.add(receptionistRadioButton);

        loginButton = new JButton("Login");
        loginButton.setBounds(200, 420, 100, 30);
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        logInFrame.add(loginButton);

        hiddenWrongLogin = new JLabel("User is not Found");
        hiddenWrongLogin.setBounds(190, 450, 400, 70);
        hiddenWrongLogin.setFont(new Font("Arial", Font.BOLD, 15));
        hiddenWrongLogin.setForeground(Color.red);
        hiddenWrongLogin.setVisible(false);
        logInFrame.add(hiddenWrongLogin);

        loginButton.addActionListener(this);
        adminRadioButton.addActionListener(this);
        logInFrame.setVisible(true);

    }
    // Login Page End

    // Admin Page Start

    JLabel adminHeaderLabel;
    JLabel adminUsernameLabel;

    JButton addPatient;
    JButton viewReceptionist;
    JButton addReceptionist;

    public void AdminPage(String username) {
     
        logInFrame.setVisible(false);

        window.setVisible(true);
        window.setTitle("Admin :" + username);

        tabbedPane.addTab("View doctor", new JScrollPane(viewDRA("Doctor")));

        tabbedPane.addTab("Add doctor", new JScrollPane(addDRA("Doctor")));

        tabbedPane.addTab("View admin", new JScrollPane(viewDRA("Admin")));
        tabbedPane.addTab("Add admin", new JScrollPane(addDRA("Admin")));

        tabbedPane.addTab("View receptionist", new JScrollPane(viewDRA("Receptionist")));
        tabbedPane.addTab("Add receptionist", new JScrollPane(addDRA("Receptionist")));
        tabbedPane.addTab("View patient", new JScrollPane(viewPatient()));

        tabbedPane.addTab("Add patient", new JScrollPane(AddPatient()));

    }

    // Change password of the user
    JButton confirmButton;
    JPasswordField oldPasswordTextField;
    JPasswordField newPassword1TextField;
    JPasswordField newPassword2TextField;
    JLabel errorLabel;
    JLabel successLabel;

    public void changePassword(String username, String password) {
        JFrame changePasswordFrame;
        changePasswordFrame = new JFrame("Change Password");
        changePasswordFrame.setSize(800, 700);
        changePasswordFrame.setLocation(500, 100);
        changePasswordFrame.setResizable(true);
        changePasswordFrame.setVisible(true);
        changePasswordFrame.setLayout(null);

        JLabel header = new JLabel("Change Password");
        JLabel oldPasswordLabel = new JLabel("Enter your old Password: ");
        JLabel newPassword1Label = new JLabel("Enter your new Password: ");
        JLabel newPassword2Label = new JLabel("Repeat your new Password: ");

        header.setBounds(250, 50, 300, 50);
        header.setFont(new Font("Arial", Font.BOLD, 25));
        changePasswordFrame.add(header);
        oldPasswordLabel.setBounds(80, 150, 250, 50);
        oldPasswordLabel.setFont(new Font("Arial", Font.BOLD, 20));
        changePasswordFrame.add(oldPasswordLabel);
        newPassword1Label.setBounds(80, 250, 270, 50);
        newPassword1Label.setFont(new Font("Arial", Font.BOLD, 20));
        changePasswordFrame.add(newPassword1Label);
        newPassword2Label.setBounds(80, 350, 290, 50);
        newPassword2Label.setFont(new Font("Arial", Font.BOLD, 20));
        changePasswordFrame.add(newPassword2Label);

        oldPasswordTextField = new JPasswordField();
        newPassword1TextField = new JPasswordField();
        newPassword2TextField = new JPasswordField();

        oldPasswordTextField.setBounds(390, 150, 200, 50);
        oldPasswordTextField.setFont(new Font("Arial", Font.BOLD, 20));
        changePasswordFrame.add(oldPasswordTextField);
        newPassword1TextField.setBounds(390, 250, 200, 50);
        newPassword1TextField.setFont(new Font("Arial", Font.BOLD, 20));
        changePasswordFrame.add(newPassword1TextField);
        newPassword2TextField.setBounds(390, 350, 200, 50);
        newPassword2TextField.setFont(new Font("Arial", Font.BOLD, 20));
        changePasswordFrame.add(newPassword2TextField);

        confirmButton = new JButton("Confirm");
        confirmButton.setBounds(330, 450, 130, 50);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 20));
        changePasswordFrame.add(confirmButton);

        errorLabel = new JLabel("Error Found Check your inputs");
        errorLabel.setBounds(310, 500, 300, 50);
        errorLabel.setFont(new Font("Arial", Font.BOLD, 15));
        errorLabel.setForeground(Color.RED);
        errorLabel.setVisible(false);
        changePasswordFrame.add(errorLabel);

        successLabel = new JLabel("Successfully Updated");
        successLabel.setBounds(310, 500, 200, 50);
        successLabel.setFont(new Font("Arial", Font.BOLD, 15));
        successLabel.setForeground(Color.GREEN);
        successLabel.setVisible(false);
        changePasswordFrame.add(successLabel);

        confirmButton.addActionListener(this);

    }

    // Function that adds a doctor or an Admin or A Receptionist
    public JPanel addDRA(String user) {
        JPanel addUserFrame = new JPanel();
        addUserFrame.setSize(800, 800);

        addUserFrame.setLayout(null);

        JLabel header = new JLabel("Add " + user);
        JLabel userUsernameLabel = new JLabel("Enter " + user + " Username: ");
        JTextField userUsernameTextField = new JTextField();
        JLabel userNameLabel = new JLabel("Enter " + user + "'s Name: ");
        JTextField userNameTextField = new JTextField();
        JLabel userIDLabel = new JLabel("Enter " + user + "'s ID: ");
        JTextField userIDTextField = new JTextField();
        JLabel userMobileLabel = new JLabel("Enter " + user + "'s mobile: ");
        JTextField userMobileTextField = new JTextField();
        JLabel userAddressLabel = new JLabel("Enter " + user + "'s Address: ");
        JTextField userAddressTextField = new JTextField();
        JLabel userPasswordLabel1 = new JLabel("Enter " + user + "'s Password: ");
        JPasswordField userPasswordTextField1 = new JPasswordField();
        JLabel userPasswordLabel2 = new JLabel("Confirm " + user + "'s Password: ");
        JPasswordField userPasswordTextField2 = new JPasswordField();
        JButton add = new JButton("Add " + user);
        JLabel error = new JLabel("Error is found check your inputs");
        JLabel success = new JLabel(user + " is successfully added");

        header.setBounds(300, 20, 300, 50);
        header.setFont(new Font("Arial", Font.BOLD, 25));
        addUserFrame.add(header);

        userNameLabel.setBounds(40, 180, 300, 50);
        userNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        addUserFrame.add(userNameLabel);
        userNameTextField.setBounds(400, 180, 300, 50);
        userNameTextField.setFont(new Font("Arial", Font.BOLD, 20));
        addUserFrame.add(userNameTextField);

        userIDLabel.setBounds(40, 260, 300, 50);
        userIDLabel.setFont(new Font("Arial", Font.BOLD, 20));
        addUserFrame.add(userIDLabel);
        userIDTextField.setBounds(400, 260, 300, 50);
        userIDTextField.setFont(new Font("Arial", Font.BOLD, 20));
        addUserFrame.add(userIDTextField);

        userMobileLabel.setBounds(40, 340, 300, 50);
        userMobileLabel.setFont(new Font("Arial", Font.BOLD, 20));
        addUserFrame.add(userMobileLabel);
        userMobileTextField.setBounds(400, 340, 300, 50);
        userMobileTextField.setFont(new Font("Arial", Font.BOLD, 20));
        addUserFrame.add(userMobileTextField);

        userAddressLabel.setBounds(40, 420, 300, 50);
        userAddressLabel.setFont(new Font("Arial", Font.BOLD, 20));
        addUserFrame.add(userAddressLabel);
        userAddressTextField.setBounds(400, 420, 300, 50);
        userAddressTextField.setFont(new Font("Arial", Font.BOLD, 20));
        addUserFrame.add(userAddressTextField);

        userPasswordLabel1.setBounds(40, 500, 300, 50);
        userPasswordLabel1.setFont(new Font("Arial", Font.BOLD, 20));
        addUserFrame.add(userPasswordLabel1);
        userPasswordTextField1.setBounds(400, 500, 300, 50);
        userPasswordTextField1.setFont(new Font("Arial", Font.BOLD, 20));
        addUserFrame.add(userPasswordTextField1);

        userPasswordLabel2.setBounds(40, 580, 300, 50);
        userPasswordLabel2.setFont(new Font("Arial", Font.BOLD, 20));
        addUserFrame.add(userPasswordLabel2);
        userPasswordTextField2.setBounds(400, 580, 300, 50);
        userPasswordTextField2.setFont(new Font("Arial", Font.BOLD, 20));
        addUserFrame.add(userPasswordTextField2);

        add.setBounds(300, 650, 200, 50);
        add.setFont(new Font("Arial", Font.BOLD, 20));
        addUserFrame.add(add);

        error.setBounds(250, 730, 400, 50);
        error.setFont(new Font("Arial", Font.BOLD, 20));
        error.setForeground(Color.red);
        error.setVisible(false);
        addUserFrame.add(error);

        success.setBounds(250, 730, 400, 50);
        success.setFont(new Font("Arial", Font.BOLD, 20));
        success.setForeground(Color.green);
        success.setVisible(false);
        addUserFrame.add(success);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                boolean validInputs = false;
                boolean userAdded = false;

                String name = userNameTextField.getText();
                String ID = userIDTextField.getText();
                String mobile = userMobileTextField.getText();
                String address = userAddressTextField.getText();
                String password1 = userPasswordTextField1.getText();
                String password2 = userPasswordTextField2.getText();
                if (password1.equals(password2) && password1.length() > 0
                        && name.length() > 0 && ID.length() > 0 && mobile.length() > 0 && address.length() > 0) {
                    validInputs = true;
                    System.out.println("Add Button worked");
                }
                Admin a = new Admin();
                Doctor d = new Doctor();
                Receptionist r = new Receptionist();

                if (user.equals("Admin") && validInputs) {
                    System.out.println("admin add");
                    a = new Admin(name, ID, address, mobile, password1);
                    userAdded = connect.addadmin(a);
                } else if (user.equals("Doctor") && validInputs) {
                    d = new Doctor(name, ID, address, mobile, password1);
                    userAdded = connect.adddoctor(d);
                } else if (user.equals("Receptionist") && validInputs) {
                    r = new Receptionist(name, ID, address, mobile, password1);
                    userAdded = connect.addreceptionist(r);
                }

                if (userAdded) {
                    error.setVisible(false);
                    success.setVisible(true);
                } else {
                    error.setVisible(true);
                    success.setVisible(false);
                }
            }
        });
        return addUserFrame;
    }

    // Admin Buttons A function that is used to display the information of the
    // doctor, Admin and receptionist
    public JPanel viewDRA(String user){
        JPanel viewUserFrame=new JPanel();
        viewUserFrame.setSize(800,800);
                                                 
        viewUserFrame.setLayout(null);
        
        JLabel header=new JLabel("View "+user);
        JLabel userIDLabel=new JLabel("Enter "+user+"'s ID: ");
        JTextField userIDTextField=new JTextField();

                
        header.setBounds(300,20,300,50);
        header.setFont(new Font("Arial",Font.BOLD,25));
        viewUserFrame.add(header);
        userIDLabel.setBounds(40, 150, 270, 50);
        userIDLabel.setFont(new Font("Arial",Font.BOLD,20));
        viewUserFrame.add(userIDLabel);
        userIDTextField.setBounds(400,150,300,50);
        userIDTextField.setFont(new Font("Arial",Font.BOLD,20));
        viewUserFrame.add(userIDTextField);
        
        JLabel userName=new JLabel(user+"'s Name:\t ");
        userName.setBounds(40, 270, 500, 50);
        userName.setFont(new Font("Arial",Font.BOLD,25));
        viewUserFrame.add(userName);

        JLabel userMobile=new JLabel(user+"'s Mobile: \t");
        userMobile.setBounds(40, 370, 500, 50);
        userMobile.setFont(new Font("Arial",Font.BOLD,25));
        viewUserFrame.add(userMobile);

        JLabel userAddress=new JLabel(user+"'s Address:\t ");
        userAddress.setBounds(40, 470, 500, 50);
        userAddress.setFont(new Font("Arial",Font.BOLD,25));
        viewUserFrame.add(userAddress);
        
        JButton view=new JButton("View "+user);
        view.setFont(new Font("Arial",Font.BOLD,25));
        view.setBounds(300,550,250,50); 
        viewUserFrame.add(view);
        
        JLabel Error;
        Error = new JLabel(user+ " is not Found");
        Error.setFont(new Font("Arial",Font.BOLD,15));
        Error.setBounds(320,610,250,50);
        Error.setForeground(Color.red);
        Error.setVisible(false);
        viewUserFrame.add(Error);
        

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("View Button worked");
                userName.setText(user+"'s Name:\t ");
                userAddress.setText(user+"'s Address:\t ");
                userMobile.setText(user+"'s Mobile: \t");
                Admin a=new Admin();
                Doctor d=new Doctor();
                Receptionist r=new Receptionist();
                boolean userFound=false;
               
                String ID=userIDTextField.getText();
               
                       
                              
                                if(user.equals("Admin")){
                                 a=connect.viewadmin(ID);
                                 if (a!=null)
                                 {
                                    userName.setText(userName.getText()+a.getName());
                                    userAddress.setText(userAddress.getText()+a.getAddress());
                                    userMobile.setText(userMobile.getText()+a.getPhoneNumber());
                                    Error.setVisible(false);
                                    userFound=true;
                                }
                                     }
                                else if(user.equals("Doctor")){
                                    d=connect.viewdoctor(ID);
                                    if(d!=null)
                                    {

                                    userName.setText(userName.getText()+d.getName());
                                    userAddress.setText(userAddress.getText()+d.getAddress());
                                    userMobile.setText(userMobile.getText()+d.getPhoneNumber());
                                    Error.setVisible(false);
                                    userFound=true;
                                    }
                                }
                                else if(user.equals("Receptionist")){
                                    r=connect.viewreceptionist(ID);
                                    if (r!=null)
                                    {
                                    userName.setText(userName.getText()+r.getName());
                                    userAddress.setText(userAddress.getText()+r.getAddress());
                                    userMobile.setText(userMobile.getText()+r.getPhoneNumber());
                                    Error.setVisible(false);
                                    userFound=true;
                                }
                            }
                                
                            
                        
                
               
                
                if(!userFound){
                    Error.setVisible(true);
                }
            }
            });
        
        return viewUserFrame;
    }

    // Patient Buttons in the Admin Page
    public JPanel viewPatient() {
        JPanel viewPatientFrame = new JPanel();
        viewPatientFrame.setSize(800, 800);

        viewPatientFrame.setLayout(null);

        JLabel header = new JLabel("View Patient");
        JLabel patientIDLabel = new JLabel("Enter Patient's ID: ");
        JTextField patientIDTextField = new JTextField();

        header.setBounds(300, 20, 300, 50);
        header.setFont(new Font("Arial", Font.BOLD, 25));
        viewPatientFrame.add(header);

        patientIDLabel.setBounds(40, 100, 270, 50);
        patientIDLabel.setFont(new Font("Arial", Font.BOLD, 20));
        viewPatientFrame.add(patientIDLabel);
        patientIDTextField.setBounds(400, 100, 300, 50);
        patientIDTextField.setFont(new Font("Arial", Font.BOLD, 20));
        viewPatientFrame.add(patientIDTextField);

        JLabel patientName = new JLabel("Patient's Name:\t ");
        patientName.setBounds(40, 200, 500, 50);
        patientName.setFont(new Font("Arial", Font.BOLD, 25));
        viewPatientFrame.add(patientName);

        JLabel patientMobile = new JLabel("Patient's phonenumber: \t");
        patientMobile.setBounds(40, 280, 500, 50);
        patientMobile.setFont(new Font("Arial", Font.BOLD, 25));
        viewPatientFrame.add(patientMobile);

        JLabel patientAddress = new JLabel("Patient's Address:\t ");
        patientAddress.setBounds(40, 360, 500, 50);
        patientAddress.setFont(new Font("Arial", Font.BOLD, 25));
        viewPatientFrame.add(patientAddress);

        JLabel doctorIDLabel = new JLabel("Doctor's ID: \t ");
        doctorIDLabel.setBounds(40, 440, 500, 50);
        doctorIDLabel.setFont(new Font("Arial", Font.BOLD, 25));
        viewPatientFrame.add(doctorIDLabel);

        JLabel admissiondateLabel = new JLabel("Admission date: \t ");
        admissiondateLabel.setBounds(40, 520, 500, 50);
        admissiondateLabel.setFont(new Font("Arial", Font.BOLD, 25));
        viewPatientFrame.add(admissiondateLabel);

        JLabel recentdateLabel = new JLabel("Recent date: \t ");
        recentdateLabel.setBounds(40, 600, 500, 50);
        recentdateLabel.setFont(new Font("Arial", Font.BOLD, 25));
        viewPatientFrame.add(recentdateLabel);

        JButton view = new JButton("View Patient");
        view.setFont(new Font("Arial", Font.BOLD, 20));
        view.setBounds(300, 680, 200, 50);
        viewPatientFrame.add(view);

        JLabel Error;
        Error = new JLabel("Patient is not Found");
        Error.setFont(new Font("Arial", Font.BOLD, 15));
        Error.setBounds(320, 740, 250, 50);
        Error.setForeground(Color.red);
        Error.setVisible(false);
        viewPatientFrame.add(Error);

        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("View Patient Button worked");
                patientName.setText("Patient's Name:\t ");
                patientAddress.setText("Patient's Address:\t ");
                patientMobile.setText("Patient's phonenumber: \t");
                doctorIDLabel.setText("Doctor's ID: \t ");
                admissiondateLabel.setText("Admission date: ");
                recentdateLabel.setText("Recent date:  ");
                Patient p = new Patient();
                boolean patientFound = false;
                String ID = patientIDTextField.getText();
        p=connect.viewpatient(ID);
        if (p!=null)
        {
patientFound=true;
        }
                    
               

                if (patientFound) {
                    patientName.setText(patientName.getText() + p.getName());
                    patientAddress.setText(patientAddress.getText() + p.getAddress());
                    patientMobile.setText(patientMobile.getText() + p.getPhoneNumber());
                    doctorIDLabel.setText(doctorIDLabel.getText() + p.getDoctorID());
                    admissiondateLabel.setText(admissiondateLabel.getText() + p.getadmissiondate());
                    recentdateLabel.setText(recentdateLabel.getText() + p.getrecentdate());
                    Error.setVisible(false);
                } else {
                    Error.setVisible(true);
                }
            }
        });
        return viewPatientFrame;
    }

    public JPanel AddPatient() {
        JPanel PatientFrame = new JPanel();
        PatientFrame.setSize(800, 800);

        PatientFrame.setLayout(null);

        JLabel header = new JLabel("Add Patient");
        JLabel patientIDLabel = new JLabel("Enter Patient's ID: ");
        JTextField patientIDTextField = new JTextField();

        header.setBounds(300, 20, 300, 50);
        header.setFont(new Font("Arial", Font.BOLD, 25));
        PatientFrame.add(header);

        patientIDLabel.setBounds(40, 100, 270, 50);
        patientIDLabel.setFont(new Font("Arial", Font.BOLD, 20));
        PatientFrame.add(patientIDLabel);
        patientIDTextField.setBounds(400, 100, 300, 50);
        patientIDTextField.setFont(new Font("Arial", Font.BOLD, 20));
        PatientFrame.add(patientIDTextField);

        JLabel patientName = new JLabel("Patient's Name:\t ");
        patientName.setBounds(40, 200, 500, 50);
        patientName.setFont(new Font("Arial", Font.BOLD, 25));
        PatientFrame.add(patientName);
        JTextField patientNameTextField = new JTextField();
        patientNameTextField.setBounds(400, 200, 300, 50);
        patientNameTextField.setFont(new Font("Arial", Font.BOLD, 20));
        PatientFrame.add(patientNameTextField);

        JLabel patientMobile = new JLabel("Patient's Mobile: \t");
        patientMobile.setBounds(40, 280, 500, 50);
        patientMobile.setFont(new Font("Arial", Font.BOLD, 25));
        PatientFrame.add(patientMobile);
        JTextField patientMobileTextField = new JTextField();
        patientMobileTextField.setBounds(400, 280, 300, 50);
        patientMobileTextField.setFont(new Font("Arial", Font.BOLD, 20));
        PatientFrame.add(patientMobileTextField);

        JLabel patientAddress = new JLabel("Patient's Address:\t ");
        patientAddress.setBounds(40, 360, 500, 50);
        patientAddress.setFont(new Font("Arial", Font.BOLD, 25));
        PatientFrame.add(patientAddress);
        JTextField patientAddressTextField = new JTextField();
        patientAddressTextField.setBounds(400, 360, 300, 50);
        patientAddressTextField.setFont(new Font("Arial", Font.BOLD, 20));
        PatientFrame.add(patientAddressTextField);

        JLabel doctorIDLabel = new JLabel("Doctor's ID: \t ");
        doctorIDLabel.setBounds(40, 440, 500, 50);
        doctorIDLabel.setFont(new Font("Arial", Font.BOLD, 25));
        PatientFrame.add(doctorIDLabel);
        JTextField doctorIDTextField = new JTextField();
        doctorIDTextField.setBounds(400, 440, 300, 50);
        doctorIDTextField.setFont(new Font("Arial", Font.BOLD, 20));
        PatientFrame.add(doctorIDTextField);

        JLabel admissiondateLabel = new JLabel("Admission date: \t ");
        admissiondateLabel.setBounds(40, 520, 500, 50);
        admissiondateLabel.setFont(new Font("Arial", Font.BOLD, 25));
        PatientFrame.add(admissiondateLabel);
        JTextField admisssiondateTextField = new JTextField();
        admisssiondateTextField.setBounds(400, 520, 300, 50);
        admisssiondateTextField.setFont(new Font("Arial", Font.BOLD, 20));
        PatientFrame.add(admisssiondateTextField);

        JLabel recentdateLabel = new JLabel("Recent date: \t ");
        recentdateLabel.setBounds(40, 600, 500, 50);
        recentdateLabel.setFont(new Font("Arial", Font.BOLD, 25));
        PatientFrame.add(recentdateLabel);
        JTextField recentdateTextField = new JTextField();
        recentdateTextField.setBounds(400, 600, 300, 50);
        recentdateTextField.setFont(new Font("Arial", Font.BOLD, 20));
        PatientFrame.add(recentdateTextField);

        JButton Add = new JButton("Add Patient");
        Add.setFont(new Font("Arial", Font.BOLD, 20));
        Add.setBounds(300, 680, 200, 50);
        PatientFrame.add(Add);

        JLabel Error;
        Error = new JLabel("Patient is not Added");
        Error.setFont(new Font("Arial", Font.BOLD, 15));
        Error.setBounds(320, 730, 250, 50);
        Error.setForeground(Color.red);
        Error.setVisible(false);
        PatientFrame.add(Error);

        JLabel Success;
        Success = new JLabel("Patient is successfully Added");
        Success.setFont(new Font("Arial", Font.BOLD, 15));
        Success.setBounds(320, 730, 250, 50);
        Success.setForeground(Color.green);
        Success.setVisible(false);
        PatientFrame.add(Success);

        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Add Patient Button worked");
                String id = patientIDTextField.getText();
                String name = patientNameTextField.getText();
                String address = patientAddressTextField.getText();
                String mobile = patientMobileTextField.getText();
                String doctorID = doctorIDTextField.getText();
                String admissiondate = admisssiondateTextField.getText();
                String recentdate = recentdateTextField.getText();
                Patient p = new Patient(name, id, address, mobile, recentdate, admissiondate, doctorID);

                boolean patientAdded = connect.addpatient(p);
               
                   
                    
               

                if (patientAdded) {
                    Success.setVisible(true);
                    Error.setVisible(false);
                } else {
                    Success.setVisible(false);
                    Error.setVisible(true);
                }
            }
        });

        return PatientFrame;
    }

    // Admin Page End

    // Receptionist Page Start

    public void receptionistPage(String username) {
        logInFrame.setVisible(false);
        window.setVisible(true);
        window.setTitle("Receptionist :" + username);

        tabbedPane.addTab("Add patient", new JScrollPane(AddPatient()));
        tabbedPane.addTab("View patient", new JScrollPane(viewPatient()));

        // tabbedPane.addTab("patient in line ", new JScrollPane(patientInline()));
        // tabbedPane.addTab("View prescription", new JScrollPane(viewPrescription()));
        // tabbedPane.addTab("Resources tracking", new JScrollPane(resourcesTracking()));

    }

    // public JPanel viewPrescription() {
    //     JPanel prescriptionFrame = new JPanel();
    //     prescriptionFrame.setSize(800, 800);

    //     prescriptionFrame.setLayout(null);

    //     JLabel patientIDLabel = new JLabel("Enter Patient ID: ");
    //     patientIDLabel.setBounds(50, 50, 200, 50);
    //     patientIDLabel.setFont(new Font("Arial", Font.BOLD, 20));
    //     patientIDLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    //     prescriptionFrame.add(patientIDLabel);

    //     JTextField patientIDTextField = new JTextField();
    //     patientIDTextField.setBounds(400, 50, 300, 50);
    //     patientIDTextField.setFont(new Font("Arial", Font.BOLD, 20));
    //     patientIDTextField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    //     prescriptionFrame.add(patientIDTextField);

    //     JLabel DatePrescriptionLabel = new JLabel("Date Prescrition: ");
    //     DatePrescriptionLabel.setBounds(50, 130, 500, 50);
    //     DatePrescriptionLabel.setFont(new Font("Arial", Font.BOLD, 20));
    //     DatePrescriptionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    //     prescriptionFrame.add(DatePrescriptionLabel);

    //     JLabel describeMedicineLabel = new JLabel("Describe Medicine: ");
    //     describeMedicineLabel.setBounds(50, 210, 500, 50);
    //     describeMedicineLabel.setFont(new Font("Arial", Font.BOLD, 20));
    //     describeMedicineLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    //     prescriptionFrame.add(describeMedicineLabel);

    //     JLabel bedNeededLabel = new JLabel("Bed Needed: ");
    //     bedNeededLabel.setBounds(50, 290, 500, 50);
    //     bedNeededLabel.setFont(new Font("Arial", Font.BOLD, 20));
    //     bedNeededLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    //     prescriptionFrame.add(bedNeededLabel);

    //     JLabel residencyDaysLabel = new JLabel("Number of residency days: ");
    //     residencyDaysLabel.setBounds(50, 370, 500, 50);
    //     residencyDaysLabel.setFont(new Font("Arial", Font.BOLD, 20));
    //     residencyDaysLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    //     prescriptionFrame.add(residencyDaysLabel);

    //     JButton MedicinePriceButton = new JButton("Enter medicine price");
    //     MedicinePriceButton.setBounds(100, 500, 250, 50);
    //     MedicinePriceButton.setFont(new Font("Arial", Font.BOLD, 20));
    //     MedicinePriceButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    //     MedicinePriceButton.setBackground(Color.DARK_GRAY);
    //     MedicinePriceButton.setForeground(Color.CYAN);
    //     prescriptionFrame.add(MedicinePriceButton);

    //     JButton resourcesChargesButton = new JButton("Enter resources Charges");
    //     resourcesChargesButton.setBounds(450, 500, 300, 50);
    //     resourcesChargesButton.setFont(new Font("Arial", Font.BOLD, 20));
    //     resourcesChargesButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    //     resourcesChargesButton.setBackground(Color.DARK_GRAY);
    //     resourcesChargesButton.setForeground(Color.CYAN);
    //     prescriptionFrame.add(resourcesChargesButton);

    //     JButton bedCostButton = new JButton("Enter bed cost");
    //     bedCostButton.setBounds(100, 580, 250, 50);
    //     bedCostButton.setFont(new Font("Arial", Font.BOLD, 20));
    //     bedCostButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    //     bedCostButton.setBackground(Color.DARK_GRAY);
    //     bedCostButton.setForeground(Color.CYAN);
    //     prescriptionFrame.add(bedCostButton);

    //     JButton totalButton = new JButton("Total");
    //     totalButton.setBounds(450, 580, 100, 50);
    //     totalButton.setFont(new Font("Arial", Font.BOLD, 20));
    //     totalButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    //     totalButton.setBackground(Color.DARK_GRAY);
    //     totalButton.setForeground(Color.CYAN);
    //     prescriptionFrame.add(totalButton);

    //     JLabel dischargePatientLabel = new JLabel("Discharge Patient");
    //     dischargePatientLabel.setBounds(550, 580, 250, 50);
    //     dischargePatientLabel.setFont(new Font("Arial", Font.BOLD, 20));
    //     dischargePatientLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    //     dischargePatientLabel.setForeground(Color.CYAN);
    //     prescriptionFrame.add(dischargePatientLabel);

    //     JButton viewButton = new JButton("View");
    //     viewButton.setBounds(350, 650, 100, 50);
    //     viewButton.setFont(new Font("Arial", Font.BOLD, 20));
    //     // viewButton.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    //     viewButton.setBackground(Color.DARK_GRAY);
    //     viewButton.setForeground(Color.CYAN);
    //     prescriptionFrame.add(viewButton);

    //     JLabel fail = new JLabel("Patient is not found");
    //     fail.setBounds(325, 710, 200, 50);
    //     fail.setFont(new Font("Arial", Font.BOLD, 15));
    //     fail.setForeground(Color.red);
    //     fail.setVisible(false);
    //     prescriptionFrame.add(fail);

    //     MedicinePriceButton.addActionListener((ae) -> {
    //         String price = JOptionPane.showInputDialog(prescriptionFrame,
    //                 "Enter Medicine price", null);
    //         System.out.println(price);
    //     });
    //     resourcesChargesButton.addActionListener((ae) -> {
    //         String number = JOptionPane.showInputDialog(prescriptionFrame,
    //                 "Enter resources charges", null);
    //         System.out.println(number);
    //     });
    //     bedCostButton.addActionListener((ae) -> {
    //         String price = JOptionPane.showInputDialog(prescriptionFrame,
    //                 "Enter bed cost", null);
    //         System.out.println(price);
    //     });
    //     totalButton.addActionListener((ae) -> {
    //         dischargePatientLabel.setText("0");
    //     });

    //     viewButton.addActionListener((ae) -> {
    //         String id = patientIDTextField.getText();
    //         DatePrescriptionLabel.setText("Date Prescrition:            ");
    //         describeMedicineLabel.setText("Describe medicine:           ");
    //         bedNeededLabel.setText("Bed Needed:                 ");
    //         residencyDaysLabel.setText("Number of residency days:              ");
    //         dischargePatientLabel.setText("Discharge patient");
    //         fail.setVisible(false);

    //         boolean found = false;
    //         try {
    //             Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "root", "root");
    //             java.sql.Statement st = con.createStatement();
    //             String patientStatement;
    //             patientStatement = "select * from patient";
    //             ResultSet rs;
    //             rs = st.executeQuery(patientStatement);
    //             while (rs.next()) {
    //                 if (rs.getString("id").equals(id)) {
    //                     System.out.println("Patient Found");
    //                     DatePrescriptionLabel.setText(DatePrescriptionLabel.getText() + rs.getString("date"));
    //                     residencyDaysLabel
    //                             .setText(residencyDaysLabel.getText() + rs.getString("number_residency_days"));
    //                     found = true;
    //                     break;
    //                 }
    //             }
    //             String prescriptionStatment;
    //             prescriptionStatment = "select * from PRESCRIPTION";
    //             rs = st.executeQuery(prescriptionStatment);
    //             while (rs.next() && found) {
    //                 if (rs.getString("patient_id").equals(id)) {
    //                     System.out.println("Patient Found");
    //                     describeMedicineLabel
    //                             .setText(describeMedicineLabel.getText() + rs.getString("describe_medicine"));
    //                     bedNeededLabel.setText(bedNeededLabel.getText() + rs.getString("bed_needed"));
    //                     found = true;
    //                     break;
    //                 }
    //             }

    //         } catch (SQLException e) {
    //             System.out.println(e);
    //         }
    //         if (!found) {
    //             fail.setVisible(true);
    //         }

    //     });

    //     prescriptionFrame.setVisible(true);
    //     return prescriptionFrame;
    // }

    // public JPanel resourcesTracking() {

    //     JPanel frame = new JPanel();
    //     frame.setSize(800, 750);

    //     frame.setLayout(null);

    //     JLabel header = new JLabel("Medicine Information");
    //     header.setBounds(250, 20, 300, 50);
    //     header.setFont(new Font("Arial", Font.BOLD, 30));
    //     frame.add(header);

    //     DefaultTableModel tableModel = new DefaultTableModel();
    //     JTable table = new JTable(tableModel);
    //     tableModel.addColumn("Barcode");
    //     tableModel.addColumn("Name");
    //     tableModel.addColumn("Cost");
    //     tableModel.addColumn("Number Pieces");

    //     try {
    //         Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "root", "root");
    //         java.sql.Statement st = con.createStatement();
    //         String patientStatement;
    //         patientStatement = "select * from medicine";
    //         ResultSet rs;
    //         rs = st.executeQuery(patientStatement);
    //         while (rs.next()) {
    //             tableModel.insertRow(0, new Object[] { rs.getString("barcode"), rs.getString("name"),
    //                     rs.getString("cost"), rs.getString("number_pieces") });
    //         }
    //     } catch (SQLException e) {
    //         System.out.println(e);
    //     }

    //     JScrollPane sp = new JScrollPane(table);
    //     sp.setBounds(50, 100, 700, 300);
    //     frame.add(sp);

    //     JButton insertButton = new JButton("Insert");
    //     insertButton.setFont(new Font("Arial", Font.BOLD, 20));
    //     insertButton.setBounds(50, 500, 150, 50);
    //     frame.add(insertButton);

    //     JLabel insertBarCodeLabel = new JLabel("Barcode");
    //     insertBarCodeLabel.setFont(new Font("Arial", Font.BOLD, 15));
    //     insertBarCodeLabel.setBounds(300, 450, 100, 50);
    //     frame.add(insertBarCodeLabel);

    //     JTextField insertBarCodeTextField = new JTextField();
    //     insertBarCodeTextField.setFont(new Font("Arial", Font.BOLD, 15));
    //     insertBarCodeTextField.setBounds(300, 500, 70, 50);
    //     frame.add(insertBarCodeTextField);

    //     JLabel insertNameLabel = new JLabel("Name");
    //     insertNameLabel.setFont(new Font("Arial", Font.BOLD, 15));
    //     insertNameLabel.setBounds(400, 450, 100, 50);
    //     frame.add(insertNameLabel);

    //     JTextField insertNameTextField = new JTextField();
    //     insertNameTextField.setFont(new Font("Arial", Font.BOLD, 15));
    //     insertNameTextField.setBounds(400, 500, 70, 50);
    //     frame.add(insertNameTextField);

    //     JLabel insertCostLabel = new JLabel("Cost");
    //     insertCostLabel.setFont(new Font("Arial", Font.BOLD, 15));
    //     insertCostLabel.setBounds(500, 450, 100, 50);
    //     frame.add(insertCostLabel);

    //     JTextField insertCostTextField = new JTextField();
    //     insertCostTextField.setFont(new Font("Arial", Font.BOLD, 15));
    //     insertCostTextField.setBounds(500, 500, 70, 50);
    //     frame.add(insertCostTextField);

    //     JLabel insertNumberPiecesLabel = new JLabel("Number of Pieces");
    //     insertNumberPiecesLabel.setFont(new Font("Arial", Font.BOLD, 15));
    //     insertNumberPiecesLabel.setBounds(600, 450, 200, 50);
    //     frame.add(insertNumberPiecesLabel);

    //     JTextField insertNumberPiecesTextField = new JTextField();
    //     insertNumberPiecesTextField.setFont(new Font("Arial", Font.BOLD, 15));
    //     insertNumberPiecesTextField.setBounds(600, 500, 70, 50);
    //     frame.add(insertNumberPiecesTextField);

    //     JButton updateButton = new JButton("Update");
    //     updateButton.setFont(new Font("Arial", Font.BOLD, 20));
    //     updateButton.setBounds(50, 600, 150, 50);
    //     frame.add(updateButton);

    //     JLabel updateBarCodeLabel = new JLabel("Barcode");
    //     updateBarCodeLabel.setFont(new Font("Arial", Font.BOLD, 15));
    //     updateBarCodeLabel.setBounds(400, 550, 100, 50);
    //     frame.add(updateBarCodeLabel);

    //     JTextField updateBarCodeTextField = new JTextField();
    //     updateBarCodeTextField.setFont(new Font("Arial", Font.BOLD, 15));
    //     updateBarCodeTextField.setBounds(400, 600, 70, 50);
    //     frame.add(updateBarCodeTextField);

    //     JLabel updateNumberPiecesLabel = new JLabel("Value Of number");
    //     updateNumberPiecesLabel.setFont(new Font("Arial", Font.BOLD, 15));
    //     updateNumberPiecesLabel.setBounds(550, 550, 250, 50);
    //     frame.add(updateNumberPiecesLabel);

    //     JTextField updateNumberPiecesTextField = new JTextField();
    //     updateNumberPiecesTextField.setFont(new Font("Arial", Font.BOLD, 15));
    //     updateNumberPiecesTextField.setBounds(550, 600, 70, 50);
    //     frame.add(updateNumberPiecesTextField);

    //     JButton deleteButton = new JButton("Delete");
    //     deleteButton.setFont(new Font("Arial", Font.BOLD, 20));
    //     deleteButton.setBounds(50, 680, 150, 50);
    //     frame.add(deleteButton);

    //     JLabel deleteBarCodeLabel = new JLabel("Barcode");
    //     deleteBarCodeLabel.setFont(new Font("Arial", Font.BOLD, 15));
    //     deleteBarCodeLabel.setBounds(400, 620, 100, 50);
    //     frame.add(deleteBarCodeLabel);

    //     JTextField deleteBarCodeTextField = new JTextField();
    //     deleteBarCodeTextField.setFont(new Font("Arial", Font.BOLD, 15));
    //     deleteBarCodeTextField.setBounds(400, 680, 70, 50);
    //     frame.add(deleteBarCodeTextField);

    //     insertButton.addActionListener((ae) -> {
    //         String barcode = insertBarCodeTextField.getText();
    //         String name = insertNameTextField.getText();
    //         double cost = Double.parseDouble(insertCostTextField.getText());
    //         int number = Integer.parseInt(insertNumberPiecesTextField.getText());
    //         try {
    //             Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "root", "root");
    //             java.sql.Statement st = con.createStatement();
    //             String insertMedicineStatement;
    //             insertMedicineStatement = "insert into MEDICINE (barcode, name,cost,number_pieces) values (\'" + barcode
    //                     + "\',\'" + name + "\'," + cost + "," + number + ")";
    //             st.executeUpdate(insertMedicineStatement);
    //         } catch (SQLException e) {
    //             System.out.println(e);
    //         }
    //     });

    //     updateButton.addActionListener((ae) -> {
    //         String barcode = updateBarCodeTextField.getText();
    //         int number = Integer.parseInt(updateNumberPiecesTextField.getText());
    //         try {
    //             Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "root", "root");
    //             java.sql.Statement st = con.createStatement();
    //             String updateMedicineStatement;
    //             updateMedicineStatement = "update MEDICINE set number_pieces = " + number + " where barcode = \'"
    //                     + barcode + "\'";
    //             st.executeUpdate(updateMedicineStatement);
    //         } catch (SQLException e) {
    //             System.out.println(e);
    //         }
    //     });

    //     deleteButton.addActionListener((ae) -> {
    //         String barcode = deleteBarCodeTextField.getText();
    //         try {
    //             Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "root", "root");
    //             java.sql.Statement st = con.createStatement();
    //             String deleteMedicineStatement;
    //             deleteMedicineStatement = "delete from MEDICINE where barcode = \'" + barcode + "\'";
    //             st.executeUpdate(deleteMedicineStatement);
    //         } catch (SQLException e) {
    //             System.out.println(e);
    //         }
    //     });

    //     return frame;
    // }

    // public JPanel patientInline() {
    //     JPanel frame = new JPanel();
    //     frame.setSize(800, 750);

    //     frame.setLayout(null);

    //     DefaultTableModel tableModel = new DefaultTableModel();
    //     JTable table = new JTable(tableModel);
    //     tableModel.addColumn("Patient Number");
    //     tableModel.addColumn("Doctor ID");
    //     tableModel.addColumn("Patient ID");

    //     try {
    //         Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "root", "root");
    //         java.sql.Statement st = con.createStatement();
    //         String listStatment;
    //         listStatment = "select * from WAITING_LIST";
    //         ResultSet rs;
    //         rs = st.executeQuery(listStatment);
    //         while (rs.next()) {
    //             tableModel.insertRow(0, new Object[] { rs.getString("number_patient"), rs.getString("doctor_id"),
    //                     rs.getString("patient_id") });
    //         }
    //     } catch (SQLException e) {
    //         System.out.println(e);
    //     }

    //     JScrollPane sp = new JScrollPane(table);
    //     sp.setBounds(50, 100, 700, 300);
    //     frame.add(sp);

    //     JLabel header = new JLabel("Waiting List");
    //     header.setBounds(300, 20, 300, 50);
    //     header.setFont(new Font("Arial", Font.BOLD, 30));
    //     frame.add(header);

    //     JLabel searchPatientLabel = new JLabel("Search patient number");
    //     searchPatientLabel.setBounds(50, 400, 500, 50);
    //     searchPatientLabel.setFont(new Font("Arial", Font.BOLD, 30));
    //     frame.add(searchPatientLabel);

    //     JLabel patientIDLabel = new JLabel("Enter Patient ID: ");
    //     patientIDLabel.setBounds(50, 480, 300, 50);
    //     patientIDLabel.setFont(new Font("Arial", Font.BOLD, 30));
    //     frame.add(patientIDLabel);

    //     JTextField searchPatientTextField = new JTextField();
    //     searchPatientTextField.setBounds(400, 480, 300, 50);
    //     searchPatientTextField.setFont(new Font("Arial", Font.BOLD, 30));
    //     frame.add(searchPatientTextField);

    //     JLabel PatientNumberLabel = new JLabel("Patient number is: ");
    //     PatientNumberLabel.setBounds(50, 560, 300, 50);
    //     PatientNumberLabel.setFont(new Font("Arial", Font.BOLD, 30));
    //     frame.add(PatientNumberLabel);

    //     JButton searchButton = new JButton("Search");
    //     searchButton.setBounds(280, 620, 300, 50);
    //     searchButton.setFont(new Font("Arial", Font.BOLD, 30));
    //     frame.add(searchButton);

    //     JLabel fail = new JLabel("Patient is not found");
    //     fail.setForeground(Color.red);
    //     fail.setBounds(330, 680, 300, 50);
    //     fail.setFont(new Font("Arial", Font.BOLD, 20));
    //     fail.setVisible(false);
    //     frame.add(fail);

    //     searchButton.addActionListener((ae) -> {
    //         String ID = searchPatientTextField.getText();
    //         boolean found = false;
    //         try {
    //             Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "root", "root");
    //             java.sql.Statement st = con.createStatement();
    //             String searchStatement;
    //             searchStatement = "select * from WAITING_LIST";
    //             ResultSet rs;
    //             rs = st.executeQuery(searchStatement);
    //             while (rs.next()) {
    //                 if (rs.getString("patient_id").equals(ID)) {
    //                     PatientNumberLabel.setText("Patient number is: " + rs.getString("number_patient"));
    //                     fail.setVisible(false);
    //                     found = true;
    //                 }
    //             }
    //         } catch (SQLException e) {
    //             System.out.println(e);
    //         }
    //         if (!found) {
    //             fail.setVisible(true);
    //         }
    //     });

    //     return frame;
    // }

    // Receptionist Page END

    // Doctor Page Start

    public void doctorPage(String username) {
        logInFrame.setVisible(false);
        window.setVisible(true);
        window.setTitle("Doctor :" + username);

        tabbedPane.addTab("View patient", new JScrollPane(viewPatient()));

        //tabbedPane.addTab("Patient information", new JScrollPane(patientInformation()));
    }

    //  public JPanel patientInformation() {
    //     JPanel frame = new JPanel();
    //     frame.setSize(800, 750);

    //     frame.setLayout(null);

    //     JLabel IDLabel = new JLabel("Enter Patient ID");
    //     IDLabel.setFont(new Font("Arial", Font.BOLD, 20));
    //     IDLabel.setBounds(50, 100, 300, 50);
    //     frame.add(IDLabel);

    //     JTextField IDTextField = new JTextField();
    //     IDTextField.setFont(new Font("Arial", Font.BOLD, 20));
    //     IDTextField.setBounds(400, 100, 300, 50);
    //     frame.add(IDTextField);

    //     JLabel diseasLabel = new JLabel("Write Disease");
    //     diseasLabel.setFont(new Font("Arial", Font.BOLD, 20));
    //     diseasLabel.setBounds(50, 200, 300, 50);
    //     frame.add(diseasLabel);

    //     JTextField diseaseTextField = new JTextField();
    //     diseaseTextField.setFont(new Font("Arial", Font.BOLD, 20));
    //     diseaseTextField.setBounds(400, 200, 300, 50);
    //     frame.add(diseaseTextField);

    //     JLabel describeMedicineLabel = new JLabel("Describe Medicine");
    //     describeMedicineLabel.setFont(new Font("Arial", Font.BOLD, 20));
    //     describeMedicineLabel.setBounds(50, 300, 300, 50);
    //     frame.add(describeMedicineLabel);

    //     JTextField describeMedicineTextField = new JTextField();
    //     describeMedicineTextField.setFont(new Font("Arial", Font.BOLD, 20));
    //     describeMedicineTextField.setBounds(400, 300, 300, 50);
    //     frame.add(describeMedicineTextField);

    //     JLabel bedNeededLabel = new JLabel("Need Bed");
    //     bedNeededLabel.setFont(new Font("Arial", Font.BOLD, 20));
    //     bedNeededLabel.setBounds(50, 400, 300, 50);
    //     frame.add(bedNeededLabel);

    //     JTextField bedNeededTextField = new JTextField();
    //     bedNeededTextField.setFont(new Font("Arial", Font.BOLD, 20));
    //     bedNeededTextField.setBounds(400, 400, 300, 50);
    //     frame.add(bedNeededTextField);

    //     JLabel numberResidencyDaysLabel = new JLabel("Number of residency days");
    //     numberResidencyDaysLabel.setFont(new Font("Arial", Font.BOLD, 20));
    //     numberResidencyDaysLabel.setBounds(50, 500, 300, 50);
    //     frame.add(numberResidencyDaysLabel);

    //     JTextField numberResidencyDaysTextField = new JTextField();
    //     numberResidencyDaysTextField.setFont(new Font("Arial", Font.BOLD, 20));
    //     numberResidencyDaysTextField.setBounds(400, 500, 300, 50);
    //     frame.add(numberResidencyDaysTextField);

    //     JLabel dateLabel = new JLabel("Date");
    //     dateLabel.setFont(new Font("Arial", Font.BOLD, 20));
    //     dateLabel.setBounds(50, 600, 300, 50);
    //     frame.add(dateLabel);

    //     JTextField dateTextField = new JTextField();
    //     dateTextField.setFont(new Font("Arial", Font.BOLD, 20));
    //     dateTextField.setBounds(400, 600, 300, 50);
    //     frame.add(dateTextField);

    //     JButton submitButton = new JButton("Submit");
    //     submitButton.setBounds(310, 680, 200, 50);
    //     submitButton.setFont(new Font("Arial", Font.BOLD, 20));
    //     submitButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    //     submitButton.setBackground(Color.DARK_GRAY);
    //     submitButton.setForeground(Color.CYAN);
    //     frame.add(submitButton);

    //     submitButton.addActionListener((ae) -> {
    //         String ID = IDTextField.getText();
    //         String disease = diseaseTextField.getText();
    //         String medicineDescription = describeMedicineTextField.getText();
    //         int bedNeeded = Integer.parseInt(bedNeededTextField.getText());
    //         int numberResidencyDay = Integer.parseInt(numberResidencyDaysLabel.getText());
    //         String date = dateTextField.getText();
    //         boolean patientFound = false;
    //         boolean prescriptionFound = false;
    //         try {
    //             Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "root", "root");
    //             java.sql.Statement st = con.createStatement();
    //             String selectPatientStatement;
    //             selectPatientStatement = "select * from patient";
    //             String selectPrescriptionStatement;
    //             selectPrescriptionStatement = "select * from PRESCRIPTION";
    //             ResultSet rs;
    //             rs = st.executeQuery(selectPatientStatement);
    //             while (rs.next()) {
    //                 if (rs.getString("id").equals(ID)) {
    //                     patientFound = true;
    //                 }
    //             }
    //             rs = st.executeQuery(selectPrescriptionStatement);
    //             while (rs.next()) {
    //                 if (rs.getString("patient_id").equals(ID)) {
    //                     prescriptionFound = true;
    //                 }
    //             }
    //             if (patientFound) {
    //                 st.executeUpdate("update PATIENT set NUMBER_RESIDENCY_DAYS = " + numberResidencyDay + ",date =\'"
    //                         + date + "\' where patient_id = \'" + ID + "\'");
    //             }
    //             if (prescriptionFound) {
    //                 st.executeQuery("insert into PRESCRIPTION (patient_id,date,bed_needed,describe_medicine) values (\'"
    //                         + ID + "\',\'" + date + "\'," + bedNeeded + ",\'"
    //                         + medicineDescription + "\')");
    //             }
    //         } catch (SQLException e) {
    //             System.out.println(e);
    //         }
    //         System.out.println("Submit Button");
    //     });

    //     return frame;
    // }
    // // Doctor Page END

    String Username = "";
    static String Password = "";
    static String id;
    String Job = "";

    @Override
    public void actionPerformed(ActionEvent e) {
        // Login Page Action Events Start

        if (e.getSource() == loginButton) {
            Username = usernameTextField.getText();
            Password = passwordTextField.getText();
            if (adminRadioButton.isSelected()) {
                Job = "admin";
            } else if (doctorRadioButton.isSelected()) {
                Job = "doctor";
            } else if (receptionistRadioButton.isSelected()) {
                Job = "receptionist";
            }
        }
        // Login Page Action Events END

        // Admin Page Action Events Start
        if (e.getSource() == logout) {
            System.exit(0);
            // Username="";
            // Password="";
            // Job="";
            // adminLogOut();
        }
        if (e.getSource() == changepassword) {
            changePassword(Username, Password);
        }
        if (e.getSource() == confirmButton) {
            switch (Job) {
                case "admin":
                    if (Password.equals(oldPasswordTextField.getText())
                            && newPassword1TextField.getText().equals(newPassword2TextField.getText())) {
                        errorLabel.setVisible(false);
                        if(connect.changepassword(Username, Password, newPassword1TextField.getText(),"admin"))
                        {
                          successLabel.setVisible(true);
                        }
                       else {
                        successLabel.setVisible(false);

                          errorLabel.setVisible(true);
                      }
                  }
                  else {
                    successLabel.setVisible(false);

                      errorLabel.setVisible(true);
                  }
                    break;
                case "receptionist":
                    if (Password.equals(oldPasswordTextField.getText())
                            && newPassword1TextField.getText().equals(newPassword2TextField.getText())) {
                        errorLabel.setVisible(false);
                      
                      if(connect.changepassword(Username, Password, newPassword1TextField.getText(),"receptionist"))
                      {
                        successLabel.setVisible(true);
                      }
                     else {
                        successLabel.setVisible(false);
                        errorLabel.setVisible(true);
                    }
                }
                else {
                    successLabel.setVisible(false);
                    errorLabel.setVisible(true);
                }
                    break;
                case "doctor":
                    if (Password.equals(oldPasswordTextField.getText())
                            && newPassword1TextField.getText().equals(newPassword2TextField.getText())) {
                        errorLabel.setVisible(false);
                        if(connect.changepassword(Username, Password, newPassword1TextField.getText(),"doctor"))
                        {
                          successLabel.setVisible(true);
                        }
                       else {
                        successLabel.setVisible(false);

                          errorLabel.setVisible(true);
                      }
                  }
                  else {
                    successLabel.setVisible(false);

                      errorLabel.setVisible(true);
                  }
                    break;
            }

        }

    }

}
