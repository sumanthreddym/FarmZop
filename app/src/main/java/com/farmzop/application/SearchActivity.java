package com.farmzop.application;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

import com.farmzop.application.ProductDetailsAdapter.ProductDetailsListAdapter;
import com.farmzop.application.ProductDetailsAdapter.ProductItemType1;

import java.sql.SQLException;
import java.util.ArrayList;

public class SearchActivity extends Activity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener{

    private ListView mListView;
    private SearchView searchView;
    private ProductDBAdapter mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);

        mListView = (ListView) findViewById(R.id.list);
        mDBHelper = new ProductDBAdapter(this);
        try {
            mDBHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mDBHelper.deleteAllProducts();


        mDBHelper.createProduct(1, "Rice 1", "20", "15", "Ashirvad", "2", R.drawable.rice);
        mDBHelper.createProduct(2, "Rice 2", "22", "18","Ashirvad","8", R.drawable.rice);
        mDBHelper.createProduct(6,"Rice 6","30","25","Ashirvad","2", R.drawable.rice);
        mDBHelper.createProduct(7,"Wheat 1","25","20","Old","2", R.drawable.wheat);
        mDBHelper.createProduct(8,"Wheat 2","35","30", "Ashirvad","2",R.drawable.wheat);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDBHelper != null){
            mDBHelper.close();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        showResults(query+"%");
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        showResults(newText+"%");
        return false;
    }

    @Override
    public boolean onClose() {
        showResults("");
        return false;
    }

    private void showResults(String query){
        Cursor mCursor = mDBHelper.searchProduct(query != null ? query.toString() : "@@@@");
        if (mCursor == null){
        }
        else{
            mCursor.moveToFirst();

            ArrayList<ProductItemType1> resProductItems=new ArrayList<>();

            int i=0;
            if (mCursor != null ) {
                if  (mCursor.moveToFirst()) {
                    do {

                        ProductItemType1 tmpProduct = new ProductItemType1();

                        tmpProduct.setProductId(mCursor.getInt(mCursor.getColumnIndex(ProductDBAdapter.KEY_PRODUCTID)));
                        tmpProduct.setProductTitle(mCursor.getString(mCursor.getColumnIndex(ProductDBAdapter.KEY_PRODUCTNAME)));
                        tmpProduct.setProductBrand(mCursor.getString(mCursor.getColumnIndex(ProductDBAdapter.KEY_BRAND)));
                        tmpProduct.setMarketPrice(mCursor.getString(mCursor.getColumnIndex(ProductDBAdapter.KEY_MRP)));
                        tmpProduct.setOurPrice(mCursor.getString(mCursor.getColumnIndex(ProductDBAdapter.KEY_PRICE)));
                        tmpProduct.setProductQty(mCursor.getString(mCursor.getColumnIndex(ProductDBAdapter.KEY_QTY)));

                        resProductItems.add(i++, tmpProduct);

                    }while( mCursor.moveToNext());
                }
            }


            ProductDetailsListAdapter listAdapter = new ProductDetailsListAdapter(this,resProductItems,null);
            mListView.setAdapter(listAdapter);

        }
    }

}
