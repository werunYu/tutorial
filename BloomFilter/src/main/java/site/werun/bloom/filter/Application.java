package site.werun.bloom.filter;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author werun
 * @version 1.0
 * @date 2024/10/05 14:33
 * @description 布隆过滤器使用
 **/
@Slf4j
public class Application {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://43.142.53.20:6379");
        config.useSingleServer().setPassword("yuwenqi@320911");

        // 构建Redisson
        RedissonClient redisson = Redisson.create(config);

        RBloomFilter<Object> bloomFilter = redisson.getBloomFilter("filterName");
        // 初始化布隆过滤器,预计元素100000L,误差率3%
        bloomFilter.tryInit(100000L, 0.03);

        log.info("将10086插入到布隆过滤器中");
        bloomFilter.add("10086");

        // 判断下面号码是否再布隆过滤器中
        log.info("123456是否在布隆过滤器中:{}", bloomFilter.contains("123456"));
        log.info("10086是否在布隆过滤器中:{}", bloomFilter.contains("10086"));
    }
}
