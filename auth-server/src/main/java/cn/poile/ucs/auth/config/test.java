package cn.poile.ucs.auth.config;

import cn.poile.ucs.auth.mapper.BaseUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @className: test
 * @author: yzx
 * @date: 2025/8/8 0:01
 * @Version: 1.0
 * @description:
 */
@Component
public class test {
    @Autowired
    private BaseUserMapper baseUserService;

    public static void main(String[] args) {

        new test().test();
    }
   public void test(){
//       BaseUser byId = baseUserService.selectById(1);
//       System.out.println(byId);
       String originalSecret = "yaohw"; // 原始秘钥
       String dbHash = "$2a$10$QJZpXtZf7L8G8Y7H3eY6cO3FvD6JZ9QJZpXtZf7L8G8Y7H3eY6cO"; // 数据库存储的哈希

       BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
       String encode = encoder.encode(originalSecret);
       System.out.println(encode);
       boolean matches = encoder.matches(originalSecret, dbHash);
       System.out.println("是否匹配: " + matches); // 应为 true
   }
}
