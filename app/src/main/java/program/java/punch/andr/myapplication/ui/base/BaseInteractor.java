package program.java.punch.andr.myapplication.ui.base;

import javax.inject.Inject;

import program.java.punch.andr.myapplication.data.database.dbHelper.interfaces.DbHelper;
import program.java.punch.andr.myapplication.ui.base.interfaces.BaseMvpInteractor;


public class BaseInteractor implements BaseMvpInteractor {
    private final DbHelper mAppDbHelper;


    @Inject
    public BaseInteractor(DbHelper appDbHelper) {
        mAppDbHelper = appDbHelper;

    }


    @Override
    public DbHelper getAppDbHelper() {
        return mAppDbHelper;
    }


}
