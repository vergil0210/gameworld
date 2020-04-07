package cn.jx.ecjtu.gw.util;

import cn.jx.ecjtu.gw.GameWorldApplication;
import cn.jx.ecjtu.gw.model.AdminModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class JwtUtil {
    private static Logger logger = LoggerFactory.getLogger(GameWorldApplication.class);
    private static final long EXPIRE_TIME= 15*60*60*1000;
    private static final String TOKEN_SECRET="ecjtuVergil";

    /**
     * 签名生成
     * @param admin
     * @return
     */
    public static String sign(AdminModel admin){
        logger.info("正在生成token...");
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("id", admin.getId()+"")
                    .withClaim("role", admin.getRole())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        logger.info("token生成完毕，即将发送给客户端");
        return token;

    }


    /**
     * 签名验证
     * @param token
     * @return
     */
    public static boolean verify(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            logger.info("token认证通过打印信息");
            logger.info("用户id: " + jwt.getClaim("id").asString());
            logger.info("用户权限: " + jwt.getClaim("role").asString());
            logger.info("过期时间：" + jwt.getExpiresAt());
            return true;
        } catch (Exception e){
            logger.warn("token认证失败,请尝试重新登陆");
            e.printStackTrace();
            return false;
        }
    }
}