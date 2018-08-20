CREATE TABLE TB4D_DEMO
(
    DEMO_ID       varchar(100)       NOT NULL PRIMARY KEY,
    IS_VIRTUAL      INT DEFAULT '0' NOT NULL,
    ORDER_NUM       INT             NOT NULL,
    STATUS         INT             NOT NULL,
    NAME_TEXT      varchar(100) NULL,
    F_DATE DATETIME NULL
);

CREATE TABLE TB4D_ORGAN
(
    ORGAN_ID        varchar(100)       NOT NULL PRIMARY KEY,
    CHILD_COUNT     INT        DEFAULT NULL,
    IS_VIRTUAL      INT DEFAULT '0' NOT NULL,
    ORDER_NUM       INT             NOT NULL,
    ORGAN_ADDRESS   varchar(300)  DEFAULT NULL,
    ORGAN_CONTACTOR varchar(100)  DEFAULT NULL,
    ORGAN_DOMAIN    varchar(300)       NOT NULL,
    ORGAN_FAX       varchar(100)  DEFAULT NULL,
    ORGAN_NAME      varchar(300)       NOT NULL,
    ORGAN_NUM       varchar(200)  DEFAULT NULL,
    ORGAN_PHONE     varchar(100)  DEFAULT NULL,
    ORGAN_POST      varchar(20)   DEFAULT NULL,
    ORGAN_TYPE      varchar(50)   DEFAULT NULL,
    PARENT_ID       varchar(100)  DEFAULT NULL,
    PARENT_ID_LIST  varchar(1200) DEFAULT NULL,
    SHORT_NAME      varchar(150)  DEFAULT NULL,
    ORGAN_STATUS          INT             NOT NULL
);

CREATE TABLE TB4D_DEMO2
(
    DEMO_ID       varchar(100)       NOT NULL PRIMARY KEY,
    IS_VIRTUAL      INT DEFAULT '0' NOT NULL,
    ORDER_NUM       INT             NOT NULL,
    STATUS         INT             NOT NULL,
    NAME_TEXT      varchar(100) NULL,
    F_DATE DATETIME NULL
);