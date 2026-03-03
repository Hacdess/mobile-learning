package com.example.list;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.list.R;

import org.w3c.dom.Text;

public class MainActivity extends Activity {
    TextView txtMsg; ViewGroup scrollViewgroup;
    ImageView icon; TextView caption;
    ImageView imageSelected;
    String[] items = {"duyen", "giang", "kien", "minh", "nam"};
    Integer[] thumbnails = {R.drawable.duyen, R.drawable.giang, R.drawable.kien, R.drawable.minh, R.drawable.nam};
    Integer[] largeImages = {R.drawable.duyen, R.drawable.giang, R.drawable.kien, R.drawable.minh, R.drawable.nam};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMsg= (TextView) findViewById(R.id.txtMsg);
        imageSelected= (ImageView) findViewById(R.id.imageSelected);
        scrollViewgroup= (ViewGroup) findViewById(R.id.viewgroup);
        for(int i = 0; i < items.length; i++) {
            final View singleFrame= getLayoutInflater().inflate(R.layout.frame_icon_caption, null);
            singleFrame.setId(i);
            TextView caption = (TextView) singleFrame.findViewById(R.id.caption);
            ImageView icon = (ImageView) singleFrame.findViewById(R.id.icon);
            icon.setImageResource(thumbnails[i]);
            caption.setText(items[i]); caption.setBackgroundColor(Color.YELLOW);
            scrollViewgroup.addView(singleFrame);
            singleFrame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtMsg.setText("Selected position: "+ singleFrame.getId() + " "+ items[singleFrame.getId()]);
                    showLargeImage(singleFrame.getId());
                }
            });// listener
        }
    }
    protected void showLargeImage(int frameId) {
        Drawable selectedLargeImage= getResources().getDrawable(largeImages[frameId], getTheme()); //API-21 or newer
        imageSelected.setBackground(selectedLargeImage);
    }
}
