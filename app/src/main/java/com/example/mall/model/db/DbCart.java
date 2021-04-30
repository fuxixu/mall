package com.example.mall.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.mall.model.bean.ProductInfo;
import com.example.mall.model.dao.DaoMaster;
import com.example.mall.model.dao.DaoSession;
import com.example.mall.model.dao.ProductInfoDao;

import java.util.List;

public class DbCart {
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
    private ProductInfoDao productInfoDao;

    private static DbCart mDbController;



    /**
     * 获取单例
     */
    public static DbCart getInstance(){
        return mDbController;
    }


    public static void init(Context context){

        if(mDbController == null){
            synchronized (DbCart.class){
                if(mDbController == null){
                    mDbController = new DbCart(context);
                }
            }
        }
    }


    /**
     * 初始化
     * @param context
     */
     public DbCart(Context context) {
        this.context = context;
        mHelper = new DaoMaster.DevOpenHelper(context,"cart.db", null);
        mDaoMaster =new DaoMaster(getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
        productInfoDao = mDaoSession.getProductInfoDao();
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
    public void insertOrReplace(ProductInfo productInfo){
        productInfoDao.insertOrReplace(productInfo);
    }




    /**插入一条记录，表里面要没有与之相同的记录
     *
     * @param productInfo
     */
    public long insert(ProductInfo productInfo){
           return  productInfoDao.insert(productInfo);
    }

    /**
     * 减少数量
     * @param productInfo
     */
    public int subOne(ProductInfo productInfo){
        ProductInfo mOldInfor = productInfoDao.queryBuilder().where(ProductInfoDao.Properties.Id.eq(productInfo.getId())).build().unique();//拿到之前的记录

        if(mOldInfor !=null && mOldInfor.getNumber()!=0){
            mOldInfor.setNumber(mOldInfor.getNumber()-1);
            productInfoDao.update(mOldInfor);
        }
        return 1;
    }

    /**
     * 增加数量
     * @param productInfo
     */
    public int addOne(ProductInfo productInfo){
        ProductInfo mOldInfor = productInfoDao.queryBuilder().where(ProductInfoDao.Properties.Id.eq(productInfo.getId())).build().unique();//拿到之前的记录
        if(mOldInfor !=null ){
            mOldInfor.setNumber(mOldInfor.getNumber()+1);
            productInfoDao.update(mOldInfor);
        }
        return 1;
    }



    /**
     * 更新
     * @param productInfo
     */
    public void update(ProductInfo productInfo){
        ProductInfo mOldInfor = productInfoDao.queryBuilder().where(ProductInfoDao.Properties.Id.eq(productInfo.getId())).build().unique();//拿到之前的记录
        if(mOldInfor !=null){
            mOldInfor.setNumber(mOldInfor.getNumber()+1);
            productInfoDao.update(mOldInfor);
        }
    }


    /**
     * 按条件查询数据
     */
    public List<ProductInfo> searchByWhere(String wherecluse){
        List<ProductInfo>personInfors = (List<ProductInfo>) productInfoDao.queryBuilder().where(ProductInfoDao.Properties.Name.eq(wherecluse)).build().unique();
        return personInfors;
    }
    /**
     * 查询所有数据
     */
    public  List<ProductInfo>  searchAll(){
        List<ProductInfo>personInfors=productInfoDao.queryBuilder().list();
        return personInfors;
    }
    /**
     * 删除数据
     */
    public void delete(String wherecluse){

        productInfoDao.queryBuilder().where(ProductInfoDao.Properties.Name.eq(wherecluse)).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    /**
     * 删除所有数据
     */
    public void deleteAll(){

        productInfoDao.deleteAll();
    }


    synchronized public int getAllNumber() {
            int number = 0;

            List<ProductInfo> productInfos =mDbController.searchAll();
            // 增强型for循环遍历
            for(ProductInfo value:productInfos){
                number = number+value.getNumber();
            }

            return number;
    }

    synchronized public float getAllPrice() {
        float price = 0;
        //获取所有价格
        List<ProductInfo> productInfos = mDbController.searchAll();
        // 增强型for循环遍历
        for (ProductInfo value : productInfos) {
            float a;
            a = value.getPrice() * value.getNumber();
            price = price + a;
        }
        return price;
    }
}
