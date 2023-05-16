package org.gfg.expenseTracker.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalyticalResponse {

    private String userEmail;
    private Double oneDayAmount;
    private Double sevenDayAmount;
    private Double fifteenDayAmount;

}

// 7 , 1 month , 6 month, 1 year