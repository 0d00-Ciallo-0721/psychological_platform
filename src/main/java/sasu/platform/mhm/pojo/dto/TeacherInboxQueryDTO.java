package sasu.platform.mhm.pojo.dto;

import lombok.Data;

/**
 * 教师/辅导员收件箱查询 DTO
 */
@Data
public class TeacherInboxQueryDTO extends PageQueryDTO {

    /**
     * 是否只看未读
     */
    private Boolean unreadOnly;
}


