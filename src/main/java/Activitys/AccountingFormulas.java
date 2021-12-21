package Activitys;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AccountingFormulas {


    Formulas formulas;
    JdbcDao jdbcDao;
    DateFormat df;

    private java.sql.Date date_2016_01_01;
    private java.sql.Date date_2018_10_32;
    private java.sql.Date date_2017_10_24;

    private Double v1_075 = 1.075;
    private int p5 = 5;
    private String mon = "monthly";

    private String modeOfPayments;

    public String risk;
    public String zw;
    public Double paymentIncl_5_feeMirascon;
    public int paymentTotal;
    public int paymentPeriod;
    public int paymentBegin;
    public Date endDate;
    public Double refund;

    public void accountingFormulas() throws ParseException, SQLException {
        formulas = new Formulas();
        jdbcDao = new JdbcDao();
        modeOfPayments = jdbcDao.selectColumnStringValue_WithID(1, 17);
        df = new SimpleDateFormat("yyyy-MM-dd");
        date_2016_01_01 = new java.sql.Date(df.parse("2016-01-01").getTime());
        date_2018_10_32 = new java.sql.Date(df.parse("2018-10-32").getTime());
        date_2017_10_24 = new java.sql.Date(df.parse("2018-10-24").getTime());

        jdbcDao.updateAccountingColumnDateValue_WithIdAndValue(188,28, date_2017_10_24);
        // run all methods
        risk();
        zw();
        paymentIncl_5_feeMirascon();
        paymentTotal();
        paymentPeriod();
        paymentBegin();
        endDate();
        refund();
    }

    public int checkAllAccountingDates(Date checkDate, int greaterOrLesser) throws SQLException {
        int isFullfills = 0;
        Date jdbcDate;
        for (int date = 1; date <= 16; date++) {
            jdbcDate = jdbcDao.selectAccountingColumnDateValue_WithID(1, (date + 26));
            if (jdbcDate != null) {
                switch (greaterOrLesser) {
                    case 1:
                        if (jdbcDate.compareTo(checkDate) <= 0) {
                            isFullfills++;
                        }
                        break;
                    case 2:
                        if (jdbcDate.compareTo(checkDate) >= 0) {
                            isFullfills++;
                        }
                        break;
                    case 3:
                        if (jdbcDate.compareTo(checkDate) < 0) {
                            isFullfills++;
                        }
                        break;
                }
            }
        }
        //jdbcDao.selectAccountingColumnDateValue_WithID(1, 26);
        //jdbcDao.selectAccountingColumnDateValue_WithID(1, 42);
        return isFullfills;
    }

    public void risk() throws SQLException {

        if(formulas.netPremiumPerInstallment > 0) {
            risk = "C";
            jdbcDao.updateAccountingColumnStringValue_WithIdAndValue(1, 4, risk);
        } else {
            risk = "L";
            jdbcDao.updateAccountingColumnStringValue_WithIdAndValue(1, 4, risk);
        }
    }

    public void zw() throws SQLException {
        zw = modeOfPayments;
        jdbcDao.updateAccountingColumnStringValue_WithIdAndValue(1, 5, zw);
    }

    public void paymentIncl_5_feeMirascon() throws SQLException {
        paymentIncl_5_feeMirascon = formulas.totalYearlyPremiumNet + formulas.totalYearlyPremiumNet / v1_075 * p5;
        jdbcDao.updateAccountingColumnDoubleValue_WithIdAndValue(1, 11, paymentIncl_5_feeMirascon);
    }

    public void paymentTotal() throws SQLException {
        if (zw == mon) {
            if (checkAllAccountingDates(date_2016_01_01, 1) > 12 && checkAllAccountingDates(date_2018_10_32, 2) > 12) {
                paymentTotal = 12;
                jdbcDao.updateAccountingColumnIntValue_WithIdAndValue(1, 12, paymentTotal);
            } else {
                paymentTotal = (checkAllAccountingDates(date_2016_01_01, 1) & checkAllAccountingDates(date_2018_10_32, 2));
                jdbcDao.updateAccountingColumnIntValue_WithIdAndValue(1, 12, paymentTotal);
            }
        } else {
            if (checkAllAccountingDates(date_2016_01_01, 1) > 1 && checkAllAccountingDates(date_2018_10_32, 2) > 1) {
                paymentTotal = 1;
                jdbcDao.updateAccountingColumnIntValue_WithIdAndValue(1, 12, paymentTotal);
            } else {
                paymentTotal = (checkAllAccountingDates(date_2016_01_01, 1) & checkAllAccountingDates(date_2018_10_32, 2));
                jdbcDao.updateAccountingColumnIntValue_WithIdAndValue(1, 12, paymentTotal);
            }
        }
    }

    public void paymentPeriod() throws SQLException {
        if (zw == mon && checkAllAccountingDates(date_2016_01_01, 1) > 12 && checkAllAccountingDates(date_2018_10_32, 2) > 12) {
            if (checkAllAccountingDates(date_2016_01_01, 1) > 12 && checkAllAccountingDates(date_2018_10_32, 2) > 12) {
                paymentPeriod = 12 - checkAllAccountingDates(date_2016_01_01, 3);
                jdbcDao.updateAccountingColumnIntValue_WithIdAndValue(1, 13, paymentPeriod);
            } else {
                paymentPeriod = 0;
                jdbcDao.updateAccountingColumnIntValue_WithIdAndValue(1, 13, paymentPeriod);
            }
        } else {
            if (zw == mon && checkAllAccountingDates(date_2016_01_01, 1) > 1 && checkAllAccountingDates(date_2018_10_32, 2) > 1) {
                paymentPeriod = 0;
            } else {
                if (zw == mon) {
                    if (checkAllAccountingDates(date_2016_01_01, 1) > 12 && checkAllAccountingDates(date_2018_10_32, 2) > 12) {
                        paymentPeriod = 12;
                        jdbcDao.updateAccountingColumnIntValue_WithIdAndValue(1, 13, paymentPeriod);
                    } else {
                        paymentPeriod = (checkAllAccountingDates(date_2016_01_01, 1) & checkAllAccountingDates(date_2018_10_32, 2));
                        jdbcDao.updateAccountingColumnIntValue_WithIdAndValue(1, 13, paymentPeriod);
                    }
                } else {
                    if (checkAllAccountingDates(date_2016_01_01, 1) > 1 && checkAllAccountingDates(date_2018_10_32, 2) > 1) {
                        paymentPeriod = (checkAllAccountingDates(date_2016_01_01, 1) & checkAllAccountingDates(date_2018_10_32, 2));
                        jdbcDao.updateAccountingColumnIntValue_WithIdAndValue(1, 13, paymentPeriod);
                    }
                }
            }
        }
    }

    public void paymentBegin() throws SQLException {
        if(paymentTotal == 0){
            paymentBegin = 0;
            jdbcDao.updateAccountingColumnIntValue_WithIdAndValue(1,14, paymentBegin);
        } else {
            if (paymentTotal == 1){

            }
        }
    }

    public void endDate() throws SQLException{
        if (formulas.cancellationDate != null) {
            if (formulas.cancellationDate.compareTo(formulas.date_0) < 0) {
                endDate = formulas.cancellationDate;
                jdbcDao.updateAccountingColumnDateValue_WithIdAndValue(1, 17, endDate);
            } else {
                endDate = formulas.expireDate;
                jdbcDao.updateAccountingColumnDateValue_WithIdAndValue(1, 17, endDate);
            }
        }
    }

    public void refund() throws SQLException{
        if (zw == mon && paymentTotal * formulas.premiumInclTaxPerInstallment > formulas.totalYearlyPremiumIncl_Tax - formulas.cancellationsRefundGross){
            refund = paymentTotal * formulas.premiumInclTaxPerInstallment - formulas.totalYearlyPremiumIncl_Tax - formulas.cancellationsRefundGross;
            jdbcDao.updateAccountingColumnDoubleValue_WithIdAndValue(1, 19, refund);
        } else {
            if (zw == mon && paymentTotal > 0 ){
                refund = formulas.cancellationsRefundGross;
                jdbcDao.updateAccountingColumnDoubleValue_WithIdAndValue(1, 19, refund);
            } else {
                refund = 0.0;
                jdbcDao.updateAccountingColumnDoubleValue_WithIdAndValue(1, 19, refund);
            }
        }
    }

}

