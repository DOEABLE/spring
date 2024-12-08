-- INSERT INTO book (title, author, publisher, description, isbn, availability, borrowerId, startDate, endDate)
-- VALUES
--     ('자바 프로그래밍', '김철수', '한빛미디어', '자바 프로그래밍의 기초부터 고급까지 다루는 책입니다.', '9788979141231', 1, NULL, NULL, NULL),
--     ('스프링 부트 실전', '이영희', '위키북스', '스프링 부트를 활용한 웹 애플리케이션 개발 가이드', '9788968484562', 0, 'user123', '2024-01-01 10:00:00', '2024-01-08 10:00:00'),
--     ('데이터베이스 개론', '박민수', '교보문고', 'RDBMS의 기본 개념과 SQL 실습', '9788936-7893', 1, NULL, NULL, NULL),
--     ('알고리즘의 이해', '정지원', '한울출판사', '기본적인 알고리즘 설명과 문제 해결 전략', '9788956740124', 0, 'user456', '2024-01-03 14:30:00', '2024-01-10 14:30:00'),
--     ('클린 코드', '홍길동', '영진출판사', '더 나은 코드를 작성하기 위한 실용적인 기술들', '9788931425675', 1, NULL, NULL, NULL);
INSERT INTO book (title, author, publisher, description, isbn, availability, borrowerId, startDate, endDate)
SELECT '자바 프로그래밍', '김철수', '한빛미디어', '자바 프로그래밍의 기초부터 고급까지 다루는 책입니다.', '9788979141231', 1, NULL, NULL, NULL
    WHERE NOT EXISTS (SELECT 1 FROM book WHERE isbn = '9788979141231');

INSERT INTO book (title, author, publisher, description, isbn, availability, borrowerId, startDate, endDate)
SELECT '스프링 부트 실전', '이영희', '위키북스', '스프링 부트를 활용한 웹 애플리케이션 개발 가이드', '9788968484562', 0, 'user123', '2024-01-01 10:00:00', '2024-01-08 10:00:00'
    WHERE NOT EXISTS (SELECT 1 FROM book WHERE isbn = '9788968484562');

INSERT INTO book (title, author, publisher, description, isbn, availability, borrowerId, startDate, endDate)
SELECT '데이터베이스 개론', '박민수', '교보문고', 'RDBMS의 기본 개념과 SQL 실습', '9788936-7893', 1, NULL, NULL, NULL
    WHERE NOT EXISTS (SELECT 1 FROM book WHERE isbn = '9788936-7893');

INSERT INTO book (title, author, publisher, description, isbn, availability, borrowerId, startDate, endDate)
SELECT '알고리즘의 이해', '정지원', '한울출판사', '기본적인 알고리즘 설명과 문제 해결 전략', '9788956740124', 0, 'user456', '2024-01-03 14:30:00', '2024-01-10 14:30:00'
    WHERE NOT EXISTS (SELECT 1 FROM book WHERE isbn = '9788956740124');

INSERT INTO book (title, author, publisher, description, isbn, availability, borrowerId, startDate, endDate)
SELECT '클린 코드', '홍길동', '영진출판사', '더 나은 코드를 작성하기 위한 실용적인 기술들', '9788931425675', 1, NULL, NULL, NULL
    WHERE NOT EXISTS (SELECT 1 FROM book WHERE isbn = '9788931425675');