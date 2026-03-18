package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.ParentBindChildDTO;
import sasu.platform.mhm.pojo.vo.ChildVO;
import sasu.platform.mhm.pojo.vo.CounselorDetailVO;

import java.util.List;

/**
 * 家长服务接口
 */
public interface ParentService {

    /**
     * 查询子女列表
     */
    List<ChildVO> getChildrenList(String parentId);

    /**
     * 查询子女详情
     */
    ChildVO getChildDetail(String studentId);

    /**
     * 绑定子女
     */
    void bindChild(ParentBindChildDTO dto, String parentId);

    /**
     * 解绑子女
     */
    void unbindChild(String studentId, String parentId);

    /**
     * 获取子女的辅导员信息
     */
    List<CounselorDetailVO> getCounselors(String studentId);

    /**
     * 查询子女测评记录
     */
    PageResult getChildAssessments(String studentId, Integer page, Integer pageSize);

    /**
     * 查询子女预约记录
     */
    PageResult getChildAppointments(String studentId, Integer page, Integer pageSize);

    /**
     * 查询子女活动记录
     */
    PageResult getChildActivities(String studentId, Integer page, Integer pageSize);
}

