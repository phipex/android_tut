package app.phipex.com.recordando.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import app.phipex.com.recordando.R;

/**
 * Created by sony vaio on 21/12/2015.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "agenda.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Contacto, Integer> contactoDao = null;
    private RuntimeExceptionDao<Contacto,Integer> contactoRuntimeDao = null;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION, R.raw.ormllite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getSimpleName(), "onCreate");
            TableUtils.createTable(connectionSource,Contacto.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getSimpleName(),"imposible crear la base de datos",e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getSimpleName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Contacto.class, true);
            onCreate(database,connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getSimpleName(),"imposible eliminar la base de datos",e);
            throw new RuntimeException(e);
        }
    }

    public Dao<Contacto, Integer> getContactoDao() throws SQLException {
        if(contactoDao == null){
            contactoDao = getDao(Contacto.class);
        }

        return contactoDao;
    }

    public RuntimeExceptionDao<Contacto, Integer> getContactoRuntimeDao() {
        if (contactoRuntimeDao == null){
            contactoRuntimeDao = getRuntimeExceptionDao(Contacto.class);
        }

        return contactoRuntimeDao;
    }

    @Override
    public void close() {

        super.close();
        contactoDao = null;
        contactoRuntimeDao = null;
    }
}
