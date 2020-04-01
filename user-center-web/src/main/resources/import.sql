insert  into user (id, avatar, realname, username,school, status) values (1, "https:test.com/1.png", "付达意", "fudayi", "HDU", "normal");

insert  into auth_log (id, title, content, login_ip,user_id) values (1, "正常登录", "付达意正常登录", "0.0.0.0", 1);
insert  into auth_log (id, title, content, login_ip,user_id) values (2, "正常登录", "付达意正常登录", "0.0.0.0", 1);
insert  into auth_log (id, title, content, login_ip,user_id) values (3, "正常登录", "付达意异常登录,登录地:杭州", "1.1.1.1", 1);
insert  into auth_log (id, title, content, login_ip,user_id) values (4, "正常登录", "付达意正常登录", "0.0.0.0", 1);

insert  into user_auth  (id, user_id, identifier, identity_type, credential, verified) values (1, 1, "fudayi", "email", "password", false);
insert  into user_auth  (id, user_id, identifier, identity_type, credential, verified) values (2, 1, "dov-yih", "github", "password", false);