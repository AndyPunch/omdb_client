package program.java.punch.andr.myapplication.ui.base.interfaces;


import program.java.punch.andr.myapplication.db.dbHelper.interfaces.DbHelper;


public interface BaseMvpInteractor {
    DbHelper getAppDbHelper();
}
