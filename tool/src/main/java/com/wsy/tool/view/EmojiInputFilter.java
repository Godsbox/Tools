/*
 * Copyright (c) 2017-present 3000.com All Rights Reserved.
 */
package com.wsy.tool.view;

import android.text.InputFilter;
import android.text.Spanned;
import com.wsy.tool.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wsy.tool.util.ToastUtils.showShort;
import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.UNICODE_CASE;

/**
 * Emoji 表情过滤器。
 * @author wsy
 */
public class EmojiInputFilter implements InputFilter {

    /**
     * 正则。
     */
    private Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
            UNICODE_CASE | CASE_INSENSITIVE);

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        Matcher matcher = emoji.matcher(source);
        if (matcher.find()) {
            showShort(R.string.emoji_not_supported_yet);
            return "";
        }
        return null;
    }
}
