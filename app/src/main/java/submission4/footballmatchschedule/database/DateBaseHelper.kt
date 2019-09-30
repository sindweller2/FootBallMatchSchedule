package submission4.footballmatchschedule

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DataBaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: DataBaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DataBaseHelper {
            if (instance == null) {
                instance = DataBaseHelper(ctx.applicationContext)
            }
            return instance as DataBaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            Favorite.TABLE_FAVORITE, true,
//            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.idEvent to TEXT + UNIQUE,
            Favorite.dateEvent to TEXT,
            Favorite.strHomeTeam to TEXT,
            Favorite.strAwayTeam to TEXT,
            Favorite.intHomeScore to TEXT,
            Favorite.intAwayScore to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

val Context.database: DataBaseHelper
    get() = DataBaseHelper.getInstance(applicationContext)