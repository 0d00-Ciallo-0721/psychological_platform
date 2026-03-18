INSERT INTO organization
(org_id, org_code, org_name, org_type, parent_id, level, sort_order, contact_person, contact_phone, address, description, status)
VALUES
    ('11111111-1111-1111-1111-111111111111','ORG-MHC','心理健康中心','department',NULL,1,10,'张老师','13800000001','主校区A楼','心理健康中心',1),
    ('22222222-2222-2222-2222-222222222222','ORG-XXC','xx学院','college',NULL,1,9,'李老师','13800000002','主校区B楼','二级学院',1),
    ('33333333-3333-3333-3333-333333333333','ORG-STD','学生工作处','department',NULL,1,8,'王老师','13800000003','主校区C楼','学生工作处',1),
    ('44444444-4444-4444-4444-444444444444','ORG-OPS','平台运维','department',NULL,1,7,'赵老师','13800000004','信息中心','平台运维与保障',1),
    ('55555555-5555-5555-5555-555555555555','ORG-PEER','朋辈互助站','department',NULL,1,6,'周老师','13800000005','主校区D楼','朋辈互助组织',1),
    ('66666666-6666-6666-6666-666666666666','ORG-YL','校团委','department',NULL,1,5,'吴老师','13800000006','主校区E楼','校团委',1);

INSERT INTO user
(user_id, username, password_hash, email, phone, real_name, gender, birth_date, avatar_url, id_card, wechat_openid, wechat_unionid, account_status, last_login_time, created_at, updated_at, deleted_at)
VALUES
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1','admin_mhc','$2a$10$testtesttesttesttesttesttesttesttesttesttesttestt','admin_mhc@test.com','13800001001','心理中心管理员',1,'1990-01-01',NULL,NULL,'wx_admin_mhc_01',NULL,1,NULL,NOW(),NOW(),NULL),
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2','editor_college','$2a$10$testtesttesttesttesttesttesttesttesttesttesttestt','editor_college@test.com','13800001002','学院编辑',2,'1992-02-02',NULL,NULL,'wx_editor_college_01',NULL,1,NULL,NOW(),NOW(),NULL),
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa3','editor_sao','$2a$10$testtesttesttesttesttesttesttesttesttesttesttestt','editor_sao@test.com','13800001003','学工处编辑',1,'1991-03-03',NULL,NULL,'wx_editor_sao_01',NULL,1,NULL,NOW(),NOW(),NULL),
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa4','ops_user','$2a$10$testtesttesttesttesttesttesttesttesttesttesttestt','ops_user@test.com','13800001004','运维人员',0,'1989-04-04',NULL,NULL,'wx_ops_user_01',NULL,1,NULL,NOW(),NOW(),NULL),
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa5','peer_editor','$2a$10$testtesttesttesttesttesttesttesttesttesttesttestt','peer_editor@test.com','13800001005','朋辈互助编辑',2,'1993-05-05',NULL,NULL,'wx_peer_editor_01',NULL,1,NULL,NOW(),NOW(),NULL),
    ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa6','league_editor','$2a$10$testtesttesttesttesttesttesttesttesttesttesttestt','league_editor@test.com','13800001006','团委编辑',1,'1994-06-06',NULL,NULL,'wx_league_editor_01',NULL,1,NULL,NOW(),NOW(),NULL);

INSERT INTO cms_content
(type, title, summary, content, cover_url, link_url, author, source_org, is_top, status, sort_order, publish_time, creator_id)
VALUES
-- 1 活动风采（type=1）
(1,'心理健康月启动仪式圆满举行','心理健康中心联合二级学院开展启动仪式。','【活动风采】启动仪式在学生活动中心举行，包含讲座、互动体验与心理测评体验区。','/static/cms/covers/activity-01.jpg',NULL,'心理健康中心','11111111-1111-1111-1111-111111111111',0,1,10,'2026-01-05 10:00:00','aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1'),
(1,'“减压同行”团体辅导活动回顾','通过正念练习与情绪表达降低压力。','【活动风采】团体辅导共4个环节：热身、正念练习、情绪表达、行动计划。','/static/cms/covers/activity-02.jpg',NULL,'xx学院心理辅导站','22222222-2222-2222-2222-222222222222',0,1,8,'2026-01-12 15:30:00','aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'),
(1,'二级学院心理游园会精彩瞬间','多主题摊位提升心理健康意识。','【活动风采】包含压力测量、情绪卡片、关系沟通小游戏等，现场气氛热烈。','/static/cms/covers/activity-03.jpg',NULL,'学生工作处','33333333-3333-3333-3333-333333333333',0,0,5,NULL,'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa3'),

-- 2 通知公告（type=2）
(2,'关于开展春季心理普查的通知','请各学院按要求组织学生参与。','【通知公告】普查时间：2月10日至2月28日。请各班级辅导员组织完成。',NULL,NULL,'心理健康中心','11111111-1111-1111-1111-111111111111',1,1,100,'2026-02-01 09:00:00','aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1'),
(2,'心理咨询预约流程调整说明','预约入口与时段规则更新。','【通知公告】自2月起，预约改为“先选咨询方式再选时段”，并增加取消次数限制。',NULL,NULL,'心理健康中心','11111111-1111-1111-1111-111111111111',0,1,50,'2026-02-02 10:00:00','aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1'),
(2,'系统维护公告','2月5日凌晨系统升级维护。','【通知公告】维护时间：2026-02-05 00:00-02:00，期间可能无法访问。',NULL,NULL,'平台运维','44444444-4444-4444-4444-444444444444',0,2,1,'2026-02-04 18:00:00','aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa4'),

-- 3 朋辈互助（type=3）
(3,'朋辈互助是什么？','用同伴支持促进心理成长。','【朋辈互助】朋辈互助强调倾听、共情与资源链接，不替代专业咨询。','/static/cms/covers/peer-01.jpg',NULL,'朋辈互助站','55555555-5555-5555-5555-555555555555',0,1,20,'2026-01-20 14:00:00','aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa5'),
(3,'倾听技巧：三步走','倾听不是给建议，而是理解。','【朋辈互助】三步：1复述澄清 2情绪命名 3共情回应，并注意边界与保密。','/static/cms/covers/peer-02.jpg',NULL,'朋辈互助站','55555555-5555-5555-5555-555555555555',0,1,18,'2026-01-22 16:20:00','aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa5'),
(3,'朋辈互助活动招募','面向全校招募新一期朋辈志愿者。','【朋辈互助】报名条件、培训安排与考核方式详见正文。',NULL,NULL,'校团委','66666666-6666-6666-6666-666666666666',0,0,5,NULL,'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa6'),

-- 4 心理百科（type=4，带 link_url）
(4,'情绪管理入门','认识情绪、接纳情绪、调节情绪。','【心理百科】推荐使用 ABCDE 记录法，识别事件-信念-结果，形成替代性思维。','/static/cms/covers/wiki-01.jpg','https://example.com/learn/emotion','心理百科编辑部','11111111-1111-1111-1111-111111111111',0,1,12,'2026-01-08 11:00:00','aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1'),
(4,'压力与睡眠：如何打破恶性循环','规律作息与放松训练很关键。','【心理百科】建议：睡前30分钟远离屏幕，进行渐进式肌肉放松或呼吸训练。','/static/cms/covers/wiki-02.jpg','https://example.com/learn/sleep','心理百科编辑部','11111111-1111-1111-1111-111111111111',0,1,10,'2026-01-18 21:00:00','aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1'),
(4,'心理资源导航','整理校内外心理健康学习资源。','【心理百科】包含课程、热线、科普网站与自助工具合集。',NULL,'https://example.com/resources','心理百科编辑部','11111111-1111-1111-1111-111111111111',0,2,1,'2026-01-02 08:00:00','aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa1'),

-- 5 心理美文（type=5）
(5,'与自己和解','学会温柔地对待自己。','【心理美文】允许自己慢一点，给情绪一个落脚处，给生活一点余地。','/static/cms/covers/essay-01.jpg',NULL,'学生投稿','22222222-2222-2222-2222-222222222222',0,1,30,'2026-01-25 13:10:00','aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa2'),
(5,'在不确定中前行','不确定是常态，行动是答案。','【心理美文】把注意力放回今天能做的小事，焦虑会慢慢松动。','/static/cms/covers/essay-02.jpg',NULL,'学生投稿','33333333-3333-3333-3333-333333333333',0,1,25,'2026-01-28 19:45:00','aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa3'),
(5,'夜色与星光','写给每一个努力生活的人。','【心理美文】你看见的黑暗里，也藏着微光；你走过的路，会在未来回响。',NULL,NULL,'学生投稿','33333333-3333-3333-3333-333333333333',0,0,5,NULL,'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaa3');




INSERT INTO group_activity(
    activity_id,title,summary,content,cover_url,location,
    start_time,end_time,join_start_time,join_end_time,
    capacity,joined_count,status,creator_id,created_at,updated_at
) VALUES (
             UUID(),'团体活动A','简介A','详情A',NULL,'心理中心活动室',
             DATE_ADD(NOW(), INTERVAL 1 DAY),
             DATE_ADD(NOW(), INTERVAL 1 DAY) + INTERVAL 2 HOUR,
             NOW(), DATE_ADD(NOW(), INTERVAL 1 DAY),
             30,0,'PUBLISHED',NULL,NOW(),NOW()
         );
