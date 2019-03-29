package net.csc625.checkin.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.csc625.checkin.R;
import net.csc625.checkin.models.pojos.Student;
import net.csc625.checkin.services.PopupService;
import net.csc625.checkin.services.StudentService;
import net.csc625.checkin.utils.PopupMessages;

import java.util.ArrayList;
import java.util.List;

public class ChooseStudentActivity extends BaseActivity {
    private RecyclerView recyclerView;

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, ChooseStudentActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_choose_student);
        context = this;
        //need to inflate this activity inside the relativeLayout inherited from BaseActivity.  This will add this view to the mainContent layout
        getLayoutInflater().inflate(R.layout.activity_choose_student, relativeLayout);

        //figure out which action to perform on click
        ChooseStudentActivity.Adapter.actionType = getIntent().getIntExtra("actionType", 0);

        //get UID of current user
        StudentService studentService = new StudentService(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(this));

        studentService.findLinkedStudents(this);

    }

    public void updateStudentList(List<Student> studentList)
    {
        if (studentList.size() < 1) {
            TextView noStudents = (TextView)findViewById(R.id.noStudents);
            noStudents.setVisibility(View.VISIBLE);
            return;
        }

        recyclerView.setVisibility(View.VISIBLE);

        Adapter.students.clear();
        for(Student s : studentList){
            Adapter.students.add(s);
        }
        reloadList();
    }

    public void reloadList()
    {
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public static class Adapter extends RecyclerView.Adapter<ViewHolder> {

        private final Activity context;
        private static List<Student> students = new ArrayList<>();
        private static int actionType = 0;

        public Adapter(Activity context) {
            this.context = context;

            /*if(students.size() <= 0) {
                //inital seed of list if needed
            }*/
        }

        public static void addStudent(Student student)
        {
            students.add(student);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = context.getLayoutInflater().inflate(R.layout.student_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Student student = students.get(position);

            holder.txtStudentName.setText(student.getStudentFirstName() + " " + student.getStudentLastName());

            //set created text info section
            StringBuilder sb = new StringBuilder();

            //String testImage = "http://media2.s-nbcnews.com/j/streams/2013/june/130617/6c7911377-tdy-130617-leo-toasts-1.nbcnews-ux-2880-1000.jpg";
            //Glide.with(context).load(testImage).into(holder.memeImage);
        }

        @Override
        public int getItemCount() {
            return students.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public final ImageView iconImage;
        public final TextView txtStudentName;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            iconImage = (ImageView) itemView.findViewById(R.id.iconImage);
            iconImage.setImageResource(R.drawable.ic_perm_identity_white_24px);
            txtStudentName = (TextView) itemView.findViewById(R.id.txtStudentName);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Student student = ChooseStudentActivity.Adapter.students.get(position);
            int studentID = student.getStudentID();
            String studentFullName = student.getStudentFirstName() + " " + student.getStudentLastName();

            Context context = itemView.getContext();
            //Intent intent = ViewStudentActivity.createIntent(context, studentID);
            Intent intent = ViewStudentActivity.createIntent(context, studentID);
            context.startActivity(intent);

            /*if(ChooseStudentActivity.Adapter.actionType == 0) {
                Context context = itemView.getContext();
                Intent intent = CourseGradesActivity.createIntent(context);
                intent.putExtra("chosenStudentID", studentID);
                intent.putExtra("studentFullName", studentFullName);
                context.startActivity(intent);
            }
            else if(ChooseStudentActivity.Adapter.actionType == 1) {
                Context context = itemView.getContext();
                Intent intent = ViewMyConductActivity.createIntent(context);
                intent.putExtra("chosenStudentID", studentID);
                intent.putExtra("studentFullName", studentFullName);
                context.startActivity(intent);
            }
            else if(ChooseStudentActivity.Adapter.actionType == 2) {
                Context context = itemView.getContext();
                Intent intent = MyAttendanceActivity.createIntent(context);
                intent.putExtra("chosenStudentID", studentID);
                intent.putExtra("studentFullName", studentFullName);
                context.startActivity(intent);
            }*/
        }

        @Override
        public boolean onLongClick(View v) {
            /*int position = getAdapterPosition();
            String top = Adapter.memes.get(position).getTxtTop();
            String bottom = Adapter.memes.get(position).getTxtBottom();
            String url = Adapter.memes.get(position).getMemeURL();
            Context context = itemView.getContext();
            int TEST = 0;
            context.startActivity(EditMeme.createIntent(
                    context, Adapter.memes, position, itemView, top, bottom, url));*/

            return false;
        }

    }

    private Context context;

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menu_info:
                PopupService p = new PopupService(context);
                p.showPopup(PopupMessages.CHOOSE_STUDENT_MESSAGE);
        }


        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }
}