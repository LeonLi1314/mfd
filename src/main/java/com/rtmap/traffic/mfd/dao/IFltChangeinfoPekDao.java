package com.rtmap.traffic.mfd.dao;

import java.util.List;

import com.rtmap.traffic.mfd.domain.entity.FltChangeinfoPek;

/**
 * 首都机场航班动态变更信息数据访问层接口
 * @author liqingshan
 *
 */
public interface IFltChangeinfoPekDao {
	/**
	 * 新增
	 * @param record 变更记录
	 * @return 受影响的行数
	 */
    int insert(FltChangeinfoPek record);
    
    /**
     * 更新已执行标记
     * @param recId 主键
     * @return 受影响的行数
     */
    int updateExecuted(Integer recId);

    /**
     * 根据主键查找
     * @param recId 主键
     * @return 变更记录
     */
    FltChangeinfoPek selectByRecId(Integer recId);
    
    /**
     * 获取未执行的记录
     * @param limit 前几条（小于等于0时不限制）
     * @return 未执行的记录集合
     */
    List<FltChangeinfoPek> selectNotExecuted(int limit);
}