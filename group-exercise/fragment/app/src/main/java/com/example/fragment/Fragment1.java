package com.example.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class Fragment1 extends Fragment implements FragmentCallbacks {
    MainActivity main;
    ListView listView;
    StudentAdapter adapter;
    ArrayList<Student> studentList;
    int selectedPosition = 0;

    public static Fragment1 newInstance() {
        return new Fragment1();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof MainCallbacks)) {
            throw new IllegalStateException("Activity must implement MainCallbacks");
        }
        main = (MainActivity) getActivity();
        studentList = main.getStudentList(); // Lấy danh sách từ Main
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        listView = view.findViewById(R.id.list_view_fragment1);

        adapter = new StudentAdapter(getContext(), studentList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Gửi thông báo lên Activity là Item vị trí 'position' vừa được click
                main.onMsgFromFragToMain("FRAG1", String.valueOf(position));
            }
        });

        return view;
    }

    @Override
    public void onMsgFromMainToFragment(String strValue) {
        // Nhận lệnh từ Main báo cập nhật vệt màu
        selectedPosition = Integer.parseInt(strValue);
        if(adapter != null) adapter.notifyDataSetChanged();
        listView.smoothScrollToPosition(selectedPosition);
    }

    // Custom Adapter để hiển thị Image và Text
    class StudentAdapter extends ArrayAdapter<Student> {
        public StudentAdapter(Context context, ArrayList<Student> students) {
            super(context, 0, students);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            }
            Student student = getItem(position);
            ImageView imgAvatar = convertView.findViewById(R.id.img_avatar);
            TextView txtId = convertView.findViewById(R.id.txt_item_user_id);

            imgAvatar.setImageResource(student.avatarRes);
            txtId.setText(student.id);

            // Đổi màu nền nếu item đang được chọn (highlight)
            if (position == selectedPosition) {
                convertView.setBackgroundColor(Color.LTGRAY);
            } else {
                convertView.setBackgroundColor(Color.TRANSPARENT);
            }
            return convertView;
        }
    }
}
