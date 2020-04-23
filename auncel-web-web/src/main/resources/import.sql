-- 超级用户
insert  into user (id, avatar, realname, username,school, status) values (1, "http://www.gravatar.com/avatar/fudayi?s=200&d=identicon&r=PG", "付达意", "fudayi", "HDU", "normal");
-- 普通用户
insert  into user (id, avatar, realname, username,school, status) values (2, "http://www.gravatar.com/avatar/testuser?s=200&d=identicon&r=PG", "测试用户", "testuser", "HDU", "normal");

insert  into auth_log (id, title, content, login_ip,user_id) values (1, "正常登录", "付达意正常登录", "0.0.0.0", 1);
insert  into auth_log (id, title, content, login_ip,user_id) values (2, "正常登录", "付达意正常登录", "0.0.0.0", 1);
insert  into auth_log (id, title, content, login_ip,user_id) values (3, "正常登录", "付达意异常登录,登录地:杭州", "1.1.1.1", 1);
insert  into auth_log (id, title, content, login_ip,user_id) values (4, "正常登录", "付达意正常登录", "0.0.0.0", 1);

insert  into user_auth  (id, user_id, identifier, identity_type, credential, verified) values (1, 1, "1@yidafu.dev", "email", "b305cadbb3bce54f3aa59c64fec00dea", false);
insert  into user_auth  (id, user_id, identifier, identity_type, credential, verified) values (2, 1, "dov-yih", "github", "b305cadbb3bce54f3aa59c64fec00dea", false);
insert  into user_auth  (id, user_id, identifier, identity_type, credential, verified) values (3, 2, "test@yidafu.dev", "email", "b305cadbb3bce54f3aa59c64fec00dea", false);

INSERT INTO contest(`id`, `created_at`, `clarification`, `end_time`, `invitaion_code`, `start_time`, `status`, `access`, `time_limit`, `title`, `maker_id`) VALUES (1, '2020-04-13 10:54:47', 'clearifaction', '2020-04-30 10:55:05', 'basababa', '2020-04-01 10:55:14', 1, 0, 12345667, '公开竞赛', 1);
INSERT INTO contest(`id`, `created_at`, `clarification`, `end_time`, `invitaion_code`, `start_time`, `status`, `access`, `time_limit`, `title`, `maker_id`) VALUES (2, '2020-04-13 10:54:47', 'clearifaction', '2020-04-30 10:55:05', 'basababa', '2020-04-01 10:55:14', 1, 1, 12345667, '私有竞赛1', 1);
INSERT INTO contest(`id`, `created_at`, `clarification`, `end_time`, `invitaion_code`, `start_time`, `status`, `access`, `time_limit`, `title`, `maker_id`) VALUES (3, '2020-04-13 10:54:47', 'clearifaction', '2020-04-30 10:55:05', 'basababa', '2020-04-01 10:55:14', 1, 1, 12345667, '私有竞赛2', 1);

insert into problem (id, title, description, q_html, q_css, render_tree, stars, difficulty, maker_id) values (1, "problem titile", "problem description", "<div></div>", "div { color: red; }", "{}", 1, 2, 1);
insert into problem (id, title, description, q_html, q_css, render_tree, stars, difficulty, maker_id) values (2, "problem titile", "problem description", "<div></div>", "div { color: red; }", "{}", 1, 2, 1);
insert into problem (id, title, description, q_html, q_css, render_tree, stars, difficulty, maker_id) values (3, "problem titile", "problem description", "<div></div>", "div { color: red; }", "{}", 1, 2, 1);
insert into problem (id, title, description, q_html, q_css, render_tree, stars, difficulty, maker_id) values (4, "problem titile", "problem description", "<div></div>", "div { color: red; }", "{}", 1, 2, 1);
insert into problem (id, title, description, q_html, q_css, render_tree, stars, difficulty, maker_id) values (5, "problem titile", "problem description", "<div></div>", "div { color: red; }", "{}", 1, 2, 1);

insert into contest_problem (contest_id, problem_id, score) values (1, 1, 89);
insert into contest_problem (contest_id, problem_id, score) values (1, 2, 89);
insert into contest_problem (contest_id, problem_id, score) values (1, 3, 89);
insert into contest_problem (contest_id, problem_id, score) values (1, 4, 89);
insert into contest_problem (contest_id, problem_id, score) values (2, 2, 89);

insert into tag (id, value) values (1, "css");
insert into tag (id, value) values (2, "box-model");

insert into problem_tag (tag_id, problem_id) values (1, 1);
insert into problem_tag (tag_id, problem_id) values (2, 1);

insert into user_contest (duration, status, total_score, contest_id, user_id) VALUES (234235, 0, 89, 1, 2);
insert into user_contest (duration, status, total_score, contest_id, user_id) VALUES (234235, 0, 89, 2, 2);
insert into user_contest (duration, status, total_score, contest_id, user_id) VALUES (234235, 0, 89, 3, 2);

INSERT INTO `auncel_user`.`submission`(`id`, `a_css`, `a_html`, `logs`, `score`, `screenshot`, `status`, `problem_id`, `submiter_id`) VALUES (1, 'div{color: #fff;}', '<div></ div>', '[]', 50, 'http://cs.nyu.edu/courses/fall15/CSCI-UA.0004-002/images/html_css_js.png', 0, 1, 2);
INSERT INTO `auncel_user`.`submission`(`id`, `a_css`, `a_html`, `logs`, `score`, `screenshot`, `status`, `problem_id`, `submiter_id`) VALUES (2, 'div{color: #fff;}', '<div></ div>', '[]', 50, 'http://cs.nyu.edu/courses/fall15/CSCI-UA.0004-002/images/html_css_js.png', 4, 2, 2);
INSERT INTO `auncel_user`.`submission`(`id`, `a_css`, `a_html`, `logs`, `score`, `screenshot`, `status`, `problem_id`, `submiter_id`) VALUES (3, 'div{color: #fff;}', '<div></ div>', '[]', 50, 'http://cs.nyu.edu/courses/fall15/CSCI-UA.0004-002/images/html_css_js.png', 5, 3, 2);
INSERT INTO `auncel_user`.`submission`(`id`, `a_css`, `a_html`, `logs`, `score`, `screenshot`, `status`, `problem_id`, `submiter_id`) VALUES (4, 'div{color: #fff;}', '<div></ div>', '[]', 50, 'http://cs.nyu.edu/courses/fall15/CSCI-UA.0004-002/images/html_css_js.png', 3, 4, 2);
INSERT INTO `auncel_user`.`submission`(`id`, `a_css`, `a_html`, `logs`, `score`, `screenshot`, `status`, `problem_id`, `submiter_id`) VALUES (5, 'div{color: #fff;}', '<div></ div>', '[]', 50, 'http://cs.nyu.edu/courses/fall15/CSCI-UA.0004-002/images/html_css_js.png', 6, 5, 2);