package com.example.rashwan.playacademy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rashwan.playacademy.Models.Course;
import com.example.rashwan.playacademy.Models.Teacher;

import org.json.JSONObject;

import java.util.ArrayList;

public class AllCourses extends AppCompatActivity {

    ListView coursesList;
    ArrayList<Course> courses;
    EditText searchBar;
    ImageButton searchButton;
    ImageButton cancel;
    TextView noCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_courses);
        initialize();

        if (((Teacher)Login.loggedUser).getCreatedCourses()==null){
            String link=ServicesLinks.GET_COURSES_BY_TEACHER_URL+"?teacherId="+Login.loggedUser.getUserId();
            RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, link, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Course> searchResult=search(searchBar.getText().toString());
                cancel.setVisibility(View.VISIBLE);

                if (searchResult.size()==0){
                    noCourse.setVisibility(View.VISIBLE);
                    coursesList.setAdapter(null);
                }
                else {
                    noCourse.setVisibility(View.GONE);
                    CourseAdapter searchAdapter=new CourseAdapter(getApplicationContext(),searchResult);
                    coursesList.setAdapter(null);
                    coursesList.setAdapter(searchAdapter);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchButton.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.GONE);
                noCourse.setVisibility(View.GONE);
                searchBar.setText("");
                searchBar.clearFocus();
                CourseAdapter searchAdapter=new CourseAdapter(getApplicationContext(),courses);
                coursesList.setAdapter(searchAdapter);
            }
        });

    }

    public void initialize(){
        coursesList=(ListView) findViewById(R.id.coursesList);
        courses=new ArrayList<>();
        searchBar=(EditText)findViewById(R.id.searchBar);
        searchButton=(ImageButton) findViewById(R.id.searchButton);
        cancel=(ImageButton) findViewById(R.id.cancelButton);
        noCourse=(TextView)findViewById(R.id.noCourse);
    }

    public ArrayList<Course> search(String courseName){
        ArrayList<Course> indexes=new ArrayList<>();
        for (int i=0;i<courses.size();i++){
            if (courses.get(i).getCourseName().contains(courseName)){
                indexes.add(courses.get(i));
            }
        }
        return indexes;
    }

}