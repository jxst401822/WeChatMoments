package com.john.wechatmoments.model.db;


import android.text.TextUtils;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by John on 17/12/4.
 */

public class RealmHelper implements DBHelper {

    private static final String DB_NAME = "myRealm.realm";

    private Realm mRealm;

    @Inject
    public RealmHelper() {
        mRealm = Realm.getInstance(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build());
    }

    /**
     * 数据插入
     *
     * @param bean
     */
    @Override
    public void tInsert(RealmObject bean) {
        mRealm.beginTransaction();
        mRealm.insert(bean);
        mRealm.commitTransaction();
    }

    /**
     * 批量保存插入数据
     *
     * @param list
     */
    @Override
    public boolean tInsert(List<? extends RealmObject> list) {
        try {
            mRealm.beginTransaction();
            mRealm.insert(list);
            mRealm.commitTransaction();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.cancelTransaction();
            return false;
        }
    }


    /**
     * 数据更新（如果操作的对象没有PrimaryKey, 会报错）
     *
     * @param bean
     */
    @Override
    public void tUpdate(RealmObject bean) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(bean);
        mRealm.commitTransaction();
    }

    /**
     * 批量添加或者修改
     *
     * @param list
     * @return
     */
    @Override
    public boolean tInsertOrUpdateBatch(List<? extends RealmObject> list) {
        if (list == null || list.size() < 1)
            return true;
        try {
            mRealm.beginTransaction();
            mRealm.insertOrUpdate(list);
            mRealm.commitTransaction();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.cancelTransaction();
            return false;
        }
    }

    /**
     * 数据删除 条件 field = fieldValue
     *
     * @param bean
     * @param field
     * @param fieldValue
     */
    @Override
    public void tDelete(RealmObject bean, String field, String fieldValue) {
        RealmObject search = mRealm.where(bean.getClass()).equalTo(field, fieldValue).findFirst();
        if (search != null) {
            mRealm.beginTransaction();
            search.deleteFromRealm();
            mRealm.commitTransaction();
        }
    }

    @Override
    public void tDelete(RealmObject bean, String field, int fieldValue) {
        RealmObject search = mRealm.where(bean.getClass()).equalTo(field, fieldValue).findFirst();
        if (search != null) {
            mRealm.beginTransaction();
            search.deleteFromRealm();
            mRealm.commitTransaction();
        }
    }

    /**
     * 删除所有数据
     *
     * @param clazz
     */
    @Override
    public void tDeleteAll(Class<? extends RealmObject> clazz) {
        mRealm.beginTransaction();
        mRealm.delete(clazz);
        mRealm.commitTransaction();
    }

    /**
     * 清空数据库
     *
     * @return
     */
    @Override
    public boolean clearDatabase() {
        try {
            mRealm.beginTransaction();
            mRealm.deleteAll();
            mRealm.commitTransaction();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            mRealm.cancelTransaction();
            return false;
        }
    }

    /**
     * 数据查询出第一条
     *
     * @param bean
     * @param field
     * @param fieldValue
     * @return
     */
    @Override
    public RealmObject tQueryFirst(RealmObject bean, String field, String fieldValue) {

        RealmObject results = mRealm.where(bean.getClass()).equalTo(field, fieldValue).findFirst();
        if (results != null) {
            return mRealm.copyFromRealm(results);
        } else {
            return results;
        }

    }

    @Override
    public RealmObject tQueryFirst(RealmObject bean, String field, int fieldValue) {

        RealmObject results = mRealm.where(bean.getClass()).equalTo(field, fieldValue).findFirst();

        if (results != null) {
            return mRealm.copyFromRealm(results);
        } else {
            return results;
        }
    }

    /**
     * 数据查询出多条
     *
     * @param clazz
     * @param field
     * @param fieldValue
     * @return
     */
    @Override
    public List tQueryList(Class<? extends RealmObject> clazz, String field, String fieldValue) {
        RealmResults results = null;
        if (TextUtils.isEmpty(field)) {
            results = mRealm.where(clazz).findAll();
        } else {
            results = mRealm.where(clazz).equalTo(field, fieldValue).findAll();
        }
        if (results != null) {
            return mRealm.copyFromRealm(results);
        }
        return results;
    }

    @Override
    public List tQueryList(Class<? extends RealmObject> clazz, String field, int fieldValue) {
        RealmResults results = null;
        if (TextUtils.isEmpty(field)) {
            results = mRealm.where(clazz).findAll();
        } else {
            results = mRealm.where(clazz).equalTo(field, fieldValue).findAll();
        }
        if (results != null) {
            return mRealm.copyFromRealm(results);
        }
        return results;
    }

    /**
     * 数据查询出多条 并排序
     *
     * @param clazz
     * @param sortField
     * @param isDesc
     * @return
     */
    @Override
    public List<RealmObject> tQueryListSort(Class<? extends RealmObject> clazz, String sortField, boolean isDesc) {
        RealmResults results = null;
        if (TextUtils.isEmpty(sortField)) {
            results = mRealm.where(clazz).findAll();
        } else {
            if (isDesc) {
                results = mRealm.where(clazz).findAll().sort(sortField, Sort.DESCENDING);
            } else {
                results = mRealm.where(clazz).findAll().sort(sortField, Sort.ASCENDING);
            }

        }
        return results;
    }

    @Override
    public List<RealmObject> tMultiQuery(Class<? extends RealmObject> clazz, String field, List<Integer> list) {
        RealmQuery<? extends RealmObject> query = mRealm.where(clazz);
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                query.equalTo(field, list.get(i));
            } else {
                query.or().equalTo(field, list.get(i));
            }
        }
        return (List<RealmObject>) query.findAll();
    }

}
