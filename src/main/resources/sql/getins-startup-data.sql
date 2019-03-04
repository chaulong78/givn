USE getinscloud;
/* ADMIN ACCOUNT */
INSERT INTO user
  (user_name, password, email, avatar, enabled)
VALUES ( 'chaulong78', '$2a$10$3LyhIrcZqN0bnPpt7E5z/u97ZPrnMlfA9XQlhv2xwABHmNPZlhpXW'
       , 'chaulongdbp@gmail.com'
       , 'https://scontent.fhan2-1.fna.fbcdn.net/v/t1.0-9/51293319_2009673572486778_2249694532385898496_n.jpg?_nc_cat=103&_nc_oc=AQnB4UxhIZpJHxpc1a-aLtOhVFxS7DZfku0dL82WG8nAWxN8mIlTJnBAh9lwQiaoQH0&_nc_ht=scontent.fhan2-1.fna&oh=556d6e8a00d14917d8c83a660f47e9a0&oe=5CF35744'
       , 1);

insert into user_detail
  (user_id, full_name, birth_date, gender, address, phone, job)
values (1, N'Phạm Châu Long', '1997-8-7', 1, N'Hà Nội', '0943394172', N'Sinh viên');

/* ROLE */
INSERT INTO role
(name, description, enabled)
VALUES ('ROLE_ADMIN', N'Quản trị viên', 1),
       ('ROLE_USER', N'Người dùng', 1),
       ('ROLE_MANAGER', N'Quản lý chung', 1),
       ('ROLE_POSTER', N'Quản lý sự kiện, bài viết', 1);

INSERT INTO user_role (user_id, role_id)
VALUES (1, 1);

/* Chức năng */
insert into app_function
(id, name, url, icon, parent_id)
values ('AccountInfo', N'Tài khoản cá nhân', null, 'account_box', null),
       ('Profile', N'Thông tin cá nhân', '/admin/info/profile', 'keyboard_arrow_right', 'AccountInfo'),
       ('ChangePassword', N'Đổi mật khẩu', '/admin/info/change-password', 'keyboard_arrow_right', 'AccountInfo'),

       ('SystemManagement', N'Quản lý hệ thống', null, 'settings', null),
       ('FunctionList', N'Danh sách chức năng', '/admin/system/function', 'keyboard_arrow_right', 'SystemManagement'),
       ('RoleList', N'Danh sách quyền', '/admin/system/role', 'keyboard_arrow_right', 'SystemManagement'),

       ('UserManagement', N'Quản lý người dùng', null, 'group', null),
       ('UserList', N'Danh sách người dùng', '/admin/account', 'keyboard_arrow_right', 'UserManagement'),
       ('ContactList', N'Liên hệ người dùng', '/admin/account/contact', 'keyboard_arrow_right',
        'UserManagement'),

       ('CourseManagement', N'Quản lý khóa học', null, 'book', null),
       ('CourseList', N'Danh sách khóa học', '/admin/course', 'keyboard_arrow_right', 'CourseManagement'),
       ('CourseTypeList', N'Thể loại khóa học', '/admin/course/type', 'keyboard_arrow_right',
        'CourseManagement'),
       ('ClassList', N'Danh sách lớp học', '/admin/course/class', 'keyboard_arrow_right', 'CourseManagement'),

       ('EventManagement', N'Quản lý sự kiện', null, 'event', null),
       ('PostList', N'Danh sách bài viết', '/admin/event/post', 'keyboard_arrow_right', 'EventManagement'),
       ('PostTypeList', N'Thể loại bài viết', '/admin/event/post/type', 'keyboard_arrow_right',
        'EventManagement'),
       ('AdList', N'Danh sách quảng cáo', '/admin/event/ad', 'keyboard_arrow_right', 'EventManagement'),
       ('ThemeManagement', N'Chỉnh sửa giao diện', '/admin/event/theme', 'keyboard_arrow_right', 'EventManagement'),

       ('MediaManagement', N'Quản lý media', '/admin/media', 'image', null),
       ('PhotoList', N'Danh sách hình ảnh', '/admin/media/photo', 'keyboard_arrow_right', 'MediaManagement'),
       ('VideoList', N'Danh sách video', '/admin/media/video', 'keyboard_arrow_right', 'MediaManagement');

/* ROLE FUNCTION */
insert into role_function
(role_id, function_id, can_view, can_create, can_update, can_delete)
values
(1, 'AccountInfo', 1, 1, 1, 1),
(1, 'SystemManagement', 1, 1, 1, 1),
(1, 'UserManagement', 1, 1, 1, 1),
(1, 'CourseManagement', 1, 1, 1, 1),
(1, 'EventManagement', 1, 1, 1, 1),
(1, 'MediaManagement', 1, 1, 1, 1),
(1, 'Profile', 1, 1, 1, 1),
(1, 'ChangePassword', 1, 1, 1, 1),
(1, 'RoleList', 1, 1, 1, 1),
(1, 'FunctionList', 1, 1, 1, 1),
(1, 'ThemeManagement', 1, 1, 1, 1),
(1, 'CourseTypeList', 1, 1, 1, 1),
(1, 'CourseList', 1, 1, 1, 1),
(1, 'ClassList', 1, 1, 1, 1),
(1, 'PostTypeList', 1, 1, 1, 1),
(1, 'PostList', 1, 1, 1, 1),
(1, 'AdList', 1, 1, 1, 1),
(1, 'ContactList', 1, 1, 1, 1),
(1, 'UserList', 1, 1, 1, 1),
(1, 'PhotoList', 1, 1, 1, 1),
(1, 'VideoList', 1, 1, 1, 1);

insert into role_function
(role_id, function_id, can_view, can_create, can_update, can_delete)
values
(2, 'AccountInfo', 0,0,0,0),
(2, 'SystemManagement', 0,0,0,0),
(2, 'UserManagement', 0,0,0,0),
(2, 'CourseManagement', 0,0,0,01),
(2, 'EventManagement', 0,0,0,0),
(2, 'MediaManagement', 0,0,0,0),
(2, 'Profile', 0,0,0,0),
(2, 'ChangePassword', 0,0,0,0),
(2, 'RoleList', 0,0,0,0),
(2, 'FunctionList',0 ,0,0,0),
(2, 'ThemeManagement', 0,0,0,0),
(2, 'CourseTypeList',0,0,0,0),
(2, 'CourseList', 0,0,0,0),
(2, 'ClassList', 0,0,0,0),
(2, 'PostTypeList', 0,0,0,0),
(2, 'PostList', 0,0,0,0),
(2, 'AdList', 0,0,0,0),
(2, 'ContactList', 0,0,0,0),
(2, 'UserList', 0,0,0,0),
(2, 'PhotoList', 0,0,0,0),
(2, 'VideoList', 0,0,0,0);

insert into role_function
(role_id, function_id, can_view, can_create, can_update, can_delete)
values
(3, 'AccountInfo', 0,0,0,0),
(3, 'SystemManagement', 0,0,0,0),
(3, 'UserManagement', 0,0,0,0),
(3, 'CourseManagement', 0,0,0,01),
(3, 'EventManagement', 0,0,0,0),
(3, 'MediaManagement', 0,0,0,0),
(3, 'Profile', 0,0,0,0),
(3, 'ChangePassword', 0,0,0,0),
(3, 'RoleList', 0,0,0,0),
(3, 'FunctionList',0 ,0,0,0),
(3, 'ThemeManagement', 0,0,0,0),
(3, 'CourseTypeList',0,0,0,0),
(3, 'CourseList', 0,0,0,0),
(3, 'ClassList', 0,0,0,0),
(3, 'PostTypeList', 0,0,0,0),
(3, 'PostList', 0,0,0,0),
(3, 'AdList', 0,0,0,0),
(3, 'ContactList', 0,0,0,0),
(3, 'UserList', 0,0,0,0),
(3, 'PhotoList', 0,0,0,0),
(3, 'VideoList', 0,0,0,0);

insert into role_function
(role_id, function_id, can_view, can_create, can_update, can_delete)
values
(4, 'AccountInfo', 0,0,0,0),
(4, 'SystemManagement', 0,0,0,0),
(4, 'UserManagement', 0,0,0,0),
(4, 'CourseManagement', 0,0,0,01),
(4, 'EventManagement', 0,0,0,0),
(4, 'MediaManagement', 0,0,0,0),
(4, 'Profile', 0,0,0,0),
(4, 'ChangePassword', 0,0,0,0),
(4, 'RoleList', 0,0,0,0),
(4, 'FunctionList',0 ,0,0,0),
(4, 'ThemeManagement', 0,0,0,0),
(4, 'CourseTypeList',0,0,0,0),
(4, 'CourseList', 0,0,0,0),
(4, 'ClassList', 0,0,0,0),
(4, 'PostTypeList', 0,0,0,0),
(4, 'PostList', 0,0,0,0),
(4, 'AdList', 0,0,0,0),
(4, 'ContactList', 0,0,0,0),
(4, 'UserList', 0,0,0,0),
(4, 'PhotoList', 0,0,0,0),
(4, 'VideoList', 0,0,0,0);