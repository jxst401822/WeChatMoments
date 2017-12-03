package com.john.wechatmoments.model.db;


import java.util.List;

import io.realm.RealmObject;


public interface DBHelper {

    /**
     * 数据插入
     *
     * @param bean
     */
    void tInsert(RealmObject bean);

    /**
     * 批量保存插入数据
     *
     * @param list
     */
    boolean tInsert(List<? extends RealmObject> list);

    /**
     * 数据更新 如果操作的对象没有PrimaryKey, 会报错）
     *
     * @param bean
     */
    void tUpdate(RealmObject bean);

    /**
     * 批量添加或者修改
     *
     * @param list
     * @return
     */
    boolean tInsertOrUpdateBatch(List<? extends RealmObject> list);


    /**
     * 数据删除 条件 field = fieldvalue
     *
     * @param bean
     * @param field
     * @param fieldValue
     */
    void tDelete(RealmObject bean, String field, String fieldValue);

    void tDelete(RealmObject bean, String field, int fieldValue);

    /**
     * 删除当前表中所有数据
     *
     * @param clazz
     */
    void tDeleteAll(Class<? extends RealmObject> clazz);

    /**
     * 清空数据库
     *
     * @return
     */
    public boolean clearDatabase();

    /**
     * 数据查询出第一条
     *
     * @param bean
     * @param field
     * @param fieldValue
     * @return
     */
    RealmObject tQueryFirst(RealmObject bean, String field, String fieldValue);

    RealmObject tQueryFirst(RealmObject bean, String field, int fieldValue);

    /**
     * 数据查询出多条
     *
     * @param clazz
     * @param field
     * @param fieldValue
     * @return
     */
    List<RealmObject> tQueryList(Class<? extends RealmObject> clazz, String field, String fieldValue);

    List<RealmObject> tQueryList(Class<? extends RealmObject> clazz, String field, int fieldValue);

    /**
     * 数据查询出多条 并排序
     *
     * @param clazz
     * @param sortField
     * @param isDesc
     * @return
     */
    List<RealmObject> tQueryListSort(Class<? extends RealmObject> clazz, String sortField, boolean isDesc);

    List<RealmObject> tMultiQuery(Class<? extends RealmObject> clazz, String field, List<Integer> list);
}
