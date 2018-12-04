package program.java.punch.andr.myapplication.ui.base.interfaces;


import program.java.punch.andr.myapplication.data.database.dbHelper.interfaces.DbHelper;


public interface BaseMvpInteractor {
    DbHelper getAppDbHelper();
}
