package aaron.spring.web.morecomplexcontrollerdemo.support;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
@Component
public class MoneyFormatter implements Formatter<Money> {
    // 仅处理 CNY 10.0/ 10.0 形式的字符串。
    @Override
    public Money parse(String text, Locale locale) throws ParseException {
        if(NumberUtils.isParsable(text)) {
            return Money.of(CurrencyUnit.of("CNY"),NumberUtils.createBigDecimal(text));
        } else if (StringUtils.isNotEmpty(text)) {
            String[] sp = StringUtils.split(
                    text, " "
            );
            if(sp != null && sp.length == 2 && NumberUtils.isParsable(sp[1])) {
                return Money.of(CurrencyUnit.of("CNY"),NumberUtils.createBigDecimal(sp[1]));
            }
        } else {
            throw new ParseException(text,0);
        }

        throw new ParseException(text,0);
    }

    @Override
    public String print(Money object, Locale locale) {
        if(object == null) return null;
        return object.getCurrencyUnit().getCode()+" "+ object.getAmount();
    }
}
