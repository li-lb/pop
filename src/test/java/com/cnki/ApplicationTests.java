package com.cnki;

import com.cnki.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {
    @Test
    public void test01() {
        /**
         * 测试生成一个token
         */
        String sign = JwtUtil.sign("18888888888");
        System.out.println("测试生成一个token\n"+sign);
    }

}
