package net.abjy.sms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * Oracle 代码生成
 *
 * @author lanjerry
 * @since 3.5.3
 */
public class OracleGeneratorTest extends BaseGeneratorTest {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:oracle:thin:@127.0.0.1:1521:helowin", "sms", "123456")
            .schema("SMS")
            .build();


    public void testSimple() {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        generator.strategy(strategyConfig().build());
        generator.global(globalConfig().build());
        generator.execute();
    }

    @Test
    public void testCreate()
    {
        String projectPath = System.getProperty("user.dir");



        FastAutoGenerator.create("jdbc:oracle:thin:@127.0.0.1:1521:helowin", "sms", "123456")
                .globalConfig(builder -> {
                    builder.author("liangzz") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("net.abjy.sms") // 设置父包名
                            //.moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/java/net/abjy/sms/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("SYS_PERMISSION") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .strategyConfig(strategyConfigBuilder -> strategyConfigBuilder.entityBuilder()
                        .enableTableFieldAnnotation().naming(NamingStrategy.underline_to_camel)
                        .columnNaming(NamingStrategy.underline_to_camel).idType(IdType.NONE).formatFileName("%sEntity"))


                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}