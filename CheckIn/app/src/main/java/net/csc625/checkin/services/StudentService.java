package net.csc625.checkin.services;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;


import net.csc625.checkin.activities.ChooseStudentActivity;
import net.csc625.checkin.activities.ViewStudentActivity;
import net.csc625.checkin.models.dto.StudentDTO;
import net.csc625.checkin.models.exceptions.ErrorResponse;
import net.csc625.checkin.models.pojos.QRCode;
import net.csc625.checkin.models.pojos.Student;
import net.csc625.checkin.models.pojos.User;
import net.csc625.checkin.utils.OrbitRestClient;
import net.csc625.checkin.utils.OrbitUserPreferences;
import net.csc625.checkin.utils.ServerCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;


public class StudentService extends BaseService {
    private Context context;

    public StudentService(Context context){
        this.context = context;
    }
    public void addStudent(Student newStudent, final ServerCallback<Student> callback){
        OrbitUserPreferences orbitPref = new OrbitUserPreferences(this.context);
        final User user = orbitPref.getUserPreferenceObj("loggedUser");
        newStudent.setUser(user);

        Gson gson = new Gson();
        String json = gson.toJson(newStudent);
        StringEntity entity = null;
        try {
            entity = new StringEntity(json.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Toast.makeText(context, "Creating student, please wait.", Toast.LENGTH_SHORT).show();

        // Sets the URL for the API url
        OrbitRestClient orbitRestClient = getOrbitRestClient(this.context);
        orbitRestClient.post(this.context, "create-student", entity, "application/json",
                new JsonHttpResponseHandler(){
                    @Override
                    public void onStart() {
                        // called before request is started
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject jsonStudent) {
                        // called when success happens
                        Log.i("CreateStudentActivity", "Successfully created new student: " + jsonStudent);
                        Gson gson = new Gson();
                        Student student = gson.fromJson(jsonStudent.toString(), Student.class);
                        callback.onSuccess(student);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        Log.e("CreateStudentActivity", "Error when creating new student: " + errorResponse);
                        Gson gson = new Gson();
                        ErrorResponse er = gson.fromJson(errorResponse.toString(), ErrorResponse.class);
                        callback.onFail(er);
                    }

                    @Override
                    public void onRetry(int retryNo) {
                        // called when request is retried
                    }
                });
    }

    public void getStudent(final ViewStudentActivity activity, final int studentId){
        Log.d("Student", "Getting QR Code for student ID " + studentId);
        OrbitRestClient orbitRestClient = getOrbitRestClient(this.context);
        orbitRestClient.get("get-qr-code-single/" + studentId, null, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray codes) {
                Log.e("Student", "SUCCESS - Getting QR Code for student ID: " + studentId);
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                //QRCode qrCode = gson.fromJson(code.toString(), new TypeToken<QRCode>(){}.getType());
                Log.e("Student", "TEST CODES: " + codes);
                List<QRCode> qrCodeList = gson.fromJson(codes.toString(), new TypeToken<List<QRCode>>(){}.getType());
                activity.updateScreen(qrCodeList.get(0));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.e("Student", "FAILURE - Getting QR Code for student ID: " + studentId);
            }

        });
    }

    /*public void findStudent(final int studentID){
        OrbitUserPreferences orbitPref = new OrbitUserPreferences(this.context);
        final User user = orbitPref.getUserPreferenceObj("loggedUser");

        Gson gson = new Gson();
        String json = gson.toJson(studentDTO);
        StringEntity entity = null;
        try {
            entity = new StringEntity(json.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Sets the URL for the API url
        OrbitRestClient orbitRestClient = getOrbitRestClient(this.context);
        orbitRestClient.post(this.context, "get-student", entity, "application/json",
                new JsonHttpResponseHandler(){
                    @Override
                    public void onStart() {
                        // called before request is started
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject student) {
                        // called when success happens
                        Log.i("FindStudentActivity", "Successfully found student: " + student);
                        // We have a match student. Need to do linking here.
                        //Toast.makeText(context, "Found a matching student." , Toast.LENGTH_SHORT).show();

                        //convert the json response student to a Student object and get the ID
                        Gson gson = new Gson();
                        Student foundStudent = gson.fromJson(student.toString(), Student.class);

                        /*AccountLinkDTO accountLinkDTO = new AccountLinkDTO(user.getUserID(), foundStudent.getStudentId());
                        linkStudent(accountLinkDTO);

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        Log.e("FindStudentActivity", "Error when finding student: " + errorResponse);
                        Toast.makeText(context, "Error finding student.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRetry(int retryNo) {
                        // called when request is retried
                    }
                });
    }*/


    /*public void linkStudent(AccountLinkDTO accountLinkDTO){
        Gson gson = new Gson();
        String json = gson.toJson(accountLinkDTO);
        StringEntity entity = null;
        try {
            entity = new StringEntity(json.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Sets the URL for the API url
        OrbitRestClient orbitRestClient = getOrbitRestClient(this.context);
        orbitRestClient.post(this.context, "link-student", entity, "application/json",
                new JsonHttpResponseHandler(){
                    @Override
                    public void onStart() {
                        // called before request is started
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject account) {
                        // called when success happens
                        Gson gson = new Gson();
                        AccountLink accountLink = gson.fromJson(account.toString(), AccountLink.class);

                        Log.i("StudentService", accountLink.getMessage() + account);
                        // We have a match student. Need to do linking here.
                        Toast.makeText(context, accountLink.getMessage() , Toast.LENGTH_SHORT).show();
                        context.startActivity(HomeActivity.createIntent(context));
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        Log.e("StudentService", "Error when linking student: " + errorResponse);
                        Toast.makeText(context, "Error linking student, please try again.  If the problem persists contact your administrator.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRetry(int retryNo) {
                        // called when request is retried
                    }
                });
    }*/


    public void findLinkedStudents(final ChooseStudentActivity activity)
    {
        OrbitRestClient orbitRestClient = getOrbitRestClient(this.context);
        OrbitUserPreferences orbitPref = new OrbitUserPreferences(this.context);
        final User user = orbitPref.getUserPreferenceObj("loggedUser");

        orbitRestClient.get("find-linked/" + user.getUserID(), null, new JsonHttpResponseHandler(){
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray students) {
                // called when success happens
                Gson gson = new Gson();
                List<Student> studentList = gson.fromJson(students.toString(), new TypeToken<List<Student>>(){}.getType());
                activity.updateStudentList(studentList);

                Log.i("StudentService", studentList.toString());
                Log.i("StudentService", "Find Linked Student - Successful");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.e("StudentService", "Error finding linked students: " + errorResponse);
                // Toast.makeText(context, "Error finding linked students.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }

    /*public void findAllStudents(final EnrollStudentInCourseActivity activity)
    {
        OrbitRestClient orbitRestClient = getOrbitRestClient(this.context);
        orbitRestClient.get("all-students/", null, new JsonHttpResponseHandler(){
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray students) {
                // called when success happens
                Gson gson = new Gson();
                List<Student> studentList = gson.fromJson(students.toString(), new TypeToken<List<Student>>(){}.getType());
                activity.updateStudentList(studentList);

                Log.i("StudentService", "Find Linked Student - Successful");
                //Toast.makeText(context, "FOUND LINKED STUDENTS" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.e("StudentService", "Error finding linked students: " + errorResponse);
                //Toast.makeText(context, "Error finding linked students", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }*/

    /*public void enrollStudentsInCourse(List<Student> enrollList, int courseID)
    {
        EnrollStudentInClassDTO enrollDTO = new EnrollStudentInClassDTO();

        for(int i = 0; i < enrollList.size(); i++)
        {
            enrollDTO.addEnrollRecord(enrollList.get(i).getStudentId(), courseID);
        }

        Gson gson = new Gson();
        String json = gson.toJson(enrollDTO);
        StringEntity entity = null;
        try {
            entity = new StringEntity(json.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Sets the URL for the API url
        OrbitRestClient orbitRestClient = getOrbitRestClient(this.context);
        orbitRestClient.post(this.context, "enroll-students-course", entity, "application/json",
                new JsonHttpResponseHandler(){
                    @Override
                    public void onStart() {
                        // called before request is started
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject schedule) {
                        // called when success happens
                        Gson gson = new Gson();

                        //String scheduleMessage = gson.fromJson(schedule.toString(), AccountLink.class);

                        Log.i("StudentService", schedule.toString());
                        // We have a match student. Need to do linking here.
                        Toast.makeText(context, "Enroll Successful" , Toast.LENGTH_SHORT).show();
                        context.startActivity(HomeActivity.createIntent(context));
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        Log.e("StudentService", "Error when linking student: " + errorResponse);
                        Toast.makeText(context, "Error linking student, please try again.  If the problem persists contact your administrator.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRetry(int retryNo) {
                        // called when request is retried
                    }
                });


    }*/

}
