package sasu.platform.mhm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sasu.platform.mhm.pojo.entity.ParentStudentRelation;
import sasu.platform.mhm.pojo.vo.ChildVO;
import sasu.platform.mhm.pojo.vo.CounselorDetailVO;

import java.util.List;

/**
 * 家长Mapper
 */
@Mapper
public interface ParentMapper {

    /**
     * 查询子女列表
     */
    List<ChildVO> selectChildrenList(@Param("parentId") String parentId);

    /**
     * 查询子女详情
     */
    ChildVO selectChildDetail(@Param("studentId") String studentId);

    /**
     * 插入家长-子女关联
     */
    int insertParentStudentRelation(ParentStudentRelation relation);

    /**
     * 删除家长-子女关联
     */
    int deleteParentStudentRelation(@Param("parentId") String parentId, @Param("studentId") String studentId);

    /**
     * 查询关联是否存在
     */
    ParentStudentRelation selectRelation(@Param("parentId") String parentId, @Param("studentId") String studentId);

    /**
     * 根据学生ID查询辅导员
     */
    List<CounselorDetailVO> selectCounselorsByStudentId(@Param("studentId") String studentId);
}

