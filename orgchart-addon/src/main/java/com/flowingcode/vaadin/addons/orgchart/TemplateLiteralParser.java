package com.flowingcode.vaadin.addons.orgchart;

import java.util.regex.Pattern;

class TemplateLiteralParser {
	
	static Pattern HEX_DIGIT = Pattern.compile("[0-9a-fA-F]");

	//https://www.ecma-international.org/ecma-262/6.0/#sec-template-literal-lexical-components
	
	// rewrites an ES6 template literal as an ES5 expression
	static String interpolate(String s) {
		StringBuilder sb = new StringBuilder();
		sb.ensureCapacity(s.length()+1);
		sb.append('"');
			
		//https://www.ecma-international.org/ecma-262/6.0/#sec-static-semantics-templatestrings	
		for (int i=0;i<s.length();i++) {
			char c = s.charAt(i);
			switch (c) {
				case '"':
					//escape quotes since we are using them for the string literals 
					sb.append('\\').append('"');
					break;
				case '\r':
					//The TV of TemplateCharacter :: LineTerminatorSequence is the TRV of LineTerminatorSequence.
					//The TRV of LineTerminatorSequence :: <CR><LF> is the sequence consisting of the code unit value 0x000A.
					//The TRV of LineTerminatorSequence :: <CR> is the code unit value 0x000A.
					if (i<s.length()-1 && s.charAt(i+1)=='\n') {
						++i;
					}
					//fallthough
				case '\n':
					//The TRV of LineTerminatorSequence :: <LF> is the code unit value 0x000A.
					sb.append("\\n");
					continue;
				case '\u2028':
					//The TRV of LineTerminatorSequence :: <LS> is the code unit value 0x2028.
					sb.append("\\u2028");
					continue;
				case '\u2029':
					//The TRV of LineTerminatorSequence :: <PS> is the code unit value 0x2029.
					sb.append("\\u2029");
					continue;
				case '\\':
					if (++i==s.length()) {
						throw new IllegalArgumentException("Unterminated escape sequence");
					}
					c = s.charAt(i);
					if (c=='\r' || c=='\n' || c=='\u2028' || c=='\u2029') {
						if (i<s.length()-1 && s.charAt(i+1)=='\n') {
							++i;
						}
						//LineTerminator :: <LF> | <CR> | <LS> | <PS>
						//The TV of LineContinuation :: \ LineTerminatorSequence is the empty code unit sequence.
						continue;
					} else {
						//EscapeSequence
						sb.append('\\').append(c);
						continue;
					}
				case '$': {
					//expression substitution
					if (i==s.length()-1 || s.charAt(i+1)!='{') {
						//this was a dangling dollar sign
						sb.append(c);
						continue;
					}
					
					i+=2;
					int end = s.indexOf('}',i);
					if (end<0) {
						throw new IllegalArgumentException("Unterminated template literal");
					}
					if (end==i) {
						throw new IllegalArgumentException("Unexpected token }");
					}
					
					//assume after TemplateHead there are no backticks and no string literals containing a closing curly bracket  
					String expr = s.substring(i, end);
					sb.append("\"+(").append(expr).append(")+\"");
					i=end;
					continue;
				}
				default:
					sb.append(c);
			}
		}
		
		sb.append('"');
		return sb.toString();
	}
}
