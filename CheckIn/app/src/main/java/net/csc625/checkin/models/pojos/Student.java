package net.csc625.checkin.models.pojos;

/**
 * Created by David on 11/8/2017.
 */

public class Student
{
    private int studentId;
    private String studentFirstName, studentLastName, studentDateOfBirth, studentSSN, studentAddress_1, studentAddress_2,
            studentCity, studentState, studentZipCode, studentGrade;

    private String motherFirstName, motherLastName, motherSSN, motherAddress_1,
            motherAddress_2, motherCity, motherState, motherZipCode,
            motherHomePhone, motherCellPhone, motherEmail;

    private String fatherFirstName, fatherLastName, fatherSSN, fatherAddress_1,
            fatherAddress_2, fatherCity, fatherState, fatherZipCode,
            fatherHomePhone, fatherCellPhone, fatherEmail;

    private boolean isSelected = false;

    public Student(String studentFirstName, String studentLastName,
                   String dateOfBirth, String studentSSN, String studentAddress_1, String studentAddress_2,
                   String studentCity, String studentState, String studentZipCode, String studentGrade,
                   String motherFirstName, String motherLastName, String motherSSN,
                   String motherAddress_1, String motherAddress_2, String motherCity,
                   String motherState, String motherZipCode, String motherHomePhone,
                   String motherCellPhone, String motherEmail,
                   String fatherFirstName, String fatherLastName, String fatherSSN,
                   String fatherAddress_1, String fatherAddress_2, String fatherCity,
                   String fatherState, String fatherZipCode, String fatherHomePhone,
                   String fatherCellPhone, String fatherEmail, int studentId)
    {
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentDateOfBirth = dateOfBirth;
        this.studentSSN = studentSSN;
        this.studentAddress_1 = studentAddress_1;
        this.studentAddress_2 = studentAddress_2;
        this.studentCity = studentCity;
        this.studentState = studentState;
        this.studentZipCode = studentZipCode;
        this.studentGrade = studentGrade;

        this.motherFirstName = motherFirstName;
        this.motherLastName = motherLastName;
        this.motherSSN = motherSSN;
        this.motherAddress_1 = motherAddress_1;
        this.motherAddress_2 = motherAddress_2;
        this.motherCity = motherCity;
        this.motherState = motherState;
        this.motherZipCode = motherZipCode;
        this.motherHomePhone = motherHomePhone;
        this.motherCellPhone = motherCellPhone;
        this.motherEmail = motherEmail;

        this.fatherFirstName = fatherFirstName;
        this.fatherLastName = fatherLastName;
        this.fatherSSN = fatherSSN;
        this.fatherAddress_1 = fatherAddress_1;
        this.fatherAddress_2 = fatherAddress_2;
        this.fatherCity = fatherCity;
        this.fatherState = fatherState;
        this.fatherZipCode = fatherZipCode;
        this.fatherHomePhone = fatherHomePhone;
        this.fatherCellPhone = fatherCellPhone;
        this.fatherEmail = fatherEmail;
        this.studentId = studentId;
    }

    public Student(String studentFirstName, String studentLastName, String studentDateOfBirth) {
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentDateOfBirth = studentDateOfBirth;
    }

    public Student(String studentFirstName, String studentLastName) {
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
    }

    public Student() {

    }


    public int getStudentId() {  return studentId;   }
    public void setStudentId(int studentID) {  this.studentId = studentID;   }

    public String getStudentFirstName() {  return studentFirstName;  }
    public void setStudentFirstName(String studentFirstName)
    {this.studentFirstName = studentFirstName;}

    public String getStudentLastName() {  return studentLastName;  }
    public void setStudentLastName(String studentLastName)
    {this.studentLastName = studentLastName;}

    public String getStudentDateOfBirth() {return studentDateOfBirth;}
    public void setDateOfBirth(String DateOfBirth)
    {this.studentDateOfBirth = DateOfBirth;}

    public String getStudentSSN(){return studentSSN;}
    public void setStudentSSN(String studentSSN){this.studentSSN = studentSSN;}

    public String getStudentAddress_1() {return studentAddress_1;}
    public void setStudentAddress_1(String studentAddress_1)
    {this.studentAddress_1 = studentAddress_1;}

    public String getStudentAddress_2() {return studentAddress_2;}
    public void getStudentAddress_2(String studentAddress_2)
    {this.studentAddress_2 = studentAddress_2;}

    public String getStudentCity() {return studentCity;}
    public void setStudentCity(String studentCity)
    {this.studentCity = studentCity;}

    public String getStudentState() {return studentState;}
    public void setStudentState(String studentState)
    {this.studentState = studentState;}

    public String getStudentZipCode() {return studentZipCode;}
    public void setStudentZipCode(String studentZipCode)
    {this.studentZipCode = studentZipCode;}

    public String getStudentGrade(){return studentGrade;}
    public void setStudentGrade(String studentGrade)
    {this.studentGrade = studentGrade;}

    public String getMotherFirstName() {return motherFirstName;}
    public void setMotherFirstName(String motherFirstName)
    {this.motherFirstName = motherFirstName;}

    public String getMotherLastName() {return motherLastName;}
    public void setMotherLastName(String motherLastName)
    {this.motherLastName = motherLastName;}

    public String getMotherSSN() {return motherSSN;}
    public void setMotherSSN(String motherSSN)
    {this.motherSSN = motherSSN;}

    public String getMotherAddress_1(){return motherAddress_1;}
    public void setMotherAddress_1(String motherAddress_1)
    {this.motherAddress_1 = motherAddress_1;}

    public String getMotherAddress_2(){return motherAddress_2;}
    public void setMotherAddress_2(String motherAddress_2)
    {this.motherAddress_2 = motherAddress_2;}

    public String getMotherCity(){return motherCity;}
    public void setMotherCity(String motherCity)
    {this.motherCity = motherCity;}

    public String getMotherState(){return motherState;}
    public void setMotherState(String motherState)
    {this.motherState = motherState;}

    public String getMotherZipCode() {return motherZipCode;}
    public void setMotherZipCode(String motherZipCode)
    {this.motherZipCode = motherZipCode;}

    public String getMotherHomePhone(){return motherHomePhone;}
    public void setMotherHomePhome(String motherHomePhone)
    {this.motherHomePhone = motherHomePhone;}

    public String getMotherCellPhone() {return motherCellPhone;}
    public void setMotherCellPhone(String motherCellPhone)
    {this.motherCellPhone = motherCellPhone;}

    public String getMotherEmail() {return motherEmail;}
    public void setMotherEmail(String motherEmail)
    {this.motherEmail = motherEmail;}

    public String getFatherFirstName() {return fatherFirstName;}
    public void setFatherFirstName(String fatherFirstName)
    {this.fatherFirstName = fatherFirstName;}

    public String getFatherLastName() {return fatherLastName;}
    public void setFatherLastName(String fatherLastName)
    {this.fatherLastName = fatherLastName;}

    public String getFatherSSN() {return fatherSSN;}
    public void setFatherSSN(String fatherSSN)
    {this.fatherSSN = fatherSSN;}

    public String getFatherAddress_1(){return fatherAddress_1;}
    public void setFatherAddress_1(String fatherAddress_1)
    {this.fatherAddress_1 = fatherAddress_1;}

    public String getFatherAddress_2(){return fatherAddress_2;}
    public void setFatherAddress_2(String fatherAddress_2)
    {this.fatherAddress_2 = fatherAddress_2;}

    public String getFatherCity(){return fatherCity;}
    public void setFatherCity(String fatherCity)
    {this.fatherCity = fatherCity;}

    public String getFatherState(){return fatherState;}
    public void setFatherState(String fatherState)
    {this.fatherState = fatherState;}

    public String getFatherZipCode() {return fatherZipCode;}
    public void setFatherZipCode(String fatherZipCode)
    {this.fatherZipCode = fatherZipCode;}

    public String getFatherHomePhone(){return fatherHomePhone;}
    public void setFatherHomePhome(String fatherHomePhone)
    {this.fatherHomePhone = fatherHomePhone;}

    public String getFatherCellPhone() {return fatherCellPhone;}
    public void setFatherCellPhone(String fatherCellPhone)
    {this.fatherCellPhone = fatherCellPhone;}

    public String getFatherEmail() {return fatherEmail;}
    public void setFatherEmail(String fatherEmail)
    {this.fatherEmail = fatherEmail;}

    public boolean getIsSelected() {  return isSelected;   }
    public void setIsSelected(boolean isSelected) {  this.isSelected = isSelected;   }

    @Override
    public String toString()
    {
        return "Student [studentId = " + studentId + ", firstName = " + studentFirstName+
                ",lastName = " + studentLastName + ",DOB = "+studentDateOfBirth+
                ", SSN = " + studentSSN + ", address1 = " + studentAddress_1+
                ", address2 = " + studentAddress_2 +
                ", City = " + studentCity+ ", State = " +studentState+
                ", ZipCode = "+ studentZipCode+ ", grade = "+ studentGrade+

                ", motherFirstName = " + motherFirstName+
                ", motherLastName = " + motherLastName+", motherSSN = "+motherSSN+
                ", motherAddress_1 = "+ motherAddress_1+
                ", motherAddress_2 = "+ motherAddress_2+
                ", motherCity = "+motherCity+", motherState = "+motherState+
                ", motherZip = "+motherZipCode+", motherHomePhone ="+motherHomePhone+
                ", motherCellPhone = "+motherCellPhone+
                ", motherEmail = " + motherEmail+

                ", fatherFirstName = " + fatherFirstName+
                ", fatherLastName = " + fatherLastName+", fatherSSN = "+fatherSSN+
                ", fatherAddress_1 = "+ fatherAddress_1+
                ", fatherAddress_2 = "+ fatherAddress_2+
                ", fatherCity = "+fatherCity+", motherState = "+fatherState+
                ", fatherZip = "+fatherZipCode+", motherHomePhone ="+fatherHomePhone+
                ", fatherCellPhone = "+fatherCellPhone+
                ", fatherEmail = " + fatherEmail+
                "]";
    }
}
