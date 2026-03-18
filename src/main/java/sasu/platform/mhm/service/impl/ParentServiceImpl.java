package sasu.platform.mhm.service.impl;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sasu.platform.mhm.mapper.ParentMapper;
import sasu.platform.mhm.mapper.AssessmentMapper;
import sasu.platform.mhm.mapper.ConsulateMapper;
import sasu.platform.mhm.mapper.ActivityMapper;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.ParentBindChildDTO;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.entity.ParentStudentRelation;
import sasu.platform.mhm.pojo.entity.UserDetailsImpl;
import sasu.platform.mhm.pojo.vo.ChildVO;
import sasu.platform.mhm.pojo.vo.CounselorDetailVO;
import sasu.platform.mhm.service.ParentService;

import java.util.List;

/**
 * 家长服务实现
 */
@Service
public class ParentServiceImpl implements ParentService {

    @Autowired
    private ParentMapper parentMapper;

    @Autowired
    private ConsulateMapper consulateMapper;

    /**
     * 获取当前用户ID
     */
    private String currentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl userDetails) {
            return userDetails.getUser().getUserId();
        }
        return null;
    }

    @Override
    public List<ChildVO> getChildrenList(String parentId) {
        if (!StringUtils.hasText(parentId)) {
            parentId = currentUserId();
        }
        if (!StringUtils.hasText(parentId)) {
            throw new IllegalArgumentException("未登录");
        }
        return parentMapper.selectChildrenList(parentId);
    }

    @Override
    public ChildVO getChildDetail(String studentId) {
        if (!StringUtils.hasText(studentId)) {
            throw new IllegalArgumentException("学生ID不能为空");
        }
        return parentMapper.selectChildDetail(studentId);
    }

    @Override
    public void bindChild(ParentBindChildDTO dto, String parentId) {
        if (!StringUtils.hasText(parentId)) {
            parentId = currentUserId();
        }
        if (!StringUtils.hasText(parentId)) {
            throw new IllegalArgumentException("未登录");
        }
        if (!StringUtils.hasText(dto.getStudentId())) {
            throw new IllegalArgumentException("学生ID不能为空");
        }

        // 检查是否已绑定
        ParentStudentRelation existing = parentMapper.selectRelation(parentId, dto.getStudentId());
        if (existing != null) {
            throw new IllegalArgumentException("已绑定该学生");
        }

        // 简化验证：如果传入了学生姓名或证件后四位，可以根据需要补充校验逻辑

        ParentStudentRelation relation = new ParentStudentRelation();
        relation.setRelationId(IdUtil.randomUUID());
        relation.setParentId(parentId);
        relation.setStudentId(dto.getStudentId());
        relation.setRelationType(StringUtils.hasText(dto.getRelationType()) ? dto.getRelationType() : "PARENT");
        relation.setVerificationStatus(1); // 简化处理，直接设为已验证

        int result = parentMapper.insertParentStudentRelation(relation);
        if (result <= 0) {
            throw new RuntimeException("绑定失败");
        }
    }

    @Override
    public void unbindChild(String studentId, String parentId) {
        if (!StringUtils.hasText(parentId)) {
            parentId = currentUserId();
        }
        if (!StringUtils.hasText(parentId)) {
            throw new IllegalArgumentException("未登录");
        }
        if (!StringUtils.hasText(studentId)) {
            throw new IllegalArgumentException("学生ID不能为空");
        }

        int result = parentMapper.deleteParentStudentRelation(parentId, studentId);
        if (result <= 0) {
            throw new RuntimeException("解绑失败");
        }
    }

    @Override
    public List<CounselorDetailVO> getCounselors(String studentId) {
        if (!StringUtils.hasText(studentId)) {
            throw new IllegalArgumentException("学生ID不能为空");
        }
        return parentMapper.selectCounselorsByStudentId(studentId);
    }

    @Override
    public PageResult getChildAssessments(String studentId, Integer page, Integer pageSize) {
        if (!StringUtils.hasText(studentId)) {
            throw new IllegalArgumentException("学生ID不能为空");
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }

        PageQueryDTO query = new PageQueryDTO();
        query.setPage(page);
        query.setPageSize(pageSize);
        // 这里需要调用AssessmentService，暂时简化处理
        return new PageResult(0L, List.of());
    }

    @Override
    public PageResult getChildAppointments(String studentId, Integer page, Integer pageSize) {
        if (!StringUtils.hasText(studentId)) {
            throw new IllegalArgumentException("学生ID不能为空");
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }

        PageQueryDTO query = new PageQueryDTO();
        query.setPage(page);
        query.setPageSize(pageSize);
        query.setUserId(studentId);
        PageHelper.startPage(page, pageSize);
        Page<sasu.platform.mhm.pojo.vo.AppointmentDetailVO> pageResult = consulateMapper.listAppointments(query);
        return new PageResult(pageResult.getTotal(), pageResult.getResult());
    }

    @Override
    public PageResult getChildActivities(String studentId, Integer page, Integer pageSize) {
        if (!StringUtils.hasText(studentId)) {
            throw new IllegalArgumentException("学生ID不能为空");
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10;
        }

        // 这里需要调用ActivityService，暂时简化处理
        return new PageResult(0L, List.of());
    }
}

