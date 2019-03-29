package net.csc625.checkin.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.csc625.checkin.R;
import net.csc625.checkin.models.pojos.QRCode;
import net.csc625.checkin.services.StudentService;

import org.w3c.dom.Text;

import java.util.List;

public class ViewStudentActivity extends BaseActivity {
    private static int studentID;

    public static Intent createIntent(Context context, int studentID) {
        Intent i = new Intent(context, ViewStudentActivity.class);
        ViewStudentActivity.studentID = studentID;
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //need to inflate this activity inside the relativeLayout inherited from BaseActivity.  This will add this view to the mainContent layout
        getLayoutInflater().inflate(R.layout.activity_view_student, relativeLayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent myIntent = getIntent(); // gets the previously created intent

        StudentService studentService = new StudentService(this);
        studentService.getStudent(this, ViewStudentActivity.studentID);
    }

    public void updateScreen(QRCode qrCode) {

        //update student name
        TextView viewStudent = (TextView)findViewById(R.id.txtStudentName);
        viewStudent.setText(qrCode.getStudent().getStudentFirstName() + " " + qrCode.getStudent().getStudentLastName());

        //convert returns bytes to drawable bitmap image
        byte [] encodeByte= Base64.decode(qrCode.getCode(),Base64.DEFAULT);
        Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

        ImageView image = (ImageView) findViewById(R.id.imgQRCode);

        image.setImageBitmap(Bitmap.createScaledBitmap(bitmap, image.getWidth(),
                image.getHeight(), false));
        image.setVisibility(View.GONE);
        image.setVisibility(View.VISIBLE);
    }
}
