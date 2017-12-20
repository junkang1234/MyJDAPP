package lenovo.example.com.myjdapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class RecordUntils {

    //表名可以抽取出来这个常量
    private static final String TABLE_NAME = "person";
    private SQLiteDatabase db;

    public RecordUntils(Context context){

        SQLitePersonDBOpenHelper helper = new SQLitePersonDBOpenHelper(context);
        db = helper.getWritableDatabase();

    }

    /**
     * 使用特殊的方式添加一条数据记录
     * @param name 用户姓名
     * @param phone 用户电话
     * @return  是一个boolean,true 代表数据添加成功  false  代表数据添加失败
     */

    public boolean add(String name,String phone){

        /**
         * table :  表名
         * nullColumnHack : 声明表中某些字段值可以填也可以不填  然而并没有什么卵用   一般通用做法，给一个:null
         * values : ContentValues 类似我们的Map  map特点 ： key/value
         * key : 表中的列名  value : 指定列的值
         */
        ContentValues values = new ContentValues();
        values.put("name", name);

        long result = db.insert("person",null, values);//insert into

        if(result != -1){
            return true;
        }else{
            return false;
        }

    }

    /**
     * 使用特殊的方式删除一条数据记录
     * @return  返回一个boolean的值，如果返回值 true 代表数据删除成功 否则 false 数据删除失败
     */
    public boolean deleteAll(){
        //参数1：表名
        //参数2：条件
        //参数3：条件值
        db.execSQL("delete from "+TABLE_NAME);
        return true ;
    }
    /**
     * 使用特殊的方式查询所有的数据
     */
    public String findAll(){
        /**
         * distinct ： true 去除重复数据   false 不去除重复数据  默认的情况 false
         * table : 表名
         * columns : 查询的那些列   指定你需要数据库给你返回那些的信息 name,phone,
         * 查询所有的列信息 ： null 等价于 *
         * selection : 查询的条件
         * selectionArgs : 查询的条件值
         * groupBy : 分组查询
         * having : 子查询语句
         * orderBy : 排序    升序/降序
         * limit : 分页
         */
        Cursor cursor = db.query(false, "person", null, null, null, null, null, null, null);
        //创建一个容器
        StringBuffer sb = new StringBuffer();
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            sb.append(name);
        }
        return sb.toString();
    }
}
