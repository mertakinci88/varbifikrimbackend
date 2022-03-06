package com.mcgb.varbifikrimbackend.util.helper;

import com.mcgb.varbifikrimbackend.enums.MembershipLengthTypeEnum;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtil {

    private final String DD_MM_YYYY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";

    public String convertDateToDDMMYYYYHHMMSS(Date date) {
        if (date == null)
            return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DD_MM_YYYY_HH_MM_SS);
        String formattedString = simpleDateFormat.format(date);
        return formattedString;
    }

    public int convertMembershipTypeLengthToInt(MembershipLengthTypeEnum membershipLengthType) {
        int month = 0;
        if (membershipLengthType.equals(MembershipLengthTypeEnum.SIX_MONTHS))
            month = 6;
        else if (membershipLengthType.equals(MembershipLengthTypeEnum.ONE_YEAR))
            month = 12;
        return month;
    }
}
