package sasu.platform.mhm.service;

import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.RecordCreateDTO;
import sasu.platform.mhm.pojo.dto.RecordListQueryDTO;
import sasu.platform.mhm.pojo.dto.RecordUpdateDTO;
import sasu.platform.mhm.pojo.vo.RecordVO;

/**
 * 咨询记录服务接口
 */
public interface RecordService {

    /**
     * 查询咨询记录列表
     */
    PageResult list(RecordListQueryDTO query);

    /**
     * 查询咨询记录详情
     */
    RecordVO detail(String recordId);

    /**
     * 根据预约ID查询记录
     */
    RecordVO getByAppointmentId(String appointmentId);

    /**
     * 创建咨询记录
     */
    RecordVO create(RecordCreateDTO dto);

    /**
     * 更新咨询记录
     */
    RecordVO update(RecordUpdateDTO dto);

    /**
     * 自动保存咨询记录（草稿）
     */
    RecordVO autosave(String recordId, RecordUpdateDTO dto);
}

