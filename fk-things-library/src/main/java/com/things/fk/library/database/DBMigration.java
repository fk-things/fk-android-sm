package com.things.fk.library.database;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

/**
 * @author tic
 *         created on 18-3-28
 */

public class DBMigration implements RealmMigration {

    private static volatile DBMigration sInstance;

    private DBMigration() {
    }

    public static synchronized DBMigration getInstance() {
        if (sInstance == null) {
            synchronized (DBMigration.class) {
                if (sInstance == null) {
                    sInstance = new DBMigration();
                }
            }
        }
        return sInstance;
    }

    /**
     * 数据库增加字段或修改数据库变更版本在此方法中进行
     *
     * @param realm
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        // 例子
        /*RealmSchema schema = realm.getSchema();

        // DynamicRealm exposes an editable schema
        RealmSchema schema = realm.getSchema();

        // Migrate to version 1: Add a new class.
        // Example:
        // public Person extends RealmObject {
        //     private String name;
        //     private int age;
        //     // getters and setters left out for brevity
        // }
        if (oldVersion == 0) {
            schema.create("Person")
                    .addField("name", String.class)
                    .addField("age", int.class);
            oldVersion++;
        }

        // Migrate to version 2: Add a primary key + object references
        // Example:
        // public Person extends RealmObject {
        //     private String name;
        //     @PrimaryKey
        //     private int age;
        //     private Dog favoriteDog;
        //     private RealmList<Dog> dogs;
        //     // getters and setters left out for brevity
        // }
        if (oldVersion == 1) {
            schema.get("Person")
                    .addField("id", long.class, FieldAttribute.PRIMARY_KEY)
                    .addRealmObjectField("favoriteDog", schema.get("Dog"))
                    .addRealmListField("dogs", schema.get("Dog"));
            oldVersion++;
        }*/
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
