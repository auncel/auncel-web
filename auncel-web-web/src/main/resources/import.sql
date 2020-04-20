insert  into user (id, avatar, realname, username,school, status) values (1, "http://www.gravatar.com/avatar/fudayi?s=55&d=identicon&r=PG", "付达意", "fudayi", "HDU", "normal");
insert  into user (id, avatar, realname, username,school, status) values (2, "http://www.gravatar.com/avatar/testuser?s=55&d=identicon&r=PG", "测试用户", "testuser", "HDU", "normal");

insert  into auth_log (id, title, content, login_ip,user_id) values (1, "正常登录", "付达意正常登录", "0.0.0.0", 1);
insert  into auth_log (id, title, content, login_ip,user_id) values (2, "正常登录", "付达意正常登录", "0.0.0.0", 1);
insert  into auth_log (id, title, content, login_ip,user_id) values (3, "正常登录", "付达意异常登录,登录地:杭州", "1.1.1.1", 1);
insert  into auth_log (id, title, content, login_ip,user_id) values (4, "正常登录", "付达意正常登录", "0.0.0.0", 1);

insert  into user_auth  (id, user_id, identifier, identity_type, credential, verified) values (1, 1, "1@yidafu.dev", "email", "b305cadbb3bce54f3aa59c64fec00dea", false);
insert  into user_auth  (id, user_id, identifier, identity_type, credential, verified) values (2, 1, "dov-yih", "github", "b305cadbb3bce54f3aa59c64fec00dea", false);

INSERT INTO contest(`id`, `created_at`, `clarification`, `end_time`, `invitaion_code`, `start_time`, `status`, `time_limit`, `title`, `maker_id`) VALUES (1, '2020-04-13 10:54:47', 'clearifaction', '2020-04-30 10:55:05', 'basababa', '2020-04-01 10:55:14', 'testing', 12345667, '公开题库', 1);

insert into problem (id, title, description, q_html, q_css, render_tree, stars, difficulty, maker_id) values (1, "titile", "description", "<div></div>", "div { color: red; }", "{}", 1, 2, 1);
insert into problem (id, title, description, q_html, q_css, render_tree, stars, difficulty, maker_id) values (2, "titile", "description", "<div></div>", "div { color: red; }", "{}", 1, 2, 1);

insert into contest_problem (contest_id, problem_id, score) values (1, 2, 89);

insert into tag (id, value) values (1, "css");
insert into tag (id, value) values (2, "box-model");

insert into problem_tag (tag_id, problem_id) values (1, 1);
insert into problem_tag (tag_id, problem_id) values (2, 1);

insert into user_contest (duration, status, total_score, contest_id, user_id) VALUES (23423541, '1', 89, 1, 2);
