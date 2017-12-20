package lenovo.example.com.myjdapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

public class MyExpandListView extends ExpandableListView {




    public MyExpandListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, i);
    }
}