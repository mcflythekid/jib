package mc;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Log4j2
public final class McThread {

    public static void safeSleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            log.error("Error when sleep", e);
        }
    }
}
