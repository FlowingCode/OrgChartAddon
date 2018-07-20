package com.flowingcode.vaadin.addons.orgchart;

import static com.flowingcode.vaadin.addons.orgchart.TemplateLiteralParser.interpolate;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TemplateLiteralParserTest {
	private static String quoted(String string) {
		return string.replace('\'', '"');
	}
	
	@Test
	public void testInterpolate() {
		assertThat(interpolate("A")).isEqualTo(quoted("'A'"));
		assertThat(interpolate("\"")).isEqualTo(quoted("'\\''"));
		assertThat(interpolate("${x}")).isEqualTo(quoted("''+(x)+''"));
		assertThat(interpolate("a${x}b")).isEqualTo(quoted("'a'+(x)+'b'"));
		assertThat(interpolate("a${x}${y}b")).isEqualTo(quoted("'a'+(x)+''+(y)+'b'"));
		assertThat(interpolate("a${\"b\"}c")).isEqualTo(quoted("'a'+('b')+'c'"));
		assertThat(interpolate("$")).isEqualTo(quoted("'$'"));
		assertThat(interpolate("$${a}")).isEqualTo(quoted("'$'+(a)+''"));
		assertThat(interpolate("\\u1234")).isEqualTo(quoted("'\\u1234'"));
		assertThat(interpolate("\\n")).isEqualTo(quoted("'\\n'"));
		assertThat(interpolate("\n")).isEqualTo(quoted("'\\n'"));
		assertThat(interpolate("\r")).isEqualTo(quoted("'\\n'"));
		assertThat(interpolate("\r\n")).isEqualTo(quoted("'\\n'"));
		assertThat(interpolate("\\\\")).isEqualTo(quoted("'\\\\'"));
		assertThat(interpolate("\u2028")).isEqualTo(quoted("'\\u2028'"));
		assertThat(interpolate("\u2029")).isEqualTo(quoted("'\\u2029'"));
		assertThat(interpolate("a\\\nb")).isEqualTo(quoted("'ab'"));
		assertThat(interpolate("a\\\rb")).isEqualTo(quoted("'ab'"));
		assertThat(interpolate("a\\\r\nb")).isEqualTo(quoted("'ab'"));
		assertThat(interpolate("\\${")).isEqualTo(quoted("'\\${'"));
		assertThat(interpolate("$\\{")).isEqualTo(quoted("'$\\{'"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testUnexpectedToken() {
		interpolate("${}");		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUnterminatedTemplateLiteral() {
		interpolate("${");		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUnterminatedEscapeSequence() {
		interpolate("\\");		
	}
}
