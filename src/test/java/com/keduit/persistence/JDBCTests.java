package com.keduit.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.jasper.tagplugins.jstl.core.Catch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

//Junit=>전체 프로젝트(was)를 구동하지 않고 단위 코드 테스트를 할 수 있게 해주는 라이브러리
//spring-test: junit을 확장한 스프링의 테스트 라이브러리
//applicationcontext를 만들고 작업을 할 수 있또록 junit기능을 확장
@RunWith(SpringJUnit4ClassRunner.class)
//스프링빈(bean) 설정 파일의 위치를 지정
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class JDBCTests {

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection() {
        try(Connection conn = 
                DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521:XE",
                        "scott",
                        "tiger")){ //트라이 리소스
        log.info(conn);
    }catch (Exception e) {
        fail(e.getMessage());
    }
    }

}