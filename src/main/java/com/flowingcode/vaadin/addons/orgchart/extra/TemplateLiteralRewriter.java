/*-
 * #%L
 * OrgChart Add-on
 * %%
 * Copyright (C) 2017 - 2023 Flowing Code S.A.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.flowingcode.vaadin.addons.orgchart.extra;

/**
 * ES6 template literal parser that rewrites the literal as an ES5 expression. This class is
 * experimental and subject to change, with no guarantee of completeness (but it should work for
 * simple expressions).
 *
 * @author Javier Godoy / Flowing Code
 */
public class TemplateLiteralRewriter {

  // https://www.ecma-international.org/ecma-262/6.0/#sec-template-literal-lexical-components

  public static String rewriteFunction(String s) {
    return "return " + rewrite(s) + ";";
  }

  // rewrites an ES6 template literal as an ES5 expression
  public static String rewrite(String s) {
    StringBuilder sb = new StringBuilder();
    sb.ensureCapacity(s.length() + 1);
    rewriteTemplate(s, 0, sb, true);
    return sb.toString();
  }

  /**
   * Parse a Template literal in {@code s} starting at index {@code i} and rewrite it into {@code
   * sb}.
   *
   * @param s The ES6 template literal
   * @param i The index into {@code s} where the expression begins.
   * @param sb The result buffer
   * @throws IllegalArgumentException if the template literal is not closed with a backtick
   * @return the index of the closing backtick that ends the Template literal
   */
  private static int rewriteTemplate(String s, int i, StringBuilder sb, boolean implicit) {
    int begin = i;
    sb.append('"');
    // https://www.ecma-international.org/ecma-262/6.0/#sec-static-semantics-templatestrings
    outer:
    for (; i < s.length(); i++) {
      char c = s.charAt(i);
      switch (c) {
        case '"':
          // escape quotes in TemplateCharacters since we are using them for the string literals
          sb.append('\\').append('"');
          break;
        case '\r':
          // The TV of TemplateCharacter :: LineTerminatorSequence is the TRV of
          // LineTerminatorSequence.
          // The TRV of LineTerminatorSequence :: <CR><LF> is the sequence consisting of the code
          // unit value 0x000A.
          // The TRV of LineTerminatorSequence :: <CR> is the code unit value 0x000A.
          if (i < s.length() - 1 && s.charAt(i + 1) == '\n') {
            ++i;
          }
          // fallthrough
        case '\n':
          // The TRV of LineTerminatorSequence :: <LF> is the code unit value 0x000A.
          sb.append("\\n");
          continue;
        case '\u2028':
          // The TRV of LineTerminatorSequence :: <LS> is the code unit value 0x2028.
          sb.append("\\u2028");
          continue;
        case '\u2029':
          // The TRV of LineTerminatorSequence :: <PS> is the code unit value 0x2029.
          sb.append("\\u2029");
          continue;
        case '\\':
          if (++i == s.length()) {
            throw new IllegalArgumentException("Unterminated escape sequence at index " + i);
          }
          c = s.charAt(i);
          if (c == '\r' || c == '\n' || c == '\u2028' || c == '\u2029') {
            if (i < s.length() - 1 && s.charAt(i + 1) == '\n') {
              ++i;
            }
            // LineTerminator :: <LF> | <CR> | <LS> | <PS>
            // The TV of LineContinuation :: \ LineTerminatorSequence is the empty code unit
            // sequence.
            continue;
          } else {
            // EscapeSequence
            sb.append('\\').append(c);
            continue;
          }
        case '$':
          {
            if (i == s.length() - 1 || s.charAt(i + 1) != '{') {
              // this was a dangling dollar sign
              sb.append(c);
              continue;
            }

            sb.append("\"+(");
            // expression substitution
            i = rewriteExpression(s, i + 2, sb);
            sb.append(")+\"");
            continue;
          }
        case '`':
          {
            if (implicit) {
              throw new IllegalArgumentException("Unexpected backtick at index " + i);
            } else {
              break outer;
            }
          }
        default:
          sb.append(c);
      }
    }

    if (!implicit && i >= s.length()) {
      throw new IllegalArgumentException("Unterminated template at index " + begin);
    }

    // end of Template
    sb.append('"');
    return i;
  }

  /**
   * Parse a Expression in {@code s} starting at index {@code i} and rewrite it into {@code sb}.
   *
   * @param s The ES6 template literal
   * @param i The index into {@code s} where the expression begins.
   * @param sb The result buffer
   * @return the index where the corresponding TemplateSubstitutionTail starts.
   */
  private static int rewriteExpression(String s, int i, StringBuilder sb) {
    int begin = i;

    if (begin == s.length()) {
      throw new IllegalArgumentException("Expected expression at index " + begin);
    }
    if (s.charAt(begin) == '}') {
      throw new IllegalArgumentException("Unexpected token } at index " + begin);
    }

    for (; i < s.length(); i++) {
      char c = s.charAt(i);
      switch (c) {
        case '}':
          // TemplateSubstitutionTail
          return i;
        case '`':
          // nested template literal
          sb.append("(");
          i = rewriteTemplate(s, i + 1, sb, false);
          sb.append(")");
          continue;
        case '"':
          // fallthrough
        case '\'':
          int end = s.indexOf(c, i + 1);
          if (end < 0)
            throw new IllegalArgumentException("Unterminated string literal at index " + i);
          sb.append(s.substring(i, end)).append(c);
          i = end;
          continue;
        default:
          sb.append(c);
      }
    }

    throw new IllegalArgumentException("Expected TemplateSubstitutionTail at index " + begin);
  }
}
