package sasu.platform.mhm.service;

import org.springframework.stereotype.Service;
import sasu.platform.mhm.pojo.common.PageResult;
import sasu.platform.mhm.pojo.dto.AppointmentDTO;
import sasu.platform.mhm.pojo.dto.ConsultationScheduleDTO;
import sasu.platform.mhm.pojo.dto.PageQueryDTO;
import sasu.platform.mhm.pojo.vo.CounselorDetailVO;

import java.util.Map;

@Service
public interface ConsulateService {
    boolean setConsultationTime(ConsultationScheduleDTO consultationScheduleDTO);

    Map<Boolean, String> makeAppointment(AppointmentDTO appointmentDTO);

    PageResult getScheduleByParams(PageQueryDTO pageQueryDTO);

    PageResult counselorList(PageQueryDTO pageQueryDTO);

    CounselorDetailVO getCounselorDetail(String counselorId);
}
