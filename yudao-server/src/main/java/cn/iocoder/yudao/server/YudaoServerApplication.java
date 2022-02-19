package cn.iocoder.yudao.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${yudao.info.base-package}
@SpringBootApplication(scanBasePackages = {"${yudao.info.base-package}.server", "${yudao.info.base-package}.module"})
public class YudaoServerApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext run = SpringApplication.run(YudaoServerApplication.class, args);
        Environment env = run.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty( "server.port" );
        String path = env.getProperty( "server.servlet.context-path" );
        path = path == null ? "" : path;
        System.out.println( "\n----------------------------------------------------------\n\t" + "Application is running! Access URLs:\n\t"
                + "Local: \t\thttp://localhost:" + port + path + "/\n\t" + "External: \thttp://" + ip + ":" + port + path + "/\n\t"
                + "swagger-ui: \thttp://" + ip + ":" + port + path + "/swagger-ui.html\n\t" + "Doc: \t\thttp://" + ip + ":" + port + path
                + "/doc.html\n" + "----------------------------------------------------------" );
    }

}
