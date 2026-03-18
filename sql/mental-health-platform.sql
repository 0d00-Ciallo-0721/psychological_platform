create table cms_content
(
    id           bigint auto_increment comment '主键ID'
        primary key,
    type         tinyint                              not null comment '栏目类型：1-活动风采 2-通知公告 3-朋辈互助 4-心理百科 5-心理美文',
    title        varchar(200)                         not null comment '标题',
    summary      varchar(500)                         null comment '摘要/简介',
    content      longtext                             null comment '正文内容',
    cover_url    varchar(255)                         null comment '封面图URL',
    link_url     varchar(500)                         null comment '外链（心理百科学习平台链接等）',
    author       varchar(50)                          null comment '作者/发布单位',
    source_org   varchar(36)                          null comment '来源组织（如二级学院）',
    is_top       tinyint(1) default 0                 null comment '是否置顶：0-否 1-是（通知公告常用）',
    status       tinyint(1) default 0                 null comment '状态：0-草稿 1-公布 2-禁用',
    sort_order   int        default 0                 null comment '排序值，越大越靠前',
    publish_time datetime                             null comment '发布时间',
    create_time  datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    update_time  datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    creator_id   char(36)                             null comment '创建人ID'
)
    comment '心理科普与朋辈互助-内容表' charset = utf8mb4;

create index idx_is_top
    on cms_content (is_top);

create index idx_publish_time
    on cms_content (publish_time);

create index idx_type_status
    on cms_content (type, status);

create table counselor_info
(
    counselor_id          char(36)                             not null comment '咨询师ID'
        primary key,
    employee_number       varchar(50)                          null comment '工号',
    title                 varchar(50)                          null comment '职称',
    qualification_level   varchar(50)                          null comment '资质等级',
    certificate_number    varchar(100)                         null comment '证书编号',
    certificate_image     varchar(255)                         null comment '证书图片',
    education_background  varchar(100)                         null comment '教育背景',
    graduation_school     varchar(100)                         null comment '毕业院校',
    major_field           varchar(100)                         null comment '专业领域',
    counseling_approach   varchar(100)                         null comment '咨询流派',
    expertise_areas       text                                 null comment '擅长领域',
    introduction          text                                 null comment '个人简介',
    work_experience_years int        default 0                 null comment '工作年限',
    is_full_time          tinyint(1) default 1                 null comment '是否专职: 0-兼职, 1-专职',
    counselor_status      tinyint(1) default 1                 not null comment '咨询师状态: 0-停用, 1-正常, 2-休假',
    max_cases_per_week    int        default 20                null comment '每周最大个案数',
    created_at            datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at            datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '咨询师信息扩展表';

create table counselor_schedule
(
    schedule_id          char(36)                              not null comment '排班ID'
        primary key,
    counselor_id         char(36)                              not null comment '咨询师ID',
    schedule_date        int                                   not null comment '排班日期(星期)',
    start_time           time                                  not null comment '开始时间',
    end_time             time                                  not null comment '结束时间',
    slot_duration        varchar(25) default '50'              not null comment '单次咨询时长(分钟)',
    available_slots      int         default 1                 null comment '剩余可预约数',
    schedule_type        varchar(20) default 'REGULAR'         not null comment '排班类型: REGULAR-常规, SPECIAL-特殊, HOLIDAY-节假日',
    status               tinyint(1)  default 1                 not null comment '状态: 0-停用, 1-启用, 2-已满',
    created_by           char(36)                              null comment '创建人',
    created_at           datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at           datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    reservation_location varchar(255)                          not null comment '预约地点（线上，校区）',
    constraint fk_counselor_schedule_counselor
        foreign key (counselor_id) references counselor_info (counselor_id)
            on delete cascade
)
    comment '咨询师排班表';

create table consultation_appointment
(
    appointment_id      char(36)                              not null comment '预约ID'
        primary key,
    student_id          char(36)                              not null comment '学生ID',
    schedule_id         char(36)                              not null comment '排班ID',
    appointment_type    varchar(20) default 'INDIVIDUAL'      not null comment '咨询类型: INDIVIDUAL-个体, GROUP-团体, EMERGENCY-紧急',
    consultation_mode   varchar(20) default 'OFFLINE'         not null comment '咨询方式: OFFLINE-线下, ONLINE-线上, PHONE-电话',
    location            varchar(255)                          null comment '咨询地点',
    appointment_status  varchar(20) default 'PENDING'         not null comment '预约状态: PENDING-待确认, CONFIRMED-已确认, COMPLETED-已完成, CANCELLED-已取消, NO_SHOW-爽约',
    cancellation_reason varchar(255)                          null comment '取消原因',
    cancelled_by        varchar(20)                           null comment '取消人: STUDENT-学生, COUNSELOR-咨询师, SYSTEM-系统',
    is_urgent           tinyint(1)  default 0                 null comment '是否紧急预约: 0-否, 1-是',
    has_visited_before  tinyint(1)  default 0                 null comment '是否曾经来访: 0-否, 1-是',
    visit_count         int         default 0                 null comment '来访次数',
    notes               text                                  null comment '备注',
    created_at          datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at          datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uk_schedule_student
        unique (schedule_id, student_id),
    constraint fk_appointment_schedule
        foreign key (schedule_id) references counselor_schedule (schedule_id)
            on delete cascade
)
    comment '咨询预约表';

create index idx_appointment_status
    on consultation_appointment (appointment_status);

create index idx_student_id
    on consultation_appointment (student_id);

create index idx_counselor_date
    on counselor_schedule (counselor_id, schedule_date);

create index idx_schedule_date
    on counselor_schedule (schedule_date);

create index idx_status
    on counselor_schedule (status);

create table flyway_schema_history
(
    installed_rank int                                 not null
        primary key,
    version        varchar(50)                         null,
    description    varchar(200)                        not null,
    type           varchar(20)                         not null,
    script         varchar(1000)                       not null,
    checksum       int                                 null,
    installed_by   varchar(100)                        not null,
    installed_on   timestamp default CURRENT_TIMESTAMP not null,
    execution_time int                                 not null,
    success        tinyint(1)                          not null
);

create index flyway_schema_history_s_idx
    on flyway_schema_history (success);

create table informed_consent
(
    consent_id              char(36)                             not null comment '同意书ID'
        primary key,
    appointment_id          char(36)                             not null comment '预约ID',
    consent_version         varchar(20)                          not null comment '同意书版本',
    consent_content         text                                 not null comment '同意书内容',
    has_agreed              tinyint(1) default 0                 not null comment '是否同意: 0-否, 1-是',
    agreed_at               datetime                             null comment '同意时间',
    student_signature       text                                 null comment '学生电子签名',
    handwritten_consent_url varchar(255)                         null comment '手写同意书图片URL',
    student_ip              varchar(50)                          null comment '学生IP地址',
    student_user_agent      varchar(255)                         null comment '学生用户代理',
    created_at              datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at              datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uk_appointment_version
        unique (appointment_id, consent_version),
    constraint fk_consent_appointment
        foreign key (appointment_id) references consultation_appointment (appointment_id)
            on delete cascade
)
    comment '知情同意书表';

create table organization
(
    org_id         char(36)                             not null comment '组织ID'
        primary key,
    org_code       varchar(50)                          not null comment '组织代码',
    org_name       varchar(100)                         not null comment '组织名称',
    org_type       varchar(20)                          not null comment '组织类型: school-学校, college-学院, department-系部, class-班级',
    parent_id      char(36)                             null comment '父组织ID',
    level          int        default 1                 null comment '组织层级',
    sort_order     int        default 0                 null comment '排序',
    contact_person varchar(50)                          null comment '联系人',
    contact_phone  varchar(20)                          null comment '联系电话',
    address        varchar(255)                         null comment '地址',
    description    text                                 null comment '描述',
    status         tinyint(1) default 1                 not null comment '状态: 0-禁用, 1-启用',
    created_at     datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at     datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uk_org_code
        unique (org_code)
)
    comment '组织机构表';

create index idx_org_type
    on organization (org_type);

create index idx_parent_id
    on organization (parent_id);

create table pre_assessment
(
    assessment_id         char(36)                           not null comment '评估ID'
        primary key,
    appointment_id        char(36)                           not null comment '预约ID',
    scale_type            varchar(50)                        not null comment '量表类型: PHQ-9, GAD-7, PSS, etc.',
    scale_name            varchar(100)                       not null comment '量表名称',
    total_score           int                                not null comment '总分',
    score_interpretation  varchar(20)                        null comment '分数解释: NORMAL-正常, MILD-轻度, MODERATE-中度, SEVERE-重度',
    assessment_data       json                               not null comment '评估数据(JSON格式)',
    handwritten_scale_url varchar(255)                       null comment '手写量表图片URL',
    completed_at          datetime                           null comment '完成时间',
    created_at            datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    constraint fk_assessment_appointment
        foreign key (appointment_id) references consultation_appointment (appointment_id)
            on delete cascade
)
    comment '前测量表表';

create index idx_appointment_id
    on pre_assessment (appointment_id);

create index idx_scale_type
    on pre_assessment (scale_type);

create table user
(
    user_id         char(36)                             not null comment '用户ID'
        primary key,
    username        varchar(50)                          not null comment '用户名',
    password_hash   varchar(255)                         not null comment '密码哈希',
    email           varchar(100)                         null comment '邮箱',
    phone           varchar(20)                          null comment '手机号',
    real_name       varchar(50)                          not null comment '真实姓名',
    gender          tinyint(1)                           null comment '性别: 0-未知, 1-男, 2-女',
    birth_date      date                                 null comment '出生日期',
    avatar_url      varchar(255)                         null comment '头像URL',
    id_card         varchar(20)                          null comment '身份证号',
    wechat_openid   varchar(100)                         null comment '微信OpenID',
    wechat_unionid  varchar(100)                         null comment '微信UnionID',
    account_status  tinyint(1) default 1                 not null comment '账号状态: 0-禁用, 1-正常, 2-锁定',
    last_login_time datetime                             null comment '最后登录时间',
    created_at      datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at      datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted_at      datetime                             null comment '删除时间',
    constraint uk_email
        unique (email),
    constraint uk_phone
        unique (phone),
    constraint uk_username
        unique (username),
    constraint uk_wechat_openid
        unique (wechat_openid)
)
    comment '用户基础信息表';

create table college_leader_info
(
    leader_id           char(36)                           not null comment '领导ID'
        primary key,
    user_id             char(36)                           not null comment '用户ID',
    employee_number     varchar(50)                        null comment '工号',
    college_id          char(36)                           null comment '所属学院ID',
    leadership_position varchar(50)                        null comment '领导职务',
    management_level    varchar(20)                        null comment '管理级别: 院级, 校级等',
    created_at          datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at          datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uk_user_id
        unique (user_id),
    constraint fk_college_leader_info_user
        foreign key (user_id) references user (user_id)
            on delete cascade
)
    comment '院系领导信息扩展表';

create index idx_college_id
    on college_leader_info (college_id);

create table instructor_info
(
    instructor_id               char(36)                             not null comment '辅导员ID'
        primary key,
    user_id                     char(36)                             not null comment '用户ID',
    employee_number             varchar(50)                          null comment '工号',
    college_id                  char(36)                             null comment '所属学院ID',
    position                    varchar(50)                          null comment '职务',
    is_psychological_instructor tinyint(1) default 0                 null comment '是否心理辅导员: 0-否, 1-是',
    responsible_classes         text                                 null comment '负责班级(JSON数组)',
    responsible_students_count  int        default 0                 null comment '负责学生人数',
    work_phone                  varchar(20)                          null comment '工作电话',
    office_location             varchar(100)                         null comment '办公室位置',
    created_at                  datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at                  datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uk_user_id
        unique (user_id),
    constraint fk_instructor_info_user
        foreign key (user_id) references user (user_id)
            on delete cascade
)
    comment '辅导员信息扩展表';

create index idx_college_id
    on instructor_info (college_id);

create table student_info
(
    student_id       char(36)                             not null comment '学生ID'
        primary key,
    user_id          char(36)                             not null comment '用户ID',
    student_number   varchar(50)                          not null comment '学号',
    school_id        char(36)                             null comment '学校ID',
    college_id       char(36)                             null comment '学院ID',
    major_id         char(36)                             null comment '专业ID',
    class_id         char(36)                             null comment '班级ID',
    grade            varchar(20)                          null comment '年级',
    enrollment_year  year                                 null comment '入学年份',
    education_level  varchar(20)                          null comment '学历层次: 本科, 硕士, 博士等',
    political_status varchar(20)                          null comment '政治面貌',
    native_place     varchar(100)                         null comment '籍贯',
    family_address   varchar(255)                         null comment '家庭地址',
    dormitory        varchar(100)                         null comment '宿舍信息',
    student_status   tinyint(1) default 1                 not null comment '学生状态: 0-休学, 1-在读, 2-毕业, 3-退学',
    is_poor_student  tinyint(1) default 0                 null comment '是否贫困生: 0-否, 1-是',
    is_student_cadre tinyint(1) default 0                 null comment '是否学生干部: 0-否, 1-是',
    cadre_position   varchar(50)                          null comment '干部职务',
    created_at       datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at       datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uk_student_number
        unique (student_number),
    constraint uk_user_id
        unique (user_id),
    constraint fk_student_info_user
        foreign key (user_id) references user (user_id)
            on delete cascade
)
    comment '学生信息扩展表';

create table consultation_feedback
(
    feedback_id              char(36)                             not null comment '反馈ID'
        primary key,
    appointment_id           char(36)                             not null comment '预约ID',
    student_id               char(36)                             not null comment '学生ID',
    counselor_id             char(36)                             not null comment '咨询师ID',
    satisfaction_score       int                                  null comment '满意度评分(1-5)',
    helpfulness_score        int                                  null comment '帮助程度评分(1-5)',
    counselor_attitude_score int                                  null comment '咨询师态度评分(1-5)',
    environment_score        int                                  null comment '咨询环境评分(1-5)',
    would_recommend          tinyint(1)                           null comment '是否愿意推荐: 0-否, 1-是',
    strengths                text                                 null comment '咨询优点',
    improvements             text                                 null comment '改进建议',
    overall_comments         text                                 null comment '总体评价',
    handwritten_feedback_url varchar(255)                         null comment '手写反馈问卷图片URL',
    anonymous_feedback       tinyint(1) default 0                 null comment '是否匿名反馈: 0-否, 1-是',
    completed_at             datetime                             null comment '完成时间',
    created_at               datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at               datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uk_appointment
        unique (appointment_id),
    constraint fk_feedback_appointment
        foreign key (appointment_id) references consultation_appointment (appointment_id)
            on delete cascade,
    constraint fk_feedback_counselor
        foreign key (counselor_id) references counselor_info (counselor_id)
            on delete cascade,
    constraint fk_feedback_student
        foreign key (student_id) references student_info (student_id)
            on delete cascade
)
    comment '咨询反馈问卷表';

create index idx_counselor_id
    on consultation_feedback (counselor_id);

create index idx_student_id
    on consultation_feedback (student_id);

create table consultation_record
(
    record_id              char(36)                              not null comment '记录ID'
        primary key,
    appointment_id         char(36)                              not null comment '预约ID',
    counselor_id           char(36)                              not null comment '咨询师ID',
    student_id             char(36)                              not null comment '学生ID',
    record_date            date                                  not null comment '记录日期',
    session_number         int         default 1                 null comment '第几次咨询',
    presenting_problem     text                                  null comment '呈现问题',
    session_content        text                                  not null comment '咨询内容',
    counselor_observations text                                  null comment '咨询师观察',
    intervention_methods   text                                  null comment '干预方法',
    homework_assignment    text                                  null comment '家庭作业',
    next_session_plan      text                                  null comment '下次咨询计划',
    counselor_notes        text                                  null comment '咨询师备注',
    handwritten_record_url varchar(255)                          null comment '手写咨询记录图片URL',
    confidential_level     varchar(20) default 'NORMAL'          null comment '保密级别: NORMAL-一般, SENSITIVE-敏感, HIGHLY_SENSITIVE-高度敏感',
    record_status          varchar(20) default 'DRAFT'           null comment '记录状态: DRAFT-草稿, SUBMITTED-已提交, REVIEWED-已审核',
    reviewed_by            char(36)                              null comment '审核人',
    reviewed_at            datetime                              null comment '审核时间',
    review_notes           text                                  null comment '审核意见',
    attachments            json                                  null comment '附件列表(JSON数组)',
    created_at             datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at             datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uk_appointment
        unique (appointment_id),
    constraint fk_record_appointment
        foreign key (appointment_id) references consultation_appointment (appointment_id)
            on delete cascade,
    constraint fk_record_counselor
        foreign key (counselor_id) references counselor_info (counselor_id)
            on delete cascade,
    constraint fk_record_student
        foreign key (student_id) references student_info (student_id)
            on delete cascade
)
    comment '咨询记录表';

create index idx_counselor_id
    on consultation_record (counselor_id);

create index idx_record_date
    on consultation_record (record_date);

create index idx_student_id
    on consultation_record (student_id);

create table crisis_report
(
    report_id              char(36)                              not null comment '上报ID'
        primary key,
    appointment_id         char(36)                              null comment '预约ID',
    counselor_id           char(36)                              not null comment '咨询师ID',
    student_id             char(36)                              not null comment '学生ID',
    report_type            varchar(20)                           not null comment '上报类型: SUICIDAL-自杀风险, VIOLENT-暴力倾向, SELF_HARM-自伤行为, OTHER-其他',
    crisis_level           varchar(20) default 'MODERATE'        not null comment '危机等级: LOW-低, MODERATE-中, HIGH-高, SEVERE-严重',
    incident_description   text                                  not null comment '事件描述',
    risk_assessment        text                                  not null comment '风险评估',
    immediate_actions      text                                  null comment '已采取的紧急措施',
    recommended_actions    text                                  null comment '建议措施',
    handwritten_report_url varchar(255)                          null comment '手写危机上报表图片URL',
    reported_to            varchar(255)                          null comment '上报对象(部门/人员)',
    report_status          varchar(20) default 'PENDING'         null comment '上报状态: PENDING-待处理, REVIEWING-审核中, PROCESSED-已处理, CLOSED-已关闭',
    reviewer_id            char(36)                              null comment '审核人ID',
    review_notes           text                                  null comment '审核意见',
    follow_up_plan         text                                  null comment '跟进计划',
    resolution_outcome     text                                  null comment '处理结果',
    created_at             datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at             datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint fk_crisis_appointment
        foreign key (appointment_id) references consultation_appointment (appointment_id)
            on delete set null,
    constraint fk_crisis_counselor
        foreign key (counselor_id) references counselor_info (counselor_id)
            on delete cascade,
    constraint fk_crisis_student
        foreign key (student_id) references student_info (student_id)
            on delete cascade
)
    comment '危机上报表';

create index idx_appointment_id
    on crisis_report (appointment_id);

create index idx_counselor_id
    on crisis_report (counselor_id);

create index idx_crisis_level
    on crisis_report (crisis_level);

create index idx_report_status
    on crisis_report (report_status);

create index idx_student_id
    on crisis_report (student_id);

create table long_term_consultation_request
(
    request_id              char(36)                              not null comment '申请ID'
        primary key,
    appointment_id          char(36)                              not null comment '预约ID',
    counselor_id            char(36)                              not null comment '咨询师ID',
    student_id              char(36)                              not null comment '学生ID',
    request_reason          text                                  not null comment '申请原因',
    proposed_sessions       int                                   not null comment '建议咨询次数',
    session_frequency       varchar(20)                           null comment '咨询频率: WEEKLY-每周, BIWEEKLY-每两周, MONTHLY-每月',
    treatment_goals         text                                  null comment '治疗目标',
    expected_outcomes       text                                  null comment '预期效果',
    handwritten_request_url varchar(255)                          null comment '手写申请表图片URL',
    request_status          varchar(20) default 'PENDING'         null comment '申请状态: PENDING-待审批, APPROVED-已批准, REJECTED-已拒绝',
    approver_id             char(36)                              null comment '审批人ID',
    approval_notes          text                                  null comment '审批意见',
    approved_sessions       int                                   null comment '批准的咨询次数',
    approved_frequency      varchar(20)                           null comment '批准的咨询频率',
    start_date              date                                  null comment '开始日期',
    end_date                date                                  null comment '结束日期',
    created_at              datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at              datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uk_appointment
        unique (appointment_id),
    constraint fk_longterm_appointment
        foreign key (appointment_id) references consultation_appointment (appointment_id)
            on delete cascade,
    constraint fk_longterm_counselor
        foreign key (counselor_id) references counselor_info (counselor_id)
            on delete cascade,
    constraint fk_longterm_student
        foreign key (student_id) references student_info (student_id)
            on delete cascade
)
    comment '长程咨询申请表';

create index idx_counselor_id
    on long_term_consultation_request (counselor_id);

create index idx_request_status
    on long_term_consultation_request (request_status);

create index idx_student_id
    on long_term_consultation_request (student_id);

create table parent_info
(
    parent_id                 char(36)                             not null comment '家长ID'
        primary key,
    user_id                   char(36)                             not null comment '用户ID',
    relationship_with_student varchar(20)                          not null comment '与学生关系: 父亲, 母亲, 监护人等',
    student_id                char(36)                             not null comment '关联学生ID',
    occupation                varchar(100)                         null comment '职业',
    work_unit                 varchar(100)                         null comment '工作单位',
    family_economic_status    varchar(20)                          null comment '家庭经济状况',
    is_primary_contact        tinyint(1) default 0                 null comment '是否主要联系人: 0-否, 1-是',
    contact_priority          int        default 1                 null comment '联系优先级',
    created_at                datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at                datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uk_user_id
        unique (user_id),
    constraint fk_parent_info_user
        foreign key (user_id) references user (user_id)
            on delete cascade,
    constraint parent_info_student_info_student_id_fk
        foreign key (student_id) references student_info (student_id)
)
    comment '家长信息扩展表';

create table referral_record
(
    referral_id              char(36)                              not null comment '转介ID'
        primary key,
    appointment_id           char(36)                              null comment '预约ID',
    source_counselor_id      char(36)                              not null comment '转出咨询师ID',
    target_counselor_id      char(36)                              null comment '转入咨询师ID',
    target_department        varchar(100)                          null comment '目标科室/部门',
    student_id               char(36)                              not null comment '学生ID',
    referral_reason          text                                  not null comment '转介原因',
    student_condition        text                                  null comment '学生当前状况',
    handwritten_referral_url varchar(255)                          null comment '手写转介表图片URL',
    referral_urgency         varchar(20) default 'ROUTINE'         null comment '转介紧急程度: ROUTINE-常规, URGENT-紧急, EMERGENCY-危急',
    referral_status          varchar(20) default 'PENDING'         null comment '转介状态: PENDING-待处理, ACCEPTED-已接受, REJECTED-已拒绝, COMPLETED-已完成',
    target_response          text                                  null comment '目标方回应',
    response_date            datetime                              null comment '回应日期',
    follow_up_notes          text                                  null comment '跟进备注',
    created_at               datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at               datetime    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint fk_referral_appointment
        foreign key (appointment_id) references consultation_appointment (appointment_id)
            on delete set null,
    constraint fk_referral_source_counselor
        foreign key (source_counselor_id) references counselor_info (counselor_id)
            on delete cascade,
    constraint fk_referral_student
        foreign key (student_id) references student_info (student_id)
            on delete cascade,
    constraint fk_referral_target_counselor
        foreign key (target_counselor_id) references counselor_info (counselor_id)
            on delete set null
)
    comment '转介记录表';

create index idx_appointment_id
    on referral_record (appointment_id);

create index idx_referral_status
    on referral_record (referral_status);

create index idx_source_counselor
    on referral_record (source_counselor_id);

create index idx_student_id
    on referral_record (student_id);

create index idx_target_counselor
    on referral_record (target_counselor_id);

create index idx_class_id
    on student_info (class_id);

create index idx_college_id
    on student_info (college_id);

create index idx_enrollment_year
    on student_info (enrollment_year);

create table system_message
(
    message_id          char(36)                             not null comment '消息ID'
        primary key,
    user_id             char(36)                             not null comment '用户ID',
    message_type        varchar(20)                          not null comment '消息类型: APPOINTMENT-预约, REMINDER-提醒, NOTIFICATION-通知, ALERT-警报',
    title               varchar(100)                         not null comment '消息标题',
    content             text                                 not null comment '消息内容',
    related_id          char(36)                             null comment '关联ID(如预约ID)',
    is_read             tinyint(1) default 0                 null comment '是否已读: 0-未读, 1-已读',
    read_at             datetime                             null comment '阅读时间',
    scheduled_send_time datetime                             null comment '计划发送时间',
    sent_at             datetime                             null comment '实际发送时间',
    expires_at          datetime                             null comment '过期时间',
    created_at          datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at          datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint fk_message_user
        foreign key (user_id) references user (user_id)
            on delete cascade
)
    comment '系统消息表';

create index idx_is_read
    on system_message (is_read);

create index idx_message_type
    on system_message (message_type);

create index idx_scheduled_send
    on system_message (scheduled_send_time);

create index idx_user_id
    on system_message (user_id);

create index idx_account_status
    on user (account_status);

create index idx_created_at
    on user (created_at);

create table user_role
(
    role_id     char(36)                             not null comment '角色ID'
        primary key,
    role_code   varchar(50)                          not null comment '角色代码',
    role_name   varchar(50)                          not null comment '角色名称',
    description varchar(255)                         null comment '角色描述',
    is_system   tinyint(1) default 0                 not null comment '是否系统角色: 0-否, 1-是',
    created_at  datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at  datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uk_role_code
        unique (role_code)
)
    comment '用户角色表';

create table user_role_mapping
(
    mapping_id char(36)                           not null comment '关联ID'
        primary key,
    user_id    char(36)                           not null comment '用户ID',
    role_id    char(36)                           not null comment '角色ID',
    created_at datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    constraint uk_user_role
        unique (user_id, role_id),
    constraint fk_user_role_mapping_role
        foreign key (role_id) references user_role (role_id)
            on delete cascade,
    constraint fk_user_role_mapping_user
        foreign key (user_id) references user (user_id)
            on delete cascade
)
    comment '用户角色关联表';

create table visit_registration
(
    registration_id                char(36)                             not null comment '登记ID'
        primary key,
    appointment_id                 char(36)                             not null comment '预约ID',
    main_concern                   text                                 not null comment '主要困扰',
    concern_duration               varchar(50)                          null comment '困扰持续时间',
    previous_treatment             tinyint(1) default 0                 null comment '是否接受过心理治疗: 0-否, 1-是',
    previous_treatment_detail      text                                 null comment '既往治疗详情',
    current_medication             tinyint(1) default 0                 null comment '是否正在服药: 0-否, 1-是',
    medication_detail              text                                 null comment '服药详情',
    suicidal_thoughts              tinyint(1) default 0                 null comment '是否有自杀念头: 0-否, 1-是',
    suicidal_plan                  tinyint(1) default 0                 null comment '是否有自杀计划: 0-否, 1-是',
    self_harm_behavior             tinyint(1) default 0                 null comment '是否有自伤行为: 0-否, 1-是',
    emergency_contact_name         varchar(50)                          null comment '紧急联系人姓名',
    emergency_contact_phone        varchar(20)                          null comment '紧急联系人电话',
    emergency_contact_relationship varchar(20)                          null comment '紧急联系人关系',
    expectations                   text                                 null comment '对咨询的期望',
    other_concerns                 text                                 null comment '其他关注点',
    handwritten_form_url           varchar(255)                         null comment '手写登记表图片URL',
    completed_at                   datetime                             null comment '完成时间',
    created_at                     datetime   default CURRENT_TIMESTAMP not null comment '创建时间',
    updated_at                     datetime   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint uk_appointment
        unique (appointment_id),
    constraint fk_registration_appointment
        foreign key (appointment_id) references consultation_appointment (appointment_id)
            on delete cascade
)
    comment '来访登记问卷表';

