package com.hana4.demo1;

import jakarta.persistence.EntityManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class InitialSchemaLoader implements ApplicationRunner {
    private final EntityManager em;

    public InitialSchemaLoader(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        String dropSql = "drop function if exists f_xxx";
        em.createNativeQuery(dropSql).executeUpdate();

        String createSql = """
                CREATE FUNCTION if not exists `f_xxx`(_id int) RETURNS varchar(62)
                BEGIN
                    declare v_ret varchar(62) default '';
                    
                    select concat(e.ename,'(', ifnull(d.dname,'소속없음'),')'
                    into b_ret
                    fromEmp e
                        left join Dept d on e.dept = d.id
                    where e.id = _id;
                    
                    RETURN v_ret;
                END
                """;
        em.createNativeQuery(createSql).executeUpdate();
    }
}
