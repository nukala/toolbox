package org.ravi.mbg;

import com.google.common.collect.Lists;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MybatisGenerator {
	private List<String> tableNames;
	private List<String> warnings;
	private boolean overwrite;

	public static void main(String... args) throws Exception {
		MybatisGenerator mbg = new MybatisGenerator();

		mbg.setup();
		mbg.generate();
	}

	private void setup() {
		warnings = Lists.newArrayList();
		overwrite = true;
		tableNames = Lists.newArrayList();
		tableNames.add("special_chars_config");
	}

	private Context mkContext() {
		Context ctxt = null;
		//ctxt = new Context(ModelType.)
		return ctxt;
	}

	public void generate() throws InvalidConfigurationException, SQLException, IOException, InterruptedException {
		Configuration config = new Configuration();
		config.addContext(mkContext());

		config.validate();
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		ProgressCallback progress = new VerboseProgressCallback();
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(progress);
	}
}
