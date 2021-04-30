package com.example.mall.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.mall.model.bean.OrderInfo;
import com.example.mall.model.bean.ProductInfo;
import com.example.mall.model.dao.DaoMaster;
import com.example.mall.model.dao.DaoSession;
import com.example.mall.model.dao.OrderInfoDao;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DbOrder {
    /**
     * Helper
     */
    private DaoMaster.DevOpenHelper mHelper;//获取Helper对象
    /**
     * 数据库
     */
    private SQLiteDatabase db;
    /**
     * DaoMaster
     */
    private DaoMaster mDaoMaster;
    /**
     * DaoSession
     */
    private DaoSession mDaoSession;
    /**
     * 上下文
     */
    private Context context;
    /**
     * dao
     */
    private com.example.mall.model.dao.OrderInfoDao orderDao;

    private static DbOrder mDbController;



    /**
     * 获取单例
     */
    public static DbOrder getInstance(){
        return mDbController;
    }


    public static void init(Context context){

        if(mDbController == null){
            synchronized (DbOrder.class){
                if(mDbController == null){
                    mDbController = new DbOrder(context);
                }
            }
        }
    }


    /**
     * 初始化
     * @param context
     */
     public DbOrder(Context context) {
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(context,"cart.db", null);
        mDaoMaster =new DaoMaster(getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
        orderDao = mDaoSession.getOrderInfoDao();
    }
    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase(){
        if(mHelper == null){
            mHelper = new DaoMaster.DevOpenHelper(context,"cart.db",null);
        }
        SQLiteDatabase db =mHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     * @return
     */
    private SQLiteDatabase getWritableDatabase(){
        if(mHelper == null){
            mHelper =new DaoMaster.DevOpenHelper(context,"cart.db",null);
        }
        SQLiteDatabase db = mHelper.getWritableDatabase();
        return db;
    }




    /**
     * 会自动判定是插入还是替换
     * @param productInfo
     */
    public void insertOrReplace(OrderInfo productInfo){
        orderDao.insertOrReplace(productInfo);
    }




    /**插入一条记录，表里面要没有与之相同的记录
     *
     * @param productInfo
     */
    public long insert(OrderInfo productInfo){
           return  orderDao.insert(productInfo);
    }





    /**
     * 更新
     * @param productInfo
     */
    public void update(OrderInfo productInfo){
        OrderInfo mOldInfor = orderDao.queryBuilder().where(OrderInfoDao.Properties.Id.eq(productInfo.getId())).build().unique();//拿到之前的记录
        if(mOldInfor !=null){
            mOldInfor.setNumber(mOldInfor.getNumber()+1);
            orderDao.update(mOldInfor);
        }
    }


    /**
     * 按条件查询数据
     */
    public List<OrderInfo> searchByWhere(String wherecluse){
        List<OrderInfo>personInfors = (List<OrderInfo>) orderDao.queryBuilder().where(OrderInfoDao.Properties.Name.eq(wherecluse)).build().unique();
        return personInfors;
    }
    /**
     * 查询所有数据
     */
    public  List<OrderInfo>  searchAll(){
        List<OrderInfo>personInfors=orderDao.queryBuilder().list();
        return personInfors;
    }
    /**
     * 删除数据
     */
    public void delete(String wherecluse){

        orderDao.queryBuilder().where(OrderInfoDao.Properties.Name.eq(wherecluse)).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    /**
     * 删除所有数据
     */
    public void deleteAll(){

        orderDao.deleteAll();
    }

    synchronized public void  save() {
        String time ;
        String name = null;
        String number = null;
        String  allPrice = String.valueOf(DbCart.getInstance().getAllPrice());
        Boolean status=false;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
         time =simpleDateFormat.format(date);

        List<ProductInfo> info = DbCart.getInstance().searchAll();
        // 增强型for循环遍历
        for (ProductInfo  value : info) {
            name = name+value.getName()+"\n";
            number = number+value.getNumber()+"\n";
        }

        OrderInfo a = new OrderInfo();
        a.setTime(time);
        a.setName(name);
        a.setNumber(number);
        a.setAllPrice(allPrice);
        a.setStatus(false);
        mDbController.insert(a);
    }


}
