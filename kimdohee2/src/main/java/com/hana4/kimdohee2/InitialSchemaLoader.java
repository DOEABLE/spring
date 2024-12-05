package com.hana4.kimdohee2;

import jakarta.persistence.EntityManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("!test")
public class InitialSchemaLoader implements ApplicationRunner {

    private final EntityManager em;

    public InitialSchemaLoader(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSs");
        String dropSql = "drop function if exists f_xxx";
        em.createNativeQuery(dropSql).executeUpdate();

        String createSql = """
			CREATE FUNCTION if not exists `f_xxx`(_id int) RETURNS varchar(62)
			BEGIN
			    declare v_ret varchar(62) default '';
			
			    select concat(e.ename, '(', ifnull(d.dname, '소속없음'), ')')
			    into v_ret
			    from Emp e
			             left join Dept d on e.dept = d.id
			    where e.id = _id;
			
			    RETURN v_ret;
			END
			""";
        em.createNativeQuery(createSql).executeUpdate();
    }
}