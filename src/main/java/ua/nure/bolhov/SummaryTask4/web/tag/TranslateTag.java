package ua.nure.bolhov.SummaryTask4.web.tag;

import ua.nure.bolhov.SummaryTask4.util.Constants;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class TranslateTag extends SimpleTagSupport {

    private Locale locale;
    private String prefix;
    private String key;

    public void setLocale(String locale) {
        if (Objects.isNull(locale) || locale.isEmpty()) {
            locale = Constants.DEFAULT_LOCALE_TAG;
        }
        this.locale = Locale.forLanguageTag(locale);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void doTag() throws IOException {
        if (Objects.nonNull(prefix) && Objects.nonNull(key) && Objects.nonNull(locale)) {
            JspWriter out = getJspContext().getOut();
            out.println(getTranslate());
        }
    }

    private String getTranslate() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(Constants.RESOURCE_BUNDLE, locale);
        return resourceBundle.getString(prefix+"." + key);
    }
}
