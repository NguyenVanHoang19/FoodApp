package com.nguyenvanhoang.foodapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.nguyenvanhoang.foodapp.entities.MonAn;

import java.util.ArrayList;

public class CreateDatabaseSQLite extends SQLiteOpenHelper {
    // Bang Mon An
    private static String TB_MONAN = "MONAN_GIOHANG";
    private static String TB_MONAN_ID = "MONAN_ID_GIOHANG";
    private static String TB_MONAN_MAMON = "MA_MON";
    private static String TB_MONAN_TENMON = "TEN_MON";
    private static String TB_MONAN_CHITIET = "CHI_TIET";
    private static String TB_MONAN_HINHANH = "HINH_ANH";
    private static String TB_MONAN_GIA = "GIA";
    private static String TB_MONAN_SOLUONGCHON = "SO_LUONG_CHON";
    private static String TB_MONAN_MANHAHANG = "MA_NHA_HANG";
    private static String TB_MONAN_TENNHAHANG = "TEN_NHA_HANG";
    private static String TB_MONAN_DIACHINHAHANG = "DIA_CHI_NHA_HANG";
    private static String TB_MONAN_MAUSER = "MA_USER";
    private static String TB_MONAN_LAT_NHAHANG = "LAT_NHA_HANG";
    private static String TB_MONAN_LONG_NHAHANG = "LONG_NHA_HANG";
    private static String TB_MONAN_LAT_SOKM = "SOKM_NHA_HANG";
    // user dat hang
    private static String TB_USER = "USER";
    private static String TB_USER_MA = "MA_USER";
    private static String TB_USER_TEN = "TEN_USER";
    private static String TB_USER_GMAIL = "GMAIL_USER";
    private static String TB_USER_MAMONAN = "MAMONAN_USER";


    //
    public CreateDatabaseSQLite(@Nullable Context context) {
        super(context, "FoodApp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tbGioHang = "CREATE TABLE " + TB_MONAN +" ( "+
                TB_MONAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TB_MONAN_MAMON + " TEXT," +
                TB_MONAN_TENMON + " TEXT,"+
                TB_MONAN_CHITIET + " TEXT,"+
                TB_MONAN_HINHANH + " TEXT,"+
                TB_MONAN_GIA + " DOUBLE,"+
                TB_MONAN_SOLUONGCHON + " INTEGER,"+
                TB_MONAN_MANHAHANG + " TEXT,"+
                TB_MONAN_DIACHINHAHANG + " TEXT,"+
                TB_MONAN_TENNHAHANG + " TEXT,"+
                TB_MONAN_MAUSER + " TEXT,"+
                TB_MONAN_LAT_NHAHANG + " DOUBLE,"+
                TB_MONAN_LONG_NHAHANG + " DOUBLE,"+
                TB_MONAN_LAT_SOKM + " FLOAT )";
        String tbUser = " CREATE TABLE " + TB_USER +" ( "+
                TB_USER_GMAIL + " TEXT PRIMARY KEY," +
                TB_USER_TEN + " TEXT,"+
                TB_USER_MAMONAN + " TEXT )";
        sqLiteDatabase.execSQL(tbGioHang);
        sqLiteDatabase.execSQL(tbUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TB_MONAN);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TB_USER);
        onCreate(sqLiteDatabase);
    }
    public int insertMonAn(MonAnCart monAnCart){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TB_MONAN_MAMON,monAnCart.getMaMon());
        contentValues.put(TB_MONAN_TENMON,monAnCart.getTenMon());
        contentValues.put(TB_MONAN_CHITIET,monAnCart.getChiTiet());
        contentValues.put(TB_MONAN_HINHANH,monAnCart.getHinhAnh());
        contentValues.put(TB_MONAN_GIA,monAnCart.getGia());
        contentValues.put(TB_MONAN_SOLUONGCHON,monAnCart.getSoLuongChon());
        contentValues.put(TB_MONAN_MANHAHANG,monAnCart.getMaNhaHang());
        contentValues.put(TB_MONAN_DIACHINHAHANG,monAnCart.getDiaChiNhaHang());
        contentValues.put(TB_MONAN_TENNHAHANG,monAnCart.getTenNhaHang());
        contentValues.put(TB_MONAN_MAUSER,monAnCart.getMaUser());
        contentValues.put(TB_MONAN_LAT_NHAHANG,monAnCart.getLatNhaHang());
        contentValues.put(TB_MONAN_LONG_NHAHANG,monAnCart.getLongNhaHang());
        contentValues.put(TB_MONAN_LAT_SOKM,monAnCart.getSoKm());
        int result = (int)sqLiteDatabase.insert(TB_MONAN,null,contentValues);
        sqLiteDatabase.close();
        return result;
    }
    public ArrayList<MonAnCart> getAllMonAn(){
        ArrayList<MonAnCart> list = new ArrayList<MonAnCart>();
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from " +TB_MONAN,null);
        if(cursor !=  null){
            cursor.moveToFirst();
        }
        while (cursor.isAfterLast() == false){
            MonAnCart monAnCart = new MonAnCart();
            monAnCart.setMaMon(cursor.getString(1));
            monAnCart.setTenMon(cursor.getString(2));
            monAnCart.setChiTiet(cursor.getString(3));
            monAnCart.setHinhAnh(cursor.getString(4));
            monAnCart.setGia(cursor.getDouble(5));
            monAnCart.setSoLuongChon(cursor.getInt(6));
            monAnCart.setMaNhaHang(cursor.getString(7));
            monAnCart.setDiaChiNhaHang(cursor.getString(8));
            monAnCart.setTenNhaHang(cursor.getString(9));
            monAnCart.setMaUser(cursor.getString(10));
            monAnCart.setLatNhaHang(cursor.getDouble(11));
            monAnCart.setLongNhaHang(cursor.getDouble(12));
            monAnCart.setSoKm(cursor.getFloat(13));
            list.add(monAnCart);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public ArrayList<MonAnCart> getAllMonAnCartNoUser(String maUser){
        ArrayList<MonAnCart> list = new ArrayList<MonAnCart>();
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from " +TB_MONAN +" WHERE " +TB_MONAN_MAUSER + " =" +"'" + maUser + "'" ,null);
        if(cursor !=  null){
            cursor.moveToFirst();
        }
        while (cursor.isAfterLast() == false){
            MonAnCart monAnCart = new MonAnCart();
            monAnCart.setMaMon(cursor.getString(1));
            monAnCart.setTenMon(cursor.getString(2));
            monAnCart.setChiTiet(cursor.getString(3));
            monAnCart.setHinhAnh(cursor.getString(4));
            monAnCart.setGia(cursor.getDouble(5));
            monAnCart.setSoLuongChon(cursor.getInt(6));
            monAnCart.setMaNhaHang(cursor.getString(7));
            monAnCart.setDiaChiNhaHang(cursor.getString(8));
            monAnCart.setTenNhaHang(cursor.getString(9));
            monAnCart.setMaUser(cursor.getString(10));
            monAnCart.setLatNhaHang(cursor.getDouble(11));
            monAnCart.setLongNhaHang(cursor.getDouble(12));
            monAnCart.setSoKm(cursor.getFloat(13));
            list.add(monAnCart);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public ArrayList<MonAnCart> getAllMonAnCartByUser(String maUser){
        ArrayList<MonAnCart> list = new ArrayList<MonAnCart>();
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from " +TB_MONAN +" WHERE " +TB_MONAN_MAUSER + " =" +"'" + maUser + "'" ,null);
        if(cursor !=  null){
            cursor.moveToFirst();
        }
        while (cursor.isAfterLast() == false){
            MonAnCart monAnCart = new MonAnCart();
            monAnCart.setMaMon(cursor.getString(1));
            monAnCart.setTenMon(cursor.getString(2));
            monAnCart.setChiTiet(cursor.getString(3));
            monAnCart.setHinhAnh(cursor.getString(4));
            monAnCart.setGia(cursor.getDouble(5));
            monAnCart.setSoLuongChon(cursor.getInt(6));
            monAnCart.setMaNhaHang(cursor.getString(7));
            monAnCart.setDiaChiNhaHang(cursor.getString(8));
            monAnCart.setTenNhaHang(cursor.getString(9));
            monAnCart.setMaUser(cursor.getString(10));
            monAnCart.setLatNhaHang(cursor.getDouble(11));
            monAnCart.setLongNhaHang(cursor.getDouble(12));
            monAnCart.setSoKm(cursor.getFloat(13));
            list.add(monAnCart);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public MonAnCart kiemTraTrungMaMonAn(String maMonAn,String maUser){
        MonAnCart monAnCart = new MonAnCart();
        SQLiteDatabase db =this.getReadableDatabase();
        String sql = "SELECT "+TB_MONAN_MAMON+","+TB_MONAN_SOLUONGCHON+" FROM " +TB_MONAN+" WHERE " + TB_MONAN_MAMON + " ="+ "'" + maMonAn + "'" +
                                                                                            " AND " + TB_MONAN_MAUSER+ " =" + "'" + maUser + "'";
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        while (cursor.isAfterLast() == false){
            monAnCart.setMaMon(cursor.getString(0));
            monAnCart.setSoLuongChon(cursor.getInt(1));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return monAnCart;
    }

    public MonAnCart timMonAnTheoMa(String maMonAn){
        MonAnCart monAnCart = new MonAnCart();
        SQLiteDatabase db =this.getReadableDatabase();
        String sql = "SELECT "+TB_MONAN_MAMON+","+TB_MONAN_SOLUONGCHON+","+TB_MONAN_GIA+" FROM " +TB_MONAN+" WHERE " + TB_MONAN_MAMON + " ="+ "'" + maMonAn + "'";
        Cursor cursor =db.rawQuery(sql,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        while (cursor.isAfterLast() == false){
            monAnCart.setMaMon(cursor.getString(0));
            monAnCart.setSoLuongChon(cursor.getInt(1));
            monAnCart.setGia(cursor.getInt(2));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return monAnCart;
    }
    public int updateSoLuongMonAn(String maMon,int soLuong,String maUser){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TB_MONAN_SOLUONGCHON,soLuong);
        int result = (int)sqLiteDatabase.update(TB_MONAN,contentValues,TB_MONAN_MAMON + "="+"'"+maMon+"'" + " AND " + TB_MONAN_MAUSER + " = " +"'" + maUser + "'",null);
        sqLiteDatabase.close();
        return result;
    }
    public int updateUserMonAnCart(String maNhaHang,String maUser){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TB_MONAN_MAUSER,maUser);
        int result = (int)sqLiteDatabase.update(TB_MONAN,contentValues,TB_MONAN_MANHAHANG + "="+"'"+maNhaHang+"'" + " AND " + TB_MONAN_MAUSER + "=" +"'" + "false"+"'" ,null);
        sqLiteDatabase.close();
        return result;
    }
    public int deleteMonAn(String maMonAn,String maUser){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int result =(int) sqLiteDatabase.delete(TB_MONAN, TB_MONAN_MAMON + " = " + '"' + maMonAn + '"' + " AND " + TB_MONAN_MAUSER + " = "+'"' + maUser + '"',null);
        sqLiteDatabase.close();
        return result;
    }
    public int deleteGioHang(String idNhaHang,String maUser){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int result =(int) sqLiteDatabase.delete(TB_MONAN, TB_MONAN_MANHAHANG + " = " + '"' + idNhaHang + '"' + " AND " + TB_MONAN_MAUSER + " = "+'"' + maUser + '"',null);
        sqLiteDatabase.close();
        return result;
    }
}
