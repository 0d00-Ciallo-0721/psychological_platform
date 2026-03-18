package sasu.platform.mhm.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sasu.platform.mhm.pojo.dto.RecordListQueryDTO;
import sasu.platform.mhm.pojo.entity.ConsultationRecord;
import sasu.platform.mhm.pojo.vo.RecordVO;

/**
 * 咨询记录Mapper
 */
@Mapper
public interface RecordMapper {

    /**
     * 查询咨询记录列表
     */
    Page<RecordVO> selectRecordList(@Param("query") RecordListQueryDTO query);

    /**
     * 查询咨询记录详情
     */
    RecordVO selectRecordById(@Param("recordId") String recordId);

    /**
     * 根据预约ID查询记录
     */
    RecordVO selectRecordByAppointmentId(@Param("appointmentId") String appointmentId);

    /**
     * 插入咨询记录
     */
    int insertRecord(ConsultationRecord record);

    /**
     * 更新咨询记录
     */
    int updateRecord(ConsultationRecord record);

    /**
     * 自动保存（更新草稿）
     */
    int autosaveRecord(ConsultationRecord record);
}

