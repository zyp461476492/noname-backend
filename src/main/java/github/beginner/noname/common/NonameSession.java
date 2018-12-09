package github.beginner.noname.common;

import github.beginner.noname.utils.LangUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import static java.lang.Boolean.FALSE;
import static java.time.LocalDateTime.now;

/**
 * @author wpt
 * @version 1.0
 * .
 * .                 .-~~~~~~~~~-._       _.-~~~~~~~~~-.
 * .             __.'              ~.   .~              `.__
 * .           .'//                  \./                  \\`.
 * .         .'//                     |                     \\`.
 * .       .'// .-~"""""""~~~~-._     |     _,-~~~~"""""""~-. \\`.
 * .     .'//.-"                 `-.  |  .-'                 "-.\\`.
 * .   .'//______.============-..   \ | /   ..-============.______\\`.
 * . .'______________________________\|/______________________________`.
 * .                    高山仰止,景行行止.虽不能至,心向往之
 * @date 18/12/9 22:48
 */
@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NonameSession implements Serializable {

    private static final long DEFAULT_TIMEOUT_MILS = 30 * 60 * 1000;
    @Builder.Default
    private String sessionId = LangUtils.RANDOM.uuid();
    private String host;
    @Builder.Default
    private LocalDateTime startTimestamp = now();
    @Builder.Default
    private LocalDateTime lastAccessTime = now();
    @Builder.Default
    private long timeout = DEFAULT_TIMEOUT_MILS;
    @Builder.Default
    private LocalDateTime expireTimestamp = now().plusSeconds(DEFAULT_TIMEOUT_MILS / 1000);
    @Builder.Default
    private Boolean expired = FALSE;

    private static HttpServletRequest getUserUrlRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes != null ? requestAttributes.getRequest() : null;
    }

    /**
     * 更新session
     */
    public void updateSession() {
        String info = "update session from id:[{}], at [{}] by url: [{}] ";
        log.info(info, this.getSessionId(), now(), Objects.requireNonNull(getUserUrlRequest()).getRequestURL());
        this.lastAccessTime = now();
        this.expireTimestamp = lastAccessTime.plusSeconds(getTimeout() / 1000);
    }

    /**
     * 注销session,把session变为过期状态
     */
    public void destorySession() {
        String info = "destroy session from id [{}], at [{}]";
        log.info(info, this.getSessionId(), now());
        this.expired = true;
    }

    /**
     * 判断session状态
     *
     * @return expire
     */
    public boolean isExpired() {
        if (this.expired) {
            return true;
        }
        long timeout = getTimeout();
        if (timeout >= 0) {
            LocalDateTime lastAccessTime = getLastAccessTime();
            if (lastAccessTime == null) {
                throw new IllegalStateException("最后访问时间为空");
            }
            LocalDateTime expire = getExpireTimestamp();
            boolean isExpire = now().isAfter(lastAccessTime.plusSeconds(timeout / 1000));
            log.info("当前时间：{}, 最后访问时间： {}, 过期时间：{}, session 是否过期： {}", now(), lastAccessTime, expire, isExpire);
            if (isExpire) {
                this.expired = true;
            }
            return isExpire;
        }
        this.expired = true;
        return true;
    }
}

