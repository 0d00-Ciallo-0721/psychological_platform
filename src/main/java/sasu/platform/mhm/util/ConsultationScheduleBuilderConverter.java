package sasu.platform.mhm.util;

import org.springframework.stereotype.Component;
import sasu.platform.mhm.pojo.dto.ConsultationScheduleDTO;
import sasu.platform.mhm.pojo.entity.ConsultationSchedule;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Builder模式的转换器
 */
@Component
public class ConsultationScheduleBuilderConverter {

    // 默认配置
    private String defaultSlotDuration = "30分钟";
    private int defaultAvailableSlots = 1;
    private String userId;

    // Builder模式
    public static Builder builder() {
        return new Builder();
    }

    /**
     * 将咨询安排DTO转换为实体对象
     *
     * @param dto 咨询安排数据传输对象，包含咨询安排的相关信息
     * @return 转换后的咨询安排实体对象，如果输入为null则返回null
     */
    public ConsultationSchedule toEntity(ConsultationScheduleDTO dto) {
        // 空值检查，直接返回null
        if (dto == null) {
            return null;
        }

        // 创建实体对象并设置用户ID
        ConsultationSchedule entity = new ConsultationSchedule();
        entity.setUserId(userId);

        // 转换并设置咨询安排日期
        if (dto.getConsultationScheduleDates() != null) {
            List<ConsultationSchedule.ConsultationScheduleDate> entityDates =
                    dto.getConsultationScheduleDates().stream()
                            .map(this::buildEntityDate)
                            .collect(Collectors.toList());
            entity.setConsultationScheduleDates(entityDates);
        }

        return entity;
    }

    /**
     * 转换Entity到DTO
     */
    public ConsultationScheduleDTO toDto(ConsultationSchedule entity) {
        if (entity == null) {
            return null;
        }

        ConsultationScheduleDTO dto = new ConsultationScheduleDTO();

        if (entity.getConsultationScheduleDates() != null) {
            List<ConsultationScheduleDTO.ConsultationScheduleDate> dtoDates =
                    entity.getConsultationScheduleDates().stream()
                            .map(this::buildDtoDate)
                            .collect(Collectors.toList());
            dto.setConsultationScheduleDates(dtoDates);
        }

        return dto;
    }

    // ==================== 私有构建方法 ====================

    private ConsultationSchedule.ConsultationScheduleDate buildEntityDate(
            ConsultationScheduleDTO.ConsultationScheduleDate dtoDate) {
        ConsultationSchedule.ConsultationScheduleDate entityDate =
                new ConsultationSchedule.ConsultationScheduleDate();
        entityDate.setSchedule_date(dtoDate.getSchedule_date());

        if (dtoDate.getConsultationTime() != null) {
            List<ConsultationSchedule.ConsultationTime> entityTimes =
                    dtoDate.getConsultationTime().stream()
                            .map(this::buildEntityTime)
                            .collect(Collectors.toList());
            entityDate.setConsultationTime(entityTimes);
        }

        return entityDate;
    }

    private ConsultationSchedule.ConsultationTime buildEntityTime(
            ConsultationScheduleDTO.ConsultationTime dtoTime) {
        ConsultationSchedule.ConsultationTime entityTime = new ConsultationSchedule.ConsultationTime();

        // 设置共同字段
        entityTime.setStartTime(dtoTime.getStartTime());
        entityTime.setEndTime(dtoTime.getEndTime());
        entityTime.setStatus(dtoTime.getStatus());
        entityTime.setSchedule_type(dtoTime.getSchedule_type());
        entityTime.setReservationLocation(dtoTime.getReservationLocation());
        String formattedDifference = getFormattedDifference(dtoTime.getStartTime(), dtoTime.getEndTime());
        // 设置Entity特有字段
        entityTime.setSlot_duration(formattedDifference);
        entityTime.setAvailable_slots(defaultAvailableSlots);

        return entityTime;
    }

    private ConsultationScheduleDTO.ConsultationScheduleDate buildDtoDate(
            ConsultationSchedule.ConsultationScheduleDate entityDate) {
        ConsultationScheduleDTO.ConsultationScheduleDate dtoDate =
                new ConsultationScheduleDTO.ConsultationScheduleDate();
        dtoDate.setSchedule_date(entityDate.getSchedule_date());

        if (entityDate.getConsultationTime() != null) {
            List<ConsultationScheduleDTO.ConsultationTime> dtoTimes =
                    entityDate.getConsultationTime().stream()
                            .map(this::buildDtoTime)
                            .collect(Collectors.toList());
            dtoDate.setConsultationTime(dtoTimes);
        }

        return dtoDate;
    }

    /**
     * 将实体时间对象转换为DTO时间对象
     *
     * @param entityTime 实体时间对象，包含完整的时间信息
     * @return 转换后的DTO时间对象，只包含必要的字段
     */
    private ConsultationScheduleDTO.ConsultationTime buildDtoTime(
            ConsultationSchedule.ConsultationTime entityTime) {
        ConsultationScheduleDTO.ConsultationTime dtoTime = new ConsultationScheduleDTO.ConsultationTime();

        // 只设置DTO需要的字段
        dtoTime.setStartTime(entityTime.getStartTime());
        dtoTime.setEndTime(entityTime.getEndTime());
        dtoTime.setStatus(entityTime.getStatus());
        dtoTime.setSchedule_type(entityTime.getSchedule_type());

        return dtoTime;
    }
    /**
     * 计算两个Time之间的分钟差
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 分钟差（如果结束时间早于开始时间，返回负数）
     */
    public static long getMinutesDifference(Time startTime, Time endTime) {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("时间参数不能为空");
        }

        // 转换为LocalTime
        LocalTime start = startTime.toLocalTime();
        LocalTime end = endTime.toLocalTime();

        // 计算分钟差
        return Duration.between(start, end).toMinutes();
    }
    /**
     * 获取格式化的时间差（如：2小时30分钟）
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 格式化字符串
     */
    public static String getFormattedDifference(Time startTime, Time endTime) {
        long minutes = getMinutesDifference(startTime, endTime);

        if (minutes == 0) {
            return "0分钟";
        }

        boolean isNegative = minutes < 0;
        minutes = Math.abs(minutes);

        long hours = minutes / 60;
        long remainingMinutes = minutes % 60;

        StringBuilder result = new StringBuilder();
        if (isNegative) {
            result.append("-");
        }

        if (hours > 0) {
            result.append(hours).append("小时");
        }
        if (remainingMinutes > 0 || hours == 0) {
            if (hours > 0) {
                result.append(" ");
            }
            result.append(remainingMinutes).append("分钟");
        }

        return result.toString();
    }

    // ==================== Builder类 ====================

    public static class Builder {
        private ConsultationScheduleBuilderConverter converter = new ConsultationScheduleBuilderConverter();

        public Builder defaultSlotDuration(String slotDuration) {
            converter.defaultSlotDuration = slotDuration;
            return this;
        }

        public Builder defaultAvailableSlots(int availableSlots) {
            converter.defaultAvailableSlots = availableSlots;
            return this;
        }

        public Builder userId(String userId) {
            converter.userId = userId;
            return this;
        }

        public ConsultationScheduleBuilderConverter build() {
            return converter;
        }
    }
}