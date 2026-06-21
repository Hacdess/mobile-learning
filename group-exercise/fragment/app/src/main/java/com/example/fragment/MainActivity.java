package com.example.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainCallbacks {
    FragmentTransaction ft;
    Fragment1 fragment1;
    Fragment2 fragment2;

    ArrayList<Student> studentList;
    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // activity_main.xml chứa 2 thẻ FrameLayout id: fragment1 và fragment2
        setContentView(R.layout.activity_main);

        initData();

        // 1. Gắn Fragment 1 vào FrameLayout bên trái
        fragment1 = Fragment1.newInstance();
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment1, fragment1);
        ft.commit();

        // 2. Gắn Fragment 2 vào FrameLayout bên phải
        fragment2 = Fragment2.newInstance();
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment2, fragment2);
        ft.commit();

        // Hiển thị dữ liệu mặc định ban đầu sau khi UI đã load (delay nhẹ để Fragment kịp khởi tạo)
        findViewById(android.R.id.content).post(new Runnable() {
            @Override
            public void run() {
                updateFragments();
            }
        });
    }

    private void initData() {
        studentList = new ArrayList<>();
        // Giả lập dữ liệu (Sử dụng 1 icon mặc định trong drawable/mipmap của bạn)
        studentList.add(new Student("A1_1809", "Lê Thị A", "A1", 8.0, R.mipmap.ic_launcher));
        studentList.add(new Student("A1_9829", "Trần Văn B", "A1", 7.5, R.mipmap.ic_launcher));
        studentList.add(new Student("A2_3509", "Nguyễn Thị C", "A2", 9.0, R.mipmap.ic_launcher));
        studentList.add(new Student("A3_4120", "Phạm Văn D", "A3", 6.5, R.mipmap.ic_launcher));
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    // Nơi nhận lệnh điều phối theo chuẩn bài học
    @Override
    public void onMsgFromFragToMain(String sender, String strValue) {
        if (sender.equals("FRAG1")) {
            // Nhận index từ thao tác click list
            currentIndex = Integer.parseInt(strValue);
        } else if (sender.equals("FRAG2")) {
            // Nhận thao tác từ các nút
            if (strValue.equals("FIRST")) currentIndex = 0;
            else if (strValue.equals("PREV") && currentIndex > 0) currentIndex--;
            else if (strValue.equals("NEXT") && currentIndex < studentList.size() - 1) currentIndex++;
            else if (strValue.equals("LAST")) currentIndex = studentList.size() - 1;
        }
        updateFragments();
    }

    // Hàm chung để báo 2 fragment tự làm mới giao diện
    private void updateFragments() {
        if (fragment1 != null) {
            // Báo Fragment 1 đổi vệt màu (highlight)
            fragment1.onMsgFromMainToFragment(String.valueOf(currentIndex));
        }

        if (fragment2 != null) {
            // Gói dữ liệu sinh viên hiện tại thành chuỗi (String) để báo Fragment 2 hiển thị
            Student s = studentList.get(currentIndex);
            String dataString = s.id + "|" + s.name + "|" + s.className + "|" + s.gpa;
            fragment2.onMsgFromMainToFragment(dataString);
        }
    }
}