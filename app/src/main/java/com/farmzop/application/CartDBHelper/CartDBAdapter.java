package com.farmzop.application.CartDBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.farmzop.application.ProductDetailsAdapter.ProductItemType1;

import java.sql.SQLException;
import java.util.ArrayList;

public class CartDBAdapter {

    public static final String KEY_PRODUCTNAME = "pname";
    public static final String KEY_PRICE = "price";
    public static final String KEY_ICON = "icon";
    public static final String KEY_QTY = "qty";
    public static final String KEY_MRP = "mrp";
    public static final String KEY_PRODUCTID = "pid";
    public static final String KEY_BRAND = "brand";

    private static final String TAG = "cartDB";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "CartProductData";
    private static final String CART_TABLE = "CartProductInfo";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "CREATE TABLE " + CART_TABLE + "(" +
                    KEY_PRODUCTID +" INTEGER ," +
                    KEY_PRODUCTNAME + " TEXT ," +
                    KEY_MRP + " TEXT ," +
                    KEY_PRICE + " TEXT ," +
                    KEY_QTY + " TEXT," +
                    KEY_BRAND + " TEXT," +
                    " UNIQUE (" + KEY_PRODUCTID + "));";

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(TAG, DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + CART_TABLE);
            onCreate(db);
        }
    }

    public CartDBAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public CartDBAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    public long createProduct(int pid,String productName,String mrp, String price,String brand,String Qty, int icon) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_PRODUCTID, pid);
        initialValues.put(KEY_PRODUCTNAME, productName);
        initialValues.put(KEY_PRICE, price);
        initialValues.put(KEY_MRP,mrp);
        initialValues.put(KEY_BRAND, brand);
        initialValues.put(KEY_QTY,Qty);


        return mDb.insert(CART_TABLE, null, initialValues);
    }


/*
    public Cursor searchProduct(String inputText) throws android.database.SQLException {
        Log.w(TAG, inputText);
        String query = "SELECT docid as _id," +
                KEY_PRODUCTNAME + "," +
                KEY_PRICE +
                " from " + CART_TABLE +
                " where " + KEY_SEARCH + " MATCH'" + inputText + "';";
        Log.w(TAG, query);
        Cursor mCursor = mDb.rawQuery(query, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
*/

    /**
     * This function retrives the Cart item data from the db
     * @return the Cart items from the databases
     * @throws android.database.SQLException
     */
    public ArrayList<ProductItemType1> getAllCartItems() throws android.database.SQLException {

        String query = "SELECT *" +
                " from " + CART_TABLE +";";
        Log.w(TAG, query);

        //mCursor holds the result
        Cursor mCursor = mDb.rawQuery(query, null);

            mCursor.moveToFirst();

        ArrayList<ProductItemType1> arrayListCartItems=new ArrayList<>();
        int i=0;
        if (mCursor != null ) {
            if  (mCursor.moveToFirst()) {
                do {

                    ProductItemType1 tmpProduct = new ProductItemType1();

                    tmpProduct.setProductId(mCursor.getInt(mCursor.getColumnIndex(KEY_PRODUCTID)));
                    tmpProduct.setProductTitle(mCursor.getString(mCursor.getColumnIndex(KEY_PRODUCTNAME)));
                    tmpProduct.setProductBrand(mCursor.getString(mCursor.getColumnIndex(KEY_BRAND)));
                    tmpProduct.setMarketPrice(mCursor.getString(mCursor.getColumnIndex(KEY_MRP)));
                    tmpProduct.setOurPrice(mCursor.getString(mCursor.getColumnIndex(KEY_PRICE)));
                    tmpProduct.setProductQty(mCursor.getString(mCursor.getColumnIndex(KEY_QTY)));

                    arrayListCartItems.add(i++, tmpProduct);

                }while( mCursor.moveToNext());
            }
        }

        return arrayListCartItems;
    }

    /**
     * Deletes a row based its primary key
     * @return true iif deletion is successfull
     */
    public boolean deleteRowItem(int pid)
    {
        return mDb.delete(CART_TABLE, KEY_PRODUCTID + "=" + pid, null) > 0;
    }

    /**
     * Updates the database values
     * @param pid identifies the row to be updated
     * @param qty identifies the quantity to be changed
     * @return true if row changed
     */
    public boolean updateQuantity(int pid,int qty)
    {  ContentValues data=new ContentValues();
        data.put(KEY_QTY,qty);

        return mDb.update(CART_TABLE, data, KEY_PRODUCTID +"="+ pid, null) > 0;
    }


    public boolean deleteAllProducts() {
        int doneDelete = 0;
        doneDelete = mDb.delete(CART_TABLE, null, null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;
    }
}
