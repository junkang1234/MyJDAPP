package lenovo.example.com.myjdapp.untils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import lenovo.example.com.myjdapp.R;
import lenovo.example.com.myjdapp.bean.SelectBean;


public class MyTextView extends LinearLayout{


    private Button btn_add;
    private Button btn_jian;
    private EditText edit_num;
    private String text;
    private int price = 0 ;
    private int num1 =0;

    public int getNum() {
//        for (SelectBean.DataBean dd : group_list){
//            List<SelectBean.DataBean.ListBean> list = dd.getList();
//            for (SelectBean.DataBean.ListBean ddl : list){
//                if(ddl.isItemCheck()){
//                    num1+=ddl.getNum();
//                }
//            }
//        }
        return num1;
    }

    public void setNum(int num) {
        this.num1 = num;
    }

    public int getPrice() {

//        for (SelectBean.DataBean dd : group_list){
//            List<SelectBean.DataBean.ListBean> list = dd.getList();
//            for (SelectBean.DataBean.ListBean ddl : list){
//                if(ddl.isItemCheck()){
//                    num1+=ddl.getNum();
//                }
//            }
//        }
//
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        edit_num.setText(text);
    }
    private int i;
    private int i1;
    private  List<List<SelectBean.DataBean.ListBean>> child_list = new ArrayList<>();

    public List<List<SelectBean.DataBean.ListBean>> getChild_list() {
        return child_list;
    }
    private List<SelectBean.DataBean> group_list = new ArrayList<>();

    public List<SelectBean.DataBean> getGroup_list() {
        return group_list;
    }

    public void setGroup_list(List<SelectBean.DataBean> group_list) {
        this.group_list = group_list;
    }

    public void setChild_list(List<List<SelectBean.DataBean.ListBean>> child_list) {
        this.child_list = child_list;
    }

    public int getI1() {
        return i1;
    }

    public void setI1(int i1) {
        this.i1 = i1;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public MyTextView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        //设置view
        View v = View.inflate(context, R.layout.myview,this);
        btn_add = v.findViewById(R.id.addnum);
        btn_jian = v.findViewById(R.id.jiannum);
        edit_num = v.findViewById(R.id.edit_num);

        btn_jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = edit_num.getText().toString();
                int num = Integer.valueOf(s);
                if(num>1){
                    num--;
                    for (int i=0;i<child_list.size();i++){
                        List<SelectBean.DataBean.ListBean> listBeen = child_list.get(i);
                        for (int y=0;y<listBeen.size();y++){
                            if(listBeen.get(y).isItemCheck()){
                                num1=listBeen.get(y).getNum()-1;
                                price= (int) (listBeen.get(y).getPrice()*num1);
                            }
                        }
                    }
                }else {
                    num = 1 ;
                    //吐司事件
                }
                edit_num.setText(num+"");
                child_list.get(i).get(i1).setNum(num);
                if(num1!=0){
                    onclick.setOnClick(num1,price);
                    Log.d("qq","打印的数量：" + num1 + "打印的价格：" + price + "group_list " +group_list.toString());
                }

            }
        });
        btn_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = edit_num.getText().toString();
                int num = Integer.valueOf(s);
                if(num<99){
                    num++;
                    for (int i=0;i<child_list.size();i++){
                        List<SelectBean.DataBean.ListBean> listBeen = child_list.get(i);
                        for (int y=0;y<listBeen.size();y++){
                            if(listBeen.get(y).isItemCheck()){
                                num1=listBeen.get(y).getNum()+1;
                                price= (int) (listBeen.get(y).getPrice()*num1);
                            }
                        }
                    }
                }else {
                    num=99 ;
                    //吐司事件
                }
                edit_num.setText(num+"");
                child_list.get(i).get(i1).setNum(num);
                if(num1!=0){
                    onclick.setOnClick(num1,price);
                    Log.d("qq","打印的数量：" + num1 + "打印的价格：" + price + "group_list " +group_list.toString());
                }

            }
        });

    }

    Onclick onclick ;

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }

    public interface Onclick{
        void  setOnClick(int num, int peice);
    }

}
