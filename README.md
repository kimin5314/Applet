# 乡村闲置资源

## 目录

1. [开发流程](#开发流程)
    1. [需求分析](#1-需求分析)
        1. [必要需求](#必要需求)
        2. [重要需求](#重要需求)
        3. [期望需求](#期望需求)
        4. [非功能需求](#非功能需求)
        5. [用户需求调查](#用户需求调查)
    2. [系统分析与设计](#2-系统分析与设计)
        1. [功能模块划分](#功能模块划分)
        2. [页面设计](#页面设计)
            - [首页](#首页)
            - [注册/登录页](#注册登录页)
            - [资源发布页](#资源发布页)
            - [资源列表页](#资源列表页)
            - [资源详情页](#资源详情页)
            - [搜索页](#搜索页)
            - [个人主页](#个人主页)
            - [收藏页](#收藏页)
            - [设置页](#设置页)
            - [浏览历史页](#浏览历史页)
        3. [数据库设计](#数据库设计)
            - [user(用户表, 用户基本信息)](#user用户表-用户基本信息)
            - [resource(资源表, 资源基本信息)](#resource资源表-资源基本信息)
            - [media(媒体表, 资源拓展信息如图片视频等)](#media媒体表-资源拓展信息如图片视频等)
            - [log(日志表, 记录用户操作日志)](#log日志表-记录用户操作日志)
            - [comment(评论表, 记录用户对资源的评价)](#comment评论表-记录用户对资源的评价)
            - [favorite(收藏表, 记录用户收藏的资源)](#favorite收藏表-记录用户收藏的资源)
            - [history(浏览历史表, 记录用户浏览的资源)](#history浏览历史表-记录用户浏览的资源)
            - [message(消息表, 记录用户消息)](#message消息表-记录用户消息)
        4. [技术架构设计](#技术架构设计)
            - [整体架构](#整体架构)
            - [系统组件](#系统组件)
            - [交互模式](#交互模式)
            - [安全性](#安全性)
            - [开发和部署工具](#开发和部署工具)
    3. [技术选型](#3-技术选型)
    4. [接口设计](#4-接口设计)
        - [用户相关](#用户相关)
        - [资源相关](#资源相关)
        - [文件相关](#文件相关)
        - [评论相关](#评论相关)
        - [IM相关](#IM相关)
    5. [开发计划](#5-开发计划)
    6. [测试](#6-测试)
    7. [部署上线](#7-部署上线)
    8. [维护迭代](#8-维护迭代)

2. [API示例](#API示例)
    1. [微信小程序登录](#微信小程序登录)
    2. [微信小程序注册](#微信小程序注册)
    3. [修改用户信息](#修改用户信息)
    4. [发布资源](#发布资源)
    5. [修改资源信息](#修改资源信息)
    6. [删除资源](#删除资源)
    7. [获取资源列表](#获取资源列表)
    8. [推荐资源](#推荐资源)
    9. [收藏资源](#收藏资源)
    10. [取消收藏资源](#取消收藏资源)
    11. [收藏列表](#收藏列表)
    12. [资源详情](#资源详情)
    13. [浏览历史](#浏览历史)
    14. [上传文件](#上传文件)
    15. [访问文件](#访问文件)
    16. [删除文件](#删除文件)

## 开发流程

## 1 需求分析

### 必要需求

- [ ] 出租/租赁资源
- [ ] 资源展示
- [ ] 账号系统

### 重要需求

- [ ] 资源审核
- [ ] 反馈评价
- [ ] 客服和帮助中心
- [ ] 地图定位
- [ ] 系统通知

### 期望需求

- [ ] AR/VR展示
- [ ] 社区交流
- [ ] 个性化推荐
- [ ] 资源评估
- [ ] 信用评分

### 非功能需求

- [ ] 日志记录
- [ ] 数据加密

### 用户需求调查

- [ ] TODO

## 2 系统分析与设计

### 功能模块划分

- [ ] 用户模块
- [ ] 资源模块
- [ ] 评价模块
- [ ] 日志模块

### 页面设计

#### 首页

1. 功能
    1. 资源展示
    2. 资源搜索
    3. 资源推荐
2. 页面跳转关系
    1. [搜索页](#搜索页)
    2. 热门/推荐资源[资源详情页](#资源详情页)
    3. [资源发布页](#资源发布页)

#### 注册/登录页

1. 功能
    1. 用户注册
    2. 用户登录
2. 页面跳转关系
    1. [首页](#首页)

#### 资源发布页

1. 功能
    1. 资源发布
    2. 资源编辑

#### 资源列表页

1. 功能
    1. 资源展示
    2. 资源筛选
    3. 资源排序
2. 页面跳转关系
    1. [资源详情页](#资源详情页)
    2. [资源发布页](#资源发布页)

#### 资源详情页

1. 功能
    1. 资源展示
    2. 资源评论
    3. 资源收藏
    4. 联系出租方
2. 页面跳转关系
    1. 资源所属用户[个人主页](#个人主页)

#### 搜索页

1. 功能
    1. 资源搜索
    2. 资源筛选
    3. 资源排序
2. 页面跳转关系
    1. 搜索结果[资源列表页](#资源列表页)

#### 个人主页

1. 功能
    1. 用户信息
    2. 用户资源
    3. 用户收藏
    4. 浏览历史
    5. 用户评价
    6. 用户设置
2. 页面跳转关系
    1. 已发布/编辑中资源[资源详情页](#资源详情页)
    2. [资源发布页](#资源发布页)
    3. [收藏页](#收藏页)
    4. [浏览历史页](#浏览历史页)
    5. [设置页](#设置页)

#### 收藏页

1. 功能
    1. 查看收藏
    2. 取消收藏
2. 页面跳转关系
    1. [资源详情页](#资源详情页)

#### 设置页

1. 功能
    1. 修改用户信息
    2. 其他设置

#### 浏览历史页

1. 功能
    1. 查看浏览历史
    2. 管理浏览历史
2. 页面跳转关系
    1. [资源详情页](#资源详情页)

### 数据库设计

#### user(用户表, 用户基本信息)

```sql
create table user
(
    id         int auto_increment
        primary key,
    openid     varchar(255) unique null,
    unionid    varchar(255) null,
    phone      char(11) null,
    name       varchar(255) null,
    avatar     varchar(255) null,
    role       varchar(255) null,
    created_at datetime default CURRENT_TIMESTAMP null,
    updated_at datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint user_pk 
        unique (phone)
);
```

#### resource(资源表, 资源基本信息)

```sql
create table resource
(
    id          int auto_increment
        primary key,
    name        varchar(255) null,
    type        varchar(255) null,
    duration    varchar(255) null,
    user_id     int null,
    description text null,
    price       decimal(10, 2) null,
    view        int null,
    created_at  datetime default CURRENT_TIMESTAMP null,
    updated_at  datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    constraint resource_pk
        unique (name, user_id),
    constraint resource_ibfk_1
        foreign key (user_id) references user (id)
);

create index user_id
    on resource (user_id);
```

#### media(媒体表, 资源拓展信息如图片视频等)

```sql
create table media
(
    id          int auto_increment
        primary key,
    resource_id int null,
    url         varchar(255) null,
    created_at  datetime default CURRENT_TIMESTAMP null,
    constraint media_ibfk_1
        foreign key (resource_id) references resource (id)
);

create index resource_id
    on media (resource_id);
```

#### log(日志表, 记录用户操作日志)

```sql
create table log
(
    id          int auto_increment
        primary key,
    user_id     int null,
    resource_id int null,
    action      varchar(255) null,
    created_at  datetime default CURRENT_TIMESTAMP null,
    constraint log_ibfk_1
        foreign key (user_id) references user (id),
    constraint log_ibfk_2
        foreign key (resource_id) references resource (id)
);

create index resource_id
    on log (resource_id);

create index user_id
    on log (user_id);
```

#### comment(评论表, 记录用户对资源的评价)

```sql
create table comment
(
    id          int auto_increment
        primary key,
    user_id     int null,
    resource_id int null,
    parent_id   int null,
    content     text null,
    created_at  datetime default CURRENT_TIMESTAMP null,
    constraint comment_ibfk_1
        foreign key (user_id) references user (id),
    constraint comment_ibfk_2
        foreign key (resource_id) references resource (id)
);

create index resource_id
    on comment (resource_id);

create index user_id
    on comment (user_id);
```

#### favorite(收藏表, 记录用户收藏的资源)

```sql
create table favorite
(
    id          int auto_increment
        primary key,
    user_id     int null,
    resource_id int null,
    created_at  datetime default CURRENT_TIMESTAMP null,
    constraint favorite_ibfk_1
        foreign key (user_id) references user (id),
    constraint favorite_ibfk_2
        foreign key (resource_id) references resource (id)
);
```

#### history(浏览历史表, 记录用户浏览的资源)

```sql
create table history
(
    id          int auto_increment
        primary key,
    user_id     int null,
    resource_id int null,
    created_at  datetime default CURRENT_TIMESTAMP null,
    constraint history_ibfk_1
        foreign key (user_id) references user (id),
    constraint history_ibfk_2
        foreign key (resource_id) references resource (id)
);
```

#### message(消息表, 记录用户消息)

```sql
create table message
(
    id          int auto_increment
        primary key,
    sender_id   int null,
    receiver_id int null,
    content     text null,
    created_at  datetime default CURRENT_TIMESTAMP null,
    constraint message_user_id_fk
        foreign key (sender_id) references user (id),
    constraint message_user_id_fk_2
        foreign key (receiver_id) references user (id)
);
```

### 技术架构设计

#### 整体架构

单体架构+前后端分离

#### 系统组件

- 用户界面层
- 业务逻辑层
- 数据访问层

#### 交互模式

- RESTful API
- json数据交互

#### 安全性

- 数据加密
- 用户认证

## 3 技术选型

### 前端

- uni-app
- Vue.js

### 后端

- Java Spring Boot

### 数据库

- MySQL

### 服务器

- Ubuntu 22.04 LTS Server

### 安全

- HTTPS
- MD5

### 开发和部署工具

- Git
- Docker

## 4 接口设计

### 用户相关

1. 微信小程序登录
    - URL: /user/wxlogin
    - Method: POST
    - Request:
        - code: 小程序登录code
    - Response:
        - data:
            - openid: 微信openid
            - unionid: 微信unionid (如果用户已授权)
            - user: 用户信息 (如果已注册)
            - isNewUser: 是否新用户

2. 微信小程序注册
    - URL: /user/register
    - Method: POST
    - Request:
        - code: 小程序登录code
        - user: 用户信息对象
    - Response:
        - data: 用户信息
            - id: 用户ID
            - openid: 微信openid
            - unionid: 微信unionid (如果有)
            - name: 用户名
            - avatar: 头像(url)
            - role: 角色

3. 更新用户信息
    - URL: /user/update/{id}
    - Method: PUT
    - Request:
        - id: 用户ID
        - user: 用户信息对象 (包含要更新的字段)
    - Response:
        - data: 更新后的用户信息

4. 获取用户信息
    - URL: /user/info/{openid}
    - Method: GET
    - Request:
        - openid: 微信openid
    - Response:
        - data: 用户信息

5. 注册/登录[示例](#注册登录)
    - URL: /user/login
    - Method: POST
    - Request:
        - phone: 手机号
        - role: 角色
    - Response:
        - data:
            - id: 用户ID
            - phone: 手机号
            - name: 用户名
            - avatar: 头像(url)
            - role: 角色

6. 更新用户信息[示例](#修改用户信息)
    - URL: /user/update/{id}
    - Method: PUT
    - Request:
        - id: 用户ID
        - phone: 手机号
        - name: 用户名
        - avatar: 头像(通过Media接口上传)
        - role: 角色
    - Response:
        - data: 用户信息
            - id: 用户ID
            - phone: 手机号
            - name: 用户名
            - avatar: 头像(url)
            - role: 角色

### 资源相关

1. 获取用户发布的资源列表[示例](#获取资源列表)
    - URL: /resource/list/{userid}
    - Method: GET
    - Request:
        - userId: 用户ID
    - Response:
        - data: 资源列表
            - item: 资源信息
                - id: 资源ID
                - name: 资源名称
                - type: 资源类型
                - user: 发布者
                - cover: 封面图片url
                - description: 描述
                - price: 价格
                - view: 浏览量
                - duration: 租赁时长

2. 发布资源[示例](#发布资源)
    - URL: /resource/publish
    - Method: POST
    - Request:
        - userId: 发布者ID
        - name: 资源名称
        - type: 资源类型
        - description: 描述
        - address: 地址
            - latitude: 纬度
            - longitude: 经度
        - price: 价格
        - view: 浏览量
        - duration: 租赁时长
        - decoration: 装修情况
        - equipment: 周边设备
        - _media: 媒体资源通过Media接口上传_
    - Response:
        - data: 资源信息
            - id: 资源ID
            - name: 资源名称
            - type: 资源类型
            - userId: 发布者ID
            - cover: 封面图片url
            - description: 描述
            - address: 地址
                - latitude: 纬度
                - longitude: 经度
            - price: 价格
            - view: 浏览量
            - decoration: 装修情况
            - equipment: 周边设备
            - duration: 租赁时长

3. 修改资源信息[示例](#修改资源信息)
    - URL: /resource/update/{id}
    - Method: PUT
    - Request: id必须, 剩余参数可选
        - id: 资源ID
        - name: 资源名称
        - type: 资源类型
        - description: 描述
        - price: 价格
        - view: 浏览量
        - duration: 租赁时长
        - decoration: 装修情况
        - equipment: 周边设备
        - _media: 媒体资源通过Media接口上传_
    - Response:
        - data: 资源信息
            - id: 资源ID
            - name: 资源名称
            - type: 资源类型
            - user: 发布者
            - cover: 封面图片url
            - description: 描述
            - price: 价格
            - view: 浏览量
            - duration: 租赁时长
            - decoration: 装修情况
            - equipment: 周边设备

4. 删除资源[示例](#删除资源)
    - URL: /resource/delete/{id}
    - Method: DELETE
    - Request: id必须
        - id: 资源ID

5. 推荐资源[示例](#推荐资源)
    - URL: /resource/recommend
    - Method: GET
    - Response:
        - data: 资源列表
            - item: 资源信息
                - id: 资源ID
                - name: 资源名称
                - type: 资源类型
                - user: 发布者
                - cover: 封面图片url
                - description: 描述
                - price: 价格
                - view: 浏览量
                - duration: 租赁时长

6. 收藏资源[示例](#收藏资源)
    - URL: /resource/favorite/{userId}/{resourceId}
    - Method: POST
    - Request:
        - userId: 用户ID
        - resourceId: 资源ID

7. 取消收藏资源[示例](#取消收藏资源)
    - URL: /resource/unfavorite/{userId}/{resourceId}
    - Method: DELETE
    - Request:
        - userId: 用户ID
        - resourceId: 资源ID

8. 收藏列表[示例](#收藏列表)
    - URL: /resource/favoriteList/{userId}
    - Method: GET
    - Request:
        - userId: 用户ID
    - Response:
        - data: 收藏列表
            - item: 收藏资源信息
                - id: 资源ID
                - name: 资源名称
                - type: 资源类型
                - user: 发布者
                - cover: 封面图片url
                - description: 描述
                - price: 价格
                - view: 浏览量
                - duration: 租赁时长
9. 资源详情[示例](#资源详情)
    - URL: /resource/detail/{userId}/{resourceId}
    - Method: GET
    - Request:
        - resourceId: 资源ID
        - userId: 用户ID
    - Response:
        - data: 资源信息
            - id: 资源ID
            - name: 资源名称
            - type: 资源类型
            - user: 发布者
            - cover: 封面图片url
            - description: 描述
            - price: 价格
            - view: 浏览量
            - duration: 租赁时长
            - mediaUrl: 媒体url列表
                - item: 媒体url
                    - url: url

10. 浏览历史[示例](#浏览历史)
    - URL: /resource/history/{userId}
    - Method: GET
    - Request:
        - userId: 用户ID
    - Response:
        - data: 浏览历史列表 按时间降序
            - item: 浏览资源信息
                - id: 资源ID
                - name: 资源名称
                - type: 资源类型
                - user: 发布者
                - cover: 封面图片url
                - description: 描述
                - price: 价格
                - view: 浏览量
                - duration: 租赁时长

11. 资源搜索[示例](#资源搜索)
    - URL: /resource/search
    - Method: GET
    - Request:
        - keyword: 关键词
    - Response:
        - data: 资源列表
            - item: 资源信息
                - id: 资源ID
                - name: 资源名称
                - type: 资源类型
                - user: 发布者
                - cover: 封面图片url
                - description: 描述
                - price: 价格
                - view: 浏览量
                - duration: 租赁时长

12. 搜索列表补全[示例](#搜索列表补全)
    - URL: /resource/search/autoCompleteList
    - Method: GET
    - Request:
        - keyword: 关键词
    - Response:
        - data: 推荐关键词列表
            - item: 关键词

13. 资源简要信息[示例](#资源简要信息)
    - URL: /resource/get/{resourceId}
    - Method: GET
    - Request:
        - resourceId: 资源ID
    - Response:
        - data: 资源信息
            - id: 资源ID
            - name: 资源名称
            - type: 资源类型
            - user: 发布者
            - cover: 封面图片url
            - description: 描述
            - price: 价格
            - view: 浏览量
            - duration: 租赁时长
            - equipment: 周边设备
            - decoration: 装修情况

14. 获取未审核资源列表[示例](#获取未审核资源列表)
    - URL: /resource/toCheck
    - Method: GET
    - Response:
        - data: 资源列表
            - item: 资源信息
                - id: 资源ID
                - name: 资源名称
                - type: 资源类型
                - user: 发布者
                - cover: 封面图片url
                - description: 描述
                - price: 价格
                - view: 浏览量
                - duration: 租赁时长
                - created_at: 发布时间
                - equipment: 周边设备
                - decoration: 装修情况

15. 审核资源[示例](#审核资源)
    - URL: /resource/check/{id}/{status}
    - Method: POST
    - Request:
        - id: 资源ID
        - status: 审核状态("unchecked", "checked", "rejected")

### 文件相关

1. 上传文件[示例](#上传文件)
    - URL: /media/upload/{resourceId}
    - Method: POST
    - Request:
        - file: 文件
        - resourceId: 资源ID(上传用户头像时resourceId设置为avatar)

2. 访问文件[示例](#访问文件)
    - URL: /media/{prefixPath:.+}/{filename:.+}
    - Method: GET
    - Request:
        - prefixPath: 文件路径(头像为avatar, 资源图片/视频为对应资源id)
        - filename: 文件名

3. 删除文件[示例](#删除文件)
    - URL: /media/delete/{prefixPath:.+}/{filename:.+}
    - Method: DELETE
    - Request:
        - prefixPath: 文件路径(头像为avatar, 资源图片/视频为对应资源id)
        - filename: 文件名

### 评论相关

1. 发布评论[示例](#发布评论)
    - URL: /comment/publish
    - Method: POST
    - Request:
        - userId: 用户ID
        - resourceId: 资源ID
        - content: 评论内容
        - parentId: 父评论ID(回复评论时使用)

2. 删除评论[示例](#删除评论)
    - URL: /comment/delete/{id}
    - Method: DELETE
    - Request:
        - id: 评论ID

3. 获取评论列表[示例](#获取评论列表)
    - URL: /comment/get/{resourceId}
    - Method: GET
    - Request:
        - resourceId: 资源ID
    - Response:
        - data: 评论列表
            - item: 评论信息
                - id: 评论ID
                - user: 评论者
                - content: 评论内容
                - created_at: 评论时间
                - children: 子评论列表
                    - item: 子评论信息
                        - id: 评论ID
                        - user: 评论者
                        - content: 评论内容
                        - created_at: 评论时间

### IM相关

1. 发送消息[示例](#发送消息)
2. 获取消息记录[示例](#获取消息记录)
    - URL: /message/dialogue/{receiverId}/{senderId}
    - Method: GET
    - Request:
        - receiverId: 接收者ID
        - senderId: 发送者ID
    - Response:
        - data: 消息列表
            - item: 消息信息
                - senderId: 发送者
                - receiverId: 接收者
                - content: 消息内容
                - time: 消息时间

## 5 开发计划

## 6 测试

## 7 部署上线

## 8 维护迭代

## API示例

### 微信小程序登录

```javascript
// 微信小程序登录
const data = {
    code: 'wx_code_from_login'  // 通过wx.login()获取的临时登录凭证
}
fetch("http://kimin.cn:8080/user/wxlogin", {
    method: "POST",
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
})
.then(response => response.json())
.then(result => {
    // result包含：
    // - openid：用户的微信openid
    // - unionid：用户的微信unionid（如果用户已授权）
    // - user：用户信息（如果已注册）
    // - isNewUser：是否新用户
})
```

### 微信小程序注册

```javascript
// 微信小程序注册/更新用户信息
const data = {
    code: 'wx_code_from_login',  // 通过wx.login()获取的临时登录凭证
    user: {
        name: "张三",
        avatar: "avatar_url",
        role: "user"  // 可选，默认为"user"
    }
}
fetch("http://kimin.cn:8080/user/register", {
    method: "POST",
    headers: {
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
})
```

### 修改用户信息

```javascript
const data = {
    name: "张三",
}
fetch("http://kimin.cn:8080/user/update/1", {
    method: "PUT",
    body: JSON.stringify({data})
})
```

### 发布资源

```javascript
const data = {
    name: "资源1",
    type: "类型1",
    description: "描述1",
    address: {
        latitude: 9.999,
        longitude: 1.111,
    },
    price: "80-100",
    decoration: "精装",
    equipment: "学校;超市",
    duration: "1w", // 一周
}
fetch("http://kimin.cn:8080/resource/publish", {
    method: "POST",
    body: JSON.stringify({data})
})
```

### 修改资源信息

```javascript
const data = {
    id: 1,
    name: "资源2",
    type: "类型2",
    description: "描述1",
    view: 1,
    price: 1000,
    address: {
        latitude: 9.999,
        longitude: 1.111,
    },
    decoration: "精装",
    equipment: "学校;超市",
    duration: "1m", // 一月
}
fetch("http://kimin.cn:8080/resource/update/1", {
    method: "PUT",
    body: JSON.stringify({data})
})
```

### 删除资源

```javascript
fetch("http://kimin.cn:8080/resource/delete/1", {
    method: "POST",
})
```

### 获取用户发布的资源列表

```javascript
fetch("http://kimin.cn:8080/resource/list/1", {
    method: "GET",
})
```

### 推荐资源

```javascript
fetch("http://kimin.cn:8080/resource/recommend", {
    method: "GET",
})
```

### 收藏资源

```javascript
fetch("http://kimin.cn:8080/resource/favorite/1", {
    method: "POST",
    body: JSON.stringify()
})
```

### 取消收藏资源

```javascript
fetch("http://kimin.cn:8080/resource/unfavorite/1", {
    method: "POST",
    body: JSON.stringify()
})
```

### 收藏列表

```javascript
fetch("http://kimin.cn:8080/resource/favorite/list/1", {
    method: "GET",
})
```

### 资源详情

```javascript
fetch("http://kimin.cn:8080/resource/detail/1/5", {
    method: "GET",
})
```

### 浏览历史

```javascript
fetch("http://kimin.cn:8080/resource/history/1", {
    method: "GET",
})
```

### 资源搜索

```javascript
fetch("http://kimin.cn:8080/resource/search?keyword=1", {
    method: "GET",
})
```

### 搜索列表补全

```javascript
fetch("http://kimin.cn:8080/resource/search/autoCompleteList?keyword=resource", {
    method: "GET",
})
```

### 资源简要信息

```javascript
fetch("http://kimin.cn:8080/resource/get/1", {
    method: "GET",
})
```

### 获取未审核资源列表

```javascript
fetch("http://kimin.cn:8080/resource/toCheck", {
    method: "GET",
})
```

### 审核资源

```javascript
fetch("http://kimin.cn:8080/resource/check/1/checked", {
    method: "POST",
})
```

### 上传文件

```javascript
const data = {
    file: null, //通过input["file"].files[0]获取
}
fetch("http://kimin.cn:8080/media/upload/1", {
    method: "POST",
    body: JSON.stringify({data})
})
```

### 访问文件

```html
<img src="http://kimin.cn:8080/media/avatar/default.png" alt="avatar">
```

### 删除文件

```javascript
fetch("http://kimin.cn:8080/media/delete/1/1.png", {
    method: "POST",
})
```

### 发布评论

```javascript
const data = {
    userId: 1,
    resourceId: 1,
    content: "评论内容",
}
fetch("http://kimin.cn:8080/comment/publish", {
    method: "POST",
    body: JSON.stringify({data})
})
```

### 回复评论

```javascript
const data = {
    resourceId: 1,
    content: "评论内容",
    parentId: 1,
}
fetch("http://kimin.cn:8080/comment/publish", {
    method: "POST",
    body: JSON.stringify({data})
})
```

### 删除评论

```javascript
fetch("http://kimin.cn:8080/comment/delete/1", {
    method: "POST",
})
```

### 获取评论列表

```javascript
fetch("http://kimin.cn:8080/comment/get/1", {
    method: "GET",
})
```

### 发送消息

```javascript
// 补充获取id的具体逻辑
var userId = 1;
var receiverId = 2;
var ws = new WebSocket("ws://kimin.cn:8080/message?userId=" + userId);
ws.onmessage = function (event) {
    // 向聊天框添加消息
    var chat = document.getElementById('chat');
    var message = document.createElement('div');
    // event.data为消息内容
    message.textContent = event.data;
    chat.appendChild(message);
}

function sendMessage() {
    var input = document.getElementById('message');
    // 格式为receiverId:message
    var msg = receiverId + ":" + input.value;
    ws.send(msg);
    input.value = '';
}
```

### 获取消息记录

```javascript
fetch("http://kimin.cn:8080/message/dialogue/1/2", {
    method: "GET",
})
```
