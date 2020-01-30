import com.mingrn.itumate.system.SystemApplication;
import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SystemApplication.class)
public class Swagger2MarkupTests {

    private static final String API_URL = "http://localhost:8002/v2/api-docs";


    /**
     * 生成 AsciiDocs 格式文档
     */
    @Test
    public void generateAsciiDocs() throws MalformedURLException {
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL(API_URL))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("target/docs/AsciiDocs/generated"));
    }

    /**
     * 生成 Markdown 格式文档
     */
    @Test
    public void generateMarkdownDocs() throws Exception {
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL(API_URL))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("target/docs/Markdown/generated"));
    }

    /**
     * 生成 Confluence 格式文档
     */
    @Test
    public void generateConfluenceDocs() throws Exception {
        //    输出Confluence使用的格式
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.CONFLUENCE_MARKUP)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL(API_URL))
                .withConfig(config)
                .build()
                .toFolder(Paths.get("target/docs/Confluence/generated"));
    }

    /**
     * 生成 AsciiDocs 格式文档,并汇总成一个文件
     */
    @Test
    public void generateAsciiDocsToFile() throws Exception {
        //    输出Ascii到单文件
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL(API_URL))
                .withConfig(config)
                .build()
                .toFile(Paths.get("target/docs/AsciiDocs/generated/all"));
    }

    /**
     * 生成 Markdown 格式文档,并汇总成一个文件
     */
    @Test
    public void generateMarkdownDocsToFile() throws Exception {
        //    输出Markdown到单文件
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                .withOutputLanguage(Language.ZH)
                .withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL(API_URL))
                .withConfig(config)
                .build()
                .toFile(Paths.get("target/docs/Markdown/generated/all"));
    }
}
