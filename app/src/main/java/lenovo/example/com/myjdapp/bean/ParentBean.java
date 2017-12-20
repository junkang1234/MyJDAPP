package lenovo.example.com.myjdapp.bean;


public class ParentBean {
    public String title;        //一级列表的标题
    public boolean isCheck;     //一级列表的CheckBox
    public boolean ziCheck;     //一级列表的编辑开关
    public ParentBean(String title,boolean isCheck,boolean ziCheck)
    {
        this.ziCheck=ziCheck;
        this.title=title;
        this.isCheck=isCheck;
    }
}
