package sasu.platform.mhm.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * 提交测评 DTO
 */
@Data
public class AssessmentSubmitDTO {

    private String recordId;

    private List<AnswerItem> answers;

    @Data
    public static class AnswerItem {
        private String questionId;
        private Object answer;
    }
}

