# 心理健康平台 - 咨询预约管理数据表说明

## 概述
本文档详细说明了心理健康平台咨询预约管理模块的12个核心数据表的作用、字段说明和业务逻辑。

## 目录
- [1. 咨询师排班表 (counselor_schedule) - 咨询师排班表](#1-咨询师排班表-counselor_schedule---咨询师排班表)
- [2. 咨询预约表 (consultation_appointment) - 咨询预约表](#2-咨询预约表-consultation_appointment---咨询预约表)
- [3. 来访登记问卷表 (visit_registration) - 来访登记问卷表](#3-来访登记问卷表-visit_registration---来访登记问卷表)
- [4. 前测量表表 (pre_assessment) - 前测量表表](#4-前测量表表-pre_assessment---前测量表表)
- [5. 知情同意书表 (informed_consent) - 知情同意书表](#5-知情同意书表-informed_consent---知情同意书表)
- [6. 咨询记录表 (consultation_record) - 咨询记录表](#6-咨询记录表-consultation_record---咨询记录表)
- [7. 咨询反馈问卷表 (consultation_feedback) - 咨询反馈问卷表](#7-咨询反馈问卷表-consultation_feedback---咨询反馈问卷表)
- [8. 转介记录表 (referral_record) - 转介记录表](#8-转介记录表-referral_record---转介记录表)
- [9. 危机上报表 (crisis_report) - 危机上报表](#9-危机上报表-crisis_report---危机上报表)
- [10. 长程咨询申请表 (long_term_consultation_request) - 长程咨询申请表](#10-长程咨询申请表-long_term_consultation_request---长程咨询申请表)
- [11. 系统消息表 (system_message) - 系统消息表](#11-系统消息表-system_message---系统消息表)
- [表关系图](#表关系图)
- [业务流程图](#业务流程图)
- [图片上传支持](#图片上传支持)
- [使用说明](#使用说明)

## 表结构总览

### 1. 咨询师排班表 (`counselor_schedule`) - 咨询师排班表
**作用**：管理咨询师的可预约时段，支持智能排班功能
**核心字段**：
- `schedule_id` - 排班ID（主键）
- `counselor_id` - 咨询师ID（外键）
- `schedule_date` - 排班日期
- `start_time`/`end_time` - 工作时间段
- `slot_duration` - 单次咨询时长（默认50分钟）
- `available_slots` - 剩余可预约数
- `status` - 状态（0-停用，1-启用，2-已满）

**业务逻辑**：
- 管理员/咨询师设置可预约时段
- 支持常规、特殊、节假日等不同类型的排班
- 自动计算剩余可预约数

### 2. 咨询预约表 (`consultation_appointment`) - 咨询预约表
**作用**：记录学生的预约信息，是核心业务表
**核心字段**：
- `appointment_id` - 预约ID（主键）
- `student_id` - 学生ID（外键）
- `counselor_id` - 咨询师ID（外键）
- `schedule_id` - 排班ID（外键）
- `appointment_status` - 预约状态（待确认、已确认、已完成、已取消、爽约）
- `consultation_mode` - 咨询方式（线下、线上、电话）
- `appointment_type` - 咨询类型（个体、团体、紧急）

**业务逻辑**：
- 学生选择咨询师和时间进行预约
- 支持不同咨询方式和类型
- 记录预约状态变化历史

### 3. 来访登记问卷表 (`visit_registration`) - 来访登记问卷表
**作用**：收集预约前的来访登记信息
**核心字段**：
- `registration_id` - 登记ID（主键）
- `appointment_id` - 预约ID（外键，唯一）
- `main_concern` - 主要困扰（必填）
- `suicidal_thoughts`/`suicidal_plan`/`self_harm_behavior` - 危机风险评估
- `emergency_contact_*` - 紧急联系人信息
- `handwritten_form_url` - 手写登记表图片URL（可选）

**业务逻辑**：
- 预约前必须填写的来访登记
- 收集学生基本困扰和危机风险信息
- 支持手写表图片上传

### 4. 前测量表表 (`pre_assessment`) - 前测量表表
**作用**：存储咨询前的心理测量数据
**核心字段**：
- `assessment_id` - 评估ID（主键）
- `appointment_id` - 预约ID（外键）
- `scale_type` - 量表类型（PHQ-9、GAD-7等）
- `scale_name` - 量表名称
- `total_score` - 总分
- `score_interpretation` - 分数解释（正常、轻度、中度、重度）
- `assessment_data` - 评估数据（JSON格式）
- `handwritten_scale_url` - 手写量表图片URL（可选）

**业务逻辑**：
- 记录咨询前的心理测量结果
- 支持多种标准化量表
- 自动计算分数和解释

### 5. 知情同意书表 (`informed_consent`) - 知情同意书表
**作用**：记录知情同意书的签署情况
**核心字段**：
- `consent_id` - 同意书ID（主键）
- `appointment_id` - 预约ID（外键）
- `consent_version` - 同意书版本
- `consent_content` - 同意书内容
- `has_agreed` - 是否同意（0-否，1-是）
- `agreed_at` - 同意时间
- `student_signature` - 学生电子签名
- `handwritten_consent_url` - 手写同意书图片URL（可选）

**业务逻辑**：
- 电子化知情同意书签署流程
- 支持版本管理
- 记录签署时间和IP地址

### 6. 咨询记录表 (`consultation_record`) - 咨询记录表
**作用**：咨询师填写的咨询过程记录
**核心字段**：
- `record_id` - 记录ID（主键）
- `appointment_id` - 预约ID（外键，唯一）
- `session_number` - 第几次咨询
- `session_content` - 咨询内容（必填）
- `counselor_observations` - 咨询师观察
- `intervention_methods` - 干预方法
- `homework_assignment` - 家庭作业
- `handwritten_record_url` - 手写咨询记录图片URL（可选）
- `confidential_level` - 保密级别（一般、敏感、高度敏感）
- `record_status` - 记录状态（草稿、已提交、已审核）

**业务逻辑**：
- 咨询师记录咨询过程和观察
- 支持多级保密和审核流程
- 可上传手写记录图片

### 7. 咨询反馈问卷表 (`consultation_feedback`) - 咨询反馈问卷表
**作用**：学生填写的咨询效果反馈
**核心字段**：
- `feedback_id` - 反馈ID（主键）
- `appointment_id` - 预约ID（外键，唯一）
- `satisfaction_score` - 满意度评分（1-5）
- `helpfulness_score` - 帮助程度评分（1-5）
- `counselor_attitude_score` - 咨询师态度评分（1-5）
- `would_recommend` - 是否愿意推荐
- `handwritten_feedback_url` - 手写反馈问卷图片URL（可选）
- `anonymous_feedback` - 是否匿名反馈

**业务逻辑**：
- 收集学生对咨询效果的反馈
- 多维度评分体系
- 支持匿名反馈

### 8. 转介记录表 (`referral_record`) - 转介记录表
**作用**：记录咨询师发起的转介流程
**核心字段**：
- `referral_id` - 转介ID（主键）
- `appointment_id` - 预约ID（外键）
- `source_counselor_id` - 转出咨询师ID（外键）
- `target_counselor_id` - 转入咨询师ID（外键）
- `referral_reason` - 转介原因（必填）
- `handwritten_referral_url` - 手写转介表图片URL（可选）
- `referral_urgency` - 转介紧急程度（常规、紧急、危急）
- `referral_status` - 转介状态（待处理、已接受、已拒绝、已完成）

**业务逻辑**：
- 咨询师之间的个案转介
- 多级紧急程度管理
- 跟踪转介处理状态

### 9. 危机上报表 (`crisis_report`) - 危机上报表
**作用**：记录危机情况的上报和处理
**核心字段**：
- `report_id` - 上报ID（主键）
- `appointment_id` - 预约ID（外键）
- `report_type` - 上报类型（自杀风险、暴力倾向、自伤行为、其他）
- `crisis_level` - 危机等级（低、中、高、严重）
- `incident_description` - 事件描述（必填）
- `risk_assessment` - 风险评估（必填）
- `handwritten_report_url` - 手写危机上报表图片URL（可选）
- `report_status` - 上报状态（待处理、审核中、已处理、已关闭）

**业务逻辑**：
- 危机情况的标准化上报流程
- 多级危机等级评估
- 完整的处理跟踪机制

### 10. 长程咨询申请表 (`long_term_consultation_request`) - 长程咨询申请表
**作用**：记录长程咨询的申请和审批
**核心字段**：
- `request_id` - 申请ID（主键）
- `appointment_id` - 预约ID（外键，唯一）
- `request_reason` - 申请原因（必填）
- `proposed_sessions` - 建议咨询次数
- `treatment_goals` - 治疗目标
- `handwritten_request_url` - 手写申请表图片URL（可选）
- `request_status` - 申请状态（待审批、已批准、已拒绝）
- `approved_sessions` - 批准的咨询次数

**业务逻辑**：
- 长程咨询的申请和审批流程
- 治疗目标和预期效果设定
- 审批结果记录

### 11. 系统消息表 (`system_message`) - 系统消息表
**作用**：管理系统通知和提醒消息
**核心字段**：
- `message_id` - 消息ID（主键）
- `user_id` - 用户ID（外键）
- `message_type` - 消息类型（预约、提醒、通知、警报）
- `title` - 消息标题
- `content` - 消息内容
- `related_id` - 关联ID（如预约ID）
- `is_read` - 是否已读
- `scheduled_send_time` - 计划发送时间

**业务逻辑**：
- 预约成功、取消、提醒等消息通知
- 支持定时发送
- 消息阅读状态跟踪

## 表关系图

```
咨询师排班表 (counselor_schedule)
       ↓
咨询预约表 (consultation_appointment)
       ├── 来访登记问卷表 (visit_registration)
       ├── 前测量表表 (pre_assessment)
       ├── 知情同意书表 (informed_consent)
       ├── 咨询记录表 (consultation_record)
       ├── 咨询反馈问卷表 (consultation_feedback)
       ├── 转介记录表 (referral_record)
       ├── 危机上报表 (crisis_report)
       └── 长程咨询申请表 (long_term_consultation_request)
       
系统消息表 (system_message) ← 独立消息系统
```

## 业务流程图

1. **预约阶段**：排班 → 预约 → 来访登记 → 前测量 → 知情同意
2. **咨询阶段**：咨询记录 → 转介/危机上报/长程申请（如需要）
3. **反馈阶段**：咨询反馈 → 系统消息通知

## 图片上传支持

以下表支持手写表图片上传（均为可选字段）：
- `visit_registration.handwritten_form_url` - 来访登记表
- `pre_assessment.handwritten_scale_url` - 前测量表
- `informed_consent.handwritten_consent_url` - 知情同意书
- `consultation_record.handwritten_record_url` - 咨询记录
- `consultation_feedback.handwritten_feedback_url` - 反馈问卷
- `referral_record.handwritten_referral_url` - 转介表
- `crisis_report.handwritten_report_url` - 危机上报表
- `long_term_consultation_request.handwritten_request_url` - 长程申请表

## 使用说明

1. 首先执行 `mental_health_platform.sql` 创建基础表结构
2. 然后执行 `consultation_tables.sql` 创建咨询预约相关表
3. 最后执行 `test_data.sql` 插入测试数据（可选）

所有表使用UTF8MB4字符集，InnoDB存储引擎，支持完整的事务和外键约束。