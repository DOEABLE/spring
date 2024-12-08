CREATE TABLE IF NOT EXISTS Post (
                                      id BIGINT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                      createAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
                                      updateAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
                                      title VARCHAR(255) NOT NULL,
                                      writer VARCHAR(36) NOT NULL COMMENT '작성자 ID',
                                      body TEXT NOT NULL,
                                      CONSTRAINT post_writer_foreign FOREIGN KEY (writer) REFERENCES User (id)
);

CREATE TABLE IF NOT EXISTS User (
                                      id VARCHAR(36) NOT NULL,
                                      name VARCHAR(31) NOT NULL,
                                      email VARCHAR(255) NULL,
                                      PRIMARY KEY (id),
                                        CONSTRAINT user_name_unique UNIQUE (name)
);



CREATE TABLE IF NOT EXISTS Comment (
                                         id BIGINT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                         createAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
                                         updateAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
                                         post BIGINT unsigned NOT NULL,
                                         writer VARCHAR(36) NOT NULL COMMENT '작성자 ID',
                                         body VARCHAR(500) NOT NULL,
                                         CONSTRAINT comment_post_foreign FOREIGN KEY (post) REFERENCES Post (id),
                                            CONSTRAINT comment_writer_foreign FOREIGN KEY (writer) REFERENCES User (id)
);
alter table User modify column name VARCHAR(31) NOT NULL after id;
alter table Post modify column createAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시' after id;
alter table Post modify column writer VARCHAR(36) NOT NULL COMMENT '작성자 ID' after title;
alter table Post modify column body VARCHAR(500) NOT NULL after writer;
alter table Comment modify column createAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시' after id;
alter table Comment modify column post BIGINT unsigned NOT NULL after updateAt;