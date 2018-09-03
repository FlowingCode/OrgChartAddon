package com.flowingcode.vaadin.addons.orgchart;

import static com.flowingcode.vaadin.addons.orgchart.TemplateLiteralParser.rewrite;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class TemplateLiteralParserTest {
	private static String quoted(String string) {
		return string.replace('\"', '\uFF00').replace('\'', '"').replace('\uFF00', '\'');
	}
	
	@Test
	public void testrewrite() {
		assertThat(rewrite("A")).isEqualTo(quoted("'A'"));
		assertThat(rewrite("\"")).isEqualTo(quoted("'\\''"));
		assertThat(rewrite("${x}")).isEqualTo(quoted("''+(x)+''"));
		assertThat(rewrite("a${x}b")).isEqualTo(quoted("'a'+(x)+'b'"));
		assertThat(rewrite("a${x}${y}b")).isEqualTo(quoted("'a'+(x)+''+(y)+'b'"));
		assertThat(rewrite("a${\"b\"}c")).isEqualTo(quoted("'a'+('b')+'c'"));
		assertThat(rewrite("a${'b'}c")).isEqualTo(quoted("'a'+(\"b\")+'c'"));
		assertThat(rewrite("$")).isEqualTo(quoted("'$'"));
		assertThat(rewrite("$${a}")).isEqualTo(quoted("'$'+(a)+''"));
		assertThat(rewrite("\\u1234")).isEqualTo(quoted("'\\u1234'"));
		assertThat(rewrite("\\n")).isEqualTo(quoted("'\\n'"));
		assertThat(rewrite("\n")).isEqualTo(quoted("'\\n'"));
		assertThat(rewrite("\r")).isEqualTo(quoted("'\\n'"));
		assertThat(rewrite("\r\n")).isEqualTo(quoted("'\\n'"));
		assertThat(rewrite("\\\\")).isEqualTo(quoted("'\\\\'"));
		assertThat(rewrite("\u2028")).isEqualTo(quoted("'\\u2028'"));
		assertThat(rewrite("\u2029")).isEqualTo(quoted("'\\u2029'"));
		assertThat(rewrite("a\\\nb")).isEqualTo(quoted("'ab'"));
		assertThat(rewrite("a\\\rb")).isEqualTo(quoted("'ab'"));
		assertThat(rewrite("a\\\r\nb")).isEqualTo(quoted("'ab'"));
		assertThat(rewrite("\\${")).isEqualTo(quoted("'\\${'"));
		assertThat(rewrite("$\\{")).isEqualTo(quoted("'$\\{'"));
		assertThat(rewrite("${`${a}`}")).isEqualTo(quoted("''+((''+(a)+''))+''"));
		assertThat(rewrite("${`a${b}`}")).isEqualTo(quoted("''+(('a'+(b)+''))+''"));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testUnexpectedToken() {
		rewrite("${}");		
	}

	@Test(expected=IllegalArgumentException.class)
	public void testUnexpectedBacktick() {
		rewrite("`");		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUnterminatedTemplateLiteral() {
		rewrite("${");		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUnterminatedExpression() {
		rewrite("${a");		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUnterminatedStringLiteral1() {
		rewrite("${'");		
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testUnterminatedStringLiteral2() {
		rewrite("${\"");		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUnterminatedEscapeSequence() {
		rewrite("\\");		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testUnterminatedTemplate() {
		rewrite("${`");		
	}

}
