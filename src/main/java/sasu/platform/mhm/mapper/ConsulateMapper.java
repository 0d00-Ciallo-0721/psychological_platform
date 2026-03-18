package sasu.platform.mhm.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.entity.Appointment;
import sasu.platform.mhm.pojo.entity.ConsultationSchedule;
import sasu.platform.mhm.pojo.entity.CounselorSchedule;
import sasu.platform.mhm.pojo.vo.AppointmentDetailVO;
import sasu.platform.mhm.pojo.vo.CounselorDetailVO;
import sasu.platform.mhm.pojo.vo.CounselorVO;

import java.util.List;

@Mapper
public interface ConsulateMapper {
    boolean insertConsultationSchedule(ConsultationSchedule consultationSchedule);

    boolean insertAppointment(Appointment appointment);

    ConsultationSchedule getScheduleInfoByScheduleId(String scheduleId);

    Page<ConsultationSchedule> getScheduleByParams(PageQueryDTO pageQueryDTO);

    Page<CounselorVO> counselorList(PageQueryDTO pageQueryDTO);

    CounselorDetailVO getCounselorDetail(String counselorId);

    ConsultationSchedule getScheduleByCounselorId(String counselorId);

    // 预约相关方法
    AppointmentDetailVO getAppointmentById(String appointmentId);

    int updateAppointmentStatus(String appointmentId, String status, String cancellationReason, String cancelledBy);

    Page<AppointmentDetailVO> listAppointments(PageQueryDTO query);

    // 排班相关方法
    int deleteSchedule(String scheduleId);

    int insertSingleSchedule(CounselorSchedule schedule);
}
