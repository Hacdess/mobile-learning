package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment implements FragmentCallbacks {
    MainActivity main;
    TextView tvBigId, tvName, tvClass, tvGpa;
    Button btnFirst, btnPrev, btnNext, btnLast;

    public static Fragment2 newInstance() {
        return new Fragment2();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!(getActivity() instanceof MainCallbacks)) {
            throw new IllegalStateException("Activity must implement MainCallbacks");
        }
        main = (MainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);

        tvBigId = view.findViewById(R.id.tv_big_id);
        tvName = view.findViewById(R.id.tv_name);
        tvClass = view.findViewById(R.id.tv_class);
        tvGpa = view.findViewById(R.id.tv_gpa);

        btnFirst = view.findViewById(R.id.btn_first);
        btnPrev = view.findViewById(R.id.btn_prev);
        btnNext = view.findViewById(R.id.btn_next);
        btnLast = view.findViewById(R.id.btn_last);

        // Gắn sự kiện click các nút điều hướng
        View.OnClickListener btnListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.btn_first) main.onMsgFromFragToMain("FRAG2", "FIRST");
                else if (id == R.id.btn_prev) main.onMsgFromFragToMain("FRAG2", "PREV");
                else if (id == R.id.btn_next) main.onMsgFromFragToMain("FRAG2", "NEXT");
                else if (id == R.id.btn_last) main.onMsgFromFragToMain("FRAG2", "LAST");
            }
        };

        btnFirst.setOnClickListener(btnListener);
        btnPrev.setOnClickListener(btnListener);
        btnNext.setOnClickListener(btnListener);
        btnLast.setOnClickListener(btnListener);

        return view;
    }

    @Override
    public void onMsgFromMainToFragment(String strValue) {
        // Nhận dữ liệu sinh viên từ MainActivity dạng "ID|Tên|Lớp|Điểm"
        String[] data = strValue.split("\\|");
        if (data.length == 4) {
            tvBigId.setText(data[0]);
            tvName.setText("Họ tên: " + data[1]);
            tvClass.setText("Lớp: " + data[2]);
            tvGpa.setText("Điểm trung bình: " + data[3]);
        }
    }
}