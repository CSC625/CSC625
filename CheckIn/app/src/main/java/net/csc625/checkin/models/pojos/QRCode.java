package net.csc625.checkin.models.pojos;

public class QRCode
{
    private int qrID;
    private Student student;
    private String active;
    private String code;

    public QRCode(int qrID, Student student, String active, String code)
    {
        this.qrID = qrID;
        this.student = student;
        this.active = active;
        this.code = code;
    }

    public QRCode() {

    }

    public int getQrID() {
        return qrID;
    }

    public void setQrID(int qrID) {
        this.qrID = qrID;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getCodeBytes() {
        return this.code.getBytes();
    }

    @Override
    public String toString()
    {
        return "Student [qrID = " + qrID + ", student = " + student+
                ",active = " + active + ",code = "+code+
                "]";
    }
}
