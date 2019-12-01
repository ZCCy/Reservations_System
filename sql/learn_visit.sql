create table njupt_wx.learn_visit
(
    id          bigint auto_increment comment 'ID'
                primary key,
    openid           varchar(255)  default ''                not null comment '用户小程序id',
    contact_unit     varchar(255)  default ''                not null comment '联系单位',
    contactMan       varchar(255)  default ''                not null comment '联系人名字',
    contactPhone     varchar(255)  default ''                not null comment '联系人电话',
    place            varchar(255) default ''                 not null comment '参观场所',
    date             date               not null                 ,
    time             time               not null                 ,
    major             varchar(255) default ''                not null comment '来宾',
    class         varchar(255) default ''                not null comment '单位职务',
    pricpleSign   varchar(255) default ''                not null comment '个人签名',
    status          int      default 0                not null comment '是否同意'
);