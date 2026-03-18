package sasu.platform.mhm.service.impl;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sasu.platform.mhm.mapper.ConsulateMapper;
import sasu.platform.mhm.mapper.RecordMapper;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.RecordCreateDTO;
import sasu.platform.mhm.pojo.dto.RecordListQueryDTO;
import sasu.platform.mhm.pojo.dto.RecordUpdateDTO;
import sasu.platform.mhm.pojo.entity.ConsultationRecord;
import sasu.platform.mhm.pojo.entity.UserDetailsImpl;
import sasu.platform.mhm.pojo.vo.AppointmentDetailVO;
import sasu.platform.mhm.pojo.vo.RecordVO;
import sasu.platform.mhm.service.RecordService;
import sasu.platform.mhm.util.OssUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 咨询记录服务实现
 */
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private ConsulateMapper consulateMapper;

    @Autowired
    private OssUtil ossUtil;

    private final ObjectMapper objectMapper = new ObjectMapper();

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
    public PageResult list(RecordListQueryDTO query) {
        if (query == null) {
            query = new RecordListQueryDTO();
        }
        if (query.getPage() == null || query.getPage() <= 0) {
            query.setPage(1);
        }
        if (query.getPageSize() == null || query.getPageSize() <= 0) {
            query.setPageSize(10);
        }

        String userId = currentUserId();
        // 如果没有指定咨询师ID或学生ID，根据当前用户角色设置
        if (!StringUtils.hasText(query.getCounselorId()) && !StringUtils.hasText(query.getStudentId())) {
            // 这里可以根据用户角色判断，暂时先不设置
        }

        PageHelper.startPage(query.getPage(), query.getPageSize());
        Page<RecordVO> page = recordMapper.selectRecordList(query);
        
        // 处理附件JSON字符串转换为List
        if (page.getResult() != null) {
            for (RecordVO vo : page.getResult()) {
                // 附件处理在查询时已经通过JSON解析处理
            }
        }
        
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public RecordVO detail(String recordId) {
        if (!StringUtils.hasText(recordId)) {
            throw new IllegalArgumentException("recordId不能为空");
        }
        RecordVO record = recordMapper.selectRecordById(recordId);
        if (record == null) {
            throw new IllegalArgumentException("咨询记录不存在");
        }
        return record;
    }

    @Override
    public RecordVO getByAppointmentId(String appointmentId) {
        if (!StringUtils.hasText(appointmentId)) {
            throw new IllegalArgumentException("appointmentId不能为空");
        }
        RecordVO record = recordMapper.selectRecordByAppointmentId(appointmentId);
        return record;
    }

    @Override
    public RecordVO create(RecordCreateDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("记录内容不能为空");
        }
        if (!StringUtils.hasText(dto.getAppointmentId())) {
            throw new IllegalArgumentException("预约ID不能为空");
        }
        if (!StringUtils.hasText(dto.getSessionContent())) {
            throw new IllegalArgumentException("咨询内容不能为空");
        }

        String userId = currentUserId();
        if (!StringUtils.hasText(userId)) {
            throw new IllegalArgumentException("未登录");
        }

        // 上传手写记录图片
        String handwrittenRecordUrl = null;
        if (dto.getHandwrittenRecordFile() != null && !dto.getHandwrittenRecordFile().isEmpty()) {
            try {
                handwrittenRecordUrl = ossUtil.uploadFile(dto.getHandwrittenRecordFile(), "record");
            } catch (Exception e) {
                throw new RuntimeException("上传手写记录图片失败: " + e.getMessage());
            }
        }

        // 处理附件JSON
        String attachmentsJson = null;
        if (dto.getAttachments() != null && !dto.getAttachments().isEmpty()) {
            try {
                attachmentsJson = objectMapper.writeValueAsString(dto.getAttachments());
            } catch (Exception e) {
                throw new RuntimeException("处理附件失败: " + e.getMessage());
            }
        }

        // 从预约表查询学生ID和咨询师ID
        AppointmentDetailVO appointment = consulateMapper.getAppointmentById(dto.getAppointmentId());
        if (appointment == null) {
            throw new IllegalArgumentException("预约不存在");
        }

        ConsultationRecord record = new ConsultationRecord();
        record.setRecordId(IdUtil.randomUUID());
        record.setAppointmentId(dto.getAppointmentId());
        record.setCounselorId(appointment.getCounselorId() != null ? appointment.getCounselorId() : userId);
        record.setStudentId(appointment.getStudentId());
        record.setRecordDate(StringUtils.hasText(dto.getRecordDate()) 
            ? LocalDate.parse(dto.getRecordDate(), DateTimeFormatter.ISO_DATE)
            : LocalDate.now());
        record.setSessionNumber(dto.getSessionNumber() != null ? dto.getSessionNumber() : 1);
        record.setPresentingProblem(dto.getPresentingProblem());
        record.setSessionContent(dto.getSessionContent());
        record.setCounselorObservations(dto.getCounselorObservations());
        record.setInterventionMethods(dto.getInterventionMethods());
        record.setHomeworkAssignment(dto.getHomeworkAssignment());
        record.setNextSessionPlan(dto.getNextSessionPlan());
        record.setCounselorNotes(dto.getCounselorNotes());
        record.setHandwrittenRecordUrl(handwrittenRecordUrl);
        record.setConfidentialLevel(StringUtils.hasText(dto.getConfidentialLevel()) 
            ? dto.getConfidentialLevel() : "NORMAL");
        record.setRecordStatus(StringUtils.hasText(dto.getRecordStatus()) 
            ? dto.getRecordStatus() : "DRAFT");
        record.setAttachments(attachmentsJson);

        int result = recordMapper.insertRecord(record);
        if (result <= 0) {
            throw new RuntimeException("创建咨询记录失败");
        }

        return recordMapper.selectRecordById(record.getRecordId());
    }

    @Override
    public RecordVO update(RecordUpdateDTO dto) {
        if (dto == null || !StringUtils.hasText(dto.getRecordId())) {
            throw new IllegalArgumentException("记录ID不能为空");
        }

        ConsultationRecord existing = new ConsultationRecord();
        existing.setRecordId(dto.getRecordId());
        existing.setSessionContent(dto.getSessionContent());
        existing.setCounselorObservations(dto.getCounselorObservations());
        existing.setInterventionMethods(dto.getInterventionMethods());
        existing.setHomeworkAssignment(dto.getHomeworkAssignment());
        existing.setNextSessionPlan(dto.getNextSessionPlan());
        existing.setCounselorNotes(dto.getCounselorNotes());
        existing.setPresentingProblem(dto.getPresentingProblem());
        existing.setConfidentialLevel(dto.getConfidentialLevel());
        existing.setRecordStatus(dto.getRecordStatus());

        // 处理附件JSON
        if (dto.getAttachments() != null) {
            try {
                existing.setAttachments(objectMapper.writeValueAsString(dto.getAttachments()));
            } catch (Exception e) {
                throw new RuntimeException("处理附件失败: " + e.getMessage());
            }
        }

        int result = recordMapper.updateRecord(existing);
        if (result <= 0) {
            throw new RuntimeException("更新咨询记录失败");
        }

        return recordMapper.selectRecordById(dto.getRecordId());
    }

    @Override
    public RecordVO autosave(String recordId, RecordUpdateDTO dto) {
        if (!StringUtils.hasText(recordId)) {
            throw new IllegalArgumentException("记录ID不能为空");
        }

        ConsultationRecord record = new ConsultationRecord();
        record.setRecordId(recordId);
        record.setSessionContent(dto.getSessionContent());
        record.setCounselorObservations(dto.getCounselorObservations());
        record.setInterventionMethods(dto.getInterventionMethods());
        record.setHomeworkAssignment(dto.getHomeworkAssignment());
        record.setNextSessionPlan(dto.getNextSessionPlan());
        record.setCounselorNotes(dto.getCounselorNotes());
        record.setPresentingProblem(dto.getPresentingProblem());
        record.setConfidentialLevel(dto.getConfidentialLevel());

        // 处理附件JSON
        if (dto.getAttachments() != null) {
            try {
                record.setAttachments(objectMapper.writeValueAsString(dto.getAttachments()));
            } catch (Exception e) {
                throw new RuntimeException("处理附件失败: " + e.getMessage());
            }
        }

        int result = recordMapper.autosaveRecord(record);
        if (result <= 0) {
            throw new RuntimeException("自动保存失败");
        }

        return recordMapper.selectRecordById(recordId);
    }
}

