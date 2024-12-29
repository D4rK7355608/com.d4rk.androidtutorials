package com.d4rk.androidtutorials.data.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 : Migration = object : Migration(startVersion = 1 , endVersion = 2) {
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
                        """.trimIndent()
        )

        db.execSQL(
            sql = """
                            INSERT INTO `Favorite Lessons_new` (
                                `lessonId`, `lessonTitle`, `lessonDescription`, `lessonType`, 
                                `lessonTags`, `thumbnailImageUrl`, `squareImageUrl`, `deepLinkPath`, `isFavorite`
                            )
                            SELECT 
                                `lessonId`,         -- or whatever your original PK column was
                                `title`, 
                                `description`,
                                `type`,
                                `tags`,
                                `bannerImageUrl`,   -- this becomes thumbnailImageUrl
                                `squareImageUrl`,
                                `deepLinkPath`,
                                `isFavorite`
                            FROM `Favorite Lessons`
                        """.trimIndent()
        )

        db.execSQL(sql = "DROP TABLE `Favorite Lessons`")

        db.execSQL(sql = "ALTER TABLE `Favorite Lessons_new` RENAME TO `Favorite Lessons`")
    }
}

val MIGRATION_2_3 : Migration = object : Migration(startVersion = 2 , endVersion = 3) {
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
                        """.trimIndent()
        )

        db.execSQL(
            sql = """
                            INSERT INTO `Favorite Lessons_new` (
                                `lessonId`, `lessonTitle`, `lessonDescription`, `lessonType`, 
                                `lessonTags`, `thumbnailImageUrl`, `squareImageUrl`, `deepLinkPath`, `isFavorite`
                            )
                            SELECT
                                `lessonId`, `lessonTitle`, `lessonDescription`, `lessonType`,
                                `lessonTags`, `thumbnailImageUrl`, `squareImageUrl`, `deepLinkPath`, `isFavorite`
                            FROM `Favorite Lessons`
                        """.trimIndent()
        )

        db.execSQL(sql = "DROP TABLE `Favorite Lessons`")

        db.execSQL(sql = "ALTER TABLE `Favorite Lessons_new` RENAME TO `Favorite Lessons`")
    }
}
