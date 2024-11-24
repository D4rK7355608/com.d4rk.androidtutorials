package com.d4rk.androidtutorials.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.d4rk.androidtutorials.data.database.converters.Converters
import com.d4rk.androidtutorials.data.database.dao.FavoriteLessonsDao
import com.d4rk.androidtutorials.data.database.table.FavoriteLessonTable

@Database(entities = [FavoriteLessonTable::class] , version = 1 , exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteLessonsDao() : FavoriteLessonsDao
}

val MIGRATION_1_2 = object : Migration(1 , 2) {
    override fun migrate(db : SupportSQLiteDatabase) {
        db.execSQL(
            sql = """
            CREATE TABLE IF NOT EXISTS `Favorite Lessons_new` (
                `lessonId` TEXT PRIMARY KEY NOT NULL,
                `lessonTitle` TEXT NOT NULL,
                `lessonDescription` TEXT NOT NULL,
                `lessonType` TEXT NOT NULL,
                `lessonTags` TEXT NOT NULL,
                `thumbnailImageUrl` TEXT NOT NULL,
                `squareImageUrl` TEXT NOT NULL,
                `deepLinkPath` TEXT NOT NULL,
                `isFavorite` INTEGER NOT NULL
            )
        """
        )

        db.execSQL(
            sql = """
                        INSERT INTO `Favorite Lessons_new` (`lessonId`, `lessonTitle`, `lessonDescription`, `lessonType`, 
                            `lessonTags`, `thumbnailImageUrl`, `squareImageUrl`, `deepLinkPath`, `isFavorite`)
                        SELECT `id`, `title`, `description`, `type`, `tags`, `bannerImageUrl`, `squareImageUrl`, `deepLinkPath`, `isFavorite`
                        FROM `Favorite Lessons`
                    """
        )

        db.execSQL(sql = "DROP TABLE `Favorite Lessons`")

        db.execSQL(sql = "ALTER TABLE `Favorite Lessons_new` RENAME TO `Favorite Lessons`")
    }
}