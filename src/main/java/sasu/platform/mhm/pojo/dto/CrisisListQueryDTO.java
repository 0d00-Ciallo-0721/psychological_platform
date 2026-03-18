package sasu.platform.mhm.pojo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

/**
 * 危机列表分页查询参数
 */
@Data
public class CrisisListQueryDTO {
    /** 学院（模糊查询） */
    private String college;

    /** 危机等级（red/orange/yellow/blue/green） */
    private String crisis_level;

    /** 处理状态（processing/closed） */
    private String report_status;

}
