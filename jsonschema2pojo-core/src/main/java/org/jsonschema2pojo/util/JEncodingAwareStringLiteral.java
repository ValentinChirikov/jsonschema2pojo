/**
 * Copyright © 2010-2020 Nokia
 *
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
 */

package org.jsonschema2pojo.util;

import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpressionImpl;
import com.sun.codemodel.JFormatter;
import org.apache.commons.lang3.text.translate.UnicodeUnescaper;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class JEncodingAwareStringLiteral extends JExpressionImpl {

        private final UnicodeUnescaper unicodeUnescaper = new UnicodeUnescaper();

        public final String str;
        public final String encoding;

        public JEncodingAwareStringLiteral(String what, String encoding) {
            this.str = what;
            this.encoding = encoding;
        }

        @Override
        public void generate(JFormatter f) {
            String input = JExpr.quotify('"', str);
            if (Charset.forName(encoding) == StandardCharsets.UTF_8) {
                input = unicodeUnescaper.translate(input);
            }
            f.p(input);
        }
    }
