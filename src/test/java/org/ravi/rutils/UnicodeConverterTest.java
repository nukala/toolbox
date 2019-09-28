package org.ravi.rutils;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UnicodeConverterTest {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private UnicodeConverter converter = new UnicodeConverter();

    @Test
    public void convertString() {
        String raw = "  foo=Norðoya Sparikassi & Suðuroyar Sparikassi ";
        String cvt = converter.convert(raw);
        String back = converter.toRaw(cvt);
        logger.warn(String.format("%nRAW\t\t[%s]%nconverted\t[%s]%nback\t\t[%s]%n", raw, cvt, back));

        List<String> parts = Splitter.on("=").splitToList(cvt);
        assertThat(parts.size()).isEqualTo(2);
    }

    @Test
    public void convertFile() throws IOException {
        // copy buffer into Sublime, then save
        String fn = "/Users/rnukala/Desktop/bank.props";
        Path path = Paths.get(fn);

        if (!Files.exists(path)) {
            logger.warn("path=[{}] does not exist", path);
            return;
        }
        List<String> linesList = Files.readAllLines(path, Charsets.UTF_8);
        logger.warn("numLines={}", linesList.size());

        assertThat(linesList.size()).isGreaterThan(3);

        List<String> converted = Lists.newArrayListWithExpectedSize(linesList.size());
        BufferedWriter bw = Files.newBufferedWriter(Paths.get(fn.replace(".props", ".out")), Charsets.UTF_8);
        try (Formatter fmtr = new Formatter(new StringBuilder(linesList.size() * 256))) {
            for (String raw : linesList) {
                String clean = converter.convert(raw).trim().replaceAll(" += +", "=");
                converted.add(clean);
                fmtr.format("%s%n", clean);
                bw.write(String.format("%s%n", clean));
            }
            bw.close();

            logger.warn("Converted({}) \n{}", converted.size(), fmtr.toString());
        }


        assertThat(linesList.size()).isEqualTo(converted.size());
    }

    @Test
    public void integerOverflow() {
        int ival = Integer.MAX_VALUE - 1;

        for (int i = 0; i < 5; i++) {
            System.out.printf("ival=%d%n", ival);
            ival = (++ival < 0) ? 0 : ival;
        }
    }

    @Test
    public void bigDecimalTest() {
        BigDecimal sval = new BigDecimal("53.023");
        BigDecimal dval = new BigDecimal(53.023d);
        BigDecimal fval = new BigDecimal(53.023F);
        logger.info("sval=[{} or {}], dval=[{} or {}], fval=[{} or {}]", sval, scaledStr(sval), dval, scaledStr(dval), fval, scaledStr(fval));
    }

    private String scaledStr(BigDecimal sval) {
        return sval.setScale(2, BigDecimal.ROUND_UP).toString();
    }

    @Test
    public void someChinese() {
        String raw = "中国工商银行";

        String cvt = converter.convert(raw);
        String back = converter.toRaw(cvt);
        logger.warn(String.format("%nRAW\t\t[%s]%nconverted\t[%s]%nback\t\t[%s]%n", raw, cvt, back));
    }
}
